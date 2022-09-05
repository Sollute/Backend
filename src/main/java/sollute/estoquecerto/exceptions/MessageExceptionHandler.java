package sollute.estoquecerto.exceptions;

import java.util.Date;

public class MessageExceptionHandler extends RuntimeException {

    private Date timetStamp;
    private Integer status;
    private String message;

    public MessageExceptionHandler(
            Date timetStamp,
            Integer status,
            String message
    ) {
        this.timetStamp = timetStamp;
        this.status = status;
        this.message = message;
    }

    public Date getTimetStamp() {
        return timetStamp;
    }

    public void setTimetStamp(Date timetStamp) {
        this.timetStamp = timetStamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
