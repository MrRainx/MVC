package Models;

import java.awt.Image;
import java.io.FileInputStream;
import java.io.Serializable;

/**
 *
 * @author MrRainx
 */
public class User implements Serializable{
    
    private int IdUser;
    private String UserName;
    private String Password;
    private String Name;
    private Image Photo;

    public User(int IdUser, String UserName, String Password, String Name, Image Photo) {
        this.IdUser = IdUser;
        this.UserName = UserName;
        this.Password = Password;
        this.Name = Name;
        this.Photo = Photo;
    }

    public User() {
    }

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int IdUser) {
        this.IdUser = IdUser;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Image getPhoto() {
        return Photo;
    }

    public void setPhoto(Image Photo) {
        this.Photo = Photo;
    }

    
    @Override
    public String toString() {
        return "Users{" + "IdUser=" + IdUser + ", UserName=" + UserName + ", Password=" + Password + ", Name=" + Name + ", Photo=" + Photo.toString() + '}';
    }
    
    
    
}
