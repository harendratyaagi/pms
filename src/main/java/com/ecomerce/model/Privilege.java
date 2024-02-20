package com.ecomerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Privilege implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "privileges", fetch = FetchType.EAGER)
    @JsonBackReference
    private Collection<Role> roles;

    public Privilege(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "";
    }
}
