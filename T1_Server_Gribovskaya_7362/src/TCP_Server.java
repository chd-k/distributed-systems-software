/*
 * Выполнила: Грибовская Анастасия, группа 7362
 * Задание: 1 Передача сообщений TCP через сокет
 * Дата выполнения: 26.09.2021
 * Версия: 0.1
 */

// ------------------------------------------- //

/*
 * Данная программа выполняет функции серверного приложения TCP-протокола.
 * В начале работы создается серверный сокет. Далее сокет переходит в режим
 * ожидания. При получении запроса от клиента сервер принимает сообщение,
 * разбирает его на составные части, выводит консольное сообщение с номером
 * полученного сообщения и направляет клиенту такое же сообщение. Сервер
 * заканчивает работу при получении сообщения END.
 */


import java.net.*;
import java.io.*;
import java.util.Objects;

public class TCP_Server {
//    локальный порт сервера
    public static final int PORT = 8080;
    public static void main(String[] args) throws IOException {
//        создание серверного сокета при помощи java.net.ServerSocket
        ServerSocket serverSocket = new ServerSocket(PORT);
//		вывод в консоль сообщения о старте
        System.out.println("Старт " + serverSocket);
//		переход в режим ожидания запроса
        Socket socket = serverSocket.accept();
//		вывод в консоль сообщения о подтверждении соединения
        System.out.println("Соединение подтверждено " + socket);
//		перехват исключений пакета java.io
        try {
//			создание потока ввода для сокета
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//			создание объекта для вывода в поток вывода сокета
            PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),
                    true);
            while (true) {
//				чтение сообщений
                String[] inputString = input.readLine().split(" ");
//				завершение цикла приема сообщений при получении "END"
                if (Objects.equals(inputString[0], "END")) break;
                System.out.println(inputString[0] + " received");
                output.println(inputString[0] + " received");
            }
        } catch (IOException e) {
//			вывод информации об исключении
            System.out.println("Исключение: " + e.getMessage());
        } finally {
            System.out.println("Завершение...");
//		    закрытие сокета
            socket.close();
//		    закрытие сокета
            serverSocket.close();
        }
    }
}
