/*
 * Выполнила: Грибовская Анастасия, группа 7362
 * Задание: 4 Удаленный "калькулятор"
 * Дата выполнения: 22.10.2021
 * Версия: 0.1
 */

// ------------------------------------------- //

/*
 * Данная программа является реализацией удаленного объекта. В классе
 * описаны все функции, определенные в интерфейсе Calculator.
 */

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorImpl extends UnicastRemoteObject implements Calculator {

  public CalculatorImpl() throws RemoteException {
    super();
  }

  public int add(int x, int y) throws RemoteException {
    return x + y;
  }

  public int subtract(int x, int y) throws RemoteException {
    return x - y;
  }

  public int multiply(int x, int y) throws RemoteException {
    return x * y;
  }

  public int divide(int x, int y) throws RemoteException {
    return x / y;
  }
}
