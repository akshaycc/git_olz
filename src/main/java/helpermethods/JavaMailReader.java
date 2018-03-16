package helpermethods;

import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.*;

/**
 * Created by RtB on 11.09.2016.
 */
public class JavaMailReader {
    private static int countMessageForRead =10;
    private static String titleRemoveMessage ="Подтвердите удаление вашего аккаунта на OLX";
    private static String titleRegistrationMessage ="Активируйте учетную запись";

    private static String getPassCode(String message) {
        String res="";
        Pattern pattern = Pattern.compile(" ");
        Pattern p = Pattern.compile("[0-9][0-9][0-9][0-9]");
        String[] word = pattern.split(message);
        for (int i=0;word.length>i;i++){
            Matcher m = p.matcher(word[i]);
            if (m.matches()) {
                res=word[i];
            }
        }
        return res;
    }

    private static String getRegistrationLink(String message){
        String firstSymbol ="<a id = \"confirmLink\" href=\"";
        String lastSymbol ="\"";
        int indexOf = message.indexOf(firstSymbol) + firstSymbol.length();
        message =message.substring(indexOf);
        return message.substring(0,message.indexOf(lastSymbol));

    }
    private static String getRemoveLinkLink(String message){
        String firstSymbol ="<a id=\"removeAccount\" href=\"";
        String lastSymbol ="\"";
        int indexOf = message.indexOf(firstSymbol) + firstSymbol.length();
        message =message.substring(indexOf);
        return message.substring(0,message.indexOf(lastSymbol));

    }



    public static String getRegistrationLink(String user,String password) throws IOException, MessagingException, InterruptedException {
        String confirmationCode = "";
        // mail server connection parameters
        String host = "imap.gmail.com";
        // connect to my imap inbox
        Properties properties = System.getProperties();
        Session session = Session.getDefaultInstance(properties);
        Store store = session.getStore("imaps");
        store.connect(host, user, password);
        Folder inbox = store.getFolder("Inbox");
        inbox.open(Folder.READ_WRITE);


        int sec =1172;
        int countR = 0;
        while (confirmationCode.isEmpty() && countR<60) {// && sec<=160000

            // get the list of inbox messages
            Message[] messages = inbox.getMessages();
            if (messages.length == 0) System.out.println("No messages found.");
            for (int i = 0; i < messages.length; i++) {

                // stop after listing ten messages
                if (i > countMessageForRead) {
                    System.exit(0);
                    inbox.close(true);
                    store.close();
                }
                if (messages[i].getSubject().equals(titleRegistrationMessage)) {
                    String body="";
                    Multipart mp;
                    Object content = messages[i].getContent();
                    if (content instanceof String)
                    {
                        body = (String)content;
                    }
                    else if (content instanceof Multipart)
                    {
                        mp = (Multipart)content;
                    }

                    confirmationCode = getRegistrationLink(body);
                    //delete letter
                    Flags deleted = new Flags(Flags.Flag.DELETED);
                    inbox.setFlags(messages, deleted, true);

                }
            }
            //sec=sec*2;
            //Thread.sleep(sec);
            countR ++;
            Thread.sleep(5000);
        }
        inbox.close(true);
        store.close();
        return confirmationCode;
    }



    public static String getLinkRemoveAccount(String user,String password) throws IOException, MessagingException, InterruptedException {
        String confirmationCode = "";
        // mail server connection parameters
        String host = "imap.gmail.com";
        //user = "testforme502@gmail.com";
        //password = "qwerty123!";

        // connect to my imap inbox
        Properties properties = System.getProperties();
        Session session = Session.getDefaultInstance(properties);
        Store store = session.getStore("imaps");
        store.connect(host, user, password);
        Folder inbox = store.getFolder("Inbox");
        inbox.open(Folder.READ_WRITE);


        int sec = 1172;
        int countR = 0;
        while (confirmationCode.isEmpty() && countR<60) {//&& sec <= 160000

            // get the list of inbox messages
            Message[] messages = inbox.getMessages();
            if (messages.length == 0) System.out.println("No messages found.");
            for (int i = 0; i < messages.length; i++) {

                // stop after listing ten messages
                if (i > countMessageForRead) {
                    System.exit(0);
                    inbox.close(true);
                    store.close();
                }
                if (messages[i].getSubject().equals(titleRemoveMessage)) {
                    String body = "";
                    Multipart mp;
                    Object content = messages[i].getContent();
                    if (content instanceof String) {
                        body = (String) content;
                    } else if (content instanceof Multipart) {
                        mp = (Multipart) content;
                    }

                    confirmationCode = getRemoveLinkLink(body);
                    //delete letter
                    Flags deleted = new Flags(Flags.Flag.DELETED);
                    inbox.setFlags(messages, deleted, true);

                }
            }
            //sec = sec * 2;
            //Thread.sleep(sec);
            countR ++;
            Thread.sleep(5000);
        }
        inbox.close(true);
        store.close();
        return confirmationCode;
    }


}

