package org.mod24task.Entities;

public class Contract {

    private Long id;
    private String name;

    public Contract(){

    }

    public Contract(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Contract(String name){
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
