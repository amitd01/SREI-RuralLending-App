package jaanpehchan.rural.srei;

import android.app.ActionBar;
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
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static jaanpehchan.rural.srei.ApplicantDetailsActivity.MARGIN_BOTTOM;
import static jaanpehchan.rural.srei.ViewUtils.dpToPx;

public class CoApplicantActivityDetails extends AppCompatActivity implements View.OnClickListener {
    private static final String ACTIVITY_TITLE = "CO-APPLICANT DETAILS";

    private ExpandableListAdapter listAdapter;
    private ExpandableListView listCoAddress;
    private List<String> listDataHeader;
    private HashMap<String, List<Proof>> listDataChild;


    private EditText coApplicantNameVal,  fatherHusbandNameVal,
             mobileNoValue, agriculturLandVal;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_co_applicant_details);
        View view = findViewById(R.id.action_bar_container);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView textTitle = view.findViewById(R.id.activity_title);
        textTitle.setText(ACTIVITY_TITLE);
        textTitle.setTextSize(20);

      
        agriculturLandVal = findViewById(R.id.text_total_agriculture_land_val);
        coApplicantNameVal = findViewById(R.id.text_co_applicant_name_val);
        fatherHusbandNameVal = findViewById(R.id.text_husband_father_val);
        mobileNoValue = findViewById(R.id.text_mobile_no_val);

        RadioButton positive = findViewById(R.id.positive);
        RadioButton negative = findViewById(R.id.negative);
        positive.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/Avenir-Book-01.ttf"));
        negative.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/Avenir-Book-01.ttf"));
        final Button buttonCoApplicantSubmit = findViewById(R.id.button_co_applicant_submit_detail);
        buttonCoApplicantSubmit.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/Avenir-Book-01.ttf"));
        buttonCoApplicantSubmit.setOnClickListener(this);

        final ScrollView scrollView = findViewById(R.id.scroll_view);


        linearLayout = findViewById(R.id.scroll_view_container);
        final ImageView Imagebutton = findViewById(R.id.button_co_applicant_submit);
        final View activityRootView = findViewById(R.id.coApplicantRoot);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int heightDiff = activityRootView.getRootView().getHeight() - activityRootView.getHeight();
                if (heightDiff > dpToPx(CoApplicantActivityDetails.this, 200)) { // if more than 200 dp, it's probably a keyboard...
                    buttonCoApplicantSubmit.setVisibility(View.GONE);
                    Imagebutton.setVisibility(View.GONE);
                    //  scrollView.set
                    LinearLayout.LayoutParams LayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
                    LayoutParams.setMargins(0, 0, 0, 0);
                    // button.setLayoutParams(buttonLayoutParams);
                    linearLayout.setLayoutParams(LayoutParams);


                } else {
                    buttonCoApplicantSubmit.setVisibility(View.VISIBLE);
                    Imagebutton.setVisibility(View.VISIBLE);
                    LinearLayout.LayoutParams LayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
                    LayoutParams.setMargins(0, 0, 0,
                            dpToPx(CoApplicantActivityDetails.this,MARGIN_BOTTOM));
                    // button.setLayoutParams(buttonLayoutParams);
                    linearLayout.setLayoutParams(LayoutParams);
                }

            }
        });



        listCoAddress = findViewById(R.id.list_co_address);
        listDataHeader = new ArrayList<>();
        listDataHeader.add("Co Applicant Address (Same)");
        final List<Proof> coAddress = new ArrayList<>();
        coAddress.add(new Proof("yes", null));
        coAddress.add(new Proof("no", null));
        listDataChild = new HashMap<>();
        listDataChild.put(listDataHeader.get(0), coAddress);
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        listCoAddress.setAdapter(listAdapter);
        setDynamicSizeForItemData(listCoAddress, scrollView, coAddress);


        ExpandableListView listRelationShip = findViewById(R.id.relationship_with_applicant);
        listDataHeader = new ArrayList<>();
        listDataHeader.add("Relationship with applicant");
        final List<Proof> realtionship = new ArrayList<>();
        realtionship.add(new Proof("father", null));
        realtionship.add(new Proof("mother", null));
        realtionship.add(new Proof("spouse", null));
        realtionship.add(new Proof("son", null));
        realtionship.add(new Proof("unmarried daughter", null));
        realtionship.add(new Proof("others", null));
        listDataChild = new HashMap<>();
        listDataChild.put(listDataHeader.get(0), realtionship);
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        listRelationShip.setAdapter(listAdapter);
        setDynamicSizeForItemData(listRelationShip, scrollView, realtionship);
        
        ExpandableListView listBackground = findViewById(R.id.list_co_appl_background);
        listDataHeader = new ArrayList<>();
        listDataHeader.add("Co-Applicant Background");
        final List<Proof> coAppBackground = new ArrayList<>();
        coAppBackground.add(new Proof("yes", null));
        coAppBackground.add(new Proof("no", null));
        listDataChild = new HashMap<>();
        listDataChild.put(listDataHeader.get(0), coAppBackground);
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        listBackground.setAdapter(listAdapter);
        setDynamicSizeForItemData(listBackground, scrollView, coAppBackground);

        ExpandableListView listResidentialStatus = findViewById(R.id.list_residential_status);
        listDataHeader = new ArrayList<>();
        listDataHeader.add("Residential status");
        final List<Proof> residentialStatus = new ArrayList<>();
        residentialStatus.add(new Proof("yes", null));
        residentialStatus.add(new Proof("no", null));
        listDataChild = new HashMap<>();
        listDataChild.put(listDataHeader.get(0), coAddress);
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        listResidentialStatus.setAdapter(listAdapter);
        setDynamicSizeForItemData(listResidentialStatus, scrollView, residentialStatus);

    }


    private void setDynamicSizeForItemData(final ExpandableListView list, final ScrollView
            scrollView, final List<Proof> listItems) {
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
                param.height = (listItems.size() + 1) * list.getHeight();
                list.setLayoutParams(param);
                list.refreshDrawableState();
                scrollView.refreshDrawableState();
            }
        });
        list.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                int index = parent.getFlatListPosition(ExpandableListView
                        .getPackedPositionForChild(groupPosition, childPosition));
                parent.setItemChecked(index, true);
                //int identify=parent.getId();
               // Toast.makeText(AssetAndDealerDetail.this,
                // identify+"...."+R.id.expandable_listView_make, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button_co_applicant_submit_detail){
            finish();
        }
    }
}