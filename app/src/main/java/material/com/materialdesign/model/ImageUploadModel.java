package material.com.materialdesign.model;


import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageUploadModel implements Serializable
{

    @SerializedName("resultCode")
    @Expose
    private String resultCode;
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = 4385227668042374314L;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}