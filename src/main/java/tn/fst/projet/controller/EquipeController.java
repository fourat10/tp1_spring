package tn.fst.projet.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.fst.projet.entity.Equipe;
import tn.fst.projet.service.EquipeService;

import java.util.List;

@RestController
@RequestMapping("/api/equipes")
@Tag(name = "Equipe", description = "Equipe management APIs")
public class EquipeController {

    @Autowired
    private EquipeService equipeService;

    // ======================
    // GET ALL
    // ======================
    @GetMapping
    @Operation(summary = "Get all equipes")
    public ResponseEntity<List<Equipe>> getAll() {
        return ResponseEntity.ok(equipeService.getAll());
    }

    // ======================
    // GET BY ID
    // ======================
    @GetMapping("/{id}")
    @Operation(summary = "Get an equipe by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Equipe found"),
            @ApiResponse(responseCode = "404", description = "Equipe not found")
    })
    public ResponseEntity<Equipe> getById(@PathVariable Integer id) {
        return equipeService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ======================
    // CREATE
    // ======================
    @PostMapping
    @Operation(summary = "Create a new equipe")
    @ApiResponse(responseCode = "201", description = "Equipe created successfully")
    public ResponseEntity<Equipe> create(@RequestBody Equipe equipe) {
        Equipe saved = equipeService.create(equipe);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // ======================
    // UPDATE
    // ======================
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing equipe")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Equipe updated"),
            @ApiResponse(responseCode = "404", description = "Equipe not found")
    })
    public ResponseEntity<Equipe> update(@PathVariable Integer id, @RequestBody Equipe equipe) {
        try {
            return ResponseEntity.ok(equipeService.update(id, equipe));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ======================
    // DELETE
    // ======================
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an equipe by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Equipe deleted"),
            @ApiResponse(responseCode = "404", description = "Equipe not found")
    })
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            equipeService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}