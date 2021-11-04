import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FigureInterface extends Remote {

  public void calculate(Figure f) throws RemoteException;
}
