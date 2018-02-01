package material.com.materialdesign.utils;

import android.content.Context;
import android.content.SharedPreferences;

import material.com.materialdesign.RegisterActivity;
import material.com.materialdesign.model.RegisterActivityModel;

/**
 * Created by user on 1/2/18.
 */

public class SharedPreferencesManager {
    private static SharedPreferencesManager mInstance;
    private static Context mContext;

    private static final String SHARED_PREF_NAME = "selfUpgrade";

    private SharedPreferencesManager(Context context) {
        mContext = context;
    }

    public static synchronized SharedPreferencesManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPreferencesManager(context);
        }
        return mInstance;
    }

    public void storePreferencesUserLogin(RegisterActivityModel user) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constants.KEY_USER_ID, user.getId());
        editor.putString(Constants.KEY_USER_NAME, user.getName());
        editor.putString(Constants.KEY_USER_EMAIL, user.getEmail());
        editor.putString(Constants.KEY_USER_PHONE, user.getPhone());
        editor.apply();
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if (sharedPreferences.getString(Constants.KEY_USER_EMAIL, null) != null)
            return true;
        return false;
    }

    public RegisterActivityModel getUser() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new RegisterActivityModel(
                sharedPreferences.getInt(Constants.KEY_USER_ID, 0),
                sharedPreferences.getString(Constants.KEY_USER_NAME, null),
                sharedPreferences.getString(Constants.KEY_USER_EMAIL, null),
                sharedPreferences.getString(Constants.KEY_USER_PHONE, null)
        );
    }

    public boolean logout() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }
}
