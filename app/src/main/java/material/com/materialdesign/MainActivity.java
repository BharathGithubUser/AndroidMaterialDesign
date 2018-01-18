package material.com.materialdesign;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
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

import material.com.materialdesign.adapter.RecyclerAdapter;
import material.com.materialdesign.async_task.AsyncTaskApiCall;
import material.com.materialdesign.model.RecyclerModel;
import material.com.materialdesign.volley.VolleyApi;
import material.com.materialdesignexample.R;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements AsyncTaskApiCall.ApiCallFinishedListener,View.OnClickListener {
    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    GridLayoutManager layoutManager;
    List<RecyclerModel> data_list;
    Context progressDialogContext;
    AsyncTaskApiCall.ApiCallFinishedListener apiCallFinishedInterfaceReferrence;
    Button asynctask;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data_list=new ArrayList<>();
        recyclerView=findViewById(R.id.async_recyclerview);
        asynctask=findViewById(R.id.asyncapi);
        next=findViewById(R.id.next);
        asynctask.setOnClickListener(this);
        next.setOnClickListener(this);
        //apiCall(0); /**This is for normal AsyncTask call from same Activity*/
        progressDialogContext=this;
        apiCallFinishedInterfaceReferrence=this;
        layoutManager=new GridLayoutManager(this,1,GridLayoutManager.HORIZONTAL,false);

        recyclerView.setLayoutManager(layoutManager);
        adapter=new RecyclerAdapter(this,data_list);
        recyclerView.setAdapter(adapter);
    }

    private void apiCall(final int id) {
        AsyncTask<Integer,Void,Void> task=new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... integers) {
                OkHttpClient client = new OkHttpClient();
                Request request=new Request.Builder()
                        .url("https://laravel-example.000webhostapp.com/api/v1/get_user_details?Id="+id)
                        .build();
                try {
                    Response response=client.newCall(request).execute();
                    JSONArray array=new JSONArray(response.body().string());
                    for(int i=0;i<array.length();i++) {
                        JSONObject object=array.getJSONObject(i);
                        RecyclerModel data = new RecyclerModel(object.getString("name"), object.getString("image"),object.getInt("id"));
                        Log.d("TAG:DEBUGGER", "" + object.get("name") + object.get("image"));
                        data_list.add(data);
                    }

                } catch (IOException|JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                adapter.notifyDataSetChanged();
            }
        };

        task.execute(id);
    }

    @Override
    public void onApiCallFinished(JSONArray responseArray) {
        try{
            for(int i=0;i<responseArray.length();i++) {
                JSONObject object=responseArray.getJSONObject(i);
                RecyclerModel data = new RecyclerModel(object.getString("name"), object.getString("image"),object.getInt("id"));
                Log.d("TAG:DEBUGGER", "" + object.get("name") + object.get("image"));
                data_list.add(data);
                adapter.notifyDataSetChanged();
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.asyncapi){
            recyclerView.setVisibility(View.VISIBLE);
            new AsyncTaskApiCall(progressDialogContext,apiCallFinishedInterfaceReferrence,0).execute(0);
        }
        else if(view.getId()==R.id.next){
            Intent volleyApi=new Intent(this,VolleyApi.class);
            startActivity(volleyApi);
        }
    }
}
