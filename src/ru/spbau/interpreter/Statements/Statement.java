package ru.spbau.interpreter.Statements;

import ru.spbau.interpreter.Context;
import ru.spbau.interpreter.Statements.Expressions.Expression;
import ru.spbau.interpreter.analyze.InterpreterVisitor;

/**
 * Created by Moskvitin Maxim.
 *
 * ru.spbau.interpreter.Statements.Statement = ru.spbau.interpreter.Statements.Expressions.Expression | Assign
 */

public abstract class Statement {

  private static final int INDENT = 2;

  public static Statement of(String line) {
    int spaceCount = 0;
    for (int i = 0; i < line.length(); i++) {
      if (line.charAt(i) != ' ')
        break;
      spaceCount++;
    }

    Statement innerStatement;
    line = line.trim();
    if (line.startsWith("var"))
      innerStatement = Assignment.of(line);
    else
      innerStatement = Expression.of(line);

    return new ScopedStatement(spaceCount / INDENT, innerStatement);
  }

  public abstract int evaluate(Context context);

  public void visit(InterpreterVisitor visitor) {
    visitor.visitStatement(this);
  }

}
