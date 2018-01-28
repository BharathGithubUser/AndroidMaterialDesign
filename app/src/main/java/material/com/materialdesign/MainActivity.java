package material.com.materialdesign;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import material.com.materialdesign.adapter.RecyclerAdapterAsyncVolley;
import material.com.materialdesign.adapter.RecyclerAdapterMainActivity;
import material.com.materialdesign.async_task.AsyncTaskActivity;
import material.com.materialdesign.async_task.AsyncTaskApiCall;
import material.com.materialdesign.model.RecyclerModelAsyncVolley;
import material.com.materialdesign.model.RecyclerModelMainActivity;
import material.com.materialdesign.retrofit.RetrofitActivity;
import material.com.materialdesign.volley.VolleyApi;
import material.com.materialdesignexample.R;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements RecyclerAdapterMainActivity.OnItemClicked {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    Context progressDialogContext;
    RecyclerAdapterMainActivity adapterMainActivity;
    List<RecyclerModelMainActivity> rowData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        progressDialogContext = this;
        rowData = new ArrayList<>();
        rowData.add(new RecyclerModelMainActivity("AsyncTask ApiCall Example", R.drawable.ic_toys_black_24dp));
        rowData.add(new RecyclerModelMainActivity("Volley ApiCall Example", R.drawable.ic_toys_black_24dp));
        rowData.add(new RecyclerModelMainActivity("Retrofit ApiCall Example", R.drawable.ic_toys_black_24dp));
        rowData.add(new RecyclerModelMainActivity("GreenDao Example", R.drawable.ic_toys_black_24dp));
        rowData.add(new RecyclerModelMainActivity("Relams Example", R.drawable.ic_toys_black_24dp));
        rowData.add(new RecyclerModelMainActivity("Ripple Effects Example", R.drawable.ic_toys_black_24dp));

        adapterMainActivity = new RecyclerAdapterMainActivity(this, rowData);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterMainActivity);
        adapterMainActivity.setOnClick(this);

    }

    @Override
    public void onItemClick(int position) {

        switch (position) {

            case 0:
                Intent asyncTask = new Intent(this, AsyncTaskActivity.class);
                startActivity(asyncTask);
                break;

            case 1:
                Intent volley = new Intent(this, VolleyApi.class);
                startActivity(volley);
                break;

            case 2:
                Intent retrofit = new Intent(this, RetrofitActivity.class);
                startActivity(retrofit);
                break;

            default:
                break;

        }
    }
}
