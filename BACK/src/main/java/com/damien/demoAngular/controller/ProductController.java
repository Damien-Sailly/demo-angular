package com.damien.demoAngular.controller;

import com.damien.demoAngular.entities.Product;
import com.damien.demoAngular.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Le controller des produits
 * On utilisera l'annotation {@link RestController} pour designer la classe
 * comme etant un controller Rest pour Spring
 */
@RestController
public class ProductController {

    /**
     * On utilise Spring IOC grace a l'annotation {@link Autowired} (injection de dependance)
     * A la place d'initialiser le repository, on laisse Spring l'initialiser une fois et on utilise
     * le meme repository dans tout le programme
     */
    @Autowired
    private ProductRepository productRepository;

    /**
     * Permet d'afficher la liste des produits
     * @return la liste de tout les produits
     */
    @GetMapping(value = "/product-list")
    public List<Product> productList() {

        return productRepository.findAll();
    }

    /**
     * Permet d'afficher un produit en le recherchant par son id
     *
     * @param id l'id du produit a trouver
     *           on utilisera l'annotation {@link PathVariable}
     *           pour permettre de designer l'id dans la requete http comme etant l'id du produit a rechercher
     * @return le produit trouve
     */
    @GetMapping(value = "/product-list/{id}")
    public Product productList(@PathVariable(name="id") Long id) {

        return productRepository.findById(id).get();
    }

    /**
     * Permet de mettre a jour un produit designe par son id
     * L'annotation {@link PutMapping} permet de faire appel a la requete http PUT
     * utilisee pour mettre a jour un element
     *
     * @param id l'id du produit a trouver
     *          on utilisera l'annotation {@link PathVariable}
     *          pour permettre de designer l'id dans la requete http comme etant l'id du produit a rechercher
     * @param product le produit a mettre a jour
     *                on utilisera l'annotation {@link RequestBody} pour envoyer le produit dans le body de la requete http
     * @return le produit mise a jour
     */
    @PutMapping(value = "/product-list/{id}")
    public Product update(@PathVariable(name="id") Long id,@RequestBody Product product) {

        // on set l'id du produit a mettre a jour comme etant le l'id disponible dans la requete http
        product.setId(id);

        return productRepository.save(product);
    }

    /**
     * Methode permettant de sauvegarder un nouveau produit
     * Avec l'annotaion {@link PostMapping} on fait appel a la requete http POST
     * @param product le produit a sauvegarder
     * @return le produit sauvegarde
     */
    @PostMapping(value = "/product-list")
    public Product save(@RequestBody Product product) {

        return productRepository.save(product);
    }

    /**
     * Permet de supprimer un produit grace a son id
     * @param id l'id du produit a supprimer
     *          on utilisera l'annotation {@link PathVariable}
     *          pour permettre de designer l'id dans la requete http comme etant l'id du produit a supprimer
     */
    @DeleteMapping(value = "/product-list/{id}")
    public void delete(@PathVariable(name="id") Long id) {

        // on utilise la methode du repository deleteById
        productRepository.deleteById(id);
    }


}
