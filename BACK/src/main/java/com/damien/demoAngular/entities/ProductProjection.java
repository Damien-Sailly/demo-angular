package com.damien.demoAngular.entities;

import org.springframework.data.rest.core.config.Projection;

/**
 * permet d'integrer des projections
 * c'est a dire de pouvoir choisir les attributs des entities a afficher
 * Ici on designe la prohection par l'annotation de Spring {@link Projection}
 * On lui donne  un nom et un type d'entitie a gerer
 *
 * Ici la projection s'appelle P1 et elle gere le type {@link Product}
 *
 * En requete HTTP, on donne comme parametre projection=NOM_DE_LA_PROJECTION
 * exemple = http://localhost:8080/products?projection=P1
 */
@Projection(name="P1",types = Product.class)
public interface ProductProjection {

    /**
     * On creait une methode pour recuperer le prix des produits
     *
     * @return le prix des produits
     */
    public double getPrice();

    /**
     * On creait une methode pour recuperer le label des produits
     *
     * @return le label des produits
     */
    public String getLabel();

}
