import java.awt.Color;
import java.awt.Font;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Telecommande extends JFrame {
	private EntierObjetTest coeffZ = new EntierObjetTest(1);
	private EntierObjetTest coeffZ1 = new EntierObjetTest(1);
	private EntierObjetTest coeffZ2 = new EntierObjetTest(1);
	private EntierObjetTest coeffXetY = new EntierObjetTest(1);
	private EntierObjetTest decalageX = new EntierObjetTest(1);
	private EntierObjetTest decalageY = new EntierObjetTest(1);
	private JSlider sZ = new JSlider(0, 100, coeffZ.entier);
	private JSlider sZ1 = new JSlider(0, 400, coeffZ1.entier);
	private JSlider sZ2 = new JSlider(0, 400, coeffZ2.entier);
	private JSlider sXY = new JSlider(0, 100, coeffXetY.entier);
	private JSlider sX = new JSlider(0, 400, decalageX.entier);
	private JSlider sY = new JSlider(0, 400, decalageY.entier);
	private JPanel panel1 = new JPanel();
	AffichageDuModele affichageDuModele;

	public Telecommande(AffichageDuModele affichageDuModele) {
		this.affichageDuModele = affichageDuModele;
		this.setSize(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setBackground(Color.GRAY);
		panel1.setBackground(Color.GRAY);
		sX.addChangeListener(new SliderListener(decalageX,
				this.affichageDuModele));
		sZ.addChangeListener(new SliderListener(coeffZ, this.affichageDuModele));
		sZ1.addChangeListener(new SliderListener(coeffZ1, this.affichageDuModele));
		sZ2.addChangeListener(new SliderListener(coeffZ2, this.affichageDuModele));
		sXY.addChangeListener(new SliderListener(coeffXetY,
				this.affichageDuModele));
		sY.addChangeListener(new SliderListener(decalageY,
				this.affichageDuModele));
		panel1.add(sX);
		panel1.add(sY);
		panel1.add(sXY);
		//panel1.add(sZ);
		panel1.add(sZ1);
		panel1.add(sZ2);
		this.add(panel1);
		this.setVisible(true);

	}

	public int getCoeffXetY() {
		return coeffXetY.entier;
	}

	public int getCoeffZ() {
		return coeffZ.entier;
	}
public int getCoeffZ1() {
	return coeffZ1.entier;
}
public int getCoeffZ2() {
	return coeffZ2.entier;
}
	public int getDecalageX() {
		return decalageX.entier;
	}

	public int getDecalageY() {
		return decalageY.entier;
	}

	private class SliderListener implements ChangeListener {
		EntierObjetTest value;
		AffichageDuModele affichageDuModele;

		public SliderListener(EntierObjetTest value, AffichageDuModele affichageDuModele) {
			this.value = value;
			this.affichageDuModele = affichageDuModele;
		}

		@Override
		public void stateChanged(ChangeEvent e) {
			JSlider source = (JSlider) e.getSource();
			this.value.entier = (int) source.getValue();
			this.affichageDuModele.repaint();
		}

	}
}
