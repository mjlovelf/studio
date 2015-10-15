package http.majian.com.volleyutil.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * 
 * Description: Toast显示工具类
 * 
 * @author Syn
 * @Version 1.0.0
 * @Created at 2014-08-27
 * @Modified by XX on XXXX-XX-XX
 */
public class ToastUtil {

    public static void show(Context context, String str) {
        Toast toast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void show(Context context, int id) {
        Toast toast = Toast.makeText(context, id, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showLong(Context context, String str) {
        Toast toast = Toast.makeText(context, str, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showLong(Context context, int id) {
        Toast toast = Toast.makeText(context, id, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}