package com.example.HomeResidents.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "home")
public class Home {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column
    private String address;

    @OneToOne
    private Person owner;

    @OneToMany(mappedBy = "home")
    private List<Person> residents;

    public Home() {
    }

    public Home(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public List<Person> getResidents() {
        return residents;
    }

    public void setResidents(List<Person> residents) {
        this.residents = residents;
    }

    @Override
    public String toString() {
        return "Home{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", owner=" + owner +
                ", residents=" + residents +
                '}';
    }
}
