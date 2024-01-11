package com.yoanmarti.boatsapp.boat;

import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class BoatService {
    private final BoatRepository boatRepository;

    public BoatService(BoatRepository boatRepository) {
        this.boatRepository = boatRepository;
    }

    public List<Boat> fetchAllBoats() {
        return boatRepository.findAll();
    }

    public Optional<Boat> fetchBoatById(Long id) {
        Optional<Boat> boat = boatRepository.findById(id);
        return boat;
    }

    public Boat saveBoat(Boat boat) {
        Boat newBoat = boatRepository.save(boat);
        return newBoat;
    }

    public Boat updateBoat(Long id, Boat updatedBoat) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        Boat existingBoat = boatRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.valueOf(id))
        );

        existingBoat.update(updatedBoat);
        Boat savedEntity = boatRepository.save(existingBoat);
        return savedEntity;
    }

    public boolean deleteBoat(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        Optional<Boat> boat = boatRepository.findById(id);
        if (boat.isPresent()) {
            boatRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
