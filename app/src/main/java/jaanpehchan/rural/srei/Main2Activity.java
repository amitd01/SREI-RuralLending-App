package jaanpehchan.rural.srei;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by 1515012 on 10-05-2018.
 */

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_main);
        final EditText textOTP=findViewById(R.id.otp_textview);
        Button buttonRegister=findViewById(R.id.verify_and_continue_button);
        buttonRegister.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/Avenir-Book-01.ttf"));

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textOTP.getText().toString().equals(""))
                    return;
                Intent intent=new Intent(Main2Activity.this,OtpSuccessfulActivity.class);
                startActivity(intent);
            }
        });
    }
}


