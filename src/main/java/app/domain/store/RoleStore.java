package app.domain.store;

import app.domain.model.Employee;
import app.domain.model.Role;

import java.util.ArrayList;

public class RoleStore {
    private ArrayList<Role> lRole= new ArrayList<>();

    public  Role create (String roleDescription,String roleID){
        return new Role(roleDescription,roleID);
    }
    public boolean add(Role r){
        if (r!=null){
            if (!exists(r)){
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
