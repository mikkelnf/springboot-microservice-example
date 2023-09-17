package com.mnf.common.entity.listener;

import com.mnf.common.entity.UserEntity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDate;
import java.util.UUID;

public class UserEntityListener {
    @PrePersist
    public void onPrePersist(UserEntity entity){
        if(entity.getCreatedDate() == null) entity.setCreatedDate(LocalDate.now());

        if(entity.getId() == null) entity.setId(UUID.randomUUID().toString());

        entity.setIsLogin(0);
    }

    @PreUpdate
    public void onPreUpdate(UserEntity userEntity){
        userEntity.setUpdatedDate(LocalDate.now());
    }
}
