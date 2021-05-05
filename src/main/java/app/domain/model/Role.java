package app.domain.model;

import java.util.Objects;

public class Role {
    private String roleDescription;
    private String roleID;
    public Role( String roleDescription, String roleID){
        this.roleDescription=roleDescription;
        this.roleID=roleID;
    }
    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
    @Override
    public boolean equals(Object o){
        if (this==o){
            return true;
        }
        if (o==null){
            return false;
        }
        if (getClass()!=o.getClass()){
            return false;
        }
        Role role= (Role) o;
        return Objects.equals(roleID,role.roleID) && Objects.equals(roleDescription,role.roleDescription);
    }
    @Override
    public String toString(){
        return String.format(roleID+". "+roleDescription);
    }
}
