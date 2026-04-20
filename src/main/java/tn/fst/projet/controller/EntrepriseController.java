package tn.fst.projet.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.fst.projet.entity.Entreprise;
import tn.fst.projet.service.EntrepriseService;

import java.util.List;

@RestController
@RequestMapping("/api/entreprises")
@Tag(name = "Entreprise", description = "Entreprise management APIs")
public class EntrepriseController {

    @Autowired
    private EntrepriseService entrepriseService;

    @GetMapping
    @Operation(summary = "Get all entreprises")
    public ResponseEntity<List<Entreprise>> getAll() {
        return ResponseEntity.ok(entrepriseService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get entreprise by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Entreprise found"),
            @ApiResponse(responseCode = "404", description = "Entreprise not found")
    })
    public ResponseEntity<Entreprise> getById(@PathVariable Long id) {
        return entrepriseService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create a new entreprise")
    @ApiResponse(responseCode = "201", description = "Entreprise created")
    public ResponseEntity<Entreprise> create(@RequestBody Entreprise entreprise) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(entrepriseService.create(entreprise));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an entreprise")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Entreprise updated"),
            @ApiResponse(responseCode = "404", description = "Entreprise not found")
    })
    public ResponseEntity<Entreprise> update(@PathVariable Long id, @RequestBody Entreprise entreprise) {
        try {
            return ResponseEntity.ok(entrepriseService.update(id, entreprise));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an entreprise")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Entreprise deleted"),
            @ApiResponse(responseCode = "404", description = "Entreprise not found")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            entrepriseService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search/nom/{nom}")
    @Operation(summary = "Search entreprises by nom")
    public ResponseEntity<List<Entreprise>> searchByNom(@PathVariable String nom) {
        return ResponseEntity.ok(entrepriseService.findByNomContaining(nom));
    }

    @GetMapping("/search/adresse/{adresse}")
    @Operation(summary = "Search entreprises by adresse")
    public ResponseEntity<List<Entreprise>> searchByAdresse(@PathVariable String adresse) {
        return ResponseEntity.ok(entrepriseService.findByAdresse(adresse));
    }

    @GetMapping("/with-equipes")
    @Operation(summary = "Get entreprises that have equipes")
    public ResponseEntity<List<Entreprise>> getWithEquipes() {
        return ResponseEntity.ok(entrepriseService.findEntreprisesWithEquipes());
    }
}