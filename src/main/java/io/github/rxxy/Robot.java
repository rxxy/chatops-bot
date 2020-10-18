package io.github.rxxy;

import io.github.rxxy.adapter.BotAdapter;
import io.github.rxxy.model.Input;
import io.github.rxxy.model.Output;
import io.github.rxxy.script.Script;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class Robot {

    @Resource()
    private List<BotAdapter> botAdapters;

    /**
     * 接收用户输入
     * @param botAdapter 适配器
     * @param input 输入
     */
    public void receive(BotAdapter botAdapter, Input input){
        Output output = Script.process(input);
        botAdapter.send(output);
    }

    /**
     * 发送给所有适配器
     * @param output
     */
    public void sendToAllAdapter(Output output){
        this.botAdapters.forEach(botAdapter -> botAdapter.send(output));
    }

}
