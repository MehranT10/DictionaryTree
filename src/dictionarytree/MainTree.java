
package dictionarytree;
import java.util.ArrayList;
import java.util.Stack;

public class MainTree 
{
    private static TreeNode Root;
    private TreeNode Ptr;
    private static final char alphabet[] ={'z','y','x','w','v','u','t','s','r','q','p','o','n','m','l','k','j','i','h','g','f','e','d','c','b','a'};
    public MainTree()
    {
        Root = new TreeNode(null,'*');
    }
    
    public void addWord(String s)//azafeh kardan kalameh
    {
        Ptr = Root;
        for(int i = 0;i < s.length();i++)
        {
            if(Ptr.get_childe(s.charAt(i)) == null)//agar yek harf ghablan vojood nadashteh bashad
            {
                Ptr.set_childe(new TreeNode(Ptr,s.charAt(i)));
                Ptr = Ptr.get_childe(s.charAt(i));
            }
            else // aagar vojood dasht
                Ptr = Ptr.get_childe(s.charAt(i));
            if(i+1 == s.length())//akharin harf akharin node ra kalameh gharar midahad
                Ptr.set_IsWord();
        }
    }
    
    public String search(String s)//search mamuli ke agar kalame vojood dasht khodash ra bar migardand
    {
        String M = "";
        Ptr = Root;
        for(int i = 0 ; i < s.length();i++)
        {
            if(Ptr.get_childe(s.charAt(i)) != null) //har bar check mikonad aya farzand ba harf kalameh vojood darad ya na
            {
                M += s.charAt(i);
                Ptr = Ptr.get_childe(s.charAt(i));
                if(i == s.length()-1)//akhar kalame residim
                   return M;
            }
            else
                return "!";
        }
        return "!";
    }
    
    public String search1(String s)
    {
        TreeNode Help;
        String N = "";//ta ghabl az ? horof ra dar N negah midard
        String L = "";//bad az ? horof dar L negah dashteh mishavad
        String M = "";
        Ptr = Root;
        for(int i = 0 ; i < s.length();i++)
        {
            if(Ptr.get_childe(s.charAt(i)) != null || s.charAt(i) == '?')//vujod farzand
            {
                if(s.charAt(i) == '?')//vaghti be chrachter ? residim
                {
                    Help = Ptr;
                    for(TreeNode m : Help.childs) //as karakter ? be bad hameh farzan dan ptr ra check mikonad
                    {
                        if(s.charAt(s.length()-1) != '?')
                        {
                            Ptr = Help;
                            L="";
                            L+=m.get_ch();
                            Ptr = m;
                            for(int j = i+1 ; j < s.length();j++)
                            {
                                if(Ptr.get_childe(s.charAt(j))!= null)
                                {
                                    L+=s.charAt(j);
                                    if(j == s.length()-1 && Ptr.get_childe(s.charAt(j)).get_isword())//agar kalameh bud be M ezafe mishavad
                                        M+=N+L+"\n";
                                    Ptr = Ptr.get_childe(s.charAt(j));
                                }
                            }
                        }
                        else if(s.charAt(s.length()-1) == '?')
                        {
                            if(m.get_isword())
                                M+=N+m.get_ch()+"\n";
                        }
                    }
                    return M;
                }
                else
                {
                        N+=s.charAt(i);
                        Ptr = Ptr.get_childe(s.charAt(i));
                }
            }
            else
                return "!";
        }
        return "!";
    }
    

    public String search2(String s)
    {
        ArrayList<String> keep = new ArrayList<>();
        String L;
        String M = "";
        for(int i = 0 ; i < s.length() ; i++)//ba estefade az search sadeh kalamat ba yek har kamtar ra pida mikonad
        {
            L = "";
            for(int j =0 ;j < s.length();j++)
                if(i != j)
                    L+=s.charAt(j);
            if(search(L) != "!")
                M+= search(L);
        }
        for(int i = 0 ; i < s.length() ; i++)//ba estefadeh az search1 kalamat ba yek harf tafavot ra pida mikonad
        {
            L = "";
            for(int j =0 ;j < s.length();j++)
            {
                if(i != j)
                    L+=s.charAt(j);
                else
                    L+="?";
            }
            if(search1(L) != "!")
                M+= search1(L);
        }
        for(int i =0 ; i < s.length();i++)//ba estefadeh az search1 kalamat ba yek harf bishtar ra pida mikonad
        {
            L = "";
            for(int j = 0;j < s.length() ; j++)
            {
                if(j == i)
                    L+="?"+s.charAt(j);
                else
                    L+=s.charAt(j);
            }
            if(search1(L) != "!")
                M+= search1(L);
        }
        L ="";
        for(int i =0 ; i<M.length() ; i++)//kalamat ra baray hazf tekrary ha darun arraylist mirizad
        {
            if(M.charAt(i) != '\n')
                L+=M.charAt(i);
            else
            {
                keep.add(L);
                L = "";
            }
        }
        M = "";
        for(int i =0 ; i<keep.size();i++)//tekrary ha ra hazf mikonad
            for(int j = 0 ; j < keep.size();j++)
            {
                if(keep.get(i) == keep.get(j))
                    keep.remove(j);
            }
        for(int i =1 ; i<keep.size();i++)//kalamat ra be M ezafeh mikonad
        {
            M+=keep.get(i)+"\n";
        }
        return M;
    }
    
    public String search3(String s)
    {
        TreeNode help1;
        ArrayList<Character> L = new ArrayList();//horof motafavet bad az * ra negah midarad
        String M = "";
        Ptr = Root;
        for(int i = 0 ; i < s.length();i++)
        {
            if(Ptr.get_childe(s.charAt(i)) != null || s.charAt(i) == '*')//farzand vujood dashteh bashad
            {
                if(s.charAt(i) == '*')
                {
                    Stack<TreeNode> see_chi = new Stack<>();//stak baray negah dashtan farzandan
                    see_chi.push(Ptr);
                    see_chi.push(Ptr);
                    L.remove(L.size()-1);
                    while(!see_chi.isEmpty())//ta zamani ke stack khali nashodeh ast
                    {
                        if(!see_chi.lastElement().is_Leaf())//agar barg nabashad
                        {
                            if(see_chi.lastElement().get_isword())//agar kalameh bashad anra be M ezafeh mikonad
                            {
                                L.add(see_chi.lastElement().get_ch());
                                for(int k =0 ; k < L.size();k++)
                                M+=L.get(k);
                                M+="\n";
                                L.remove(L.size()-1);
                            }
                            help1 = see_chi.pop();
                            L.add(help1.get_ch());
                            see_chi.push(null);//baray inke az farzandan yek node be farzandan hamzadash beravim yek null baray fahmidanash migozarim
                            for(char ch : alphabet)//az sar stack yek node bardashteh va farzandanash ra bejay an migozarad
                                if(help1.get_childe(ch) != null)
                                see_chi.push(help1.get_childe(ch));
                        }
                        else
                        {
                            L.add(see_chi.pop().get_ch());
                            for(int k =0 ; k < L.size();k++)
                            M+=L.get(k);
                            M+="\n";
                            L.remove(L.size()-1);
                        }
                        while(see_chi.lastElement() == null)//be ezay har null yek harf L hazf mishavad
                        {
                            see_chi.pop();
                            L.remove(L.size()-1);
                        }
                        if(see_chi.lastElement() == Ptr)
                            see_chi.pop();
                    }
                    return M;
                }
                if(Ptr.get_childe(s.charAt(i)) != null)//ghabl az residan be *
                {
                    L.add(s.charAt(i));
                    Ptr = Ptr.get_childe(s.charAt(i));
                }
            }
            else
                return "!";
        }
        return "!";
    }
    
    public String Sort()
    {
        TreeNode help;
        ArrayList<Character> L = new ArrayList();//baray negah dashtan horoof
        String M ="";//negah dashtan kalamat
        Stack<TreeNode> sorting = new Stack();//baray negah dashtan node ha.
        sorting.push(Root);
        sorting.push(Root);
        while(!sorting.isEmpty())//ta zamani ke stack khali shavad
        {
            if(!sorting.lastElement().is_Leaf())//sar stack be onvan esharegar amal mikonad
            {
                if(sorting.lastElement().get_isword())//agar node neshangar yek kalemrh bashad kalameh ra be M ezafe mikonad
                {
                    L.add(sorting.lastElement().get_ch());
                    for(int k =0 ; k < L.size();k++)
                    M+=L.get(k);
                    M+="\n";
                    L.remove(L.size()-1);
                }
                help = sorting.pop();
                L.add(help.get_ch());
                sorting.push(null);//bray inke tafavot bin bachehay do hamzad mpshakhas bashad yek null push mikonim
                for(char ch : alphabet)//yek nod dar help pop mishavad va bachehay an baraks horoofalephba push mishavad
                    if(help.get_childe(ch) != null)//check kardan inke node vojood dashteh bashad
                    sorting.push(help.get_childe(ch));
            }
            else
            {
                L.add(sorting.pop().get_ch());
                for(int k =0 ; k < L.size();k++)
                M+=L.get(k);
                M+="\n";
                L.remove(L.size()-1);
            }
            while(sorting.lastElement() == null)
            {
                sorting.pop();
                L.remove(L.size()-1);
            }
            if(sorting.lastElement() == Root)
                sorting.pop();
        }
        return M;
    }
    
}
