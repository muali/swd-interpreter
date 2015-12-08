package ru.spbau.interpreter.Statements.Expressions;

import ru.spbau.interpreter.Context;
import ru.spbau.interpreter.ParseException;
import ru.spbau.interpreter.analyze.InterpreterVisitor;

public class VariableExpression extends Expression {

  private final String name;

  private VariableExpression(String name) {
    this.name = name;
  }

  public static VariableExpression of(String s) {
    if (!s.matches("\\w+")) {
      throw new ParseException("Invalid variable name: " + s);
    }
    return new VariableExpression(s);
  }

  @Override
  public int evaluate(Context context) {
    if (context.isDeclared(name)) {
      return context.evaluate(name);
    }
    return 0;
  }

  @Override
  public void visit(InterpreterVisitor visitor) {
    super.visit(visitor);
    visitor.visitVariableExpression(this);
  }

}
