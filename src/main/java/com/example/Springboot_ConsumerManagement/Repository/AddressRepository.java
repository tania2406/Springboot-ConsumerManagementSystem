package com.example.Springboot_ConsumerManagement.Repository;

import com.example.Springboot_ConsumerManagement.Entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Integer>{

    List<Address> findByCity(String city);
}

    
