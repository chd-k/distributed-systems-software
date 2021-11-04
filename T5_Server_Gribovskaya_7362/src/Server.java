import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
  public static final String OBJECT_NAME = "FigureImpl";
  public static final int PORT = 1099;

  public static void main(String[] args) {
    try {
//      создается реестр, обращение к которому происходит по порту PORT
      final Registry registry = LocateRegistry.createRegistry(PORT);
//      получен удаленный объект
      FigureInterface serviceFigure = new FigureImpl();
//      связывается ссылка с объектом
      registry.bind(OBJECT_NAME, serviceFigure);
    } catch (RemoteException | AlreadyBoundException e) {
      e.printStackTrace();
    }
    System.out.println("Сервер начал работу...");
  }
}
