package org.mccallum.dtos.WhatsappResponseHeirarchy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.mccallum.dtos.MessageDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ValueDTO {
    private String messaging_product;
    private List<MessageDTO> messages;

    //DTO for users would be here
}
