/*
 * Выполнила: Грибовская Анастасия, группа 7362
 * Задание: 4 Удаленный "калькулятор"
 * Дата выполнения: 22.10.2021
 * Версия: 0.1
 */

// ------------------------------------------- //

/*
 * Данная программа является реализацией серверного приложения. Сервер создает
 * реестр, получает удаленный объект, связывает ссылку с удаленным объектом,
 * занося запись в реестр. Обращение к серверу позволяет выполнять функции
 * удаленного объекта с полученными параметрами.
 */

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

  public static final String OBJECT_NAME = "calculatorImpl";
  public static final int PORT = 1099;

  public static void main(String[] args) {
    try {
//      создается реестр, обращение к которому происходит по порту PORT
      final Registry registry = LocateRegistry.createRegistry(PORT);
//      получен удаленный объект
      Calculator service = new CalculatorImpl();
//      связывается ссылка с объектом
      registry.bind(OBJECT_NAME, service);
    } catch (RemoteException | AlreadyBoundException e) {
      e.printStackTrace();
    }
    System.out.println("Сервер начал работу...");
  }
}


