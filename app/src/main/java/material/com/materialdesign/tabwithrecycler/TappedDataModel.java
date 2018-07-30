package material.com.materialdesign.tabwithrecycler;

/**
 * Created by user on 7/2/18.
 */

public class TappedDataModel {
    int sno;
    String data;

    public int getSno() {
        return sno;
    }

    public String getData() {
        return data;
    }

    public TappedDataModel(int position, String data) {
        this.sno = position;
        this.data = data;
    }
}
