package com.exam.model;



import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="roles")
public class Role {

    @Id
    private Long roleId;
    private String roleName;

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "role")
    private Set<UserRole> userRoles=new HashSet<>();

    public Role() {

    }

    public Role(Long roleId, String roleName, Set<UserRole> userRoles) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.userRoles = userRoles;
    }

    public Long getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
