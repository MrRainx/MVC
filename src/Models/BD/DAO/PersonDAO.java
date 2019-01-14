package Models.BD.DAO;

import java.util.List;
import Models.Person;

/**
 *
 * @author MrRainx
 */
public interface PersonDAO {
    
    public boolean Insert();
    
    public List<Person>SelectAll();
    
    public List<Person>SelectOne(String Aguja);
    
    public boolean Update(String Pk);    
    
    public boolean Delete(String Pk);
    
}
