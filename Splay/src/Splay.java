


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Splay {

    static Node root; 
    static int rota=0;
    
    
     static int verifica_linha(int linha,Node atual){
        Linhas aux=atual.linhas.head;
        for(int i=0;i<atual.linhas.getsize();i++){
            if(aux.data ==linha){ 
                return 1;
            }
            aux=aux.next;
        }
        return 0;
    }
    
     
     
      public void leftRotate(Node x) {
          rota++;
    Node y = x.right;
    x.right = y.left;
    if(y.left != null) {
      y.left.parent = x;
    }
    y.parent = x.parent;
    if(x.parent == null) { 
      this.root = y;
    }
    else if(x == x.parent.left) { 
      x.parent.left = y;
    }
    else { 
      x.parent.right = y;
    }
    y.left = x;
    x.parent = y;
  }

  public void rightRotate(Node x) {
      rota++;
    Node y = x.left;
    x.left = y.right;
    if(y.right != null) {
      y.right.parent = x;
    }
    y.parent = x.parent;
    if(x.parent == null) {
      this.root = y;
    }
    else if(x == x.parent.right) { 
      x.parent.right = y;
    }
    else { 
      x.parent.left = y;
    }
    y.right = x;
    x.parent = y;
  }
     
     
  
    static void preOrder(Node node) { 
        if (node != null) { 
            System.out.print(node.key + " "+node.color); 
            preOrder(node.left); 
            preOrder(node.right); 
        } 
    }
  
    
       static Node find(String key) {
        Node current = root;
        while (current != null) {
            if (key.toLowerCase().equals(current.key)) {
                break;
            }
            else if(key.toLowerCase().compareTo(current.key)<0){
                current=current.left;
            }
            else{
                current=current.right;
            }
          
        }
    return current;
}
   
    
    
    
     public void sobe_ate_raiz(Node novo){

         while(novo!=root){
            if(novo.parent!=null && novo.parent==root){ 
                if(novo==novo.parent.left){ 
                    rightRotate(novo.parent);
                }
                else{
                    leftRotate(novo.parent);
                }

            }

            else if(novo.parent==novo.parent.parent.left){ 

                if(novo==novo.parent.left){ 
                    rightRotate(novo.parent.parent);
                    rightRotate(novo.parent);
                }
                else{

                    leftRotate(novo.parent);
                    rightRotate(novo.parent);
                }
            }
            else{ 
                if(novo==novo.parent.right){ 
                    leftRotate(novo.parent.parent);
                    leftRotate(novo.parent);
                }    
                else{
                    rightRotate(novo.parent);
                    leftRotate(novo.parent);
                    
                }
            }

         }
   
     }
     
     
    
    void insert(Node novo,String key,int linha,ArrayList<String> alea){
          Node pai=null;
          Node temp=this.root;
          while(temp!=null){
              
              pai=temp; 
              if (novo.key.compareTo(temp.key)<0){
                  temp=temp.left;
              }
              else if(novo.key.compareTo(temp.key)>0)
                  temp=temp.right;
              else{
                 
                  if(verifica_linha(linha,temp)==0){
                    temp.linhas.insert(linha);
                      sobe_ate_raiz(temp);
                    
                   }
                  return;
              }
          }
          
          novo.parent=pai;
        
          if(pai==null){ 
              this.root=novo; 
          }
          else if(novo.key.compareTo(pai.key)<0){ 
              pai.left=novo;
          }
          else {
            pai.right=novo;  
          }
          novo.right=null;
          novo.left=null;
          
          alea.add(key);
          sobe_ate_raiz(novo);
    }
    
    
    
    
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Splay tree = new Splay();
        File file = new File("C:\\Users\\bruno\\Documents\\NetBeansProjects\\Splay\\src\\F3 TEXTO B.txt"); 
        BufferedReader br = new BufferedReader(new FileReader(file));
        ArrayList<String> seq = new ArrayList<String>();
        ArrayList<String> alea = new ArrayList<String>();
        String output = new String();
        String texto;
        int conta=0;
        Scanner s= new Scanner(System.in);
       
        while(!(texto=br.readLine()).equals("TCHAU")){
            String [] texto_aux=texto.trim().split("[ \n]");
            int i=-1;
            if(texto_aux[0].equals("TEXTO")){ 
                conta=1;
                do{
                    i++; 
                    seq.add(br.readLine().trim()); 
                    String[] seq_aux=seq.get(i).trim().split("[ ();\n,.]"); 
                    if(!seq_aux[0].equals("FIM")){
                     for (int w=0;w<seq_aux.length;w++){
                        Node novo=new Node(seq_aux[w].toLowerCase(),i,null);
                        tree.insert(novo,seq_aux[w].toLowerCase(),i,alea);
                        
                    
 
                     }
                    }
                }while(!seq.get(i).equals("FIM."));
               
                 System.out.println("rota: "+rota);
                output=output+"GUARDADO.\n";

            }
             //else if (texto_aux[0].equals("LINHAS") && conta==1){
             
             
             long media=0;
             //for(int m=0;m<5;m++){
              // long startTime = System.nanoTime();
               long startTime = System.nanoTime();
               ArrayList<String> pal = new ArrayList<String>();
               for(int n=0;n<500;n++){
                   Random rand=new Random();
                   int num_r= rand.nextInt(alea.size());
                   String texto_aux1=alea.get(num_r);
                    Node word=find(texto_aux1);
                
                    if(word!=null){
                        Linhas aux=word.linhas.head;
                        while(aux!=null){
                            if(aux.next!=null){
                                output=output+aux.data+" ";
                            }
                            else{
                                output=output+aux.data+"\n";
                            }
                            aux=aux.next;
                        }
                        tree.sobe_ate_raiz(word);
                    }
                    else{
                        output=output+"-1\n";
                    }
                     long endtime=System.nanoTime();
                long elapsedTime=endtime-startTime;
                System.out.println("tempo: "+elapsedTime);
              }
              // long endtime=System.nanoTime();
                //long elapsedTime=endtime-startTime;
               // System.out.println("tempo: "+elapsedTime);
                //media=media+elapsedTime;
                
                
             
               
            // }   
             //System.out.println("media: "+media/5);
            //}    
           /*
            //else if(texto_aux[0].equals("ASSOC")&& conta==1){
               long startTime = System.nanoTime();
                for(int n=0;n<50;n++){
                    Random rand=new Random();
                    int num_r= rand.nextInt(alea.size());
                   String texto_aux1=alea.get(num_r);
                   int texto2=rand.nextInt(alea.size());
                    Node atual=find(texto_aux1);
                if(atual != null && verifica_linha((texto2),atual)==1){
                    output=output+"ENCONTRADA.\n";
                    tree.sobe_ate_raiz(atual);
                }
                else{
                    output=output+"NAO ENCONTRADA.\n";
                }
           // }
            
                }
                 long endtime=System.nanoTime();
                long elapsedTime=endtime-startTime;
                System.out.println("tempo: "+elapsedTime);
*/
        }
        System.out.print(output);
    }

 
}
 
//black=0 red=1
class Node {
    String key;
    int color;
    LinkedList linhas;
    
    Node left, right,parent; 
  
    Node(String d, int linha,Node parent) { 
        this.linhas = new LinkedList();
        this.linhas.insert(linha);
        key = d; 
        color = 1; 
        this.parent=parent;
    } 
} 




     class LinkedList { 
  
    Linhas head;
    
    public void insert(int data) 
    { 

        Linhas new_node = new Linhas(data); 
        new_node.next = null; 

        if (this.head == null) { 
            this.head = new_node; 
        } 
        else { 

            Linhas last = this.head; 
            while (last.next != null) { 
                last = last.next; 
            } 

            last.next = new_node; 
        } 

   
    }
       
       
       public  void printList() 
    { 
        Linhas currNode = this.head; 
        while (currNode != null) { 
            currNode = currNode.next; 
        } 
    } 
       
     public int getsize(){
         Linhas curr=this.head;
         int tam=0;
         while(curr!=null){
             tam++;
             curr=curr.next;
         }
         return tam;
     }  
    
 }
     class Linhas { 
  
        int data; 
        Linhas next; 

        Linhas(int d) 
        { 
            data = d; 
            next = null; 
        }
        
}
      
