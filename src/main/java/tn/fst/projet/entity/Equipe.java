package tn.fst.projet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Equipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;

    @Enumerated(EnumType.STRING)
    private Domain domaine;

    // inverse side ManyToMany
    @ManyToMany(mappedBy = "equipes")
    @JsonIgnore                          // ← add this
    private List<Projet> projets;

    // getters & setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public Domain getDomaine() { return domaine; }
    public void setDomaine(Domain domaine) { this.domaine = domaine; }

    public List<Projet> getProjets() { return projets; }
    public void setProjets(List<Projet> projets) { this.projets = projets; }
}
