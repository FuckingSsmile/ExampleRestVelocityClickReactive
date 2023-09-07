package ru.home.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.velocity.VelocityContext;
import ru.home.velocity.VelocityContextCreator;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {
    private int id;
    private String name;
    private String surName;
    private String email;
    private int age;
    private String city;
}