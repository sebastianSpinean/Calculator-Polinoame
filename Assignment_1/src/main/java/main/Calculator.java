package main;
import view.View;
import controller.Controller;

public class Calculator {

	public static void main(String[] args) {
		View view = new View();                           //instantiere view
		Controller controller = new Controller(view);       //instantiere controller

	}

}
