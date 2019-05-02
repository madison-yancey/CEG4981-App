package example.yancey.ceg4981app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CookScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cook_screen);

        Button btnRecipes = findViewById(R.id.btnRecipes);
        Button btnSchedule = findViewById(R.id.btnSchedule);
        Button btnManualCook = findViewById(R.id.btnManual);
        Button btnBack = findViewById(R.id.btnBackToHome);

        final Intent intentRecipes = new Intent(this, RecipesScreen.class);
        intentRecipes.putExtra("Thing to do", "Go to recipes screen");

        final Intent intentSchedules = new Intent(this, ScheduleScreen.class);
        intentSchedules.putExtra("Thing to do", "Go to schedules screen");

        final Intent intentManual = new Intent(this, ManualCookScreen.class);
        intentManual.putExtra("Thing to do", "Go to manual cook screen");

        final Intent intentHome = new Intent(this, HomeScreen.class);

        btnRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(intentRecipes);
            }
        });

        btnSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(intentSchedules);
            }
        });

        btnManualCook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(intentManual);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(intentHome);
            }
        });
    }
}
