package ru.spbau.interpreter;

import org.junit.Before;
import org.junit.Test;
import ru.spbau.interpreter.Statements.Statement;

import static org.junit.Assert.assertEquals;

/**
 * Created by Moskvitin Maxim.
 */
public class VariablesTest {
  private Context context;

  @Before
  public void setUp() {
    context = new Context();
  }

  @Test
  public void testSimpleDeclaration() throws Exception {
    assertEquals(5, Statement.of("var x = 5").evaluate(context));
    assertEquals(5, Statement.of("x").evaluate(context));
  }

  @Test
  public void testUsageInExpression() throws Exception {
    Statement.of("var x = 1").evaluate(context);
    assertEquals(7, Statement.of("(x + x) + 5").evaluate(context));
  }

  @Test
  public void testUsageInDeclaration() throws Exception {
    Statement.of("var x = 5").evaluate(context);
    assertEquals(6, Statement.of("var y = x + 1").evaluate(context));
  }

  @Test(expected = EvaluationException.class)
  public void testScopedDeclaration() throws Exception {
    Statement.of("  var x = 5").evaluate(context);
    Statement.of("x").evaluate(context);
  }

  @Test(expected = EvaluationException.class)
  public void testScopeClearing() throws Exception {
    Statement.of("  var x = 5").evaluate(context);
    Statement.of("0").evaluate(context);
    Statement.of("  x").evaluate(context);
  }

  @Test
  public void testRedefinition() throws Exception {
    Statement.of("var x = 3").evaluate(context);
    Statement.of("var x = 5").evaluate(context);
    assertEquals(5, Statement.of("x").evaluate(context));
  }

  @Test
  public void testUsageInInnerScope() throws Exception {
    Statement.of("var x = 3").evaluate(context);
    assertEquals(6, Statement.of("  x + x").evaluate(context));
    assertEquals(5, Statement.of("  var y = x + 2").evaluate(context));
  }

  @Test
  public void testShadowing() throws Exception {
    Statement.of("var x = 3").evaluate(context);
    Statement.of("  var x = x + x").evaluate(context);
    assertEquals(6, Statement.of("  x").evaluate(context));
    assertEquals(3, Statement.of("x").evaluate(context));
  }

  @Test(expected = ParseException.class)
  public void testInvalidDeclaration() throws Exception {
    Statement.of("var x = ");
  }

}
