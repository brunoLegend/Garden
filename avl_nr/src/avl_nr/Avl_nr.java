/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avl_nr;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author bruno
 */
public class Avl_nr {

   static Node1 root; 
    int conta_linha=0;
    // A utility function to get the height of the tree 
    int height(Node1 N) { 
        if (N == null) 
            return 0; 
  
        return N.height; 
    } 
    
    
    // A utility function to get maximum of two integers 
    int max(int a, int b) { 
        if (a>b){
            return a;
        }
        else {
            return b;
        }
        //return (a > b) ? a : b; 
    } 
    
    
     // A utility function to right rotate subtree rooted with y 
    Node1 rightRotate(Node1 y) { 
        System.out.println("rotating node: "+y.key);
        Node1 x = y.left; 
        Node1 T2 = x.right; 
  
        // Perform rotation 
        x.right = y; 
        y.left = T2; 
  
        // Update heights 
        y.height = max(height(y.left), height(y.right)) + 1; 
        x.height = max(height(x.left), height(x.right)) + 1; 
  
        // Return new root 
        return x; 
    } 
    
     // A utility function to left rotate subtree rooted with x 
    
    Node1 leftRotate(Node1 x) { 
        System.out.println("rotating node: "+x.key);
        Node1 y = x.right; 
        Node1 T2 = y.left; 
  
        // Perform rotation 
        y.left = x; 
        x.right = T2; 
  
        //  Update heights 
        x.height = max(height(x.left), height(x.right)) + 1; 
        y.height = max(height(y.left), height(y.right)) + 1; 
  
        // Return new root 
        return y; 
    } 
    
    
    // Get Balance factor of node N 
    int getBalance(Node1 N) { 
        if (N == null) 
            return 0; 
  
        return height(N.left) - height(N.right); 
    } 
    
    static int verifica_linha(int linha,Node1 atual){
       
        for(int i=0;i<atual.linhas.size();i++){
            if(atual.linhas.get(i)==linha){ //ja existe
                return 1;
            }
        }
        return 0;
    }
    
        Node1 insert(Node1 node, String key,int linha) { //MUDAR PARA != NULL
            
          System.out.println("--------------------dentro insert------------------------");
          Node1 aux=node;
          Node1 prev=node;
          Node1 current =prev;
          Node1 check=null;

          while(aux!=null){
              if (key.compareTo(aux.key)>0){ //se for maior vai para a direita
                  System.out.println("comparand1o...");
                  System.out.println("palavra é maior do que "+aux.key+"vou comparar agora com o proximo: ");
                  prev=aux;

                  aux=aux.right;
        }
          else if (key.compareTo(aux.key)<0){ //se for menor vai para a esquerda
                  System.out.println("comparando...");
                  System.out.println("palavra é menor do que "+aux.key+"vou comparar agora com o proximo: ");

                  prev=aux;
                  aux=aux.left; 
                  System.out.println("debug");
                  conta_linha++;

          }   

              else{ //se a palavra for igual
              if(verifica_linha(linha,aux)==0){
                 aux.linhas.add(linha);//adiciona linha ao no
              }
              return current; 
          }
          }
          if(node==null){
              System.out.println("vou adicionar uma palavra: "+key+" que aparece na linha: "+linha);
              ArrayList<Integer> linhas=new ArrayList<Integer>();
              linhas.add(linha); //linhas[0]=linha onde aparece
              System.out.println("adicionada");
              node=new Node1(key,linhas,null);
              return node;
          }

          if (aux == null){ // se nao existir nenhum elemento cria um no com a key e valor a 0
              System.out.println("vou adicionar uma palavra: "+key+" que aparece na linha: "+linha);
              System.out.println("anterior: "+prev.key);
              ArrayList<Integer> linhas=new ArrayList<Integer>();
              linhas.add(linha); //linhas[0]=linha onde aparece
              System.out.println("adicionada");
              if (key.compareTo(prev.key)>0){
                  prev.right=new Node1(key,linhas,prev);
                  check=prev.right;
                  System.out.println("criada: "+prev.right.key+"ACESSIVEL POR: "+check.key);
              }
              else if (key.compareTo(prev.key)<0){
                  prev.left=new Node1(key,linhas,prev);
                  check=prev.left;
                  System.out.println("criada: "+prev.left.key+"ACESSIVEL POR: "+check.key);
              }
              else{
                  if(verifica_linha(linha,prev)==0){
                       prev.linhas.add(linha);//adiciona linha ao no
              }
              return current; 
              }

            System.out.println("passseie");
            prev=check;
            
              System.out.println("heigth: "+node.height+1);
              int n=node.height;
            for(int i=0;i<n;i++){
                System.out.println("checking:::: "+prev.key);
                prev=prev.parent;
                System.out.println("prev hei"+prev.key);
                prev.height = 1 + max(height(prev.left),height(prev.right)); //obtenho altura do novo no na arvore
                System.out.println("node: "+prev.key+"height: "+prev.height);
         
                //fazer for para percorrer a arvore e balancar tudo
                    int balance = getBalance(prev); //verifico se a arvore continua balançada (diferença entre altura é <=1)

                // If this node becomes unbalanced, then there 
                // are 4 cases Left Left Case 
                if (balance > 1 && key.compareTo(prev.left.key)<0 ) {
                    //return 

                    prev=rightRotate(prev); 
                    System.out.println("prev1: "+prev.parent.key+"p: "+prev.key);
                }
                // Right Right Case 
                if (balance < -1 && key.compareTo(prev.right.key)>0){ 
                   // return

                    prev=leftRotate(prev); 
                    System.out.println("prev2: "+prev.key);
                    
                }
                // Left Right Case 
                if (balance > 1 && key.compareTo(prev.left.key)>0) { 
                    prev.left = leftRotate(prev.left); 
                    //return 

                    prev=rightRotate(prev); 
                    return prev;
                   // System.out.println("prev3: "+prev.key);
                } 

                // Right Left Case 
                if (balance < -1 && key.compareTo(prev.right.key)<0) { 
                    prev.right = rightRotate(prev.right); 
                    //return 
                    prev=leftRotate(prev); 
                    return(prev);
                  //  System.out.println("prev4: "+prev.key);
                } 

          }
              System.out.println("prev? "+prev.parent.key);
        }
          if(prev.parent.right!=null && prev.parent.left!=null)
            System.out.println("SUPOSTA raiz "+prev.parent.right.key+" ou "+prev.parent.left.key);
                
              return prev.parent;
        }  
        
      
        
        
       /* 
        if (node == null){ // se nao existir nenhum elemento cria um no com a key e valor a 0
            System.out.println("vou adicionar uma palavra: "+key+" que aparece na linha: "+linha);
            ArrayList<Integer> linhas=new ArrayList<Integer>();
            linhas.add(linha); //linhas[0]=linha onde aparece
            System.out.println("adicionada");
            return (new Node1(key,linhas,null)); 
        }

        //caso contrario vamos comparar com o que está lá
        if (key.compareTo(node.key)>0){ //se for maior vai para a direita
                System.out.println("comparando1...");
                System.out.println("palavra é maior do que "+node.key+"vou comparar agora com o proximo: ");
                node.right = insert(node.right, key,linha);
                //System.out.println("descendo node: "+node.key);
      }
        else if (key.compareTo(node.key)<0){ //se for menor vai para a esquerda
                System.out.println("comparando...");
                System.out.println("palavra é menor do que "+node.key+"vou comparar agora com o proximo: ");
                node.left = insert(node.left, key,linha); 
                //System.out.println("descendo node: "+node.key);
        }
                //System.out.println("debug");
                //conta_linha++;
         
         
        else{ //se a palavra for igual
            if(verifica_linha(linha,node)==0){
                node.linhas.add(linha);//adiciona linha ao no
            }
            return node; 
        }
       
          System.out.println("passseie");
            System.out.println("descendo node: "+node.key);
        node.height = 1 + max(height(node.left),height(node.right)); //obtenho altura do novo no na arvore
          System.out.println("node: "+node.key+"height: "+node.height);
  

        int balance = getBalance(node); //verifico se a arvore continua balançada (diferença entre altura é <=1)
  
        // If this node becomes unbalanced, then there 
        // are 4 cases Left Left Case 
        if (balance > 1 && key.compareTo(node.left.key)<0) 
            return rightRotate(node); 
  
        // Right Right Case 
        if (balance < -1 && key.compareTo(node.right.key)>0) 
            return leftRotate(node); 
  
        // Left Right Case 
        if (balance > 1 && key.compareTo(node.left.key)>0) { 
            node.left = leftRotate(node.left); 
            return rightRotate(node); 
        } 
  
        
        if (balance < -1 && key.compareTo(node.right.key)<0) { 
            node.right = rightRotate(node.right); 
            return leftRotate(node); 
        } 
  
    
       
        return node; 
    } 
    
        
        
     */   
        
    static Node1 find(String key) {
        Node1 current = root;
        while (current != null) {
            if (key.toLowerCase().equals(current.key)) {
                break;
            }
            else if(key.toLowerCase().compareTo(current.key)<0){
                System.out.println("andei para a esquerda");
                current=current.left;
            }
            else{
                System.out.println("andei para a direita");
                current=current.right;
            }
          
        }
    return current;
}
      
      
       static void preOrder(Node1 node) { 
        if (node != null) { 
            System.out.print(node.key + " "); 
            preOrder(node.left); 
            preOrder(node.right); 
        } 
    }
    
    
    public static void main(String[] args) {
        Avl_nr tree = new Avl_nr(); 
        /* Constructing tree given in the above figure */
         ArrayList<String> seq = new ArrayList<String>();
        int num_linhas=0;
        int existe=0;
        String texto;
        Scanner s= new Scanner(System.in);
        while(!(texto=s.nextLine()).equals("TCHAU")){
            String [] texto_aux=texto.trim().split("[ \n]");
            int contador=0;
            int i=-1;
            if(texto_aux[0].equals("TEXTO")){ // caso em que as palavras estao na mesma linha
                do{
                    i++; //linha
                    seq.add(s.nextLine().trim()); // tem as frases em cada indice
                    String[] seq_aux=seq.get(i).trim().split("[ \n,.]"); //tem as palavras de cada linha
                    if(!seq_aux[0].equals("FIM")){
                     for (int w=0;w<seq_aux.length;w++){
                        System.out.println("seq_aux: "+seq_aux[w]); //palavras
                        System.out.println("entrei com palavra: "+seq_aux[w]);
                        tree.root=tree.insert(tree.root,seq_aux[w].toLowerCase(),i);
                         System.out.println("__________SAI INSERT__________");
                         System.out.println("tree.root: "+tree.root.key);
                         System.out.println("Preorder traversal" + 
                        " of constructed tree is : "); 
                         preOrder(tree.root);
                             
                        
                     }
                    }
                }while(!seq.get(i).equals("FIM"));
                System.out.println("Guardado\n");
        
        
            }
                
            else if (texto_aux[0].equals("LINHAS")){
          
                Node1 word=find(texto_aux[1]);
                System.out.println("linhas: "+word.linhas);
            }    
            
            else if(texto_aux[0].equals("ASSOC")){
                //ir a palavra e ver se a linha existe
                
                Node1 atual=find(texto_aux[1]);
                if(verifica_linha(Integer.parseInt(texto_aux[2]),atual)==1){
                      System.out.println("ENCONTRADA.");
                }
                else{
                    System.out.println("NAO ENCONTRADA.");
                }
            }
         

         System.out.println("Preorder traversal" + 
                        " of constructed tree is : "); 
        tree.preOrder(tree.root); 
    
    }
    }
}
    