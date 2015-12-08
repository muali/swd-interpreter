package ru.spbau.interpreter;

import ru.spbau.interpreter.Statements.Statement;

import java.util.Scanner;

/**
 * Created by Moskvitin Maxim.
 */
public class Interpreter {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Context context = new Context();
    while (scanner.hasNext()) {
      String line = scanner.nextLine();
      Statement statement = Statement.of(line);
      System.out.println(statement.evaluate(context));
    }
  }

}
