package ru.spbau.interpreter.Statements.Expressions;

import ru.spbau.interpreter.Context;
import ru.spbau.interpreter.Statements.Expressions.Expression;
import ru.spbau.interpreter.analyze.InterpreterVisitor;

public class SumExpression extends Expression {
  private final Expression left;
  private final Expression right;

  public SumExpression(Expression left, Expression right) {
    this.left = left;
    this.right = right;
  }

  @Override
  public int evaluate(Context context) {
    return left.evaluate(context) + right.evaluate(context);
  }

  @Override
  public void visit(InterpreterVisitor visitor) {
    super.visit(visitor);
    visitor.visitSumExpression(this);
    left.visit(visitor);
    right.visit(visitor);
  }

}
