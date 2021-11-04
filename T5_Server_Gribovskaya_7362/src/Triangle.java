class Triangle extends Figure {

  public double b;
  public double c;

  public Triangle(double a, double b, double c, String name) {
    super(a, name);
    this.b = b;
    this.c = c;
  }

  @Override
  public double calculateSquare() {
    double semiperimeter = (a + b + c) / 2;
    return Math.sqrt(
        semiperimeter * (semiperimeter - a) * (semiperimeter - b) * (semiperimeter - c));
  }

  public void setParametr(double a, double b, double c) {
    this.a = a;
    this.b = b;
    this.c = c;
  }
}
