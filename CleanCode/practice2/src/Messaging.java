/**
 * Created by iceberg on 3/25/2016.
 */
import java.io.*;
import java.text.*;
import java.util.*;
import java.lang.reflect.Type;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
public class Messaging {
private List <Message> messages;
    public Messaging() {
        messages = new ArrayList<>();
    }
    public void getFileData(String path) throws IOException {

        FileReader fr = new FileReader(path);
        Scanner sc = new Scanner(fr);
        Gson gson = new Gson();
        Type collectionType = new TypeToken<ArrayList<Message>>() {
        }.getType();
        String string;
        if(sc.hasNext()){
            string = sc.nextLine();
            messages = gson.fromJson(string, collectionType);
        }
        else System.out.print("File is empty!\n");
        fr.close();

    }
    public void saveToFile(String path) throws IOException {

        FileWriter fileWriter = new FileWriter(path);
        Gson gson = new Gson();
        String json = gson.toJson(messages);
        fileWriter.write(json);
        fileWriter.close();

    }
    public void addMessage() {

        Scanner scanner = new Scanner(System.in);

        String message;
        String nickname;

        System.out.print("Enter your nickname:\n");
        nickname = scanner.nextLine();
        System.out.print("Enter your message:\n");
        message = scanner.nextLine();

        messages.add(new Message( message , nickname));

    }
    private void sortMessagesByDate() {

        Collections.sort(messages, new Comp());

    }

    public void delMsg() {


        Scanner scanner = new Scanner(System.in);
        String delId;

        System.out.print("Enter message id:\n");

        delId = scanner.nextLine();

        for (int i = 0; i < messages.size(); i++) {
            if (messages.get(i).getId().equals(delId)) {
                messages.remove(i);
                break;
            }
        }


    }
    private void printMsg(Message m) {

        Date date = new Date(m.getTimestamp());
        System.out.print(date.toString() + " : " + m.getAuthor() + ": " + m.getMessage()+ "\n");

    }

    public void printSortedMsgs() {

        sortMessagesByDate();

        for (Message temp : messages) {
            printMsg(temp);
        }

    }

    public void findMsgByAuthor() {

        Scanner scanner = new Scanner(System.in);
        String auth;
        boolean check = false;
        System.out.print("Input author name:\n");

        auth = scanner.nextLine();


        for (Message temp : messages) {
            if (temp.getAuthor().equals(auth)) {
                printMsg(temp);
                check = true;
            }
        }

        if(!check){
            System.out.print("No such message\n");
        }
    }

    public void findMsgByWord() {

        Scanner scanner = new Scanner(System.in);
        String word;
        boolean check = false;

        System.out.print("Enter word:\n");
        word = scanner.nextLine();

        for (Message temp : messages) {
            if (temp.getMessage().contains(word)) {
                printMsg(temp);
                check = true;
            }
        }
        if(!check){
            System.out.print("No such message\n");
        }
    }

    public void findMsgByRegular() {

        System.out.print("Enter regular expression:\n");
        Scanner scanner = new Scanner(System.in);
        Pattern pattern = Pattern.compile(scanner.nextLine());
        boolean flag = false;

        for (Message temp : messages) {
            Matcher m = pattern.matcher(temp.getMessage());
            if (m.matches()) {
                printMsg(temp);
                flag = true;
            }
        }
        if(!flag){
            System.out.print("No such message\n");
        }
    }

    public void findMsgByDate() throws ParseException {

        Scanner sc = new Scanner(System.in);
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm");
        System.out.print("Enter date in \"dd.MM.yyyy 'at' HH:mm\" format:\n");
        Date date1 = format.parse(sc.nextLine());
        Date date2 = format.parse(sc.nextLine());
        Date defDate;
        boolean check = false;

        for(Message temp : messages){
            defDate = new Date(temp.getTimestamp());
            if(defDate.before(date1) && defDate.after(date2) ){
                printMsg(temp);
                check = true;
            }
        }
        if(!check){
            System.out.print("No such message\n");
        }
    }

}
