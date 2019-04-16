package example.yancey.ceg4981app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddRecipeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_recipe_screen);

        Button btnClear = findViewById(R.id.btnClear);
        final TextView txtRecipeName = findViewById(R.id.txtRecipeName);
        final TextView txtDescription = findViewById(R.id.txtDescription);
        //final Intent intent = new Intent(this, CookScreen.class);
        //intent.putExtra("Thing to do", "Go to cook screen");

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                txtRecipeName.setText("");
                txtDescription.setText("");
            }
        });
    }
}
