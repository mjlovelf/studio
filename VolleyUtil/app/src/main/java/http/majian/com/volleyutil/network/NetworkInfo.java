package http.majian.com.volleyutil.network;

import java.util.Map;
import android.content.Context;

import http.majian.com.volleyutil.common.UserUtil;


/**
 * Created by apple on 15/5/18.
 */
public class NetworkInfo {
    private String mToken;
    private String mAppKey;
    private String mUrl;
    private String mResponse;
    private int mWhat;
    private int mFailedWhat;
    private Map<String, String> mDataParams;
    private Object mExtendObject;
    private String mMethod;
    private boolean mBeforeLogin;
    private boolean mJsonFormat;

    public NetworkInfo(Context context, String url, int what, int errorWhat) {
        this(context, url, what, errorWhat, null, null);
    }

    public NetworkInfo(Context context, String url, int what, int errorWhat, String response) {
        this(context, url, what, errorWhat, response, null);
    }

    public NetworkInfo(Context context, String url, int what, int errorWhat, String response, Object extendObject) {
        mToken = UserUtil.getToken(context);
        mAppKey = UserUtil.getId(context);
        mUrl = url;
        mWhat = what;
        mFailedWhat = errorWhat;
        mExtendObject = extendObject;
        mResponse = response;
    }

    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        this.mToken = token;
    }

    public String getAppKey() {
        return mAppKey;
    }

    public void setAppKey(String appKey) {
        this.mAppKey = appKey;
    }

    public boolean isBeforeLogin() {
        return mBeforeLogin;
    }

    public void setBeforeLogin(boolean beforeLogin) {
        mBeforeLogin = beforeLogin;
    }

    public boolean isJsonFormat() {
        return mJsonFormat;
    }

    public void setJsonFormat(boolean jsonFormat) {
        mJsonFormat = jsonFormat;
    }

    public String getMethod() {
        return mMethod;
    }

    public void setMethod(String method) {
        mMethod = method;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public Object getExtendObject() {
        return mExtendObject;
    }

    public void setExtendObject(Object extendObject) {
        mExtendObject = extendObject;
    }

    public int getWhat() {
        return mWhat;
    }

    public void setWhat(int what) {
        mWhat = what;
    }

    public int getFailedWhat() {
        return mFailedWhat;
    }

    public void setFailedWhat(int what) {
        mFailedWhat = what;
    }

    public String getResponse() {
        return mResponse;
    }

    public void setResponse(String response) {
        mResponse = response;
    }

    public void setParams(final Map<String, String> params) {
        mDataParams = params;
    }

    public Map<String, String> getParams() {
        return mDataParams;
    }
}
