import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ReadWriteLogger<E> extends Thread
{ 
      //runs appContents
      public static void main(String[] args) throws IOException
      {
    	  fContents("Log.txt");
      }
      
      	   //Displays content in console
           static public String displayFileContent(String fName){
           String fContents = new String();   
           try {
        	   BufferedReader br = new BufferedReader(new FileReader(fName));
               StringBuilder sb = new StringBuilder();
               String line = br.readLine();

               while (line != null) {
                   sb.append(line);
                   sb.append(System.lineSeparator());
                   line = br.readLine();
               }
               String fContent = sb.toString();
               fContents = fContent;
               //prints log file data to the console
               System.out.println(fContent);
               br.close();
           } catch (IOException e) {
        	   System.out.println(" IOException: " + e.getMessage());
           }
           return fContents;
           }
           
           //Writes content to file
           static public void setFileContent(String fName, String fContents){
           DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
           Date date = new Date();
           try(FileWriter fW = new FileWriter(fName, true);
        		    BufferedWriter bW = new BufferedWriter(fW);
        		    PrintWriter pW = new PrintWriter(bW))
        		{
        	   		if(fContents.isEmpty()){
        		    pW.println(dateFormat.format(date) + " Info: File has been Created");
        		    }
        		    //runs the log for four entries
        	   		for(int i = 0; i < 4; i++) {
        		    pW.println(dateFormat.format(date) + " Info: log running...");
        		    // sets thread to sleep for a second
        		    try{
                    Thread.sleep(1000);
        		    }catch (InterruptedException e){
        		    	pW.println(dateFormat.format(date) + " Error: Thread interrupted.");
        		    }
        	   		}
        		    //IO Exception will display a message in console
        		} catch (IOException e) {
        		    System.out.println(dateFormat.format(date) + " IOException: " + e.getMessage());
        		}
           }
           
           // runs both display and set methods synchronized
           public static synchronized void fContents(String fName){      		 
        		   	String content = displayFileContent(fName);
        		   	setFileContent(fName, content);
           }

      }
