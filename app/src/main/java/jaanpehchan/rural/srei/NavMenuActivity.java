package jaanpehchan.rural.srei;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NavMenuActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textItem1, textItem2, textItem3,
            textItem4, textItem5, textItem6, textItem7, textItem8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_menu);
        LinearLayout leadDetails=findViewById(R.id.lead_details);
        leadDetails.setOnClickListener(this);
        LinearLayout JpQueue=findViewById(R.id.jp_queue);
        JpQueue.setOnClickListener(this);
        LinearLayout installmentCalculater=findViewById(R.id.installmentCalculator);
        installmentCalculater.setOnClickListener(this);
        textItem1 = findViewById(R.id.list_item1);
        textItem2 = findViewById(R.id.list_item2);
        textItem3 = findViewById(R.id.list_item3);
        textItem4 = findViewById(R.id.list_item4);
        textItem5 = findViewById(R.id.list_item5);
        textItem6 = findViewById(R.id.list_item6);
        textItem7 = findViewById(R.id.list_item7);
        textItem8 = findViewById(R.id.list_item8);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.jp_queue: startActivity(new Intent(NavMenuActivity.this,JpQueue.class));
                break;
            case R.id.lead_details:startActivity(new Intent(NavMenuActivity.this,LeadDetailsActivity.class));
                break;
            case R.id.installmentCalculator:startActivity(new Intent(NavMenuActivity.this,InstallmentCalculatorActivity.class));

        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
