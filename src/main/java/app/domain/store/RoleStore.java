package app.domain.store;


import app.domain.model.Role;

import java.util.ArrayList;
import java.util.List;


public class RoleStore {
    /**
     * Object oriented class used to Store the roles within the company.
     */
    private List<Role> lRole;

    /**
     * Constructor for the Role Store. It Creates an empty list that stores roles.
     */
    public RoleStore(){
        this.lRole= new ArrayList<>();
    }

    /**
     * Creates a new Role using the given role parameters
     * @param roleDescription-String that the describes the role.
     * @param roleID-String that identifies the role within the system.
     * @param roleShortname- Shortname of the string.
     * @return - Object of the Role Class.
     */
    public  Role create (String roleDescription,String roleID,String roleShortname){
        return new Role(roleDescription,roleID,roleShortname);
    }

    /**
     * Getter for the Role List withing
     * @return Arraylist with the stored Roles.
     */
    public List<Role> getlRole() {
        ArrayList<Role> lRole= new ArrayList<>();
        lRole.addAll(this.lRole);
        return lRole;
    }

    /**
     * Setter for the Role List.
     * @param lRole- new List with roles.
     */
    public void setlRole(List<Role> lRole) {
        this.lRole = lRole;
    }

    /**
     * Adds a Role to the list if it does not exist in it and is valid.
     * @param r- Role to be added.
     * @return boolean value to confirm if the Role was added or not.
     */
    public boolean add(Role r){
        if (r!=null){
            if (!exists(r)&&r.validateRole()){
                return this.lRole.add(r);
            }
        }
        return false;

    }

    /**
     * Removes a role within the role list.
     * @param r- Non Null Role.
     * @return boolean value to confirm if the Role was removed or not.
     */
    public boolean remove(Role r)
    {
        if (r != null)
            return this.lRole.remove(r);
        return false;
    }

    /**
     * Uses an index number to get a certain role from the list.
     * @param index- index number of the desired role.
     * @return object of the Role Class.
     */
    public Role get(int index){
        return lRole.get(index);
    }

    /**
     * Checks to see if a role already exists in the Role list.
     * @param r- Role to be checked.
     * @return boolean value telling if the role is in the list or not.
     */
    public boolean exists(Role r){
        return this.lRole.contains(r);
    }
}
