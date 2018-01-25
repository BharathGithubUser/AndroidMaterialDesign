package material.com.materialdesign.retrofit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;
import material.com.materialdesign.MainActivity;
import material.com.materialdesignexample.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity implements View.OnClickListener {
    ProgressDialog pDialog;
    String TAG;
    RecyclerView recyclerView;
    RetrofitRecyclerAdapter adapter;
    GridLayoutManager layoutManager;
    List<RetrofitModel> data_list;
    Button retrofitApi, next;
    String baseUrl="http://laravel-example.000webhostapp.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TAG = "json_array_req";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        retrofitApi=findViewById(R.id.retrofitapi);
        next=findViewById(R.id.next);
        recyclerView = findViewById(R.id.retrofit_recyclerview);
        layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        retrofitApi.setOnClickListener(this);
        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.retrofitapi) {
            recyclerView.setVisibility(View.VISIBLE);
            retrofitApiCall();
        } else if (view.getId() == R.id.next) {
            Intent main = new Intent(this, MainActivity.class);
            startActivity(main);
        }
    }

    private void retrofitApiCall() {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<List<RetrofitModel>> call = request.getApiResponseArray();
        call.enqueue(new Callback<List<RetrofitModel>>() {
            @Override
            public void onResponse(Call<List<RetrofitModel>> call,Response<List<RetrofitModel>> response) {
               /*This is another method of fetching specific data
                for(int i=0;i<response.body().size();i++){
                    RetrofitModel retrofitModel = response.body().get(i);
                    data_list.add(retrofitModel);
                }*/
                data_list = response.body();
                adapter = new RetrofitRecyclerAdapter(RetrofitActivity.this,data_list);
                recyclerView.setAdapter(adapter);
                pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<RetrofitModel>> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }
}
