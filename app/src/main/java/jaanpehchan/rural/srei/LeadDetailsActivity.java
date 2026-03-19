package jaanpehchan.rural.srei;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

public class LeadDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String ACTIVITY_TITLE = "ENTER LEAD DETAILS";
    private TextView textName, textAadhar, textMobileNo, textDOB;
    private EditText editTextName, editTextAadhar, editTextMobileNo, editTextDOB;
    private Button buttonSendForVerfication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead_details);
        Button buttonEndVerfication = findViewById(R.id.button_send_for_verfication);
        buttonEndVerfication.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Avenir-Book-01.ttf"));

        View view = findViewById(R.id.action_bar_container);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView textTitle = view.findViewById(R.id.activity_title);
        textTitle.setText(ACTIVITY_TITLE);
        textTitle.setTextSize(20);

        textName = findViewById(R.id.text_customer_name);
        textAadhar = findViewById(R.id.text_aadhar_no);
        textMobileNo = findViewById(R.id.text_mobile_no);
        textDOB = findViewById(R.id.text_date_of_birth);

        editTextAadhar = findViewById(R.id.edittext_aadhar_no);
        editTextDOB = findViewById(R.id.edittext_date_of_birth);
        //  editTextDOB.setInputType(InputType.TYPE_NULL);
        editTextDOB.setOnClickListener(this);
        editTextMobileNo = findViewById(R.id.edittext_mobile_no);
        editTextName = findViewById(R.id.edittext_customer_name);
        ImageView customerName = findViewById(R.id.customer_name_icon);
        ImageView mobileNo = findViewById(R.id.mobile_no_icon);
        ImageView aadharNo = findViewById(R.id.aadhar_icon);
        ImageView dob = findViewById(R.id.calender_icon);

        buttonSendForVerfication = findViewById(R.id.button_send_for_verfication);
        buttonEndVerfication.setOnClickListener(this);
        customerName.setOnClickListener(this);
        mobileNo.setOnClickListener(this);
        aadharNo.setOnClickListener(this);
        dob.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.customer_name_icon:
                editTextName.requestFocus();
                break;
            case R.id.mobile_no_icon:
                editTextMobileNo.requestFocus();
                break;
            case R.id.aadhar_icon:

                editTextAadhar.requestFocus();
                break;
            case R.id.edittext_date_of_birth:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(getWindow().getDecorView()
                            .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            case R.id.calender_icon:
                editTextDOB.setEnabled(true);
                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                System.out.println("the selected " + mDay);
                DatePickerDialog dialog = new DatePickerDialog(LeadDetailsActivity.this,
                        new mDateSetListener(), mYear, mMonth, mDay);
                dialog.show();
                break;
            case R.id.button_send_for_verfication:
                finish();
                break;
        }
    }

    class mDateSetListener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            String Month[] = {"January", "February", "March", "April",
                    "May", "June", "july", "August", "September", "October", "Novermber", "December"};
            int mYear = year;
            int mMonth = monthOfYear;
            int mDay = dayOfMonth;

            editTextDOB.setText(new StringBuilder()
                    // Month is 0 based so add 1
                    .append(mDay).append(" ").append(Month[mMonth]).append(", ")
                    .append(mYear).append(" "));
            System.out.println(editTextDOB.getText().toString());
            /* editTextDOB.requestFocus();*/
            // editTextDOB.setEnabled(false);
        }

    }
}


