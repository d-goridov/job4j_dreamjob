package ru.job4j.dreamjob.model;

import java.time.LocalDate;
import java.util.Objects;

public class Candidate {
    private int id;
    private String name;
    private String description;
    private LocalDate created;
    private City city;
    private byte[] photo;

    public Candidate() {
    }

    public Candidate(int id, String name, String desc, LocalDate created) {
        this.id = id;
        this.name = name;
        this.description = desc;
        this.created = created;
    }

    public Candidate(int id, String name, String desc, LocalDate created, City city) {
        this.id = id;
        this.name = name;
        this.description = desc;
        this.created = created;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Candidate candidate = (Candidate) o;
        return id == candidate.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
