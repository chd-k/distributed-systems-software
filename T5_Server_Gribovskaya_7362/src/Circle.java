class Circle extends Figure {

  public Circle(double a, String name) {
    super(a, name);
  }

  @Override
  public double calculateSquare() {
    return Math.PI * a * a;
  }
}