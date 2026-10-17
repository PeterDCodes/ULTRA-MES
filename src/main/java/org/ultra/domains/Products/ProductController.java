package org.ultra.domains.Products;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @PostMapping
    public Product makeProduct(@RequestParam("skuNumber") String skuNumber, @RequestParam("productName") String productName) {
        return ProductHandler.makeProduct(skuNumber, productName);
    }

    @GetMapping
    public List<Product> getProducts() {
        return ProductHandler.getProducts();
    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable("productId") int productId) {
        return ProductHandler.getProductById(productId);
    }

    @DeleteMapping("/{productId}")
    public void removeProduct(@PathVariable("productId") int productId) {
        ProductHandler.removeProduct(productId);
    }
}
