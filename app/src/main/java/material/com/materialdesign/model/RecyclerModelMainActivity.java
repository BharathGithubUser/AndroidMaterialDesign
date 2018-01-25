package material.com.materialdesign.model;

import android.content.res.Resources;

/**
 * Created by user on 25/1/18.
 */

public class RecyclerModelMainActivity {
    private String name;
    private int image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public RecyclerModelMainActivity(String name, int image) {
        this.name = name;
        this.image = image;
    }

}
