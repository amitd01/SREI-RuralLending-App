package jaanpehchan.rural.srei;

import android.app.ActionBar;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import static jaanpehchan.rural.srei.ApplicantDetailsActivity.MARGIN_BOTTOM;
import static jaanpehchan.rural.srei.ApplicantDetailsActivity.dpToPx;

public class GuarantorDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String ACTIVITY_TITLE = "GUARANTOR DETAILS";

    private CustomFontEditText textGuarantorAddVal, textTractorDetailsVal,
            textLandOwnedVal, textPropertyOwnedVal,
            textITRCopyVal, textGuarantorBackgroundVal,
            textResidentialStatusVal, textDisOfGuaFromApplVal,
            guarantorVal;

    private RadioButton positive, negative;
    private Button guarantorSubmitButton;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guarantor_details);
        View view = findViewById(R.id.action_bar_container);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView textTitle = view.findViewById(R.id.activity_title);
        textTitle.setText(ACTIVITY_TITLE);
        textTitle.setTextSize(20);

        textGuarantorAddVal = findViewById(R.id.text_guarantor_address_val);
        textTractorDetailsVal = findViewById(R.id.text_details_tractor_val);
        textLandOwnedVal = findViewById(R.id.text_land_owned_val);
        textPropertyOwnedVal = findViewById(R.id.text_property_owned_val);
        textITRCopyVal = findViewById(R.id.text_itr_copy_val);
        textGuarantorBackgroundVal = findViewById(R.id.text_guarantor_background_val);
        textResidentialStatusVal = findViewById(R.id.text_residential_status_val);

        textDisOfGuaFromApplVal = findViewById(R.id.text_distance_guarantor_applicant_val);
        guarantorVal = findViewById(R.id.text_guarantor_name_val);

        positive = findViewById(R.id.positive);
        positive.setTypeface(Typeface.createFromAsset(this.getAssets(),
                "fonts/Avenir-Book-01.ttf"));
        negative = findViewById(R.id.negative);
        negative.setTypeface(Typeface.createFromAsset(this.getAssets(),
                "fonts/Avenir-Book-01.ttf"));

        guarantorSubmitButton = findViewById(R.id.button_guarantor_submit_detail);
        guarantorSubmitButton.setTypeface(Typeface.createFromAsset(this.getAssets(),
                "fonts/Avenir-Book-01.ttf"));
        guarantorSubmitButton.setOnClickListener(this);


        linearLayout = findViewById(R.id.scroll_view_container);
        final ImageView Imagebutton = findViewById(R.id.button_guarantor_submit);
        final View activityRootView = findViewById(R.id.guarantorRoot);
        activityRootView.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        int heightDiff =
                                activityRootView.getRootView().getHeight() -
                                        activityRootView.getHeight();
                        if (heightDiff > dpToPx(GuarantorDetailsActivity.this,
                                200)) {
                            // if more than 200 dp, it's probably a keyboard...
                            guarantorSubmitButton.setVisibility(View.GONE);
                            Imagebutton.setVisibility(View.GONE);
                            //  scrollView.set
                            LinearLayout.LayoutParams LayoutParams = new LinearLayout
                                    .LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                    ActionBar.LayoutParams.MATCH_PARENT);
                            LayoutParams.setMargins(0, 0, 0, 0);
                            // button.setLayoutParams(buttonLayoutParams);
                            linearLayout.setLayoutParams(LayoutParams);
                            //   Toast.makeText(GuarantorDetailsActivity.this, 2+"", Toast.LENGTH_SHORT).show();

                        } else {
                            guarantorSubmitButton.setVisibility(View.VISIBLE);
                            Imagebutton.setVisibility(View.VISIBLE);
                            LinearLayout.LayoutParams LayoutParams =
                                    new LinearLayout.LayoutParams(
                                            LinearLayout.LayoutParams.MATCH_PARENT,
                                            ActionBar.LayoutParams.MATCH_PARENT);
                            LayoutParams.setMargins(0, 0, 0,
                                    dpToPx(GuarantorDetailsActivity.this, MARGIN_BOTTOM));
                            // button.setLayoutParams(buttonLayoutParams);
                            linearLayout.setLayoutParams(LayoutParams);

                        }

                    }
                });


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_guarantor_submit_detail) {
            finish();
        }
    }

   /* @Override
    public void ellipsized(int start, int end) {
        TextPaint textPaint=new TextPaint();

        float o;
        while (textDisOfGuaFromAppl != TextUtils.ellipsize(textDisOfGuaFromAppl, textPaint, TextUtils.TruncateAt.END)) {
            textPaint.setTextSize(textPaint.getTextSize() - 1);
        }
    }*/
}
