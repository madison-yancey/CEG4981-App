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

import org.json.JSONArray;
import org.json.JSONObject;

public class RecipesScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipes_screen);

        Button btnAddRecipe = findViewById(R.id.btnAddRecipe);
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
        JSONObject response = null;
        JSONArray allRecipes = null;
        JSONObject r = null;

        String name = "";
        String body = "";
        int id = 0;

        try{
            response = Endpoints.listRecipe();
            allRecipes = response.getJSONArray("recipes");

            for(int i = 0; i < allRecipes.length(); i++){
                r = allRecipes.getJSONObject(i);

                //write over name and body
                name = r.getString("name");
                body = r.getString("body");
                id = r.getInt("id");

                //add to layout, probably in a loop
                final Button btnRecipe = new Button(this);
                btnRecipe.setId(id);
                btnRecipe.setText(name + ": " + body);

                btnRecipe.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        int value = btnRecipe.getId(); //get value from row

                        Bundle b = new Bundle();
                        b.putInt("key", value);
                        intent.putExtras(b);
                        startActivity(intent);
                    }
                });

                /*TextView txtSpace = new TextView(this);
                txtSpace.setText("                  ");

                TextView txtRecipeBody = new TextView(this);
                txtRecipeBody.setText(body);*/

               /* row.addView(txtSpace);
                row.addView(txtRecipeBody);*/
                layout.addView(btnRecipe);
            }

        } catch(Exception e) {
            Log.d("All Recipes", response.toString());
        }


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
