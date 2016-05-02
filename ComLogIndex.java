package conversion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;


public class ComLogIndex {
	//@SuppressWarnings("null")
	static String[] temp= new String[109];
	static String[] tcl= new String[109];
	 
	public static void main(String [] args) {
		
		//RubtoTxt rub=new RubtoTxt();
		//ex e=new ex();
		//AllData gui= new AllData();{all files already exist.

        // The name of the file to open.
        String fileName = "Commit.txt";
        String fileName1 = "C:/Pragya/New folder/refLogs2/AP_Reflog.txt";
        String fileName2 = "C:/Pragya/New folder/refLogs2/RP_Reflog.txt";
        String fileName3 = "C:/Pragya/New folder/refLogs2/MM_Reflog.txt";
        String fileName4 = "C:/Pragya/New folder/refLogs2/RM_Reflog.txt";
        String fileName5 = "C:/Pragya/New folder/refLogs2/IT_Reflog.txt";
        String fileName6 = "C:/Pragya/New folder/refLogs2/PUC_Reflog.txt";
        String fileName7 = "C:/Pragya/New folder/refLogs2/PDM_Reflog.txt";
   		
        readFile(fileName,fileName1);
        
        readFile(fileName,fileName2);
       
        readFile(fileName,fileName3);
       
        readFile(fileName,fileName4);
        
        readFile(fileName,fileName5);
        
        readFile(fileName,fileName6);
        
        readFile(fileName,fileName7);
        
        
        }
	
	private static void readFile(String fileName, String fileName1) {
		// TODO Auto-generated method stub
		try{       
       	 FileInputStream read = new FileInputStream(fileName);
            BufferedReader reader=
           		 new BufferedReader(new InputStreamReader(read));
            FileInputStream readref = new FileInputStream(fileName1);
            BufferedReader readerRef=
           		 new BufferedReader(new InputStreamReader(readref));
                        
            File file = new File ("ComLog1.txt");
           FileWriter fw = new FileWriter(file,true); //the true will append the new data
           String str;
           String str1,st,cl;
           
           int li=0;
           while((str1=readerRef.readLine())!=null){
        	   cl= (str1.split("::",2)[0]).toString();
        	   
        	   st= (str1.split("commitID:",2)[1]).toString();	temp[li]=st.split("\\,",2)[0]; tcl[li]=cl;
        	  // System.out.println(tcl[li]);
       		li++;
       }
           
           int in; // index parameter for calling each string of array
           
           //This loop finds Commit Statement from Commit.txt and connects it to the CommitId from refactoring changes.
           
           while ((str=reader.readLine())!=null) {
        	  
        	  	for (in=0;in<li;in++){
           			st=temp[in];
           			if(str.contains(st)) {  
           				//fw.append appends new text into already existing text file. 
           				fw.append(str.split("-",2)[1]+ ","+ temp[in]+" , "+ tcl[in]+ fileName1.substring(fileName1.split("/",5)[4].length(),fileName1.split("_",2)[0].length())+"\n");//appends the string to the file
           			  }
           		}
          
           }

              
   // Always close files.
   reader.close(); //reader reads CommitLog from commit.txt
   readerRef.close();//readerRef reads refactoring files to connect statements to file
   fw.close();// closing new file created
   
   java.awt.Desktop.getDesktop().edit(file); //opens the file in desktop
   
 }
	catch(FileNotFoundException ex) {
       System.out.println(
           "Unable to open file '" );  }
  
       catch(Exception e){System.out.println("Null Pointer exception");}
       
}
	

}


