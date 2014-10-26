public class Segment {
	private int p1;
	private int p2;
	private int numero;

	public Segment(int p1, int p2, int numero) {
		this.p1 = p1;
		this.p2 = p2;
		this.numero = numero;
	}

	@Override
	public String toString() {
		return p1 + " " + p2;
	}

	public int getNumero() {
		return numero;
	}

	public int getP1() {
		return p1;
	}

	public int getP2() {
		return p2;
	}
}
