package platform.controller.api;

import org.springframework.web.bind.annotation.*;
import platform.entity.Code;
import platform.utils.InMemoryDb;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController("/api/code")
@RequestMapping("api")
public class CodeController {

    @GetMapping(value = "/code", produces = "application/json")
    public Map<String, String> getCodeSnippet() {
        Map<String, String> code = new LinkedHashMap<>();
        code.put("code", InMemoryDb.getCode().getCode());
        code.put("date", InMemoryDb.getCode().getFormattedCreateTime());

        return code;
    }

    @PostMapping(value = "/code/new", consumes = "application/json")
    public Map<String, String> postCodeSnippet(@RequestBody Map<String, String> code) {
        InMemoryDb.setCode(new Code(code.get("code"), LocalDateTime.now()));
        return new LinkedHashMap<>();
    }
}
