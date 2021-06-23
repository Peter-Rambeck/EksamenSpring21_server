/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Dog;
import entities.Owner;
import entities.Walker;
import java.util.ArrayList;
import java.util.List;

public class DogDTO {

    private Integer id;
    private String name;
    private String breed;
    private String image;
    private String gender;
    private String birthdate;
    private Owner owner;

    public DogDTO(String name, String breed, String image, String gender, String birthdate) {
        this.name = name;
        this.breed = breed;
        this.image = image;
        this.gender = gender;
        this.birthdate = birthdate;
    }
    
    public DogDTO(String name, String breed, String image, String gender, String birthdate, Owner owner) {
        this.name = name;
        this.breed = breed;
        this.image = image;
        this.gender = gender;
        this.birthdate = birthdate;
        this.owner = owner;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public DogDTO(Dog dog) {
        this.id = dog.getId();
        this.name = dog.getName();
        this.breed = dog.getBreed();
        this.image = dog.getImage();
        this.gender = dog.getGender();
        this.birthdate = dog.getBirthdate();
        this.owner = dog.getOwner();
    }

    public static List<DogDTO> getDtos(List<Dog> dogs) {
        List<DogDTO> dogdtos = new ArrayList();
        dogs.forEach(dog -> dogdtos.add(new DogDTO(dog)));
        return dogdtos;
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

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "DogDTO{" + "id=" + id + ", name=" + name + ", breed=" + breed + ", image=" + image + ", gender=" + gender + ", birthdate=" + birthdate + ", owner=" + owner + '}';
    }
    
    

}
