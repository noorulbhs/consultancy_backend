package com.altrevo.consultancy.repository;

import com.altrevo.consultancy.entity.BlogPost;
import com.altrevo.consultancy.entity.InMemoryBlogStore;

import java.util.*;

public class BlogInMemoryRepository {
    public BlogPost save(BlogPost blogPost) {
        if (blogPost.getId() == null) {
            blogPost.setId(InMemoryBlogStore.getNextId());
        }
        InMemoryBlogStore.BLOG_POSTS.put(blogPost.getId(), blogPost);
        return blogPost;
    }

    public Optional<BlogPost> findById(Long id) {
        return Optional.ofNullable(InMemoryBlogStore.BLOG_POSTS.get(id));
    }

    public List<BlogPost> findAll() {
        return new ArrayList<>(InMemoryBlogStore.BLOG_POSTS.values());
    }

    public void deleteById(Long id) {
        InMemoryBlogStore.BLOG_POSTS.remove(id);
    }

    public Optional<BlogPost> findBySlug(String slug) {
        return InMemoryBlogStore.BLOG_POSTS.values().stream()
                .filter(post -> slug.equals(post.getSlug()))
                .findFirst();
    }
}

