package example.yancey.ceg4981app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONObject;

public class ScheduleScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_screen);

        Button btnAddSchedule = findViewById(R.id.btnAddSchedule);

        final Intent intent = new Intent(this, AddScheduleScreen.class);
        intent.putExtra("Thing to do", "Go to cook screen");

        btnAddSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(intent);
            }
        });

        // Endpoints.createSchedule("test", "testSchedule", 120, "1");
        // Endpoints.updateSchedule(8, "update", "updatedTestSchedule", 12000, "2");
        // Endpoints.deleteSchedule(8);
        // Endpoints.getTemp();     prints temperature in Endpoints
        
        //0 - Off
        //1 - Warm
        //2 - Low
        //3 - High

        //get one (1) schedule
        //Endpoints.getSchedule("1");

        //get all schedules
    }
}


