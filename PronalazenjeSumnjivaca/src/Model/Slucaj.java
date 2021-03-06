package Model;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import com.mysql.jdbc.StringUtils;


public class Slucaj implements StrategijaUpit<Slucaj>{

	private Integer brojSlucaja;
	private String nazivSlucaja;
	private String opis;
	private Osoba glavniOsumnjiceni;
	private Set<Osoba> popisOsumnjicenih = new HashSet<Osoba>();
	private Set<Osoba> popisSvjedoka = new HashSet<Osoba>();
	private Set<Dokaz> popisDokaza = new HashSet<Dokaz>();
	private Set<Pozornik> popisPolicajaca = new HashSet<Pozornik>();
	private TrenutniStatusSlucaja status;
	private Set<String> fotografijeSlučaja=new HashSet<>();
	private Set<Dogadaj> popisDogadaja= new HashSet<>();


	public Slucaj(){
		super();
	}

	public Slucaj(Integer brojSlucaja, String nazivSlucaja, String opis, Osoba glavniOsumnjiceni,
			Set<Osoba> popisOsumnjicenih, Set<Osoba> popisSvjedoka, Set<Dokaz> popisDokaza, Set<Pozornik> popisPolicajaca,
			TrenutniStatusSlucaja status, Set<String> fotografijeSlučaja, Set<Dogadaj> popisDogadaja) {
		super();
		this.brojSlucaja = brojSlucaja;
		this.nazivSlucaja = nazivSlucaja;
		this.opis = opis;
		this.glavniOsumnjiceni = glavniOsumnjiceni;
		this.popisOsumnjicenih = popisOsumnjicenih;
		this.popisSvjedoka = popisSvjedoka;
		this.popisDokaza = popisDokaza;
		this.popisPolicajaca = popisPolicajaca;
		this.status = status;
		this.fotografijeSlučaja = fotografijeSlučaja;
		this.popisDogadaja = popisDogadaja;
	}

	public Integer getBrojSlucaja() {
		return brojSlucaja;
	}

	public void setBrojSlucaja(Integer brojSlucaja) {
		this.brojSlucaja = brojSlucaja;
	}

	public String getNazivSlucaja() {
		return nazivSlucaja;
	}

	public void setNazivSlucaja(String nazivSlucaja) {
		this.nazivSlucaja = nazivSlucaja;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Osoba getGlavniOsumnjiceni() {
		return glavniOsumnjiceni;
	}

	public void setGlavniOsumnjiceni(Osoba glavniOsumnjiceni) {
		this.glavniOsumnjiceni = glavniOsumnjiceni;
	}

	public Set<Osoba> getPopisOsumnjicenih() {
		return popisOsumnjicenih;
	}

	public void setPopisOsumnjicenih(Set<Osoba> popisOsumnjicenih) {
		this.popisOsumnjicenih = popisOsumnjicenih;
	}

	public boolean addOsumnjiceni(Osoba os){
		return this.popisOsumnjicenih.add(os);
	}

	public boolean addAllOsumnjiceni(Collection<Osoba> osumnjiceni){
		return this.popisOsumnjicenih.addAll(osumnjiceni);
	}

	public Set<Osoba> getPopisSvjedoka() {
		return popisSvjedoka;
	}

	public void setPopisSvjedoka(Set<Osoba> popisSvjedoka) {
		this.popisSvjedoka = popisSvjedoka;
	}

	public boolean addSvjedok(Osoba svjedok){
		return this.popisSvjedoka.add(svjedok);
	}

	public boolean addAllSvjedoci(Collection<Osoba> svjedoci){
		return this.popisSvjedoka.addAll(svjedoci);
	}

	public Set<Dokaz> getPopisDokaza() {
		return popisDokaza;
	}

	public void setPopisDokaza(Set<Dokaz> popisDokaza) {
		this.popisDokaza = popisDokaza;
	}

	public boolean addDokaz(Dokaz dokaz){
		return this.popisDokaza.add(dokaz);
	}

	public boolean addAllDokazi(Collection< Dokaz> dokazi){
		return this.popisDokaza.addAll(dokazi);
	}

	public Set<Pozornik> getPopisPolicajaca() {
		return popisPolicajaca;
	}

	public void setPopisPolicajaca(Set<Pozornik> popisPolicajaca) {
		this.popisPolicajaca = popisPolicajaca;
	}

	public boolean addPolicajac(Pozornik p){
		return this.popisPolicajaca.add(p);
	}

	public boolean addAllPolicajci(Collection<Pozornik> policajci){
		return this.popisPolicajaca.addAll(policajci);
	}

	public TrenutniStatusSlucaja getStatus() {
		return status;
	}

	public void setStatus(TrenutniStatusSlucaja status) {
		this.status = status;
	}

	public Set<String> getFotografijeSlučaja() {
		return fotografijeSlučaja;
	}

	public void setFotografijeSlučaja(Set<String> fotografijeSlučaja) {
		this.fotografijeSlučaja = fotografijeSlučaja;
	}

	public boolean addFotografija(String foto){
		return this.fotografijeSlučaja.add(foto);
	}

	public boolean addAllFotografije(Collection<String> fotografije){
		return this.fotografijeSlučaja.addAll(fotografije);
	}

	public Set<Dogadaj> getPopisDogadaja() {
		return popisDogadaja;
	}

	public void setPopisDogadaja(Set<Dogadaj> popisDogadaja) {
		this.popisDogadaja = popisDogadaja;
	}

	public boolean addDogadaj(Dogadaj dog){
		return this.popisDogadaja.add(dog);
	}

	public boolean addAllDogadaji (Collection<Dogadaj> dogadaji){
		return this.popisDogadaja.addAll(dogadaji);
	}
	
	public static String izmjenaDogađaja(String value){
		return value+"*ListaDogađaja.nazivDogađaja*ListaDogađaja.nazivDogađaja";
	}
	public static String izmjenaSvjedoka(String value){
		return value+"*ListaSvjedoka.osobaOib*ListaSvjedoka.osobaOib";
	}
	public static String izmjenaOsumnjicenih(String value){
		return value+"*ListaOsumnjicenihOsoba.osobaOib*ListaOsumnjicenihOsoba.osobaOib";
	}
	public static String izmjenaFotografija(String value){
		return value+"*FotografijaPolicijskogSlučaja.fotografijaURL*FotografijaPolicijskogSlučaja.fotografijaURL";
	}
	public static String izmjenaPolicajca(String value){
		return value+"*PolicajciDodijeljeniSlučaju.jedinstveniBrojPolicajca*PolicajciDodijeljeniSlučaju.jedinstveniBrojPolicajca";
	}
        public static String izmjenaDokaza(String value) {
            return value+"*DokazniMaterijal.nazivDokaznogMaterijala*DokazniMaterijal.fotografijaDokaznogMaterijalaURL";
        }
        
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brojSlucaja == null) ? 0 : brojSlucaja.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Slucaj other = (Slucaj) obj;
		if (brojSlucaja == null) {
			if (other.brojSlucaja != null)
				return false;
		} else if (!brojSlucaja.equals(other.brojSlucaja))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\nSlucaj [brojSlucaja=" + brojSlucaja + ", nazivSlucaja=" + nazivSlucaja + ", status=" + status + "]";
	}

	@Override
	public String generirajSQLupit( String vrijednostPretrage,String relacijaAtribut) {
		String select=generirajSelectOsnovniPodaci();
		String from=" ";
	
		switch (relacijaAtribut) {
			case "ListaDogađaja.nazivDogađaja":
				from+=StrategijaUpit.generirajFrom("ListaDogađaja","ListaDogađaja.brojSlučaja","PolicijskiSlučaj.brojSlučaja");
				break;
				
			case "DokazniMaterijal.brojDokaznogMaterijala":
				from+=StrategijaUpit.generirajFrom("DokazniMaterijal", relacijaAtribut,"PolicijskiSlučaj.brojSlučaja");
				break;
				
			case "ListaOsumnjicenihOsoba.osobaOib":
				from+=StrategijaUpit.generirajFrom("ListaOsumnjicenihOsoba","ListaOsumnjicenihOsoba.brojSlučaja","PolicijskiSlučaj.brojSlučaja");
				break;
				
			case "PolicajciDodijeljeniSlučaju.jedinstveniBrojPolicajca":
				from+=StrategijaUpit.generirajFrom("PolicajciDodijeljeniSlučaju", "PolicajciDodijeljeniSlučaju.brojSlučaja","PolicijskiSlučaj.brojSlučaja");
				break;
				
			case "ListaSvjedoka.osobaOib":
				from+=StrategijaUpit.generirajFrom("ListaSvjedoka", "ListaSvjedoka.brojSlučaja","PolicijskiSlučaj.brojSlučaja");
				break;
			default: break;
		}
		String where=StrategijaUpit.generirajWhere(relacijaAtribut,vrijednostPretrage);
		System.out.println(select+from+where);
		return select+from+where;
	}
		
	@Override
	public  Set<String>  generirajListuAtributaPretrage() {
		 Set< String> listaAtributa=new HashSet<>();

		if (brojSlucaja!=null) {
			listaAtributa.add(brojSlucaja.toString()+"*PolicijskiSlučaj.brojSlučaja");
		}
		if (!StringUtils.isEmptyOrWhitespaceOnly(nazivSlucaja)){
			listaAtributa.add(nazivSlucaja+"*PolicijskiSlučaj.nazivSlučaja" );
		}
		if (status!=null){
			switch (status) {
			case riješen: listaAtributa.add("riješen*PolicijskiSlučaj.trenutniStatus"); break;
			case zatvoren: listaAtributa.add("zatvoren ali neriješen*PolicijskiSlučaj.trenutniStatus"); break;
			case otvoren: listaAtributa.add("otvoren*PolicijskiSlučaj.trenutniStatus"); break;
			}
		}
		if (glavniOsumnjiceni!=null){
			if (glavniOsumnjiceni.getOib()!=null) listaAtributa.add(glavniOsumnjiceni.getOib().toString() +"*PolicijskiSlučaj.glavnaOsumljicenaOsobaOib");
		}

		if (popisDogadaja!=null && !popisDogadaja.isEmpty()){
			for (Dogadaj d:popisDogadaja){
				if(!StringUtils.isEmptyOrWhitespaceOnly(d.getNaziv())) listaAtributa.add(d.getNaziv()+"*ListaDogađaja.nazivDogađaja");
			}
		}
		if (popisDokaza!=null && !popisDokaza.isEmpty()){
			for(Dokaz d: popisDokaza){
				if (d!=null && d.getID()!=null) listaAtributa.add(d.getID().toString()+"*DokazniMaterijal.brojDokaznogMaterijala");
			}
		}
		if (popisOsumnjicenih!=null && !popisOsumnjicenih.isEmpty()){
			for(Osoba o: popisOsumnjicenih){
				if(o!=null && o.getOib()!=null) listaAtributa.add(o.getOib().toString()+"*ListaOsumnjicenihOsoba.osobaOib");
			}
		}
		if (popisPolicajaca!=null && !popisPolicajaca.isEmpty()){
			for(Pozornik p:popisPolicajaca){
				if(p!=null && p.getJedinstveniBroj() != null){
					listaAtributa.add(p.getJedinstveniBroj().toString()+"*PolicajciDodijeljeniSlučaju.jedinstveniBrojPolicajca");
					}
				}
		}
		if (popisSvjedoka!=null && !popisSvjedoka.isEmpty()){
			for(Osoba o:popisSvjedoka){
				if(o!=null && o.getOib()!=null) listaAtributa.add(o.getOib().toString()+"*ListaSvjedoka.osobaOib");
			}
		}	
		return listaAtributa;
	}

	@Override
	public Set<Slucaj> vratiContext(String upit) throws SQLException {
		return PristupBaziPodataka.vratiSlucajeve(upit);
	}

	@Override
	public String generirajTextualniOpis(Set<String> listaAtributa) {
		StringBuilder sbBuilder=new StringBuilder();
		sbBuilder.append("Traži se policijski slučaj sa sljedećim obilježjima: ");
		if(listaAtributa.isEmpty()) return null;
		for (String string: listaAtributa){
			String [] parts=string.split("\\*");
			String obiljezje=parts[1];
			String vrijednost=parts[0];
			switch (obiljezje){

			case "PolicijskiSlučaj.brojSlučaja":
				sbBuilder.append("broj slučaja - "+vrijednost+", ");
				break;
			case "PolicijskiSlučaj.nazivSlučaja": 
				sbBuilder.append("naziv slučaja - "+vrijednost+", ");
				break;
			case "PolicijskiSlučaj.trenutniStatus":
				sbBuilder.append("trenutni status - "+vrijednost+", ");
				break;
			case "PolicijskiSlučaj.glavnaOsumljicenaOsobaOib":
				sbBuilder.append("glavna osumnjicena osoba oib - "+vrijednost+", ");
				break;
			case "ListaDogađaja.nazivDogađaja":
				sbBuilder.append("naziv događaja - "+vrijednost+", ");
				break;
			case "DokazniMaterijal.brojDokaznogMaterijala":
				sbBuilder.append("broj dokaznog materijala - "+vrijednost+", ");
				break;
			case "ListaOsumnjicenihOsoba.osobaOib":
				sbBuilder.append("osumnjicena osoba oib - "+vrijednost+", ");
				break;
			case "PolicajciDodijeljeniSlučaju.jedinstveniBrojPolicajca":
				sbBuilder.append("jedinstveni broj policajca - "+vrijednost+", ");
				break;
			case "ListaSvjedoka.osobaOib":
				sbBuilder.append("svjedok oib - "+vrijednost+", ");
				break;
			}
		}
		System.out.println(sbBuilder.toString().substring(0, sbBuilder.lastIndexOf(",")));
		return sbBuilder.toString().substring(0, sbBuilder.lastIndexOf(","));
	}

	@Override
	public Set<String> generirajListuIzmjenjenihAtributa(Slucaj izmjenjeniSlucaj) {
		Set<String> atributiSlucaja=new LinkedHashSet<>();
		
		if(!this.nazivSlucaja.equals(izmjenjeniSlucaj.getNazivSlucaja())&& !StringUtils.isEmptyOrWhitespaceOnly(izmjenjeniSlucaj.getNazivSlucaja())) atributiSlucaja.add(izmjenjeniSlucaj.nazivSlucaja+"*nazivSlučaja");
		if(!this.opis.equals(izmjenjeniSlucaj.getOpis())&& !StringUtils.isEmptyOrWhitespaceOnly(izmjenjeniSlucaj.getOpis())) atributiSlucaja.add(izmjenjeniSlucaj.getOpis()+"*opis");
		if(this.status!=izmjenjeniSlucaj.getStatus()&& izmjenjeniSlucaj.getStatus()!=null) atributiSlucaja.add(izmjenjeniSlucaj.getStatus().name()+"*trenutniStatus");
		if(this.glavniOsumnjiceni!=izmjenjeniSlucaj.getGlavniOsumnjiceni() ) {
			if (izmjenjeniSlucaj.getGlavniOsumnjiceni()!=null) {
				atributiSlucaja.add(izmjenjeniSlucaj.getGlavniOsumnjiceni().getOib().toString()+"*glavnaOsumljicenaOsobaOib");
			}else atributiSlucaja.add("NULL*glavnaOsumljicenaOsobaOib");
		}
		return atributiSlucaja;
	}

	@Override
	public String vratiID() {
		return this.getBrojSlucaja().toString();
	}
	
	@Override
	public String vratiAtributID(){
		return "brojSlučaja";
	}

	
	@Override
	public String generirajSelectOsnovniPodaci() {
	return	" SELECT distinct PolicijskiSlučaj.brojSlučaja, "
				+ "						PolicijskiSlučaj.nazivSlučaja, "
				+ "						PolicijskiSlučaj.trenutniStatus "
				+" FROM PolicijskiSlučaj  ";
	
	}

	@Override
	public String generirajUpdateSQL() {
		return "UPDATE PolicijskiSlučaj SET ";
	}

	@Override
	public String vratiAtributID2() {
		return "brojSlučaja";
	}

}
