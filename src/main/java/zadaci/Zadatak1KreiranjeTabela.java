package zadaci;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import model.Roba;
import model.Avion;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by android on 1.10.16..
 */
public class Zadatak1KreiranjeTabela {
    public static void main(String[] args) {
        ConnectionSource connectionSource = null;

        try {
            //Potrebno je prvo konektovati se na bazu
            connectionSource=new JdbcConnectionSource("jdbc:sqlite:avionRoba.db");

             /*Brisanje tabela iz baze
              Zbog ogranicenja referencijalnog integriteta
              prvo treba obrisati tabelu za klasu Roba a
              zatim Avion
            */
            TableUtils.dropTable(connectionSource, Roba.class,true);
            TableUtils.dropTable(connectionSource, Avion.class,true);

            /*Kreiranje tabela u bazi
              Zbog ogranicenja referencijalnog integriteta
              prvo treba kreirati tabelu za klasu Avion a
              zatim Roba
            */
            TableUtils.createTable(connectionSource, Avion.class);
            TableUtils.createTable(connectionSource, Roba.class);


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
