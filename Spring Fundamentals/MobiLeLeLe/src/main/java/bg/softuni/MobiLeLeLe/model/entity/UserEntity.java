package bg.softuni.MobiLeLeLe.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String username;

    private String firstName;

    private String lastName;

    private Boolean isActive;

    @ManyToMany
    private List<UserRole> roles;

    private String imageUrl;

    @Column(nullable = false)
    private String password;

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Boolean getActive() {
        return isActive;
    }

    public UserEntity setActive(Boolean active) {
        isActive = active;
        return this;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public UserEntity setRoles(List<UserRole> roles) {
        this.roles = roles;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public UserEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
