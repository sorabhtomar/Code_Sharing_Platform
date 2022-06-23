package platform.controller.api;

import org.springframework.web.bind.annotation.*;
import platform.entity.Code;
import platform.utils.InMemoryDb;

import java.time.LocalDateTime;
import java.util.*;

@RestController("/api/code")
@RequestMapping("api")
public class CodeController {

    @GetMapping(value = "/code/{id}", produces = "application/json")
    public Code getCodeSnippet(@PathVariable long id) {
         return InMemoryDb.getCodeById(id);
    }

    @GetMapping(value = "/code/latest", produces = "application/json")
    public Collection<Code> getLatestCodes() {
        return InMemoryDb.getLatestCodes().values();
    }

    @PostMapping(value = "/code/new", consumes = "application/json")
    public Map<String, String> postCodeSnippet(@RequestBody Map<String, String> code) {
        Code newCode = new Code(code.get("code"), LocalDateTime.now());
        long id = InMemoryDb.setNewCode(newCode);

        return Map.of("id", String.valueOf(id));
    }
}
