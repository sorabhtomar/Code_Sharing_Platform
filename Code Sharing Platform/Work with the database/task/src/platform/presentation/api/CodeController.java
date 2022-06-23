package platform.presentation.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import platform.business.entities.Code;
import platform.business.service.CodeService;
import platform.business.utils.InMemoryDb;

import java.time.LocalDateTime;
import java.util.*;

@RestController("/api/code")
@RequestMapping("api")
public class CodeController {
    @Autowired
    private CodeService codeService;

    @GetMapping(value = "/code/{id}", produces = "application/json")
    public Code getCodeSnippet(@PathVariable long id) {
         return codeService.getCodeById(id);
    }

    @GetMapping(value = "/code/latest", produces = "application/json")
    public List<Code> getLatestCodes() {
        return codeService.getLatestCodes();
    }

    @PostMapping(value = "/code/new", consumes = "application/json")
    public Map<String, String> postCodeSnippet(@RequestBody Map<String, String> code) {
        long id = codeService.saveNewCode(code);
        return Map.of("id", String.valueOf(id));
    }
}
