package demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final DBProvider dbProvider;

    public UserService(DBProvider dbProvider) {
        this.dbProvider = dbProvider;
    }

    public boolean createUser(User user) throws SQLException {
        boolean isclosed = false;
        try (Connection conn = dbProvider.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("insert into users(id,name,email) values(?,?,?)");
            pstmt.setLong(1, user.getId());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.execute();
            pstmt.closeOnCompletion();
            isclosed = pstmt.isCloseOnCompletion();
        }
        return isclosed;
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();

        try (Connection conn = dbProvider.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("select id,name,email from users");
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                users.add(new User(id, name, email));
            }

        } // Los recursos se cerrarán automáticamente al salir de este bloque try-with-resources

        return users;
    }

}
