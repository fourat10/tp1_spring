package tn.fst.projet.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.fst.projet.entity.Projet;
import tn.fst.projet.entity.ProjetDetail;
import tn.fst.projet.service.ProjetService;

import java.util.List;

@RestController
@RequestMapping("/api/projets")
@Tag(name = "Projet", description = "Projet management APIs")
public class ProjetController {

    @Autowired
    private ProjetService projetService;

    // ======================
    // GET ALL
    // ======================
    @GetMapping
    @Operation(summary = "Get all projets")
    public ResponseEntity<List<Projet>> getAll() {
        return ResponseEntity.ok(projetService.getAll());
    }

    // ======================
    // GET BY ID
    // ======================
    @GetMapping("/{id}")
    @Operation(summary = "Get a projet by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Projet found"),
            @ApiResponse(responseCode = "404", description = "Projet not found")
    })
    public ResponseEntity<Projet> getById(@PathVariable String id) {
        return projetService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ======================
    // CREATE
    // ======================
    @PostMapping
    @Operation(summary = "Create a new projet")
    @ApiResponse(responseCode = "201", description = "Projet created successfully")
    public ResponseEntity<Projet> create(@RequestBody Projet projet) {
        Projet saved = projetService.create(projet);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // ======================
    // UPDATE
    // ======================
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing projet")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Projet updated"),
            @ApiResponse(responseCode = "404", description = "Projet not found")
    })
    public ResponseEntity<Projet> update(@PathVariable String id, @RequestBody Projet projet) {
        try {
            return ResponseEntity.ok(projetService.update(id, projet));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ======================
    // DELETE
    // ======================
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a projet by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Projet deleted"),
            @ApiResponse(responseCode = "404", description = "Projet not found")
    })
    public ResponseEntity<Void> delete(@PathVariable String id) {
        try {
            projetService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ======================
    // Page 4 - GET details by tech
    // ======================
    @GetMapping("/details/tech/{tech}")
    @Operation(summary = "Get projet details by technology")
    public ResponseEntity<List<ProjetDetail>> getDetailsByTech(@PathVariable String tech) {
        return ResponseEntity.ok(projetService.getByTechnologie(tech));
    }

    // ======================
    // Page 5 - GET projets by tech
    // ======================
    @GetMapping("/tech/{tech}")
    @Operation(summary = "Get projets by technology")
    public ResponseEntity<List<Projet>> getProjetsByTech(@PathVariable String tech) {
        return ResponseEntity.ok(projetService.getProjetsByTechnologie(tech));
    }

    // ======================
    // Page 6 - GET projets by equipe
    // ======================
    @GetMapping("/equipe/{id}")
    @Operation(summary = "Get projets by equipe ID")
    public ResponseEntity<List<Projet>> getProjetsByEquipe(@PathVariable Integer id) {
        return ResponseEntity.ok(projetService.getProjetsByEquipe(id));
    }

    // ======================
    // Page 7 - GET projets by equipe with description
    // ======================
    @GetMapping("/equipe/{id}/description")
    @Operation(summary = "Get projets by equipe ID that have a description")
    public ResponseEntity<List<Projet>> getProjetsByEquipeWithDesc(@PathVariable Integer id) {
        return ResponseEntity.ok(projetService.getProjetsByEquipeWithDescription(id));
    }
}