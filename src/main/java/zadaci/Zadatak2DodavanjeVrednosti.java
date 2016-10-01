package zadaci;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by android on 1.10.16..
 */
public class Zadatak2DodavanjeVrednosti {

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
