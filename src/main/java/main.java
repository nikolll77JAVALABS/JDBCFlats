import java.sql.*;

public class main {

    static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/mydb";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "zxcv4321";

    static Connection databaseConnection;

    public static void main(String[] args) throws SQLException {

        try {
            databaseConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        initDB();
        String SqlQuery="INSERT INTO flats (region,rooms,price) VALUES(?,?,?) ";
        PreparedStatement ps = databaseConnection.prepareStatement(SqlQuery);
        ps.setString(1,"Centr");
        ps.setInt(2,3);
        ps.setInt(3,30000);
        ps.executeUpdate();

        ps.setString(1,"borshaga");
        ps.setInt(2,2);
        ps.setInt(3,20000);
        ps.executeUpdate();

        SqlQuery="select * from flats where price<=?";
        ps = databaseConnection.prepareStatement(SqlQuery);
        ps.setInt(1,25000);

        ResultSet rs=ps.executeQuery();
        ResultSetMetaData md= rs.getMetaData();
        for(int i=1;i<=md.getColumnCount();i++) {
            System.out.print(md.getColumnName(i)+"\t\t");
        }
        System.out.println();
        while (rs.next()) {
            for(int i=1;i<=md.getColumnCount();i++) {
                System.out.print(rs.getString(i)+"\t\t");
            }
        }
        System.out.println();
    }

    private static void initDB() throws SQLException {
        String sqlCreate = "CREATE TABLE `flats` (" +
                "`id` INT(11) NOT NULL AUTO_INCREMENT," +
                "`region` VARCHAR(20) NULL DEFAULT NULL COLLATE 'cp1251_ukrainian_ci',\n" +
                "`address` VARCHAR(50) NULL DEFAULT NULL COLLATE 'cp1251_ukrainian_ci',\n" +
                "`square` INT(11) NULL DEFAULT NULL," +
                "`rooms` INT(11) NULL DEFAULT NULL," +
                "`price` INT(11) NULL DEFAULT NULL," +
                "PRIMARY KEY (`id`)" +
                ")" +
                "COLLATE='cp1251_ukrainian_ci'" +
                "ENGINE=InnoDB;";

        Statement st = databaseConnection.createStatement();
        st.execute("DROP TABLE IF EXISTS flats");
        st.execute(sqlCreate);
        st.close();
    }

}
