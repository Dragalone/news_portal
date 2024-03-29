package com.example.news.service.impl;

import com.example.news.exception.EntityNotFoundException;
import com.example.news.model.*;
import com.example.news.repository.PostRepository;
import com.example.news.repository.PostSpecification;
import com.example.news.service.CategoryService;
import com.example.news.service.PostService;
import com.example.news.service.UserService;
import com.example.news.web.model.request.PostFilterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final UserService userService;

    private final CategoryService categoryService;

    private final PostRepository postRepository;
    @Override
    public Post updateFields(Post oldEntity, Post newEntity) {
        if (StringUtils.hasText(newEntity.getTitle())) {
            oldEntity.setTitle(newEntity.getTitle());
        }
        if (StringUtils.hasText(newEntity.getText())) {
            oldEntity.setText(newEntity.getText());
        }
        if (newEntity.getCategory() != null &&
                !Objects.equals(oldEntity.getCategory().getId(), newEntity.getCategory().getId())) {
            oldEntity.setCategory(newEntity.getCategory());
        }
        if (newEntity.getAuthor() != null &&
                !Objects.equals(oldEntity.getAuthor().getId(), newEntity.getAuthor().getId())) {
            oldEntity.setAuthor(newEntity.getAuthor());
        }
        return oldEntity;
    }

    @Override
    @Transactional
    public Post addPost(Post post, Long userId, Long categoryId) {
        User author = userService.findById(userId);
        Category category = categoryService.findById(categoryId);

        author.addPost(post);
        category.addPost(post);

        return save(post);
    }

    @Override
    @Transactional
    public Post updatePost(Post post, Long id, Long categoryId) {
        if (categoryId != null) {
            Category category = categoryService.findById(categoryId);
            post.setCategory(category);
        }

        return update(id, post);
    }

    @Override
    public Page<Post> findAllByCategoryId(Long categoryId, Pageable pageable) {
        return postRepository.findAllByCategoryId(categoryId, pageable);
    }

    @Override
    public Page<Post> findAllByAuthorId(Long userId, Pageable pageable) {
        return postRepository.findAllByAuthorId(userId, pageable);
    }

    @Override
    public Page<Post> filterBy(PostFilterRequest filter) {
        return postRepository.findAll(
                PostSpecification.withFilter(filter),
                filter.getPagination().pageRequest()
        );
    }

    @Override
    public boolean existsByIdAndAuthorId(Long id, Long authorId) {
        return postRepository.existsByIdAndAuthorId(id, authorId);
    }

    @Override
    public Post findById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("Post with ID {0} not found!", postId)
                ));
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post update(Long id, Post post) {

        Post updatedPost = updateFields(findById(id), post);

        return postRepository.save(updatedPost);
    }

    @Override
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }
}
