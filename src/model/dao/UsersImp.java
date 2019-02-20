package model.dao;

import controller.libraries.ImgLib;
import model.interfaces.UserDAO;
import model.dto.UserDTO;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MrRainx
 */
public class UsersImp extends UserDTO implements UserDAO {

    public UsersImp(int IdUser, String UserName, String Password, String Name, Image Photo) {
        super(IdUser, UserName, Password, Name, Photo);
    }

    public UsersImp() {

    }

    @Override
    public boolean insert() {

        String INSERT = "INSERT INTO "
                + " usuarios (username,password,nombre,foto) "
                + " VALUES("
                + " '" + getUserName() + "', "
                + " set_byte( MD5('" + getPassword() + "')::bytea, 4,64), "
                + " '" + getName() + "', "
                + " '" + ImgLib.setImageInBase64(getPhoto()) + "' "
                + ")";

        return ResourceManager.Statement(INSERT) == null;

    }

    @Override
    public List<UserDTO> selectAll() {

        String SELECT = "SELECT * FROM usuarios";

        return selectSimple(SELECT);

    }

    @Override
    public List<UserDTO> selectWhereUsernameAndPassword() {
        String SELECT = "SELECT  * "
                + " FROM usuarios "
                + " WHERE "
                + " username = '" + getUserName() + "'"
                + " AND "
                + " password = set_byte( MD5('" + getPassword() + "')::bytea, 4,64)";

        return selectSimple(SELECT);
    }

    private List<UserDTO> selectSimple(String QUERY) {
        List<UserDTO> Lista = new ArrayList<>();

        try {

            try (ResultSet rs = ResourceManager.Query(QUERY)) {
                while (rs.next()) {

                    UserDTO user = new UserDTO();

                    byte[] bytePhoto;

                    user.setIdUser(rs.getInt("idusuario"));
                    user.setUserName(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setName(rs.getString("nombre"));

                    bytePhoto = rs.getBytes("foto");

                    if (bytePhoto != null) {

                        user.setPhoto(ImgLib.ByteToImage(bytePhoto));

                    } else {
                        user.setPhoto(null);
                    }

                    Lista.add(user);
                }
                rs.close();
                ResourceManager.closeRs();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return Lista;
    }

    @Override
    public boolean update(int Pk) {
        String UPDATE = " UPDATE usuarios SET "
                + " idusuario = " + getIdUser() + ","
                + " username = '" + getUserName() + "', "
                + " password = set_byte( MD5('" + getPassword() + "')::bytea, 4,64),"
                + " nombre = '" + getName() + "',"
                + " foto = '" + ImgLib.setImageInBase64(getPhoto()) + "' "
                + " WHERE "
                + " idusuario = " + Pk
                + " ";

        return ResourceManager.Statement(UPDATE) == null;
    }

    @Override
    public boolean delete(int Pk) {

        String DELETE = " DELETE FROM usuarios "
                + " WHERE idusuario = " + Pk;

        return ResourceManager.Statement(DELETE) == null;
    }

}
