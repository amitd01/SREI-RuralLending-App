package jaanpehchan.rural.srei;

import android.content.Context;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 1515012 on 08-05-2018.
 */

class LeadListAdapter extends RecyclerView.Adapter<LeadListAdapter.RecipeViewHolder> {
    private ArrayList<FakeData> lead;
    private Context mContext;
    private RecyclerViewClickListener mListener;

    public interface RecyclerViewClickListener {

        void onClick(View view, int position);
    }

    public LeadListAdapter(Context context, ArrayList<FakeData> l, RecyclerViewClickListener listener) {
        lead = l;
        mContext = context;
        mListener = listener;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecipeViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.jp_queue_item, parent, false),mListener);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {

        holder.remark.setText(lead.get(position).getRemark());
        holder.leadId.setText(String.valueOf(lead.get(position).getId()));
        holder.status.setText(lead.get(position).getStatus());

        String status = lead.get(position).getStatus();
        if (status.equals("REJECTED")) {
            holder.idImage.setImageDrawable(ContextCompat.getDrawable(mContext,
                    R.drawable.current_active_menu_indicator));
            holder.rejectedStatusImage.setImageDrawable(ContextCompat.getDrawable(mContext,
                    R.drawable.jp_queue_status_rejected));
        } else {
            holder.idImage.setImageDrawable(ContextCompat.getDrawable(mContext,
                    R.drawable.current_active_menu_indicator));
            holder.rejectedStatusImage.setImageDrawable(ContextCompat.getDrawable(mContext,
                    R.drawable.jp_queue_status_accepted));
        }
    }

    @Override
    public int getItemCount() {
        return lead.size();
    }

    void changeData(ArrayList<FakeData> arrayList) {
        lead = arrayList;
        notifyDataSetChanged();
    }


    class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView idImage, rejectedStatusImage;
        private TextView leadId, remark, status;
        private RecyclerViewClickListener mListener;

        RecipeViewHolder(View itemView, RecyclerViewClickListener mListener) {
            super(itemView);
            leadId = itemView.findViewById(R.id.lead_id);
            idImage = itemView.findViewById(R.id.active);
            rejectedStatusImage = itemView.findViewById(R.id.status_image);
            status = itemView.findViewById(R.id.status);
            remark = itemView.findViewById(R.id.remark);
            this.mListener=mListener;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            mListener.onClick(view,getAdapterPosition());
        }
    }
}
