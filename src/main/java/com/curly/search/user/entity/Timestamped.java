package com.curly.search.user.entity;

import java.time.LocalDateTime;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Timestamped {

    // 처음 만들었을때의 시간
    @CreatedDate
    private LocalDateTime createdAt;

    // 마지막 수정한 시간
    @LastModifiedDate
    private LocalDateTime modifiedAt;
}

