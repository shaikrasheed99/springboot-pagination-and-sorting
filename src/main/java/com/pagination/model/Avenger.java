package com.pagination.model;

import javax.persistence.*;

@Entity
@Table(name = "avengers")
public class Avenger {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String introducedInMovie;
    private int introducedInYear;

    public Avenger() {
    }

    public Avenger(String name, String introducedInMovie, int introducedInYear) {
        this.name = name;
        this.introducedInMovie = introducedInMovie;
        this.introducedInYear = introducedInYear;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIntroducedInMovie() {
        return introducedInMovie;
    }

    public int getIntroducedInYear() {
        return introducedInYear;
    }

    @Override
    public String toString() {
        return "Avenger{" + "id=" + id + ", name='" + name + '\'' + ", introducedInMovie='" + introducedInMovie + '\'' + ", introducedInYear=" + introducedInYear + '}';
    }
}
