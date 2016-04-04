/**
 * Created by iceberg on 3/29/2016.
 */
import java.util.Comparator;
public class Comp implements Comparator <Message>{
    @Override
    public int compare(Message message1, Message message2) {

        return Long.valueOf(message1.getTimestamp()).compareTo(Long.valueOf(message2.getTimestamp()));

    }
}
