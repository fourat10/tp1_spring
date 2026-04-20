package tn.fst.projet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class ProjetDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String technologie;
    private Long cout;

    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    // OWNER SIDE OneToOne
    @OneToOne
    @JoinColumn(name ="projet_id")
    @JsonIgnore                          // ← add this
    private Projet projet;

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getTechnologie() { return technologie; }
    public void setTechnologie(String technologie) { this.technologie = technologie; }

    public Long getCout() { return cout; }
    public void setCout(Long cout) { this.cout = cout; }

    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }

    public Projet getProjet() { return projet; }
    public void setProjet(Projet projet) { this.projet = projet; }

}
