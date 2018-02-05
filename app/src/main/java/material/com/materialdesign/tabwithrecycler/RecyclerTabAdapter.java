package material.com.materialdesign.tabwithrecycler;

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
    List<RecyclerTabModel.Topic> rowData;

    public RecyclerTabAdapter(List<RecyclerTabModel.Topic> rowData) {
        this.rowData = rowData;
    }

    @Override
    public HolderView onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_card_view, parent, false);
        return new HolderView(viewItem);
    }

    @Override
    public void onBindViewHolder(HolderView holder, int position) {
        holder.textViewSno.setText("" + rowData.get(position).getSno());
        holder.textViewData.setText(rowData.get(position).getData());
    }

    @Override
    public int getItemCount() {
        return rowData.size();
    }

    public class HolderView extends RecyclerView.ViewHolder {
        TextView textViewSno;
        TextView textViewData;

        public HolderView(View itemView) {
            super(itemView);
            textViewSno = itemView.findViewById(R.id.sno);
            textViewData = itemView.findViewById(R.id.rowData);
        }
    }
}
