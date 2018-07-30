package material.com.materialdesign.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import material.com.materialdesign.room.daointerface.ProductDao;
import material.com.materialdesign.room.entity.Product;

/**
 * Created by user on 12/2/18.
 */
@Database(entities = {Product.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProductDao productDao();
}