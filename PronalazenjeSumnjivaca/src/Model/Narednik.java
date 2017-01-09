package Model;
import java.util.Set;

import Controller.RazinaPristupa;

public class Narednik extends Pozornik {
	public Narednik() {
		razinaPristupa = RazinaPristupa.SREDNJA;
	}

	public Narednik(String ime, String prezime,Integer jedinstveniBrojPolicajca) {
		this.ime = ime;
		this.prezime = prezime;
		super.setJedinstveniBroj(jedinstveniBrojPolicajca);
		razinaPristupa = RazinaPristupa.SREDNJA;

	}
	
	public static boolean dodajNoviDokaz(Dokaz dokaz) {
		PristupBaziPodataka.dodajNoviDokaz(dokaz);
		return true;	
	}
	
	public static boolean izmjeni(Dokaz trenutni,Dokaz izmjenjeni,Set<String> dodaniAtributi,Set<String> izbrisani){
		Context<Dokaz> context=new Context<>(trenutni);
		return context.izmjeni(izmjenjeni, dodaniAtributi, izbrisani);
	}
	
	public static boolean izmjeni(Slucaj trenutni,Slucaj izmjenjeni,Set<String>dodaniAtributi,Set<String> izbrisani){
		Context<Slucaj> context=new Context<>(trenutni);
		return context.izmjeni(izmjenjeni, dodaniAtributi, izbrisani);
	}
	
	public static boolean izmjeni(Osumnjiceni trenutni,Osumnjiceni izmjenjeni,Set<String> dodaniAtributi,Set<String> izbrisani){
		Context<Osumnjiceni> context=new Context<>(trenutni);
		return context.izmjeni(izmjenjeni, dodaniAtributi, izbrisani);
	}

    @Override
    public String toString() {
        return "Narednik " + ime + " " + prezime + " (" + super.getOib() + ")";
    }

}
