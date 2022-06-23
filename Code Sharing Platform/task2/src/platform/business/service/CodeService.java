package platform.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.business.entities.Code;
import platform.business.entities.wrappers.CodeInputDto;
import platform.persistence.CodeRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CodeService {
    private final CodeRepository codeRepository;

    @Autowired
    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public Optional<Code> getCodeById(String id) {
        Optional<Code> optionalCode = codeRepository.findById(id);

        if (optionalCode.isEmpty()) {
            return optionalCode;
        }

        Code code = optionalCode.get();
        if (!code.isSecret()) {
            // when it's NOT a secret code
            return optionalCode;
        }

        // When it's a secret code, refresh it's "time" and "views" values
        // (if it's NOT already marked as deleted), and update the code in the database
        return updateSecretCodeAfterRefresh(code);
    }

    public List<Code> getLatestCodes() {
        // latest 10 non-secret codes
        return codeRepository.findTop10BySecretFalseOrderByCreateTimeDesc();
    }

    public String saveNewCode(CodeInputDto toSave) {
        // CodeInputDto toSave: "code" details input by the user (and we got from @ResponseBody)

        // Creating a new instance of Code, instead of just saving "toSave",
        // to set the rest of the important fields that we didn't get from the user
         Code code = new Code(toSave);

        Code savedCode = codeRepository.save(code);
        return savedCode.getId();
    }

    private Optional<Code> updateSecretCodeAfterRefresh(Code secretCode) {
        // All these operations are for the "secret" code only, since we've already checked that "secretCode" is a "secret" code

        // When "secretCode" is already deleted. No need to bother refreshing etc. Just return Optional.empty().
        // This case would NOT arise, if we were actually deleting (instead of soft deleting, in our case)
        if (secretCode.isDeleted()) {
            return Optional.empty();
        }

        // "secretCode" is NOT deleted till now, which means "time" and "views" of "secretCode" are still valid
        secretCode.refreshTimeAndViews();

//        // Now, "secretCode" may have been marked deleted. And if so, it's possible that
//        // it may have been valid before getting marked deleted. So, check if "secretCode" was actually valid
//        if (secretCode.getTime() <= 0 || secretCode.getViews() < 0) {
//            // 1. permissible time to view the "secretCode" has just elapsed (or was already elapsed), or
//            // 2. permissible number of views for the "secretCode" has just reached (or was already reached), or
//            // 3. both
//
//            // In all of the above cases, "secretCode" got invalid.
//            // So, mark "secretCode" as deleted (i.e. update "secretCode" in the database)
//            // Could do this right after refreshTimeAndViews() call, but it's better to do it here (for more readability/understandability)
//            codeRepository.save(secretCode);
//            return Optional.empty();
//        }
//
//        // secretCode.getViews() == 0, is a valid case but just this once (when the "secretCode" was just marked deleted).
//        // That is, "views" (i.e. permissible views left) is still > 0; or its value was 1, and just got decremented by 1 (to 0).
//
//        // Also, "time" (i.e. permissible time still left to view the "secretCode") is valid. That is "time" is still > 0 (in seconds)
//
//        // In both cases: when "secretCode" is still valid, and when "secretCode" was valid just before refresh (and now invalid);
//        // we need to update the database with the "refreshed" value of "time", "views", "deleted" properties of "secretCode"
//        codeRepository.save(secretCode);
//        return Optional.of(secretCode);

        // After refreshing, we need to save "secretCode" into the database. Doesn't matter if it's still valid or not.
        // We'll check if it's valid or not, next.
        // Don't need to create a new instance of Code, while saving this time (like: new Code(secretCode)). Because, this is an update.
        // And we had already saved the code details that the user had entered the first time (along with the important missing fields)
        codeRepository.save(secretCode);

        // Need to check if, "secretCode" is still valid. In either of the following cases is true, then "secretCode" is still valid.
        // That is, time is endless and views are valid (i.e. views >= 0 (instead of views > 0))
        // Or, time is valid (i.e. time > 0) and views are endless
        // Or, time is valid (i.e. time > 0) and views are valid (i.e. views >= 0 (instead of views > 0))
        if (secretCode.isTimeEndless() && secretCode.getViews() >= 0
                || secretCode.getTime() > 0 && secretCode.isViewsEndless()
                || secretCode.getTime() > 0 && secretCode.getViews() >= 0) {
            // And in this case, we'll just return the Optional of "secretCode"
            return Optional.of(secretCode);
        } else {
            // And if "secretCode" is NOT valid (and we know that it is a "secret" code), we simply mark it as deleted.
            // And return, an empty Optional

            // "secretCode" is NOT valid means: secretCode.getTime() <= 0 && secretCode.isViewsEndless() ||
            // secretCode.isTimeEndless() && secretCode.getViews() <= 0 ||
            // secretCode.getTime() <= 0 && secretCode.getViews() <= 0

            // We're soft-deleting here, with these 2 statements below.
            secretCode.markAsDeleted();
            // After "secretCode" is marked deleted, we need to save this state of "secretCode" in the database.
            // Don't need to create a new instance of Code, while saving this time (like: new Code(secretCode)). Because, this is an update.
            // And we had already saved the code details that the user had entered the first time (along with the important missing fields)
            codeRepository.save(secretCode);

            // We could actually delete teh "secretCode" (record) from the "codes" table in the database (i.e. hard delete).
            // If we were to do that, the following preparations are needed:
            // 1. Remove the field "deleted" from "Code" entity class (and its getters and setters). And delete all the places this field is marked as true/false (constructor, markAsDeleted()).
            // And also, remove markAsDeleted() method from "Code" entity.
            // 2. In this method i.e. updateSecretCodeAfterRefresh(), remove secretCode.isDeleted() conditional check. And then, remove the soft-delete statements.
            // Instead, use the following (commented) statement for acutally deleting "secretCode" record from the "codes" table in the database.
            // codeRepository.delete(secretCode); // or, codeRepository.deleteById(secretCode.getId());

            return Optional.empty();
        }
    }
}
