package example.yancey.ceg4981app;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class AddRecipeScreen extends AppCompatActivity {

    private int value = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_recipe_screen);

        Button btnClear = findViewById(R.id.btnClear);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnBack = findViewById(R.id.btnBack);
        Button btnDelete = findViewById(R.id.btnDeleteRecipe);
        Button btnLink = findViewById(R.id.btnLink);

        final TextView txtRecipeName = findViewById(R.id.txtRecipeName);
        final TextView txtDescription = findViewById(R.id.txtDescription);
        final Intent intent = new Intent(this, RecipesScreen.class);
        final Intent intentScheduleScreen = new Intent(this, AddScheduleScreen.class);
        final Intent intentChooseSchedule = new Intent(this, ScheduleScreen.class);
        final Intent intentHome = new Intent(this, HomeScreen.class);

        //get recipeId for edit or whether this is a new recipe
        Bundle b = getIntent().getExtras();
        if(b != null){
            value = b.getInt("key");
        }

        if(value != 0){
            try{
                String name = "";
                String body = "";

                //parse out recipe name and body
                JSONObject response = null;
                JSONObject theRecipe = null;

                response = Endpoints.getRecipe(String.valueOf(value));
                theRecipe = response.getJSONObject("recipe");

                //write over name and body
                name = theRecipe.getString("name");
                body = theRecipe.getString("body");

                txtRecipeName.setText(name);
                txtDescription.setText(body);
            } catch(Exception e){
                Toast.makeText(getApplicationContext(),"Error Loading Recipe",
                        Toast.LENGTH_SHORT).show();
            }
        }

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                txtRecipeName.setText("");
                txtDescription.setText("");
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //updating a recipe
                if(value != 0){
                    try{
                        Endpoints.updateRecipe(value, txtDescription.getText().toString(),
                                txtRecipeName.getText().toString());
                        Toast.makeText(getApplicationContext(),"Recipe Updated",
                                Toast.LENGTH_SHORT).show();
                        startActivity(intentHome);
                    } catch(Exception e){
                        Toast.makeText(getApplicationContext(),"Unable To Update",
                                Toast.LENGTH_SHORT).show();
                        startActivity(intentHome);
                    }

                }

                //creating a recipe
                if (value == 0) {
                    try{
                        Endpoints.createRecipe( txtDescription.getText().toString(),
                                txtRecipeName.getText().toString());
                        Toast.makeText(getApplicationContext(),"Recipe Created",
                                Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    } catch(Exception e){
                        Toast.makeText(getApplicationContext(),"Unable To Create Recipe",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //if value = 1, ask before they leave
                if(value != 0){
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
            public void onClick(View view)
            {
            try{
                Endpoints.deleteRecipe(value);
                Toast.makeText(getApplicationContext(),"Recipe Successfully Deleted",
                        Toast.LENGTH_SHORT).show();
                startActivity(intentHome);
            }catch(Exception e){
                Toast.makeText(getApplicationContext(),"Recipe Could Not Be Deleted",
                        Toast.LENGTH_SHORT).show();
                startActivity(intentHome);
            }
            }
        });

        btnLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get recipe id
                JSONObject result = null;

                try{
                    result = Endpoints.getRecipeScheduleByRecipeId(value);
                    Log.d("Response:", result.toString());

                    if(result != null){
                        int scheudleId = 0;
                        JSONObject theRecipeSchedule = null;

                        theRecipeSchedule = result.getJSONObject("recipeSchedule");

                        scheudleId = theRecipeSchedule.getInt("schedule_id");

                        Bundle b = new Bundle();
                        b.putInt("key", scheudleId);
                        intentScheduleScreen.putExtras(b);
                        startActivity(intentScheduleScreen);
                    } /*else{
                        Toast.makeText(getApplicationContext(),"No Link Found",
                                Toast.LENGTH_SHORT).show();

                        Bundle b = new Bundle();
                        b.putInt("key", -1);
                        b.putInt("recipeId", value);
                        intentChooseSchedule.putExtras(b);
                        startActivity(intentChooseSchedule);
                    }*/
                } catch(Exception e){
                    Toast.makeText(getApplicationContext(),"No Link Found",
                            Toast.LENGTH_SHORT).show();

                    Bundle b = new Bundle();
                    b.putInt("key", -1);
                    b.putInt("recipeId", value);
                    intentChooseSchedule.putExtras(b);
                    startActivity(intentChooseSchedule);
                }
            }
        });
    }
}
