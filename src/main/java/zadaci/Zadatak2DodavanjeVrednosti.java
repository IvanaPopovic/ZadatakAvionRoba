package zadaci;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import model.Roba;
import model.Avion;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by android on 1.10.16..
 */
public class Zadatak2DodavanjeVrednosti {
    //Dao objekti sa pomocnim metodama za rad sa bazom
    static Dao<Avion,Integer> avionDao;
    static Dao<Roba,Integer> robaDao;

    public static void main(String[] args) {
        ConnectionSource connectionSource = null;

        try {
            //Potrebno je prvo konektovati se na bazu
            connectionSource=new JdbcConnectionSource("jdbc:sqlite:avionRoba.db");

            //Instanciranje Dao objekata
            avionDao=DaoManager.createDao(connectionSource, Avion.class);
            robaDao=DaoManager.createDao(connectionSource, Roba.class);

            //Kreiranje objekta klase Avion
            //a)
            Avion a1=new Avion("Avion 1", 34);
            avionDao.create(a1);

            //b)
            Avion a2=new Avion("Avion 2", 21);
            avionDao.create(a2);

            //Kreiranje objekta klase Roba
            //a)
            Roba r1=new Roba("Patike"," Duboke patike", 1, 0.1, 0.4, 0.05);
            r1.setAvion(a1);
            robaDao.create(r1);

            //b)
            Roba r2=new Roba("Kosulja"," Na duge rukave", 0.4, 0.01, 2.4, 0.5);
            r2.setAvion(a1);
            robaDao.create(r2);

            //c)
            Roba r3=new Roba("Voda"," Voda za pice", 1.4, 0.3, 0.04, 0.03);
            r3.setAvion(a1);
            robaDao.create(r3);

            //d)
            Roba r4=new Roba("Ploce", " Drvene ploce", 3.4, 0.1, 3,2.3);
            r4.setAvion(a2);
            robaDao.create(r4);

            //e)
            Roba r5=new Roba("Stolica"," Plasticna stolica", 2.4, 1.2, 0.8, 0.5);
            r5.setAvion(a2);
            robaDao.create(r5);

            //Prikaz svih vrednosti tabela Avion
            List<Avion> avion=avionDao.queryForAll();
            for(Avion a:avion)
                System.out.println("a = " + a);

            //Prikaz svih vrednosti tabela Roba
            List<Roba> roba=robaDao.queryForAll();
            for(Roba r:roba)
                System.out.println("r = " + r);


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                //Zatvaranje konekcije sa bazom
                connectionSource.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


}
