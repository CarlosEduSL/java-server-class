package chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 5002);
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                Scanner scanner = new Scanner(System.in)) {
            System.out.println("Conectado ao servidor.");
            String mensagem;

            while (true) {
                System.out.println("Voce: ");
                mensagem = scanner.nextLine();
                output.writeUTF(mensagem);

                if (mensagem.equalsIgnoreCase("sair")) {
                    break;
                }

                String resposta = input.readUTF();
                System.out.println("(Servidor): " + resposta);
            }

        } catch (IOException e) {
            System.err.println("Erro no Client: " + e.getMessage());
        }
        System.out.println("Cliente finalizado");
    }

}