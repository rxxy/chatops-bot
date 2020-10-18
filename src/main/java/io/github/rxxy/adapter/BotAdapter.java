package io.github.rxxy.adapter;

import io.github.rxxy.model.Input;
import io.github.rxxy.model.Output;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 机器人适配器
 */
public interface BotAdapter {


    /**
     * 机器人发送消息
     * @param output
     */
    void send(Output output);


    /**
     * 从http回调中接收消息
     */
    void receive(HttpServletRequest request);

}
