package ru.spbau.interpreter.Statements.Expressions;

import ru.spbau.interpreter.Statements.Statement;
import ru.spbau.interpreter.analyze.InterpreterVisitor;

public abstract class Expression extends Statement {
  public static Expression of(String s) {
    s = s.trim();
    int outerPlus = findOuterPlus(s);
    if (outerPlus > 0) {
      return new SumExpression(
          Expression.of(s.substring(0, outerPlus)),
          Expression.of(s.substring(outerPlus + 1)));
    }

    if (s.startsWith("(") && s.endsWith(")")) {
      return Expression.of(s.substring(1, s.length() - 1));
    }

    if (Character.isDigit(s.charAt(0))) {
      return ConstantExpression.of(s);
    }

    return VariableExpression.of(s);
  }

  private static int findOuterPlus(String s) {
    int scopeCounter = 0;
    for (int i = 0; i < s.length(); i++) {
      switch (s.charAt(i)) {
        case ')':
          scopeCounter += 1;
          break;
        case '(':
          scopeCounter -= 1;
          break;
        case '+':
          if (scopeCounter == 0) {
            return i;
          }
      }
    }
    return -1;
  }

  @Override
  public void visit(InterpreterVisitor visitor) {
    super.visit(visitor);
    visitor.visitExpression(this);
  }

}
