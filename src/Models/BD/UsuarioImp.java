package Models.BD;

import Controllers.Libraries.ImgLib;
import Models.BD.DAO.UsuarioDAO;
import Models.User;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MrRainx
 */
public class UsuarioImp extends User implements UsuarioDAO {

    public UsuarioImp(int IdUser, String UserName, String Password, String Name, Image Photo) {
        super(IdUser, UserName, Password, Name, Photo);
    }

    public UsuarioImp() {
        
    }

    @Override
    public boolean insertar() {

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
    public List<User> SelectAll() {

        String SELECT = "SELECT * FROM usuarios";

        List<User> Lista = new ArrayList<>();

        try {

            ResultSet rs = ResourceManager.Query(SELECT);

            while (rs.next()) {
                Lista.add(getUsuarioFromRs(rs));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return Lista;
    }

    @Override
    public List<User> SelectOne() {
        String SELECT = "SELECT  * "
                + " FROM usuarios "
                + " WHERE "
                + " username = '" + getUserName() + "'"
                + " AND "
                + " password = set_byte( MD5('" + getPassword() + "')::bytea, 4,64)";

        List<User> Lista = new ArrayList<>();

        try {

            ResultSet rs = ResourceManager.Query(SELECT);

            while (rs.next()) {
                Lista.add(getUsuarioFromRs(rs));
            }
            
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return Lista;
        
    }

    @Override
    public boolean Editar(int Pk) {
        
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
    public boolean Eliminar(int Pk) {
        
        String DELETE = " DELETE FROM usuarios "
                + " WHERE idusuario = "+Pk;
        
        return  ResourceManager.Statement(DELETE) == null;
    }

    private User getUsuarioFromRs(ResultSet rs) {

        User user = new User();

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
