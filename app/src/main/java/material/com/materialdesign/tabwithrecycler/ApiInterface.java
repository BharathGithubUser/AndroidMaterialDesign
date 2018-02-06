package material.com.materialdesign.tabwithrecycler;

import material.com.materialdesign.model.RegisterActivityModel;
import material.com.materialdesign.retrofit.RetrofitObjectModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by user on 6/2/18.
 */

public interface ApiInterface {
    @GET("api/v1/getSampleData")
    Call<RecyclerTabModel> getSampleData();
}
