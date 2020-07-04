package pl.sikora.katarzyna.ShoppingList.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sikora.katarzyna.ShoppingList.model.Product;
import pl.sikora.katarzyna.ShoppingList.service.ProductService;

import javax.xml.bind.ValidationException;
import java.util.List;

//TODO add methods after join with recipes table

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://foodstuff.sikorakatarzyna.pl", "http://www.foodstuff.sikorakatarzyna.pl"})
public class ProductController {

    private final ProductService service;

    @Autowired
    ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return this.service.getAllProducts();
    }

    //    @GetMapping("/products/{product_id}")
    public Object getProduct(@PathVariable Long product_id) {
        if (this.service.isProductIdExist(product_id)) {
            return this.service.getProductById(product_id);
        } else {
            return new ResponseEntity(product_id, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/product")
    public Product addProduct(@RequestBody Product product) {
        System.out.println(product.toString());
        System.out.println(this.service.addProduct(product));
        return this.service.addProduct(product);
    }

    @PutMapping("/products/{product_id}")
    public ResponseEntity<Product> editProduct(@RequestBody Product product, @PathVariable Long product_id) throws ValidationException {
        if (this.service.isProductIdExist(product_id)) {
            return new ResponseEntity(this.service.editProduct(product, product_id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(product, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/product/{product_id}")
    public void deleteProduct(@PathVariable Long product_id) throws ValidationException {
        if (this.service.isProductIdExist(product_id)) {
            this.service.deleteProduct(product_id);
        } else {
            throw new ValidationException("No user With this ID");
        }
    }
}
