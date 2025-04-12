package com.example.Springboot_ConsumerManagement.Controller;

import com.example.Springboot_ConsumerManagement.Entity.Address;
import com.example.Springboot_ConsumerManagement.Entity.Consumer;
import com.example.Springboot_ConsumerManagement.Exception.*;
import com.example.Springboot_ConsumerManagement.Repository.AddressRepository;
import com.example.Springboot_ConsumerManagement.Repository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ConsumerController
{
    @Autowired
    AddressRepository arepo;
    @Autowired
    ConsumerRepository crepo;
    @GetMapping("/test")
    public String test()
    {
        return " Consumer Management Test";
    }
    @PostMapping("/save")
    public String addConsumer(@RequestBody Consumer csr)
    {
        Consumer existingConsumer = crepo.findById(csr.getCid())
                .orElse(null);
        if(existingConsumer==null)
        {
            csr.getAddress().setConsumer(csr);
            Consumer consumer=crepo.save(csr);
            return "consumer has been added successfully";
        }
        else
            throw new ConsumerAlreadyExistsException(
                    "consumer already exists!!"
            );
    }
    @GetMapping("/all")
    public List<Consumer> all()
    {
        return crepo.findAll();
    }
    @GetMapping("/{cid}")
    public Consumer getConsumer(@PathVariable int id)
    {
        return crepo.findById(id).orElseThrow(
                ()-> new NoSuchElementException("No Consumer Present with id ="+id)
        );
    }
    @PutMapping("/update/{cid}")
    public String updateConsumer(@RequestBody Consumer csr,@PathVariable int cid) {
        Consumer existingConsumer = crepo.findById(csr.getCid()).orElse(null);
        if (existingConsumer == null)
            throw new NoSuchConsumerExistsException("No such Consumer Exists!!");
        else {
            existingConsumer.setAddress(csr.getAddress());
            existingConsumer.setFname(csr.getFname());
            existingConsumer.setLname(csr.getLname());
            existingConsumer.setDateofbirth(csr.getDateofbirth());
            existingConsumer.setEmail(csr.getEmail());
            existingConsumer.setPhoneno(csr.getPhoneno());
            crepo.save(existingConsumer);
            return "Record updated Successfully";
        }
    }
        @DeleteMapping("/del/{cid}")
        public String deleteConsumerData(@PathVariable int Cid)
        {
            crepo.deleteById(cid);
            return "data has been deleted successfully";
    }
   @GetMapping("/both/{fname}/{lname}")
    public List<Consumer> byfirstnameandlastname(@PathVariable String fname,@PathVariable String lname)throws Exception
    {
        List<Consumer>c=crepo.findByFnameAndLname(fname,lname);
        if (c.isEmpty())
        {
            throw new NameNotFoundException();
        }
        return c;
    }
    @GetMapping("/city/{city}")
    public List<Address> byCity(@PathVariable String city)
    {
        List<Address> a=arepo.findByCity(city);
        if(a.isEmpty())
        {
            throw new CityNotFoundException();
        }
        return a;
    }

    @GetMapping("/Email/{email}")
    public List<Consumer> byEmail(@PathVariable String email)
    {
        return crepo.findByEmail(email);
    }
    @GetMapping("/bynumber/{phoneno}")
    public List<Consumer> ByConsumerPhoneno(@PathVariable String phoneno) throws Exception
    {
        List<Consumer> s = crepo.findByPhoneno(phoneno);
        if(s.isEmpty())
        {
            throw new PhoneNumberNotFoundException();
        }
        return s;

    }
}
