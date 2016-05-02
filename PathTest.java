package conversion;
import java.awt.*;  
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.*;  
import javax.swing.tree.*;  

public class PathTest  
{  
    public PathTest(String value)  
    {  
    	 GettingRefactoringTypes gt=new GettingRefactoringTypes();

    	 HashMap<String,String> hm = new HashMap<String,String>();
    	 hm= gt.gRTypes();
    	String v =(String)hm.get(value);
    	System.out.println(v);
    	DefaultMutableTreeNode   node = buildNodeFromString(value);   


    	 DefaultTreeModel model = new DefaultTreeModel(node);  
    	 JTree tree = new JTree(model);  
    	 JFrame f = new JFrame();  
    	 f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    	 f.add(tree);  
    	 f.setSize(300,300);  
    	 f.setLocation(200,200);  
    	 f.setVisible(true);  
    }  
   
    public DefaultMutableTreeNode buildNodeFromString(String value) {
	
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

        public static void main(String[] args)  
        {  
          //  new PathTest();  
        }  
    }  