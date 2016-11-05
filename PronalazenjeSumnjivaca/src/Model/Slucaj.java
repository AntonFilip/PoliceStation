package Model;

import java.util.HashSet;
import java.time.LocalTime;

public class Slucaj {

	int ID;
	String naziv;
	String opis;
	Osumnjiceni glavniOsumnjiceni;
	HashSet<Osumnjiceni> popisOsumnjicenih = new HashSet<Osumnjiceni>();
	HashSet<Osoba> popisSvjedoka = new HashSet<Osoba>();
	HashSet<String> popisAdresa = new HashSet<String>();
	HashSet<LocalTime> popisVremena = new HashSet<LocalTime>();
	HashSet<Dokaz> popisDokaza = new HashSet<Dokaz>();
	HashSet<Policajac> popisPolicajaca = new HashSet<Policajac>();
	TrenutniStatusSlucaja status;
}
