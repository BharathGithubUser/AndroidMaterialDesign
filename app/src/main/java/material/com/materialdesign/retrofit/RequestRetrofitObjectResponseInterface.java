package material.com.materialdesign.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by user on 24/1/18.
 */

public interface RequestRetrofitObjectResponseInterface {

    @GET("api/v1/get_user_details_by_id?Id=2")
    Call<RetrofitObjectModel> getApiResponseObject();

}