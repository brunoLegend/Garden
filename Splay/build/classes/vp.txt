


import java.util.ArrayList;
import java.util.Scanner;

public class Black_red {
    
     static Node root; 
     

  public void leftRotate(Node x) {
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
    
    
    static void preOrder(Node node) { 
        if (node != null) { 
            System.out.print(node.key + " "+node.color); 
            preOrder(node.left); 
            preOrder(node.right); 
        } 
    }
    
    
    public void print_pais(Node no){
        Node aux=no;
        int i=0;
        while(aux.parent!=null){
            i++;
            System.out.println(i);
            aux=aux.parent;
        }
    }
    
    
    
    void atualiza(Node novo){
        if(novo.parent!=null){
        while(novo.parent!=null && novo.parent.color==1 ){
            if(novo.parent==novo.parent.parent.left){
                Node tio=novo.parent.parent.right; 
                if(tio!=null && tio.color==1){ 
                    novo.parent.color=0;
                    tio.color=0;
                    novo.parent.parent.color=1;
                    novo=novo.parent.parent;
                }
                else{ 
                    if(novo==novo.parent.right){ 
                        novo=novo.parent;
                        leftRotate(novo); 
                    }
                    novo.parent.color=0; 
                    novo.parent.parent.color=1; 
                    rightRotate(novo.parent.parent);
                }
            }
            else{ 

                Node tio=novo.parent.parent.left;
                if(tio!=null && tio.color==1){ 

                    novo.parent.color=0;
                    tio.color=0;
                    novo.parent.parent.color=1;
                    novo=novo.parent.parent;
                   
                }
                else{ 
 
                    if(novo==novo.parent.left){ 
                        novo=novo.parent;
                        rightRotate(novo);
                        
                    }
                    novo.parent.color=0;
                    novo.parent.parent.color=1;
                    leftRotate(novo.parent.parent);   
                }

            } 

        }
    }
     
        this.root.color=0; 
        
    }
    
     int insert(Node novo,String key,int linha) { 

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
                    
                   }
                  return 0;
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
          
          atualiza(novo);
          return 0;
         
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
    
    
    
    public static void main(String[] args) {
         Black_red tree = new Black_red(); 
       ArrayList<String> seq = new ArrayList<String>();
        String output = new String();
        String texto;
        int conta=0;
        Scanner s= new Scanner(System.in);
        while(!(texto=s.nextLine()).equals("TCHAU")){
            String [] texto_aux=texto.trim().split("[ \n]");
            int i=-1;
            if(texto_aux[0].equals("TEXTO")){ 
                conta=1;
                do{
                    i++; 
                    seq.add(s.nextLine().trim()); 
                    String[] seq_aux=seq.get(i).trim().split("[ ();\n,.]"); 
                    if(!seq_aux[0].equals("FIM")){
                     for (int w=0;w<seq_aux.length;w++){
                        Node novo=new Node(seq_aux[w].toLowerCase(),i,null);
                            tree.insert(novo,seq_aux[w].toLowerCase(),i);
                        }
                   
 
                     
                     
                    }
                }while(!seq.get(i).equals("FIM."));
                output=output+"GUARDADO.\n";

            }
           else if (texto_aux[0].equals("LINHAS") && conta==1){
               
                Node word=find(texto_aux[1]);
                
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
                }
                else{
                    output=output+"-1\n";
                }
            }    
            else if(texto_aux[0].equals("ASSOC")&& conta==1){
                Node atual=find(texto_aux[1]);
                if(atual != null && verifica_linha(Integer.parseInt(texto_aux[2]),atual)==1){
       
                    output=output+"ENCONTRADA.\n";
                }
                else{
                    output=output+"NAO ENCONTRADA.\n";
                }
            }

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
    