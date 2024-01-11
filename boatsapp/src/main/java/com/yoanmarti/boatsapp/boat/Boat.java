package com.yoanmarti.boatsapp.boat;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "boat")
public class Boat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "Description is mandatory")
    private String description;

    @Column(nullable = true)
    private String image_path;

    public Boat() {

    }

    public Boat(Long id, String name, String description, String image_path) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image_path = image_path;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public void update(Boat updatedBoat) {
        this.name = updatedBoat.name;
        this.description = updatedBoat.description;
        this.image_path = updatedBoat.image_path;
    }
}
