package com.example.news.repository;


import com.example.news.model.Category;
import com.example.news.model.Post;
import com.example.news.model.User;
import com.example.news.web.model.request.PostFilterRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public interface PostSpecification {

    static Specification<Post> withFilter(PostFilterRequest filter) {
        return Specification.where(byCategoryName(filter.getCategoryName()))
                .and(byUsername(filter.getUsername()));
    }

    static Specification<Post> byCategoryName(String categoryName) {
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(categoryName)) {
                return null;
            }

            return criteriaBuilder.equal(root.get(Post.Fields.category).get(Category.Fields.name), categoryName);
        };
    }

    static Specification<Post> byUsername(String username) {
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(username)) {
                return null;
            }

            return criteriaBuilder.equal(root.get(Post.Fields.author).get(User.Fields.username), username);
        };
    }

}

