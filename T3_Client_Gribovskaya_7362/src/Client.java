/*
 * Выполнила: Грибовская Анастасия, группа 7362
 * Задание: 3 Сериализация объекта
 * Дата выполнения: 08.10.2021
 * Версия: 0.1
 */

// ------------------------------------------- //

/*
 * Данная программа выполняет функции клиентского приложения. В начале работы
 * создается сокет для связи с сервером. Далее создается объект класса Rectangle
 * с заданными значениями a и b. Объект сериализуется и передается серверу.
 * В заключении от сервера принимается ответ, состоящий из вычисленной площади.
 */

import java.net.*;
import java.io.*;

public class Client {

  //    локальный порт сервера
  public static final int PORT = 8080;

  public static void main(String[] args) {
//        адрес сокета
    String address = "localhost";
    System.out.println("Адрес сокета клиента: " + address);
    Socket socket = null;
    ObjectOutputStream objectOutputStream = null;
    try {
//            создание сокета для связи с сервером
      socket = new Socket(address, PORT);
      System.out.println("Сокет клиента: " + socket);
//            значения a и b объекта класса Rectangle
      double a = 9.0;
      double b = 13.5;
//            создание объекта класса
      Rectangle rectangle = new Rectangle(a, b);
      OutputStream outputStream = socket.getOutputStream();
//            создание объекта для сериализации объектов в поток
      objectOutputStream = new ObjectOutputStream(outputStream);
//            сериализация объекта класса Rectangle
      objectOutputStream.writeObject(rectangle);
      System.out.println("Объект класса со значениями a = " + a + " b = " + b + " передан серверу");
//            создание буферированного потока ввода
      BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            вывод значения площади, полученной от сервера
      System.out.println("Площадь, вычисленная сервером: " + input.readLine());
    } catch (IOException e) {
      System.out.println("Исключение: " + e.getMessage());
      e.printStackTrace();
    } finally {
      System.out.println("Завершение...");
      try {
//                закрытие потока
        if (objectOutputStream != null) {
          objectOutputStream.close();
        }
//                закрытие сокета
        if (socket != null) {
          socket.close();
        }
      } catch (IOException e) {
        System.out.println("Исключение: " + e.getMessage());
        e.printStackTrace();
      }
    }
  }
}

class Rectangle implements Serializable {

  private double a;
  private double b;
  public static final long serialVersionUID = 2L;

  public Rectangle(double a, double b) {
    this.a = a;
    this.b = b;
  }

  public double square() {
    return a * b;
  }
}
