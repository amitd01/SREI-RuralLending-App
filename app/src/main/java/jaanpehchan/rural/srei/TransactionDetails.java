package jaanpehchan.rural.srei;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static jaanpehchan.rural.srei.ApplicantDetailsActivity.MARGIN_BOTTOM;
import static jaanpehchan.rural.srei.ApplicantDetailsActivity.dpToPx;

public class TransactionDetails extends AppCompatActivity implements View.OnClickListener {


    private static final String ACTIVITY_TITLE = "TRANSACTION DETAIL";
    /*private TextView textHeadField, textHeadValue, textProposeMRP,
            textProposedAsset, textLoanAmt,
            textTenure,  textMoratorium,
            textPDC,  textFlatRate,
            textDocumentationCharges, textProcessingCharges,textTractorAmt,  textInsuranceAmt;
*/
    private CustomFontEditText textProposeMRPValue, textProposedAssetValue, textLoanAmtValue,textTenureValue,
            textMoratoriumValue,textPDCValue,textFlateRateVal, textDocumentationChargesVal,
            textProcessingChargesVal, textTractorAmtVal,textInsuranceAmtVal;

    private Button buttontransactionSubmit;
    private LinearLayout linearLayout;
    private ExpandableListView listInstallment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_details);
        View view = findViewById(R.id.action_bar_container);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView textTitle = view.findViewById(R.id.activity_title);
        textTitle.setText(ACTIVITY_TITLE);
        textTitle.setTextSize(20);

        buttontransactionSubmit = findViewById(R.id.button_transaction_submit_detail);
        buttontransactionSubmit.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/Avenir-Book-01.ttf"));
        buttontransactionSubmit.setOnClickListener(this);


        ScrollView scrollView=findViewById(R.id.scroll_view);

       /* textHeadField = findViewById(R.id.text_head_field);
        textHeadValue = findViewById(R.id.text_head_value);
        textProposeMRP = findViewById(R.id.text_proposed_mrp);
       */ textProposeMRPValue = findViewById(R.id.text_proposed_mrp_val);
       // textProposedAsset = findViewById(R.id.text_proposed_asset);
        textProposedAssetValue = findViewById(R.id.text_proposed_asset_val);
        //textLoanAmt = findViewById(R.id.text_loan_amt);
        textLoanAmtValue = findViewById(R.id.text_loan_amt_val);
        //textTenure = findViewById(R.id.text_tenure);
        textTenureValue = findViewById(R.id.text_tenure_val);
      //  textMoratorium = findViewById(R.id.text_moratorium);
        textMoratoriumValue = findViewById(R.id.text_moratorium_val);
      //  textPDC = findViewById(R.id.text_pdc);
        textPDCValue = findViewById(R.id.text_pdc_val);
      //  textFlatRate = findViewById(R.id.text_flat_rate);
        textFlateRateVal = findViewById(R.id.text_flat_rate_val);
       // textDocumentationCharges = findViewById(R.id.text_documentation_charges);
     //   textProcessingCharges = findViewById(R.id.text_processing_charges);
        textDocumentationChargesVal = findViewById(R.id.text_documentation_charges_val);
        textProcessingChargesVal = findViewById(R.id.text_processing_charges_val);
       // textTractorAmt = findViewById(R.id.text_tractor_premium_amt);
        textTractorAmtVal = findViewById(R.id.text_tractor_premium_amt_val);
     //   textInsuranceAmt = findViewById(R.id.text_credit_insurance_amt);
        textInsuranceAmtVal = findViewById(R.id.text_credit_insurance_amt_val);
        listInstallment = findViewById(R.id.list_installment);
        ArrayList<String> listDataHeader = new ArrayList<>();
        listDataHeader.add("first reference remarks");
        ArrayList<Proof> listInstall = new ArrayList<>();
        listInstall.add(new Proof("good", null));
        listInstall.add(new Proof("bad", null));
        HashMap<String, List<Proof>> listDataChild = new HashMap<>();
        listDataChild.put(listDataHeader.get(0), listInstall);
        ExpandableListAdapter listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        listInstallment.setAdapter(listAdapter);
        setDynamicSizeForItemData(listInstallment, scrollView, listInstall.size());



        linearLayout = findViewById(R.id.scroll_view_container);
        final ImageView Imagebutton = findViewById(R.id.image_transaction_submit);
        final View activityRootView = findViewById(R.id.transactionroot);

        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int heightDiff = activityRootView.getRootView().getHeight() - activityRootView.getHeight();
                if (heightDiff > dpToPx(TransactionDetails.this, 200)) { // if more than 200 dp, it's probably a keyboard...
                    buttontransactionSubmit.setVisibility(View.GONE);
                    Imagebutton.setVisibility(View.GONE);
                    //  scrollView.set
                    LinearLayout.LayoutParams LayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT);
                    LayoutParams.setMargins(0, 0, 0, 0);
                    // button.setLayoutParams(buttonLayoutParams);
                    linearLayout.setLayoutParams(LayoutParams);

                } else {
                    buttontransactionSubmit.setVisibility(View.VISIBLE);
                    Imagebutton.setVisibility(View.VISIBLE);
                    LinearLayout.LayoutParams LayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    LayoutParams.setMargins(0, 0, 0,
                            dpToPx(TransactionDetails.this,MARGIN_BOTTOM));
                    // button.setLayoutParams(buttonLayoutParams);
                    linearLayout.setLayoutParams(LayoutParams);
                }

            }
        });


    }

    private void setDynamicSizeForItemData(final ExpandableListView list, final ScrollView scrollView,
                                           final int size) {
        list.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                int index = parent.getFlatListPosition(ExpandableListView.getPackedPositionForChild(groupPosition, childPosition));
                parent.setItemChecked(index, true);
                // parent.getExpandableListAdapter().getChild(groupPosition, childPosition).toString();
             //   Proof p = (Proof) parent.getExpandableListAdapter().getChild(groupPosition, childPosition);// this will return your child Object (i guess String in your case)
                //  Toast.makeText(ApplicantDetailsActivity.this, groupPosition + "," + childPosition + "," + index + "", Toast.LENGTH_SHORT).show();
             //   mapExpandableList.put(groupPosition, new ExpandableListItemSelected(p.getS(), index));
                return false;
            }
        });
        list.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                LinearLayout.LayoutParams param = (LinearLayout.LayoutParams) list.getLayoutParams();
                param.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                list.setLayoutParams(param);
                list.refreshDrawableState();
                scrollView.refreshDrawableState();
            }
        });
        list.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                LinearLayout.LayoutParams param = (LinearLayout.LayoutParams) list.getLayoutParams();
                param.height = ((size+ 1) * list.getHeight());
                list.setLayoutParams(param);
                list.refreshDrawableState();
                scrollView.refreshDrawableState();
            }
        });
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button_transaction_submit_detail){
            finish();
        }
    }
}
