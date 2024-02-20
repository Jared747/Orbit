package org.mccallum.dtos.WhatsappResponseHeirarchy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class WhatsappMessageWrapperDTO {
    private String object;
    private List<EntryDTO> entry;
}
