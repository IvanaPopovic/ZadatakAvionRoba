package model;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by android on 1.10.16..
 */
@DatabaseTable(tableName = "avion")//Anotacija za tabelu avion
public class Avion {
    //Staticki atributi
    public static final String POLJE_OZNAKA="oznaka";
    public static final String POLJE_RASPON_KRILA="raspon_krila";


    //atributi za upisu u bazu
    @DatabaseField(generatedId = true)//primarni kljuc koji se automatski generise
    private int id;
    @DatabaseField(columnName = POLJE_OZNAKA)
    private String oznaka;
    @DatabaseField(columnName = POLJE_RASPON_KRILA)
    private int rasponKrila;

    private Boolean poleteo = true;

    //Atribut za vise kraj veze izmendju klasa Avion i Roba
    @ForeignCollectionField(foreignFieldName = "avion")
    private ForeignCollection<Roba> roba;

    //Konstrutkor bez parametara
    public Avion(){
        //Obavezan za potrebe ORMLite-a
    }

    //Konstrutkor koji ocekuje parametre naslov, brojStrana i datumIzdanja
    public Avion(String oznaka, int rasponKrila) {
        this.oznaka = oznaka;
        this.rasponKrila = rasponKrila;
    }

    //get i set metode
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOznaka() {
        return oznaka;
    }

    public void setOznaka(String oznaka) {
        this.oznaka= oznaka;
    }

    public int getRasponKrila() {
        return rasponKrila;
    }

    public void setRasponKrila(int rasponKrila) {
        this.rasponKrila = rasponKrila;
    }

    public Boolean isPoleteo() {
        return poleteo;
    }

    public void setPoleteo(Boolean poleteo) {
        this.poleteo= poleteo;
    }


    public ForeignCollection<Roba> getRoba() {
        return roba;
    }

    public void setRoba(ForeignCollection<Roba> roba) {
        this.roba = roba;
    }

    //Redefinisana metoda toString
    @Override
    public String toString() {
        return "Avion{" +
                "id='" + id + '\'' +
                ", oznaka=" + oznaka +
                ", rasponKrila=" + rasponKrila +
                '}';
    }


}
