package itbrains.az.blogpage2.services.impl;

import itbrains.az.blogpage2.dtos.categorydtos.CategoryCreateDto;
import itbrains.az.blogpage2.dtos.categorydtos.CategoryDto;
import itbrains.az.blogpage2.models.Category;
import itbrains.az.blogpage2.repositories.CategoryRepository;
import itbrains.az.blogpage2.services.CategoryService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void add(CategoryCreateDto categoryCreateDto) {
        Category category = modelMapper.map(categoryCreateDto,Category.class);
        categoryRepository.save(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<CategoryDto> categories = categoryRepository.findAll().stream().map(category -> modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
        return categories;
    }
}
