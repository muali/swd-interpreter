package ru.spbau.interpreter.analyze;

import ru.spbau.interpreter.Statements.Assignment;
import ru.spbau.interpreter.Statements.Expressions.ConstantExpression;
import ru.spbau.interpreter.Statements.Expressions.Expression;
import ru.spbau.interpreter.Statements.Expressions.SumExpression;
import ru.spbau.interpreter.Statements.Expressions.VariableExpression;
import ru.spbau.interpreter.Statements.ScopedStatement;
import ru.spbau.interpreter.Statements.Statement;

/**
 * Created by Moskvitin Maxim.
 */
public interface InterpreterVisitor {

  default void visitStatement(Statement statement) {
  }

  default void visitScopedStatement(ScopedStatement statement) {
  }

  default void visitExpression(Expression expression) {
  }

  default void visitConstantExpression(ConstantExpression expression) {
  }

  default void visitSumExpression(SumExpression expression) {
  }

  default void visitVariableExpression(VariableExpression variableExpression) {
  }

  default void visitAssignment(Assignment assignment) {
  }

}
