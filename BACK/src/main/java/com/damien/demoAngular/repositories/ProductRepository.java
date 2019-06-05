package com.damien.demoAngular.repositories;

import com.damien.demoAngular.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

/**
 * Repository des entities {@link Product}
 *
 * Avec l'annotation {@link CrossOrigin}
 * On dit a l'application que si une application provenant d'un autre domaine essaye d'acceder a l'application rest
 * On lui donne l'autorisation
 * On peut specifier les noms de domaines autorises a acceder a cette api reste en les mettant dans les parametres de l'annotation
 */

@CrossOrigin("*")
@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {

    // recherche par mot clef
    // en URL -> http://localhost:8080/products/search/byLabelPage?kw=HP
    @RestResource(path = "/byLabel")
    public List<Product> findByLabelContains(@Param("kw") String label);


    /**
     * recherche par mot clef et par page
     * en URL -> http://localhost:8080/products/search/byLabelPage?kw=HP&page=1&size=3
     * @param label la designation du produits
     * @param pageable permet de set la page et le nombre de produits
     * @return une page de produits
     */
    @RestResource(path = "/byLabelPage")
    public Page<Product> findByLabelContains(@Param("kw") String label, Pageable pageable);
}
