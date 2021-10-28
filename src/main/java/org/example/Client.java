package org.example;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


/**
 * Класс клиента, подключается к серверу и отправляет данные
 */
public class Client {

    /**
     * Метод производит включение клиента и отправляет на сервер
     * целые числа или "end"
     */
    public void onClient() {
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", 23444);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in)) {
            String msg;
            while (true) {
                System.out.println("Введите целое натуральное число (или end): ");
                msg = scanner.nextLine();
                out.println(msg);
                //Если ввели end - прерываем поток
                if ("end".equals(msg)) {
                    Thread.currentThread().interrupt();
                }

                System.out.println("SERVER: " + in.readLine());
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
