
package dictionarytree;

import java.util.ArrayList;


public class TreeNode 
{
    private char ch; //character node
    private boolean isWord,Leaf; //isword baray kalame budane node, leaf baray barg bodan
    private TreeNode Father;//negah dashatan pedar
    ArrayList <TreeNode> childs = new ArrayList();//negah dashtan farzandan
    public TreeNode(TreeNode Fa,char cha)
    { 
        isWord = false;
        Leaf = true;
        Father = Fa;
        ch = cha; 
    }

    public void set_IsWord() 
    {
        isWord = true;
    }
    
    public void set_childe(TreeNode M)
    {
        childs.add(M);
        Leaf = false;//agar farzand dasht digar barg  nabashad
    }
    
    public boolean is_Leaf()
    {
        return Leaf;
    }
    
    public TreeNode get_father()
    {
        return Father;
    }
    
    public boolean get_isword()
    {
        return isWord;
    }
    
    public char get_ch()
    {
        return ch;
    }
    
    public TreeNode get_childe(char ch)//pida kardan farzand bar asas charachter
    {
        for(int i = 0 ; i < childs.size();i++)
        {
            if(childs.get(i).get_ch() == ch)
                return childs.get(i);
        }
        return null;
    }
}
