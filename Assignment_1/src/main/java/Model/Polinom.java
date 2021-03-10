package Model;

import java.util.*;

public class Polinom {

	private ArrayList<Monom> polinom = new ArrayList<Monom>();     //lista de monoame

	public Polinom() {

	}

	public Polinom(int rang) {
		int i=0;
		while(i<=rang) {
			Monom m = new Monom();               // constructor care construieste un polinom de un anumit rang si avand toti coeficientii 0        
			m.setCoeficient(0);
			m.setPutere(i);
			this.polinom.add(m);
			i++;
		}

	}

	public void addMonom(Monom a) {
		polinom.add(a);                             //functie pentru adaugarea unui monom in lista

	}
	
	public void stergere() {                        //stergerea primului element din lista
		polinom.remove(0);
	}

	public ArrayList<Monom> getPolinom() {
		return polinom;
	}

	public void setPolinom(ArrayList<Monom> polinom) {
		this.polinom = polinom;
	}

	public int rang() {
		int rang = 0;
		for (Monom i : polinom) {
			if (i.getPutere() >= rang) {                     //  functie pentru determinarea rangului unui polinom
				rang = i.getPutere();
			}
		}
		return rang;

	}

	public Polinom completareCuZero() {                              // functie pentu completarea unui polinom cu termenii lipsa avand coeficientii 0
		int rang = this.rang();                                      //determinam rangul polinomului
		Polinom pol = new Polinom(rang);                             // initializam un polinom de acelasi rang cu toti termenii 0
		for (Monom i : this.getPolinom()) {
			for (Monom j : pol.getPolinom()) {                       //pentru puterile egale schimbam coeficientul din 0 in valoarea coeficientului polinomului this  
				if (i.getPutere() == j.getPutere()) {
					j.setCoeficient(i.getCoeficient());

				}
			}
		}
		return pol;                             //returnam polinomul completat

	}
	
	
		
	
	

	public Polinom adunare(Polinom p) {

		
		Polinom rez = new Polinom();
		
		if(this.rang()>p.rang()) {
				p.addMonom(new Monom(0,this.rang()));
			}                                                                //facem ca ambii polinomi sa aiba acelasi grad
			else if(this.rang()<p.rang()) {		
					this.addMonom(new Monom(0,p.rang()));
		}
		
		Polinom pol1 = this.completareCuZero();                  //completam cei doi polinomi cu 0
		Polinom pol2 = p.completareCuZero();

		for (Monom i : pol1.getPolinom()) {
			for (Monom j : pol2.getPolinom()) {
				if (i.getPutere() == j.getPutere()) {
					Monom rez_monom = new Monom();
					rez_monom.setCoeficient(i.getCoeficient().intValue() + j.getCoeficient().intValue());              //pentru monoamele cu putere egala adunam coeficientii intr-un nou monom pe care il adaugam polinomului rez
					rez_monom.setPutere(i.getPutere());
					rez.addMonom(rez_monom);
				}
			}
		}
		Collections.sort(rez.getPolinom());              //sortam polinoml
		
		Polinom rez2 = new Polinom();
		for(Monom i : rez.getPolinom()) {                    //eliminam termenii care au coeficientul 0 
			if(i.getCoeficient().intValue()!=0) {
				rez2.addMonom(i);
			}
		}
		if(rez2.getPolinom().isEmpty())                    //in caz ca toti termenii s-au eliminat mai adaugam termenul liber 0
			rez2.addMonom(new Monom(0,0));

		return rez2;                       //returnam rezultatul

	}

	public Polinom scadere(Polinom p) {

		
		Polinom rez = new Polinom();
		
		
		if(this.rang()>p.rang()) {
				p.addMonom( new Monom(0,this.rang()));
			}                                                                  //facem ca ambii polinomi sa aiba acelasi grad
			else if(this.rang()<p.rang()) {
					this.addMonom(new Monom(0,p.rang()));	
		}

		Polinom pol1 = this.completareCuZero();                             //completam cei doi polinomi cu 0
		Polinom pol2 = p.completareCuZero();

		for (Monom i : pol1.getPolinom()) {
			for (Monom j : pol2.getPolinom()) {
				if (i.getPutere() == j.getPutere()) {                      //pentru monoamele cu putere egala scadem coeficientii intr-un nou monom pe care il adaugam polinomului rez
					Monom rez_monom = new Monom();
					rez_monom.setCoeficient(i.getCoeficient().doubleValue() - j.getCoeficient().doubleValue());
					rez_monom.setPutere(i.getPutere());
					rez.addMonom(rez_monom);
				}
			}
		}
		Collections.sort(rez.getPolinom());                //sortam polinoml

		Polinom rez2 = new Polinom();
		for(Monom i : rez.getPolinom()) {                         //eliminam termenii care au coeficientul 
			if(i.getCoeficient().doubleValue()!=0) {
				rez2.addMonom(i);
			}
		}
		
		if(rez2.getPolinom().isEmpty())                        //in caz ca toti termenii s-au eliminat mai adaugam termenul liber 0
			rez2.addMonom(new Monom(0,0));
	
		return rez2;             //returnam rezultatul
		 
	
	}

	public Polinom inmultire(Polinom p) {
		
		Polinom rez = new Polinom();
	

		Polinom pol1 = this;             // atribuim cei doi polinomi pe care vrem sa-i inmultim polinoamelor pol1 si pol2
		Polinom pol2 = p;

		for (Monom i : pol1.getPolinom()) {
			for (Monom j : pol2.getPolinom()) {
				Monom rez_monom = new Monom();
				rez_monom.setCoeficient(i.getCoeficient().doubleValue() * j.getCoeficient().doubleValue()); //inmultim termen cu termen obtinand un nou polinom,rez
				rez_monom.setPutere(i.getPutere() + j.getPutere());
				rez.addMonom(rez_monom);
			}
		}

		Monom x;
		Polinom pol_rez = new Polinom(pol1.rang() + pol2.rang());           //initializam un nou polinom,pol_rez avand gradul egal cu suma gradelor lui pol1 si pol2 si toti termenii 0
		for (Monom i : rez.getPolinom()) {                              //parcurgem polinomul rez
		
			x = pol_rez.getPolinom().get(i.getPutere());                 //alegem termenul din pol_rez care are puterea egala cu puterea lui i            
			x.setCoeficient(x.getCoeficient().doubleValue() + i.getCoeficient().doubleValue());  // adunam la coeficientul lui x coeficientul lui i, adunam astfel termenii asemenea
		}
		Collections.sort(pol_rez.getPolinom());                //sortam polinomul
		Polinom rez2 = new Polinom();
		for(Monom i : pol_rez.getPolinom()) {
			if(i.getCoeficient().doubleValue()!=0) {               //eliminam termenii cu coeficientul 0
				rez2.addMonom(i);
			}
		}
		if(rez2.getPolinom().isEmpty())                       //in caz ca toti termenii s-au eliminat mai adaugam termenul liber 0
			rez2.addMonom(new Monom(0,0));
		return rez2;                             //returnam rezultatul
		

	}
	
	public ArrayList<Polinom> impartire(Polinom p) {
		
	
		ArrayList<Polinom> polinoame = new ArrayList<Polinom>();         //initializam lista de polinome
	
		Collections.sort(this.getPolinom());              //sortam cei doi polinomi
     	Collections.sort(p.getPolinom());
		Polinom cat = new Polinom();                //catul impartirii
		Polinom deimp = this;                       //deimpartitul
		Polinom imp = p;                             //impartitorul
		Polinom a = new Polinom();                   //polinom auxiliar
	
			while(deimp.rang()>=imp.rang() && deimp.getPolinom().get(0).getCoeficient().doubleValue()!=0) {  //cat timp gradul deimpartitului este mai mare sau egal cu cel al impartitorului si deimpartitul este diferit de 0
		    Monom catMonom = new Monom();
			Monom deimpMonom = new Monom();
			Monom r = new Monom();
			deimpMonom = deimp.getPolinom().get(0);       //extragem primul monom din deimpartit
			catMonom = imp.getPolinom().get(0);            //extragem primul monom din impartitor
			r.setPutere(deimpMonom.getPutere()-catMonom.getPutere());
			r.setCoeficient(deimpMonom.getCoeficient().doubleValue()/catMonom.getCoeficient().doubleValue());   //in monomul r salvam monomul obtinut in urma impartirii monoamelor deimpMonom si impMonom
			cat.addMonom(r);              //adaugam monomul r catului
			a.addMonom(r);               //adaugam monomul r polinomului auxiliar
			
			Polinom rest = new Polinom();
			rest = imp.inmultire(a);           //in rest avem inmultirea impartitorului cu polinomul auxiliar
			a.stergere();                      //stergem monomul din polinomul auxiliar
			
			deimp = deimp.scadere(rest);         //deimpartitorul este egal cu diferenta dintre el insusi si rezultatul inmultirii de mai sus
			Collections.sort(deimp.getPolinom());          //sortam deimpartitul
			
			
		}
		if(cat.getPolinom().isEmpty())            //daca catul este gol adaugam termenul liber 0
			cat.addMonom(new Monom(0,0));
		Collections.sort(deimp.getPolinom());
		Collections.sort(cat.getPolinom());
		polinoame.add(cat);                    //adaugam catul in lista de polinoame
		polinoame.add(deimp);                  //acum deimpartitorul a devenit rest si il adaugam
	 
		return polinoame;             //returnam catul si restul
		 
		
	}
	
	
	public Polinom derivare() {
		Polinom rez = new Polinom();
		for(Monom i : polinom) {
			if(i.getPutere()!=0) {
				i.setCoeficient(i.getCoeficient().intValue()*i.getPutere());           //parcurgem tot polinomul si pentru termenii care au puterea diferita de 0 modificamcoeficientul si puterea
				i.setPutere(i.getPutere()-1);                                          //termenii care au puterea 0 nu se mai adauga  
				rez.addMonom(i);                                                        
			}
			
		}
		Collections.sort(rez.getPolinom());
	
		if(rez.getPolinom().isEmpty())
			rez.addMonom(new Monom(0,0));                   //daca polinomul rezultat este gol adaugam 0
		return rez;
	}
	
	public Polinom integrare() {
		Polinom rez = new Polinom();
		for(Monom i: polinom) {
			i.setCoeficient(i.getCoeficient().doubleValue()/(i.getPutere()+1)); //parcurgem tot polinomul si pentru fiecare termen modificam puterea si coeficientul
			i.setPutere(i.getPutere()+1);
			
			rez.addMonom(i);
		}
		Collections.sort(rez.getPolinom());
		return rez;
	}
	
	public String toString() {
		String r="";
		Collections.sort(polinom);
		for(Monom i : polinom) {
		    r=r+i.toString();
		}
		return r;
		
		
	}
	
	
	
	
	
}
