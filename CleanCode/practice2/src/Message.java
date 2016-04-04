/**
 * Created by iceberg on 3/25/2016.
 */
import java.util.UUID;
import java.sql.Timestamp;
public class Message {
    private String id;
    private String message;
    private String author;
    private long timestamp;
    // universally unique identifier : UUID
    public Message(String messageString, String authorString){
        // The java.lang.System.currentTimeMillis() method returns the current time in milliseconds.
        Timestamp timeMillis = new Timestamp(System.currentTimeMillis());
        UUID idOne = UUID.randomUUID();
        id = idOne.toString();
        message = messageString;
        author = authorString;
        timestamp = timeMillis.getTime();
    }
    public String getMessage(){
        return message;
    }

    public String getId(){
        return id;
    }

    public String getAuthor(){
        return author;
    }

    public long getTimestamp(){
        return timestamp;
    }
}
