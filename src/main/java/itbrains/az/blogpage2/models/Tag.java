package itbrains.az.blogpage2.models;


import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(name = "article_tags", joinColumns = @JoinColumn(name = "tags", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "articles",referencedColumnName = "id"))
    private Set<Article> article = new HashSet<>();
}
