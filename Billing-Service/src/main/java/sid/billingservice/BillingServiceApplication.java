package sid.billingservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.hateoas.PagedModel;
import sid.billingservice.dao.BillRepository;
import sid.billingservice.dao.ProductItemRepository;
import sid.billingservice.entities.Bill;
import sid.billingservice.entities.ProductItem;
import sid.billingservice.feign.CustomerRestClient;
import sid.billingservice.feign.ProductRestClient;
import sid.billingservice.models.Customer;
import sid.billingservice.models.Product;

import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(BillRepository billRepository, ProductItemRepository productItemRepository,
							ProductRestClient inventoryServiceClient, CustomerRestClient customerRestClient, RepositoryRestConfiguration restConfiguration){

		restConfiguration.exposeIdsFor(Bill.class,ProductItem.class);
		return args -> {

			Customer customer=customerRestClient.findCustomerById(1L);
			Bill bill=billRepository.save(new Bill(null,new Date(),null,null,customer.getId()));
			PagedModel<Product> productPagedModel=inventoryServiceClient.pageProducts(1,3);

			productPagedModel.forEach(p->{

				ProductItem productItem=new ProductItem();
				productItem.setPrice(p.getPrice());
				productItem.setQuantity(1+ new Random().nextInt(100));
				productItem.setBill(bill);
				productItemRepository.save(productItem);

			});

		};

	}
}
