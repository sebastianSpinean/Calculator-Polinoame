package Model;

public class Monom implements Comparable<Monom>{
	
		private int putere;                     // variabilele pentru putere si pentru coeficient
		private Number coeficient;
		
	
		public Monom(Number coeficient1, int putere) {
			this.coeficient=coeficient1;                         //constructor pentru initializarea variabilelor instanta
			this.putere=putere;
		}
		
		public Monom() {
			
		}

		
		
		public Number getCoeficient() {
		return coeficient;
	    }
		

		
		
		public void setCoeficient(Number coeficient) {
			this.coeficient = coeficient;
		}
		

		public int getPutere() {
			return putere;
		}

		public void setPutere(int putere) {
			this.putere = putere;
		}

		
		public int compareTo(Monom o) {                         //pentru ordonarea descrescatoare a monoamelor in functie de putere
			return o.getPutere()-this.putere;
			
		}
		
		public String toString() {
			String r="";
			
			
			if(coeficient.doubleValue()> 0 && putere!=0) {
				r=r+"+"+coeficient+"X^"+putere;                                //daca coeficientul este pozitiv si puterea diferita de 0 afisam un termen de forma +coefX^putere
			}
			else if(coeficient.doubleValue()<0 && putere!=0) r=r+coeficient+"X^"+putere;     // pentru numere negative
			else if(coeficient.doubleValue()>=0 && putere==0) r=r+"+"+coeficient;         //pentru termenii liberi pozitivi
			else if(coeficient.doubleValue()<0 && putere==0) r=r+coeficient;              //pentru termenii liberi negativi
			else r=r;
			return r;
		}
    
    
	

}
