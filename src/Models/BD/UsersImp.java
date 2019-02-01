package Models.BD;

import Controllers.Libraries.ImgLib;
import Models.DAO.UserDAO;
import Models.DTO.UserDTO;
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
    public boolean Insert() {

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
    public List<UserDTO> SelectAll() {

        String SELECT = "SELECT * FROM usuarios";

        List<UserDTO> Lista = new ArrayList<>();

        try {

            try (ResultSet rs = ResourceManager.Query(SELECT)) {
                while (rs.next()) {
                    Lista.add(getUsuarioFromRs(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return Lista;
    }

    @Override
    public List<UserDTO> SelectOne() {
        String SELECT = "SELECT  * "
                + " FROM usuarios "
                + " WHERE "
                + " username = '" + getUserName() + "'"
                + " AND "
                + " password = set_byte( MD5('" + getPassword() + "')::bytea, 4,64)";

        List<UserDTO> Lista = new ArrayList<>();

        try {

            try (ResultSet rs = ResourceManager.Query(SELECT)) {
                while (rs.next()) {
                    Lista.add(getUsuarioFromRs(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return Lista;
        
    }
    
    @Override
    public boolean Update(int Pk) {
        String UPDATE = " UPDATE usuarios SET "
                + " idusuario = "+getIdUser()+","
                + " username = '"+getUserName()+"', "
                + " password = set_byte( MD5('" + getPassword() + "')::bytea, 4,64),"
                + " nombre = '"+getName()+"',"
                + " foto = '"+ImgLib.setImageInBase64( getPhoto() )+"' "
                + " WHERE "
                + " idusuario = "+Pk
                + " ";
        
        return ResourceManager.Statement(UPDATE) == null;
    }

    @Override
    public boolean Delete(int Pk) {
        
        String DELETE = " DELETE FROM usuarios "
                + " WHERE idusuario = "+Pk;
        
        return  ResourceManager.Statement(DELETE) == null;
    }

    private UserDTO getUsuarioFromRs(ResultSet rs) {

        UserDTO user = new UserDTO();

        byte[] bytePhoto;

        try {

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

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return user;
    }



}
