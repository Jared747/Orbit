package org.mccallum.dtos.WhatsappResponseHeirarchy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChangeDTO {
    private ValueDTO value;
    private String field;
}
