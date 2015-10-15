package http.majian.com.volleyutil.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by Huoyunren on 2015/10/15.
 */
public class UserUtil {
    public static final String ID = "id";
    public static final String TOKEN = "token";
    public static final String CONFIG = "app_config";

    public static void setId(Context context, String id) {
        setString(context, ID, id);
    }

    public static String getId(Context context) {
        return getString(context, ID, "");
    }

    public static void setToken(Context context, String token) {
        setString(context, TOKEN, token);
    }

    public static String getToken(Context context) {
        return getString(context, TOKEN, "");
    }

    public static String getString(Context context, String key, String defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(
                CONFIG, Context.MODE_MULTI_PROCESS);
        return sp.getString(key, defaultValue);
    }

    public static void setString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(
                CONFIG, Context.MODE_MULTI_PROCESS);
        Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }
}
