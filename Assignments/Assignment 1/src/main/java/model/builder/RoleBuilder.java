package model.builder;

import model.Role;

public class RoleBuilder {

    private final Role role;

    public RoleBuilder() {
       role = new Role();
    }

    public RoleBuilder setID(Long id){
        role.setId(id);
        return this;
    }

    public RoleBuilder setRole(String name){
        role.setName(name);
        return this;
    }

    public Role build(){
        return role;
    }

}
