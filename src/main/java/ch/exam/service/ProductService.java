package ch.exam.service;

import ch.exam.model.service.ProductServiceModel;
import ch.exam.model.view.ProductViewModel;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void addProduct(ProductServiceModel productServiceModel);

    List<ProductViewModel> getAllProductsByCategory(String category);

    BigDecimal getTotalSum();

    void buyAll();

    void buyProductById(String id);
}
