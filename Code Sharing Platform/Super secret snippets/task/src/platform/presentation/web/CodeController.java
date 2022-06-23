package platform.presentation.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import platform.business.entities.Code;
import platform.business.service.CodeService;

import java.util.Optional;
import java.util.UUID;

@Controller("/code")
public class CodeController {
    @Autowired
    private CodeService codeService;

    @GetMapping(value = "/code/{id}", produces = "text/html")
    public String getCodeSnippet(@PathVariable String id, Model model) {
        Optional<Code> optionalCode = codeService.getCodeById(id);

        if (optionalCode.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        model.addAttribute("code", optionalCode.get());
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
