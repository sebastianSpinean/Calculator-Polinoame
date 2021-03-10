package PT2020.Assignment_1;

import Model.Polinom;
import Model.Monom;
import org.junit.*;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;


public class CalculatorTest {

	@Test
	public void adunareTest() {
		Polinom p1 = new Polinom();
		Polinom p2 = new Polinom();
		p1.addMonom(new Monom(2,3));
		p1.addMonom(new Monom(1,1));
		p2.addMonom(new Monom(3,3));
		p2.addMonom(new Monom(-4,2));
		p2.addMonom(new Monom(5,0));
		String suma = "+5X^3-4X^2+1X^1+5";
		
		assertEquals(suma, p1.adunare(p2).toString());
	}
	
	@Test
	public void scadereTest() {
		Polinom p1 = new Polinom();
		Polinom p2 = new Polinom();
		p1.addMonom(new Monom(4,6));
		p1.addMonom(new Monom(-2,4));
		p1.addMonom(new Monom(8,0));
		p2.addMonom(new Monom(3,6));
		p2.addMonom(new Monom(-3,4));
		p2.addMonom(new Monom(5,0));
		String diferenta = "+1.0X^6+1.0X^4+3.0";
		assertEquals(diferenta, p1.scadere(p2).toString());
		
	}
	
	@Test
	public void inmultireTest() {
		Polinom p1 = new Polinom();
		Polinom p2 = new Polinom();
		p1.addMonom(new Monom(1,2));
		p1.addMonom(new Monom(1,1));
		p2.addMonom(new Monom(3,3));
		p2.addMonom(new Monom(5,2));
		String rezultat = "+3.0X^5+8.0X^4+5.0X^3";
		assertEquals(rezultat, p1.inmultire(p2).toString());
	}
	
	@Test
	public void impartireCatTest() {
		Polinom p1 = new Polinom();
		Polinom p2 = new Polinom();
		p1.addMonom(new Monom(4,4));
		p1.addMonom(new Monom(2,2));
		p2.addMonom(new Monom(2,1));
		String rezultat = "+2.0X^3+1.0X^1";
		ArrayList<Polinom> polinoame;
		polinoame=p1.impartire(p2);
		assertEquals(rezultat, polinoame.get(0).toString());
	}
	
	@Test
	public void impartireRestTest() {
		Polinom p1 = new Polinom();
		Polinom p2 = new Polinom();
		p1.addMonom(new Monom(4,4));
		p1.addMonom(new Monom(2,0));
		p2.addMonom(new Monom(2,5));
		String rezultat = "+4X^4+2";
		ArrayList<Polinom> polinoame;
		polinoame=p1.impartire(p2);
		assertEquals(rezultat, polinoame.get(1).toString());
	}
	
	@Test
	public void derivareTest() {
		Polinom p1 = new Polinom();
		p1.addMonom(new Monom(3,5));
		p1.addMonom(new Monom(2,3));
		p1.addMonom(new Monom(1,2));
		p1.addMonom(new Monom(5,0));
		String rezultat = "+15X^4+6X^2+2X^1";
		assertEquals(rezultat, p1.derivare().toString());
		
	}
	
	@Test
	public void integrareTest() {
		Polinom p1 = new Polinom();
		p1.addMonom(new Monom(10,4));
		p1.addMonom(new Monom(9,2));
		p1.addMonom(new Monom(5,0));
		String rezultat = "+2.0X^5+3.0X^3+5.0X^1";
		assertEquals(rezultat, p1.integrare().toString());
	}
	
	
}
