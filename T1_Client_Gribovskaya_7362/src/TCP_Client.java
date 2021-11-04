/*
 * Выполнила: Грибовская Анастасия, группа 7362
 * Задание: 1 Передача сообщений TCP через сокет
 * Дата выполнения: 26.09.2021
 * Версия: 0.1
 */

// ------------------------------------------- //

/*
 * Данная программа выполняет функции клиентского приложения TCP-протокола.
 * В начале работы создается сокет для связи с сервером. Далее серверу
 * передается десять сообщений, состоящих из заголовка с номером и
 * текста "long symbol line...". В конце передается сообщение END,
 * свидетельствующее об окончании передачи сообщений.
 */


import java.net.*;
import java.io.*;

public class TCP_Client {
    //    локальный порт сервера
    public static final int PORT = 8080;

    public static void main(String[] args) throws IOException {

//		адрес сокета
        String address = "localhost";
        System.out.println("Адрес сокета клиента: " + address);
//		создание сокета для связи с сервером
        Socket socket = new Socket(address, PORT);
        System.out.println("Сокет: " + socket);
//		перехват исключений пакета java.io
        try {
//			буферированные потоки вывода и ввода
            PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),
                    true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            for (int i = 1; i < 11; i++) {
//				передача сообщения на сервер
                output.println(i + (int)(Math.random()*1000) + " " + "long symbol line...");
//				сообщение от сервера
                System.out.println("Сообщение от сервера: " + input.readLine());
            }
//			сообщение, сигнализирующее о завершении цикла
            output.println("END");
        } catch (IOException e) {
//			вывод информации об исключении
            System.out.println("Исключение: " + e.getMessage());
        } finally {
            System.out.println("Завершение...");
//			закрытие сокета
            socket.close();
        }
    }
}
