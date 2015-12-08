package ru.spbau.interpreter.Statements.Expressions;

import ru.spbau.interpreter.Context;
import ru.spbau.interpreter.ParseException;
import ru.spbau.interpreter.analyze.InterpreterVisitor;

public class ConstantExpression extends Expression {
  private final int value;

  public ConstantExpression(int value) {
    this.value = value;
  }

  @Override
  public int evaluate(Context context) {
    return value;
  }

  public static ConstantExpression of(String s) {
    try {
      return new ConstantExpression(Integer.valueOf(s));
    } catch (NumberFormatException e) {
      throw new ParseException("Wrong number format for: " + s, e);
    }
  }

  @Override
  public void visit(InterpreterVisitor visitor) {
    super.visit(visitor);
    visitor.visitConstantExpression(this);
  }
}
