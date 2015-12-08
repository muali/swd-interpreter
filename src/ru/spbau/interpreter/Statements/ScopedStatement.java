package ru.spbau.interpreter.Statements;

import ru.spbau.interpreter.Context;
import ru.spbau.interpreter.Statements.Statement;
import ru.spbau.interpreter.analyze.InterpreterVisitor;

/**
 * Created by Moskvitin Maxim.
 */
public class ScopedStatement extends Statement {

  private int deep;
  private Statement inner;

  public ScopedStatement(int deep, Statement inner) {
    this.deep = deep;
    this.inner = inner;
  }

  @Override
  public int evaluate(Context context) {
    while (deep > context.getDeep())
      context.enterScope();
    while (deep < context.getDeep())
      context.exitScope();
    return inner.evaluate(context);
  }

  @Override
  public void visit(InterpreterVisitor visitor) {
    super.visit(visitor);
    visitor.visitScopedStatement(this);
    inner.visit(visitor);
  }

}
