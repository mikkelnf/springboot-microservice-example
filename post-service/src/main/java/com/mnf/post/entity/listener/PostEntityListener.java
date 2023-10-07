package com.mnf.post.entity.listener;

import com.mnf.post.entity.PostEntity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

public class PostEntityListener {
    @PrePersist
    public void onPrePersist(PostEntity entity){
        if(entity.getId() == null) entity.setId(UUID.randomUUID().toString());

        if(entity.getCreatedDate() == null) entity.setCreatedDate(LocalDate.now());

        if(entity.getIsActive() == null) entity.setIsActive(1);
    }

    @PreUpdate
    public void onPreUpdate(PostEntity entity){
        entity.setUpdatedDate(LocalDate.now());
    }
}
