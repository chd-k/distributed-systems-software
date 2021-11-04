class Rectangle extends Figure {

  public double b;

  public Rectangle(double a, double b, String name) {
    super(a, name);
    this.b = b;
  }

  @Override
  public double calculateSquare() {
    return a * b;
  }

  public void setParametr(double a, double b) {
    this.a = a;
    this.b = b;
  }
}
