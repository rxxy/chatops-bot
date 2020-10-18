package io.github.rxxy.adapter.dingtalk;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class RobotMessage {

    /**
     * conversationId : cid/3UvzX6A3aqCCkxiLDulww==
     * atUsers : [{"dingtalkId":"$:LWCP_v1:$GAXASu5uRyJPtvOfEmV/EXap4xuQXtrn"}]
     * chatbotUserId : $:LWCP_v1:$GAXASu5uRyJPtvOfEmV/EXap4xuQXtrn
     * msgId : msgr24DwWITe9d7UNbjQmfJfQ==
     * senderNick : 杨超辉
     * isAdmin : false
     * sessionWebhookExpiredTime : 1603005227003
     * createAt : 1602999826953
     * conversationType : 2
     * senderId : $:LWCP_v1:$0Rdd1WrryR58GWjDxS4SBF17bTgsI1O7
     * conversationTitle : 大聪明-TEST
     * isInAtList : true
     * sessionWebhook : https://oapi.dingtalk.com/robot/sendBySession?session=fab10412ce230721dd5920a95a3b18c7
     * text : {"content":" jj"}
     * msgtype : text
     */

    @JsonProperty("conversationId")
    private String conversationId;
    @JsonProperty("chatbotUserId")
    private String chatbotUserId;
    @JsonProperty("msgId")
    private String msgId;
    @JsonProperty("senderNick")
    private String senderNick;
    @JsonProperty("isAdmin")
    private Boolean isAdmin;
    @JsonProperty("sessionWebhookExpiredTime")
    private Long sessionWebhookExpiredTime;
    @JsonProperty("createAt")
    private Long createAt;
    @JsonProperty("conversationType")
    private String conversationType;
    @JsonProperty("senderId")
    private String senderId;
    @JsonProperty("conversationTitle")
    private String conversationTitle;
    @JsonProperty("isInAtList")
    private Boolean isInAtList;
    @JsonProperty("sessionWebhook")
    private String sessionWebhook;
    @JsonProperty("text")
    private TextDTO text;
    @JsonProperty("msgtype")
    private String msgtype;
    @JsonProperty("atUsers")
    private List<AtUsersDTO> atUsers;

    @NoArgsConstructor
    @Data
    public static class TextDTO {
        /**
         * content :  jj
         */

        @JsonProperty("content")
        private String content;
    }

    @NoArgsConstructor
    @Data
    public static class AtUsersDTO {
        /**
         * dingtalkId : $:LWCP_v1:$GAXASu5uRyJPtvOfEmV/EXap4xuQXtrn
         */

        @JsonProperty("dingtalkId")
        private String dingtalkId;
    }
}
