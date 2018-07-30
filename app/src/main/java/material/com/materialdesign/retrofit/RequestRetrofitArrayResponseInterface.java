package material.com.materialdesign.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by user on 24/1/18.
 */

public interface RequestRetrofitArrayResponseInterface {

    @GET("api/v1/get_user_details")
    Call<List<RetrofitArrayModel>> getApiResponseArray();

}