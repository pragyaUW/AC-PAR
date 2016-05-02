package conversion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class DisplayForRef {

	private String path="FormattedCommitlog.txt";
	private String storeAllString="start";
	String st=" ";
	private JButton sveCloseBtn= new JButton("Save Changes and close");
	private JButton closeBtn= new JButton("Exit without saving");
	private JTextField ctxtField= new JTextField();
	private JLabel clabel=new JLabel("Class Name");
	
	private JTextField mtxtField= new JTextField();
	private JLabel mlabel=new JLabel("Method Name");
		
	private JTextField ptxtField= new JTextField();
	private JLabel plabel=new JLabel("Refactoring Type");
	
	private JFrame frame= new JFrame("Reasons for Refactoring Changes");
	private JTextArea txtArea= new JTextArea();
	private JTextArea txtArea1= new JTextArea();
	GettingRefactoringTypes gt=new GettingRefactoringTypes();
	 
	
	private	JList<String> listbox1;
	 String te[]={"Move_Method_Refactoring","Remove_Parameter_Refactoring","Rename_Method_Refactoring","Added_Parameter_Refactoring","inline_Template","Push_Down_Method_Refactoring","Pull_Up_Constructor_Refactoring"};


	private void fileRead(){
		try{
			FileReader read= new FileReader("C:/Users/Pragya/git/cassandra2.1/CHANGES.txt");
			Scanner scan= new Scanner(read);
			while(scan.hasNextLine()){
				String temp=scan.nextLine()+"\n";
				storeAllString=storeAllString+temp;
			}
			txtArea1.setText(storeAllString);
		scan.close();
		}
		catch(Exception e){}
	}
	
	private void panels(){
		JPanel panel= new JPanel(new GridLayout(2,1));
		panel.setBorder(new EmptyBorder(5,5,5,5));
		JPanel rightPanel= new JPanel(new GridLayout(5,0,5,5));
		rightPanel.setBorder(new EmptyBorder(10,5,0,10));
		
		//JPanel rightBPanel= new JPanel(new GridLayout(8,0,5,5));
		JScrollPane scrollBarForTxtArea=new JScrollPane(txtArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JScrollPane scrollBarForTxtArea1=new JScrollPane(txtArea1,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		listbox1 = new JList<String>(te);
		listbox1.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listbox1.setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane(listbox1);
		listScroller.setPreferredSize(new Dimension(10, 20));
		
		panel.add(scrollBarForTxtArea);
		panel.add(scrollBarForTxtArea1);
		frame.add(panel);
		frame.getContentPane().add(rightPanel,BorderLayout.EAST);
		
		
		//rightPanel.add(sveCloseBtn);
		//rightPanel.add(closeBtn);
		//rightPanel.add(clabel);
		//rightPanel.add(ctxtField);
		//rightPanel.add(mlabel);
		//rightPanel.add(mtxtField);
		rightPanel.add(plabel);
		//rightPanel.add(rightBPanel);
		//rightPanel.add(ptxtField);
		rightPanel.add(listbox1);
		
		closeBtn.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent ae){
			frame.dispose();
		}
		});
		
		sveCloseBtn.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent ae){
			saveBtn();
			frame.dispose();
		}
		});
		frame.setSize(1000,700);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		txtArea.addCaretListener(new CaretListener(){ 
			public void caretUpdate( CaretEvent ce){ 
				JTextArea editArea=(JTextArea)ce.getSource();
				int linenum =1;
				int columnnum=1;
					try{
					int caretPos= editArea.getCaretPosition();
					linenum= editArea.getLineOfOffset(caretPos);
					columnnum=caretPos-editArea.getLineStartOffset(linenum);
					linenum++;
				}
				catch (Exception ex){}
				updateStatus(linenum, columnnum);
				String st=txtArea.getSelectedText();
				//System.out.println(rmp);
				 String te1= st.substring(3,st.length());
				 String temp=te1.split("\\(",2)[0];
				 //String temp1=te1.substring(te1.split("\\(",2)[1].length(),te1.length()-1);
				 ptxtField.setText(temp);
			
				}
			});
		callCommitId();
		 //UseGrep ug= new UseGrep("AbstractByteOrderedPartitioner",path);
		}
	
	
	 
	protected void callCommitId() {
		// TODO Auto-generated method stub
		try{       
	       	 FileInputStream read = new FileInputStream("ComLogU.txt");
	            BufferedReader reader=
	           		 new BufferedReader(new InputStreamReader(read));
	           /*File file = new File ("ComLog.txt");
	           FileWriter fw = new FileWriter(file,true); //the true will append the new data*/
	           String str1,str = "", temp="";
	          
	          // String[] temp=new String[50];
	           int lin=1;
	           while((str1=reader.readLine())!=null){
	        	  
	        	   if(str1.split("/",2)[1].equals("refLogs2/MM"))
	       	        	{	//te[lin]=" Move Method Refactoring";
	       	        		//lin++;
	        		   		temp+=  str1.split(":",2)[0]+"\n";
	        		   	}
	        	   if(str1.split("/",2)[1].equals("refLogs2/RP"))
	       	        	{  //	te[lin]="\t Remove Parameter Refactoring";
	       	        		//lin++;
	       	        		temp+= str1.split(":",2)[0]+"\n";
	       	        	}
	        	   if(str1.split("/",2)[1].equals("refLogs2/RM"))
	       	        	{	//te[lin]= "\t Rename Method Refactoring";
	       	        		//lin++;
	       	        		temp+=  str1.split(":",2)[0]+"\n";}
	        	   
	        	   if(str1.split("/",2)[1].equals("refLogs2/AP"))
	       	        	{	//te[lin]= "\t Added Parameter Refactoring";
	       	        		//lin++;
	       	        		temp+=  str1.split(":",2)[0]+"\n";}
	        	   
	        	   if(str1.split("/",2)[1].equals("refLogs2/IT"))
	       	        	{	//te[lin]= "\t Inline Template";
	       	        		//lin++;
	       	        		temp+=  str1.split(":",2)[0]+"\n";}
	        	   
	        	   if(str1.split("/",2)[1].equals("refLogs2/PDM"))
	       	        	{	//te[lin]= "\t Push Down Method Refactoring";
	       	        		//lin++;
	       	        		temp+=  str1.split(":",2)[0]+"\n";}
	        	   
	        	   if(str1.split("/",2)[1].equals("refLogs2/PUC"))
	       	        	{	//te[lin]="\t Pull Up Constructor Refactoring";
	       	        		//lin++;
	       	        		temp+=  str1.split(":",2)[0]+"\n";}
	        	   //lin++;
	        	  
		        	  /* Display content using Iterator*/
		        }
	           HashMap<String,String> hm = new HashMap<String,String>();
	      	 hm= gt.gRTypes();
	          String key="";
	        	  /* Display content using Iterator*/
	              Set set = hm.entrySet();
	              Iterator iterator = set.iterator();
	              while(iterator.hasNext()) {
	                 Map.Entry mentry = (Map.Entry)iterator.next();
	                 key+= (String) mentry.getKey()+"\n";
	                 //System.out.println(mentry.getValue());
	              }
		           txtArea.setText(key);
	                   
	           
	 // Always close files.
	   reader.close(); //reader reads CommitLog from commit.txt
	 }
		catch(FileNotFoundException ex) {
	       System.out.println(
	           "Unable to open file '" );  }
	  
	       catch(Exception e){System.out.println("Null Pointer exception");}
	 
	}
		
	private void updateStatus(int linenumber, int columnnumber){
		ctxtField.setText("Line:"+linenumber+ "  Column:"+ columnnumber);
		}
	

	private void saveBtn(){
		File file=null;
		FileWriter out=null;
		try{
			file= new File("output.txt");
			out= new FileWriter(file);
			out.write(txtArea.getText());
			out.close();
		}
		catch(FileNotFoundException e){e.printStackTrace();} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
		public static void main(String[] args) {
		// TODO Auto-generated method stub
			DisplayForRef dpr=new DisplayForRef(); 
			dpr.fileRead();
			dpr.panels();
	}

}
