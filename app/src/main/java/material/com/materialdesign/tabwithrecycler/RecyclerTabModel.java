package material.com.materialdesign.tabwithrecycler;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 5/2/18.
 */

public class RecyclerTabModel {

    @SerializedName("Topics")
    @Expose
    private  List<Topic> topics;

    public RecyclerTabModel(List<Topic> topics) {
        this.topics = topics;
    }


    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }


    public static  final class Topic {

        @SerializedName("sno")
        @Expose
        private Integer sno;
        @SerializedName("Data")
        @Expose
        private String data;

        Topic(Integer sno, String data) {
            this.sno = sno;
            this.data = data;
        }

        public Integer getSno() {
            return sno;
        }

        public void setSno(Integer sno) {
            this.sno = sno;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }



    }
}
