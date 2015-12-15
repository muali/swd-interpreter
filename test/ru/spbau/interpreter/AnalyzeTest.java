package ru.spbau.interpreter;

import org.junit.Before;
import org.junit.Test;
import ru.spbau.interpreter.Context;
import ru.spbau.interpreter.Statements.Expressions.Expression;
import ru.spbau.interpreter.ParseException;
import ru.spbau.interpreter.analyze.AdditionCounter;
import ru.spbau.interpreter.analyze.Program;

import static org.junit.Assert.assertEquals;

/**
 * Created by Moskvitin Maxim.
 */
public class AnalyzeTest {

  private AdditionCounter additionCounter;

  @Before
  public void setUp() {
    additionCounter = new AdditionCounter();
  }

  @Test
  public void testSingleExpression() throws Exception {
    Program program = new Program(new String[]{"1 + 2 + (3 + 4)"});
    program.visit(additionCounter);
    assertEquals(3, additionCounter.getAdditionCount());
  }

  @Test
  public void testMultipleExpression() throws Exception {
    Program program = new Program(new String[]{
            "1 + 2",
            "2 + 0"
    });
    program.visit(additionCounter);
    assertEquals(2, additionCounter.getAdditionCount());
  }

  @Test
  public void testScopedExpression() throws Exception {
    Program program = new Program(new String[]{"  1 + 1"});
    program.visit(additionCounter);
    assertEquals(1, additionCounter.getAdditionCount());
  }

  @Test
  public void testVariableDeclaration() throws Exception {
    Program program = new Program(new String[]{"var x = 2 + 2"});
    program.visit(additionCounter);
    assertEquals(1, additionCounter.getAdditionCount());
  }

}
