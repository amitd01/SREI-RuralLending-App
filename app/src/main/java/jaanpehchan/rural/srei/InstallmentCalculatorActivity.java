package jaanpehchan.rural.srei;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class InstallmentCalculatorActivity extends AppCompatActivity
        implements View.OnClickListener {

    private final String ACTIVITY_TITLE = "INSTALLMENT CALCULATOR";
    private CustomFontEditText textHeadVal, textAssestVal, textFinancedVal, textIrrVal,
            textTenureVal, textTotal, textLabel, textPayFrequencyVal, textMoratVal;
    private Button buttonCalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.installment_calculator);

        View view = findViewById(R.id.action_bar_container);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView textTitle = view.findViewById(R.id.activity_title);
        textTitle.setText(ACTIVITY_TITLE);
        textTitle.setTextSize(20);

        textTenureVal = findViewById(R.id.text_tenure_val);
        textHeadVal = findViewById(R.id.text_head_value);
        textAssestVal = findViewById(R.id.text_assest_val);
        textFinancedVal = findViewById(R.id.text_financed_amt_val);
        textIrrVal = findViewById(R.id.text_irr_val);
        textPayFrequencyVal = findViewById(R.id.text_pay_freq_val);
        textMoratVal = findViewById(R.id.text_moratorium_val);
        textTotal = findViewById(R.id.text_amount);
        textLabel = findViewById(R.id.text_amount_label);
        buttonCalAmount = findViewById(R.id.button_cal_installment);
        buttonCalAmount.setOnClickListener(this);
        buttonCalAmount.setTypeface(Typeface.createFromAsset(getAssets(),
                "fonts/Avenir-Book-01.ttf"));
    }


    @SuppressLint("StaticFieldLeak")
    @Override
    public void onClick(View v) {
        textTotal.setVisibility(View.INVISIBLE);
        textLabel.setVisibility(View.INVISIBLE);
        final SweetAlertDialog pDialog =
                new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper()
                .setBarColor(getResources().getColor(R.color.button_background_color));
        pDialog.setTitleText("Calculating....");
        // pDialog.setCancelable(false);
        pDialog.show();
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {

                }

                pDialog.dismiss();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                textTotal.setVisibility(View.VISIBLE);
                textLabel.setVisibility(View.VISIBLE);
            }
        }.execute();


    }
}


