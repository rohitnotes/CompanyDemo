package company.demo;

import android.content.Context;
import android.content.SharedPreferences;

public class AppSharedPreferences {

    /**
     * PREFS_NAME is a file name which generates inside data folder of application
     */
    private static final String PREFS_NAME = "company_demo";

    static SharedPreferences sharedPreferences;
    static SharedPreferences.Editor prefEditor = null;

    private static Context mContext = null;
    public static AppSharedPreferences instance = null;

    public static AppSharedPreferences getInstance(Context context) {
        mContext = context;
        if (instance == null) {
            instance = new AppSharedPreferences();
        }
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefEditor = sharedPreferences.edit();
        return instance;
    }

    public void register(String firstNameString, String lastNameString, String emailString, String passwordString) {
        prefEditor.putString("firstNameString", firstNameString);
        prefEditor.putString("lastNameString", lastNameString);
        prefEditor.putString("emailString", emailString);
        prefEditor.putString("passwordString", passwordString);
        prefEditor.commit();
    }

    public String getEmail() {
        return sharedPreferences.getString("emailString", "not found");
    }

    public String getPassword() {
        return sharedPreferences.getString("passwordString", "not found");
    }

    public void clearData() {
        prefEditor.clear();
        prefEditor.commit();
    }
}
