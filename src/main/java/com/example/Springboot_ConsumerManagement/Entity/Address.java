package com.example.Springboot_ConsumerManagement.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity(name = "Address")
public class Address
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "Address_id",unique = true,nullable = false)
    int aid;
    @Column(name = "Address_City",nullable = false)
    @NotEmpty(message = "Please Enter The City Of the Consumer....City Can't Be Empty")
    String city;

    @Column(name = "Address_Street",nullable = false)
    @NotEmpty(message = "Please Enter The Street Of The Consumer.... Street can't Be Empty")
    String street;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("address")
    Consumer consumer;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public  @NotEmpty(message = "Please Enter The City Of the Consumer....City Can't Be Empty")String getCity() {
        return city;
    }

    public void setCity( @NotEmpty(message = "Please Enter The City Of the Consumer....City Can't Be Empty")String city) {
        this.city = city;
    }

    public  @NotEmpty(message = "Please Enter The Street Of the Consumer....Street Can't Be Empty") String getStreet() {
        return street;
    }

    public void setStreet( @NotEmpty(message = "Please Enter The Street Of the Consumer....Street Can't Be Empty")String street) {
        this.street = street;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }
}
