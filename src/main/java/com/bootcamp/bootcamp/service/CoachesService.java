package com.bootcamp.bootcamp.service;

import com.bootcamp.bootcamp.model.Coach;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Service
public class CoachesService {


    public List<Coach> getCoaches(){

        Coach coach1 = new Coach();
        coach1.setId(1);
        coach1.setName("Witold");
        coach1.setSurname("Dorań");
        coach1.setSalary(3500);

        Coach coach2 = new Coach();
        coach2.setId(2);
        coach2.setName("Stanisław");
        coach2.setSurname("Skarzyński");
        coach2.setSalary(5500);

        Coach coach3 = new Coach();
        coach3.setId(3);
        coach3.setName("Marek");
        coach3.setSurname("Rowar");
        coach3.setSalary(2500);

        List<Coach> list = new LinkedList<>();

        list.add(coach1);
        list.add(coach2);
        list.add(coach3);


        return list;



    }
}
