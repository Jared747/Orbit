package org.mccallum.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    private String responseId;
    private MessageDTO message;
    private String content;
}
