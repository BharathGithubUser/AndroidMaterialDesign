package material.com.materialdesign.login_register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import material.com.materialdesign.model.RegisterActivityModel;
import material.com.materialdesign.utils.Constants;

import material.com.materialdesign.utils.SharedPreferencesManager;
import material.com.materialdesignexample.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {
    EditText editTextname;
    EditText editTextemail;
    EditText editTextphone;
    EditText editTextpassword;
    Button buttonsubmit;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editTextname = findViewById(R.id.name);
        editTextemail = findViewById(R.id.email);
        editTextphone = findViewById(R.id.phone);
        editTextpassword = findViewById(R.id.password);
        buttonsubmit = findViewById(R.id.signup);
        buttonsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callRegisterApi();
            }
        });
        SharedPreferencesManager getUserData;
        getUserData = SharedPreferencesManager.getInstance(getApplicationContext());
        if (getUserData.getUser() != null) {
            editTextname.setText(getUserData.getUser().getName());
            editTextemail.setText(getUserData.getUser().getEmail());
            editTextphone.setText(getUserData.getUser().getPhone());
        }

    }

    private void callRegisterApi() {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        String name = editTextname.getText().toString().trim();
        String email = editTextemail.getText().toString().trim();
        String phone = editTextphone.getText().toString().trim();
        String password = editTextpassword.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LoginRegisterApiInterface request = retrofit.create(LoginRegisterApiInterface.class);
        Call<RegisterActivityModel> call = request.createUser(name, email, phone, password);
        call.enqueue(new Callback<RegisterActivityModel>() {
            @Override
            public void onResponse(Call<RegisterActivityModel> call, Response<RegisterActivityModel> response) {
                if (response.body() != null) {
                    if (response.body().getResultCode().equals("Success")) {
                        pDialog.dismiss();
                        SharedPreferencesManager.getInstance(getApplicationContext()).storePreferencesUserLogin(response.body().getUserData());
                        Intent loginActivity = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(loginActivity);
                        finish();
                    } else {
                        if (response.body().getResultCode().equals("Failure")) {
                            pDialog.dismiss();
                            Log.d("ACTUAL ERRORS::", response.body().getErrors());
                            Toast.makeText(getApplicationContext(),response.body().getErrors(), Toast.LENGTH_SHORT).show();
                            Log.d("ResponseError", "" + response.body());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterActivityModel> call, Throwable t) {
                pDialog.dismiss();
                Log.d("Error", t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
