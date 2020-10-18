package io.github.rxxy;

import cn.hutool.extra.spring.SpringUtil;
import io.github.rxxy.model.Output;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author 杨超辉
 */
@SpringBootApplication
@EnableCaching
@EnableAsync
@Slf4j
@Import(SpringUtil.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        Robot robot = SpringUtil.getBean(Robot.class);
        Output output = new Output();
        output.setContent("你好呀");
        robot.sendToAllAdapter(output);
        log.info("应用启动成功");
    }
}
