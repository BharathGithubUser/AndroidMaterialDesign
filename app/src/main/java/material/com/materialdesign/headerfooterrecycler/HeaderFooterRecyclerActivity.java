package material.com.materialdesign.headerfooterrecycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import material.com.materialdesignexample.R;
public class HeaderFooterRecyclerActivity extends AppCompatActivity {

    RecyclerView recyclerViewHeaderFooter;

    LinearLayoutManager linearLayoutManager;

    HeaderFooterRecyclerAdapter headerFooterRecyclerAdapter;

    ArrayList<String> singleRowData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_footer_recycler_view);
        recyclerViewHeaderFooter = findViewById(R.id.recyclerview_header_footer);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewHeaderFooter.setLayoutManager(linearLayoutManager);
        singleRowData = new ArrayList<>();
        singleRowData.add("Recycler View 1");
        singleRowData.add("Recycler View 2");
        singleRowData.add("Recycler View 3");
        singleRowData.add("Recycler View n...");
        headerFooterRecyclerAdapter = new HeaderFooterRecyclerAdapter(this,singleRowData);
        recyclerViewHeaderFooter.setAdapter(headerFooterRecyclerAdapter);

    }
}
