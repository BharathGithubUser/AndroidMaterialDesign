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
import java.util.Arrays;
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
    RecyclerView arrayRecyclerView;
    RecyclerView objectRecyclerView;
    RetrofitArrayRecyclerAdapter arrayRecyclerAdapter;
    RetrofitObjectRecyclerAdapter objectRecyclerAdapter;
    GridLayoutManager layoutManagerRetrofitArray;
    GridLayoutManager layoutManagerRetrofitObject;
    List<RetrofitArrayModel> data_list;
    ArrayList<RetrofitObjectModel> data_list_object;
    Button retrofitApiArray;
    Button retrofitApiObject;
    Button next;

    String baseUrl="http://laravel-example.000webhostapp.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TAG = "json_array_req";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        retrofitApiArray=findViewById(R.id.retrofitapiarray);
        retrofitApiObject = findViewById(R.id.retrofitapiobject);
        next=findViewById(R.id.next);
        arrayRecyclerView = findViewById(R.id.retrofit_recyclerview);
        objectRecyclerView = findViewById(R.id.retrofitobject_recyclerview);
        layoutManagerRetrofitArray = new GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false);
        layoutManagerRetrofitObject = new GridLayoutManager(this,1,GridLayoutManager.HORIZONTAL,false);
        arrayRecyclerView.setLayoutManager(layoutManagerRetrofitArray);
        objectRecyclerView.setLayoutManager(layoutManagerRetrofitObject);
        retrofitApiArray.setOnClickListener(this);
        retrofitApiObject.setOnClickListener(this);
        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.retrofitapiarray) {
            arrayRecyclerView.setVisibility(View.VISIBLE);
            retrofitApiArrayCall();
        } else if (view.getId() == R.id.next) {
            Intent main = new Intent(this, MainActivity.class);
            startActivity(main);
        } else if (view.getId() == R.id.retrofitapiobject){
            retrofitApiObjectCall();
        }
    }

    private void retrofitApiObjectCall() {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestRetrofitObjectResponseInterface request = retrofit.create(RequestRetrofitObjectResponseInterface.class);
        Call<RetrofitObjectModel> call = request.getApiResponseObject();
        call.enqueue(new Callback<RetrofitObjectModel>() {
            @Override
            public void onResponse(Call<RetrofitObjectModel> call,Response<RetrofitObjectModel> response) {
                data_list_object = new ArrayList<>(Arrays.asList(response.body()));
                Log.e("",""+data_list_object.get(0));
                Log.e("",""+data_list_object.get(0).getAge());
                Log.e("",""+data_list_object.get(0).getImage());
                objectRecyclerAdapter = new RetrofitObjectRecyclerAdapter(RetrofitActivity.this,data_list_object);
                objectRecyclerView.setAdapter(objectRecyclerAdapter);
                pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<RetrofitObjectModel> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }

    private void retrofitApiArrayCall() {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestRetrofitArrayResponseInterface request = retrofit.create(RequestRetrofitArrayResponseInterface.class);
        Call<List<RetrofitArrayModel>> call = request.getApiResponseArray();
        call.enqueue(new Callback<List<RetrofitArrayModel>>() {
            @Override
            public void onResponse(Call<List<RetrofitArrayModel>> call, Response<List<RetrofitArrayModel>> response) {
               /*This is another method of fetching specific data
                for(int i=0;i<response.body().size();i++){
                    RetrofitArrayModel retrofitModel = response.body().get(i);
                    data_list.add(retrofitModel);
                }*/
                data_list = response.body();
                arrayRecyclerAdapter = new RetrofitArrayRecyclerAdapter(RetrofitActivity.this,data_list);
                arrayRecyclerView.setAdapter(arrayRecyclerAdapter);
                pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<RetrofitArrayModel>> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }
}
