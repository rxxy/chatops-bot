package io.github.rxxy.adapter.dingtalk;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import io.github.rxxy.Robot;
import io.github.rxxy.adapter.BotAdapter;
import io.github.rxxy.model.Input;
import io.github.rxxy.model.Output;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.Map;

@Component
@Slf4j
public class DingTalkAdapter implements BotAdapter {

    private static final String webhookUrl = "https://oapi.dingtalk.com/robot/send?access_token=1f5046590cbb35a40b139acbbc642eb0ea00a2549a45bc9d9c5656dc2918a552";


    @SneakyThrows
    @Override
    public void send(Output output) {
        long timeStamp = System.currentTimeMillis();
        String sign = DingUtil.generateSign(timeStamp);
        DingTalkClient client = new DefaultDingTalkClient(StrUtil.format("{}&timestamp={}&sign={}", webhookUrl, timeStamp, sign));
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("text");
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent(output.getContent());
        request.setText(text);

//        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
//        at.setAtMobiles(Arrays.asList("132xxxxxxxx"));
//// isAtAll类型如果不为Boolean，请升级至最新SDK
//        at.setIsAtAll(true);
//        request.setAt(at);
//
//        request.setMsgtype("link");
//        OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
//        link.setMessageUrl("https://www.dingtalk.com/");
//        link.setPicUrl("");
//        link.setTitle("时代的火车向前开");
//        link.setText("这个即将发布的新版本，创始人xx称它为红树林。而在此之前，每当面临重大升级，产品经理们都会取一个应景的代号，这一次，为什么是红树林");
//        request.setLink(link);
//
//        request.setMsgtype("markdown");
//        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
//        markdown.setTitle("杭州天气");
//        markdown.setText("#### 杭州天气 @156xxxx8827\n" +
//                "> 9度，西北风1级，空气良89，相对温度73%\n\n" +
//                "> ![screenshot](https://gw.alicdn.com/tfs/TB1ut3xxbsrBKNjSZFpXXcXhFXa-846-786.png)\n"  +
//                "> ###### 10点20分发布 [天气](http://www.thinkpage.cn/) \n");
//        request.setMarkdown(markdown);

        OapiRobotSendResponse response = client.execute(request);
        if (response.getErrcode() != 0) {
            log.error(response.getErrmsg());
        }
    }

    @SneakyThrows
    @Override
    public void receive(HttpServletRequest request) {
        boolean isValid = checkRequestSign(request);
        if (!isValid){
            log.warn("请求不合法");
        }
        Map<String, String[]> parameterMap = request.getParameterMap();
        String body = IoUtil.read(request.getReader());
        JSONObject jsonObject = JSONUtil.parseObj(body);
        RobotMessage robotMessage = JSONUtil.toBean(jsonObject, RobotMessage.class);
        Robot robot = SpringUtil.getBean(Robot.class);
        Input input = new Input();
        input.setContent(robotMessage.getText().getContent());
        robot.receive(this, input);
        System.out.printf("123");

    }

    /**
     * 校验请求是否合法
     * @param request
     * @return
     */
    private boolean checkRequestSign(HttpServletRequest request){
        try{
            long timestamp = Long.parseLong(request.getHeader("timestamp"));
            //todo 校验时间戳与当前时间差是否大于一小时
            String sign = request.getHeader("sign");
            String calcSign = DingUtil.generateHandlerSign(timestamp);
            return calcSign.equals(sign);
        }catch (Exception e){
            return false;
        }
    }

}
