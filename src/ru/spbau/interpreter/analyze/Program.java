package ru.spbau.interpreter.analyze;

import ru.spbau.interpreter.Statements.Statement;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by Moskvitin Maxim.
 */
public class Program {
  private List<Statement> statements;

  public Program(String[] lines) {
    statements = new LinkedList<>();
    for (String line: lines) {
      statements.add(Statement.of(line));
    }
  }

  public void visit(InterpreterVisitor visitor) {
    for (Statement statement: statements) {
      statement.visit(visitor);
    }
  }

}

