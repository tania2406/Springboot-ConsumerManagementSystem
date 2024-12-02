package com.example.Springboot_ConsumerManagement.Repository;

import com.example.Springboot_ConsumerManagement.Entity.Consumer;
import org.hibernate.id.IntegralDataTypeHolder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {


    List<Consumer> findByEmail(String email);


    List<Consumer> findByFnameAndLname(String fname, String lname);

    List<Consumer> findByPhoneno(String phoneno);
}
