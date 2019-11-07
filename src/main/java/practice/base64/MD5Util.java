package practice.base64;

import java.security.MessageDigest;

/**
 * md5 加动态盐
 */
public class MD5Util {
	
	private static final String KEY = "c4e9ec1c-315e-4cda-8734-969a8aea940d" ;

	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
            n += 256;
        }
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String MD5Encode(String origin, String charsetname) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (charsetname == null || "".equals(charsetname)) {
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes()));
            } else {
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes(charsetname)));
            }
		} catch (Exception exception) {
		}
		return resultString;
	}
	

	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	
	public static String MD5EncodeWithKey(String origin) {
		try {
			String str = new String(origin+ KEY);
			MessageDigest md = MessageDigest.getInstance("MD5");
			return  byteArrayToHexString(md.digest(str
						.getBytes("UTF-8")));
		} catch (Exception exception) {
			exception.printStackTrace();		
			return origin;
		}
	}
	
	
	public static String MD5Encode(String origin) {
		String resultString = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(origin
						.getBytes("UTF-8")));
		} catch (Exception exception) {
			exception.printStackTrace();		
		}
		return resultString;
	}

	public static void main(String[] args) {
		System.out.println(MD5Encode("lvrz"));
		System.out.println(MD5Encode("lvrz"));
		System.out.println(MD5EncodeWithKey("lvrz"));
		System.out.println(MD5EncodeWithKey("lvrz"));
	}

}
