import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import javax.net.ServerSocketFactory;

import java.io.IOException;


public class Server implements Runnable {
	 int serverPort = 9000;
	 ServerSocket serverSocket = null;
	 boolean isStopped    = false;
	 Thread  runningThread= null;
	 Dictionary dic;
	

	    public Server(int port, Dictionary dic){
	        this.serverPort = port;
	        this.dic = dic;
	    }

	    public void run(){
	        synchronized(this){
	            this.runningThread = Thread.currentThread();
	        }
	        openServerSocket();
	        while(!	isStopped()){ 	
	            Socket clientSocket = null;
	            try {
	                clientSocket = this.serverSocket.accept();
	            } catch (IOException e) {
	                if(isStopped()) {
	                    System.out.println("Server Stopped.") ;
	                    return;
	                }
	                throw new RuntimeException(
	                    "Error accepting client connection", e);
	            }
	            new Thread(
	                new WorkerThread(
	                    clientSocket, "Multithreaded Dictionary Server",dic)
	            ).start();
	        }
	        System.out.println("Server Stopped.") ;
	    }


	    private synchronized boolean isStopped() {
	        return this.isStopped;
	    }
	    public synchronized void stop(){
	        this.isStopped = true;
	        try {
	            this.serverSocket.close();
	        } catch (IOException e) {
	            throw new RuntimeException("Error closing server"+serverPort, e);
	        }
	    }

	    private void openServerSocket() {
	        try {
	            this.serverSocket = new ServerSocket(this.serverPort);
	        } catch (IOException e) {
	            throw new RuntimeException("Cannot open port", e);
	        }
	    }
}
