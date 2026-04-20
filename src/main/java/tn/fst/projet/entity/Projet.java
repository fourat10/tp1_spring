package tn.fst.projet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Projet {

    @Id
    private String id;

    private String sujet;

    // OneToOne inverse side
    @OneToOne(mappedBy = "projet", cascade = CascadeType.ALL)
    @JsonIgnore                          // ← add this if ProjetDetail also references back Projet
    private ProjetDetail projetDetail;

    // ManyToMany owner side
    @ManyToMany
    @JoinTable(name = "projet_equipe", joinColumns = @JoinColumn(name = "projet_id"),
    inverseJoinColumns = @JoinColumn(name = "equipe_id"))
    private List<Equipe> equipes;

    // getters & setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getSujet() { return sujet; }
    public void setSujet(String sujet) { this.sujet = sujet; }

    public ProjetDetail getProjetDetail() { return projetDetail; }
    public void setProjetDetail(ProjetDetail projetDetail) { this.projetDetail = projetDetail; }

    public List<Equipe> getEquipes() { return equipes; }
    public void setEquipes(List<Equipe> equipes) { this.equipes = equipes; }
}
