/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Dog;
import entities.Owner;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;

public class OwnerDTO {

    private Integer id;
    private String name;
    private String address;
    private String address2;
    private int phone;
    private List<DogDTO> dogdtos;

    public OwnerDTO(String name, String address, String address2, int phone) {
        this.name = name;
        this.address = address;
        this.address2 = address2;
        this.phone = phone;
    }
    
        public OwnerDTO(String name, String address, String address2, int phone, List<DogDTO> dogs) {
        this.name = name;
        this.address = address;
        this.address2 = address2;
        this.phone = phone;
        this.dogdtos = dogs;
    }


    public OwnerDTO(Owner owner) {
        this.name = owner.getName();
        this.id = owner.getId();
        this.address = owner.getAddress();
        this.address2 = owner.getAddress2();
        this.phone = owner.getPhone();
    }
    
    
    public static List<OwnerDTO> getDtos(List<Owner> owners) {
        List<OwnerDTO> ownerdtos = new ArrayList();
        owners.forEach(owner -> ownerdtos.add(new OwnerDTO(owner)));
        return ownerdtos;
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

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    
    public List<DogDTO> getDogdtos() {
        return dogdtos;
    }

    public void setDogdtos(List<DogDTO> dogdtos) {
        this.dogdtos = dogdtos;
    }

   
    

}
