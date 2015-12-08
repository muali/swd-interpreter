package ru.spbau.interpreter.analyze;

import ru.spbau.interpreter.Statements.Expressions.SumExpression;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Moskvitin Maxim.
 */
public class AdditionCounter implements InterpreterVisitor {

  private int additionCount = 0;

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    List<String> lines = new LinkedList<>();
    while (scanner.hasNext()) {
      String line = scanner.nextLine();
      lines.add(line);
    }
    Program program = new Program(lines.toArray(new String[lines.size()]));

    AdditionCounter counter = new AdditionCounter();
    program.visit(counter);
    System.out.println(counter.additionCount);
  }

  @Override
  public void visitSumExpression(SumExpression expression) {
    additionCount++;
  }

}
