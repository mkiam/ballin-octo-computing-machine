

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


@SuppressWarnings("serial")
public class Formulaire extends JFrame implements ActionListener{
	protected JFrame f=new JFrame();
	protected Container c=f.getContentPane();

	public  Formulaire(){

		c.setLayout(new BorderLayout());
		Image wall=f.getToolkit().getImage("formulaire.png/");
		Icon ic=new ImageIcon(wall);
		JLabel jl=new JLabel(ic);
		c.add(jl,BorderLayout.NORTH);
		JFrame f1=new JFrame();
		Container c1=f1.getContentPane();
		c1.setLayout(new GridLayout(4,1,5,2));
		Dimension d=new Dimension(150,50);
		JPanel pa1=new JPanel();
		JPanel pa2=new JPanel();
		JPanel pa3=new JPanel();


		JTextField b=new JTextField("Nombre de points");
		b.setPreferredSize(new Dimension(200,50));
		JTextField e=new JTextField("Nombre de segments");
		e.setPreferredSize(new Dimension(200,50));
	    JButton g=new JButton("Valider");
		g.setPreferredSize(d);

		pa1.add(b);
		pa2.add(e);
		pa3.add(g);	

		c1.add(pa1);
		c1.add(pa2);
		c1.add(pa3);

		c.add(c1,BorderLayout.CENTER);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(640,480);
		f.setResizable(false);
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}