package Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class DB_Connection {

    //Singleton Design Pattern
    private static DB_Connection _dbCon;
    private String conString = "jdbc:sqlserver://localhost:1433;database=Kutuphane;integratedSecurity=true;ssl=true;sslProtocol=TLS;encrypt=true;trustServerCertificate=true;";
    Connection connection = null;

    private DB_Connection(){

    }

    public static DB_Connection ConnectAsSingleton(){

        if(_dbCon == null){
            _dbCon = new DB_Connection();
        }
        return _dbCon;
    }


    public Connection getConnection() throws SQLException{
        if(connection == null || connection.isClosed()){
            connection = DriverManager.getConnection(conString);
        }
        return connection;
    }

    public void kitapKisitlamalariGeciciKaldır() {
        try {
            String sql = "ALTER TABLE odunc_kitaplar DROP constraint  kitap_id";
            String sql2 ="ALTER TABLE iade_kitaplar DROP constraint kitap_id";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.execute();
                try(PreparedStatement statement2 = connection.prepareStatement(sql2)){
                    statement2.execute();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void kitapKisitlamalariGeriEkle() {
        try {
            String sql = "ALTER TABLE odunc_kitaplar ADD CONSTRAINT FK_odunc_kitaplar_kitaplar FOREIGN KEY (kitap_id) REFERENCES kitaplar(kitap_id);";
            String sql2 = "ALTER TABLE iade_kitaplar ADD CONSTRAINT FK_iade_kitaplar_kitaplar FOREIGN KEY (kitap_id) REFERENCES kitaplar(kitap_id);";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.execute();
                try(PreparedStatement statement2 = connection.prepareStatement(sql2)){
                    statement2.execute();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
