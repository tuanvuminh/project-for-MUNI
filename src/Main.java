import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {

        // Porty
        String serverIP = "78.128.211.58";
        int serverPort = 4444;

        // Komunikace
        try (Socket socket = new Socket(serverIP, serverPort);
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter sendMessage = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader readMessage = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Připojení k serveru proběhlo úspěšně. Pro ukončení konverzace zadejte \"exit\"");

            String userMessage;
            for (boolean continueConversation = true; continueConversation; ) {
                userMessage = reader.readLine();

                if (userMessage.equalsIgnoreCase("exit")) {
                    continueConversation = false;
                } else {
                    sendMessage.println(userMessage);
                    String serverResponse = readMessage.readLine();
                    System.out.println("Vaše poslaná zpráva: " + serverResponse);
                }
            }
            System.out.println("Byli jste odpojeni ze serveru.");
        } catch (IOException e) {
            System.err.println("Nastala chyba při připojení.");
        }
    }
}
