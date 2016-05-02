package conversion;
import java.io.*;
import java.util.*;

public class AllData {
	File file =new File("FormattedCommitlog1.txt");
    String statement = "";
    Scanner in = null;
    private String storeAllString="start";
	String st=" ";
    
	public AllData(){
		fileRead(); //reads the file output.txt to extract refactoring types and find the class name, method name, parameters changed etc.
		}
	
	private void fileRead(){
		try{
			FileReader read= new FileReader("output.txt"); //reads file 
			Scanner scan= new Scanner(read);
			
			//Finding exact refactoring type and getting the names from each line
			while(scan.hasNextLine()){
				String st=scan.nextLine()+"\n";
				String refType = st.split("\\(",2)[0];
				String temp= st.split("%.")[0];
				
				if(refType.equals("remove_parameter")){
					String fal1= st.split("'\\,'",2)[0];
					String renm= fal1.substring((fal1.split("#",2)[0].length()+1),fal1.length()-2);
					
					String fal2=st.split("\\,'",2)[1];
					String rp= fal2.substring(0, fal2.split("'\\,'",3)[0].length()-4);
					
					String classname= fal1.substring((fal1.split("%\\.",2)[0].length()+2),fal1.length()-renm.length()-3);
					//System.out.println("type: "+ refType +" , in method: "+ renm +" , removed parameter "+ rp + " class: "+ classname);
				
					String commitId1=checkForRefactoring(refType,classname,renm+"(");// extracting commitId from commitlog of branch.
					ref_fileWrite(classname,renm,rp,refType,commitId1);
				}
				
				else if (refType.equals("move_method")){
					String m= st.split("\\,'",2)[0];
					String method= st.substring((refType.length()+2),(m.length()-1));
					String faltu= st.split("%\\.",2)[1];
					String fromclass= faltu.split("'\\,'",2)[0];
					
					String fal= st.split("%.",2)[1];
					String toclass= fal.substring(((fal.split("%\\.",2)[0]).length()+2),((fal.split("'\\)",2)[0]).length()));
					///System.out.println("type: "+ refType +" , method/field name: "+ method +" , FromClass: "+ fromclass+ " ToClass: "+ toclass);
					
					String commitId2=checkForRefactoring(refType,fromclass,toclass,(method.split("\\(",2)[0]));// extracting commitId from commitlog of branch.
					ref_fileWrite(fromclass,method.split("\\(",2)[0],toclass,refType,commitId2);	
				}
			
				else if (refType.equals("move_field")){
					String m= st.split("\\,'",2)[0];
					String method= st.substring((refType.length()+2),(m.length()-1));
					String faltu= st.split("%\\.",2)[1];
					String fromclass= faltu.split("'\\,'",2)[0];
					
					String fal= st.split("%.",2)[1];
					String toclass= fal.substring(((fal.split("%\\.",2)[0]).length()+2),((fal.split("'\\)",2)[0]).length()));
					//System.out.println("type: "+ refType +" , method/field name: "+ method +" , FromClass: "+ fromclass+ " ToClass: "+ toclass);
					
					String commitId3=checkForRefactoring(refType,fromclass,toclass,method);// extracting commitId from commitlog of branch.
					ref_fileWrite(fromclass,method,toclass,refType,commitId3);	
					
				}
			
					else if (refType.equals("add_parameter")){
					String fal1= st.split("'\\,'",2)[0];
					String renm= fal1.substring((fal1.split("#",2)[0].length()+1),fal1.length()-2);
					
					String fal2=st.split("\\,'",2)[1];
					String ap= fal2.substring(0, fal2.split("'\\,'",3)[0].length()-3);
					
					String classname= fal1.substring((fal1.split("%\\.",2)[0].length()+2),fal1.length()-renm.length()-3);
					//System.out.println("type: "+ refType +" , in method: "+ renm +" , added parameter "+ ap + " class: "+ classname);
				
					String commitId7=checkForRefactoring(refType,classname,renm+"(");// extracting commitId from commitlog of branch.
					ref_fileWrite(classname,renm,ap,refType,commitId7);
				}
			
				else if (refType.equals("push_down_method")){
					String falpdm= st.split("'\\,'",2)[0];
					String pdm= falpdm.substring((falpdm.split("\\('",2)[0].length()+2),falpdm.length());
					
					String falpcn=st.split("\\,'",2)[1];
					String pcn= falpcn.substring((falpcn.split("%\\.",2)[0].length()+2), falpcn.split("'\\,'",3)[0].length());
					
					String falccn= falpcn.substring((falpcn.split("'\\,'",3)[0].length()),falpcn.length()-3);
					String ccn= falccn.substring((falccn.split("%\\.",3)[0].length()+2),falccn.length());
					//System.out.println("type: "+ refType +" , Methodname: "+ pdm +" , parent class "+ pcn + " Child Class: "+ ccn);
				    
					String commitId8=checkForRefactoring(refType,pcn,pdm,ccn);// extracting commitId from commitlog of branch.
					ref_fileWrite(pcn,pdm,ccn,refType,commitId8);
				}
			
				else if (refType.equals("pull_up_constructor_body")){
					String falpc= st.split("'\\)",2)[0];
					String pc= falpc.substring((falpc.split("%\\.",2)[0].length()+2),falpc.length());
					String con= pc;
					//System.out.println("type: "+ refType +" , class in which constructor pulled "+ pc);
					
					String commitId9=checkForRefactoring(refType,pc,con);// extracting commitId from commitlog of branch.
					ref_fileWrite(pc,con,"NA",refType,commitId9);
				}
				
				else if (refType.equals("extract_superclass")){
					String falsubc= st.split("'\\,'",2)[0];
					String supc= falsubc.substring((falsubc.split("%\\.",2)[0].length()+2),falsubc.length());
					
					String faltc=st.split("\\,'",2)[1];
					String toclass= faltc.substring((faltc.split("%\\.",2)[0].length()+2), faltc.length()-4);
					//System.out.println("type: "+ refType +" , from class: "+ supc +" , To "+ toclass);
					
					String commitId4=checkForRefactoring(refType,supc,toclass);// extracting commitId from commitlog of branch.
					ref_fileWrite(supc,"no method",toclass,refType,commitId4);	
					
				}
				
				else if (refType.equals("extract_subclass")){
					String falsubc= st.split("'\\,'",2)[0];
					String subc= falsubc.substring((falsubc.split("%\\.",2)[0].length()+2),falsubc.length());
					
					String faltc=st.split("\\,'",2)[1];
					String toclass= faltc.substring((faltc.split("%\\.",2)[0].length()+2), faltc.length()-4);
					//System.out.println("type: "+ refType +" , from sub class: "+ subc +" , To Class: "+ toclass);
					
					String commitId5=checkForRefactoring(refType,subc,toclass);// extracting commitId from commitlog of branch.
					ref_fileWrite(subc,"NA",toclass,refType,commitId5);	
					
				}
			
				else if (refType.equals("rename_method")){
					String falrenm= st.split("'\\,'",2)[0];
					String renm= falrenm.substring((falrenm.split("#",2)[0].length()+1),falrenm.length());
					
					String faltm=st.split("\\,'",2)[1];
					String tom= faltm.substring((faltm.split("#",2)[0].length()+1), faltm.split("'\\,'",3)[0].length());
					
					String classname= faltm.substring((faltm.split("%\\.",3)[1].length()),faltm.length()-3);
					String cln= classname.substring((classname.split("%\\.",3)[0].length()+2),classname.length()-1);
					//System.out.println("type: "+ refType +" , OldMethodname: "+ renm +" , NewMethodname "+ tom + " InClass: "+ cln);
					
					String commitId6=checkForRefactoring(refType,cln,renm,tom);// extracting commitId from commitlog of branch.
					ref_fileWrite(cln,renm,tom,refType,commitId6);
				
				}
			
				
				else if (refType.equals("inline_temp")){
					String m= st.split("'\\,'",2)[0];
					String mn= m.split("\\('",2)[1];
					String method= st.split("'\\,'",3)[1];
				
					String falmnam=st.split("%\\.",2)[1];
					String mnam=falmnam.substring(falmnam.split("#",2)[0].length()+1,falmnam.length()-4);
					
					String classn=falmnam.split("#",2)[0] ;
					//System.out.println("type: "+ refType +" , identifier: "+ mn + " ,Expression: "+ method + " ,method_Name: "+ mnam +" classname: "+ classn);
				
					String commitId9=checkForRefactoring(refType,classn,mnam, mn);// extracting commitId from commitlog of branch.
					ref_fileWrite(classn,mn,mnam,refType,commitId9);
				}
			
				else
				{
					System.out.println("refactoring not checked work in progress");
				}

				storeAllString=storeAllString+temp;
			}
			//System.out.println(storeAllString);
		scan.close();
		}
		catch(Exception e){System.out.println("error"); e.printStackTrace();}
	}
	
	
	//Method to check for refactoring in which one class name and method are present. generally for remove parameter and add parameter.
	private String checkForRefactoring(String refType, String classname, String mname) {
		// TODO Auto-generated method stub
    int linno=0;
    String tempstmt = ""; 	
    if(classname.contains("#")){classname= classname.split("#",2)[1];}
    
    if( mname.contains("#")){ mname= mname.split("#",2)[1];}
    
    if(mname.equals("<init>(")){mname= classname+"(";}
      try {
        in = new Scanner(file);
      
        while(in.hasNext())
        {
            String line=in.nextLine();
            if(line.contains(classname+".java")&& line.contains(mname)){
            		statement+=line.split(" - ",3)[0];
            		statement+=" , ";
            		linno++;}
          	}
        if(refType.equals("remove_parameter")){
        	statement= statement.split("\\,",2)[0];
        }
        if(refType.equals("add_parameter")){
        	statement= statement.substring((statement.length()-statement.split("\\,",2)[0].length()+1),statement.length());
        }
            tempstmt+=statement;
            System.out.println(linno+ "-" +statement);
                
      in.close();  
    }catch (NullPointerException ne) {
        // TODO Auto-generated catch block
        ne.printStackTrace();
    } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    statement=" ";
	return tempstmt;
	}
	
	//Method to check for refactoring in which class name changes with method name change. Method overloading.
	private String checkForRefactoring(String refType,String fromclass, String toclass, String method) {
		// TODO Auto-generated method stub
		int linno=0;
     	String stmt=" ";
     	method=method.split("\\(\\)",2)[0];
     	if(toclass.contains("\\(\\)")){toclass=toclass.split("\\(\\)",2)[0];}
     	if(fromclass.contains("#")){fromclass= fromclass.split("#",2)[1];}
     	if(toclass.contains("#")){toclass= toclass.split("#",2)[1];}
     	if( method.contains("#")){ method= method.split("#",2)[1];}
        
        if(method.equals("<init>(")){method= toclass;}
        
	      try {
	        in = new Scanner(file);
	      
	        while(in.hasNext())
	        {
	            String line=in.nextLine();
	            if((line.contains(fromclass+".java")&& line.contains(method))
	            		||(line.contains(toclass+".java")&& line.contains(method))
	            		||(line.contains(fromclass)&& line.contains(method))
	            		||(line.contains(toclass)&& line.contains(method))) {
	            	
	            		statement+=line.split(" - ",2)[0];
	            		statement+=",";
	            		linno++;}
	            
	            if(toclass.contains("\\(\\)")){
	            	toclass=toclass.split("\\(\\)",2)[0];
	            	if((line.contains(toclass)&& line.contains(fromclass+".java"))||(line.contains(method)&& line.contains(fromclass+".java"))){
	            		statement+=line.split(" - ",2)[0];
	            		statement+=",";
	            		linno++;
	            		}
	            	}
	                          		          
	        }
	            
	        if(refType.equals("remove_parameter")){
            	statement= statement.split("\\,",2)[0];
            }
            if(refType.equals("add_parameter")){
            	statement= statement.substring((statement.length()-statement.split("\\,",2)[0].length()+1),statement.length());
            }
          
	        System.out.println(linno+ "-" +statement);
	        stmt+=statement;     
	       
	    }catch (NullPointerException ne) {
	        // TODO Auto-generated catch block
	        ne.printStackTrace();
	    } catch (FileNotFoundException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
		in.close();
		statement=" ";
		return (stmt);
		}
	
	//Method to write the refactoring files according to their types and with commitId's to extract specific commit statement.
	private void ref_fileWrite(String classname, String m, String pd,String refType, String commit) {
		
		// All filenames are mentioned here for future purposes change to dynamically getting names from the user.
		String filename1="C:/Pragya/New folder/refLogs2/RP_Reflog.txt";
		String filename2="C:/Pragya/New folder/refLogs2/AP_Reflog.txt";
		String filename3="C:/Pragya/New folder/refLogs2/MF_Reflog.txt";
		String filename4="C:/Pragya/New folder/refLogs2/MM_Reflog.txt";
		String filename5="C:/Pragya/New folder/refLogs2/RM_Reflog.txt";
		String filename6="C:/Pragya/New folder/refLogs2/PDM_Reflog.txt";
		String filename7="C:/Pragya/New folder/refLogs2/PUC_Reflog.txt";
		String filename8="C:/Pragya/New folder/refLogs2/ESup_Reflog.txt";
		String filename9="C:/Pragya/New folder/refLogs2/ESub_Reflog.txt";
		String filename10="C:/Pragya/New folder/refLogs2/EI_Reflog.txt";
		String filename11="C:/Pragya/New folder/refLogs2/IT_Reflog.txt";
		
		
		try{
			FileWriter fw,fw1,fw2,fw3,fw4,fw5,fw6,fw7,fw8,fw9,fw10;
			
			switch(refType)
			{
			case "remove_parameter": fw=new FileWriter(new File(filename1),true);
			//Write entire string buffer into the file
			fw.append(classname+".java ,"+m+","+pd+","+commit+"\n");
			fw.close(); break;
			
			case "add_parameter": fw1=new FileWriter(new File(filename2),true);
			//Write entire string buffer into the file
			fw1.append(classname+".java ,"+m+","+pd+","+commit+"\n");
			fw1.close(); break;
			
			case "move_field": fw2=new FileWriter(new File(filename3),true);
			//Write entire string buffer into the file
			fw2.append(classname+".java ,"+m+","+pd+","+commit+"\n");
			fw2.close(); break;
			
			case "move_method": fw3=new FileWriter(new File(filename4),true);
			//Write entire string buffer into the file
			fw3.append(classname+".java ,"+m+","+pd+","+commit+"\n");
			fw3.close(); break;
			
			case "rename_method": fw4=new FileWriter(new File(filename5),true);
			//Write entire string buffer into the file
			fw4.append(classname+".java ,"+m+","+pd+","+commit+"\n");
			fw4.close(); break;
			
			case "push_down_method": fw5=new FileWriter(new File(filename6),true);
			//Write entire string buffer into the file
			fw5.append(classname+".java ,"+m+","+pd+","+commit+"\n");
			fw5.close(); break;
			
			case "pull_up_constructor_body": fw6=new FileWriter(new File(filename7),true);
			//Write entire string buffer into the file
			fw6.append(classname+".java ,"+m+" ,"+pd+","+commit+"\n");
			fw6.close(); break;
			
			case "extract_superclass": fw7=new FileWriter(new File(filename8),true);
			//Write entire string buffer into the file
			fw7.append(classname+".java ,"+m+" ,"+pd+","+commit+"\n");
			fw7.close(); break;
			
			case "extract_subclass": fw8=new FileWriter(new File(filename9),true);
			//Write entire string buffer into the file
			fw8.append(classname+".java ,"+m+" ,"+pd+","+commit+"\n");
			fw8.close(); break;
			
			case "extract_interface": fw9=new FileWriter(new File(filename10),true);
			//Write entire string buffer into the file
			fw9.append(classname+".java ,"+m+" ,"+pd+","+commit+"\n");
			fw9.close(); break;
			
			case "inline_temp": fw10=new FileWriter(new File(filename11),true);
			//Write entire string buffer into the file
			fw10.append(classname+".java ,"+m+" ,"+pd+","+commit+"\n");
			fw10.close(); break;
			
			default :System.out.println("more");break;
			
			}
		
		
		}
		catch (Exception re){};
		
	}
		
	}