package zadaci;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import model.Avion;
import model.Roba;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
/**
 * Created by android on 1.10.16..
 */
public class Zadatak4BrisanjeVrednosti {
    //Dao objekti sa pomocnim metodama za rad sa bazom
    static Dao<Avion,Integer> avionDao;
    static Dao<Roba,Integer> robaDao;
    public static void main(String[] args) {
        ConnectionSource connectionSource = null;
        try {
            //Potrebno je prvo konektovati se na bazu
            connectionSource=new JdbcConnectionSource("jdbc:sqlite:avionRoba.db");

            //Instanciranje Dao objekata
            avionDao= DaoManager.createDao(connectionSource, Avion.class);
            robaDao=DaoManager.createDao(connectionSource, Roba.class);

            //Prikaz vrednosti tabele Roba
            List<Roba> roba=robaDao.queryForAll();
            for(Roba r:roba)
                System.out.println("r = " + r);

            //Pronalazenje robe koja za vrednost kolone naziv imaju
            // vrednost "Voda"
            roba=robaDao.queryForEq(Roba.POLJE_NAZIV,"Voda");
            Roba zaBrisanje=roba.get(0);//Preuzimamo prvi pronadjeni
            //Brisemo vrednost iz baze
            robaDao.delete(zaBrisanje);

            /*Prikaz vrednosti tabele Roba
               da potvrdimo da je vrednost obrisana
             */
            roba=robaDao.queryForAll();
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
