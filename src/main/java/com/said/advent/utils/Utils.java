package com.said.advent.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Utils {

  public static InputStream getFileFromResourceAsStream(String fileName) {
    InputStream is = Utils.class.getClassLoader().getResourceAsStream(fileName);

    if (is == null) {
      throw new IllegalArgumentException("file not found! " + fileName);
    }

    return is;
  }

  public static Set<Integer> parseResourceIntoSet(String fileName) throws Exception {

    Set<Integer> intSet = new HashSet<>();

    try (InputStreamReader streamReader =
             new InputStreamReader(Utils.getFileFromResourceAsStream(fileName), StandardCharsets.UTF_8);
         BufferedReader reader = new BufferedReader(streamReader)) {

      String line;
      while ((line = reader.readLine()) != null) {
        intSet.add(Integer.parseInt(line));
      }

    } catch (IOException e) {
      throw new Exception("Error occurred parsing " + fileName);
    }

    return intSet;
  }

  public static List<String> parseResourceIntoList(String fileName) throws Exception {
    List<String> stringList = new ArrayList<>();

    try (InputStreamReader streamReader =
             new InputStreamReader(Utils.getFileFromResourceAsStream(fileName), StandardCharsets.UTF_8);
         BufferedReader reader = new BufferedReader(streamReader)) {

      String line;
      while ((line = reader.readLine()) != null) {
        stringList.add(line);
      }

    } catch (IOException e) {
      throw new Exception("Error occurred parsing " + fileName);
    }

    return stringList;
  }

  public static char[][] parseResourceIntoArray(String fileName) throws Exception {
    List<String> stringList = parseResourceIntoList(fileName);
    int cols = stringList.get(0).length();
    int rows = stringList.size();

    char[][] array = new char[rows][cols];
    for (int rowIx = 0; rowIx < rows; rowIx++) {

      String line = stringList.get(rowIx);
      for (int colIx = 0; colIx < line.length(); colIx++) {
        array[rowIx][colIx] = line.charAt(colIx);
      } // end cols
    } // end rows

    return array;
  }
}
