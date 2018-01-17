package material.com.materialdesign;

/**
 * Created by user on 13/12/17.
 */

public class RecyclerModel {
    private String name;
    private String image;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public RecyclerModel(String name, String image,int id) {
        this.name = name;
        this.image = image;
        this.id=id;
    }

    public int getId() {
        return id;
    }

}
