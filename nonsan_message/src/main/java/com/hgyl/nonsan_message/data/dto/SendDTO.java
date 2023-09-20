package com.hgyl.nonsan_message.data.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 쪽지 작성
 * sendId
 * receiveId
 * title
 * content
 * sendDate
 * readStatus
 * deleteStatus
 * **/

@Getter
@Setter
@ToString
@Component
public class SendDTO {


    private String sendId;

    @NonNull
    private String receiveId;

    @NonNull
    private String title;

    @NonNull
    private String content;

    @CreationTimestamp
    private LocalDateTime sendDate;

    private boolean readStatus;

    private boolean deleteStatus;
}
