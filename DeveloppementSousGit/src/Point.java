public class Point {
	private Double x;
	private Double y;
	private Double z;
	private int numero;

	public Point(Double x, Double y, Double z, int numero) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.numero = numero;
	}

	@Override
	public String toString() {
		return x + " " + y + " " + z;
	}

	public Double getX() {
		return x;
	}

	public Double getY() {
		return y;
	}

	public Double getZ() {
		return z;
	}

	public int getNumero() {
		return numero;
	}

}
