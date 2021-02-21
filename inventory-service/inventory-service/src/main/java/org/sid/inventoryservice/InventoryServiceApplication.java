package org.sid.inventoryservice;
import org.sid.inventoryservice.entities.Product;
import org.sid.inventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository,
                            RepositoryRestConfiguration restConfiguration) {

       restConfiguration.exposeIdsFor(Product.class);
        return args -> {

            productRepository.save(new Product(null, "Ordinateur", 70000, 10));

            productRepository.save(new Product(null, "Imprimante", 80000, 5));

            productRepository.save(new Product(null, "SmartTv", 9000, 4));

            productRepository.findAll().forEach(p -> {
                System.out.println(p.getName() + "   " + p.getPrice());
            });
        };
    }
}
