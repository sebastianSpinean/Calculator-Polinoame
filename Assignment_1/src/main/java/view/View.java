package view;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class View extends JFrame {

	private JTextField polinom1Text = new JTextField(15);
	private JTextField polinom2Text = new JTextField(15);   //text field-urile pentru introducerea polinoamelor si pentru afisarea rezultatului si al restului impartirii
	private JTextField rezultat = new JTextField(15);
	private JTextField rest = new JTextField(15);

	private JButton adunare = new JButton("Adunare");
	private JButton scadere = new JButton("Scadere");
	private JButton inmultire = new JButton("Inmultire");           //instantierea butoanelor pentru fiecare operatie
	private JButton impartire = new JButton("Impartire");
	private JButton derivare = new JButton("Derivare");
	private JButton integrare = new JButton("Integrare");
	
	
	public View() {
		JPanel content = new JPanel();      //initializare panel principal
		content.setLayout(new BoxLayout(content,BoxLayout.Y_AXIS));       //setare mod de aranjare
		JPanel content1 = new JPanel();                //initializare panel secundar
		JPanel content2 = new JPanel();
		content1.setLayout(new FlowLayout());                //setare mod aranjare panel secundar
		content2.setLayout(new FlowLayout());
		content1.add(new JLabel("Primul polinom"));
		content1.add(polinom1Text);
		content1.add(new JLabel("Al doilea polinom"));
		content1.add(polinom2Text);
		content1.add(new JLabel("Rezultat"));
		content1.add(rezultat);                                //adaugare elemente pe cele doua penel-uri secundare
		content1.add(new JLabel("Rest"));
		content1.add(rest);
		content2.add(adunare);
		content2.add(scadere);
		content2.add(inmultire);
		content2.add(impartire);
		content2.add(derivare);
		content2.add(integrare);
		rezultat.setEditable(false);                     //text field-urile pentru rezultat nu sunt editabile
		rest.setEditable(false);
		content.add(content1);                      //adaugare panel-uri secundare pe panel-ul principal
		content.add(content2);
		this.setContentPane(content);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      //setare sa se inchida la apasarea lui x
		this.setVisible(true);                                  //setare sa fie visibil
		this.setTitle("Calculator polinoame");               //adaugare titlu
	}
	
	public String getPolinom1() {
		return polinom1Text.getText();
	}
	
	public void setPolinom1() {
		 polinom1Text.setText("");
	}
	
	public String getPolinom2() {
		return polinom2Text.getText();
	}
	
	public void setPolinom2() {
		 polinom2Text.setText("");
	}
	
	public void setRezultat(String rez) {
		rezultat.setText(rez);
		
	}
	
	public void setRest(String res) {
		rest.setText(res);
		
	}
	
	public void addAdunareListener(ActionListener a) {                   //adaugare ascultatori pentru butoane
		adunare.addActionListener(a);
	}
	
	public void addScadereListener(ActionListener a) {
		scadere.addActionListener(a);
	}
	
	public void addInmultireListener(ActionListener a) {
		inmultire.addActionListener(a);
	}
	
	public void addImpartireListener(ActionListener a) {
		impartire.addActionListener(a);
	}
	
	public void addDerivareListener(ActionListener a) {
		derivare.addActionListener(a);
	}
	
	public void addIntegrareListener(ActionListener a) {
		integrare.addActionListener(a);
	}
	
}
