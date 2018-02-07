package material.com.materialdesign.tabwithrecycler;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import material.com.materialdesignexample.R;

/**
 * Created by user on 5/2/18.
 */

public class RecyclerTabAdapter extends RecyclerView.Adapter<RecyclerTabAdapter.HolderView> {
    List<RecyclerTabModel> rowData;
    List<RecyclerTabModel.Topic> topics;
    TabDataFromApi.OnItemClick OnItemClickListener;

    public RecyclerTabAdapter(List<RecyclerTabModel> rowData, List<RecyclerTabModel.Topic> topics, TabDataFromApi.OnItemClick itemClicklistener) {
        this.rowData = rowData;
        this.topics = topics;
        this.OnItemClickListener = itemClicklistener;
    }

    @Override
    public HolderView onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_card_view, parent, false);
        return new HolderView(viewItem);
    }

    @Override
    public void onBindViewHolder(HolderView holder, final int position) {
        holder.textViewSno.setText("" + topics.get(position).getSno());
        holder.textViewData.setText(topics.get(position).getData());
    }

    @Override
    public int getItemCount() {
        return topics.size();
    }

    public class HolderView extends RecyclerView.ViewHolder {
        TextView textViewSno;
        TextView textViewData;
        CardView rootView;

        public HolderView(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    OnItemClickListener.onClicked(getPosition());
                }
            });
            rootView = itemView.findViewById(R.id.cardview);
            textViewSno = itemView.findViewById(R.id.sno);
            textViewData = itemView.findViewById(R.id.rowData);
        }
    }
}
