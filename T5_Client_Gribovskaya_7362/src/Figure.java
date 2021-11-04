import java.io.Serializable;


abstract class Figure implements Serializable {

  private static final long serialVersionUID = 1L;
  public double a;
  public String name;

  public Figure(double a, String name) {
    super();
    this.a = a;
    this.name = name;
  }

  public void setParametr(double a) {
    this.a = a;
  }

  public abstract double calculateSquare();
}

