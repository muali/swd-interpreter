import org.junit.Test;

import static java.util.Collections.singletonMap;
import static org.junit.Assert.assertEquals;

public class ArithmeticTest {
  @Test
  public void testEvalOtherConst() throws Exception {
    Expression expression = Parser.parse("4");
    int actual = expression.evaluate();
    assertEquals(4, actual);
  }

  @Test
  public void testScopedInteger() throws Exception {
    Expression expression = Parser.parse("(42)");
    int actual = expression.evaluate();
    assertEquals(42, actual);
  }

  @Test(expected = ParseException.class)
  public void testInvalidParenthesis() throws Exception {
    Parser.parse("((");
  }

  @Test
  public void testSum() throws Exception {
    Expression expression = Parser.parse("2+2");
    int actual = expression.evaluate();
    assertEquals(4, actual);
  }

  @Test
  public void testSpacedExpression() throws Exception {
    Expression expression = Parser.parse("(2 + 2)");
    int actual = expression.evaluate();
    assertEquals(4, actual);
  }

  @Test
  public void testSumWithParenthesis() throws Exception {
    Expression expression = Parser.parse("2+(2 + 2)");
    int actual = expression.evaluate();
    assertEquals(6, actual);
  }

  @Test
  public void testComplicatedExpression() throws Exception {
    Expression expression = Parser.parse("((2)+(2 + 2))");
    int actual = expression.evaluate();
    assertEquals(6, actual);
  }

  @Test
  public void testVariableExpression() throws Exception {
    Parser.parse("foo");
  }

  @Test
  public void testEvaluateInEnvironment() throws Exception {
    Expression expression = Parser.parse("(x + 5)");
    assertEquals(97, expression.evaluate(singletonMap("x", 92)));
  }
}
