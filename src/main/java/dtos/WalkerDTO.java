/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Walker;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WalkerDTO {

    private Integer id;
    private String name;
    private String address;
    private int phone;

    public WalkerDTO(String name, String address, int phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public WalkerDTO(Walker walker) {
        this.id = walker.getId();
        this.name = walker.getName();
        this.address = walker.getAddress();
        this.phone = walker.getPhone();
    }

    public static List<WalkerDTO> getDtos(List<Walker> walkers) {
        List<WalkerDTO> walkerdtos = new ArrayList();
        walkers.forEach(walker -> walkerdtos.add(new WalkerDTO(walker)));
        return walkerdtos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.address);
        hash = 67 * hash + this.phone;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WalkerDTO other = (WalkerDTO) obj;
        if (this.phone != other.phone) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
