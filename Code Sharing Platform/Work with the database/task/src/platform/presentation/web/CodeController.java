package platform.presentation.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import platform.business.service.CodeService;
import platform.business.utils.InMemoryDb;

@Controller("/code")
public class CodeController {
    @Autowired
    private CodeService codeService;

    @GetMapping(value = "/code/{id}", produces = "text/html")
    public String getCodeSnippet(@PathVariable long id, Model model) {
        model.addAttribute("code", codeService.getCodeById(id));
        return "code";
    }

    @GetMapping(value = "/code/new", produces = "text/html")
    public String newCodeSnippet() {
        return "create";
    }

    @GetMapping(value = "/code/latest", produces = "text/html")
    public String getLatestCodeSnippets(Model model) {
        model.addAttribute("codes", codeService.getLatestCodes());
        return "latest";
    }
}
