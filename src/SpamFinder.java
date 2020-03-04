import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SpamFinder {
    private static final String[] spamWords = {
            "nigerian", "disclaimer", "urgent", "necessary", "free", "4u",
            "guarantee", "profits", "congratulations", "opportunity",
            "unlimited", "scam", "casino", "profits", "amazing", "mailto",
            "mlm", "unsecured", "winner", "winning", "hormone", "insurance",
            "spam", "ringtones", "pharmacy", "blackjack", "cwas", "chatroom",
            "duty-free", "shoes"};

    public static void main(String[] args) {
        String firstFile = "src/Email1.txt";
        String secondFile = "src/Email2.txt";
        String firstEmail = readFile(firstFile);
        String secondEmail = readFile(secondFile);

        firstEmail = processString(firstEmail);
        spamCheck(firstEmail);

        secondEmail = processString(secondEmail);
        spamCheck(secondEmail);

    }

    public static String readFile(String fileName) {
        try {
            String file = new String(Files.readAllBytes(Paths.get(fileName)));
            return file;

        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean validateWhitespaces(String string){
        return string.matches("[a-zA-z]+\\s([a-zA-z]+?\\s?)+?");
    }

    public static String processString(String email){

        System.out.println("EMAIL IS: ");
        System.out.println(email);
        System.out.println();

        email = email.replaceAll("[!.?]+", " ");

        /*Testing the removal of punctuations
        System.out.println();
        System.out.println("REPLACING PUNCTUATIONS ");
        System.out.println(email);
         */


        if (!validateWhitespaces(email)) {
            email = email.replaceAll("\\s+", " ");
        }

        /* Testing the strings without extra whitespaces
        System.out.println();
        System.out.println("REMOVING EXTRA WHITESPACES");
        System.out.println(email);*/

        System.out.println();

        email = email.toLowerCase();

       /* Testing Lower Case Email
        System.out.println("MAKING LOWER CASE FOR EASIER COMPARISON");
        System.out.println(email);
        System.out.println();
        */

        return email;
    }

    public static void spamCheck(String email){
        int spamCount=0;
        String[] emailTokens = email.split(" ");

        for(int i =0; i < emailTokens.length; i++){
            for(int j = 0; j < spamWords.length; j++){
                if(emailTokens[i].compareTo(spamWords[j]) == 0){
                    spamCount++;
                }
            }
        }

        System.out.println("SPAM WORD COUNT FOR EMAIL IS: " + spamCount);
        if( spamCount == 0){
            System.out.println("No Spam Words. Not A Scam.");
        }
        else if(spamCount < 3){
            System.out.println("Light Chance of Scam");
        }
        else if(spamCount > 3 && spamCount < 7){
            System.out.println("Moderate Chance of Scam");
        }
        else{
            System.out.println("High Chance of Scam ");
        }

        System.out.println();
    }
}
