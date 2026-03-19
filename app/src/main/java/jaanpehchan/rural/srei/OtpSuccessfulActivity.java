package jaanpehchan.rural.srei;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class OtpSuccessfulActivity extends AppCompatActivity {
    private static final int TIME_TO_START_ACTIVITY = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.congratulations);
/*        new Handler().postAtTime(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(OtpSuccessfulActivity.this, NavMenuActivity.class);
                startActivity(intent);
            }
        }, TIME_TO_START_ACTIVITY);*/
        Thread t=new Thread() {
            public void run() {

                try {
                    //sleep thread for 10 seconds, time in milliseconds
                    sleep(TIME_TO_START_ACTIVITY);

                    //start new activity
                    Intent i=new Intent(OtpSuccessfulActivity.this,NavMenuActivity.class);
                    getSharedPreferences(MainActivity.LOGIN, 0).edit().putBoolean("successfull", true).apply();
                    startActivity(i);

                    //destroying Splash activity
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        //start thread
        t.start();
    }
}
