package example.yancey.ceg4981app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class ScheduleScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_screen);

        Button btnAddSchedule = findViewById(R.id.btnAddSchedule);
        Button btnEditSchedule = findViewById(R.id.btnEditSchedule);
        Button btnDeleteSchedule = findViewById(R.id.btnDeleteSchedule);
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
                //user adds new recipe, not editing one
                Bundle b = new Bundle();
                b.putInt("key", 0);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        btnEditSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //user edits schedule
                int value = 1; //get value from row

                Bundle b = new Bundle();
                b.putInt("key", value);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        btnDeleteSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                int value = -1; //get value from row

                try{
                    Endpoints.deleteSchedule(value);
                    Toast.makeText(getApplicationContext(),"Schedule Successfully Deleted",
                            Toast.LENGTH_SHORT).show();
                }catch(Exception e){
                    Toast.makeText(getApplicationContext(),"Schedule Could Not Be Deleted",
                            Toast.LENGTH_SHORT).show();
                }
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
         * LOAD IN ALL RECIPES
         */
        JSONObject allRecipes = null;
        try{
            allRecipes = Endpoints.listRecipe();
        } catch(Exception e) {
            Log.d("All Recipes", allRecipes.toString());
        }

        //parse out recipes

        //add to layout, probably in a loop
        TextView txtScheduleName = new TextView(this);
        //txtRecipeName.setTextColor(000000);
        //txtRecipeName.setTextSize(14);
        txtScheduleName.setText("Test Schedule");

        TextView txtScheduleBody = new TextView(this);
        //txtRecipeBody.setTextColor(000000);
        //txtRecipeBody.setTextSize(14);
        txtScheduleBody.setText("This is a test");

        TextView txtScheduleTime = new TextView(this);
        //txtRecipeBody.setTextColor(000000);
        //txtRecipeBody.setTextSize(14);
        txtScheduleTime.setText("1200");

        TextView txtScheduleSetting = new TextView(this);
        //txtRecipeBody.setTextColor(000000);
        //txtRecipeBody.setTextSize(14);
        txtScheduleSetting.setText("Warm");

        TextView txtSpace1 = new TextView(this);
        txtSpace1.setText("     ");
        TextView txtSpace2 = new TextView(this);
        txtSpace2.setText("     ");
        TextView txtSpace3 = new TextView(this);
        txtSpace3.setText("     ");

        LinearLayout row = new LinearLayout(this);
        row.addView(txtScheduleName);
        row.addView(txtSpace1);
        row.addView(txtScheduleBody);
        row.addView(txtSpace2);
        row.addView(txtScheduleTime);
        row.addView(txtSpace3);
        row.addView(txtScheduleSetting);
        layout.addView(row);


        // Endpoints.createSchedule("test", "testSchedule", 120, "1");
        // Endpoints.updateSchedule(8, "update", "updatedTestSchedule", 12000, "2");
        // Endpoints.deleteSchedule(8);

        //0 - Off
        //1 - Warm
        //2 - Low
        //3 - High

        //get all schedules
    }
}
