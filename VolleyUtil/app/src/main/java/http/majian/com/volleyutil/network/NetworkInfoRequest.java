package http.majian.com.volleyutil.network;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

import http.majian.com.volleyutil.BuildConfig;
import http.majian.com.volleyutil.common.Constants;
import http.majian.com.volleyutil.network.error.AuthFailureError;
import http.majian.com.volleyutil.util.MD5Utils;
import http.majian.com.volleyutil.util.SignGenerator;


/**
 *
 */
public class NetworkInfoRequest extends Request<NetworkInfo> {

    private final NetworkInfo mNetworkInfo;
    private final Response.Listener<NetworkInfo> mListener;

    public NetworkInfoRequest(int method, String url, NetworkInfo networkInfo,
            Response.Listener<NetworkInfo> listener,
            Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        mNetworkInfo = networkInfo;
        mListener = listener;
    }

    public NetworkInfoRequest(String url, NetworkInfo networkInfo,
            Response.Listener<NetworkInfo> listener,
            Response.ErrorListener errorListener) {
        this(0, url, networkInfo, listener, errorListener);
    }

    @Override
    protected Response<NetworkInfo> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException var4) {
            parsed = new String(response.data);
        }
        mNetworkInfo.setResponse(parsed);
        return Response.success(mNetworkInfo, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(NetworkInfo t) {
        mListener.onResponse(t);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        if (mNetworkInfo.getParams() == null) {
            throw new AuthFailureError("缺少参数！");
        } else {
            Map<String, String> dataParams = mNetworkInfo.getParams();
            if (mNetworkInfo.isJsonFormat()) {
                return buildParams(mNetworkInfo.getToken(), mNetworkInfo.getAppKey(),
                        mNetworkInfo.getMethod(), mNetworkInfo.isBeforeLogin(), dataParams);
            } else {
                dataParams.put("method", mNetworkInfo.getMethod());
                return dataParams;
            }
        }
    }

    public static Map<String, String> buildParams(String token,
            String id,
            String method,
            boolean beforeLogin,
            Map<String, String> dataParams) {

        Map<String, String> params = new HashMap<>();
        params.put("method", method);
        String app_key = beforeLogin ? Constants.DEAD_APP_KEY : id;
        params.put("app_key", app_key);
        params.put("clienttype", "citydriver");
        long time = System.currentTimeMillis() / 1000;
        params.put("timestamp", String.valueOf(time));
        String versionName = BuildConfig.VERSION_NAME;
        if (versionName.contains("_")) {
            int index = versionName.indexOf("_");
            versionName = versionName.substring(0, index);
        }
        dataParams.put("app_version", versionName);
        if (dataParams.size() > 0) {
            try {
                params.put("data", URLEncoder.encode(
                        new JSONObject(dataParams).toString(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (beforeLogin) {
            params.put("sign", SignGenerator.createSign(params,
                    MD5Utils.encode(Constants.DEAD_APP_KEY + time).toUpperCase()));
        } else {
            params.put("sign", SignGenerator.createSign(params, token));
        }
        return params;
    }
}
