package conversion;

import java.text.SimpleDateFormat;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.Color;
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

import javax.print.attribute.AttributeSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.text.html.*;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolTip;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.plaf.ColorUIResource;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import javax.swing.text.Utilities.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class DisplayFor {
	RXTextUtilities rx=new RXTextUtilities();
	private String path="C:/Users/Pragya/git/cassandra2.1/CHANGES.txt";
	private String storeAllString="";
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
	
	StyleContext sc = new StyleContext();
    final DefaultStyledDocument doc = new DefaultStyledDocument(sc);
       

	private JTextPane txtArea1= new JTextPane();
	GettingRefactoringTypes gt=new GettingRefactoringTypes();

	
	HTMLEditorKit txtArea1_html_kit = new HTMLEditorKit();
		
	private	JList<String> listbox1;
	 String te[]={" Move_Method_Refactoring","Remove_Parameter_Refactoring","Rename_Method_Refactoring","Added_Parameter_Refactoring","inline_Template","Push_Down_Method_Refactoring","Pull_Up_Constructor_Refactoring"};
	
	 	
	private void panels(){
		JPanel panel= new JPanel(new GridLayout(2,1));
		panel.setBorder(new EmptyBorder(5,5,5,5));
		JPanel tPanel= new JPanel(new GridLayout(30,0,5,5));
		tPanel.setBorder(new EmptyBorder(1,1,1,1));
		JPanel bPanel= new JPanel(new GridLayout(40,0,5,5));
		bPanel.setBorder(new EmptyBorder(1,1,1,1));
		JPanel rightPanel= new JPanel(new GridLayout(6,0,5,5));
		rightPanel.setBorder(new EmptyBorder(10,5,0,10));
		
    	 
    	DefaultMutableTreeNode  node = buildNodeFromString("Refactoring,Classname,Method"); 
    	DefaultTreeModel model = new DefaultTreeModel(node);
    	JTree tree = new JTree(model); 
    	bPanel.add(tree);  
    	bPanel.setSize(300,300);  
    	bPanel.setLocation(200,200);  
    	bPanel.setVisible(true);  
		
		//JPanel rightBPanel= new JPanel(new GridLayout(8,0,5,5));
		JScrollPane scrollBarForTxtArea=new JScrollPane(txtArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JScrollPane scrollBarForTxtArea1=new JScrollPane(txtArea1,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		listbox1 = new JList<String>(te);
		listbox1.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listbox1.setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane(listbox1);
		listScroller.setPreferredSize(new Dimension(20, 20));
		
		
		panel.add(scrollBarForTxtArea);
		panel.add(scrollBarForTxtArea1);
		frame.add(panel);
		frame.getContentPane().add(rightPanel,BorderLayout.EAST);
		
		rightPanel.add(listbox1);
		rightPanel.add(bPanel,BorderLayout.SOUTH);
		
		rightPanel.add(ptxtField);
		
		//rightPanel.add(plabel);
		//rightPanel.add(rightBPanel);
		
		//rightPanel.add(listbox1);
		
		closeBtn.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent ae){
			frame.dispose();
		}
		});
		
		sveCloseBtn.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent ae){
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
				int columnnum=1;int ln=0;
					try{
					int caretPos= editArea.getCaretPosition();
					linenum= editArea.getLineOfOffset(caretPos);
					columnnum=caretPos-editArea.getLineStartOffset(linenum);
					linenum++;
				}
				catch (Exception ex){}
				//updateStatus(linenum, columnnum);
				 String st=txtArea.getSelectedText();
				//System.out.println(rmp);
				 String te1= st;
				 String temp=te1.split("CASSANDRA-",2)[1];
				 String temp1=te1.substring(te1.split("CASSANDRA-",2)[0].length(),te1.length()-1);
				 ptxtField.setText(temp1);
				 ln= getLineCount("("+temp1+")");
				 rx.gotoStartOfLine(txtArea1, 0);
				 buildNodeFromString(temp1);
				}
			});
		callCommitId();
		}
	
	
	 private DefaultMutableTreeNode buildNodeFromString(String value) {
		// TODO Auto-generated method stub
		 DefaultMutableTreeNode node, lastNode = null, root = null;
		  
		 String[] s = value.split(",");
		 for (String str : s) {
	        node = new DefaultMutableTreeNode(str);     
	        if (root == null)
	            root = node;
	        if (lastNode != null)
	            lastNode.add(node);
	        lastNode = node;
	        }

	    return root;

	}


	private void fileRead(){
			try{
				String temp=" ",str="";
				FileReader read= new FileReader("CHANGES.txt");
				Scanner scan= new Scanner(read);
				
				while(scan.hasNextLine()){
						str=scan.nextLine();
						str.trim();
						temp+=str+"\n";
					}
				txtArea1.setText(temp);        
					scan.close();
					read.close();
					temp=null;
			}
			catch(Exception e){e.printStackTrace();System.out.println("Changes.txt");}
		}
		
	
	protected void callCommitId() {
		// callCommitId method calls the specific reasons for Refactoring ....
		try{       
	            HashMap<String,String> hm = new HashMap<String,String>();
		      	 hm= gt.gRTypes();
		          String key="",value=" ";
		        	  /* Display content using Iterator*/
		              Set set = hm.entrySet();
		              Iterator iterator = set.iterator();
		              while(iterator.hasNext()) {
		                 Map.Entry mentry = (Map.Entry)iterator.next();
		                 key+= (String) mentry.getKey()+"\n";
		                 value+=(String) mentry.getValue()+"\n";
		                 //System.out.println(mentry.getValue());
		              }
			           txtArea.setText(key);
			          
	 } // try block closed
		
		catch(Exception e){System.out.println("Null Pointer exception");e.printStackTrace();}
	 
	}// close of callCommitId method.
		
	
		
	public int getLineCount(String temp){ 
		int lineCount,l=0;
		lineCount=0;String line="";
		 Scanner sc;
		 try{   
    	 sc=new Scanner(txtArea1.getText());
    	 while(sc.hasNextLine()){
    		 
    		 line=sc.nextLine();
    		
            if(line.contains(temp)){
            	l=lineCount;
            	txtArea1.replaceSelection("\n\n:Reason:-----------> " + line+" <----------- \n\n\n\n");
            	
            }
            lineCount++;
        }
        System.out.println(" - "+l);
        sc.close();line=null;
     }catch(Exception re){re.printStackTrace();}
     return l;
     
	}
			    
	
public static void main(String[] args) {
	// TODO Auto-generated method stub
		DisplayFor dpr=new DisplayFor(); 
		dpr.fileRead();
		dpr.panels();
	}

		  
} //End of class.


