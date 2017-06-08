package com.kadequart.android.quartrack.utils;

/**
 * Created by jeetkunedo on 08/06/2017.
 */

public class StringUtils {
  public static String capitalize(String s) {
    if (s.length() == 0) return s;
    return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
  }
}
