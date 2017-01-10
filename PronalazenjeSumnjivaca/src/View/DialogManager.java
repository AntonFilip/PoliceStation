package View;

import Model.Dogadaj;
import Model.Osoba;

/**
 *
 * @author Karmela
 */
public interface DialogManager {
    public void dodajDogadaj(Dogadaj dogadaj);
    public void dodajOsumnjicenog(Osoba osumnjiceni);
    public void dodajSvjedoka(Osoba svjedok);
}
