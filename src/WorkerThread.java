import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import shared.Message;


public class WorkerThread implements Runnable {
	  protected Socket clientSocket = null;
	    protected String serverText   = null;
	    Message d = null;
	    Message outputWord = new Message();
	    Dictionary dic;

	    public WorkerThread(Socket clientSocket, String serverText,Dictionary dic) {
	        System.out.println("Worker Thread created");
	    	this.clientSocket = clientSocket;
	        this.serverText   = serverText;
	        this.dic = dic;
	    }

	    public void run() {
	        try {
	            InputStream input  = clientSocket.getInputStream();
	         // Output Stream
	            ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
	            ObjectInputStream in = new ObjectInputStream(input);
	            try {
	            	//while(in.available() > 0) {
	            		d = (Message) in.readObject();
	            		
	            	//}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println("Recieved empty message from Client.");
					e.printStackTrace();
				}
	            
	            System.out.println(d.name);
	            dicFunctions(d);
	       
	            long time = System.currentTimeMillis();
	          
	            
	            output.writeObject(outputWord);
	            output.close();
	            input.close();
	            System.out.println("Request processed: " + time);
	            
	        } catch (IOException e) {
	            //report exception somewhere.
	            e.printStackTrace();
	        }
	    }

		private void dicFunctions(Message d2) {
			// TODO Auto-generated method stub
			synchronized(this) {	
				if(d2.function.equals("add")) {
					outputWord = dic.addToDic(d2);
				}
				else if (d2.function.equals("find")) {
					outputWord = dic.queryDic(d2);
				}
				else if(d2.function.equals("delete")) {
					outputWord = dic.removeFromDic(d2);
				}
			}
			
		}

	
}
