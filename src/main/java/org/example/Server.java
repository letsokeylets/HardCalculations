package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс Сервера (принимает сообщения от клиента)
 */
public class Server {
    //Первые 2 числа последовательности
    private final int FIRST_FIBO_NUM = 0;
    private final int SECOND_FIBO_NUM = 0;

    /**
     * Включает сервер и ждёт от клиента число
     * Выбран блокирующий вариант, так как мы не можем дробить ввод и вывод для данной задачи
     * Должны получить целое число и отправить сразу результат вычислений (не частями)
     */
    public void onServer() {
        ServerSocket servSocket = null;
        try {
            servSocket = new ServerSocket(23444);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try (Socket socket = servSocket.accept();
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    //Если ввели end - прерываем поток
                    if (line.equals("end")) {
                        Thread.currentThread().interrupt();
                    }
                    final int fiboNum = fibonachiCalculate(Integer.parseInt(line));
                    out.println("Вычесленное число фибаначи: " + fiboNum);
                }
            } catch (IOException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }

    /**
     * Метод подсчёта Числа Фибоначчи
     * в качестве аргумента "n" - принимает целое натуральное число
     * Возвращает результат вычислений (n-ый член вычислений)
     */
    private int fibonachiCalculate(int n) {
        List<Integer> list = new ArrayList<>();
        list.add(FIRST_FIBO_NUM);
        list.add(SECOND_FIBO_NUM);
        for (int i = 0; i < n; i++) {
            list.add(list.get(i) + list.get(i + 1));
        }
        return list.get(n - 1);
    }
}
