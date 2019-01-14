package Models.BD;

import Models.BD.DAO.UserDAO;
import Models.User;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author MrRainx
 */
public class UsersImp extends User implements UserDAO {

    public UsersImp(int IdUser, String UserName, String Password, String Name, ImageIcon Photo) {
        super(IdUser, UserName, Password, Name, Photo);
    }

    public UsersImp() {
    }

    @Override
    public boolean Insert() {

        String INSERT = "INSERT INTO "
                + " users(idusuario,username,password,nombre,foto)"
                + " VALUES( "
                + " " + getIdUser() + ""
                + " '" + getUserName() + "'"
                + " '" + getPassword() + "'"
                + " '" + getName() + "'"
                + " '" + getPhoto() + "'"
                + ")";

        return ResoucerManager.Statement(INSERT) == null;

    }

    @Override
    public List<User> SelectAll() {

        String SELECT = "SELECT * FROM users";

        List<User> List = new ArrayList<>();

        try {

            ResultSet rs = ResoucerManager.Query(SELECT);

            while (rs.next()) {

                List.add(GetUserFromRs(rs));

            }

            rs.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return List;

    }

    @Override
    public List<User> SelectOne() {
        String SELECT = "SELECT  * "
                + " FROM users "
                + " WHERE username = '" + getUserName() + "'"
                + " AND "
                + " password = '" + getPassword() + "'";

        List<User> List = new ArrayList<>();

        try {

            ResultSet rs = ResoucerManager.Query(SELECT);

            while (rs.next()) {

                List.add(GetUserFromRs(rs));

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return List;
    }

    @Override
    public boolean Update(int Pk) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Delete(int Pk) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private User GetUserFromRs(ResultSet rs) {
        User user = new User();
        try {

            user.setIdUser(rs.getInt(1));
            user.setUserName(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setName(rs.getString(4));

            if (rs.getBinaryStream(5) != null) {
                InputStream photo = rs.getBinaryStream(5);

                BufferedImage image = ImageIO.read(photo);

                ImageIcon foto = new ImageIcon(image);

                user.setPhoto(foto);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsersImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UsersImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

}
