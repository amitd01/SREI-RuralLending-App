package jaanpehchan.rural.srei;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Checklist extends AppCompatActivity implements View.OnClickListener {

    private static final String ACTIVITY_TITLE = "CHECKLIST";
    public static final String CHECK_LIST_PREFERENCE="Applicant Shared Preference";
    public static final String APPLICANT="Applicant";
    private TextView textLeadId, textLeadNo, textApplicant, textCoApplicant, textGuarantor,
            textAsset, textTransaction;
    private ImageView applicantImage;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);
        View view = findViewById(R.id.action_bar_container);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.modify_close_icon);
        TextView textTitle = view.findViewById(R.id.activity_title);
        textTitle.setText(ACTIVITY_TITLE);
        textTitle.setTextSize(20);

        sharedPreferences=getSharedPreferences(CHECK_LIST_PREFERENCE,0);
        Button buttonApplicationSubmit = findViewById(R.id.button_application_submit);
        buttonApplicationSubmit.setTypeface(Typeface.createFromAsset(getAssets(),
                "fonts/Avenir-Book-01.ttf"));
        applicantImage=findViewById(R.id.applicant_icon);


        buttonApplicationSubmit.setOnClickListener(this);
        textLeadId = findViewById(R.id.text_lead_id_);
        textLeadNo = findViewById(R.id.text_lead_id_val);
        textApplicant = findViewById(R.id.text_applicant);
        textCoApplicant = findViewById(R.id.text_co_applicant);
        textGuarantor = findViewById(R.id.text_guarantor);
        textAsset = findViewById(R.id.text_asset);
        textTransaction = findViewById(R.id.text_transaction);

        LinearLayout applicant = findViewById(R.id.applicant);
        LinearLayout coApplicant = findViewById(R.id.co_applicant);
        LinearLayout guarantor = findViewById(R.id.guarantor);
        LinearLayout asset = findViewById(R.id.asset);
        LinearLayout transaction = findViewById(R.id.transaction);
        applicant.setOnClickListener(this);
        coApplicant.setOnClickListener(this);
        guarantor.setOnClickListener(this);
        asset.setOnClickListener(this);
        transaction.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(sharedPreferences.getBoolean(APPLICANT,false)) {
            applicantImage.setImageResource(R.drawable.modify_saved);
        }else{
            applicantImage.setImageResource(R.drawable.modify_edit_button);
        }
    }


    @Override
    public void onClick(View v) {
        // switch(R.id.*) replaced with if/else — R.id values are non-final in AndroidX
        int vid = v.getId();
        if (vid == R.id.button_application_submit) {
            startActivity(new Intent(Checklist.this, FeedbackFormActivity.class));
        } else if (vid == R.id.transaction) {
            startActivity(new Intent(Checklist.this, TransactionDetails.class));
        } else if (vid == R.id.applicant) {
            startActivity(new Intent(Checklist.this, ApplicantDetailsActivity.class));
        } else if (vid == R.id.co_applicant) {
            startActivity(new Intent(Checklist.this, CoApplicantActivityDetails.class));
        } else if (vid == R.id.guarantor) {
            startActivity(new Intent(Checklist.this, GuarantorDetailsActivity.class));
        } else if (vid == R.id.asset) {
            startActivity(new Intent(Checklist.this, AssetAndDealerDetail.class));
        }
    }

}
