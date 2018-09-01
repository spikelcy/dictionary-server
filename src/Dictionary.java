import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


import shared.Message;

public class Dictionary {
	private Map<String,String> map = new ConcurrentHashMap<String,String>();
	Message output = new Message();
	
	public Dictionary(String file) {
		try {
			fillDic(map,file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void fillDic(Map<String,String> map,String filename) throws FileNotFoundException {
		System.out.println("Starting filling");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filename));

            // The rest of the code
        } catch (FileNotFoundException e) {
            System.err.println("Unable to find input file: " + filename);
            System.err.println("Terminating...");
            System.exit(3);
        }
		
		  String st;
		  try {
			while ((st = br.readLine()) != null) {
			    System.out.println(st);
			    String [] res = st.split(";");
			    map.put(res[0], res[1]);
			  }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not write file to dictionary");
			e.printStackTrace();
		}
		   
	}
	
	public Message removeFromDic(Message d2) {
		// TODO Auto-generated method stub
		
			if(map.get(d2.name) == null) {
				output.name="Error";
				output.meaning="Word not found";	
				System.out.println("Not Found");
			}else {
				map.remove(d2.name);
				output.name="Success";
				output.meaning="Word deleted";
				System.out.println("result returned");
			}
			return output;
		
	}
	
	public Message queryDic(Message d2) {
		// TODO Auto-generated method stub
		
			
			// System.out.println("Locked:"+ Thread.holdsLock(this) ); 
			if(map.get(d2.name) == null) {
				output.name="Error";
				output.meaning="Word not found";	
				System.out.println("Not Found");
			}else {
				String result = map.get(d2.name);
				output.name=d2.name;
				output.meaning=result;	
				System.out.println("result returned");
			}
			return output;
		
	}

	public Message addToDic(Message d2) {
		// TODO Auto-generated method stub
		if(!map.containsKey(d2.name)) {
			map.put(d2.name, d2.meaning); 
			if(map.containsKey(d2.name)) {
				output.name="Success";
				output.meaning="Word added to Dictionary";
				System.out.println("added to Dictionary");
			}else {
				output.name="Error";
				output.meaning="Word could not be added to Dictionary";
				System.out.println("Error");
			}
		}else {
			output.name="Error";
			output.meaning="Word already exist in Dictionary";
			System.out.println("Error");
		}
		return output;
			
		
	}
}
