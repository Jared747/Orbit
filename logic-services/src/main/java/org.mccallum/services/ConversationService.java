package org.mccallum.services;

import org.mccallum.Constants;
import org.mccallum.dtos.ConversationDTO;
import org.mccallum.dtos.UserDTO;
import org.mccallum.entities.ConversationEntity;
import org.mccallum.enums.ConversationStatus;
import org.mccallum.mappers.ConversationMapper;
import org.mccallum.repositories.ConversationRepository;
import org.mccallum.repositories.MessageRepository;
import org.mccallum.repositories.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ConversationService {

    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;
    private final ResponseRepository responseRepository;


    @Autowired
    public ConversationService(ConversationRepository conversationRepository,
                               MessageRepository messageRepository,
                               ResponseRepository responseRepository)
            throws FileNotFoundException
    {
        this.conversationRepository = conversationRepository;
        this.messageRepository = messageRepository;
        this.responseRepository = responseRepository;
    }

    public ConversationDTO startConversation(UserDTO user) {
        String uniqueConversationId = generateUniqueId();

        ConversationDTO conversation = ConversationDTO.builder()
                .conversationId(uniqueConversationId)
                .user(user)
                .startTime(LocalDateTime.now())
                .status(ConversationStatus.ACTIVE)
                .build();

        ConversationEntity conversationEntity = ConversationMapper.convertDtoToEntity(conversation);

        conversationRepository.save(conversationEntity);

        return conversation;
    }

    private String generateUniqueId() {
        return UUID.randomUUID().toString();
    }


    public void sendMessage(String phoneNumber, String messageContent) {
        String url = Constants.FACEBOOK_URL;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(Constants.BEARER_AUTH_TOKEN);

        String requestJson = constructMessagePayload(phoneNumber, messageContent);

        HttpEntity<String> request = new HttpEntity<>(requestJson, headers);

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            System.out.println("WhatsApp API response: " + response.getBody());
        } catch (RestClientException e) {
            System.err.println("Error sending WhatsApp message: " + e.getMessage());
        }
    }

    private String constructMessagePayload(String phoneNumber, String messageContent) {
        return "{"
                + "\"to\":\"" + phoneNumber + "\","
                + "\"type\":\"text\","
                + "\"text\":{\"body\":\"" + messageContent + "\"}"
                + "}";
    }

    public void sendTemplateMessage(String phoneNumber, String templateName) {
        String url = Constants.FACEBOOK_URL;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(Constants.BEARER_AUTH_TOKEN);

        String requestJson = constructTemplateMessagePayload(phoneNumber, templateName, new HashMap<>()); // Assuming no parameters for simplicity

        HttpEntity<String> request = new HttpEntity<>(requestJson, headers);

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            System.out.println("WhatsApp API response: " + response.getBody());
        } catch (RestClientException e) {
            System.err.println("Error sending WhatsApp template message: " + e.getMessage());
        }
    }

    private String constructTemplateMessagePayload(String phoneNumber, String templateName, Map<String, String> parameters) {
        // Simplified payload construction for a template message. Adjust based on actual needs.
        String payload = "{" +
                "\"messaging_product\": \"whatsapp\"," +
                "\"to\": \"" + phoneNumber + "\"," +
                "\"type\": \"template\"," +
                "\"template\": {" +
                "\"name\": \"" + templateName + "\"," +
                "\"language\": {\"code\": \"en\"}" +
                "}}";

        return payload;
    }




}

