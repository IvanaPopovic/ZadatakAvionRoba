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
import java.util.Random;
/**
 * Created by android on 1.10.16..
 */
public class AvionNit extends Thread {

    private Avion avion;

    static Dao<Avion,Integer> avionDao;

    public AvionNit(Avion avion) {
        this.avion= avion;
    }

    @Override
    public void run() {
        boolean dozvoljenoSletanje = false;
        System.out.println("Pocinju provere za avion : " + avion.getId());
        Random random = new Random();

        try {
            // Provere traju od 0 do 2 sekunde
            sleep(random.nextInt(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Avion : " + avion.getId() + " je spreman za poletanje i ceka dozvolu za poletanje.");
        do {
            synchronized (avion) {

                if (avion.isPoleteo()) {
                    //Pista je sada zauzeta

                    try {
                        //Poletanje traje 2 sekunde
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    dozvoljenoSletanje = true;
                    avion.setPoleteo(false);
                    System.out.println("Avion " + avion.getId() + " izlazi na pistu i polece." );


                    avion.setPoleteo(true);
                    System.out.println("Avion " + avion.getId() + " je poleteo. ");
                }
            }
        } while (!dozvoljenoSletanje) ;
    }

    public static void main(String[] args){
        ConnectionSource connectionSource = null;
        try {
            //Potrebno je prvo konektovati se na bazu
            connectionSource=new JdbcConnectionSource("jdbc:sqlite:knjigaOblast.db");
            //Instanciranje Dao objekata
            avionDao= DaoManager.createDao(connectionSource, Avion.class);

            //Uzmemo sve avione (bice ih 2)
            //Za obe kreiramo po nit za svaki
            List<Avion> sviAvioni = avionDao.queryForAll();

            AvionNit an1 = new AvionNit(sviAvioni.get(0));

            AvionNit an2= new AvionNit(sviAvioni.get(1));

            an1.start();
            an2.start();

            System.out.println("Svi avioni su poleteli.");

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
