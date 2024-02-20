package org.mccallum.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mccallum.Constants;
import org.mccallum.dtos.WhatsappResponseHeirarchy.ChangeDTO;
import org.mccallum.dtos.WhatsappResponseHeirarchy.EntryDTO;
import org.mccallum.dtos.WhatsappResponseHeirarchy.WhatsappMessageWrapperDTO;
import org.mccallum.services.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/message")
public class MessageController {

    private final ConversationService conversationService;

    @Autowired
    public MessageController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    //Verifying Webhook
    @GetMapping("/receive")
    public ResponseEntity<String> verifyWebhook(@RequestParam("hub.mode") String mode,
                                                @RequestParam("hub.challenge") String challenge,
                                                @RequestParam("hub.verify_token") String verifyToken) {

        if ("subscribe".equals(mode) &&  Constants.VERIFICATION_TOKEN.equals(verifyToken)) {
            System.out.println("Webhook Verified");
            return ResponseEntity.ok(challenge);
        } else {
            System.out.println("Webhook Verification Failed");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Verification failed");
        }
    }

    @PostMapping("/receive")
    public ResponseEntity<String> receiveMessage(@RequestBody String rawMessage) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            WhatsappMessageWrapperDTO wrapper = objectMapper.readValue(rawMessage, WhatsappMessageWrapperDTO.class);

            if (wrapper.getEntry() != null && !wrapper.getEntry().isEmpty()) {
                for (EntryDTO entry : wrapper.getEntry()) {
                    for (ChangeDTO change : entry.getChanges()) {
                        if (change.getValue() != null && change.getValue().getMessages() != null && !change.getValue().getMessages().isEmpty()) {
                            String senderPhoneNumber = change.getValue().getMessages().get(0).getFrom();
                            conversationService.sendTemplateMessage(senderPhoneNumber, "maintenance");
                            System.out.println("Maintenance template message sent to: " + senderPhoneNumber);
                        }
                    }
                }
            }

            return ResponseEntity.ok("Callback received and processed");
        } catch (Exception e) {
            System.err.println("Error parsing incoming message: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to parse message");
        }
    }


}
