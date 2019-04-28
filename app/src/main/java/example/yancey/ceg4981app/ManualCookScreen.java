package example.yancey.ceg4981app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
        final TextView tvSetting2 = findViewById(R.id.tvSetting2);

        //TO DO: only change setting when someone hits cook
        // NO NO NO: save previous setting in case back button is pressed
        //Button btnBack = findViewByID(R.id.btnBack);
        //on click--get previous setting and SET previous setting

        btnLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                tvSetting2.setText("Low");
            }
        });

        btnHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                tvSetting2.setText("High");
            }
        });

        btnWarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                tvSetting2.setText("Warm");
            }
        });

        btnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                tvSetting2.setText("Off");
            }
        });

        btnCookManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String setting = (String) tvSetting2.getText();
                String numericalSetting = "-1";

                if(setting == "Low"){
                    numericalSetting = "2";
                }else if(setting == "Warm"){
                    numericalSetting = "1";
                } else if(setting == "High"){
                    numericalSetting = "3";
                } else{
                    numericalSetting = "0";
                }
                //CHECK IF SCHEDULE NEEDS STOPPED

                Log.d("Setting", numericalSetting);
                Endpoints.setCookSetting(numericalSetting);
                //TO DO: set setting to this (probably change to int)
            }
        });

        //STOP schedule then set cook setting
    }
}
