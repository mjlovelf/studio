package http.majian.com.volleyutil.network;

import http.majian.com.volleyutil.network.error.VolleyError;

/**
 * Created by apple on 15/5/18.
 */
public class NetworkInfoErrorListener implements Response.ErrorListener {
    private NetworkInfo mNetworkInfo;

    public NetworkInfoErrorListener(NetworkInfo networkInfo) {
        mNetworkInfo = networkInfo;
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        if (mNetworkInfo != null) {
        }
    }
}
