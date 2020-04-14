package pl.sikora.katarzyna.ShoppingList.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pl.sikora.katarzyna.ShoppingList.model.Product;
import pl.sikora.katarzyna.ShoppingList.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product addProduct(Product product) {
        return this.repository.save(product);
    }

    public List<Product> getAllProducts() {
        return this.repository.findAll();
    }

    public Product getProductById(Long productId) {
        return this.repository.getOne(productId);
    }

    public Object editProduct(Product product, Long product_id) {
        Product existingProduct = this.repository.getOne(product_id);
        BeanUtils.copyProperties(product, existingProduct, "id");
        return this.repository.saveAndFlush(existingProduct);
    }

    public void deleteProduct(Long product_id) {
        this.repository.deleteById(product_id);
    }

    public boolean isProductIdExist(Long product_id) {
        return this.repository.existsById(product_id);
    }
}
