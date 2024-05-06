package sg.edu.np.mad.madpractical4;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

//add imports here
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize a new User object
        User user = new User ("John Doe", "MAD Developer", 1, false);

        // Get the TextViews and Button from the layout
        TextView tvName = findViewById(R.id.tvView2);
        TextView tvDescription = findViewById(R.id.tvView3);
        Button btnFollow = findViewById(R.id.button1);
        Button btnMsg = findViewById(R.id.button2);

        // Retrieve the integer value from the Intent
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("Name");
        String description = bundle.getString("Description");
        Boolean followed = bundle.getBoolean("Followed");

        // Set the TextViews with the User's name, description and default button message
        tvName.setText(name);
        tvDescription.setText(description);
        if (followed){
            btnFollow.setText("Unfollow");
        } else {
            btnFollow.setText("Follow");
        }

        btnFollow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (user.followed){
                    user.followed = false;
                    btnFollow.setText("Follow");
                    Toast.makeText(MainActivity.this, "Unfollowed", Toast.LENGTH_SHORT).show();
                } else {
                    user.followed = true;
                    btnFollow.setText("Unfollow");
                    Toast.makeText(MainActivity.this, "Followed", Toast.LENGTH_SHORT).show();
                }
                Log.i(TAG, "Status: " + user.followed);
            }
        });

//        btnMsg.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                Intent MainActivity = new Intent(MainActivity.this, MessageGroup.class);
//                startActivity(MainActivity);
//            }
//        });
    }
}
