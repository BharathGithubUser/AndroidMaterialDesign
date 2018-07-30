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

/**
 * Created by user on 7/2/18.
 */

public class TappedDataInFirstTab extends Fragment {
    Activity activity;
    Context context;
    RecyclerView recyclerView;
    LinearLayoutManager recyclerLayoutManager;
    RecyclerTappedAdapter recyclerTappedAdapter;
    View rootView;
    String data;
    List<RecyclerTabModel> rowDataFromApi;
    List<TappedDataModel> tappedData;
    ProgressDialog pDialog;
    int getTappedPosition;

    public static TappedDataInFirstTab createInstance(int position, String data) {
        TappedDataInFirstTab tappedDataInFirstTab = new TappedDataInFirstTab();
        tappedDataInFirstTab.getTappedDetails(position, data);
        return tappedDataInFirstTab;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_tapped_data, container, false);
        activity = getActivity();
        iniViews();
        return rootView;
    }

    private void iniViews() {
        recyclerView = rootView.findViewById(R.id.recyclerview_tapped_data);
        recyclerLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(recyclerLayoutManager);
        tappedData = new ArrayList<>();
        tappedData.add(new TappedDataModel(getTappedPosition,data));
        recyclerTappedAdapter = new RecyclerTappedAdapter(tappedData);
        recyclerView.setAdapter(recyclerTappedAdapter);
    }

    private void getTappedDetails(int position, String data) {
        this.getTappedPosition = position;
        this.data = data;
    }

/*    private void callRetrofit() {
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
                TabDataFromApi.OnItemClick itemClickListener = new TabDataFromApi.OnItemClick() {
                    @Override
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
                    }
                };
                recyclerTabAdapter = new RecyclerTabAdapter(rowDataFromApi, topics, itemClickListener);
                recyclerView.setAdapter(recyclerTabAdapter);
                recyclerView.findViewById(getTappedPosition);
                pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<RecyclerTabModel> call, Throwable t) {
                pDialog.dismiss();
                Log.d("Error", t.toString());

            }
        });

    }*/

    interface OnItemClick {
        void onClicked(int position);
    }
}
