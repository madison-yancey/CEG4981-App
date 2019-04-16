package example.yancey.ceg4981app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONObject;

public class RecipesScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipes_screen);

        Button btnAddRecipe = findViewById(R.id.btnAddRecipe);

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
        JSONObject recipe = Endpoints.getRecipe("1");

        //get all recipes
        JSONObject allRecipes = Endpoints.listRecipe();

        //array

        //then parse array

    }
}
