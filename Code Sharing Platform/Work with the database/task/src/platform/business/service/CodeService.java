package platform.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.business.entities.Code;
import platform.business.utils.TemporalFormatter;
import platform.persistence.CodeRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CodeService {
    private CodeRepository codeRepository;

    @Autowired
    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public Code getCodeById(long id) {
        return codeRepository.findById(id).orElseThrow();
    }

    public List<Code> getLatestCodes() {
        return codeRepository.findTop10ByOrderByCreateTimeDesc();
    }

    public long saveNewCode(Map<String, String> code) {
        Code newCode = new Code();
        newCode.setCode(code.get("code"));
        newCode.setCreateTime(LocalDateTime.now());

        Code savedCode = codeRepository.save(newCode);
        return savedCode.getId();
    }
}
