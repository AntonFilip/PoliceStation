package Model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import com.mysql.jdbc.StringUtils;

public class Osumnjiceni extends Osoba implements StrategijaUpit<Osumnjiceni> {

	private TrenutniStatusKriminalca status;
	private String brojTelefona;
	private String opisKriminalnihDjelatnosti;
	private FizickeOsobine fizickeOsobine;
	private KarakterneOsobine karakterneOsobine;
	private String otisakPrstaURL;
	private Set<String> fotografijeURL=new LinkedHashSet<>();
	private Set<String> popisAliasa=new LinkedHashSet<>();
	private Set<AdresaIMjestoStanovanja> poznateAdrese=new LinkedHashSet<>();
	private Set<Osumnjiceni> popisPovezanihKriminalaca=new LinkedHashSet<>();
	private Set<Slucaj> povezaniSlucajevi=new LinkedHashSet<>();
	private LocalDate datumRodjenja;

	public Osumnjiceni() {
	}

	public Osumnjiceni(TrenutniStatusKriminalca status, String brojTelefona, String opisKriminalnihDjelatnosti,
			FizickeOsobine fizickeOsobine, KarakterneOsobine karakterneOsobine, String otisakPrstaURL,
			Set<String> fotografijeURL, Set<String> popisAliasa, Set<AdresaIMjestoStanovanja> poznateAdrese,
			Set<Osumnjiceni> popisPovezanihKriminalaca, Set<Slucaj> povezaniSlucajevi,LocalDate datumRodjenja) {
		super();
		this.status = status;
		this.brojTelefona = brojTelefona;
		this.opisKriminalnihDjelatnosti = opisKriminalnihDjelatnosti;
		this.fizickeOsobine = fizickeOsobine;
		this.karakterneOsobine = karakterneOsobine;
		this.otisakPrstaURL = otisakPrstaURL;
		this.fotografijeURL = fotografijeURL;
		this.popisAliasa = popisAliasa;
		this.poznateAdrese = poznateAdrese;
		this.popisPovezanihKriminalaca = popisPovezanihKriminalaca;
		this.povezaniSlucajevi = povezaniSlucajevi;
		this.datumRodjenja=datumRodjenja;
	}

	public TrenutniStatusKriminalca getStatus() {
		return status;
	}

	public LocalDate getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(LocalDate datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public void setStatus(TrenutniStatusKriminalca status) {
		this.status = status;
	}

	public String getBrojTelefona() {
		return brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	public String getOpisKriminalnihDjelatnosti() {
		return opisKriminalnihDjelatnosti;
	}

	public void setOpisKriminalnihDjelatnosti(String opisKriminalnihDjelatnosti) {
		this.opisKriminalnihDjelatnosti = opisKriminalnihDjelatnosti;
	}

	public FizickeOsobine getFizickeOsobine() {
		return fizickeOsobine;
	}

	public void setFizickeOsobine(FizickeOsobine fizickeOsobine) {
		this.fizickeOsobine = fizickeOsobine;
	}

	public KarakterneOsobine getKarakterneOsobine() {
		return karakterneOsobine;
	}

	public void setKarakterneOsobine(KarakterneOsobine karakterneOsobine) {
		this.karakterneOsobine = karakterneOsobine;
	}

	public String getOtisakPrstaURL() {
		return otisakPrstaURL;
	}

	public void setOtisakPrstaURL(String otisakPrstaURL) {
		this.otisakPrstaURL = otisakPrstaURL;
	}

	public Set<String> getFotografijeURL() {
		return fotografijeURL;
	}

	public void setFotografijeURL(Set<String> fotografijeURL) {
		this.fotografijeURL = fotografijeURL;
	}

	public boolean addFotografijaURL(String foto){
		return fotografijeURL.add(foto);
	}

	public boolean addAllFotografijaURL(Collection<String> foto){
		return fotografijeURL.addAll(foto);
	}

	public Set<String> getPopisAliasa() {
		return popisAliasa;
	}

	public void setPopisAliasa(Set<String> popisAliasa) {
		this.popisAliasa = popisAliasa;
	}

	public boolean addAlias(String alias){
		return popisAliasa.add(alias);
	}

	public boolean addAllAlias(Collection<String> alias){
		return popisAliasa.addAll(alias);
	}

	public Set<AdresaIMjestoStanovanja> getPoznateAdrese() {
		return poznateAdrese;
	}

	public void setPoznateAdrese(Set<AdresaIMjestoStanovanja> poznateAdrese) {
		this.poznateAdrese = poznateAdrese;
	}

	public boolean addPoznataAdresa(AdresaIMjestoStanovanja adr){
		return poznateAdrese.add(adr);
	}

	public boolean addAllPoznateAdrese(Collection<AdresaIMjestoStanovanja> adr){
		return poznateAdrese.addAll(adr);
	}

	public Set<Osumnjiceni> getPopisPovezanihKriminalaca() {
		return popisPovezanihKriminalaca;
	}

	public void setPopisPovezanihKriminalaca(Set<Osumnjiceni> popisPovezanihKriminalaca) {
		this.popisPovezanihKriminalaca = popisPovezanihKriminalaca;
	}

	public boolean addPovezanKriminalac(Osumnjiceni kriminalac){
		return this.popisPovezanihKriminalaca.add(kriminalac);
	}

	public boolean addAllPovezanKriminalac(Collection<Osumnjiceni> kriminalac){
		return this.popisPovezanihKriminalaca.addAll(kriminalac);
	}

	public Set<Slucaj> getPovezaniSlucajevi() {
		return povezaniSlucajevi;
	}

	public void setPovezaniSlucajevi(Set<Slucaj> povezaniSlucajevi) {
		this.povezaniSlucajevi = povezaniSlucajevi;
	}

	public boolean addPovezanSlucaj(Slucaj slucaj){
		return povezaniSlucajevi.add(slucaj);
	}

	public boolean addAllPovezanSlucaj(Collection<Slucaj> slucaj){
		return povezaniSlucajevi.addAll(slucaj);
	}

	@Override
	public String generirajTextualniOpis(Set<String> listaAtributa) {
		StringBuilder sbBuilder=new StringBuilder();
		sbBuilder.append("Traži se kriminalac sa sljedećim obilježjima: ");

		for (String string: listaAtributa){
			String [] parts=string.split("\\*");
			String obiljezje=parts[1];
			String vrijednost=parts[0];
			switch (obiljezje){

			case "Kriminalac.oib":
				sbBuilder.append("oib - "+vrijednost+", ");
				break;

			case "Kriminalac.trenutniStatus":
				sbBuilder.append("status - "+vrijednost+", ");
				break;

			case "Kriminalac.kontaktniBrojTelefona":
				sbBuilder.append("kontaktni broj telefona - "+vrijednost+", ");
				break;

			case "Kriminalac.spol": 
				sbBuilder.append("spol - "+vrijednost+", ");
				break;

			case "Kriminalac.rasa": 
				sbBuilder.append("rasa - "+vrijednost+", ");
				break;

			case "Kriminalac.bojaKose":
				sbBuilder.append("boja kose - "+vrijednost+", ");
				break;

			case "Kriminalac.oblikGlave":
				sbBuilder.append("oblik glave - "+vrijednost+", ");
				break;

			case "Kriminalac.oblikFrizure": 
				sbBuilder.append("frizura - "+vrijednost+", ");
				break;

			case "Kriminalac.bojaOčiju": 
				sbBuilder.append("boja očiju - "+vrijednost+", ");
				break;

			case "Kriminalac.građaTijela": 
				sbBuilder.append("građa tijela - "+vrijednost+", ");
				break;

			case "Kriminalac.razinaApstraktneInteligencije":
				sbBuilder.append("razina apstraktne inteligencije - "+vrijednost+", ");
				break;

			case "Kriminalac.načinGovora": 
				sbBuilder.append("način govora - "+vrijednost+", ");
				break;

			case "Osoba.imeOsobe":
				sbBuilder.append("ime - "+vrijednost+", ");
				break;

			case "Osoba.prezimeOsobe":
				sbBuilder.append("prezime - "+vrijednost+", ");
				break;

			case "Kriminalac.visina":
				String [] parts0=vrijednost.split("#");
				String visinaMin=parts0[0];
				String visinaMax=parts0[1];
				sbBuilder.append("visina između "+visinaMin+" i "+visinaMax+" cm, ");
				break;

			case "Kriminalac.tezina":
				String [] parts1=vrijednost.split("#");
				String tezinaMin=parts1[0];
				String tezinaMax=parts1[1];
				sbBuilder.append("težina između "+tezinaMin+" i "+tezinaMax+" kg, ");
				break;

			case "Kriminalac.datumRođenja": 
				String [] parts2=vrijednost.split("#");
				String godineMin=parts2[0];
				String godineMax=parts2[1];
				sbBuilder.append("starost između "+godineMin+" i "+godineMax+" godina, ");
				break;

			case "Tetovaža.opisTetovaže": 
				sbBuilder.append("tetovaža - "+vrijednost+", ");
				break;

			case "FizičkiNedostatak.fizičkiNedostatakOpis": 
				sbBuilder.append("fizički nedostatak - "+vrijednost+", ");
				break;

			case "Bolest.nazivBolesti": 
				sbBuilder.append("bolest - "+vrijednost+", ");
				break;

			case "FizičkaOsobina.fizičkaOsobinaOpis": 
				sbBuilder.append("fizička osobina - "+vrijednost+", ");
				break;

			case "PsihološkiProblem.psihološkiProblemOpis": 
				sbBuilder.append("psihološki problem - "+vrijednost+", ");
				break;

			case "KarakternaOsobina.karakternaOsobinaOpis": 
				sbBuilder.append("karakterna osobina - "+vrijednost+", ");
				break;

			case "ListaAliasa.alias": 
				sbBuilder.append("alias - "+vrijednost+", ");
				break;

			case "PoznateAdreseStanovanjaKriminalca":
				String [] parts3=vrijednost.split("#");
				String adresaNaziv=parts3[0];
				String mjestoNaziv=parts3[1];
				sbBuilder.append("poznata adresa stanovanja - "+adresaNaziv+" u mjestu "+mjestoNaziv+", ");
				break;

			case "ListaPovezanihKriminalaca.povezanSaKriminalacOib": 
				sbBuilder.append("povezan s kriminalcem čiji je oib - "+vrijednost+", ");
				break;

			case "ListaOsumnjicenihOsoba.brojSlučaja": 
				sbBuilder.append("osumnjičen u slučaju broj - "+vrijednost+", ");
				break;

			case "Osoba.adresaPrebivalista":
				String [] parts4=vrijednost.split("#");
				String adresaNaziv2=parts4[0];
				String mjestoNaziv2=parts4[1];
				sbBuilder.append("adresa prebivališta - "+adresaNaziv2+" u mjestu "+mjestoNaziv2+", ");
			}
		}
		System.out.println(sbBuilder.toString().substring(0, sbBuilder.lastIndexOf(",")));
		return sbBuilder.toString().substring(0, sbBuilder.lastIndexOf(","));
	}

	@Override
	public String generirajSQLupit(String vrijednostPretrage, String relacijaAtributDB) {
		String select=this.generirajSelectOsnovniPodaci();
		String from=" ";
		String where=StrategijaUpit.generirajWhere(relacijaAtributDB,vrijednostPretrage);
		String relAtr2="Kriminalac.oib";

		switch (relacijaAtributDB){

		case "Kriminalac.visina":
			String [] parts0=vrijednostPretrage.split("#");
			String visinaMin=parts0[0];
			String visinaMax=parts0[1];
			String where0=StrategijaUpit.generirajWhere(relacijaAtributDB, visinaMin, visinaMax);
			return select+from+where0;

		case "Kriminalac.tezina":
			String [] parts1=vrijednostPretrage.split("#");
			String tezinaMin=parts1[0];
			String tezinaMax=parts1[1];
			String where1=StrategijaUpit.generirajWhere(relacijaAtributDB, tezinaMin, tezinaMax);
			return select+from+where1;

		case "Kriminalac.datumRođenja": 
			String [] parts2=vrijednostPretrage.split("#");
			String godineMin=parts2[0];
			String godineMax=parts2[1];
			String where2="WHERE (YEAR( CURRENT_TIMESTAMP )-year("+relacijaAtributDB+")) between "+godineMin+" and "+godineMax ;
			return select+from+where2;

		case "Tetovaža.opisTetovaže": 
			from+=StrategijaUpit.generirajFrom("TetovažeKriminalca","TetovažeKriminalca.kriminalacOib", relAtr2);
			from+=StrategijaUpit.generirajFrom("Tetovaža", "Tetovaža.tetovažaID", "TetovažeKriminalca.tetovažaID");
			break;

		case "FizičkiNedostatak.fizičkiNedostatakOpis": 
			from+=StrategijaUpit.generirajFrom("FizičkiNedostaciKriminalca", "FizičkiNedostaciKriminalca.kriminalacOib", relAtr2);
			from+=StrategijaUpit.generirajFrom("FizičkiNedostatak","FizičkiNedostatak.fizičkiNedostatakID" , "FizičkiNedostaciKriminalca.fizičkiNedostatakID");
			break;

		case "Bolest.nazivBolesti": 
			from+=StrategijaUpit.generirajFrom("ListaBolestiKriminalca", "ListaBolestiKriminalca.kriminalacOib", relAtr2);
			from+=StrategijaUpit.generirajFrom("Bolest", "Bolest.bolestID", "ListaBolestiKriminalca.bolestID");
			break;

		case "FizičkaOsobina.fizičkaOsobinaOpis": 
			from+=StrategijaUpit.generirajFrom("OstaleFizičkeOsobineKriminalca", "OstaleFizičkeOsobineKriminalca.kriminalacOib", relAtr2);
			from+=StrategijaUpit.generirajFrom("FizičkaOsobina", "FizičkaOsobina.fizičkaOsobina", "OstaleFizičkeOsobineKriminalca.fizičkaOsobinaID");
			break;

		case "PsihološkiProblem.psihološkiProblemOpis": 
			from+=StrategijaUpit.generirajFrom("PsihološkiProblemiKriminalca", "PsihološkiProblemiKriminalca.kriminalacOib", relAtr2);
			from+=StrategijaUpit.generirajFrom("PsihološkiProblem", "PsihološkiProblem.psihološkiProblemID", "PsihološkiProblemiKriminalca.psihološkiProblemID");
			break;

		case "KarakternaOsobina.karakternaOsobinaOpis": 
			from+=StrategijaUpit.generirajFrom("OstaleKarakterneOsobineKriminalca", "OstaleKarakterneOsobineKriminalca.kriminalacOib", relAtr2);
			from+=StrategijaUpit.generirajFrom("KarakternaOsobina","KarakternaOsobina.karakternaOsobinaID", "OstaleKarakterneOsobineKriminalca.karakternaOsobinaID");
			break;

		case "ListaAliasa.alias": 
			from+=StrategijaUpit.generirajFrom("ListaAliasa", "ListaAliasa.kriminalacOib",relAtr2);
			break;

		case "PoznateAdreseStanovanjaKriminalca":
			String [] parts3=vrijednostPretrage.split("#");
			String adresaNaziv=parts3[0];
			String mjestoNaziv=parts3[1];
			from+=StrategijaUpit.generirajFrom("PoznateAdreseStanovanjaKriminalca", "PoznateAdreseStanovanjaKriminalca.kriminalacOib", relAtr2);
			from+=StrategijaUpit.generirajFrom("Mjesto", "Mjesto.pbrMjesto", "PoznateAdreseStanovanjaKriminalca.pbrMjesto");
			String where3=StrategijaUpit.generirajWhere("PoznateAdreseStanovanjaKriminalca.adresaStanovanja", "Mjesto.nazivMjesto", adresaNaziv, mjestoNaziv);
			return select+from+where3;

		case "ListaPovezanihKriminalaca.povezanSaKriminalacOib": 
			from+=StrategijaUpit.generirajFrom("ListaPovezanihKriminalaca", "ListaPovezanihKriminalaca.kriminalacOib", relAtr2);
			break;

		case "ListaOsumnjicenihOsoba.brojSlučaja": 
			from+=StrategijaUpit.generirajFrom("ListaOsumnjicenihOsoba", "ListaOsumnjicenihOsoba.osobaOib", "Osoba.oib");
			break;

		case "Osoba.adresaPrebivalista":
			String [] parts4=vrijednostPretrage.split("#");
			String adresaNaziv2=parts4[0];
			String mjestoNaziv2=parts4[1];
			from+=StrategijaUpit.generirajFrom("Mjesto", "Mjesto.pbrMjesto", "Osoba.Mjesto_pbrMjesto");
			String where4=StrategijaUpit.generirajWhere("Osoba.adresaPrebivalista","Mjesto.nazivMjesto",  adresaNaziv2, mjestoNaziv2);
			System.out.println(select+from+where4);
			return select+from+where4;
		}
		System.out.println(select+from+where);
		return select+from+where;
	}

	@Override
	public Set<String> generirajListuAtributaPretrage() {
		Set<String> listaAtributa=new HashSet<>(); 
		if(status!=null){
			switch(status){
			case sloboda: listaAtributa.add("na slobodi*Kriminalac.trenutniStatus"); break;
			case u_pritvoru: listaAtributa.add("u pritvoru*Kriminalac.trenutniStatus");break;
			case u_zatvoru: listaAtributa.add("u zatvoru*Kriminalac.trenutniStatus");break;
			}
		}
		if(!StringUtils.isEmptyOrWhitespaceOnly(brojTelefona)) listaAtributa.add(brojTelefona+"*Kriminalac.kontaktniBrojTelefona");
		if(fizickeOsobine!=null){
			Spol spol=fizickeOsobine.getSpol();
			String rasa=fizickeOsobine.getRasa();

			Float visinaMin=fizickeOsobine.getVisinaMin();
			Float visinaMax=fizickeOsobine.getVisinaMax();

			Float tezinaMin=fizickeOsobine.getTezinaMin();
			Float tezinaMax=fizickeOsobine.getTezinaMax();

			Integer godineMin=fizickeOsobine.getGodineMin();
			Integer godineMax=fizickeOsobine.getGodineMax();

			String bojaKose=fizickeOsobine.getBojaKose();
			String oblikGlave=fizickeOsobine.getOblikGlave();
			String oblikFrizure=fizickeOsobine.getOblikFrizure();
			String bojaOciju=fizickeOsobine.getBojaOciju();
			GradaTijela gradaTijela=fizickeOsobine.getGradaTijela();
			Set<String> tetovaze = fizickeOsobine.getTetovaze();
			Set<String> fizickiNedostatci=fizickeOsobine.getFizickiNedostatci();
			Set<String> bolesti=fizickeOsobine.getBolesti();
			Set<String> ostaleFizickeOsobine=fizickeOsobine.getOstaleFizickeOsobine();

			if(spol!=null ) listaAtributa.add(spol.name()+"*Kriminalac.spol");
			if(!StringUtils.isEmptyOrWhitespaceOnly(rasa)) listaAtributa.add(rasa+"*Kriminalac.rasa");

			if(visinaMin!=null && visinaMax!=null) listaAtributa.add(visinaMin.toString()+"#"+visinaMax.toString()+ "*Kriminalac.visina");
			if(tezinaMin!=null && tezinaMax!=null) listaAtributa.add(tezinaMin.toString()+"#"+tezinaMax.toString()+ "*Kriminalac.tezina");
			if(godineMin!=null && godineMax!=null) listaAtributa.add(godineMin.toString()+"#"+godineMax.toString()+ "*Kriminalac.datumRođenja");

			if(!StringUtils.isEmptyOrWhitespaceOnly(bojaKose)) listaAtributa.add(bojaKose+"*Kriminalac.bojaKose");
			if(!StringUtils.isEmptyOrWhitespaceOnly(oblikGlave)) listaAtributa.add(oblikGlave+"*Kriminalac.oblikGlave");
			if(!StringUtils.isEmptyOrWhitespaceOnly(oblikFrizure)) listaAtributa.add(oblikFrizure+"*Kriminalac.oblikFrizure");
			if(!StringUtils.isEmptyOrWhitespaceOnly(bojaOciju)) listaAtributa.add(bojaOciju+"*Kriminalac.bojaOčiju");
			if(gradaTijela!=null) listaAtributa.add(gradaTijela.name()+"*Kriminalac.građaTijela");
			if(tetovaze!=null && !tetovaze.isEmpty()) {
				for(String tetovaza:tetovaze){
					if (!StringUtils.isEmptyOrWhitespaceOnly(tetovaza)) listaAtributa.add(tetovaza+"*Tetovaža.opisTetovaže");
				}
			}
			if(fizickiNedostatci!=null && !fizickiNedostatci.isEmpty()){
				for (String nedostatak: fizickiNedostatci){
					if (!StringUtils.isEmptyOrWhitespaceOnly(nedostatak)) listaAtributa.add(nedostatak+"*FizičkiNedostatak.fizičkiNedostatakOpis");
				}
			}
			if(bolesti!=null && !bolesti.isEmpty()){
				for(String bolest:bolesti){
					if (!StringUtils.isEmptyOrWhitespaceOnly(bolest)) listaAtributa.add(bolest+"*Bolest.nazivBolesti");
				}
			}
			if(ostaleFizickeOsobine!=null && !ostaleFizickeOsobine.isEmpty()){
				for(String ostalo:ostaleFizickeOsobine){
					if (!StringUtils.isEmptyOrWhitespaceOnly(ostalo)) listaAtributa.add(ostalo+"*FizičkaOsobina.fizičkaOsobinaOpis");
				}
			}
		}
		if(karakterneOsobine!=null) {
			String nacinGovora=karakterneOsobine.getNacinGovora();
			RazinaApstraktneInteligencije razinaApstraktneInteligencije=karakterneOsobine.getRazinaApstraktneInteligencije();
			Set <String> psiholoskiProblemi=karakterneOsobine.getPsiholoskiProblemi();
			Set <String> ostaleKarakterneOsobine=karakterneOsobine.getOstaleKarakterneOsobine();
			if(!StringUtils.isEmptyOrWhitespaceOnly(nacinGovora)) listaAtributa.add(nacinGovora+"*Kriminalac.načinGovora");
			if(razinaApstraktneInteligencije!=null) listaAtributa.add(razinaApstraktneInteligencije.name()+"*Kriminalac.razinaApstraktneInteligencije");
			if(psiholoskiProblemi!=null && !psiholoskiProblemi.isEmpty()){
				for(String problem:psiholoskiProblemi){ 
					if (!StringUtils.isEmptyOrWhitespaceOnly(problem)) listaAtributa.add(problem+"*PsihološkiProblem.psihološkiProblemOpis");
			}}
			if(ostaleKarakterneOsobine!=null && !ostaleKarakterneOsobine.isEmpty()){
				for(String ostalo: ostaleKarakterneOsobine) {
					if (!StringUtils.isEmptyOrWhitespaceOnly(ostalo)) listaAtributa.add(ostalo+"*KarakternaOsobina.karakternaOsobinaOpis");
				}
			}
		}
		if(popisAliasa!=null && !popisAliasa.isEmpty()){
			for(String alias:popisAliasa){
				if (!StringUtils.isEmptyOrWhitespaceOnly(alias)) listaAtributa.add(alias+"*ListaAliasa.alias");
			}
		}
		if(poznateAdrese!=null && !poznateAdrese.isEmpty()){
			{
				for (AdresaIMjestoStanovanja adrmj:poznateAdrese) {
					String adr=adrmj.getAdresa();
					String mj=adrmj.getNazivMjesta();
					if(!StringUtils.isEmptyOrWhitespaceOnly(adr) && !StringUtils.isEmptyOrWhitespaceOnly(mj)) listaAtributa.add(adr+"#"+mj+"*PoznateAdreseStanovanjaKriminalca");
				}
			}
		}
		if(popisPovezanihKriminalaca!=null && !popisPovezanihKriminalaca.isEmpty()){
			for(Osumnjiceni o: popisPovezanihKriminalaca){
				if(o.getOib()!=null) listaAtributa.add(o.getOib().toString()+"*ListaPovezanihKriminalaca.povezanSaKriminalacOib");
			}
		}
		if(povezaniSlucajevi!=null && !povezaniSlucajevi.isEmpty()){
			for (Slucaj s: povezaniSlucajevi) {
				if(s.getBrojSlucaja()!=null) listaAtributa.add(s.getBrojSlucaja().toString()+"*ListaOsumnjicenihOsoba.brojSlučaja");
			}
		}

		String ime=super.getIme();
		String prezime=super.getPrezime();
		String oib = null;
		if(super.getOib() != null) oib=super.getOib().toString();
	
		AdresaIMjestoStanovanja admjprebivaliste=super.getAdresaPrebivalista();

		if(!StringUtils.isEmptyOrWhitespaceOnly(ime)) listaAtributa.add(ime+"*Osoba.imeOsobe");
		if(!StringUtils.isEmptyOrWhitespaceOnly(prezime)) listaAtributa.add(prezime+"*Osoba.prezimeOsobe");
		if(!StringUtils.isEmptyOrWhitespaceOnly(oib)) listaAtributa.add(oib +"*Kriminalac.oib");
		if(admjprebivaliste!=null){
			String adr=admjprebivaliste.getAdresa();
			String mj=admjprebivaliste.getNazivMjesta();
			if (!StringUtils.isEmptyOrWhitespaceOnly(adr) && !StringUtils.isEmptyOrWhitespaceOnly(mj)) listaAtributa.add(adr+"#"+mj+"*Osoba.adresaPrebivalista");
		}
	
		return listaAtributa;
	}

	@Override
	public Set<Osumnjiceni> vratiContext(String upit) throws SQLException {
		return PristupBaziPodataka.vratiOsumnjicene(upit);
	}
	

	@Override
	public Set<String> generirajListuIzmjenjenihAtributa(Osumnjiceni izmjenjeniCon) {
		Set<String> atributi=new LinkedHashSet<>();
		if(!status.equals(izmjenjeniCon.status) && izmjenjeniCon.status!=null) {
			switch (izmjenjeniCon.status) {
			case sloboda: atributi.add("na slobodi*trenutniStatus"); break;
			case u_pritvoru: atributi.add("u pritvoru*trenutniStatus");  break;
			case u_zatvoru: atributi.add("u zatvoru*trenutniStatus");  break;
			}
		}
		if(!brojTelefona.equals(izmjenjeniCon.brojTelefona)){
			if(!StringUtils.isEmptyOrWhitespaceOnly(izmjenjeniCon.brojTelefona)) atributi.add(izmjenjeniCon.brojTelefona+"*kontaktniBrojTelefona");
			else atributi.add("NULL*kontaktniBrojTelefona");
		}
		if(!fizickeOsobine.getVisina().equals(izmjenjeniCon.fizickeOsobine.getVisina())){
			if(izmjenjeniCon.fizickeOsobine.getVisina()!=null) atributi.add(izmjenjeniCon.fizickeOsobine.getVisina()+"*visina");
			atributi.add("NULL*visina");
		}
		if(!fizickeOsobine.getTezina().equals(izmjenjeniCon.fizickeOsobine.getTezina())){
			if(izmjenjeniCon.fizickeOsobine.getTezina()!=null) atributi.add(izmjenjeniCon.fizickeOsobine.getTezina()+"*tezina");
			else atributi.add("NULL*tezina");
		}
		if(!fizickeOsobine.getBojaKose().equals(izmjenjeniCon.fizickeOsobine.getBojaKose())){
			if(!StringUtils.isEmptyOrWhitespaceOnly(izmjenjeniCon.fizickeOsobine.getBojaKose())) atributi.add(izmjenjeniCon.fizickeOsobine.getBojaKose()+"*bojaKose");
			else atributi.add("NULL*bojaKose");
		}
		if(!fizickeOsobine.getOblikFrizure().equals(izmjenjeniCon.fizickeOsobine.getOblikFrizure())){
			if(!StringUtils.isEmptyOrWhitespaceOnly(izmjenjeniCon.fizickeOsobine.getOblikFrizure())) atributi.add(izmjenjeniCon.fizickeOsobine.getOblikFrizure()+"*oblikFrizure");
			else atributi.add("NULL*oblikFrizure");
		}
		if(izmjenjeniCon.fizickeOsobine.getGradaTijela()!=null) {
			if(!fizickeOsobine.getGradaTijela().equals(izmjenjeniCon.fizickeOsobine.getGradaTijela())) atributi.add(izmjenjeniCon.fizickeOsobine.getGradaTijela()+"*građaTijela");
		} else atributi.add("NULL*građaTijela");
		if(!opisKriminalnihDjelatnosti.equals(izmjenjeniCon.opisKriminalnihDjelatnosti)){
			if(!StringUtils.isEmptyOrWhitespaceOnly(izmjenjeniCon.opisKriminalnihDjelatnosti)) atributi.add(izmjenjeniCon.getOpisKriminalnihDjelatnosti()+"*opisKriminalnihDjelatnosti");
			else atributi.add("NULL*opisKriminalnihDjelatnosti");
		}
		return atributi;
	}

	@Override
	public String vratiID() {
		return this.getOib().toString();
	}

	@Override
	public String vratiAtributID() {
		return "oib";
	}

	@Override
	public String generirajSelectOsnovniPodaci() {
		return " SELECT distinct Kriminalac.oib, Osoba.imeOsobe,Osoba.prezimeOsobe,Kriminalac.trenutniStatus  FROM Kriminalac left join Osoba on Kriminalac.oib=Osoba.oib  ";
	}

	@Override
	public String generirajUpdateSQL() {
		return " UPDATE Kriminalac SET ";
	}

	
}
