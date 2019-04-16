package example.yancey.ceg4981app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        Button btnCook = findViewById(R.id.btnCook);
        TextView tvTemp = findViewById(R.id.tvTemp);
        TextView tvSetting = findViewById(R.id.tvSetting);
        CheckBox chkSchedule = findViewById(R.id.chkSchedule);

        //TO DO: When screen opens (or refreshes), redisplay the temperature, setting, and
        // if a schedule is being used

        final Intent intent = new Intent(this, CookScreen.class);
        intent.putExtra("Thing to do", "Go to cook screen");

        btnCook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(intent);
            }
        });
    }
}
