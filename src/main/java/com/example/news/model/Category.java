package com.example.news.model;
import jakarta.persistence.*;
import lombok.experimental.FieldNameConstants;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@FieldNameConstants
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Post> posts = new ArrayList<>();

    public void addPost(Post post) {
        post.setCategory(this);
        posts.add(post);
    }

}

