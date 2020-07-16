package ch.exam.service;

import ch.exam.model.entity.Category;
import ch.exam.model.entity.CategoryName;

public interface CategoryService {
    void initCategories();

    Category findByCategoryName(CategoryName category);
}
