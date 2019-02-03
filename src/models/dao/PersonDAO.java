package models.dao;

import java.util.List;
import models.dto.PersonDTO;

/**
 *
 * @author MrRainx
 */
public interface PersonDAO {
    
    public boolean Insert();
    
    public List<PersonDTO>SelectAll();
    
    public List<PersonDTO>SelectOne(String Aguja);
    
    public boolean Update(String Pk);    
    
    public boolean Delete(String Pk);
    
}
