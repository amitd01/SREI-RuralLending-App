package jaanpehchan.rural.srei;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import Helpers.Animations;

public class MainActivity extends AppCompatActivity  {

    public   static final String LOGIN="abcdef";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText editSMCode=findViewById(R.id.sm_code);
        final EditText editMobileNo=findViewById(R.id.mobile_no);
        Button buttonRegister=findViewById(R.id.button_register);
        final ScrollView registerView = findViewById(R.id.registerView);
        final ScrollView mobileVerificationView = findViewById(R.id.mobileVerificationView);

        if(getSharedPreferences(LOGIN,0).getBoolean("successfull",false)){
            Intent intent = new Intent(MainActivity.this, NavMenuActivity.class);
            startActivity(intent);
        }
        buttonRegister.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/Avenir-Book-01.ttf"));
      //  editOTP.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/Avenir-Book-01.ttf"));

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //registerView.setVisibility(View.GONE);
                /*Animation fromBottom = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.enter_from_left);
                registerView.setAnimation(fromBottom);
                mobileVerificationView.setVisibility(View.VISIBLE);*/
                if(editMobileNo.getText().toString().equals("") || editSMCode.getText().toString().equals(""))
                    return;
            //    if(!getSharedPreferences(LOGIN,0).getBoolean("successfull",false)) {
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                       startActivity(intent);
              //  }
              /*  Animations.fading(getApplicationContext(),registerView,mobileVerificationView);*/
            }
        });
    }


}


