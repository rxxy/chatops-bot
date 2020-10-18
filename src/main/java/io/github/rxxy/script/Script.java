package io.github.rxxy.script;

import io.github.rxxy.model.Input;
import io.github.rxxy.model.Output;

import java.util.regex.Pattern;

/**
 * 消息处理脚本
 */
public class Script {

    private static final Pattern pattern = Pattern.compile(".*你好.*");
    /**
     * 消息处理器
     * @param input
     * @return
     */
    public static Output process(Input input) {
        Output output = new Output();
        output.setContent("嘻嘻嘻");
        if (pattern.matcher(input.getContent()).matches()){
            output.setContent("你也好");
        }
        return output;
    }
}
