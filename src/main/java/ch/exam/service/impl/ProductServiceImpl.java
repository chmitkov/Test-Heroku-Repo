package ch.exam.service.impl;

import ch.exam.model.entity.Product;
import ch.exam.model.service.ProductServiceModel;
import ch.exam.model.view.ProductViewModel;
import ch.exam.repository.ProductRepository;
import ch.exam.service.CategoryService;
import ch.exam.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addProduct(ProductServiceModel productServiceModel) {
        Product product = this.modelMapper.map(productServiceModel, Product.class);

        product.setCategory(this.categoryService.findByCategoryName(
                productServiceModel.getCategory().getCategory()));

        this.productRepository.saveAndFlush(product);
    }

    @Override
    public List<ProductViewModel> getAllProductsByCategory(String category) {
        return this.productRepository
                .findAll()
                .stream()
                .filter(product -> product.getCategory().getCategory().name().equals(category))
                .map(product -> this.modelMapper.map(product, ProductViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal getTotalSum() {
        return this.productRepository
                .findAll()
                .stream()
                .map(Product::getPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    @Override
    public void buyAll() {
        this.productRepository
                .deleteAll();
    }

    @Override
    public void buyProductById(String id) {
        this.productRepository
                .deleteById(id);
    }
}
