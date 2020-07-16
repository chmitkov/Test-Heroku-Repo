package ch.exam.model.service;

import ch.exam.model.entity.CategoryName;

public class CategoryServiceModel extends BaseServiceModel{

    private CategoryName category;
    private String description;


    public CategoryServiceModel() {
    }

    public CategoryName getCategory() {
        return category;
    }

    public void setCategory(CategoryName category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
