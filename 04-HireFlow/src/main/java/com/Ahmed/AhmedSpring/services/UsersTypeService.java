package com.Ahmed.AhmedSpring.services;

import com.Ahmed.AhmedSpring.entity.UsersType;
import com.Ahmed.AhmedSpring.repos.UsersTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersTypeService {

    private UsersTypeRepo utr;
    @Autowired
    public UsersTypeService(UsersTypeRepo utr){
        this.utr=utr;
    }

    public List<UsersType> getAll(){

        return utr.findAll();

    }

}
