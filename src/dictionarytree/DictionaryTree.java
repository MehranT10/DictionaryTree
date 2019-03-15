
package dictionarytree;


import javax.swing.JFrame;


public class DictionaryTree {

    /**
     *
     */
    public static void main(String[] args) 
    {
        MainD my = new MainD(); 
        JFrame DIC = new JFrame("TreeDic");
        DIC.add(my);
        DIC.setSize(490,620);
        DIC.setLocation(700, 150);
        DIC.setVisible(true);
        DIC.setResizable(false);
        DIC.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
