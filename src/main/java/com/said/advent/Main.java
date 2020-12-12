package com.said.advent;

import com.said.advent.utils.StringUtils;
import com.said.advent.utils.Utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

  public static void main(String[] args) throws Exception {

    // day 1
    //fixExpenseReports();
    //fixExpenseReports3();

    // day 2
    //fetchValidPasswords();

    // day 3
    //tobogganRide();

    // day 4
    passportProcessing();
  }

  private static void fixExpenseReports() throws Exception {

    Set<Integer> expenses = Utils.parseResourceIntoSet("expenseReport");
    for(Integer expense : expenses) {
      Integer compliment = 2020 - expense;
      if (expenses.contains(compliment)) {
        System.out.println(expense + ":" + compliment);
        System.out.println(expense * compliment);
        return;
      }
    }
  }

  private static void fixExpenseReports3() throws Exception {

    Set<Integer> expenses = Utils.parseResourceIntoSet("expenseReport");
    Integer[] expenseArray = expenses.toArray(Integer[]::new);

    for (int i = 0; i < expenseArray.length; i++) {
      for (int j = 1; j < expenseArray.length; j++) {
        for (int k = 2; k < expenseArray.length; k++) {
          if (expenseArray[i] + expenseArray[j] + expenseArray[k] == 2020) {
            System.out.println(expenseArray[i] + ":" + expenseArray[j] + ":" + expenseArray[k]);
            System.out.println(expenseArray[i] * expenseArray[j] * expenseArray[k]);
            return;
          }
        }
      }
    }
  }

  private static void fetchValidPasswords() throws Exception {

    List<String> passwords = Utils.parseResourceIntoList("passwords");
    int validPasswords = 0;

    for(String password : passwords) {
      if (StringUtils.isValidPassword(password))
        validPasswords++;
    }

    System.out.println(validPasswords);
  }

  private static void tobogganRide() throws Exception {

    char[][] map = Utils.parseResourceIntoArray("tobogganMap");

    int colIx = 0, rowIx = 0, numTrees = 0;
    while (rowIx < map.length) {

      if (map[rowIx][colIx] == '#') {
        numTrees++;
      }

      colIx = (colIx + 1) % map[rowIx].length;
      rowIx = rowIx + 2;
    }

    System.out.println(numTrees);
  }

  private static void passportProcessing() throws Exception {

    List<String> passportStrings = Utils.parseResourceIntoList("passports");

    int validPassports = 0;
    int i = 0;
    while (i < passportStrings.size()) {

      Map<String, String> passport = new HashMap<>();

      while (i < passportStrings.size() && !passportStrings.get(i).isBlank()) {

        String[] parts = passportStrings.get(i).split(" ");
        for (int j = 0; j < parts.length; j++) {
          String[] keyVal = parts[j].split(":");
          passport.put(keyVal[0], keyVal[1]);
        }

        i++;
      }

      if (isValidPassport(passport)) {
        validPassports++;
      }

      i++;
    }

    System.out.println(validPassports);
  }

  private static boolean isValidPassport(Map<String, String> passport) {
    if (passport.get("ecl") == null ||
        passport.get("pid") == null ||
        passport.get("hcl") == null ||
        passport.get("byr") == null ||
        passport.get("iyr") == null ||
        passport.get("hgt") == null ||
        passport.get("eyr") == null) {
      return false;
    }

    try {
      Integer byr = Integer.parseInt(passport.get("byr"));
      if (byr < 1920 || byr > 2002)
        return false;

      Integer iyr = Integer.parseInt(passport.get("iyr"));
      if (iyr < 2010 || iyr > 2020)
        return false;

      Integer eyr = Integer.parseInt(passport.get("eyr"));
      if (eyr < 2020 || eyr > 2030)
        return false;

      Boolean isCm = true;
      Integer hgtIx = passport.get("hgt").indexOf("cm");
      if (hgtIx == -1) {
        hgtIx = passport.get("hgt").indexOf("in");
        if (hgtIx == -1)
          return false;

        isCm = false;
      }

      Integer hgt = Integer.parseInt(passport.get("hgt").substring(0, hgtIx));
      if (isCm) {
        if (hgt < 150 || hgt > 193)
          return false;
      } else {
        if (hgt < 59 || hgt > 76)
          return false;
      }

      if (!isValidHairColor(passport.get("hcl")))
        return false;

      if (!isValidEyeColor(passport.get("ecl")))
        return false;

      if (!isValidPassportNum(passport.get("pid")))
        return false;

    } catch (Exception e) {
      return false;
    }

    return true;
  }

  private static boolean isValidHairColor(String hairColor) {
    if (hairColor.length() != 7)
      return false;

    if (hairColor.charAt(0) != '#')
      return false;

    List<Character> validValues = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        'a', 'b', 'c', 'd', 'e', 'f');

    for (int i = 1; i < hairColor.length(); i++) {
      if (!validValues.contains(hairColor.charAt(i)))
        return false;
    }

    return true;
  }

  private static boolean isValidEyeColor(String eyeColor) {
    List<String> validEyes = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
    if (!validEyes.contains(eyeColor))
      return false;

    return true;
  }

  private static boolean isValidPassportNum(String passportNum) {
    if (passportNum.length() != 9)
      return false;

    for (int i = 0; i < passportNum.length(); i++) {
      Integer num = Integer.parseInt(String.valueOf(passportNum.charAt(i)));
      if (num < 0 || num > 9) {
        return false;
      }
    }

    return true;
  }
}
