package conversion;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class GettingRefactoringTypes {
	  HashMap<String, String> hmap = new HashMap<String, String>();
	public HashMap<String, String> gRTypes(){
	try{       
      	 FileInputStream read = new FileInputStream("Comlog111.txt");
           BufferedReader reader=
          		 new BufferedReader(new InputStreamReader(read));
         
          String str1,classname=" ",methodname=" ",refType=" ", st="";
        
         
         String[][] temp2=new String[20][3];
          int lin=1;
          while((str1=reader.readLine())!=null){
        	  hmap.put(str1.split(":",2)[0], "ID"+lin);
        	  int i=0;
        	  classname = str1.substring(str1.split(" \\, ",2)[0].length()+2,str1.split(".java",2)[0].length()+5);
        	  methodname= str1.substring(str1.split(".java",2)[0].length()+5,str1.split(" \\, folder/",2)[0].length()-20);
        	  refType= str1.substring(str1.split("/refLogs2/",2)[0].length()+10,str1.length());
        	  if(hmap.containsKey(str1.split(" : ",2)[0])){
        		  st+= refType+" , "+classname+" , "+ methodname+"," ;
        		  hmap.put(str1.split(":",2)[0],st); 
        	  } else{
        		  st = refType+" , "+classname+" , "+ methodname;
        	  	  hmap.put(str1.split(":",2)[0], st);
        	  }
        	  lin++;
         }  
          
          /* Display content using Iterator*/
          Set set = hmap.entrySet();
          Iterator iterator = set.iterator();
         while(iterator.hasNext()) {
             Map.Entry mentry = (Map.Entry)iterator.next();
             //System.out.print( mentry.getKey());
             //System.out.println(mentry.getValue());
          }
          	
// Always close files.
  reader.close(); //reader reads CommitLog from commit.txt

}
	catch(FileNotFoundException ex) {
      System.out.println( " Unable to open file " );  }
 
      catch(Exception e){System.out.println("Null Pointer exception");e.printStackTrace();}
	return hmap;

}
	 public static void main(String[] args){
		 GettingRefactoringTypes gt=new GettingRefactoringTypes();
		 gt.gRTypes();
	 }
}
	
