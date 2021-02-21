package sid.billingservice.models;

import lombok.Data;

@Data
public class Product {
    Long id ;
    String name ;
    double price ;
    double quantity ;
}
