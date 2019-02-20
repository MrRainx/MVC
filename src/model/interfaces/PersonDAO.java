package model.interfaces;

import java.util.List;
import model.dto.PersonDTO;

/**
 *
 * @author MrRainx
 */
public interface PersonDAO {

    public boolean insert();

    public List<PersonDTO> selectAll();

    public List<PersonDTO> selectWhereNombreOrApellidoLike(String Aguja);

    public boolean update(String Pk);

    public boolean delete(String Pk);

}
