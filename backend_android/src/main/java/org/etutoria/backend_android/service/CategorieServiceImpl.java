package org.etutoria.backend_android.service;

import jakarta.transaction.Transactional;
import org.etutoria.backend_android.dao.CategoryRepository;
import org.etutoria.backend_android.entities.Category;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CategorieServiceImpl implements CategorieService {
    private CategoryRepository categoryRepository;

    public CategorieServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category saveCategory(Category category) {

        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {

        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);


    }

    @Override
    public Category getCategory(Long id) {

        return categoryRepository.findById(id).get();
    }

    @Override
    public Iterable<Category> getAllCategories() {

        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryByName(String name) {

        return categoryRepository.findByName(name);
    }
}
