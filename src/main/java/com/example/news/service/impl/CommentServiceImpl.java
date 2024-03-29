package com.example.news.service.impl;

import com.example.news.exception.EntityNotFoundException;
import com.example.news.model.Comment;
import com.example.news.model.Post;
import com.example.news.model.User;
import com.example.news.repository.CommentRepository;
import com.example.news.service.CommentService;
import com.example.news.service.PostService;
import com.example.news.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final UserService userService;

    private final PostService postService;

    private final CommentRepository commentRepository;
    @Override
    public Comment updateFields(Comment oldEntity, Comment newEntity) {
        if (!Objects.equals(oldEntity.getComment(), newEntity.getComment())) {
            oldEntity.setComment(newEntity.getComment());
        }

        return oldEntity;
    }

    @Override
    public Page<Comment> findAllByPostId(Long postId, Pageable pageable) {
        return commentRepository.findAllByPostId(postId, pageable);

    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public boolean existsByIdAndUserId(Long id, Long userId) {
        return commentRepository.existsByIdAndUserId(id, userId);
    }

    @Override
    @Transactional
    public Comment addComment(Comment comment, Long userId, Long postId) {
        User author = userService.findById(userId);
        Post post = postService.findById(postId);

        author.addComment(comment);
        post.addComment(comment);

        return save(comment);
    }

    @Override
    public Page<Comment> findAll(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    @Override
    public Comment update(Long id, Comment comment) {
        Comment updatedComment = updateFields(findById(id), comment);

        return commentRepository.save(updatedComment);
    }

    @Override
    public Comment findById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("Comment with ID {0} not found!", commentId)
                ));
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }
}

