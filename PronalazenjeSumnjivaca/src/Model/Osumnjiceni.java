package Model;

import java.util.HashSet;

public class Osumnjiceni extends Osoba{
	
	HashSet<String> popisAliasa = new HashSet<String>();
	String adresa;
	HashSet<String> poznateAdrese = new HashSet<String>();
	String brojTelefona;
	HashSet<Integer> povezaniSlucajevi = new HashSet<Integer>();
	FizickeOsobine fizickeOsobine;
	KarakterneOsobine karakterneOsobine;
	String opisKriminalnihDjelatnosti;
	HashSet<Osumnjiceni> popisPovezanihKriminalaca = new HashSet<Osumnjiceni>();
	TrenutniStatusKriminalca status;

}
