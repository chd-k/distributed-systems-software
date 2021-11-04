/*
 * Выполнила: Грибовская Анастасия, группа 7362
 * Задание: 4 Удаленный "калькулятор"
 * Дата выполнения: 22.10.2021
 * Версия: 0.1
 */

// ------------------------------------------- //

/*
 * Данная программа является реализацией заглушки. В классе определены
 * все функции, предназначенные для вычислений.
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote {

  public int add(int x, int y) throws RemoteException;

  public int subtract(int x, int y) throws RemoteException;

  public int multiply(int x, int y) throws RemoteException;

  public int divide(int x, int y) throws RemoteException;
}
