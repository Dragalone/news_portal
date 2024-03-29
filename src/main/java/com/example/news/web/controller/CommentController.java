package com.example.news.web.controller;

import com.example.news.aop.AccessCheckType;
import com.example.news.aop.Checkable;
import com.example.news.mapper.CommentMapper;
import com.example.news.model.Comment;
import com.example.news.service.CommentService;
import com.example.news.web.model.request.PaginationRequest;
import com.example.news.web.model.request.UpsertCommentRequest;
import com.example.news.web.model.response.CommentResponse;
import com.example.news.web.model.response.ModelListResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    private final CommentMapper commentMapper;

    @GetMapping
    public ResponseEntity<ModelListResponse<CommentResponse>> getByPost(@Valid PaginationRequest request,
                                                                        @RequestParam Long postId) {
        Page<Comment> comments = commentService.findAllByPostId(postId, request.pageRequest());

        return ResponseEntity.ok(
                ModelListResponse.<CommentResponse>builder()
                        .totalCount(comments.getTotalElements())
                        .data(comments.stream().map(commentMapper::commentToResponse).toList())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(commentMapper.commentToResponse(commentService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<CommentResponse> addComment(@RequestBody UpsertCommentRequest request,
                                                      @RequestParam Long postId,
                                                      @RequestParam Long userId) {
        Comment newComment = commentService.addComment(commentMapper.upsertRequestToComment(request), userId, postId);

        return ResponseEntity.ok(commentMapper.commentToResponse(newComment));
    }

    @Checkable(checkBy = AccessCheckType.COMMENT)
    @PutMapping("/{id}")
    public ResponseEntity<CommentResponse> updateComment(@RequestBody UpsertCommentRequest request,
                                                         @PathVariable Long id) {
        Comment updatedComment = commentService.update(id, commentMapper.upsertRequestToComment(request));

        return ResponseEntity.ok(commentMapper.commentToResponse(updatedComment));
    }


    @DeleteMapping("/{id}")
    @Checkable(checkBy = AccessCheckType.COMMENT)
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        commentService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
