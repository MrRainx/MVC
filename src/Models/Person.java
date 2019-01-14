/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author MrRainx
 */
public class Person implements Serializable{
    
    private String idPerson;
    private String Names;
    private String LastNames;
    private LocalDate Birthdate;
    private String Phone;
    private String Sex;
    private double Salary;
    private int Quota;

    public Person(String idPerson, String Names, String LastNames, LocalDate Birthdate, String Phone, String Sex, double Salary, int Quota) {
        this.idPerson = idPerson;
        this.Names = Names;
        this.LastNames = LastNames;
        this.Birthdate = Birthdate;
        this.Phone = Phone;
        this.Sex = Sex;
        this.Salary = Salary;
        this.Quota = Quota;
    }

    public Person() {
    }

    public String getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(String idPerson) {
        this.idPerson = idPerson;
    }

    public String getNames() {
        return Names;
    }

    public void setNames(String Names) {
        this.Names = Names;
    }

    public String getLastNames() {
        return LastNames;
    }

    public void setLastNames(String LastNames) {
        this.LastNames = LastNames;
    }

    public LocalDate getBirthdate() {
        return Birthdate;
    }

    public void setBirthdate(LocalDate Birthdate) {
        this.Birthdate = Birthdate;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String Sex) {
        this.Sex = Sex;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double Salary) {
        this.Salary = Salary;
    }

    public int getQuota() {
        return Quota;
    }

    public void setQuota(int Quota) {
        this.Quota = Quota;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.idPerson);
        hash = 59 * hash + Objects.hashCode(this.Names);
        hash = 59 * hash + Objects.hashCode(this.LastNames);
        hash = 59 * hash + Objects.hashCode(this.Birthdate);
        hash = 59 * hash + Objects.hashCode(this.Phone);
        hash = 59 * hash + Objects.hashCode(this.Sex);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.Salary) ^ (Double.doubleToLongBits(this.Salary) >>> 32));
        hash = 59 * hash + this.Quota;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (Double.doubleToLongBits(this.Salary) != Double.doubleToLongBits(other.Salary)) {
            return false;
        }
        if (this.Quota != other.Quota) {
            return false;
        }
        if (!Objects.equals(this.idPerson, other.idPerson)) {
            return false;
        }
        if (!Objects.equals(this.Names, other.Names)) {
            return false;
        }
        if (!Objects.equals(this.LastNames, other.LastNames)) {
            return false;
        }
        if (!Objects.equals(this.Birthdate, other.Birthdate)) {
            return false;
        }
        if (!Objects.equals(this.Phone, other.Phone)) {
            return false;
        }
        return Objects.equals(this.Sex, other.Sex);
    }

    @Override
    public String toString() {
        return "ID: "+idPerson+""
                + "\nNames: "+Names+""
                + "\nLastNames: "+LastNames;
    }
    
    
    
    
    
}
