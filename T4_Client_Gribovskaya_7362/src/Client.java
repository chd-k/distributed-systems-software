/*
 * Выполнила: Грибовская Анастасия, группа 7362
 * Задание: 4 Удаленный "калькулятор"
 * Дата выполнения: 22.10.2021
 * Версия: 0.1
 */

// ------------------------------------------- //

/*
 * Данная программа является реализацией клиентского приложения. Клиент
 * получает ссылку на заглушку для удаленного объекта, обрабатывает ввод
 * пользователя и инициирует вызов функции, реализованной на сервере.
 * Полученный от сервера результат выводится на экран.
 */

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.Naming;
import java.util.Scanner;

public class Client {

  public static final String OBJECT_NAME = "calculatorImpl";
  //  адрес удаленного объекта
  public static final String ADDRESS = "rmi://localhost:1099/" + OBJECT_NAME;

  public static void main(String[] args) {
    System.out.println("Клиент начал работу...");
    Calculator calculator = null;
    try {
//      получена ссылка на заглушку
      calculator = (Calculator) Naming.lookup(ADDRESS);
    } catch (NotBoundException | MalformedURLException | RemoteException e) {
      e.printStackTrace();
    }
    if (calculator != null) {
      Scanner scanner = new Scanner(System.in);
      System.out.println(
          "Калькулятор предназначен для выполнения действий между двумя целыми числами");
      System.out.println("Выражение необходимо вводить в одну строку, используя знаки +, -, * и /");
      System.out.println("Для завершения работы нажмите 1");
//      цикл для ввода пользователя, цикл завершает работу, если пользователь ввел 1
      while (true) {
        System.out.println("Введите данные: ");
//        удаляются лишние пробелы из строки
        String inputString = scanner.nextLine().replace(" ", "");
//        проверяется условие завершения работы
        if (inputString.equals("1")) {
          break;
        }
//        проверяется правильность введенной строки с помощью регулряного выражения
        if (inputString.matches("\\-?\\d+(\\+|\\-|\\*|\\/)\\-?\\d+")) {
//          извлекаются аргументы арфиметического выражения
          String[] numbers = inputString.split("\\b(\\+|\\-|\\*|\\/)\\b");
          try {
//            извлекается знак выражения, находится подходящая функция и печатается результат
            switch (inputString.replace(numbers[0], "").replace(numbers[1], "")) {
              case "+" -> System.out.println(
                  "Результат = " + calculator.add(Integer.parseInt(numbers[0]),
                      Integer.parseInt(numbers[1])));
              case "-" -> System.out.println(
                  "Результат = " + calculator.subtract(Integer.parseInt(numbers[0]),
                      Integer.parseInt(numbers[1])));
              case "*" -> System.out.println(
                  "Результат = " + calculator.multiply(Integer.parseInt(numbers[0]),
                      Integer.parseInt(numbers[1])));
              case "/" -> System.out.println(
                  "Результат = " + calculator.divide(Integer.parseInt(numbers[0]),
                      Integer.parseInt(numbers[1])));
              default -> System.out.println("Ошибка! Введен неверный знак");
            }
          } catch (RemoteException e) {
            e.printStackTrace();
          }
        } else {
          System.out.println("Ошибка! Строка введена неверно");
        }
      }
      System.out.println("Клиент завершает свою работу...");
    }
  }
}
