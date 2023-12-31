package fr.diginamic.entities;

import jakarta.persistence.*;

import java.time.temporal.ValueRange;
import java.util.Set;

@Entity
@Table
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_ingredient;
    @Column(length = 4096)
    private String nom_ingredient;

    @ManyToMany
    @JoinTable(name = "INGREDIENT_PROD",
            joinColumns = @JoinColumn(name = "ID_INGREDIENT", referencedColumnName = "id_ingredient"),
            inverseJoinColumns = @JoinColumn(name = "ID_PRODUIT", referencedColumnName = "id_produit")
    )
    private Set<Produit> produits;


    public Ingredient() {
    }

    public Ingredient(String nom_ingredient) {
        this.nom_ingredient = nom_ingredient;
    }

    public Ingredient(Integer id_ingredient, String nom_ingredient) {
        this.id_ingredient = id_ingredient;
        this.nom_ingredient = nom_ingredient;
    }

    public Integer getId_ingredient() {
        return id_ingredient;
    }

    public void setId_ingredient(Integer id) {
        this.id_ingredient = id;
    }

    public String getNom_ingredient() {
        return nom_ingredient;
    }

    public void setNom_ingredient(String nom_ingredient) {
        this.nom_ingredient = nom_ingredient;
    }

    public Set<Produit> getProduits() {
        return produits;
    }

    public void setProduits(Set<Produit> produits) {
        if (this.produits != null) {
            for (Produit produit : this.produits) {
                produit.getIngredients().remove(this);
            }
        }
        this.produits = produits;
        if (this.produits != null) {
            for (Produit produit : this.produits) {
                produit.getIngredients().add(this);
            }
        }
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ingredient{");
        sb.append("id_ingredient=").append(id_ingredient);
        sb.append(", nom_ingredient='").append(nom_ingredient).append('\'');
        sb.append(", produits=").append(produits);
        sb.append('}');
        return sb.toString();
    }
}
