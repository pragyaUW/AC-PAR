package conversion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
 
public class ex
{
	public ex()
	{
		//Enter name of the file here
		String filename="C://Users/Pragya/git/cassandra2.1/commitIDCommit.txt";
		//String filename="example.txt";
		//Enter starting line here
		int startline=0;
		//Enter number of lines here.
		int numlines=1;
 		delete(filename,startline,numlines);
	}
	
	void delete(String filename, int startline, int numlines)
	{try
	{
		BufferedReader br=new BufferedReader(new FileReader(filename));

		//String buffer to store contents of the file
		StringBuffer sb=new StringBuffer("");

		//Keep track of the line number
		int linenumber=1;
		String line;
        
		while((line=br.readLine())!=null)
		{
			
			//Store each valid line in the string buffer
			if(linenumber<startline||linenumber>=startline+numlines)
				
				if ((line.startsWith(":: commitID:"))){
					sb.append("\n"+ line);
					}
				else{
					line= line.replace("/"," "); 
					line= line.replace("    "," "); 
					line= line.replace("   "," "); 
					line= line.replace("  "," "); 
					//line=line.replace(".java"," .java"); 
					sb.append(line);}
			linenumber++;
				
		}
		if(startline+numlines>linenumber)
			System.out.println("End of file reached.");
		br.close();
		String filename1="FormattedCommitlog1.txt";
		FileWriter fw=new FileWriter(new File(filename1));
		//Write entire string buffer into the file
		fw.write(sb.toString());
		fw.close();
	}
	catch (Exception e)
	{
		System.out.println("Something went horribly wrong: "+e.getMessage());
	}
}
}
