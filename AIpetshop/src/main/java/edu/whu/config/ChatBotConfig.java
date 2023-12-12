package edu.whu.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "chatbot")
public class ChatBotConfig {

    private Map<String, List<String>> prompts;

    // Getters and Setters
    public Map<String, List<String>> getPrompts() {
        return prompts;
    }

    public void setPrompts(Map<String, List<String>> prompts) {
        this.prompts = prompts;
    }
}

