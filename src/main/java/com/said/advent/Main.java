package com.said.advent;

import com.said.advent.utils.StringUtils;
import com.said.advent.utils.Utils;

import java.util.List;
import java.util.Set;

public class Main {

  public static void main(String[] args) throws Exception {

    // day 1
    //fixExpenseReports();
    //fixExpenseReports3();

    // day 2
    //fetchValidPasswords();

    // day 3
    tobogganRide();

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
}
