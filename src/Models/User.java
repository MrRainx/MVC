package Models;

import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 *
 * @author MrRainx
 */
public class User implements Serializable{
    
    private int IdUser;
    private String UserName;
    private String Password;
    private String Name;
    private ImageIcon Photo;

    public User(int IdUser, String UserName, String Password, String Name, ImageIcon Photo) {
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

    public ImageIcon getPhoto() {
        return Photo;
    }

    public void setPhoto(ImageIcon Photo) {
        this.Photo = Photo;
    }

    
    @Override
    public String toString() {
        return "Users{" + "IdUser=" + IdUser + ", UserName=" + UserName + ", Password=" + Password + ", Name=" + Name + ", Photo=" + Photo + '}';
    }
    
    
    
}
