package http.majian.com.volleyutil.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.TextView;
import org.json.JSONObject;

import http.majian.com.volleyutil.BuildConfig;
import http.majian.com.volleyutil.R;
import http.majian.com.volleyutil.common.Urls;
import http.majian.com.volleyutil.network.NetworkInfo;
import http.majian.com.volleyutil.network.RequestHelper;
import http.majian.com.volleyutil.network.Response.ErrorListener;
import http.majian.com.volleyutil.network.Response.Listener;
import http.majian.com.volleyutil.network.error.VolleyError;
import http.majian.com.volleyutil.util.ToastUtil;


public class LoginActivity extends Activity {

    private Context mContext;
    private EditText etPhone;
    private EditText etCode;
    private TextView tvVoice;
    private TextView tvNext;

    private static final int CAPTCHA_SUCCESS = 0;
    private static final int CAPTCHA_FAILED = 1;
    private static final int LOGIN_SUCCESS = 2;
    private static final int LOGIN_FAILED = 3;
    private static final int CAPTCHA_MSG = 5;

    private boolean isAgree = true;


    class MyHandler extends Handler {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == CAPTCHA_SUCCESS) {
                String response = msg.getData().getString("response");
                try {
                    JSONObject responseJson = new JSONObject(response);
                    int code = responseJson.getInt("code");
                    if (code == 0) {

                    } else {
                        ToastUtil.show(mContext, responseJson.getString("message"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (msg.what == CAPTCHA_FAILED) {
                ToastUtil.show(mContext, R.string.request_network_available);
            } else if (msg.what == LOGIN_SUCCESS) {
                String response = msg.getData().getString("response");
                try {
                    JSONObject responseJson = new JSONObject(response);
                    int code = responseJson.getInt("code");
                    if (code == 0) {
                        String dataJsons = responseJson.getString("data");
                        JSONObject data = new JSONObject(dataJsons);
                        if (!data.has("code")) {

                        }
                    } else {
                        ToastUtil.show(mContext, responseJson.getString("message"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (msg.what == LOGIN_FAILED) {
                String response = msg.getData().getString("response");
                ToastUtil.show(mContext, response);
            } else if (msg.what == CAPTCHA_MSG) {
            /*
            MessageItem sms = (MessageItem) msg.obj;
            fillCode(sms.getBody());
            if (mSmsObserver != null) {
                getContentResolver().unregisterContentObserver(mSmsObserver);
                mSmsObserver = null;
            }
            // 直接登录
            String phoneNumber = etPhone.getText().toString().trim();
            String authCode = etCode.getText().toString().trim();
            if (!TextUtils.TextUtils.isEmpty(phoneNumber) && !TextUtils.TextUtils.isEmpty(authCode)) {
                if (NetWorkDetectionUtils.checkNetworkAvailable(mContext)) {
                    userLogin("", "", phoneNumber, authCode, "3");
                } else {
                    showToast(R.string.tip_no_net);
                    return;
                }
            } else {
                showToast(R.string.login_info_empty);
                return;
            }
            */
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setBackgroundDrawable(null);

        mContext = this;
        initView();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    private void initView() {

        // init the content view
        etPhone = (EditText) findViewById(R.id.et_login_phone);
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    if (!TextUtils.isEmpty(etCode.getText().toString())) {
                        tvNext.setEnabled(true);
                    }
                } else {
                    tvNext.setEnabled(false);
                }
            }
        });
    }

    private void captchaCreate(String phone) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("phone", phone);
        //params.put("type", "1");
        params.put("length", "4");

        NetworkInfo networkInfo = RequestHelper.createNetworkInfo(mContext, CAPTCHA_SUCCESS, CAPTCHA_FAILED);
        networkInfo.setMethod(Urls.METHOD_CAPTCHA_CREATE);
        networkInfo.setBeforeLogin(true);
        networkInfo.setParams(params);
        requestHttp(networkInfo, null);
    }

    /**
     * 登录验证权限
     */
    private void userLogin(String username, String password,
            final String phone, String captcha, String mode) {
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        params.put("phone", phone);
        params.put("captcha", captcha);
        params.put("mode", mode);
        params.put("versionCode", BuildConfig.VERSION_CODE + "");
        NetworkInfo networkInfo = RequestHelper.createNetworkInfo(this,
                LOGIN_SUCCESS, LOGIN_FAILED);
        networkInfo.setMethod(Urls.METHOD_USER_LOGIN);
        networkInfo.setBeforeLogin(true);
        networkInfo.setParams(params);
        requestHttp(networkInfo, null);
    }


    private boolean phoneNumValid(String number) {
        if (!TextUtils.isEmpty(number)) {
            String pattern = "^1[34578]{1}[0-9]{9}$";// "1[3|4|5|8][0-9]\\d{4,8}$";
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(number);
            return m.matches();
        } else {
            return false;
        }
    }

    protected boolean requestHttp(final NetworkInfo networkInfo, Object tag) {
        Listener<NetworkInfo> listener = new Listener<NetworkInfo>() {
            @Override
            public void onResponse(NetworkInfo ni) {
                Message msg = new Message();
                msg.what = ni.getWhat();
                Bundle bundle = new Bundle();
                bundle.putString("response", ni.getResponse());
                msg.setData(bundle);
                MyHandler myHandler = new MyHandler();
                myHandler.sendMessage(msg);
            }
        };

        ErrorListener errorListener = new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Message msg = new Message();
                msg.what = networkInfo.getFailedWhat();
                ;
                Bundle bundle = new Bundle();
                bundle.putString("response", error.toString());
                msg.setData(bundle);
                MyHandler myHandler = new MyHandler();
                myHandler.sendMessage(msg);
            }
        };

        return RequestHelper.createRequest(this, Urls.getMopHostUrl(this), networkInfo, tag, listener, errorListener);
    }

}
