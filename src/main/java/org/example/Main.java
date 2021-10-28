package org.example;

/**
 * Main Class!
 */
public class Main
{
    public static void main( String[] args )
    {
        Client client = new Client();
        Server server = new Server();
        Thread serverThread = new Thread(server::onServer, "Сервер");
        Thread clientThread = new Thread(client::onClient, "Старт");
        serverThread.start();
        clientThread.start();
        while (true) {
            //Выключаем программу, если остановили хоть 1 поток (клиент ввел end)
            if (clientThread.isInterrupted() || serverThread.isInterrupted()) {
                System.exit(0);
            }
        }
    }
}
