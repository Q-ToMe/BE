package com.example.qtome_be.config;

import com.slack.api.Slack;
import com.slack.api.model.Attachment;
import com.slack.api.model.Field;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.RequestInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.slack.api.webhook.WebhookPayloads.payload;

@Aspect
@Component
//@Profile(value = {"dev", "prod"})
public class SlackNotificationAspect {
    private final Environment env;
    @Value("${slack.webhook}")
    private String SLACK_WEBHOOK_URL;

    private final Slack slackClient = Slack.getInstance();

    public SlackNotificationAspect(Environment env) {
        this.env = env;
    }

    @Pointcut("@annotation(com.example.qtome_be.config.SlackNotification)")
    private void slack(){};

    @Around("slack()")
    public Object slackNotificate(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("aop start");


        String title = "Slack 메시지 전송 테스트";
        HashMap<String, String> data = new HashMap<>();
        data.put("테스트1", "테스트1 내용");
        data.put("테스트2", "테스트2 내용");

        sendMessage(title, data);

        Object result= joinPoint.proceed();
        return result;
    }


    /**
     * 슬랙 메시지 전송
     **/
    public void sendMessage(String title, HashMap<String, String> data) {
        try {
            slackClient.send(SLACK_WEBHOOK_URL, payload(p -> p
                    .text(title) // 메시지 제목
                    .attachments(List.of(
                            Attachment.builder().color(Color.GREEN.toString()) // 메시지 색상
                                    .fields( // 메시지 본문 내용
                                            data.keySet().stream().map(key -> generateSlackField(key, data.get(key))).collect(Collectors.toList())
                                    ).build())))
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Slack Field 생성
     **/
    private Field generateSlackField(String title, String value) {
        return Field.builder()
                .title(title)
                .value(value)
                .valueShortEnough(false)
                .build();
    }
}