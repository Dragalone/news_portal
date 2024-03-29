package com.example.news.web.controller;

import com.example.news.aop.AccessCheckType;
import com.example.news.aop.Checkable;
import com.example.news.mapper.PostMapper;
import com.example.news.model.Post;
import com.example.news.service.PostService;
import com.example.news.web.model.request.PostFilterRequest;
import com.example.news.web.model.request.UpsertPostRequest;
import com.example.news.web.model.response.ModelListResponse;
import com.example.news.web.model.response.PostListResponse;
import com.example.news.web.model.response.PostResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    private final PostMapper postMapper;

    
    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(postMapper.postToResponse(postService.findById(id)));
    }
    @GetMapping
    public ResponseEntity<ModelListResponse<PostListResponse>> filterBy(@Valid @RequestBody PostFilterRequest request) {
        Page<Post> posts = postService.filterBy(request);
        return ResponseEntity.ok(
                ModelListResponse.<PostListResponse>builder()
                        .totalCount(posts.getTotalElements())
                        .data(posts.stream().map(postMapper::postToListResponse).toList())
                        .build()
        );
    }
    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody UpsertPostRequest request,
                                                   @RequestParam Long userId,
                                                   @RequestParam Long categoryId) {
        Post newPost = postService.addPost(postMapper.upsertRequestToPost(request), userId, categoryId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postMapper.postToResponse(newPost));
    }
    @Checkable(checkBy = AccessCheckType.POST)
    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> updatePost(@RequestBody UpsertPostRequest request,
                                                   @PathVariable Long id,
                                                   @RequestParam(required = false) Long categoryId) {
        Post updatedPost = postService.updatePost(postMapper.upsertRequestToPost(request), id, categoryId);

        return ResponseEntity.ok(postMapper.postToResponse(updatedPost));
    }

    @Checkable(checkBy = AccessCheckType.POST)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostById(@PathVariable Long id) {
        postService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
