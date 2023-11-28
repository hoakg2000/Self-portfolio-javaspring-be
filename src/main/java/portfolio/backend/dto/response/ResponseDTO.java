package portfolio.backend.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
public class ResponseDTO {

    @Setter
    @Getter
    class Header{
        private HttpStatus statusCode;
    }

    @Setter
    @Getter
    class Body{
        private Object data;
    }

    @Setter
    @Getter
    class Message{
        private String messenger;
        private Throwable error;
        private ZonedDateTime timestamp;
    }

    private Header header = new Header();
    private Body body = new Body();
    private Message message = new Message();

    public ResponseDTO(){}

    public ResponseDTO(RuntimeException e){
        setHeader(HttpStatus.BAD_REQUEST);
        setMessage(e.getMessage());
        setTime(ZonedDateTime.now(ZoneId.of("Z")));
    }
    public ResponseDTO(Object data){
        setHeader(HttpStatus.OK);
        setData(data);
        setTime(ZonedDateTime.now(ZoneId.of("Z")));
    }

    public ResponseDTO(HttpStatus code, Object obj){
        setHeader(code);
        setData(obj);
    }

    public void setHeader(HttpStatus code){
        this.header.setStatusCode(code);
    }

    public void setData(Object obj){
        this.body.setData(obj);
    }

    public void setMessage(String mes){
        this.message.setMessenger(mes);
    }

    public void setError(Throwable err){
        this.message.setError(err);
    }

    public void setTime(ZonedDateTime zonedDateTime){
        this.message.setTimestamp(zonedDateTime);
    }
}
