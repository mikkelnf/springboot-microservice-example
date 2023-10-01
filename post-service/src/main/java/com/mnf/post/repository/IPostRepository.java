package com.mnf.post.repository;

import com.mnf.post.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostRepository extends JpaRepository<PostEntity, String> {
}
