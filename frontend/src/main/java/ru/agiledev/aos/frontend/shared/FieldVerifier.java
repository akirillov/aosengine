package ru.agiledev.aos.frontend.shared;

public class FieldVerifier {

  //todo: можно использовать server-side верификацию для первоначального определения типа запроса (но нахуя?)
  /*или что-то в этом роде*/

  public static boolean isValidName(String name) {
    if (name == null) {
      return false;
    }
    return name.length() > 2;
  }
}
