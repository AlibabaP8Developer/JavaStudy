package com.xiaomi.brake;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GPTApiClient {
    private static final String API_URL = "https://api.openai.com/v1/models";

    public String generateResponse(String prompt) throws Exception {
        String apiKey = "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";
        String parameters = "prompt=" + prompt + "&max_tokens=50";

        URL url = new URL(API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Authorization", "Bearer " + apiKey);
        conn.setDoOutput(true);

        conn.getOutputStream().write(parameters.getBytes());

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        return response.toString();
    }

    // 使用示例
    public static void main(String[] args) throws Exception {
        GPTApiClient apiClient = new GPTApiClient();
        String prompt = "你好，";
        String response = apiClient.generateResponse(prompt);
        System.out.println(response);
    }
}
