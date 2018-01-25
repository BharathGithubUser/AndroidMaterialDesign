package material.com.materialdesign.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import material.com.materialdesign.model.RecyclerModelAsyncVolley;
import material.com.materialdesignexample.R;

/**
 * Created by user on 13/12/17.
 */

public class RecyclerAdapterAsyncVolley extends RecyclerView.Adapter<RecyclerAdapterAsyncVolley.ViewHolder> {
    private Context mContext;
    private List<RecyclerModelAsyncVolley> data_list;

    public RecyclerAdapterAsyncVolley(Context mContext, List<RecyclerModelAsyncVolley> data_list) {  //Constructor for RecyclerAdapterAsyncVolley
        this.mContext = mContext;
        this.data_list = data_list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{ //defining ViewHolder
        public TextView name;
        public ImageView image;

        public ViewHolder(View itemView) { //Constructor for ViewHolder
            super(itemView);
            name=itemView.findViewById(R.id.name);
            image=itemView.findViewById(R.id.image);

        }
    }

    @Override
    public RecyclerAdapterAsyncVolley.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {  //Inflating the Card View in OnCreateViewHolder
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterAsyncVolley.ViewHolder holder, int position) {
        holder.name.setText(data_list.get(position).getName());
        Glide.with(mContext).load(data_list.get(position).getImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return data_list.size();
    }
}
