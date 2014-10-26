import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Label;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;

@SuppressWarnings("serial")
public class AffichageDuModele extends JFrame {
	private JPanel panel1 = new JPanel();
	private List<Point> list_points = new ArrayList<Point>();
	private List<Segment> list_segments = new ArrayList<Segment>();
	private List<Face> list_faces = new ArrayList<Face>();
	//private Integer coeffZ = 1;
	//private Integer coeffXetY =1;
	//private Integer decalageX = 1;
	//private Integer decalageY = 1;
	//private JSlider sZ = new JSlider(0, 1000, coeffZ);
	//private JSlider sXY = new JSlider(0, 1000, coeffXetY);
	//private JSlider sX = new JSlider(0, 1000, decalageX);
	//private JSlider sY = new JSlider(0, 1000, decalageY);
	Telecommande t = new Telecommande(this);
	
	private boolean RecupDonneeFichier(){
		List<String> fichier = new ArrayList<String>();
		JFileChooser fc = new JFileChooser("modeles/");
		fc.setAcceptAllFileFilterUsed(false);
		fc.addChoosableFileFilter(new FileFilter() {
			@Override
			public boolean accept(File f) {
				 return f.getName().endsWith(".gts");
			}
			@Override
			public String getDescription() {
				return "Fichier GNU Triangulated Surface Library (.gts)";
			}
		});
		fc.addChoosableFileFilter(new FileFilter() {
			@Override
			public boolean accept(File f) {
				 return f.getName().endsWith(".txt");
			}
			@Override
			public String getDescription() {
				return "Fichier texte (.txt)";
			}
		});
		fc.showOpenDialog(getParent());
		this.setTitle("Affichage de " + fc.getSelectedFile().getName());
		try {
			String ligne;
			FileReader flux;
			BufferedReader entree;
			flux = new FileReader(fc.getSelectedFile().getAbsolutePath());
			entree = new BufferedReader(flux);
			while ((ligne = entree.readLine()) != null) {
				fichier.add(ligne);
			}
			entree.close();
		} catch (Exception e) {
			System.err.println(e.toString());
			return false;
		}
		String first = fichier.get(0);
		int nbPoints = Integer.parseInt(first.substring(0,first.indexOf(' ')));
		int nbSegments = Integer.parseInt(first.substring(first.indexOf(' ')+1,first.indexOf(' ')+1+first.substring(first.indexOf(' ')+1).indexOf(' ')));
		int nbFaces = Integer.parseInt(first.substring(first.indexOf(' ')+1+first.substring(first.indexOf(' ')+1).indexOf(' ')+1));
		int parcoursDeLigne = 1;
		for (int i = parcoursDeLigne; i < parcoursDeLigne+nbPoints; i++) {
			list_points.add(new Point(Double.parseDouble(fichier.get(i).substring(0,fichier.get(i).indexOf(' '))), Double.parseDouble(fichier.get(i).substring(fichier.get(i).indexOf(' ')+1,fichier.get(i).indexOf(' ')+1+fichier.get(i).substring(fichier.get(i).indexOf(' ')+1).indexOf(' '))), Double.parseDouble(fichier.get(i).substring(fichier.get(i).indexOf(' ')+1+fichier.get(i).substring(fichier.get(i).indexOf(' ')+1).indexOf(' ')+1)),(i-parcoursDeLigne+1)));
		}
		parcoursDeLigne+=nbPoints;
		for (int i = parcoursDeLigne; i < parcoursDeLigne+nbSegments; i++) {
			list_segments.add(new Segment(Integer.parseInt(fichier.get(i).substring(0,fichier.get(i).indexOf(' '))), Integer.parseInt(fichier.get(i).substring(fichier.get(i).indexOf(' ')+1)),(i-parcoursDeLigne+1)));
		}
		parcoursDeLigne+=nbSegments;
		for (int i = parcoursDeLigne; i < parcoursDeLigne+nbFaces; i++) {
			list_faces.add(new Face(Integer.parseInt(fichier.get(i).substring(0,fichier.get(i).indexOf(' '))), Integer.parseInt(fichier.get(i).substring(fichier.get(i).indexOf(' ')+1,fichier.get(i).indexOf(' ')+1+fichier.get(i).substring(fichier.get(i).indexOf(' ')+1).indexOf(' '))), Integer.parseInt(fichier.get(i).substring(fichier.get(i).indexOf(' ')+1+fichier.get(i).substring(fichier.get(i).indexOf(' ')+1).indexOf(' ')+1)),(i-parcoursDeLigne+1)));
		}
		return true;
	}

	
	public AffichageDuModele() {
		if(!RecupDonneeFichier()){
			this.setTitle("ERROR");
			this.setSize(700, 500);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setLocationRelativeTo(null);
			this.setResizable(false);
			panel1.setBackground(Color.RED);
			Label error = new Label("ERROR");
			Font font = new Font("Arial",Font.BOLD,150);
			error.setFont(font);		
			panel1.add(error);
			this.add(panel1);
			this.setVisible(true);			
		}else{
			this.setSize(500, 500);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setLocationRelativeTo(null);
			this.setResizable(false);
			this.setBackground(Color.WHITE);
			/*panel1.setBackground(Color.RED);
			sX.addChangeListener(new SliderListener(decalageX));
			sZ.addChangeListener(new SliderListener(coeffZ));
			sXY.addChangeListener(new SliderListener(coeffXetY));
			sY.addChangeListener(new SliderListener(decalageY));
			panel1.add(sX);
			panel1.add(sZ);
			panel1.add(sXY);
			panel1.add(sY);
			this.add(panel1);*/
			this.setVisible(true);
		}
	}
	public void paint (Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0,3000, 3000);
		g.setColor(Color.BLACK);
		for (int i = 0; i < list_segments.size(); i++) {
			int x1 = (int) (list_points.get(list_segments.get(i).getP1()-1).getX()*t.getCoeffXetY() + list_points.get(list_segments.get(i).getP1()-1).getZ()*t.getCoeffZ1());
			int y1 = (int) (list_points.get(list_segments.get(i).getP1()-1).getY()*t.getCoeffXetY() + list_points.get(list_segments.get(i).getP1()-1).getZ()*t.getCoeffZ2());
			int x2 = (int) (list_points.get(list_segments.get(i).getP2()-1).getX()*t.getCoeffXetY() + list_points.get(list_segments.get(i).getP2()-1).getZ()*t.getCoeffZ1());
			int y2 = (int) (list_points.get(list_segments.get(i).getP2()-1).getY()*t.getCoeffXetY() + list_points.get(list_segments.get(i).getP2()-1).getZ()*t.getCoeffZ2());
			g.drawLine(t.getDecalageX()+x1, t.getDecalageY()+y1, t.getDecalageX()+x2, t.getDecalageY()+y2);
			//System.out.println("draw line : "+x1 + " "+y1 + " "+x2 + " "+y2 + " ");
		}
	}
	
	private class SliderListener implements ChangeListener{
		Integer value;
		public SliderListener(Integer value) {
			this.value = value;
		}
		@Override
		public void stateChanged(ChangeEvent e) {
			JSlider source = (JSlider)e.getSource();
			this.value =(int) source.getValue();
		}
		
	}
	public static void main(String[] args) {
		new AffichageDuModele();
	}
}
