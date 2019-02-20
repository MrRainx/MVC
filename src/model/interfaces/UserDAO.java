package model.interfaces;

import model.dto.UserDTO;
import java.util.List;

/**
 *
 * @author MrRainx
 */
public interface UserDAO {
    
    public boolean insert();
    
    public List<UserDTO>selectAll();
    
    public List<UserDTO>selectWhereUsernameAndPassword();
    
    public boolean update(int Pk);    
    
    public boolean delete(int Pk);    
    
}
