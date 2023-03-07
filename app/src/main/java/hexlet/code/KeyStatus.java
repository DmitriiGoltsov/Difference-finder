package hexlet.code;

public class KeyStatus {

    private final String status;
    private final Object value1;
    private Object value2;

    public KeyStatus(String status, Object value1, Object value2) {
        this.status = status;
        this.value1 = value1;
        this.value2 = value2;
    }

    public KeyStatus(String status, Object value) {
        this.status = status;
        this.value1 = value;
    }

    public String getStatus() {
        return status;
    }

    public Object getValue1() {
        return value1;
    }

    public Object getValue2() {
        return value2;
    }

}
