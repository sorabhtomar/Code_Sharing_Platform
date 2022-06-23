package platform.business.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import platform.business.utils.TemporalFormatter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "codes")
public class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    private String code;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "date")
    private LocalDateTime createTime;

    public Code() {
    }

    public Code(long id, String code, LocalDateTime createTime) {
        this.id = id;
        this.code = code;
        this.createTime = createTime;
    }

    public String getCode() {
        return code;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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