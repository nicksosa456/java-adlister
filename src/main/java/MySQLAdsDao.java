import com.mysql.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAdsDao implements Ads {

    private Connection connection;

    public MySQLAdsDao() {
        try {
//            Config config = new Config();
            DriverManager.registerDriver(new Driver());
            this.connection = DriverManager.getConnection(
                    Config.getUrl(),
                    Config.getUsername(),
                    Config.getPassword()
            );
            System.out.println("success to database");
        } catch (SQLException e) {
            System.out.println("failed");
        }
    }

    @Override
    public List<Ad> all() {
        List<Ad> ads = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            String queryString = "SELECT * FROM ads";
            ResultSet rs = stmt.executeQuery(queryString);
            if (rs != null) {
                System.out.println("success");
                while (rs.next()) {
                    Ad nextAd = new Ad(
                            rs.getLong("id"),
                            rs.getLong("user_id"),
                            rs.getString("title"),
                            rs.getString("description")
                    );
                    ads.add(nextAd);
                }
                System.out.println("finished");
            } else {
                System.out.println("no ads");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ads;
    }

//    private List<Ad> showAds() {
//        try {
//            Statement stmt = connection.createStatement();
//            String queryString = "SELECT * from adlist_db.ads";
//            ResultSet rs = stmt.executeQuery(queryString);
//            return generate(rs);
//        } catch (SQLException e) {
//            throw new RuntimeException("error retrieving all ads", e);
//        }
//    }
//
//    private List<Ad> generate(ResultSet rs) throws SQLException {
//        List<Ad> ads = new ArrayList<>();
//        while (rs.next()) {
//            ads.add(extract(rs));
//        }
//        return ads;
//    }
//
//    private Ad extract(ResultSet rs) throws SQLException {
//        return new Ad(
//                rs.getInt("user_id"),
//                rs.getString("title"),
//                rs.getString("description")
//        );
//    }

    @Override
    public Long insert(Ad ad) {
        long id = -1;
        try {
            Statement stmt = connection.createStatement();
            String query = "insert into ads(user_id, title, description) values (1, '"+ad.getTitle()+"','"+ad.getDescription()+"');";
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("error retrieving all ads", e);
        }
        return id;
    }

}
