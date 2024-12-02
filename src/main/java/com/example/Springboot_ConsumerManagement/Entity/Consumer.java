package com.example.Springboot_ConsumerManagement.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

@Entity(name = "Consumer")
public class Consumer
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Consumer Id",unique = true, nullable = false)
    int cid;

    @Column(name = "Consumer_Firstname",nullable = false)
    @NotEmpty(message = "Enter your first name....can't be empty")
    String fname;


    @Column(name ="Consumer_Lastname",nullable = false)
    @NotEmpty(message = "Enter the lastname....can't be empty")
    String lname;

    @Column(name = "Consumer_DOB",nullable = false)
    @NotNull(message = "PLease Enter Your Date Of Birth (yyyy-mm-dd)")
    LocalDate dateofbirth;

    @Column(name = "Phone_no.",unique = true,nullable = false)
    @NotNull(message = "Please Enter Your Phone Number...")
    @Pattern(regexp="^[6-9]{1}[0-9]{9}$",message = "Please Enter a Valid Phone Number")
    String phoneno;

    @Column(name = "Consumer_Email",unique = true,nullable = false)
    @Email(message = "Please Enter a Valid Email....")
    String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("Consumer")
    Address address;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public @NotEmpty(message = "Please Enter Your First Name....cannot be empty") String getFname() {
        return fname;
    }

    public void setFname (@NotEmpty(message = "Please Enter Your First Name....cannot be empty")String fname) {
        this.fname = fname;
    }

    public @NotEmpty(message = "Please Enter Your Last Name....cannot be empty")String getLname() {
        return lname;
    }

    public  @NotNull(message = "Please Enter Your Date of Birth (yyyy-mm-dd)")LocalDate getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth( @NotNull(message = "Please Enter Your Date of Birth (yyyy-mm-dd)")LocalDate dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public void setLname(@NotEmpty(message = "Please Enter Your Last Name....cannot be empty")String lname) {
        this.lname = lname;
    }

    public @NotNull(message = "Please Enter your Phone Number...") @Pattern(regexp = "^[6-9]{1}[0-9]{9}$", message = "Please Enter Valid Phone Number") String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno( @NotNull(message = "Please Enter your Phone Number...") @Pattern(regexp = "^[6-9]{1}[0-9]{9}$", message = "Please Enter Valid Phone Number")String phoneno) {
        this.phoneno = phoneno;
    }

    public @Email(message = "Please Enter Valid Email....") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Please Enter Valid Email....") String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
