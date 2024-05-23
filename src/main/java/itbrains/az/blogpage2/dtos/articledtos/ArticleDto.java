package itbrains.az.blogpage2.dtos.articledtos;


import itbrains.az.blogpage2.dtos.categorydtos.CategoryDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ArticleDto {
    private Long id;
    private String title;
    private String photoUrl;
    private Date createdDate;
    private Date updatedDate;
    private int viewCount;
    private CategoryDto category;
}
