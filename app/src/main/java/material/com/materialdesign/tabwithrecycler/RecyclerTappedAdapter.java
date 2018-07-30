package material.com.materialdesign.tabwithrecycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import material.com.materialdesignexample.R;

/**
 * Created by user on 7/2/18.
 */

public class RecyclerTappedAdapter extends RecyclerView.Adapter<RecyclerTappedAdapter.HolderView> {
    List<TappedDataModel> tappedData;
    public RecyclerTappedAdapter(List<TappedDataModel> tappedData) {
        this.tappedData = tappedData;
    }

    @Override
    public RecyclerTappedAdapter.HolderView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_card_view,parent,false);
        return  new HolderView(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerTappedAdapter.HolderView holder, int position) {
        holder.eTSno.setText(""+tappedData.get(position).getSno());
        holder.eTData.setText(tappedData.get(position).getData());
    }

    @Override
    public int getItemCount() {
        return tappedData.size();
    }

    public class HolderView extends RecyclerView.ViewHolder {
        TextView eTSno;
        TextView eTData;
        public HolderView(View itemView) {
            super(itemView);
            eTSno = itemView.findViewById(R.id.sno);
            eTData = itemView.findViewById(R.id.rowData);
        }
    }
}
