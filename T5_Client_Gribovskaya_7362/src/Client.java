import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.Naming;
import java.util.Scanner;

public class Client {

  public static final String OBJECT_NAME = "FigureImpl";
  //  адрес удаленного объекта
  public static final String ADDRESS = "rmi://localhost:1099/" + OBJECT_NAME;

  public static void main(String[] args) {
    System.out.println("Клиент начал работу...");
    FigureInterface figure = null;
    try {
//      получена ссылка на заглушку
      figure = (FigureInterface) Naming.lookup(ADDRESS);
    } catch (NotBoundException | MalformedURLException | RemoteException e) {
      e.printStackTrace();
    }
    if (figure != null) {
      Scanner scanner = new Scanner(System.in);
      System.out.println(
          "Калькулятор предназначен для выполнения действий между двумя целыми числами");
      System.out.println("Выражение необходимо вводить в одну строку, используя знаки +, -, * и /");
      System.out.println("Для завершения работы нажмите 1");
      Circle circle;
//      цикл для ввода пользователя, цикл завершает работу, если пользователь ввел 1
//      while (true) {
        System.out.println("Введите имя: ");
        String inputName = scanner.nextLine();
        System.out.println("Введите параметр: ");
        double inputA = scanner.nextInt();
        circle = new Circle(inputA, inputName);
      try {
        figure.calculate(circle);
      } catch (RemoteException e) {
        e.printStackTrace();
      }
//        проверяется условие завершения работы
//        if (inputString.equals("1")) {
//          break;
//        }

//      }
      System.out.println("Клиент завершает свою работу...");
    }
  }
}