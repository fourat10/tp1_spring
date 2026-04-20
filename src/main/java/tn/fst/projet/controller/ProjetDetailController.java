package tn.fst.projet.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.fst.projet.entity.ProjetDetail;
import tn.fst.projet.service.ProjetDetailService;

import java.util.List;

@RestController
@RequestMapping("/api/projet-details")
@Tag(name = "ProjetDetail", description = "Projet Detail management APIs")
public class ProjetDetailController {

    @Autowired
    private ProjetDetailService projetDetailService;

    // ======================
    // GET ALL
    // ======================
    @GetMapping
    @Operation(summary = "Get all projet details")
    public ResponseEntity<List<ProjetDetail>> getAll() {
        return ResponseEntity.ok(projetDetailService.getAll());
    }

    // ======================
    // GET BY ID
    // ======================
    @GetMapping("/{id}")
    @Operation(summary = "Get a projet detail by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ProjetDetail found"),
            @ApiResponse(responseCode = "404", description = "ProjetDetail not found")
    })
    public ResponseEntity<ProjetDetail> getById(@PathVariable Long id) {
        return projetDetailService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ======================
    // CREATE
    // ======================
    @PostMapping
    @Operation(summary = "Create a new projet detail")
    @ApiResponse(responseCode = "201", description = "ProjetDetail created successfully")
    public ResponseEntity<ProjetDetail> create(@RequestBody ProjetDetail projetDetail) {
        ProjetDetail saved = projetDetailService.create(projetDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // ======================
    // UPDATE
    // ======================
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing projet detail")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ProjetDetail updated"),
            @ApiResponse(responseCode = "404", description = "ProjetDetail not found")
    })
    public ResponseEntity<ProjetDetail> update(@PathVariable Long id, @RequestBody ProjetDetail projetDetail) {
        try {
            return ResponseEntity.ok(projetDetailService.update(id, projetDetail));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ======================
    // DELETE
    // ======================
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a projet detail by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "ProjetDetail deleted"),
            @ApiResponse(responseCode = "404", description = "ProjetDetail not found")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            projetDetailService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ======================
    // SEARCH by technologie LIKE
    // ======================
    @GetMapping("/search/like/{technologie}")
    @Operation(summary = "Search projet details by technologie (LIKE)")
    public ResponseEntity<List<ProjetDetail>> getByTechnologieLike(@PathVariable String technologie) {
        return ResponseEntity.ok(projetDetailService.getByTechnologieLike("%" + technologie + "%"));
    }

    // ======================
    // SEARCH by technologie CONTAINS
    // ======================
    @GetMapping("/search/contains/{technologie}")
    @Operation(summary = "Search projet details by technologie (Contains)")
    public ResponseEntity<List<ProjetDetail>> getByTechnologieContains(@PathVariable String technologie) {
        return ResponseEntity.ok(projetDetailService.getByTechnologieContains(technologie));
    }

    // ======================
    // SEARCH by technologie CONTAINING
    // ======================
    @GetMapping("/search/containing/{technologie}")
    @Operation(summary = "Search projet details by technologie (Containing)")
    public ResponseEntity<List<ProjetDetail>> getByTechnologieContaining(@PathVariable String technologie) {
        return ResponseEntity.ok(projetDetailService.getByTechnologieContaining(technologie));
    }
}