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
    
    public boolean Editar(int Pk);
    
    public boolean Eliminar(int Pk);
    
}
