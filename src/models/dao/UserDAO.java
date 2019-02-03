package models.dao;

import models.dto.UserDTO;
import java.util.List;

/**
 *
 * @author MrRainx
 */
public interface UserDAO {
    
    public boolean Insert();
    
    public List<UserDTO>SelectAll();
    
    public List<UserDTO>SelectOne();
    
    public boolean Update(int Pk);    
    
    public boolean Delete(int Pk);    
    
}
