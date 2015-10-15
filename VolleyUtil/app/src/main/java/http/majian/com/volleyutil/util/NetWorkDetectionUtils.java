package http.majian.com.volleyutil.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * 检测网络是不是可用
 *
 * @author renfujiang
 *
 */
public class NetWorkDetectionUtils {
	private static InetAddress addr;


	public static boolean checkNetworkAvailable(Context context) {
		NetworkInfo info = null;
		ConnectivityManager connManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connManager != null) {
			info = connManager.getActiveNetworkInfo();
			if (info == null) {
				return false;
			} else {
				return info.isConnected();
			}
		} else {
			return false;
		}

	}

	public static String getNetworkType(Context context) {
		String type = "";
		ConnectivityManager connManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connManager != null) {
			NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
			if (networkInfo != null) {
				type = networkInfo.getTypeName();
			}
		}
		return type;
	}


	/**
	 * 手机连接的网络类型
	 * */
	public static String getNetWork(Context context) {
		int type;
		ConnectivityManager connManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connManager != null) {
			NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
			if (networkInfo != null) {
				type = networkInfo.getType();
				switch (type) {
				case ConnectivityManager.TYPE_WIFI:
					return "wifi";
				case ConnectivityManager.TYPE_MOBILE:

					return getMobileType(context);
				default:
					break;
				}


			}
		}
		return "no network";
	}

	/**
	 * 网络类型是2g还是3g
	 */
	public static String getMobileType(Context context){
		int type;
		ConnectivityManager connectManager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		type=connectManager.getActiveNetworkInfo().getSubtype();
		switch (type) {
		case TelephonyManager.NETWORK_TYPE_CDMA:

			return "2g CDMA";

		case TelephonyManager.NETWORK_TYPE_EDGE:
			return "2g EDEG";
		case TelephonyManager.NETWORK_TYPE_GPRS:
			return "2g GPRS";
		case TelephonyManager.NETWORK_TYPE_EVDO_0:
			return "3g EVDO";
		case TelephonyManager.NETWORK_TYPE_EVDO_A:
			return "3g EVDO";
		case TelephonyManager.NETWORK_TYPE_HSDPA:
			return "3g WCDMA";
		case TelephonyManager.NETWORK_TYPE_UMTS:
			return "3g WCDMA";
		case TelephonyManager.NETWORK_TYPE_HSPA:
			return "3g WCDMA";
		case TelephonyManager.NETWORK_TYPE_HSUPA:
			return "3g WCDMA";
		default:
			break;
		}
		return " mobile";
	}

	/**
	 * get ip address
	 * */
	public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e("WifiPreference IpAddress", ex.toString());
        }
        return null;
    }


	public static InetAddress getIpAddress(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(android.content.Context.WIFI_SERVICE );
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        try {
			addr = InetAddress.getByName(String.format("%d.%d.%d.%d",
					(ipAddress & 0xff), (ipAddress >> 8 & 0xff),
					(ipAddress >> 16 & 0xff), (ipAddress >> 24 & 0xff)));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return addr;
}
}
