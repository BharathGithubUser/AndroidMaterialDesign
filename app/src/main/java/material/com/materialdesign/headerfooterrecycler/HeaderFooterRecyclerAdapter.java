package material.com.materialdesign.headerfooterrecycler;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import material.com.materialdesignexample.R;

/**
 * Created by user on 28/2/18.
 */

public class HeaderFooterRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_ITEM = 2;

    private ArrayList<String> rowData;
    private Activity activity;

    public HeaderFooterRecyclerAdapter(Activity activity, ArrayList<String> rowData) {
        this.activity = activity;
        this.rowData = rowData;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_ITEM){
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_view,parent,false);
            return new ItemViewHolder(itemView);

        }else if(viewType == TYPE_HEADER){
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_header_view,parent,false);
            return new HeaderViewHolder(itemView);

        }else if(viewType == TYPE_FOOTER){
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_footer_view,parent,false);
            return new FooterViewHolder(itemView);

        }else return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,final int position) {

        if(holder instanceof HeaderViewHolder){

            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;

            headerViewHolder.headerTextView.setText("Recycler View Header Text");

        }else if (holder instanceof ItemViewHolder){

            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;

            itemViewHolder.itemTextView.setText("Recycler Item number:"+position);

        }else if(holder instanceof FooterViewHolder){

            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
            footerViewHolder.footerTextView.setText("Recycler View Footer Text");

        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else if (position == rowData.size() + 1) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }


    @Override
    public int getItemCount() {
        return rowData.size()+2;
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView itemTextView;
        ItemViewHolder(View itemView) {
            super(itemView);
            itemTextView = itemView.findViewById(R.id.tv_item_view);
        }
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView headerTextView;
        HeaderViewHolder(View itemView) {
            super(itemView);
            headerTextView = itemView.findViewById(R.id.tv_header_text);
        }
    }

    private class FooterViewHolder extends RecyclerView.ViewHolder {
        TextView footerTextView;
        FooterViewHolder(View itemView) {
            super(itemView);
            footerTextView = itemView.findViewById(R.id.tv_footer_text);
        }
    }
}
