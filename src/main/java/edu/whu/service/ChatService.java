package edu.whu.service;

import edu.whu.dao.ChatRecordDao;
import edu.whu.domain.ChatRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ChatService {

    private static final Logger logger = LoggerFactory.getLogger(ChatService.class);

    @Autowired
    private ChatRecordDao chatRecordDao;

    @Value("${api.url}")
    private String apiUrl;

    @Value("${api.key}")
    private String apiKey;

    public void chatWithModelAndSave(String userMessage) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        // 构建消息部分
        List<Map<String, String>> messagesList = new ArrayList<>();
        Map<String, String> messageMap = new HashMap<>();
        messageMap.put("role", "user");
        messageMap.put("content", userMessage);
        messagesList.add(messageMap);

        // 构建请求的映射
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("model", "gpt-3.5-turbo");
        requestMap.put("messages", messagesList);

        // 将映射转换为JSON字符串
        String requestJson = objectMapper.writeValueAsString(requestMap);

        logger.info("Request JSON to model: " + requestJson);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, entity, String.class);

        logger.info("Received response from model: " + response.getBody());

        String modelResponse = parseModelResponse(response.getBody());

        saveChatRecord(userMessage, modelResponse);
    }

    private String parseModelResponse(String responseBody) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> responseMap = objectMapper.readValue(responseBody, Map.class);
        List<Map<String, Object>> choices = (List<Map<String, Object>>) responseMap.get("choices");
        if (!choices.isEmpty()) {
            Map<String, String> message = (Map<String, String>) choices.get(0).get("message");
            return message.get("content");
        }
        return null;
    }

    private void saveChatRecord(String userMessage, String modelResponse) {
        ChatRecord record = new ChatRecord();
        record.setUserMessage(userMessage);
        record.setModelResponse(modelResponse);
        chatRecordDao.insert(record);
    }
}
