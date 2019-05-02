package example.yancey.ceg4981app;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class HomeScreen extends AppCompatActivity {

    private int value = -1;
    private int setting = -1;
    private String temp = "";
    private Runnable r;
    private Handler h;
    private Runnable rTimer;
    private Handler hTimer;
    private int time = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        Button btnCook = findViewById(R.id.btnCook);
        final TextView tvTemp = findViewById(R.id.tvTemp);
        final TextView tvSetting = findViewById(R.id.tvSetting);
        final CheckBox chkSchedule = findViewById(R.id.chkSchedule);
        final TextView tvTimer = findViewById(R.id.tvTimer);

        //get whether schedule is starting or ending
        Bundle b = getIntent().getExtras();
        if (b != null) {
            value = b.getInt("key");
            setting = b.getInt("setting");
            time = b.getInt("timer");
        }

        if (value != 1) {
            //end timer
            chkSchedule.setChecked(false);
            tvTimer.setText("00:00");
        } else if (value == 1) {
            chkSchedule.setChecked(true);
            try {
                rTimer = new Runnable() {
                    @Override
                    public void run() {
                        if(time == 0){
                            tvSetting.setText("Off");
                            tvTimer.setText("00:00");
                            chkSchedule.setChecked(false);
                            Toast.makeText(getApplicationContext(),"Finished!",
                                    Toast.LENGTH_SHORT).show();
                        }
                        int displayTimeHr = (time / 3600);
                        int displayTimeMin = ((time % 3600)/60);
                        tvTimer.setText(displayTimeHr + ":" + displayTimeMin);

                        //Log.d("Time remaining:", String.valueOf(time));
                        //update time
                        if (time != 0) {
                            time = time - 1;
                            hTimer.postDelayed(rTimer, 1000);
                        }
                    }
                };

                hTimer = new Handler();
                hTimer.postDelayed(rTimer, 1000);
            } catch (Exception e) {
                tvTimer.setText("00:00");
            }
        }

        if (setting == 3) {
            tvSetting.setText("High");
        } else if (setting == 2) {
            tvSetting.setText("Low");
        } else if (setting == 1) {
            tvSetting.setText("Warm");
        } else if (setting == 0) {
            tvSetting.setText("Off");
        } else {
            tvSetting.setText("---");
        }

        //TO DO: When screen opens (or refreshes), redisplay the temperature, setting, and
        // if a schedule is being used

        try {
            r = new Runnable() {
                @Override
                public void run() {
                    temp = Endpoints.getTemp();     //prints temperature in Endpoints
                    temp = temp.trim();
                    tvTemp.setText(temp + " °F");
                    h.postDelayed(r, 5000);
                }
            };

            h = new Handler();
            h.postDelayed(r, 5000);
        } catch (Exception e) {
            tvTemp.setText("---");
        }

        final Intent intent = new Intent(this, CookScreen.class);
        intent.putExtra("Thing to do", "Go to cook screen");

        btnCook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}
