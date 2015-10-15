package http.majian.com.volleyutil.network;

import java.util.Map;
import android.content.Context;

import http.majian.com.volleyutil.R;
import http.majian.com.volleyutil.common.UserUtil;
import http.majian.com.volleyutil.network.Request.Method;
import http.majian.com.volleyutil.network.Response.ErrorListener;
import http.majian.com.volleyutil.network.Response.Listener;
import http.majian.com.volleyutil.util.NetWorkDetectionUtils;
import http.majian.com.volleyutil.util.ToastUtil;


public class RequestHelper {
    private static final int DEFAULT_REQUEST_TIMEOUT = 20 * 1000;
    private static final int DEFAULT_REQUEST_RETRY_COUNT = 1;

    private static RequestQueue mRequestQueue;

    public static RequestQueue getRequestQueue(Context context) {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context);
        }
        return mRequestQueue;
    }

    public static boolean createRequest(final Context context, String url,
                                     final NetworkInfo networkInfo,
                                     Object tag,
                                     Listener<NetworkInfo> listener, ErrorListener errListener) {
        if (NetWorkDetectionUtils.checkNetworkAvailable(context)) {
            NetworkInfoRequest request = new NetworkInfoRequest(Method.POST, url,
                    networkInfo, listener, errListener);
            request.setRetryPolicy(new DefaultRetryPolicy(DEFAULT_REQUEST_TIMEOUT,
                    DEFAULT_REQUEST_RETRY_COUNT,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            request.setShouldCache(false);
            request.setTag(getRequestTag(context, tag));
            getRequestQueue(context).add(request);
            return true;
        } else {
            ToastUtil.show(context, context.getString(R.string.request_network_available));
            return false;
        }
    }

    public static NetworkInfo createNetworkInfo(Context context, int what, int failedWhat) {
        NetworkInfo networkInfo = new NetworkInfo(context, null, what, failedWhat);
        networkInfo.setBeforeLogin(false);
        networkInfo.setJsonFormat(true);
        return networkInfo;
    }

    public static Map<String, String> buildParams(Context context,
                                                  String method,
                                                  boolean beforeLogin,
                                                  Map<String, String> dataParams) {

        return NetworkInfoRequest.buildParams(UserUtil.getToken(context),
                UserUtil.getId(context), method, beforeLogin, dataParams);
    }

    public static void cancelAll(Context context, Object tag) {
        if (context != null) {
            getRequestQueue(context).cancelAll(getRequestTag(context, tag));
        }
    }

    public static Object getRequestTag(Context context, Object tag) {
        if (context == null) {
            return "";
        }
        if (tag == null) {
            return context.getClass().getName();
        }
        return tag;
    }

}
