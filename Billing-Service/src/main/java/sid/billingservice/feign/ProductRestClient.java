package sid.billingservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import sid.billingservice.models.Product;

@FeignClient(name = "INVENTORY-SERVICE")
public interface ProductRestClient {
    @GetMapping("/products/{id}")
    Product getProductById(@PathVariable("id") Long id );

    @GetMapping("/products")
    PagedModel<Product> pageProducts(@RequestParam("page") int page, @RequestParam(value = "size") int size);
}
