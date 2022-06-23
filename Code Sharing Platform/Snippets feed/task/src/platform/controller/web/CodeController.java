package platform.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import platform.entity.Code;
import platform.utils.InMemoryDb;

import java.time.LocalDateTime;

@Controller("/code")
public class CodeController {
    @GetMapping(value = "/code/{id}", produces = "text/html")
    public String getCodeSnippet(@PathVariable long id, Model model) {
        model.addAttribute("code", InMemoryDb.getCodeById(id));
        return "code";
    }

    @GetMapping(value = "/code/new", produces = "text/html")
    public String newCodeSnippet() {
        return "create";
    }

    @GetMapping(value = "/code/latest", produces = "text/html")
    public String getLatestCodeSnippets(Model model) {
        model.addAttribute("codes", InMemoryDb.getLatestCodes().values());
        return "latest";
    }
}
