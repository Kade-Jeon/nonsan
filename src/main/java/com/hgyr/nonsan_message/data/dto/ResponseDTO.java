package com.hgyr.nonsan_message.data.dto;

import com.hgyr.nonsan_message.data.entity.ReceiveMessage;
import com.hgyr.nonsan_message.data.entity.SendMessage;
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

    private boolean archiveStatus;

    public SendMessage toSend() {
        return SendMessage.builder()
                .id(id)
                .sendId(sendId)
                .receiveId(receiveId)
                .title(title)
                .content(content)
                .sendDate(sendDate)
                .readStatus(readStatus)
                .archiveStatus(archiveStatus)
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
                .archiveStatus(archiveStatus)
                .build();
    }
}
