package http.majian.com.volleyutil.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5数据加密
 *
 * @author ʯѩ÷
 */
public class MD5Utils {
	/**
	 * @param text
	 * @return
	 */
	public static String encode(String text) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(text.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			text = buf.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return text;
	}
}
