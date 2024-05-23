package itbrains.az.blogpage2.services;

import itbrains.az.blogpage2.dtos.categorydtos.CategoryCreateDto;
import itbrains.az.blogpage2.dtos.categorydtos.CategoryDto;
import itbrains.az.blogpage2.models.Category;

import java.util.List;

public interface CategoryService {
    void add (CategoryCreateDto categoryCreateDto);
    List<CategoryDto> getAllCategories();
}
