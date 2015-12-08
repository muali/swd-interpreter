package ru.spbau.interpreter.Statements;

import ru.spbau.interpreter.Context;
import ru.spbau.interpreter.Statements.Expressions.Expression;
import ru.spbau.interpreter.ParseException;
import ru.spbau.interpreter.analyze.InterpreterVisitor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Moskvitin Maxim.
 */
public class Assignment extends Statement {

  private String variableName;
  private Expression expression;

  public static Assignment of(String line) {
    Pattern pattern = Pattern.compile("\\s*var\\s*(\\w+)\\s*=(.*)");
    Matcher matcher = pattern.matcher(line);
    if (matcher.find()) {
      String name = matcher.group(1);
      String rawExpression = matcher.group(2);
      return new Assignment(name, Expression.of(rawExpression));
    } else {
      throw new ParseException("Expected assignment statement, found: " + line);
    }
  }

  public Assignment(String variableName, Expression expression) {
    this.variableName = variableName;
    this.expression = expression;
  }

  @Override
  public int evaluate(Context context) {
    int value = expression.evaluate(context);
    context.declare(variableName, value);
    return value;
  }

  @Override
  public void visit(InterpreterVisitor visitor) {
    super.visit(visitor);
    visitor.visitAssignment(this);
    expression.visit(visitor);
  }

}
