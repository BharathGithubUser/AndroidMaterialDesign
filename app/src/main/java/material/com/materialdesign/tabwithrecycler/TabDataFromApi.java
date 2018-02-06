package material.com.materialdesign.tabwithrecycler;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import material.com.materialdesign.utils.Constants;
import material.com.materialdesignexample.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TabDataFromApi extends Fragment {
    Activity activity;
    Context context;
    RecyclerView recyclerView;
    LinearLayoutManager recyclerLayoutManager;
    RecyclerTabAdapter recyclerTabAdapter;
    View rootView;
    RecyclerTabModel rowData;

    /*
        List<RecyclerTabModel.Topic> rowDataContent;
    */
    List<RecyclerTabModel> rowDataFromApi;
    ProgressDialog pDialog;
    OnItemClick onClickListener;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.tab_datafrom_api, container, false);
        activity = getActivity();
        context = getActivity().getApplicationContext();
        iniViews();
        return rootView;
    }

    private void iniViews() {
        recyclerView = rootView.findViewById(R.id.recycler_data_from_api);
        recyclerLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(recyclerLayoutManager);
        callRetrofit();
/*
        recyclerTabAdapter.setOnClick((RecyclerTabAdapter.OnItemClicked)this,context);
*/


/*
        //for doing this you should have List<RecyclerTabModel.Topic>
        rowDataContent = new ArrayList<>();
        rowDataContent.add(new RecyclerTabModel.Topic(1, "data"));
        rowDataContent.add(new RecyclerTabModel.Topic(2, "data"));
        rowDataContent.add(new RecyclerTabModel.Topic(3, "data"));
        rowDataContent.add(new RecyclerTabModel.Topic(4, "data"));
        rowData = new RecyclerTabModel(rowDataContent);
        recyclerTabAdapter = new RecyclerTabAdapter(rowData.getTopics());
        recyclerView.setAdapter(recyclerTabAdapter);*/
    }

    private void callRetrofit() {
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final ApiInterface request = retrofit.create(ApiInterface.class);
        Call<RecyclerTabModel> call = request.getSampleData();
        call.enqueue(new Callback<RecyclerTabModel>() {
            @Override
            public void onResponse(Call<RecyclerTabModel> call, Response<RecyclerTabModel> response) {
                RecyclerTabModel recyclerTabModel = response.body();
                List<RecyclerTabModel.Topic> topics = recyclerTabModel.getTopics();
                rowDataFromApi = new ArrayList(Arrays.asList(response.body()));
                recyclerTabAdapter = new RecyclerTabAdapter(rowDataFromApi, topics,onClickListener);
                recyclerView.setAdapter(recyclerTabAdapter);
                pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<RecyclerTabModel> call, Throwable t) {
                pDialog.dismiss();
                Log.d("Error", t.toString());

            }
        });


    }

/*    @Override
    public void onClicked(int position) {
        switch (position) {

            case 0:
                Toast.makeText(getActivity(), "Position" + position + "is Clicked", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(getActivity(), "Position" + position + "is Clicked", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(getActivity(), "Position" + position + "is Clicked", Toast.LENGTH_SHORT).show();
                break;

            case 3:
                Toast.makeText(getActivity(), "Position" + position + "is Clicked", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(getActivity(), "Position" + position + "is Clicked", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;

        }
    }*/
    interface OnItemClick{
        void onClicked();
    }
}
