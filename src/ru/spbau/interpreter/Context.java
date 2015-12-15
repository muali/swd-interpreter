package ru.spbau.interpreter;


import java.util.*;

/**
 * Created by Moskvitin Maxim.
 */
public class Context {

  private Map<String, ScopedVariable> variables;
  private Stack<Map<String, ScopedVariable>> scopes;
  private int deep;

  private class ScopedVariable {
    final String name;
    int value;
    ScopedVariable shadowed;

    ScopedVariable(String name, int value, ScopedVariable shadowed) {
      this.name = name;
      this.value = value;
      this.shadowed = shadowed;
    }
  }

  public Context() {
    variables = new HashMap<>();
    scopes = new Stack<>();
    scopes.push(new HashMap<>());
    deep = 0;
  }

  public boolean isDeclared(String name) {
    return variables.containsKey(name);
  }

  public int evaluate(String name) {
    return variables.get(name).value;
  }

  public void declare(String name, int value) {
    Map<String, ScopedVariable> locals = scopes.peek();

    ScopedVariable actual;
    if (locals.containsKey(name)) {
      ScopedVariable old = locals.get(name);
      actual = new ScopedVariable(name, value, old.shadowed);
    } else {
      actual = new ScopedVariable(name, value, variables.get(name));
    }

    locals.put(name, actual);
    variables.put(name, actual);
  }

  public void enterScope() {
    deep++;
    scopes.push(new HashMap<>());
  }

  public void exitScope() {
    if (deep == 0) {
      throw new EvaluationException("Attempt exit from root scope");
    }

    Map<String, ScopedVariable> localScope = scopes.pop();
    for (ScopedVariable variable: localScope.values()) {
      if (variable.shadowed != null) {
        declare(variable.name, variable.shadowed.value);
      } else {
        variables.remove(variable.name);
      }
    }
    deep--;
  }

  public int getDeep() {
    return deep;
  }

}
