package com.ilkay.controller;

import com.ilkay.entity.Shark;
import com.ilkay.service.SharkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shark")
public class SharkController {

    private SharkService service;

    private SharkController(SharkService service) {
        this.service = service;
    }

    //Create
    @PostMapping("/create")
    //@RequestBody allows us to pass through an object/data when we make the request
    public ResponseEntity<Shark> createShark(@RequestBody Shark shark) {
        return new ResponseEntity<Shark>(this.service.create(shark), HttpStatus.CREATED);
    }

    //Read All
    @GetMapping("/readAll")
    public ResponseEntity<List<Shark>> readAllSharks() {
        //We return a list because the readAll method in the service class returns a list also
        return new ResponseEntity<>(this.service.readAll(), HttpStatus.OK);
    }


    //Read By Id
    @GetMapping("/readById/{id}")
    //@PathVariable allows us to pass a variable (in this case ID) to the path & service.readById method
    public ResponseEntity<Shark> readById(@PathVariable long id) {
        return new ResponseEntity<>(this.service.readById(id), HttpStatus.OK);
    }

    //Update
    @PutMapping("/update/{id}")
    //update requires both RequestBody and PathVariable as it takes in the id, and also passes through the new object information
    public ResponseEntity<Shark> updateShark(@PathVariable long id, @RequestBody Shark shark) {
        return new ResponseEntity<>(this.service.update(id, shark), HttpStatus.ACCEPTED);
    }

    //Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteShark(@PathVariable long id) {
        //Ternary operator, IF service.delete goes through (deletes the id), return no content, ELSE return not found
        return (this.service.delete(id)) ? new ResponseEntity<Boolean>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
