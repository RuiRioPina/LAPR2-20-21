package app.domain.store;


import app.domain.model.Role;

import java.util.ArrayList;
import java.util.List;

public class RoleStore {
    private List<Role> lRole;
    public RoleStore(){
        this.lRole= new ArrayList<>();
    }

    public  Role create (String roleDescription,String roleID,String roleShortname){
        return new Role(roleDescription,roleID,roleShortname);
    }

    public List<Role> getlRole() {
        ArrayList<Role> lRole= new ArrayList<>();
        lRole.addAll(this.lRole);
        return lRole;
    }

    public void setlRole(List<Role> lRole) {
        this.lRole = lRole;
    }

    public boolean add(Role r){
        if (r!=null){
            if (!exists(r)&&r.validateRole()){
                return this.lRole.add(r);
            }
        }
        return false;

    }
    public boolean remove(Role r)
    {
        if (r != null)
            return this.lRole.remove(r);
        return false;
    }
    public Role get(int index){
        return lRole.get(index);
    }
    public boolean exists(Role r){
        return this.lRole.contains(r);
    }
}
