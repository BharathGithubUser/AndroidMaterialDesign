package material.com.materialdesign.volley;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import material.com.materialdesign.adapter.RecyclerAdapterAsyncVolley;
import material.com.materialdesign.model.RecyclerModelAsyncVolley;
import material.com.materialdesign.retrofit.RetrofitActivity;
import material.com.materialdesignexample.R;

public class VolleyApi extends AppCompatActivity implements View.OnClickListener {

    ProgressDialog pDialog;
    String TAG;
    RecyclerView recyclerView;
    RecyclerAdapterAsyncVolley adapter;
    GridLayoutManager layoutManager;
    List<RecyclerModelAsyncVolley> data_list;
    Button volleyApi, next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TAG = "json_array_req";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_api);
        data_list = new ArrayList<>();
        volleyApi=findViewById(R.id.volleyapi);
        next=findViewById(R.id.next);
        recyclerView = findViewById(R.id.volley_recyclerview);
        adapter = new RecyclerAdapterAsyncVolley(this, data_list);
        layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        volleyApi.setOnClickListener(this);
        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.volleyapi) {
            recyclerView.setVisibility(View.VISIBLE);
            volleyApiCall();
        } else if (view.getId() == R.id.next) {
            Intent main = new Intent(this, RetrofitActivity.class);
            startActivity(main);
        }
    }

    private void volleyApiCall() {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        // Adding request to request queue
        String url = "https://laravel-example.000webhostapp.com/api/v1/get_user_details?Id=5";
        JsonArrayRequest request = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray responseArray) {
                        try {
                            for (int i = 0; i < responseArray.length(); i++) {
                                JSONObject object = responseArray.getJSONObject(i);
                                RecyclerModelAsyncVolley data = new RecyclerModelAsyncVolley(object.getString("name"), object.getString("image"), object.getInt("id"));
                                Log.d("TAG:DEBUGGER", "" + object.get("name") + object.get("image"));
                                data_list.add(data);
                            }
                            adapter.notifyDataSetChanged();
                            pDialog.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        pDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                pDialog.dismiss();
            }
        });
        VolleyAppController.getInstance().addToRequestQueue(request, TAG);
    }
}
