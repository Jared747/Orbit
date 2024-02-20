package org.mccallum.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.mccallum.dtos.WhatsappResponseHeirarchy.TextDTO;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageDTO {
    private String from;
    private String id;
    private String timestamp;
    private TextDTO text;
    private String field;

    public String getContent(){
        return text != null ? text.getBody() : null;
    }
}
