package material.com.materialdesign;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
/**
 * Created by user on 17/1/18.
 */

public class AsyncTaskApiCall extends AsyncTask<Integer,Void,JSONArray> {
    private int id;
    private JSONArray responseArray;
    private ApiCallFinishedListener onApiCallFinishedListener;
    private Context progressDialogContext;
    ProgressDialog progressDialog;

    AsyncTaskApiCall(Context progressDialogContext,ApiCallFinishedListener apiCallFinishedInterfaceReferrence,int id) {
        this.id=id;
        this.progressDialogContext=progressDialogContext;
        this.onApiCallFinishedListener=apiCallFinishedInterfaceReferrence;

    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(progressDialogContext);
        progressDialog.show();
    }

    @Override
        protected JSONArray doInBackground(Integer... integers) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://laravel-example.000webhostapp.com/api/v1/get_user_details?Id=" + id)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                responseArray = new JSONArray(response.body().string());

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return responseArray;
        }
    @Override
    protected void onPostExecute(JSONArray responseArray) {
        super.onPostExecute(responseArray);
        progressDialog.dismiss();
        onApiCallFinishedListener.onApiCallFinished(responseArray);
    }

    public interface ApiCallFinishedListener{
        void onApiCallFinished(JSONArray responseArray);
    }
}
