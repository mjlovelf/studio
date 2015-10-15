package http.majian.com.volleyutil.network;

import java.util.List;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

/**
 * Created by apple on 15/5/18.
 */
public class ResponseHelper {

    public static <T> ResponseObject doParseResult(String data, Class<T> classOft) {
        try {
            return parseResultData(data, classOft);
        } catch (Exception e) {
            return ResponseObject.sResponseObjectConnectError;
        }
    }

    public static <T> ResponseObject doParseResultList(String data, TypeToken typeToke) {
        try {
            return parseResultListData(data, typeToke);
        } catch (Exception e) {
            return ResponseObject.sResponseObjectConnectError;
        }
    }

    private static <T> ResponseObject parseResultData(String data, Class<T> classOfT) throws JsonSyntaxException, JSONException {
        if (TextUtils.isEmpty(data)) {
            return ResponseObject.sResponseObjectDataNoEnough;
        }

        JSONObject json = new JSONObject(data);

        boolean success = json.getInt("code") == 0;
        int code = json.optInt("code", 1);
        ResponseObject clientObject = new ResponseObject(code == 0, code);
        clientObject.setServerData(data);
        if (json.has("message")) {
            clientObject.setMessage(json.getString("message"));
        }
        if (!success) {
            return clientObject;
        }
        if (!json.has("data")) {
            return ResponseObject.sResponseObjectDataParseError;
        }
        Object jsonData = json.get("data");
        if (classOfT != null) {
            Object clientData = (T) (new Gson().fromJson(jsonData.toString(), classOfT));
            clientObject.setClientData(clientData);
        } else {
            clientObject.setClientData(jsonData);
        }
        return clientObject;
    }

    private static <T> ResponseObject parseResultListData(String data, TypeToken typeToke)
            throws JSONException {
        if (TextUtils.isEmpty(data)) {
            return ResponseObject.sResponseObjectDataNoEnough;
        }
        JSONObject json = new JSONObject(data);

        boolean success = json.getInt("code") == 0;
        int code = json.optInt("code", 1);
        ResponseObject clientObject = new ResponseObject(code == 0, code);
        clientObject.setServerData(data);
        if (json.has("message")) {
            clientObject.setMessage(json.getString("message"));
        }
        if (!success) {
            return clientObject;
        }
        if (!json.has("data")) {
            return ResponseObject.sResponseObjectDataParseError;
        }

        JSONObject dataJson = json.getJSONObject("data");
        String result = null;
        if (dataJson.has("result")) {
            result = dataJson.getString("result");
        } else if (dataJson.has("data")) {
            result = dataJson.getString("data");
        }

        if (!"[]".equals(result)) {
            List<T> list = new Gson().fromJson(result, typeToke.getType());
            clientObject.setClientData(list);
        } else {
            clientObject.setClientData(dataJson);
        }
        return clientObject;
    }
}
