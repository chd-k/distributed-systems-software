/*
 * Выполнила: Грибовская Анастасия, группа 7362
 * Задание: 2 Передача звукового файла через UDP сокет
 * Дата выполнения: 29.09.2021
 * Версия: 0.1
 */

// ------------------------------------------- //

/*
 * Данная программа выполняет функции серверного приложения UDP-протокола.
 * В начале работы создается серверный датаграммный сокет. Далее сокет
 * переходит в режим ожидания. При получении пакета от клиента сервер
 * собирает данные в mp3 файл. Сервер заканчивает работу после 10 секунд
 * ожидания нового пакета.
 */

import java.io.*;
import java.net.*;

public class UDP_Server{
//    порт для запуска UDP-сокета сервера
    public final static int PORT = 1099;
    public static void main(String[] args) throws IOException{
//        создание сокета для приема датаграмм на сервере
        DatagramSocket serverSocket = new DatagramSocket(PORT);
//        создание буфера для принимаемых данных
        byte[] receivingDataBuffer = new byte[1024];
//        создание пакета для приема данных
        DatagramPacket receivedPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);
        System.out.println("Ожидание соединения с клиентом...");
//        создание потока для формирования полученной информации в файл
        FileOutputStream outStream = new FileOutputStream("received_file.mp3");
        try {
            serverSocket.setSoTimeout(10000);
            while (true) {
//                ожидание пакета
                serverSocket.receive(receivedPacket);
//                запись данных в поток
                outStream.write(receivingDataBuffer);
//                очистка потока
                outStream.flush();
            }
        } catch (SocketTimeoutException e) {
            outStream.close();
            System.out.println("Приём данных окончен");
        }
        System.out.println("Завершение...");
        serverSocket.close();
    }
}
