package Model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class KarakterneOsobine {
	private String nacinGovora;
	private RazinaApstraktneInteligencije razinaApstraktneInteligencije;
	private Set <String> psiholoskiProblemi=new HashSet<>();
	private Set <String> ostaleKarakterneOsobine=new HashSet<>();
	
	
	public KarakterneOsobine() {
		super();
	}
	
	
	public KarakterneOsobine(String nacinGovora, RazinaApstraktneInteligencije razinaApstraktneInteligencije,
			Set<String> psiholoskiProblemi, Set<String> ostaleKarakterneOsobine) {
		super();
		this.nacinGovora = nacinGovora;
		this.razinaApstraktneInteligencije = razinaApstraktneInteligencije;
		this.psiholoskiProblemi = psiholoskiProblemi;
		this.ostaleKarakterneOsobine = ostaleKarakterneOsobine;
	}


	public String getNacinGovora() {
		return nacinGovora;
	}
	public void setNacinGovora(String nacinGovora) {
		this.nacinGovora = nacinGovora;
	}
	public RazinaApstraktneInteligencije getRazinaApstraktneInteligencije() {
		return razinaApstraktneInteligencije;
	}
	public void setRazinaApstraktneInteligencije(RazinaApstraktneInteligencije razinaApstraktneInteligencije) {
		this.razinaApstraktneInteligencije = razinaApstraktneInteligencije;
	}
	public Set<String> getPsiholoskiProblemi() {
		return psiholoskiProblemi;
	}
	public void setPsiholoskiProblemi(Set<String> psiholoskiProblemi) {
		this.psiholoskiProblemi = psiholoskiProblemi;
	}
	public boolean addPsiholoskiProblem(String psihProblem){
		return psiholoskiProblemi.add(psihProblem);
	}
	public boolean addAllPsiholoskiProblem(Collection<String> psihProblem){
		return psiholoskiProblemi.addAll(psihProblem);
	}
	
	public Set<String> getOstaleKarakterneOsobine() {
		return ostaleKarakterneOsobine;
	}
	public void setOstaleKarakterneOsobine(Set<String> ostaleKarakterneOsobine) {
		this.ostaleKarakterneOsobine = ostaleKarakterneOsobine;
	}
	
	public boolean addOstalo(String ostalo){
		return ostaleKarakterneOsobine.add(ostalo);
	}
	
	public boolean addAllOstalo(Collection<String> ostalo){
		return ostaleKarakterneOsobine.addAll(ostalo);
	}
   

}
