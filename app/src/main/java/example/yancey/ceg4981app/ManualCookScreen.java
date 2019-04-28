package example.yancey.ceg4981app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class ManualCookScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manual_cook_screen);

        Button btnOff = findViewById(R.id.btnOff);
        Button btnLow = findViewById(R.id.btnLow);
        Button btnHigh = findViewById(R.id.btnHigh);
        Button btnWarm = findViewById(R.id.btnWarm);
        Button btnCookManual = findViewById(R.id.btnCookManual);
        final CheckBox chkSchedule = findViewById(R.id.chkSchedule);
        final TextView tvSetting2 = findViewById(R.id.tvSetting2);
        final Intent intent = new Intent(this, HomeScreen.class);

        btnLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvSetting2.setText("Low");
            }
        });

        btnHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvSetting2.setText("High");
            }
        });

        btnWarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvSetting2.setText("Warm");
            }
        });

        btnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvSetting2.setText("Off");
            }
        });

        btnCookManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String setting = (String) tvSetting2.getText();
                String numericalSetting = "-1";

                if (setting == "Low") {
                    numericalSetting = "2";
                } else if (setting == "Warm") {
                    numericalSetting = "1";
                } else if (setting == "High") {
                    numericalSetting = "3";
                } else {
                    numericalSetting = "0";
                }

                //CHECK IF SCHEDULE NEEDS STOPPED
                try {
                    Endpoints.stopSchedule();
                    //chkSchedule.setChecked(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    Endpoints.setCookSetting(numericalSetting);
                    Toast.makeText(getApplicationContext(), "Setting Updated", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    //go back to homescreen and pass 0 which means no schedule and clear timer
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
