package io.github.rxxy;

import io.github.rxxy.adapter.BotAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class MainController {
    @Resource
    private List<BotAdapter> botAdapters;

    @RequestMapping("/handler")
    public void process(HttpServletRequest request, HttpServletResponse response){
        botAdapters.forEach(botAdapter -> botAdapter.receive(request));
    }

}
