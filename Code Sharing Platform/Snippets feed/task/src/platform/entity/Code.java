package platform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import platform.utils.TemporalFormatter;

import java.time.LocalDateTime;

public class Code {
    private String code;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    public Code() {
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

    @JsonProperty("date")
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    @JsonProperty("date")
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @JsonIgnore
    public String getFormattedCreateTime() {
        return TemporalFormatter.getFormattedDateTime(createTime);
    }
}