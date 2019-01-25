package Models.BD.DAO;

import Models.User;
import java.util.List;

/**
 *
 * @author MrRainx
 */
public interface UsuarioDAO {
    
    public boolean insertar();
    
    public List<User>SelectAll();
    
    public List<User>SelectOne();
    
    public List<User>SelectOne(int Pk);
    
    public boolean Editar(int Pk);
    
    public boolean Eliminar(int Pk);
    
}
