package example.yancey.ceg4981app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class RecipesScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipes_screen);

        Button btnAddRecipe = findViewById(R.id.btnAddRecipe);
        Button btnEditRecipe = findViewById(R.id.btnEditRecipe);
        Button btnDeleteRecipe = findViewById(R.id.btnDeleteRecipe);
        Button btnRefreshRecipe = findViewById(R.id.btnRefreshRecipe);
        LinearLayout layout = findViewById(R.id.layout);
        final Intent intent = new Intent(this, AddRecipeScreen.class);

        //if user edits recipe, key = 1
        //if user adds recipe, key = 0;

        /**
         * BUTTONS
         */
        btnAddRecipe.setOnClickListener(new View.OnClickListener() {
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

        btnEditRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //user edits recipe
                int value = 1; //get value from row

                Bundle b = new Bundle();
                b.putInt("key", value);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        btnDeleteRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                int value = -1; //get value from row

                try{
                    Endpoints.deleteRecipe(value);
                    Toast.makeText(getApplicationContext(),"Recipe Successfully Deleted",Toast.LENGTH_SHORT).show();
                }catch(Exception e){
                    Toast.makeText(getApplicationContext(),"Recipe Could Not Be Deleted",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRefreshRecipe.setOnClickListener(new View.OnClickListener() {
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
        TextView txtRecipeName = new TextView(this);
        //txtRecipeName.setTextColor(000000);
        //txtRecipeName.setTextSize(14);
        txtRecipeName.setText("Recipe Name");

        TextView txtSpace = new TextView(this);
        txtSpace.setText("                  ");

        TextView txtRecipeBody = new TextView(this);
        //txtRecipeBody.setTextColor(000000);
        //txtRecipeBody.setTextSize(14);
        txtRecipeBody.setText("Recipe Body");

        LinearLayout row = new LinearLayout(this);
        row.addView(txtRecipeName);
        row.addView(txtSpace);
        row.addView(txtRecipeBody);
        layout.addView(row);

        //get one (1) recipe
        //JSONObject recipe = Endpoints.getRecipe("1");
        //Log.d("Recipe: ", recipe.toString());


//        String s = "{\n" + "\"recipes\": [\n" + "{\n" + "\"body\": \"This is a chicken recipe\",\n" +
//                "\"date_created\": \"Thu, 18 Apr 2019 14:43:14 GMT\",\n" +"\"id\": 1,\n" +"\"name\": \"Chicken\"\n" +"}\n" +"]\n" +"}\n";

//        JSONArray obj2 = new JSONArray();
//        JSONObject obj3;
//
//        String name;
//        String body;
//
//        try {
//            //FOR ARRAY:
//            obj2.put(allRecipes);
//            //obj2 = allRecipes.put("recipes");
//
//            for(int i = 0; i < obj2.length(); i++){
//                obj3 = obj2.getJSONObject(i);
//                name = obj3.getString("name");
//                body = obj3.getString("body");
//
//                TextView txtRecipe = new TextView(this);
//                txtRecipe.setText("Recipe: " + name + " Description: " + body);
//                layout.addView(txtRecipe);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }
}
