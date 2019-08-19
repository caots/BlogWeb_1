package com.newdev.caots.entities.admin;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name = "about_me")
@Entity
public class AboutMe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String value;

    private boolean status;
}
