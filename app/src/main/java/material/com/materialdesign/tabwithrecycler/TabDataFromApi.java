package material.com.materialdesign.tabwithrecycler;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import material.com.materialdesignexample.R;

public class TabDataFromApi extends Fragment {
    Activity activity;
    Context context;
    RecyclerView recyclerView;
    LinearLayoutManager recyclerLayoutManager;
    RecyclerTabAdapter recyclerTabAdapter;
    View rootView;
    RecyclerTabModel rowData;
    List<RecyclerTabModel.Topic> rowDataContent;

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
        recyclerLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(recyclerLayoutManager);
        rowDataContent = new ArrayList<>();
        rowDataContent.add(new RecyclerTabModel.Topic(1,"data"));
        rowDataContent.add(new RecyclerTabModel.Topic(2,"data"));
        rowDataContent.add(new RecyclerTabModel.Topic(3,"data"));
        rowDataContent.add(new RecyclerTabModel.Topic(4,"data"));
        rowData = new RecyclerTabModel(rowDataContent);
        recyclerTabAdapter = new RecyclerTabAdapter(rowData.getTopics());
        recyclerView.setAdapter(recyclerTabAdapter);
    }


}
