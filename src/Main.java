
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		
		String file = "C:\\Users\\Spike\\eclipse-workspace\\Assignment1\\dic.txt";
		Dictionary dic = null;
		int port = 0;
		if(args.length <= 2) {
			dic = new Dictionary(args[1]);
			try {
				port = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				System.out.println("port has to be a number!");
				e.printStackTrace();
			}
			
		}else {
			System.out.println("Incorrect number of arguments!");
			System.exit(0);
		}
		
		Server server = new Server(port,dic);
		new Thread(server).start();
		
		
	}
	
	
}
