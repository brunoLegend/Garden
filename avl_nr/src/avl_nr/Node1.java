/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avl_nr;

import java.util.ArrayList;

/**
 *
 * @author bruno
 */
public class Node1 {
    String key;
    int height;
    ArrayList<Integer> linhas;
    Node1 left, right,parent; 
  
    //construtor
    Node1(String d, ArrayList<Integer> linhas,Node1 parent) { 
        this.linhas = new ArrayList<Integer>();
        this.linhas=linhas;
        this.parent=parent;
        key = d; 
        height = 1; 
    } 
} 
