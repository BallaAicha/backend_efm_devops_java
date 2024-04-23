package org.etutoria.backend_android.service;

import org.etutoria.backend_android.entities.Category;

public interface CategorieService {
    Category saveCategory(Category category);
    Category updateCategory(Category category);
    void deleteCategory(Long id);
    Category getCategory(Long id);
    Iterable<Category> getAllCategories();

    Category getCategoryByName(String name);

}
