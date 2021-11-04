/*
 * Выполнила: Грибовская Анастасия, группа 7362
 * Задание: 2 Передача звукового файла через UDP сокет
 * Дата выполнения: 29.09.2021
 * Версия: 0.1
 */

// ------------------------------------------- //

/*
 * Данная программа выполняет функции клиентского приложения UDP-протокола.
 * В начале работы создается датаграммный сокет. Далее звуковой файл по частям,
 * равным 1024 байта, передается на сервер.
 */

import java.io.*;
import java.net.*;

public class UDP_Client {
//    локальный порт, на который будут посылаться пакеты
    public static final int PORT = 1099;
    public static void main(String[] args) throws IOException {
//        создание сокета для отправки датаграмм
        DatagramSocket clientSocket = new DatagramSocket();
//        формирование IP-адреса сервера
        InetAddress IPAddress = InetAddress.getByName("localhost");
//        буфер для передаваемых данных
        byte[] dataToSendBuffer = new byte[1024];
        try {
//            создание потока для отправляемого файла
            FileInputStream musicFile = new FileInputStream("antonio-vivaldi-autumn.mp3");
            System.out.println("Отправка начата...");
//            чтение файла, пока не будет достигнут конец файла
            while (musicFile.read(dataToSendBuffer) != -1) {
                DatagramPacket packet = new DatagramPacket(dataToSendBuffer, dataToSendBuffer.length, IPAddress, PORT);
//                отправка пакета
                clientSocket.send(packet);
            }
            System.out.println("Файл отправлен");
        } catch (FileNotFoundException e) {
            System.out.println("Исключение: " + e.getMessage());
        }
        System.out.println("Завершение...");
        clientSocket.close();
    }
}
