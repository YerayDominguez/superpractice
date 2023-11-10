package dev.yeray.sp.utils;

public final class Utils {

	private Utils() {}
	
    public static boolean isNotBlank(String str) {
        return str != null && !str.trim().isEmpty();
    }
}
