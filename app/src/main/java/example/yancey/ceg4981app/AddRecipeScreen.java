package example.yancey.ceg4981app;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AddRecipeScreen extends AppCompatActivity {

    private int value = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_recipe_screen);

        Button btnClear = findViewById(R.id.btnClear);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnBack = findViewById(R.id.btnBack);

        final TextView txtRecipeName = findViewById(R.id.txtRecipeName);
        final TextView txtDescription = findViewById(R.id.txtDescription);
        final Intent intent = new Intent(this, RecipesScreen.class);
        intent.putExtra("Thing to do", "Go to back to recipe screen");

        //get recipeId for edit or whether this is a new recipe
        Bundle b = getIntent().getExtras();
        if(b != null){
            value = b.getInt("key");
        }

        String name = "";
        String body = "";

        try{
            Endpoints.getRecipe(String.valueOf(value));

            //parse out recipe name and body

            //write over name and body
            name = "";
            body = "";

            txtRecipeName.setText(name);
            txtDescription.setText(body);
        } catch(Exception e){
            Toast.makeText(getApplicationContext(),"Error Loading Recipe",Toast.LENGTH_SHORT).show();
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
                    } catch(Exception e){
                        Toast.makeText(getApplicationContext(),"Unable To Update",
                                Toast.LENGTH_SHORT).show();
                    }

                }

                //creating a recipe
                if (value == 0) {
                    try{
                        Endpoints.createRecipe( txtDescription.getText().toString(),
                                txtRecipeName.getText().toString());
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
    }
}
