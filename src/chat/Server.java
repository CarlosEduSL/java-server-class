package chat;

import java.net.ServerSocket;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.io.IOError;

public class Server {
    public static void main(String[] args) {
        System.out.println("Servidor iniciado. Aguardando conexao");

        try (ServerSocket serverSocket = new ServerSocket(5002);
                Socket clientSocket = serverSocket.accept(); // bloqueia até um cliente se conectar
                DataInputStream input = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream())) {
            System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

            while (true) {
                String mensagem = input.readUTF(); // recebe string do cliente
                System.out.println("(cliente) " + mensagem);

                if (mensagem.equalsIgnoreCase("sair")) {
                    output.writeUTF("Conexao encerrado pelo client."); // resposta do servidor ao cliente
                    break;
                }

                output.writeUTF("Servidor recebeu: " + mensagem);
            }

        } catch (IOException e) {
            System.err.println("Erro no servidor: " + e.getMessage());
        }

        System.out.println("Servidor Finalizado.");
    }
}
