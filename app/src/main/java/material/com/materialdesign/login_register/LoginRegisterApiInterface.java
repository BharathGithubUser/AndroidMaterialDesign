package material.com.materialdesign.login_register;

import material.com.materialdesign.model.LoginModel;
import material.com.materialdesign.model.RegisterActivityModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by user on 1/2/18.
 */

public interface LoginRegisterApiInterface {
    @FormUrlEncoded
    @POST("api/v1/signup")
    Call<RegisterActivityModel> createUser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("phone") String password,
            @Field("password") String gender);

}
