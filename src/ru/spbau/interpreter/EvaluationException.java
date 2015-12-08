package ru.spbau.interpreter;

/**
 * Created by Moskvitin Maxim.
 */
public class EvaluationException extends RuntimeException {

  public EvaluationException(String message, Throwable cause) {
    super(message, cause);
  }

  public EvaluationException(String message) {
    super(message);
  }

}
