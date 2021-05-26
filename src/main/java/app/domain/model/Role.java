package app.domain.model;

import java.util.Objects;

public class Role {
    /**
     * Object oriented class to describe a role within a class.
     */
    private String roleDescription;
    private String roleID;
    private String roleShortname;

    /**
     * Constructor of the Role Class.
     *
     * @param roleDescription-String that the describes the role.
     * @param roleID-String          that identifies the role within the system.
     * @param roleShortname-         Shortname of the string.
     */
    public Role(String roleDescription, String roleID, String roleShortname) {
        this.roleDescription = roleDescription;
        this.roleID = roleID;
        this.roleShortname = roleShortname;
    }

    /**
     * Getter of the roleID of the role.
     *
     * @return String with the role ID.
     */
    public String getRoleID() {
        return roleID;
    }

    /**
     * Setter for the role ID.
     *
     * @param roleID- String with the new RoleID.
     */
    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    /**
     * Getter for the role Description.
     *
     * @return String with the role Description.
     */
    public String getRoleDescription() {
        return roleDescription;
    }

    /**
     * Setter for the role Description.
     *
     * @param roleDescription- String with the new Role Description.
     */
    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    /**
     * New equals method for the Role Class.Checks to see if the object has the same memory adress or not.
     * * Then checks to see if the other object is null.
     * * Then checks to see if the other object has the same class.
     * * Then compares every parameter of the two Roles to see if they are equal and returns true if that is the case.
     *
     * @param o- Another Object
     * @return boolean value telling if the object is equal or not to this a certain Role.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;
        return Objects.equals(roleID, role.roleID) && Objects.equals(roleDescription, role.roleDescription) && Objects.equals(roleShortname, role.roleShortname);
    }

    /**
     * New toString method for the Role class.
     *
     * @return String with a formatted String presenting the Role information.
     */
    @Override
    public String toString() {
        return String.format("%s. %s. %s.", roleID, roleDescription, roleDescription);
    }

    /**
     * Getter for the role shortname.
     *
     * @return String with the role Shortname.
     */
    public String getRoleShortname() {
        return roleShortname;
    }

    /**
     * Setter for the roleShortname.
     *
     * @param roleShortname- String with the new roleShortname.
     */
    public void setRoleShortname(String roleShortname) {
        this.roleShortname = roleShortname;
    }

    /**
     * Checks to see if the role's shortname has more than 15 characters. If that is the case it returns false.
     *
     * @return boolean value telling if the role's shortname has less than 15 characters.
     */
    public boolean validateRole() {
        return this.roleShortname.length() <= 15;
    }
}
