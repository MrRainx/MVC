package model.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.interfaces.PersonDAO;
import model.dto.PersonDTO;

/**
 *
 * @author MrRainx
 */
public class PersonImp extends PersonDTO implements PersonDAO {

    public PersonImp(String idPerson, String Names, String LastNames, LocalDate Birthdate, String Phone, String Sex, double Salary, int Quota) {
        super(idPerson, Names, LastNames, Birthdate, Phone, Sex, Salary, Quota);
    }

    public PersonImp() {

    }

    @Override
    public boolean insert() {
        String INSERT = "INSERT INTO persona "
                + " (idpersona, nombres, apellidos, fechanacimiento, telefono, sexo, salario, cupo) "
                + " VALUES ("
                + " '" + getIdPerson() + "',"
                + " '" + getNames() + "',"
                + " '" + getLastNames() + "', "
                + " '" + Date.valueOf(getBirthdate()) + "', "
                + " '" + getPhone() + "', "
                + " '" + getSex() + "',"
                + " " + getSalary() + ","
                + " " + getQuota() + ")";

        return ResourceManager.Statement(INSERT) == null;
    }

    @Override
    public List<PersonDTO> selectAll() {
        String SELECT_ALL = "SELECT idpersona,nombres, apellidos,fechanacimiento,telefono,sexo,salario,cupo "
                + " FROM persona "
                + " ORDER BY nombres";
        
        return SelectSimple(SELECT_ALL);
    }

    @Override
    public List<PersonDTO> selectWhereNombreOrApellidoLike(String Aguja) {
        String SELECT_ONE = "SELECT idpersona,nombres, apellidos,fechanacimiento,telefono,sexo,salario,cupo "
                + "FROM persona "
                + "WHERE idpersona like '%" + Aguja + "%'"
                + " or nombres like '%" + Aguja + "%'"
                + " or apellidos like '%" + Aguja + "%' "
                + " ORDER BY nombres";
        
        return SelectSimple(SELECT_ONE);

    }

    @Override
    public boolean update(String Pk) {

        String UPDATE = "UPDATE persona SET "
                + " idpersona = '" + getIdPerson() + "' ,"
                + " nombres = '" + getNames() + "' ,"
                + " apellidos = '" + getLastNames() + "' , "
                + " fechanacimiento = '" + Date.valueOf(getBirthdate()) + "' ,"
                + " telefono = '" + getPhone() + "' , "
                + " sexo = '" + getSex() + "' ,"
                + " salario = " + getSalary() + " ,"
                + " cupo = " + getQuota() + " "
                + " WHERE idpersona = '" + Pk + "'";

        return ResourceManager.Statement(UPDATE) == null;

    }

    @Override
    public boolean delete(String Pk) {
        String DELETE = "DELETE FROM persona WHERE idpersona= '" + Pk + "'";
        return ResourceManager.Statement(DELETE) == null;

    }

    private List<PersonDTO> SelectSimple(String QUERY) {
        List<PersonDTO> PersonList = new ArrayList<>();

        try {

            try (ResultSet rs = ResourceManager.Query(QUERY)) {
                while (rs.next()) {
                    PersonDTO person = new PersonDTO();
                    
                    person.setIdPerson(rs.getString("idpersona"));
                    person.setNames(rs.getString("nombres"));
                    person.setLastNames(rs.getString("apellidos"));
                    person.setBirthdate(rs.getDate("fechanacimiento").toLocalDate());
                    person.setPhone(rs.getString("telefono"));
                    person.setSex(rs.getString("sexo"));
                    person.setSalary(rs.getDouble("salario"));
                    person.setQuota(rs.getInt("cupo"));
                    PersonList.add(person);
                }
            }
            ResourceManager.closeRs();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return PersonList;

    }

}
