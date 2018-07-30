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
import java.util.Set;

import material.com.materialdesign.mapintegration.MapActivity;
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
    List<RecyclerTabModel> rowDataFromApi;
    ProgressDialog pDialog;
    TabDataFromApi.SetTabData setTabData;
    public static TabDataFromApi createInstance(){
        return  new TabDataFromApi();
    }
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

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            if (activity instanceof MapActivity) {
                setTabData = null;
            } else {
                setTabData = (SetTabData) activity;
            }
        } catch (ClassCastException e) {
            Log.i("", "" + e);
        }
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
                final List<RecyclerTabModel.Topic> topics = recyclerTabModel.getTopics();
                rowDataFromApi = new ArrayList(Arrays.asList(response.body()));
                OnItemClick itemClickListener = new OnItemClick() {
                    @Override
                    public void onClicked(int position) {
                        switch (position) {

                            case 0:
                                setTabData.setTappedData(position, topics.get(position).getData());
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
                    }
                };
                recyclerTabAdapter = new RecyclerTabAdapter(rowDataFromApi, topics, itemClickListener);
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

    interface OnItemClick {
        void onClicked(int position);
    }

    interface SetTabData {
        void setTappedData(int position, String data);
    }
}
