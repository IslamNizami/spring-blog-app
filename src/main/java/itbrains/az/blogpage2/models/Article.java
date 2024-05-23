package itbrains.az.blogpage2.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String subTitle;
    @Column(name = "description", length = 10000)
    private String description;
    private String photoUrl;
    private Date createdDate;
    private Date updatedDate;
    private int viewCount;
    private Boolean isDeleted = false;
    private String seoUrl;

    @ManyToOne
    private Category category;

    @ManyToMany
    @JoinTable(name = "article_tags", joinColumns = @JoinColumn(name = "articles", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "tags",referencedColumnName = "id"))
    private Set<Tag> tag = new HashSet<>();

}
