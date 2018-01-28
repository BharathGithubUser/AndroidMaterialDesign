package material.com.materialdesign.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import material.com.materialdesign.model.RecyclerModelMainActivity;
import material.com.materialdesignexample.R;

/**
 * Created by user on 25/1/18.
 */

public class RecyclerAdapterMainActivity extends RecyclerView.Adapter<RecyclerAdapterMainActivity.ViewHolderRow> {
    Context context;
    List<RecyclerModelMainActivity> rowData;
    private OnItemClicked onClick;


    public RecyclerAdapterMainActivity(Context context,List<RecyclerModelMainActivity> rowData) {
        this.context = context;
        this.rowData = rowData;
    }

    public interface OnItemClicked {
        void onItemClick(int position);
    }

    public void setOnClick(OnItemClicked onClick)
    {
        this.onClick=onClick;
    }

    @Override
    public RecyclerAdapterMainActivity.ViewHolderRow onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_single_card,parent,false);
        return new ViewHolderRow(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterMainActivity.ViewHolderRow holder, final int position) {
        holder.itemTitle.setText(rowData.get(position).getName());
        holder.itemTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(position);
            }
        });
        holder.itemImage.setImageResource(rowData.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return rowData.size();
    }

    public class ViewHolderRow extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemTitle;
        public ViewHolderRow(View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.itemimage);
            itemTitle = itemView.findViewById(R.id.itemtitle);
        }
    }
}
