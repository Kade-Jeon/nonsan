package com.hgyl.nonsan_message.data.dto;

import com.hgyl.nonsan_message.data.entity.ReceiveMessage;
import com.hgyl.nonsan_message.data.entity.SendMessage;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Component
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {

    private Long id;

    private String sendId;

    private String receiveId;

    private String title;

    private String content;

    private LocalDateTime sendDate;

    private boolean readStatus;

    private boolean deleteStatus;

    public SendMessage toSend() {
        return SendMessage.builder()
                .id(id)
                .sendId(sendId)
                .receiveId(receiveId)
                .title(title)
                .content(content)
                .sendDate(sendDate)
                .readStatus(readStatus)
                .deleteStatus(deleteStatus)
                .build();
    }

    public ReceiveMessage toReceive() {
        return ReceiveMessage.builder()
                .id(id)
                .sendId(sendId)
                .receiveId(receiveId)
                .title(title)
                .content(content)
                .sendDate(sendDate)
                .readStatus(readStatus)
                .deleteStatus(deleteStatus)
                .build();
    }
}
