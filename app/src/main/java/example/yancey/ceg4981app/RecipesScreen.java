package example.yancey.ceg4981app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RecipesScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipes_screen);

        Button btnAddRecipe = findViewById(R.id.btnAddRecipe);

        final Intent intent = new Intent(this, AddRecipeScreen.class);
        intent.putExtra("Thing to do", "Go to cook screen");

        btnAddRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(intent);
            }
        });
    }
}
