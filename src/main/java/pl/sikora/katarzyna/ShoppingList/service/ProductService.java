package pl.sikora.katarzyna.ShoppingList.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pl.sikora.katarzyna.ShoppingList.model.Product;
import pl.sikora.katarzyna.ShoppingList.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product addProduct(Product product) {
        product.setName(product.getName().toLowerCase());
        if (!isProductNameExist(product.getName())) {
            this.repository.save(product);
        }
        return this.repository.getProductByName(product.getName());
    }

    public Product editProduct(Product product, Long product_id) {
        Product existingProduct = this.repository.getOne(product_id);
        BeanUtils.copyProperties(product, existingProduct, "id");
        return this.repository.saveAndFlush(existingProduct);
    }

    public void deleteProduct(Long product_id) {
        this.repository.deleteById(product_id);
    }

    public List<Product> getAllProducts() {
        return this.repository.findAll();
    }

    public Product getProductById(Long productId) {
        return this.repository.getOne(productId);
    }

    public Product getProductByName(String productName){
        return this.repository.getProductByName(productName);
    }

    public boolean isProductNameExist(String productName) {
        if (productName != null) {
            return this.repository.existsProductByName(productName);
        }
        return false;
    }

    public boolean isProductIdExist(Long product_id) {
        if (product_id != null) {
            return this.repository.existsById(product_id);
        }
        return false;
    }
}
