package http.majian.com.volleyutil.network;

/**
 * 服务端数据接收实体
 */
public final class ResponseObject {

    // 客户端错误
    public static final int RESPONSE_ERROR_CONNECT = 10000;
    public static final int RESPONSE_ERROR_TIMEOUT = 10001;
    public static final int RESPONSE_ERROR_DATAPARSEERROR = 10002;
    public static final int RESPONSE_ERROR_DATANOENOUGH = 10003;
    public static final int RESPONSE_ERROR_SERVERERROR = 10004;
    public static final int RESPONSE_ERROR_NOCONNECTION = 10005;
    public static final int RESPONSE_ERROR_UPLOADFILEFAILED = 10006;
    public static final int RESPONSE_ERROR_PARAMERROR = 10007;
    public static final int RESPONSE_ERROR_DATAGETERROR = 10008;
    public static final int RESPONSE_ERROR_CANCELED = 20000;
    public static final int RESPONSE_ERROR_SUCCESS = 0;

    public static ResponseObject sResponseObjectSuccessed = new ResponseObject();
    public static ResponseObject sResponseObjectConnectError = new ResponseObject(ResponseObject.RESPONSE_ERROR_CONNECT);
    public static ResponseObject sResponseObjectDataNoEnough = new ResponseObject(ResponseObject.RESPONSE_ERROR_DATANOENOUGH);
    public static ResponseObject sResponseObjectDataParseError = new ResponseObject(ResponseObject.RESPONSE_ERROR_DATAPARSEERROR);
    public static ResponseObject sResponseObjectDataGetError = new ResponseObject(ResponseObject.RESPONSE_ERROR_DATAGETERROR);
    public static ResponseObject sResponseObjectNoConnection = new ResponseObject(ResponseObject.RESPONSE_ERROR_NOCONNECTION);
    public static ResponseObject sResponseObjectCanceled = new ResponseObject(ResponseObject.RESPONSE_ERROR_CANCELED);
    public static ResponseObject sResponseObjectParamError = new ResponseObject(ResponseObject.RESPONSE_ERROR_PARAMERROR);

    private final boolean mSuccess;
    private final int mErrorCode;
    private String mMessage;
    private String mServerData;
    private Object mClientData;

    public ResponseObject(boolean success, int code) {
        mSuccess = success;
        mErrorCode = code;
    }

    public ResponseObject(int code) {
        this(code == RESPONSE_ERROR_SUCCESS,code);
    }

    public ResponseObject() {
        this(true, RESPONSE_ERROR_SUCCESS);
    }

    public boolean isSuccess() {
        return mSuccess;
    }

    public int getErrorCode() {
        return mErrorCode;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getServerData() {
        return mServerData;
    }

    public void setServerData(String data) {
        mServerData = data;
    }

    public Object getClientData() {
        return mClientData;
    }

    public void setClientData(Object clientData) {
        mClientData = clientData;
    }

    @Override
    public String toString() {
        return "success=" + mSuccess +
                ";errorCode=" + mErrorCode +
                ";message=" + mMessage +
                ";data=" + mServerData;
    }
}
