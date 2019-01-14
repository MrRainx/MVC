package Models.BD.DAO;

import Models.User;
import java.util.List;

/**
 *
 * @author MrRainx
 */
public interface UserDAO {
    
    public boolean Insert();
    
    public List<User>SelectAll();
    
    public List<User>SelectOne();
    
    public boolean Update(int Pk);    
    
    public boolean Delete(int Pk);    
    
}
