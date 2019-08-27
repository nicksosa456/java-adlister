import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAdsDao implements Ads {
    private List<Ad> ads;

    private Connection connection;

    public MySQLAdsDao() throws SQLException {
        Config config = new Config();
        this.connection = DriverManager.getConnection(
                config.getUrl(),
                config.getUsername(),
                config.getPassword()
        );
    }

    @Override
    public List<Ad> all() {
        if (ads == null) {
            ads = showAds();
        }
        return ads;
    }

    private List<Ad> showAds() {
        try {
            Statement stmt = connection.createStatement();
            String queryString = "SELECT * from adlist_db.ads";
            ResultSet rs = stmt.executeQuery(queryString);
            return generate(rs);
        } catch (SQLException e) {
            throw new RuntimeException("error retrieving all ads", e);
        }
    }

    private List<Ad> generate(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
            ads.add(extract(rs));
        }
        return ads;
    }

    private Ad extract(ResultSet rs) throws SQLException {
        return new Ad(
                rs.getInt("user_id"),
                rs.getString("title"),
                rs.getString("description")
        );
    }

    @Override
    public Long insert(Ad ad) {
        if (ads == null) {
            ads = showAds();
        }

        try {
            Statement stmt = connection.createStatement();
            String query = "insert into adlist_db.ads(title, description) values ('2nd Test Ad','This is another test ad')";
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                ad.setId((long) ads.size());
            }
        } catch (SQLException e) {
            throw new RuntimeException("error retrieving all ads", e);
        }
        return ad.getId();
    }

}
