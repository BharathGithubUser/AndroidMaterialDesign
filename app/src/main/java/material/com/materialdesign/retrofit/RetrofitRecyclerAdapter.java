package material.com.materialdesign.retrofit;

/**
 * Created by user on 24/1/18.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import material.com.materialdesignexample.R;

public class RetrofitRecyclerAdapter extends RecyclerView.Adapter<RetrofitRecyclerAdapter.ViewHolder> {
    private List<RetrofitModel> response;
    private Context context;


    public RetrofitRecyclerAdapter(Context context,List<RetrofitModel> response) {
        this.response = response;
        this.context = context;
    }

    @Override
    public RetrofitRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RetrofitRecyclerAdapter.ViewHolder viewHolder, int i) {

        viewHolder.name.setText(response.get(i).getName());
        Glide.with(context).load(response.get(i).getImage()).into(viewHolder.image);
    }

    @Override
    public int getItemCount() {
        return response.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private ImageView image;
        public ViewHolder(View view) {
            super(view);
            name = (TextView)view.findViewById(R.id.name);
            image = (ImageView) view.findViewById(R.id.image);

        }
    }

}