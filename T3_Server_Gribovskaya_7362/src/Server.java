/*
 * Выполнила: Грибовская Анастасия, группа 7362
 * Задание: 3 Сериализация объекта
 * Дата выполнения: 08.10.2021
 * Версия: 0.1
 */

// ------------------------------------------- //

/*
 * Данная программа выполняет функции серверного приложения. В начале работы
 * создается серверный сокет, который позднее переходит в режим ожидания.
 * При получении запроса от клиента сервер десериализует объект. На основании
 * полученных значений серверное приложение вычисляет площадь прямоугольника
 * и передает ответ клиенту через сокет.
 */

import java.io.*;
import java.net.*;

public class Server {

  //    локальный порт сервера
  public static final int PORT = 8080;

  public static void main(String[] args) {
    ServerSocket serverSocket = null;
    Socket socket = null;
    ObjectInputStream objectInputStream = null;
    try {
//            создание серверного сокета
      serverSocket = new ServerSocket(PORT);
      System.out.println("Старт " + serverSocket);
//            переход в режим ожидания запроса
      socket = serverSocket.accept();
      System.out.println("Соединение подтверждено " + socket);
      InputStream inputStream = socket.getInputStream();
//            создание объекта для чтения сериализованных данных из потока
      objectInputStream = new ObjectInputStream(inputStream);
//            десериализация объекта класса Rectangle
      Rectangle receivedRectangle = (Rectangle) objectInputStream.readObject();
      System.out.println("Получен объект от клиента");
//            создание объекта для вывода в поток вывода сокета
      PrintWriter output = new PrintWriter(
          new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),
          true);
//            вычисление площади и передача значения клиенту
      output.println(receivedRectangle.square());
      System.out.println("Значение площади вычислено и отправлено клиенту");
    } catch (IOException | ClassNotFoundException e) {
      System.out.println("Исключение: " + e.getMessage());
      e.printStackTrace();
    } finally {
      System.out.println("Завершение...");
      try {
//                закрытие потока
          if (objectInputStream != null) {
              objectInputStream.close();
          }
          if (socket != null) {
              socket.close();
          }
//                закрытие сокета
          if (serverSocket != null) {
              serverSocket.close();
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

