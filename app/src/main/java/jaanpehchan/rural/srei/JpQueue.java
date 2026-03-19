package jaanpehchan.rural.srei;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 1515012 on 08-05-2018.
 */

public class JpQueue extends AppCompatActivity implements LeadListAdapter.RecyclerViewClickListener {

    private final String ACTIVITY_TITLE = "JP QUEUE";
    private TextView textLeadID, headStatus, headRemark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jp_queue);
        View view = findViewById(R.id.action_bar_container);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView textTitle = view.findViewById(R.id.activity_title);
        textTitle.setText(ACTIVITY_TITLE);
        textTitle.setTextSize(24);
        textLeadID = findViewById(R.id.head_lead_id);
        headStatus = findViewById(R.id.head_status);
        headRemark = findViewById(R.id.head_remark);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        ArrayList<FakeData> l = new ArrayList<>();
        l.add(new FakeData(1, "REJECTED", "Completed"));
       /* l.add(new FakeData(2, "REJECTED", "Completed"));
        l.add(new FakeData(3, "REJECTED", "Completed"));
       */
       l.add(new FakeData(4, "ACCEPTED", "Completed"));
        LeadListAdapter recipeAdapter = new LeadListAdapter(JpQueue.this, l,this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(recipeAdapter);
    }

    @Override
    public void onClick(View view, int position) {
        Intent checkListIntent=new Intent(JpQueue.this,Checklist.class);
        startActivity(checkListIntent);
    }

    @Override
    public void onBackPressed() {
        NavUtils.navigateUpFromSameTask(this);
    }
}
