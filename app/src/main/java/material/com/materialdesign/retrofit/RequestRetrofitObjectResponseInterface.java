package material.com.materialdesign.retrofit;

import material.com.materialdesign.model.ImageUploadModel;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


/**
 * Created by user on 24/1/18.
 */

public interface RequestRetrofitObjectResponseInterface {

    @GET("api/v1/get_user_details_by_id?Id=2")
    Call<RetrofitObjectModel> getApiResponseObject();
    @Multipart
    @POST("api/v1/image_upload")
    Call<ImageUploadModel> uploadImage(@Part("email") RequestBody email,
                                       @Part MultipartBody.Part image);


}