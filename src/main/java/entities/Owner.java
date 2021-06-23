package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "owner")
@NamedQuery(name = "Owner.deleteAllRows", query = "DELETE from Owner")
public class Owner implements Serializable {

    private static final int serialVersionUID = (int) 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "address2")
    private String address2;
    @Column(name = "phone")
    private int phone;

    @OneToMany(mappedBy = "owner",
            cascade = CascadeType.PERSIST)
    @JoinColumn(name = "dog_id")
    private List<Dog> dogs = new ArrayList<>();

    public Owner() {
    }

    public Owner(String name, String address, String address2, int phone) {
        this.name = name;
        this.address = address;
        this.address2 = address2;
        this.phone = phone;
    }

    public Owner(Integer id, String name, String address, String address2, int phone, List<Dog> dogs) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.address2 = address2;
        this.phone = phone;
        this.dogs = dogs;
    }
    
    public void addDog(Dog dog) {
        if (dog != null) {
            this.dogs.add(dog);
            dog.setOwner(this);
        }
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

}
