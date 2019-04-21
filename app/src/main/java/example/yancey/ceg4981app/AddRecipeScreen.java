package example.yancey.ceg4981app;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddRecipeScreen extends AppCompatActivity {

    private int value = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_recipe_screen);

        Bundle b = getIntent().getExtras();

        if(b != null){
            value = b.getInt("key");
        }

        Button btnClear = findViewById(R.id.btnClear);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnBack = findViewById(R.id.btnBack);

        final TextView txtRecipeName = findViewById(R.id.txtRecipeName);
        final TextView txtDescription = findViewById(R.id.txtDescription);
        final Intent intent = new Intent(this, RecipesScreen.class);
        intent.putExtra("Thing to do", "Go to back to recipe screen");

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
                //TO DO: save recipe name and description
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //if value = 1, ask before they leave
                if(value == 1){
                    //TO DO: ask before leaving
                }
                //if value = 0, just go back
                if (value == 0) {
                    startActivity(intent);
                }
            }
        });
        //TO DO: if a recipe is being edited, check to save changes before closing or going back
    }
}
