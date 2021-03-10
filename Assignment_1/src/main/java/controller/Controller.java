package controller;

import view.View;
import Model.Monom;
import Model.Polinom;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;



public class Controller {

	private View view;                      //variabila de tip view
	public Controller(View view) {
		this.view=view;
		
		view.addAdunareListener(new AdunareListener());
		view.addScadereListener(new ScadereListener());                //adaugare ascultatori la view
		view.addInmultireListener(new InmultireListener());
		view.addImpartireListener(new ImpartireListener());
		view.addDerivareListener(new DerivareListener());
		view.addIntegrareListener(new IntegrareListener());
	}
	
	
	public boolean validare(String polinom) { //metoda pentru validare polinom
		
	
		String VALIDARE_PATTERN="(([+-][1-9]([0-9])*X\\^[1-9]([0-9])*)|([+-]*[0]|[+-][1-9]([0-9])*))+";  //expresie regulata pentru validare
		Pattern patternVal = Pattern.compile(VALIDARE_PATTERN);
		Matcher matcherVal1 = patternVal.matcher(polinom);
		if(matcherVal1.matches()) {         //daca este valid se returneaza true altfel false
			return true;
		}
		return false;
	}
	
	public Polinom crearePolinom(String polinom) {         //metoda pentru extragere coeficienti si puteri
		String POL_PATTERN="((([+-])([1-9][0-9]*))X\\^([1-9][0-9]*))";  //regex pentru termenii ce contin si x
		String Liber_PATTERN="([+-][1-9][0-9]*X\\^[1-9][0-9]*)|([+-][1-9][0-9]*)";  // regex pentru termenii liberi
		Pattern pattern1 = Pattern.compile(POL_PATTERN);
		Matcher ma = pattern1.matcher(polinom);
		Polinom p1 = new Polinom();
		while(ma.find()) {	
				int coef = Integer.parseInt(ma.group(2));    //extragere coeficient termen cu x
				int putere = Integer.parseInt(ma.group(5));   //extragere putere termen cu x
				Monom m = new Monom(coef,putere);
				p1.addMonom(m);	
		}
		Pattern pattern2 = Pattern.compile(Liber_PATTERN);
		Matcher ma2 = pattern2.matcher(polinom);
		while(ma2.find()) {
			if(ma2.group(1)==null && ma2.group(2)!=null) {
				int coef = Integer.parseInt(ma2.group(2));   //extragere coeficient pentru termenii liberi
				Monom m = new Monom(coef,0);
				p1.addMonom(m);
			}
		}
		return p1;
	}
	
	
	class AdunareListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String polinom1="";
			String polinom2="";
			view.setRezultat("");               //sterge ce era inainte afisat ca rezultat
			view.setRest("");
			Polinom p1 = new Polinom();
			Polinom p2 = new Polinom();
			Polinom sum = new Polinom();
			try {
				polinom1=view.getPolinom1();          //returneaza datele introduse de utilizator
				
				polinom2=view.getPolinom2();
				if(validare(polinom1) && validare(polinom2) ) {  //verificare validitate date
				    p1 = crearePolinom(polinom1);
				    p2 = crearePolinom(polinom2);
				    sum=p1.adunare(p2);           //efectuarea operatiei
					view.setRezultat(sum.toString());       //afisare rezultat
				}
				else if(!validare(polinom1) && !validare(polinom2) ) {
					 JOptionPane.showMessageDialog(view, "polinoamele nu sunt valide");  //mesaj de eroare
				}
				else if(!validare(polinom2)) {
					JOptionPane.showMessageDialog(view, "al doilea polinom nu este valid");	 //mesaj de eroare
				}  
				else if(!validare(polinom1)) {
					 JOptionPane.showMessageDialog(view, "primul polinom nu este valid"); //mesaj de eroare
				}
				
			}
			catch(Exception ex) {	
			}
		}
	}
	
	
	
	class ScadereListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String polinom1="";
			String polinom2="";
			view.setRezultat("");       //sterge ce era inainte afisat ca rezultat
			view.setRest("");
			Polinom p1 = new Polinom();
			Polinom p2 = new Polinom();
			Polinom dif = new Polinom();
			try {
				polinom1=view.getPolinom1();                  //returneaza datele introduse de utilizator
				polinom2=view.getPolinom2();
				if(validare(polinom1) && validare(polinom2) ) { //verificare validitate date
				    p1 = crearePolinom(polinom1);
				    p2 = crearePolinom(polinom2);
				    dif=p1.scadere(p2);                       //efectuarea operatiei
					Polinom rez = new Polinom();
					for(Monom i: dif.getPolinom()) {
						Monom m = new Monom();
						m.setCoeficient(i.getCoeficient().intValue());   //setare coeficient intregi
						m.setPutere(i.getPutere());
						rez.addMonom(m);
					}
					view.setRezultat(rez.toString());	 //afisare rezultat
				}
				else if(!validare(polinom1) && !validare(polinom2) ) {
					 JOptionPane.showMessageDialog(view, "polinoamele nu sunt valide");   //mesaj de eroare
				}
				else if(!validare(polinom2)) {
					JOptionPane.showMessageDialog(view, "al doilea polinom nu este valid");	 //mesaj de eroare
				}
				else if(!validare(polinom1)) {
					 JOptionPane.showMessageDialog(view, "primul polinom nu este valid");  //mesaj de eroare
				}
				
			}
			catch(Exception ex) {	
			}
		}
	}
	
	
	class InmultireListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String polinom1="";
			String polinom2="";
			view.setRezultat("");
			view.setRest("");                      //sterge ce era inainte afisat ca rezultat
			Polinom p1 = new Polinom();
			Polinom p2 = new Polinom();
			Polinom dif = new Polinom();
			try {
				polinom1=view.getPolinom1();                     //returneaza datele introduse de utilizator
				polinom2=view.getPolinom2();
				if(validare(polinom1) && validare(polinom2) ) { //verificare validitate date
				    p1 = crearePolinom(polinom1);
				    p2 = crearePolinom(polinom2);
				    dif=p1.inmultire(p2);                         //efectuarea operatiei
					Polinom rez = new Polinom();
					for(Monom i: dif.getPolinom()) {
						Monom m = new Monom();
						m.setCoeficient(i.getCoeficient().intValue());  //setare coeficienti intregi
						m.setPutere(i.getPutere());
						rez.addMonom(m);
					}
					view.setRezultat(rez.toString());	 //afisare rezultat
				}
				else if(!validare(polinom1) && !validare(polinom2) ) {
					 JOptionPane.showMessageDialog(view, "polinoamele nu sunt valide");  //mesaj de eroare
				}
				else if(!validare(polinom2)) {
					JOptionPane.showMessageDialog(view, "al doilea polinom nu este valid");	 //mesaj de eroare
				}
				else if(!validare(polinom1)) {
					 JOptionPane.showMessageDialog(view, "primul polinom nu este valid");  //mesaj de eroare
				}
		
			}
			catch(Exception ex) {	
			}
		}
	}
	
	
	class ImpartireListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String polinom1="";
			String polinom2="";
			view.setRezultat("");
			view.setRest("");                  //sterge ce era inainte afisat ca rezultat
			Polinom p1 = new Polinom();
			Polinom p2 = new Polinom();
			ArrayList<Polinom> dif;
			try {
				polinom1=view.getPolinom1();            //returneaza datele introduse de utilizator
				polinom2=view.getPolinom2();
				if(polinom2.equals("0") || polinom2.equals("+0") || polinom2.equals("-0")) { //mesaj de eroare daca se doreste impartirea cu 0
					JOptionPane.showMessageDialog(view, "Impartirea cu 0 nu este permisa");	
				}
				else {
					
				if((polinom1.equals("0") || polinom1.equals("+0") || polinom1.equals("-0")) && validare(polinom2)) { //se afiseaza 0 daca primul polinom este 0
					view.setRezultat("0");
				}
				else if(validare(polinom1) && validare(polinom2) ) { //verificare validitate date
				    p1 = crearePolinom(polinom1);
				    p2 = crearePolinom(polinom2);
				    dif=p1.impartire(p2);                          //efectuarea operatiei
					view.setRezultat(dif.get(0).toString());           //afisare rezultat
					view.setRest(dif.get(1).toString());      //afisare rest
				}
				else if(!validare(polinom1) && !validare(polinom2) ) {
					 JOptionPane.showMessageDialog(view, "polinoamele nu sunt valide");  //mesaj de eroare
				}
				else if(!validare(polinom2)) {
					JOptionPane.showMessageDialog(view, "al doilea polinom nu este valid");	//mesaj de eroare
				}
				else if(!validare(polinom1)) {
					 JOptionPane.showMessageDialog(view, "primul polinom nu este valid");  //mesaj de eroare
				}
			
				}
			}
			catch(Exception ex) {	
				
			}
		}
	}
	
	
	class DerivareListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String polinom1="";
			view.setRezultat("");
			view.setRest("");                          //sterge ce era inainte afisat ca rezultat
			view.setPolinom2();
			Polinom p1 = new Polinom();
			Polinom p2 = new Polinom();
			try {
				polinom1=view.getPolinom1();             //returneaza datele introduse de utilizator
				if(validare(polinom1) ) {               //verificare validitate date
				    p1 = crearePolinom(polinom1);
				    
				    p2=p1.derivare();                               //efectuarea operatiei
					view.setRezultat(p2.toString());           //afisare rezultat
				}
				
				else if(!validare(polinom1)) {
					 JOptionPane.showMessageDialog(view, "primul polinom nu este valid");  //mesaj de eroare
				}
				
			}
			catch(Exception ex) {	
			}
		}
	}
	
	class IntegrareListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String polinom1="";
			view.setRezultat("");
			view.setRest("");                          //sterge ce era inainte afisat ca rezultat
			view.setPolinom2();
			Polinom p1 = new Polinom();
			Polinom p2 = new Polinom();
			try {
				polinom1=view.getPolinom1(); //returneaza datele introduse de utilizator
				
				if((polinom1.equals("0") || polinom1.equals("+0") || polinom1.equals("-0"))) { //se afiseaza 0 daca primul polinom este 0
					view.setRezultat("0");
				}
				else if(validare(polinom1) ) {                 //verificare validitate date
				    p1 = crearePolinom(polinom1);
				    
				    p2=p1.integrare();                           //efectuarea operatiei
					view.setRezultat(p2.toString());               //afisare rezultat
				}
				
				else if(!validare(polinom1)) {
					 JOptionPane.showMessageDialog(view, "primul polinom nu este valid");  //mesaj de eroare
				}
				
			}
			catch(Exception ex) {	
			}
		}
	}
}
