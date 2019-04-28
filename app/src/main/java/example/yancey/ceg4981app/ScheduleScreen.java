package example.yancey.ceg4981app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class ScheduleScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_screen);

        Button btnAddSchedule = findViewById(R.id.btnAddSchedule);
        Button btnRefreshSchedule = findViewById(R.id.btnRefreshSchedule);
        final Intent intent = new Intent(this, AddScheduleScreen.class);
        LinearLayout layout = findViewById(R.id.layoutSchedule);

        /**
         * BUTTONS
         */
        btnAddSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //user adds new schedule, not editing one
                Bundle b = new Bundle();
                b.putInt("key", 0);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        btnRefreshSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //refresh
            }
        });


        /**
         * LOAD IN ALL SCHEDULES
         */
        JSONObject response = null;
        JSONArray allSchedules = null;
        JSONObject s = null;

        String name = "";
        String body = "";
        int time = 0;
        String setting = "";
        int id = 0;

        try{
            response = Endpoints.listSchedule();
            allSchedules = response.getJSONArray("schedules");

            for(int i = 0; i < allSchedules.length(); i++){
                s = allSchedules.getJSONObject(i);

                //write over name and body
                name = s.getString("name");
                body = s.getString("body");
                time = s.getInt("time");
                setting = s.getString("setting");
                id = s.getInt("id");

                //add to layout, probably in a loop
                final Button btnSchedule = new Button(this);
                btnSchedule.setId(id);
                btnSchedule.setText(name);

                btnSchedule.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        int value = btnSchedule.getId(); //get value from row

                        Bundle b = new Bundle();
                        b.putInt("key", value);
                        intent.putExtras(b);
                        startActivity(intent);
                    }
                });
                /*TextView txtScheduleBody = new TextView(this);
                txtScheduleBody.setText(body);

                TextView txtScheduleTime = new TextView(this);
                txtScheduleTime.setText(String.valueOf(time));

                TextView txtScheduleSetting = new TextView(this);
                txtScheduleSetting.setText(setting);

                TextView txtSpace1 = new TextView(this);
                txtSpace1.setText("     ");
                TextView txtSpace2 = new TextView(this);
                txtSpace2.setText("     ");
                TextView txtSpace3 = new TextView(this);
                txtSpace3.setText("     ");*/

                layout.addView(btnSchedule);
                /*row.addView(txtSpace1);
                row.addView(txtScheduleBody);
                row.addView(txtSpace2);
                row.addView(txtScheduleTime);
                row.addView(txtSpace3);
                row.addView(txtScheduleSetting);*/
            }

            /*for(int j = 0; j < layout.getChildCount(); j++){
                Log.d("View", String.valueOf(layout.getChildAt(j).getId()));
            }*/

        } catch(Exception e) {
            Toast.makeText(getApplicationContext(),"Unable To Get Schedules",
                    Toast.LENGTH_SHORT).show();
            Log.d("All Schedules", response.toString());
        }

        // Endpoints.createSchedule("test", "testSchedule", 120, "1");
        // Endpoints.updateSchedule(8, "update", "updatedTestSchedule", 12000, "2");
        // Endpoints.deleteSchedule(8);

        //0 - Off
        //1 - Warm
        //2 - Low
        //3 - High
    }
}
