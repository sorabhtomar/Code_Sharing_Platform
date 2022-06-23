package platform.presentation.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import platform.business.entities.Code;
import platform.business.entities.wrappers.CodeInputDto;
import platform.business.entities.wrappers.CodeOutputDto;
import platform.business.service.CodeService;

import java.util.*;

@RestController("/api/code")
@RequestMapping("api")
public class CodeController {
    @Autowired
    private CodeService codeService;

    @GetMapping(value = "/code/{id}", produces = "application/json")
    public Code getCodeSnippet(@PathVariable String id) {
        Optional<Code> optionalCode = codeService.getCodeById(id);

        if (optionalCode.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return optionalCode.get();
    }

    @GetMapping(value = "/code/latest", produces = "application/json")
    public List<Code> getLatestCodes() {
        return codeService.getLatestCodes();
    }

    @PostMapping(value = "/code/new", consumes = "application/json")
    public Map<String, String> postCodeSnippet(@RequestBody CodeInputDto code) {
        String id = codeService.saveNewCode(code);
        // if "id" is of any other type, can use String.valueOf()
        return Map.of("id", id);
        // return new CodeOutputDto(id);
    }
}
