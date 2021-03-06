package example.yancey.ceg4981app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class AddScheduleScreen extends AppCompatActivity {

    private int value = -1;
    private int recipeSchedule = 0;
    private int recipeId = -1;
    private int time = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_schedule_screen);

        Button btnClear = findViewById(R.id.btnClear2);
        Button btnSave = findViewById(R.id.btnSave2);
        Button btnBack = findViewById(R.id.btnBack2);
        Button btnDelete = findViewById(R.id.btnDeleteSchedule);
        Button btnCook = findViewById(R.id.btnCookSchedule);

        final TextView txtScheduleName = findViewById(R.id.txtScheduleName);
        final TextView txtScheduleDescription = findViewById(R.id.txtScheduleDescription);
        final TextView txtScheduleTime = findViewById(R.id.txtScheduleTime);
        final RadioButton rbHigh = findViewById(R.id.rbHigh);
        final RadioButton rbLow = findViewById(R.id.rbLow);
        final RadioButton rbWarm = findViewById(R.id.rbWarm);
        final RadioGroup group = findViewById(R.id.radioGroup);
        final Intent intent = new Intent(this, ScheduleScreen.class);
        final Intent intentHome = new Intent(this, HomeScreen.class);

        //get scheduleId for edit or whether this is a new schedule
        Bundle b = getIntent().getExtras();
        if (b != null) {
            value = b.getInt("key");
            recipeSchedule = b.getInt("recipeSchedule");
            recipeId = b.getInt("recipeId");
        }

        if(recipeSchedule != 0){
            //change to say save recipe whatever
            btnSave.setText("LINK");
            btnBack.setVisibility(View.INVISIBLE);
            btnDelete.setVisibility(View.INVISIBLE);
            btnClear.setVisibility(View.INVISIBLE);
            btnCook.setVisibility(View.INVISIBLE);
        } else{
            btnSave.setText("SAVE");
            btnBack.setVisibility(View.VISIBLE);
            btnDelete.setVisibility(View.VISIBLE);
            btnClear.setVisibility(View.VISIBLE);
            btnCook.setVisibility(View.VISIBLE);
        }

        if (value != 0) {
            String name = "";
            String body = "";
            String setting = "";

            try {
                //parse out schedule
                JSONObject response = null;
                JSONObject theSchedule = null;

                response = Endpoints.getSchedule(String.valueOf(value));
                theSchedule = response.getJSONObject("schedule");

                //write over name and body
                name = theSchedule.getString("name");
                body = theSchedule.getString("body");
                time = theSchedule.getInt("time");
                setting = theSchedule.getString("setting");

                Log.d("SETTING", setting);
                txtScheduleName.setText(name);
                txtScheduleDescription.setText(body);
                txtScheduleTime.setText(String.valueOf(time));

                //TO DO: FIX THIS
                if (setting == "3") {
                    rbHigh.setChecked(true);
                    /*rbLow.setChecked(false);
                    rbWarm.setChecked(false);*/
                } else if (setting == "2") {
                    //rbHigh.setChecked(false);
                    rbLow.setChecked(true);
                    //rbWarm.setChecked(false);
                } else if (setting == "1") {
                    /*rbHigh.setChecked(false);
                    rbLow.setChecked(false);*/
                    rbWarm.setChecked(true);
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Error Loading Schedule", Toast.LENGTH_SHORT).show();
            }
        }

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtScheduleName.setText("");
                txtScheduleDescription.setText("");
                txtScheduleTime.setText("");
                rbHigh.setChecked(true);
                rbLow.setChecked(false);
                rbWarm.setChecked(false);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //updating a schedule

                if(recipeSchedule == 0) {

                    if (value != 0) {
                        try {
                            String rbSetting = "0";

                            if (rbHigh.isChecked()) {
                                rbSetting = "3";
                            } else if (rbLow.isChecked()) {
                                rbSetting = "2";
                            } else if (rbWarm.isChecked()) {
                                rbSetting = "1";
                            }

                            Endpoints.updateSchedule(value, txtScheduleDescription.getText().toString(),
                                    txtScheduleName.getText().toString(),
                                    Integer.parseInt(txtScheduleTime.getText().toString()),
                                    rbSetting);
                            Toast.makeText(getApplicationContext(), "Schedule Updated",
                                    Toast.LENGTH_SHORT).show();

                            startActivity(intentHome);
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Unable To Update",
                                    Toast.LENGTH_SHORT).show();

                            startActivity(intentHome);
                        }
                    }

                    //creating a schedule
                    if (value == 0) {

                        int rbSetting = group.getCheckedRadioButtonId();
                        rbSetting = rbSetting++;

                        Log.d("Setting", String.valueOf(rbSetting));
                        try {
                            Endpoints.createSchedule(txtScheduleDescription.getText().toString(),
                                    txtScheduleName.getText().toString(),
                                    Integer.parseInt(txtScheduleTime.getText().toString()),
                                    String.valueOf(rbSetting));
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Unable To Create Schedule",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                } else{
                    try{
                        //create recipe schedule
                        Endpoints.createRecipeSchedule("test", value, recipeId);
                        Toast.makeText(getApplicationContext(), "Recipe-Schedule Linked",
                                Toast.LENGTH_SHORT).show();

                        //go to home screen
                        startActivity(intentHome);
                    } catch(Exception e){
                        Toast.makeText(getApplicationContext(), "Recipe-Schedule Could Not Be Linked",
                                Toast.LENGTH_SHORT).show();
                        startActivity(intentHome);
                    }
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if value = 1, ask before they leave
                if (value != 0) {
                    startActivity(intent);
                }
                //if value = 0, just go back
                if (value == 0) {
                    startActivity(intent);
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Endpoints.deleteSchedule(value);
                    Toast.makeText(getApplicationContext(), "Schedule Successfully Deleted",
                            Toast.LENGTH_SHORT).show();

                    startActivity(intentHome);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Schedule Could Not Be Deleted",
                            Toast.LENGTH_SHORT).show();

                    startActivity(intentHome);
                }
            }
        });

        btnCook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Endpoints.startSchedule(String.valueOf(value));
                    Toast.makeText(getApplicationContext(), "Schedule Started",
                            Toast.LENGTH_SHORT).show();

                    //pass back to the homescreen 0 if no schedule
                    //1 bc schedule started and 1 means start timer and check checkbox

                    int setting = 0;

                    if (rbHigh.isChecked()) {
                        setting = 3;
                    } else if (rbLow.isChecked()) {
                        setting = 2;
                    } else if (rbWarm.isChecked()) {
                        setting = 1;
                    }

                    Bundle b = new Bundle();
                    b.putInt("setting", setting);
                    //start timer
                    //start timer
                    b.putInt("key", 1);
                    b.putInt("timer", time);
                    intentHome.putExtras(b);
                    startActivity(intentHome);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Unable To Start Schedule",
                            Toast.LENGTH_SHORT).show();
                    startActivity(intentHome);
                }
            }
        });
    }
}
