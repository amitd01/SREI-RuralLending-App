package jaanpehchan.rural.srei;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FeedbackFormActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String ACTIVITY_TITLE = "FEEDBACK FORM";
    private TextView text_feedback1, text_feedback2, text_feedback3, text_feedback4;
    private LinearLayout layoutLike, layoutUnLike;
    private ImageView imageItem4, imageItem1, imageItem2, imageItem3;
    private ImageView thumbUp,thumbDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_form);
        View view = findViewById(R.id.action_bar_container);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.back_arrow);
        TextView textTitle = view.findViewById(R.id.activity_title);
        textTitle.setText(ACTIVITY_TITLE);
        textTitle.setTextSize(20);
        text_feedback1 = findViewById(R.id.feedback_mark_1);
        text_feedback2 = findViewById(R.id.feedback_mark_2);
        text_feedback3 = findViewById(R.id.feedback_mark_3);
        text_feedback4 = findViewById(R.id.feedback_mark_4);
        Button buttonFeedbackSubmit = findViewById(R.id.button_feedback_submit);
        buttonFeedbackSubmit.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Avenir-Book-01.ttf"));
        buttonFeedbackSubmit.setOnClickListener(this);
        imageItem1 = findViewById(R.id.item1);
        imageItem2 = findViewById(R.id.item2);
        imageItem3 = findViewById(R.id.item3);
        imageItem4 = findViewById(R.id.item4);
        thumbUp=findViewById(R.id.thumb_up);
        thumbDown=findViewById(R.id.thumb_down);
        layoutLike = findViewById(R.id.layout_like);
        layoutUnLike = findViewById(R.id.layout_unlike);
        layoutLike.setOnClickListener(this);
        layoutUnLike.setOnClickListener(this);
        imageItem1.setOnClickListener(this);
        imageItem1.setTag(R.drawable.checkbox_empty_icon);
        imageItem2.setOnClickListener(this);
        imageItem2.setTag(R.drawable.checkbox_empty_icon);
        imageItem3.setOnClickListener(this);
        imageItem3.setTag(R.drawable.checkbox_empty_icon);
        imageItem4.setOnClickListener(this);
        imageItem4.setTag(R.drawable.checkbox_empty_icon);
    }

    private int flag = 0;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_feedback_submit:
              /*  if (imageItem1.getTag().equals(R.drawable.checkbox_checked_icon) &&
                        imageItem2.getTag().equals(R.drawable.checkbox_checked_icon) &&
                        imageItem3.getTag().equals(R.drawable.checkbox_checked_icon) &&
                        imageItem4.getTag().equals(R.drawable.checkbox_checked_icon) && flag == 1)*/
                    finish();
                break;
            case R.id.item1:
                checkFeedBack((ImageView) v);
                break;
            case R.id.item2:
                checkFeedBack((ImageView) v);
                break;
            case R.id.item3:
                checkFeedBack((ImageView) v);
                break;
            case R.id.item4:
                checkFeedBack((ImageView) v);
                break;
            case R.id.layout_like:
             //   flag = 1;
                thumbDown.setColorFilter(ContextCompat.getColor(this, R.color.unselected), PorterDuff.Mode.SRC_ATOP);
                thumbUp.setColorFilter(ContextCompat.getColor(this, R.color.button_background_color), PorterDuff.Mode.SRC_ATOP);
                break;
            case R.id.layout_unlike:
              //  flag = 1;
                thumbUp.setColorFilter(ContextCompat.getColor(this, R.color.unselected), PorterDuff.Mode.SRC_ATOP);
                thumbDown.setColorFilter(ContextCompat.getColor(this, R.color.button_background_color), PorterDuff.Mode.SRC_ATOP);
                layoutLike.setBackgroundColor(Color.TRANSPARENT);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }


    private void checkFeedBack(ImageView v) {
        if (v.getTag().equals(R.drawable.checkbox_empty_icon)) {
            v.setImageResource(R.drawable.checkbox_checked_icon);
            v.setTag(R.drawable.checkbox_checked_icon);
        } else {
            v.setImageResource(R.drawable.checkbox_empty_icon);
            v.setTag(R.drawable.checkbox_empty_icon);
        }
    }

}


