package Controller;

import Model.*;
import View.*;

public class Controller implements ViewDelegate {
	
	MainWindow mW = new MainWindow();
	Osoba person = null;
	PristupBaziPodataka db = null;
	
	Slucaj selectedCase = null;
	Dokaz evidance = null;
	Osumnjiceni suspect = null;
	
	public static void main(String[] args) {
		
	}

}
