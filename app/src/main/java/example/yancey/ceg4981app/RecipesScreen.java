package example.yancey.ceg4981app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RecipesScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipes_screen);

        Button btnAddRecipe = findViewById(R.id.btnAddRecipe);
        LinearLayout layout = findViewById(R.id.layout);

        final Intent intent = new Intent(this, AddRecipeScreen.class);

        //if user edits recipe, key = 1
        //if user adds recipe, key = 0;

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

        //get one (1) recipe
        //JSONObject recipe = Endpoints.getRecipe("1");
        //Log.d("Recipe: ", recipe.toString());

        //get all recipes
        JSONObject allRecipes = null;
        try{
            allRecipes = Endpoints.listRecipe();
        } catch(Exception e) {
            Log.d("GET all recipes", allRecipes.toString());
        }

//        String s = "{\n" + "\"recipes\": [\n" + "{\n" + "\"body\": \"This is a chicken recipe\",\n" +
//                "\"date_created\": \"Thu, 18 Apr 2019 14:43:14 GMT\",\n" +"\"id\": 1,\n" +"\"name\": \"Chicken\"\n" +"}\n" +"]\n" +"}\n";

        JSONArray obj2;
        JSONObject obj3;

        String name;
        String body;

        try {
            //FOR ARRAY:
            obj2 = allRecipes.getJSONArray("recipes");

            for(int i = 0; i < obj2.length(); i++){
                obj3 = obj2.getJSONObject(0);
                name = obj3.getString("name");
                body = obj3.getString("body");

                TextView txtRecipe = new TextView(this);
                txtRecipe.setText("Recipe: " + name + " Description: " + body);
                layout.addView(txtRecipe);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
