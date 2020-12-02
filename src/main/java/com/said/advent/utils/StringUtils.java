package com.said.advent.utils;

public class StringUtils {

  public static boolean isValidPassword(String passwordWithPolicy) {

    int passwordIndex = passwordWithPolicy.indexOf(':');

    String password = passwordWithPolicy.substring(passwordIndex + 1).trim();
    char requiredChar = passwordWithPolicy.charAt(passwordIndex - 1);
    String policy = passwordWithPolicy.substring(0, passwordIndex - 2).trim();

    int dashIndex = policy.indexOf('-');
    Integer minNumChar = Integer.parseInt(policy.substring(0, dashIndex));
    Integer maxNumChar = Integer.parseInt(policy.substring(dashIndex + 1));

    int validCnt = 0;
    if (password.charAt(minNumChar - 1) == requiredChar)
      validCnt++;

    if (password.charAt(maxNumChar - 1) == requiredChar)
      validCnt++;

    return (validCnt == 1);


    // Part 1
//    Integer charCount = 0;
//    for (int i = 0; i < password.length(); i++) {
//      if (password.charAt(i) == requiredChar) {
//        charCount++;
//      }
//    }
//
//    if (minNumChar <= charCount && charCount <= maxNumChar) {
//      return true;
//    }
//
//    return false;
  }
}
