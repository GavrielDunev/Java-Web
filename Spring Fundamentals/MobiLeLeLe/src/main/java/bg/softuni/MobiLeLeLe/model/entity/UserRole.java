package bg.softuni.MobiLeLeLe.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class UserRole extends BaseEntity{

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }
}
