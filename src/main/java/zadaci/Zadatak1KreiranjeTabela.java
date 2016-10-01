package zadaci;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;


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
