package conversion;
import java.io.*;

public class RubtoTxt {
    
	public RubtoTxt() {

        // The name of the file to open.
        String fileName = "C:/Pragya/RefFinderOutput/.metadata/.plugins/LSclipse/output/output.rub";
             // This will reference one line at a time
       
        try {
            // FileReader reads text files in the default encoding.
           	FileInputStream read = new FileInputStream(fileName);
             BufferedReader reader=
            		 new BufferedReader(new InputStreamReader(read));
            File file = new File ("output.txt");
            PrintWriter printWriter = new PrintWriter (file);
            String str;
            //reads every line of file to write it in text format and replace "" by '' to call regex.
            while ((str=reader.readLine())!=null) {
            	//System.out.println(line);
            	str=str.replace("\"","'");
            	printWriter.println(str);
            }
 
    
            // Always close files.
            
            reader.close();
            printWriter.close ();   
            java.awt.Desktop.getDesktop().edit(file);
        }
        catch(FileNotFoundException ex) {
        	System.out.println(
            "Unable to open file '" + 
            fileName + "'");                
        }
   
        catch(Exception e){e.printStackTrace();}
        
		}
	}
