package com.damien.demoAngular;

import com.damien.demoAngular.entities.Product;
import com.damien.demoAngular.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

/**
 * Classe de lancement de l'application Spring
 * l'implementation de CommandLineRunner permet de redefinir la methode Run de Spring
 */
@SpringBootApplication
@Slf4j
public class DemoAngularApplication implements CommandLineRunner {

    // permet de recuperer le productrepository et avoir les methode de gestion de l'entitie product dans la bdd
    @Autowired
    private ProductRepository productRepository;

    // objet permettant de configurer Spring data rest
    @Autowired
    private RepositoryRestConfiguration restConfiguration;

    // la methode de lancement de l'application Spring
    public static void main(String[] args) {
        SpringApplication.run(DemoAngularApplication.class, args);
    }

    /**
     * La methode run est lancee par Spring lorsque tout les repositories et les entities ont ete initialisees
     * Ici, elle nous permet de creer des produits pour tester la bdd
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        // Methode permettant d'afficher l'id des produits lorsqu'on les recupere en JSON
        restConfiguration.exposeIdsFor(Product.class);

        // on ajout les produits a la bdd pour les tests
        productRepository.save(new Product(null,"Ordinateur HP",1600,3));
        productRepository.save(new Product(null,"Smartphone Samsung",600,1));
        productRepository.save(new Product(null,"Ordinateur ASUS",1000,5));

        // on les affiche
        productRepository.findAll().forEach( p ->{
            log.info(p.toString());
        });
    }
}
