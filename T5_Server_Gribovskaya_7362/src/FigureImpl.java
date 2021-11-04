import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class FigureImpl extends UnicastRemoteObject implements FigureInterface {

  private static final long serialVersionUID = 1L;

  public FigureImpl() throws RemoteException {
    super();
  }

  @Override
  public void calculate(Figure f) throws RemoteException {
    System.out.println("Вычисленная площадь " + f.name + " равна " + f.calculateSquare());
  }
}
