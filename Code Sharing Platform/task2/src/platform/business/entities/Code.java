package platform.business.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import platform.business.entities.wrappers.CodeInputDto;
import platform.business.utils.TemporalHelper;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "codes")
@JsonPropertyOrder({"code", "date", "time", "views"})
public class Code {

    @Id
    @Column(name = "id")
    private String id;

    @NotBlank(message = "Please enter your code")
    private String code;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "date")
    private LocalDateTime createTime;

    @Column(name = "time_left")
    private long time;

    @Column(name = "total_initial_time")
    private long totalTime;

    @JsonIgnore
    @Column(name = "is_time_endless")
    private boolean timeEndless;

    @JsonIgnore
    @Column(name = "are_views_endless")
    private boolean viewsEndless;

    @Column(name = "views_left")
    private long views;

    @JsonIgnore
    @Column(name = "is_secret")
    private boolean secret;

    @JsonIgnore
    @Column(name = "is_deleted")
    private boolean deleted;

    public Code() {
        id = UUID.randomUUID().toString();
        createTime = LocalDateTime.now();
    }

    public Code(CodeInputDto code) {
        this();
        this.code = code.getCode();

        if (code.getTime() <= 0) {
            timeEndless = true;
            totalTime = 0; // by default is 0
        } else {
            timeEndless = false; // by default is "false"
            time = totalTime = code.getTime();
        }

        if (code.getViews() <= 0) {
            viewsEndless = true;
            views = 0; // by default is 0
        } else {
            viewsEndless = false; // by default if "false"
            views = code.getViews();
        }

        if (timeEndless && viewsEndless) {
            // When there is NO restriction on BOTH "time" and "views", then the code is non-secret (i.e. NOT hidden)
            secret = false;
        } else {
            secret = true;
        }

        deleted = false; // by default is "false"
    }

    @JsonIgnore
    public String getId() {
        return id;
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

    @JsonIgnore
    public String getFormattedCreateTime() {
        return TemporalHelper.getFormattedDateTime(createTime);
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public boolean isSecret() {
        return secret;
    }

    public void setSecret(boolean secret) {
        this.secret = secret;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isTimeEndless() {
        return timeEndless;
    }

    public void setTimeEndless(boolean timeEndless) {
        this.timeEndless = timeEndless;
    }

    public boolean isViewsEndless() {
        return viewsEndless;
    }

    public void setViewsEndless(boolean viewsEndless) {
        this.viewsEndless = viewsEndless;
    }

    private void refreshTime() {
        // assigning to "time", the seconds from "createTime"
        time = totalTime - TemporalHelper.timeDifferenceInSeconds(createTime, LocalDateTime.now());
    }

    private void refreshViews() {
        views--;
    }

    public void markAsDeleted() {
        deleted = true;
    }

    public void refreshTimeAndViews() {
        // We are calling refreshTime(), refreshViews(), markAsDeleted() only when the code is "secret"

        // code is secret in these 3 cases:
        // 1. when "time" is restricted (but "views" are endless/unrestricted) i.e. restriction on "time"
        // 2. when "views" are restricted (but "time" is endless/unrestricted) i.e. restriction on "views"
        // 3. when BOTH "time" and "views" are restricted
        refreshTime();
        refreshViews();
    }
}