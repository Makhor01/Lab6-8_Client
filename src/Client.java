import data.Message;
import data.Product;
import exeptions.*;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Client {

    private static Socket clientSocket; //сокет для общения
    private static BufferedReader reader; // нам нужен ридер читающий с консоли, иначе как
    // мы узнаем что хочет сказать клиент?
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет

    static Scanner userScanner = new Scanner(System.in);;
    static ProductAsk productAsk = new ProductAsk(userScanner);
    private static List<String> scriptStack = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        Message message = new Message("123","123",null);
        boolean exitFlag = false;

        // адрес - локальный хост, порт - 4004, такой же как у сервера

        do {
            try {
                String[] userCommand = {"", ""};
                userCommand = (userScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                message.setText(userCommand[0]);
                message.setValue(userCommand[1]);




                if (userCommand[0].equals("exit")) {
                    break;
                }
                if (userCommand[0].equals("execute_script")) {
                    scriptMode(message.getValue());
                } else {
                    try {
                        try {
                            if (userCommand[0].equals("update") || userCommand[0].equals("insert")) {

                                Product sendProduct = productAsk.askProduct();
                                try {
                                    sendProduct.setId(Integer.valueOf(message.getValue()));
                                    message.setProduct(sendProduct);
                                } catch (NumberFormatException exception) {
                                    System.out.println("Id должнне быть представлен числом!");
                                }
                            }


                            //clientSocket = new Socket("localhost", 4004); // этой строкой мы запрашиваем
                            //  у сервера доступ на соединение
                           // Socket clientSocket = null;
                            Socket clientSocet = null;
                            boolean isServerAvailable = false;
                            System.out.print("Идет подключение...");
                            while (!isServerAvailable) {
                                try {
                                    clientSocket = new Socket("localhost", 1234);
                                    isServerAvailable = true;
                                    System.out.println(" подключено.");
                                } catch (IOException e) {
                                }
                            }

                            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                            out.writeObject(message);
                            out.flush();

                            Message response = (Message) in.readObject();
                            System.out.println("Результат команды " + response.getValue()); // 123
                            clientSocket.close();
                        } catch (ClassNotFoundException | IncorrectInputInScriptException e) {
                            throw new RuntimeException(e);
                        }
                    } catch (IOException e) {
                        System.err.println(e);
                    }

                }
                //clientSocket.close();

            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }

        } while (true) ;
            System.out.println("сеанс завершено");



    }
    public static boolean scriptMode(String argument) {
        String[] userCommand = {"", ""};
        int commandStatus;
        scriptStack.add(argument);
        try(Scanner scriptScanner = new Scanner(new File(argument))) {
            /* Start scanner mode*/
            if (!scriptScanner.hasNext()) throw new NoSuchElementException();
            Scanner tmpScanner = productAsk.getUserScanner();
            productAsk.setUserScanner(scriptScanner);
            productAsk.setFileMode();
            String chekOn = "";
            do {
                //clientSocket = new Socket("localhost", 4004); // этой строкой мы запрашиваем
                Socket clientSocket = null;
                boolean isServerAvailable = false;
                while (!isServerAvailable) {
                    try {
                        clientSocket = new Socket("localhost", 4004);
                        isServerAvailable = true;
                    } catch (IOException e) {

                    }
                }
                //  у сервера доступ на соединение
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

                userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                while (scriptScanner.hasNextLine() && userCommand[0].isEmpty()) {
                    userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                }
                System.out.println( String.join(" ", userCommand));
                if (userCommand[0].equals("execute_script")) {
                    for (String script : scriptStack) {
                        if (userCommand[1].equals(script)) throw new ScriptRecursionException();
                    }
                }

                Message message = new Message(userCommand[0],userCommand[1],null);

                if (userCommand[0].equals("update")||userCommand[0].equals("insert")){
                    Product sendProduct = productAsk.askProduct();// throw new IncorrectInputInScriptException();
                    try {
                        sendProduct.setId(Integer.valueOf(message.getValue()));
                        message.setProduct(sendProduct);
                    } catch (NumberFormatException exception) {
                        System.out.println("Id должнне быть представлен числом!");
                    }
                }
                out.writeObject(message);
                out.flush();

                Message response = (Message) in.readObject();
                clientSocket.close();
                in.close();
                out.close();
                System.out.print("Результат команды - " + response.getValue()); // "Hi there!"
                System.out.println(); // 123
                chekOn = response.getText();


            } while (chekOn.equals("true") && scriptScanner.hasNextLine());
            productAsk.setUserScanner(tmpScanner);
            productAsk.setUserMode();
            if (chekOn.equals("false")  && !(userCommand[0].equals("execute_script") && !userCommand[1].isEmpty()))
                System.out.println("Проверьте скрипт на корректность введенных данных!");
            return chekOn.equals("true");
        } catch (FileNotFoundException exception) {
            System.out.println("Файл со скриптом не найден!");
        } catch (NoSuchElementException exception) {
            System.out.println("Файл со скриптом пуст!");
        } catch (ScriptRecursionException exception) {
            System.out.println("Скрипты не могут вызываться рекурсивно!");
        } catch (IllegalStateException exception) {
            System.out.println("Непредвиденная ошибка!");
            System.exit(0);
        } catch (IOException | IncorrectInputInScriptException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            scriptStack.remove(scriptStack.size()-1);
        }
        return false;
    }
}
