package platform.business.entities.wrappers;

public class CodeInputDto {
    private String code;
    private long time;
    private long views;

    public CodeInputDto() {
    }

    public CodeInputDto(String code, long time, long views) {
        this.code = code;
        this.time = time;
        this.views = views;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
}
