package app.domain.model;

import java.util.Objects;

public class Role {
    private String roleDescription;
    private String roleID;
    private String roleShortname;
    public Role( String roleDescription, String roleID,String roleShortname){
        this.roleDescription=roleDescription;
        this.roleID=roleID;
        this.roleShortname=roleShortname;
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
        return Objects.equals(roleID,role.roleID) && Objects.equals(roleDescription,role.roleDescription)&& Objects.equals(roleShortname,role.roleShortname);
    }
    @Override
    public String toString(){
        return String.format(roleID+". "+roleDescription+". "+roleShortname+".");
    }

    public String getRoleShortname() {
        return roleShortname;
    }

    public void setRoleShortname(String roleShortname) {
        this.roleShortname = roleShortname;
    }

    public boolean validateRole(){
        if (this.roleShortname.length()>15){
            return false;
        }
        return true;
    }
}
