package platform.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import platform.entity.Code;
import platform.utils.InMemoryDb;

import java.time.LocalDateTime;

@Controller("/code")
public class CodeController {
    @GetMapping(value = "/code", produces = "text/html")
    public String getCodeSnippet(Model model) {
        model.addAttribute("code", InMemoryDb.getCode().getCode());
        model.addAttribute("date", InMemoryDb.getCode().getFormattedCreateTime());
        return "code";
    }

    @GetMapping(value = "/code/new", produces = "text/html")
    public String newCodeSnippet() {
//        InMemoryDb.setCode(new Code(code, LocalDateTime.now()));
        return "create";
    }
}
