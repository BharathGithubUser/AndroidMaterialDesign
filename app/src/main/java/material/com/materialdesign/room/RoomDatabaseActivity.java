package material.com.materialdesign.room;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import material.com.materialdesign.room.entity.Product;
import material.com.materialdesignexample.R;

/**
 * Created by user on 12/2/18.
 */

public class RoomDatabaseActivity extends AppCompatActivity {

    public static final String DATABASE_NAME = "roomdatabase";

    public AppDatabase database;

    List<Product> productsInsert;

    List<Product> productsList;

    Product productData;

    TextView tvRowData;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_database);
        tvRowData = findViewById(R.id.roomData);
        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        productData = new Product();
        productData.setName("Bharath");
        productData.setImageUrl("http://feelgrafix.com/data_images/out/7/803622-flower-field-wallpaper.jpg");
        productsInsert = new ArrayList<>();
        productsInsert.add(productData);
        database.productDao().insertAll(productsInsert);
        productsList = database.productDao().getAll();
        for (int i = 0; i < productsList.size(); i++) {
            tvRowData.append(productsList.get(i).getUid() + "\n" + productsList.get(i).getName()+"\n"+productsList.get(i).getImageUrl());
        }
    }
}
