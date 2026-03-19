package jaanpehchan.rural.srei;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import static jaanpehchan.rural.srei.ApplicantDetailsActivity.MARGIN_BOTTOM;
import static jaanpehchan.rural.srei.ApplicantDetailsActivity.dpToPx;

public class AssetAndDealerDetail extends AppCompatActivity implements View.OnClickListener {
/*
    private TextView textManufacturingYear, textDeliveryDate,
            textEngineNo, textChasisNo, textChasisNoVal, textAssetLocatedAt,
            textDealerLocation, textDistanceOfApplicant,
            textAssetCondition,  textHeadField, textHeadValue,
            assetDeliveryStatus;*/

    private CustomFontEditText textDeliveryDateVal, textManufacturingYearVal, textEngineNoVal,
    textAssetLocatedAtVal, textDealerLocationVal, textDistanceOfApplicantVal,textAssetConditionVal;
    private static final String ACTIVITY_TITLE = "ASSET & DEALER DETAIL";
    private ArrayList<String> listDataHeader2, listDataHeader1;
    private HashMap<String, List<Proof>> listDataChild2;
    private HashMap<String, List<Proof>> listDataChild1;
    private ArrayList<String> listDataHeader;
    private ExpandableListAdapter listAdapter;
    private HashMap<String, List<Proof>> listDataChild;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_and_dealer_detail);

        View view = findViewById(R.id.action_bar_container);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView textTitle = view.findViewById(R.id.activity_title);
        textTitle.setText(ACTIVITY_TITLE);
        textTitle.setTextSize(20);

        RadioButton delivered = findViewById(R.id.delivered);
        RadioButton notDelivered = findViewById(R.id.not_delivered);
        delivered.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/Avenir-Book-01.ttf"));
        notDelivered.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/Avenir-Book-01.ttf"));


        final Button button_asset_and_dealer_detail = findViewById(R.id.button_asset_and_dealer_detail);
        button_asset_and_dealer_detail.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/Avenir-Book-01.ttf"));

        button_asset_and_dealer_detail.setOnClickListener(this);


        linearLayout = findViewById(R.id.scroll_view_container);
        final ImageView Imagebutton = findViewById(R.id.image_button_asset_dealer_detail);
        final View activityRootView = findViewById(R.id.assetRoot);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int heightDiff = activityRootView.getRootView().getHeight() - activityRootView.getHeight();
                if (heightDiff > dpToPx(AssetAndDealerDetail.this, 200)) { // if more than 200 dp, it's probably a keyboard...
                    button_asset_and_dealer_detail.setVisibility(View.GONE);
                    Imagebutton.setVisibility(View.GONE);
                    //  scrollView.set
                    LinearLayout.LayoutParams LayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
                    LayoutParams.setMargins(0, 0, 0, 0);
                    // button.setLayoutParams(buttonLayoutParams);
                    linearLayout.setLayoutParams(LayoutParams);

                } else {
                    button_asset_and_dealer_detail.setVisibility(View.VISIBLE);
                    Imagebutton.setVisibility(View.VISIBLE);
                    LinearLayout.LayoutParams LayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
                    LayoutParams.setMargins(0, 0, 0,
                            dpToPx(AssetAndDealerDetail.this, MARGIN_BOTTOM));
                    // button.setLayoutParams(buttonLayoutParams);
                    linearLayout.setLayoutParams(LayoutParams);
                }

            }
        });

  /*      textHeadField = findViewById(R.id.text_head_field);
        textHeadValue = findViewById(R.id.text_head_value);
        assetDeliveryStatus = findViewById(R.id.text_asset_delivery_status);
        textManufacturingYear = findViewById(R.id.text_manufacturing_year);
  */      textManufacturingYearVal = findViewById(R.id.text_manufacturing_year_val);
    //    textDeliveryDate = findViewById(R.id.text_delivery_Date);
        textDeliveryDateVal = findViewById(R.id.text_delivery_Date_val);
      //  textEngineNo = findViewById(R.id.text_engine_no);
        textEngineNoVal = findViewById(R.id.text_engine_no_val);
    /*    textChasisNo = findViewById(R.id.text_chasis_no);
        textChasisNoVal = findViewById(R.id.text_chasis_no_val);
        textAssetLocatedAt = findViewById(R.id.text_asset_located_at);
    */    textAssetLocatedAtVal = findViewById(R.id.text_asset_located_at_val);
      //  textDealerLocation = findViewById(R.id.text_dealer_location);
        textDealerLocationVal = findViewById(R.id.text_dealer_location_val);
       // textDistanceOfApplicant = findViewById(R.id.text_distance_of_applicant);
        textDistanceOfApplicantVal = findViewById(R.id.text_distance_of_applicant_val);
       // textAssetCondition = findViewById(R.id.text_asset_condition);
        textAssetConditionVal = findViewById(R.id.text_asset_condition_val);

        ScrollView scrollView = findViewById(R.id.scroll_view);

        ExpandableListView makeListView = findViewById(R.id.expandable_listView_make);
        listDataHeader = new ArrayList<>();
        listDataHeader.add("Make");
        final List<Proof> makeList = new ArrayList<>();
        makeList.add(new Proof("make 1", null));
        makeList.add(new Proof("make 2", null));
        listDataChild = new HashMap<>();
        listDataChild.put(listDataHeader.get(0), makeList);
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        makeListView.setAdapter(listAdapter);
        setDynamicSizeForItemData(makeListView, scrollView, makeList);

        ExpandableListView actualUser = findViewById(R.id.list_actual_user);
        listDataHeader = new ArrayList<>();
        listDataHeader.add("Actual User");
        final List<Proof> userList = new ArrayList<>();
        userList.add(new Proof("self", null));
        userList.add(new Proof("other", null));
        listDataChild = new HashMap<>();
        listDataChild.put(listDataHeader.get(0), userList);
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        actualUser.setAdapter(listAdapter);
        setDynamicSizeForItemData(actualUser, scrollView, userList);

        ExpandableListView modelListView = findViewById(R.id.expandable_listView_model);
        listDataHeader = new ArrayList<>();
        listDataHeader.add("Model");
        final List<Proof> modelList = new ArrayList<>();
        modelList.add(new Proof("model 1", null));
        modelList.add(new Proof("model 2", null));
        listDataChild = new HashMap<>();
        listDataChild.put(listDataHeader.get(0), modelList);
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        modelListView.setAdapter(listAdapter);
        setDynamicSizeForItemData(modelListView, scrollView, modelList);

        ExpandableListView listProposedAssetApplication = findViewById(R.id.list_proposed_asset_application);
        listDataHeader = new ArrayList<>();
        listDataHeader.add("Proposed asset application");
        final List<Proof> proposedList = new ArrayList<>();
        proposedList.add(new Proof("self", null));
        proposedList.add(new Proof("commercial", null));
        proposedList.add(new Proof("self and commercial", null));
        listDataChild = new HashMap<>();
        listDataChild.put(listDataHeader.get(0), proposedList);
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        listProposedAssetApplication.setAdapter(listAdapter);
        setDynamicSizeForItemData(listProposedAssetApplication, scrollView, proposedList);

        textDeliveryDateVal.setOnClickListener(this);
    }

    class mDateSetListener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
           /* String Month[] = {"January", "February", "March", "April",
                    "May", "June", "july", "August", "September", "October", "Novermber", "December"};*/
            String mYear = String.valueOf(year);
            String mMonth = String.valueOf(monthOfYear);
            String mDay = String.valueOf(dayOfMonth);

            if(dayOfMonth<=9){
                mDay="0".concat(String.valueOf(dayOfMonth));

            }
            if (monthOfYear<=9){
                mMonth="0".concat(String.valueOf(monthOfYear));
            }
            textDeliveryDateVal.setText(new StringBuilder()
                    // Month is 0 based so add 1
                    .append(mDay).append("/").append(mMonth).append("/")
                    .append(mYear.substring(2,4)).append(" "));

           // System.out.println(textDeliveryDateVal.getText().toString());
        }

    }

    private void setDynamicSizeForItemData(final ExpandableListView list, final ScrollView scrollView, final List<Proof> listItems) {
        /*list.setSelectedChild(0,1,true);
        list.smoothScrollToPosition(list.getFlatListPosition(list.getPackedPositionForChild(0, childPos)))
       */
        list.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                int index = parent.getFlatListPosition(ExpandableListView.getPackedPositionForChild(groupPosition, childPosition));
                parent.setItemChecked(index, true);
                //   int identify=parent.getId();
                //  Toast.makeText(AssetAndDealerDetail.this, identify+"...."+R.id.expandable_listView_make, Toast.LENGTH_SHORT).show();
                return true;
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
                param.height = ((listItems.size() + 1) * list.getHeight());
                list.setLayoutParams(param);
                list.refreshDrawableState();
                scrollView.refreshDrawableState();
                // list.setItemChecked(1,true);
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if ( id== R.id.button_asset_and_dealer_detail) {
            finish();
        }else if(id==R.id.text_delivery_Date_val){

            Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);

            //System.out.println("the selected " + mDay);
            DatePickerDialog dialog = new DatePickerDialog(AssetAndDealerDetail.this,
                    new AssetAndDealerDetail.mDateSetListener(), mYear, mMonth, mDay);
            dialog.show();
        }
    }
}
