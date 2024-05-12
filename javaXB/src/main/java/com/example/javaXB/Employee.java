package com.example.javaXB;


import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

@Getter
@Setter

@XmlRootElement(name="Employee")

//When a top level class or an enum type is annotated with the
//XmlRootElement annotation,
//then its value is represented as XML element in an XML document.

public class Employee {

    private String name;

    private Integer age;

    private Integer id;

    private Company company;


    @XmlElement
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @XmlElement(name="MyName")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @XmlAttribute
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
