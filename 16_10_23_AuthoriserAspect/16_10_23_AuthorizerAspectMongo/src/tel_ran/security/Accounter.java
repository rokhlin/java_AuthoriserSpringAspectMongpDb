package tel_ran.security;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Accounter {
	String fileName;
	Map<String,int[]> callRejects = new HashMap<String, int[]>(); 
	
	
	
	public Accounter(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * restore map from the file
	 */
	public void restoreMap() {
		String line;
		try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
			while((line = reader.readLine()) != null) {
				String[] arr = line.split(" ");
				int[] values = {Integer.parseInt(arr[1]),Integer.parseInt(arr[2])};
				callRejects.put(arr[0],values);
			}
		} catch (Exception e1) {
			
		}
	}
	
	
	/**
	 * save map into file
	 */
	public void saveUpdatedMap() {
		   try(FileWriter writer = new FileWriter(fileName, false)){
			   
			   for(Entry<String, int[]> entry : callRejects.entrySet()) {
				   writer.append(entry.getKey()+" "+entry.getValue()[0] + " "+ entry.getValue()[1]+'\n');
			   }
			   
			   writer.flush();
		  
		   } catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void methodCallReject(String methodName, boolean call) {
		int[] value = (callRejects.get(methodName) != null )? callRejects.get(methodName):new int[2];
		value[(call ? 1 : 0)] +=1; 
		callRejects.put(methodName, value);
	}
}
