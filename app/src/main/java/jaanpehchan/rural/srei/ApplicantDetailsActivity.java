package jaanpehchan.rural.srei;

import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ApplicantDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String ACTIVITY_TITLE = "APPLICANT DETAILS";
    public static final int MARGIN_BOTTOM = 85;
    private ScrollView scrollView;

    private LinearLayout linearLayout;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private ExpandableListView listCustomerType, listTraceability, listApplicantBackground,
            listResidentialStatus, listHouseType, listRelWithOwner, listBankDetails,
            listMarginMoneyDetails, listFirstReferenceRemarks, listSecondRefRemarks;
    private HashMap<String, List<Proof>> listDataChild;
   /* private TextView textApplicantName, textAge, textFatherName,
            textApplicantAddress, textPINCode, textStateName,
            textCityName, textVillageName, textNearPolStation, textMobileNo, textEmail,
            textHouseOwnerName, textDriving, textPan,
            textAadharNo, textTotalAgriLand,
            textFamilyLand, textCropsGrown, textIncomeFromAgri,
            textIncomeOtherSources,
            textIncomeFromTractor, textKMClimit, textKMCUtilization,
            textInstallationAmount, textVehicleOwned,
            textLoanAmount, textReferenceCheck,
            textFirstReferenceName, textFirstReferencePhone,
            textFirstRefStayPeriod,
            textSecondReferenceName, textSecondReferencePhone,
            textSecondRefStayPeriod, textFIStatus,
            nearestLandMark, tx_head, tx_head_value, martialStatus;*/
    private String CUSTOMER_TYPE = "CUSTOMER TYPE";
    CustomFontEditText textApplicantNameVal, textAgeVal, textFatherNameVal, textApplicantAddressVal,
            textPINCodeVal, textCityNameVal, textVillageNameVal, textStateNameVal, textMobileNoVal,
            textEmailVal, textHouseOwnerNameVal, textDrivingVal, textPanVal, textTotalAgriLandVal,
            textCropsGrownVal, textAadharNoVal, textFamilyLandVal, textIncomeFromAgriVal,
            textKMCUtilizationVal, textIncomeOtherSourcesVal, textIncomeFromTractorVal,
            textKMClimitVal, textInstallationAmountVal, textVehicleOwnedVal, textReferenceCheckVal,
            textLoanAmountVal, nearestLandMarkVal, textNearPolStationVal, textSecondReferencePhoneVal,
            textSecondReferenceNameVal, textFirstReferenceNameVal, textFirstReferencePhoneVal,
            textFirstReferenceVal, textSecondRefStayPeriodVal, textNoOfMembersVal,
            textNoOfDependentsVal, textEarningMembersVal;
    private ArrayList<Proof> listCustoType, listTrac, houseType, relationshipWithOwner,
            bankList, cash, firstRefRemarks;

   /* private void allFilled() {
        SharedPreferences sharedPreferences = getSharedPreferences(Checklist.CHECK_LIST_PREFERENCE, 0);
        EditText field[] = {*//*textApplicantNameVal, textAgeVal*//**//*, textFatherNameVal,
        textApplicantAddressVal, textPINCodeVal,
                textCityNameVal, textVillageNameVal, textStateNameVal, textMobileNoVal,textEmailVal,
                textHouseOwnerNameVal, textDrivingVal, textPanVal, textTotalAgriLandVal,
                textCropsGrownVal, textAadharNoVal, textFamilyLandVal, textIncomeFromAgriVal,
                textKMCUtilizationVal, textIncomeOtherSourcesVal, textIncomeFromTractorVal,
                textKMClimitVal, textInstallationAmountVal, textVehicleOwnedVal,
                textReferenceCheckVal, textLoanAmountVal, nearestLandMarkVal, textNearPolStationVal,
                textSecondReferencePhoneVal, textSecondReferenceNameVal, textFirstReferenceNameVal, textFirstReferencePhoneVal, textFirstReferenceVal,
                textSecondRefStayPeriodVal*//*};
        sharedPreferences.edit().putBoolean(Checklist.APPLICANT, false).apply();
        Toast.makeText(this, "" + mapExpandableList.size(), Toast.LENGTH_SHORT).show();
        if (mapExpandableList.size() == 2) {
            sharedPreferences.edit()
                    .putBoolean(Checklist.APPLICANT, true)
                    .apply();
            finish();


        }
    }
*/

    private boolean validate(EditText[] fields) {
        for (int i = 0; i < fields.length; i++) {
            EditText currentField = fields[i];
            if (currentField.getText().toString().length() <= 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicant_details);
        View view = findViewById(R.id.action_bar_container);
        scrollView = findViewById(R.id.scroll_view);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView textTitle = view.findViewById(R.id.activity_title);
        textTitle.setText(ACTIVITY_TITLE);
        textTitle.setTextSize(20);


        textNoOfDependentsVal = findViewById(R.id.text_no_of_dependt_val);
        textNoOfDependentsVal.setOnClickListener(this);
        //  textNoOfDependentsVal.setOnClickListener(new NumberPicker.OnValueChangeListener( ));
        textEarningMembersVal = findViewById(R.id.text_earning_members_val);
        textEarningMembersVal.setOnClickListener(this);
        RadioButton maleButton = findViewById(R.id.male);
        maleButton.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/Avenir-Book-01.ttf"));
        RadioButton femaleButton = findViewById(R.id.female);
        femaleButton.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/Avenir-Book-01.ttf"));
        final Button submitButton = findViewById(R.id.button_applicant_submit_detail);
        submitButton.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/Avenir-Book-01.ttf"));
        submitButton.setOnClickListener(this);
/*
        tx_head = findViewById(R.id.text_head_field);
        tx_head_value = findViewById(R.id.text_head_value);
        textFIStatus = findViewById(R.id.text_fi_status);
        martialStatus = findViewById(R.id.martial_status);
        textApplicantName = findViewById(R.id.text_applicant_name);*/
        textApplicantNameVal = findViewById(R.id.text_applicant_name_val);
       // textAge = findViewById(R.id.text_age);
        textAgeVal = findViewById(R.id.text_age_val);
       // textFatherName = findViewById(R.id.text_father_name);
        textFatherNameVal = findViewById(R.id.text_father_name_val);
       // textApplicantAddress = findViewById(R.id.text_applicant_address);
        textApplicantAddressVal = findViewById(R.id.text_applicant_address_val);
        //textPINCode = findViewById(R.id.text_pin_code);
        textPINCodeVal = findViewById(R.id.text_pin_code_val);
       // textStateName = findViewById(R.id.text_state_name);
        textStateNameVal = findViewById(R.id.text_state_name_val);
       // textCityName = findViewById(R.id.text_city_name);
        textCityNameVal = findViewById(R.id.text_city_name_val);
       // textVillageName = findViewById(R.id.text_village_name);
        textVillageNameVal = findViewById(R.id.text_village_name_val);
        //textNearPolStation = findViewById(R.id.text_nearest_police_station);
        textNearPolStationVal = findViewById(R.id.text_nearest_police_station_val);
       // nearestLandMark = findViewById(R.id.text_nearest_landmark);
        nearestLandMarkVal = findViewById(R.id.text_nearest_landmark_val);
       // textMobileNo = findViewById(R.id.text_mobile_no);
        textMobileNoVal = findViewById(R.id.text_mobile_no_val);
       // textEmail = findViewById(R.id.text_email);
        textEmailVal = findViewById(R.id.text_email_val);
       // textHouseOwnerName = findViewById(R.id.text_house_owner_name);
        textHouseOwnerNameVal = findViewById(R.id.text_house_owner_name_val);
       // textDriving = findViewById(R.id.text_driving);
        textDrivingVal = findViewById(R.id.text_driving_val);
        textPanVal = findViewById(R.id.text_pan_val);
       // textPan = findViewById(R.id.text_pan);
       // textAadharNo = findViewById(R.id.text_aadhar_no);
        textAadharNoVal = findViewById(R.id.text_aadhar_no_val);
      /*
       textTotalAgriLand = findViewById(R.id.text_total_agriculture_land);
        textFamilyLand = findViewById(R.id.text_total_agriculture_land_family);
        textCropsGrown = findViewById(R.id.text_crops_grown);
        textIncomeFromAgri = findViewById(R.id.text_income_from_agri);
        textIncomeOtherSources = findViewById(R.id.text_income_other_sources);
        textIncomeFromTractor = findViewById(R.id.text_income_from_tractor);
        textKMClimit = findViewById(R.id.text_kmc_limit);
        textKMCUtilization = findViewById(R.id.text_kmc_limit_utilization);
      */  textTotalAgriLandVal = findViewById(R.id.text_total_agriculture_land_val);
        textFamilyLandVal = findViewById(R.id.text_total_agriculture_land_family_val);
        textCropsGrownVal = findViewById(R.id.text_crops_grown_val);
        textIncomeFromAgriVal = findViewById(R.id.text_income_from_agri_val);
        textIncomeOtherSourcesVal = findViewById(R.id.text_income_other_sources_val);
        textIncomeFromTractorVal = findViewById(R.id.text_income_from_tractor_val);
        textKMClimitVal = findViewById(R.id.text_kmc_limit_val);
        textKMCUtilizationVal = findViewById(R.id.text_kmc_limit_utilization_val);
        /*textInstallationAmount = findViewById(R.id.text_installment_amount);
        textVehicleOwned = findViewById(R.id.text_vehicle_owned);
        textLoanAmount = findViewById(R.id.text_proposed_loan_amount);
        textReferenceCheck = findViewById(R.id.text_reference_check);
        textFirstReferenceName = findViewById(R.id.text_first_reference_name);
        textFirstReferencePhone = findViewById(R.id.text_first_reference_phone);
        textFirstRefStayPeriod = findViewById(R.id.text_first_reference_stay_period);
        textSecondReferenceName = findViewById(R.id.text_second_reference_name);
        textSecondReferencePhone = findViewById(R.id.text_second_reference_phone);
        textSecondRefStayPeriod = findViewById(R.id.text_second_reference_stay);*/
        textFirstReferenceVal = findViewById(R.id.text_first_reference_stay_period_val);
        textInstallationAmountVal = findViewById(R.id.text_installment_amount_val);
        textVehicleOwnedVal = findViewById(R.id.text_vehicle_owned_val);
        textLoanAmountVal = findViewById(R.id.text_proposed_loan_amount_val);
        textReferenceCheckVal = findViewById(R.id.text_reference_check_val);
        textFirstReferenceNameVal = findViewById(R.id.text_first_reference_name_val);
        textFirstReferencePhoneVal = findViewById(R.id.text_first_reference_phone_val);
        textSecondReferenceNameVal = findViewById(R.id.text_second_reference_name_val);
        textSecondReferencePhoneVal = findViewById(R.id.text_second_reference_phone_val);
        textSecondRefStayPeriodVal = findViewById(R.id.text_second_reference_stay_val);
       // textEmail = findViewById(R.id.text_email);
        textEmailVal = findViewById(R.id.text_email_val);
        RadioButton positive, negative;
        positive = findViewById(R.id.positive);
        positive.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/Avenir-Book-01.ttf"));
        negative = findViewById(R.id.negative);
        negative.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/Avenir-Book-01.ttf"));

        listCustomerType = findViewById(R.id.list_customer_type);
        listDataHeader = new ArrayList<>();
        listDataHeader.add(CUSTOMER_TYPE);
        listCustoType = new ArrayList<>();
        listCustoType.add(new Proof("Agricultural", null));
        listCustoType.add(new Proof("Commercial", null));
        listDataChild = new HashMap<>();
        listDataChild.put(listDataHeader.get(0), listCustoType);
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        listCustomerType.setAdapter(listAdapter);
        setDynamicSizeForItemData(listCustomerType, scrollView, listCustoType.size());

        listTraceability = findViewById(R.id.list_traceability);
        listDataHeader = new ArrayList<>();
        listDataHeader.add("LIST TRACEABILITY");
        listTrac = new ArrayList<>();
        listTrac.add(new Proof("Agricultural", null));
        listTrac.add(new Proof("Commercial", null));
        listDataChild = new HashMap<>();
        listDataChild.put(listDataHeader.get(0), listTrac);
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        listTraceability.setAdapter(listAdapter);
        setDynamicSizeForItemData(listTraceability, scrollView, listTrac.size());
        // listDataHeader = new ArrayList<>();

        //   listDataChild = new HashMap<>();

        listApplicantBackground = findViewById(R.id.list_applicant_background);
        listDataHeader = new ArrayList<>();
        listDataHeader.add("Applicant background");
        ArrayList<Proof> listBack = new ArrayList<>();
        listBack.add(new Proof("Agricultural", null));
        listBack.add(new Proof("Commercial", null));
        listDataChild = new HashMap<>();
        listDataChild.put(listDataHeader.get(0), listBack);
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        listApplicantBackground.setAdapter(listAdapter);
        setDynamicSizeForItemData(listApplicantBackground, scrollView, listBack.size());

        listResidentialStatus = findViewById(R.id.list_residential_status);
        listDataHeader = new ArrayList<>();
        listDataHeader.add("Residential status");
        ArrayList<Proof> listRes = new ArrayList<>();
        listRes.add(new Proof("Agricultural", null));
        listRes.add(new Proof("Commercial", null));
        listDataChild = new HashMap<>();
        listDataChild.put(listDataHeader.get(0), listRes);
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        listResidentialStatus.setAdapter(listAdapter);
        setDynamicSizeForItemData(listResidentialStatus, scrollView, listRes.size());


//  listDataHeader = new ArrayList<>();
        //  listDataChild = new HashMap<>();

        linearLayout = findViewById(R.id.scroll_view_container);
        final ImageView Imagebutton = findViewById(R.id.button_applicant_submit);



        final View activityRootView = findViewById(R.id.activityRoot);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int heightDiff = activityRootView.getRootView().getHeight() - activityRootView.getHeight();
                if (heightDiff > dpToPx(ApplicantDetailsActivity.this, 200)) { // if more than 200 dp, it's probably a keyboard...
                    submitButton.setVisibility(View.GONE);
                    Imagebutton.setVisibility(View.GONE);
                    //  scrollView.set
                    LinearLayout.LayoutParams LayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
                    LayoutParams.setMargins(0, 0, 0, 0);
                    // button.setLayoutParams(buttonLayoutParams);
                    linearLayout.setLayoutParams(LayoutParams);

                } else {
                    submitButton.setVisibility(View.VISIBLE);
                    Imagebutton.setVisibility(View.VISIBLE);

                    LinearLayout.LayoutParams LayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
                    LayoutParams.setMargins(0, 0, 0, dpToPx(ApplicantDetailsActivity.this, MARGIN_BOTTOM));
                    // button.setLayoutParams(buttonLayoutParams);
                    linearLayout.setLayoutParams(LayoutParams);
                }

            }
        });


        listHouseType = findViewById(R.id.list_house_type);
        listDataHeader = new ArrayList<>();
        listDataHeader.add("House Type");
        houseType = new ArrayList<>();
        houseType.add(new Proof("Semipakka", null));
        houseType.add(new Proof("pakka", null));
        houseType.add(new Proof("kaccha", null));
        listDataChild = new HashMap<>();
        listDataChild.put(listDataHeader.get(0), houseType);
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        listHouseType.setAdapter(listAdapter);
        setDynamicSizeForItemData(listHouseType, scrollView, houseType.size());

        listRelWithOwner = findViewById(R.id.list_relationship_with_owner);
        listDataHeader = new ArrayList<>();
        listDataHeader.add("Relationship With house owner");
        relationshipWithOwner = new ArrayList<>();
        relationshipWithOwner.add(new Proof("Self", null));
        relationshipWithOwner.add(new Proof("father", null));
        relationshipWithOwner.add(new Proof("mother", null));
        relationshipWithOwner.add(new Proof("blood relative", null));
        relationshipWithOwner.add(new Proof("other", null));
        listDataChild = new HashMap<>();
        listDataChild.put(listDataHeader.get(0), relationshipWithOwner);
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        listRelWithOwner.setAdapter(listAdapter);
        setDynamicSizeForItemData(listRelWithOwner, scrollView, relationshipWithOwner.size());


        listBankDetails = findViewById(R.id.list_banking_details);
        listDataHeader = new ArrayList<>();
        listDataHeader.add("Bank Details");
        bankList = new ArrayList<>();
        bankList.add(new Proof("icici", null));
        bankList.add(new Proof("sbi", null));
        bankList.add(new Proof("ubi", null));
        listDataChild = new HashMap<>();
        listDataChild.put(listDataHeader.get(0), bankList);
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        listBankDetails.setAdapter(listAdapter);
        setDynamicSizeForItemData(listBankDetails, scrollView, bankList.size());

        listMarginMoneyDetails = findViewById(R.id.list_margin_money_details);
        listDataHeader = new ArrayList<>();
        listDataHeader.add("Margin Money Details");
        cash = new ArrayList<>();
        cash.add(new Proof("Cash", null));
        cash.add(new Proof("Cheque", null));
        cash.add(new Proof("Exchange of Tractor", null));
        listDataChild = new HashMap<>();
        listDataChild.put(listDataHeader.get(0), cash);
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        listMarginMoneyDetails.setAdapter(listAdapter);
        setDynamicSizeForItemData(listMarginMoneyDetails, scrollView, cash.size());


        listFirstReferenceRemarks = findViewById(R.id.list_first_reference_remarks);
        listDataHeader = new ArrayList<>();
        listDataHeader.add("first reference remarks");
        firstRefRemarks = new ArrayList<>();
        firstRefRemarks.add(new Proof("good", null));
        firstRefRemarks.add(new Proof("bad", null));
        listDataChild = new HashMap<>();
        listDataChild.put(listDataHeader.get(0), firstRefRemarks);
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        listFirstReferenceRemarks.setAdapter(listAdapter);
        setDynamicSizeForItemData(listFirstReferenceRemarks, scrollView, firstRefRemarks.size());


        listSecondRefRemarks = findViewById(R.id.list_second_ref_remarks);
        listDataHeader = new ArrayList<>();
        listDataHeader.add("second reference remarks");
        listDataChild = new HashMap<>();
        listDataChild.put(listDataHeader.get(0), firstRefRemarks);
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        listSecondRefRemarks.setAdapter(listAdapter);

        setDynamicSizeForItemData(listSecondRefRemarks, scrollView, firstRefRemarks.size());
        textNoOfMembersVal = findViewById(R.id.text_family_members_val);

        textNoOfMembersVal.setOnClickListener(this);
    }


  //  private HashMap<Integer, ExpandableListItemSelected> mapExpandableList = new HashMap<>();

    private void setDynamicSizeForItemData(final ExpandableListView list, final ScrollView scrollView,
                                           final int size) {
        list.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                int index = parent.getFlatListPosition(ExpandableListView.getPackedPositionForChild(groupPosition, childPosition));
                parent.setItemChecked(index, true);
                // parent.getExpandableListAdapter().getChild(groupPosition, childPosition).toString();
                Proof p = (Proof) parent.getExpandableListAdapter().getChild(groupPosition, childPosition);// this will return your child Object (i guess String in your case)
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
                param.height = ((size + 1) * list.getHeight());
                list.setLayoutParams(param);
                list.refreshDrawableState();
                scrollView.refreshDrawableState();
            }
        });
    }

    public static int dpToPx(Context context, float valueInDp) {
        return ViewUtils.dpToPx(context, valueInDp);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.button_applicant_submit_detail:
                //allFilled();
                finish();
                break;
            case R.id.text_family_members_val:
                NumberPickerDialog familyDialog = new NumberPickerDialog();
                familyDialog.setValueChangeListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        textNoOfMembersVal.setText(String.valueOf(newVal));
                    }
                });
                familyDialog.show(getSupportFragmentManager(), "family Picker");
                break;
            case R.id.text_no_of_dependt_val:
                NumberPickerDialog dependentsDialog = new NumberPickerDialog();
                dependentsDialog.setValueChangeListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        textNoOfDependentsVal.setText(String.valueOf(newVal));

                    }
                });
                dependentsDialog.show(getSupportFragmentManager(), "dependents Picker");
                break;
            case R.id.text_earning_members_val:
                NumberPickerDialog earningMembers = new NumberPickerDialog();
                earningMembers.setValueChangeListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        textEarningMembersVal.setText(String.valueOf(newVal));

                    }
                });
                earningMembers.show(getSupportFragmentManager(), "earning Members Picker");
                break;
        }
    }


}
