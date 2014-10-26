public class Face {
	private int s1;
	private int s2;
	private int s3;
	private int numero;

	public Face(int s1, int s2, int s3, int numero) {
		this.s1 = s1;
		this.s2 = s2;
		this.s3 = s3;
		this.numero = numero;
	}

	@Override
	public String toString() {
		return s1 + " " + s2 + " " + s3;
	}
}
