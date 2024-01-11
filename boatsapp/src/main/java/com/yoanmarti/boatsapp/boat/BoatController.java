package com.yoanmarti.boatsapp.boat;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class BoatController {

    private final BoatService boatService;

    public BoatController(BoatService boatService) {
        this.boatService = boatService;
    }

    @GetMapping("/boats")
    public ResponseEntity<List<Boat>> getAllBoats() {
        return ResponseEntity.ok(boatService.fetchAllBoats());
    }

    @GetMapping("/boats/{id}")
    public ResponseEntity<Optional<Boat>> getBoatById(@PathVariable Long id) {
        Optional<Boat> boat = boatService.fetchBoatById(id);
        if (!boat.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(boat);
    }

    @PostMapping("/boats")
    public ResponseEntity<Boat> saveBoat(@Valid @RequestBody Boat boat) {

        // TODO: Add field validation
        Boat newBoat = boatService.saveBoat(boat);

        //return new ResponseEntity<>(newBoat, HttpStatus.CREATED);
        return ResponseEntity.ok(newBoat);
    }

    @PutMapping("/boats/{id}")
    public ResponseEntity<Boat> updateBoat(@PathVariable Long id, @Valid @RequestBody Boat boat) {
        Boat updatedBoat = boatService.updateBoat(id, boat);
        return ResponseEntity.ok(updatedBoat);
    }

    @DeleteMapping("/boats/{id}")
    public ResponseEntity<String> deleteBoat(@PathVariable Long id) {
        Boolean res = boatService.deleteBoat(id);

        if (!res) {
            return ResponseEntity.ok("Error");
        }

        return ResponseEntity.ok("Boat deleted successfully");

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
