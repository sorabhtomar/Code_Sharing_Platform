package platform.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import platform.utils.TemporalFormatter;

import java.time.LocalDateTime;

public class Code {
    private String code;
    @JsonAlias("date")
    private LocalDateTime createTime;

    public Code() {
        code = "public static void main(String[] args) {\n" +
                "    SpringApplication.run(CodeSharingPlatform.class, args);\n" +
                "}";
        createTime = LocalDateTime.now();
    }

    public Code(String code, LocalDateTime createTime) {
        this.code = code;
        this.createTime = createTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public String getFormattedCreateTime() {
        return TemporalFormatter.getFormattedDateTime(createTime);
    }

    public void setCreatTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
