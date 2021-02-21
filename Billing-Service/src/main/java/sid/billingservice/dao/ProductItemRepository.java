package sid.billingservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sid.billingservice.entities.ProductItem;

import java.util.List;

@RepositoryRestResource
public interface ProductItemRepository extends JpaRepository<ProductItem,Long> {
    List<ProductItem> findProductItemByBillId(Long BillingId);
}
