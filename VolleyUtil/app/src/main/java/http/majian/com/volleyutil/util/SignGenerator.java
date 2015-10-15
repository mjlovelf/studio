/*
 * Copyright (C) 2014 Huoyunren.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package http.majian.com.volleyutil.util;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Generate the request sign with key and ordered parameters
 * 
 * @author Jet.Z
 * 
 */
public class SignGenerator {

	/**
	 * Generate the sign<br>
	 * The rule is : MD5(key+params+key).uppercase
	 * 
	 * @param map
	 * @param secret
	 * @return
	 */
	public static String createSign(Map<String, String> map, String secret) {
		String sign = secret;
		if (map != null && !map.isEmpty()) {
			// Sort the params map by the name of key
			Map<String, String> sortMap = new TreeMap<String, String>(
					new Comparator<String>() {
						@Override
						public int compare(String o1, String o2) {
							return o1.compareTo(o2);
						}
					});
			sortMap.putAll(map);
			for (Map.Entry<String, String> entry : sortMap.entrySet()) {
				if (!("").equals(entry.getKey()) && entry.getValue() != null) {
					sign += entry.getKey() + "=" + entry.getValue();
				}
			}
		}
		sign += secret;
		sign = MD5Utils.encode(sign).toUpperCase();
		return sign;
	}

}
