package material.com.materialdesign.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 1/2/18.
 */

public class LoginModel {
    @SerializedName("resultCode")
    @Expose
    private String resultCode;
    @SerializedName("auth")
    @Expose
    private String auth;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}
