package com.xiaomi.brake;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;

import java.util.List;

public class GPTDemo01 {
    public static void main(String[] args) {
        OpenAiService service = new OpenAiService(Constants.OPENAI_TOKEN,5000);

        CompletionRequest request = CompletionRequest.builder()
                .model("text-davinci-003") // 使用模型
                .prompt("请帮我写一首情诗") // 提供的提示
                .temperature(80.0) // 提现采样的波动性
                .maxTokens(1000) // token大小设置
                .topP(10.0) // 情绪采样 [0,1]
                .frequencyPenalty(80.0) // 频率处罚的次数
                .presencePenalty(80.0) // 重复的处罚次数
                .build();

        List<CompletionChoice> list = service.createCompletion(request).getChoices();
        list.forEach(System.out::println);
    }
}
