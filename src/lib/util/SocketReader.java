package lib.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author Finn Frankis
 * @version Aug 14, 2018
 */
public class SocketReader {
    private static ServerSocket serverSocket;
    private static Socket socket;
    private static BufferedReader socketStream;

    public static void makeConnection () {
        try {
            serverSocket = new ServerSocket(Constants.SOCKET_PORT);
            new Thread() {
                public void run () {
                    try {
                        SocketReader.socket = SocketReader.serverSocket.accept();
                        socketStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isConnected () {
        return socket != null && socket.isBound();
    }

    public static String getValue ()
    {
        try {
            if (socketStream != null)
                return socketStream.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
