package material.com.materialdesign;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import material.com.materialdesign.volley.adapter.RecyclerAdapterMainActivity;
import material.com.materialdesign.async_task.AsyncTaskActivity;
import material.com.materialdesign.headerfooterrecycler.HeaderFooterRecyclerActivity;
import material.com.materialdesign.mapintegration.MapActivity;
import material.com.materialdesign.model.RecyclerModelMainActivity;
import material.com.materialdesign.retrofit.RetrofitActivity;
import material.com.materialdesign.room.RoomDatabaseActivity;
import material.com.materialdesign.tabwithrecycler.TabActivity;
import material.com.materialdesign.volley.VolleyApi;
import material.com.materialdesignexample.R;

public class MainIndexScreenActivity extends AppCompatActivity implements RecyclerAdapterMainActivity.OnItemClicked {
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
        rowData.add(new RecyclerModelMainActivity("Tab with Recycler view", R.drawable.ic_toys_black_24dp));
        rowData.add(new RecyclerModelMainActivity("Slider Image Window", R.drawable.ic_toys_black_24dp));
        rowData.add(new RecyclerModelMainActivity("Google Maps Integration", R.drawable.ic_toys_black_24dp));
        rowData.add(new RecyclerModelMainActivity("Room Database Example",R.drawable.ic_toys_black_24dp));
        rowData.add(new RecyclerModelMainActivity("Qr Code Scanner", R.drawable.ic_toys_black_24dp));
        rowData.add(new RecyclerModelMainActivity("Header and Footer RecyclerView", R.drawable.ic_toys_black_24dp));
        rowData.add(new RecyclerModelMainActivity("Image Upload to server and display", R.drawable.ic_toys_black_24dp));
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

            case 3:
                Intent tabWithRecycler = new Intent(this, TabActivity.class);
                startActivity(tabWithRecycler);
                break;

            case 4:
                break;

            case 5:
                Intent mapActivity = new Intent(MainIndexScreenActivity.this, MapActivity.class);
                startActivity(mapActivity);
                break;

            case 6:
                Intent roomDatabase = new Intent(this, RoomDatabaseActivity.class);
                startActivity(roomDatabase);
                break;
            case 7:
                break;

            case 8:
                Intent headerFooterRecycler = new Intent(this, HeaderFooterRecyclerActivity.class);
                startActivity(headerFooterRecycler);
                break;
            case 9:
                Intent imageUploadToServer = new Intent(this, HeaderFooterRecyclerActivity.class);
                startActivity(imageUploadToServer);
                break;
            default:
                break;

        }
    }
}
