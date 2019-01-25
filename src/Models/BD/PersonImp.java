package Models.BD;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import Models.BD.DAO.PersonDAO;
import Models.Person;

/**
 *
 * @author MrRainx
 */
public class PersonImp extends Person implements PersonDAO  {

    public PersonImp(String idPerson, String Names, String LastNames, LocalDate Birthdate, String Phone, String Sex, double Salary, int Quota) {
        super(idPerson, Names, LastNames, Birthdate, Phone, Sex, Salary, Quota);
    }

    public PersonImp() {
        
    }

    @Override
    public boolean Insert() {
        String INSERT = "INSERT INTO person "
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
    public List<Person> SelectAll() {
        String SELECT_ALL = "SELECT idpersona,nombres, apellidos,fechanacimiento,telefono,sexo,salario,cupo "
                + " FROM person "
                + " ORDER BY idpersona";

        List<Person> PersonList = new ArrayList<>();

        try {

            ResultSet rs = ResourceManager.Query(SELECT_ALL);
            
            while (rs.next()) {
                PersonList.add(getPersonFromResulSet(rs));//FACTORIZADO
            }
            
            rs.close();
            ResourceManager.closeRs();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return PersonList;
    }

    @Override
    public List<Person> SelectOne(String Aguja) {
        String SELECT_ONE = "SELECT idpersona,nombres, apellidos,fechanacimiento,telefono,sexo,salario,cupo "
                + "FROM person "
                + "WHERE idpersona like '%" + Aguja + "%'"
                + " or nombres like '%" + Aguja + "%'"
                + " or apellidos like '%" + Aguja + "%'";
        
        List<Person> PersonList = new ArrayList<>();
        
        try {
            
            ResultSet rs = ResourceManager.Query(SELECT_ONE);
            
            while (rs.next()) {
                PersonList.add(getPersonFromResulSet(rs));//FACTORIZADO
            }
            
            rs.close();
            ResourceManager.closeRs();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return PersonList;
    }

    @Override
    public boolean Update(String Pk) {

        String UPDATE = "UPDATE person SET "
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
    public boolean Delete(String Pk) {
        String DELETE = "DELETE FROM person WHERE idpersona= '" + Pk + "'";
        return ResourceManager.Statement(DELETE) == null;

    }

    /*
        SUPPORT METHODS
     */
    private Person getPersonFromResulSet(ResultSet rs) {
        Person person = new Person();
        try {

            person.setIdPerson(rs.getString(1));
            person.setNames(rs.getString(2));
            person.setLastNames(rs.getString(3));
            person.setBirthdate(rs.getDate(4).toLocalDate());
            person.setPhone(rs.getString(5));
            person.setSex(rs.getString(6));
            person.setSalary(rs.getDouble(7));
            person.setQuota(rs.getInt(8));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return person;
    }
}
