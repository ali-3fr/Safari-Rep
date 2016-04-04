/**
 * Created by iceberg on 3/28/2016.
 */
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException, ParseException {

        Messaging mp = new Messaging();
        boolean check = false;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Welcome to awesome chat app\n");

        while(!check){
            mp.getFileData("data.json");
            System.out.print("Press:\n" +
                    "1 - if you want to add message\n" +
                    "2 - if you want to see message history\n" +
                    "3 - if you want to delete message\n" +
                    "4 - if you want to find message\n" +
                    "5 - if you want to quit app\n");
            switch(scanner.nextInt()){
                case 1:
                    mp.addMessage();
                    break;
                case 2:
                    mp.printSortedMsgs();
                    break;
                case 3:
                    mp.delMsg();
                    break;
                case 4:
                    System.out.print("Press\n" +
                            "1 - if you want to find message by author\n" +
                            "2 - if you want to find message by word\n" +
                            "3 - if you want to find message by date\n" +
                            "4 - if you want to find message by regular expression\n");
                    switch(scanner.nextInt()){
                        case 1:
                            mp.findMsgByAuthor();
                            break;
                        case 2:
                            mp.findMsgByWord();
                            break;
                        case 3:
                            mp.findMsgByDate();
                            break;
                        case 4:
                            mp.findMsgByRegular();
                            break;
                    }
                    break;
                case 5:
                    check = true;
                    break;
            }
            mp.saveToFile("data.json");
        }
    }

}
