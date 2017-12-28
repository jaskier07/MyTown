/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alexp.pieniezno24;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author alexp
 */
public class FunFactsContext {
        private final SortedMap<Integer,FunFact> facts;        
       
        public FunFactsContext() throws FileNotFoundException, IOException {
                facts = Collections.synchronizedSortedMap(new TreeMap<Integer, FunFact>());
                
                BufferedReader reader = new BufferedReader(new FileReader("fun facts.txt"));
                //Scanner scanner = new Scanner(new File("fun facts.txt"));
                int index = 0;
                int importance;
                String text;
                String line;
                while((line = reader.readLine()) != null) {
                        importance = Integer.parseInt(line.substring(0, 1));
                        text = line.substring(2,line.length());
                        facts.put(index, new FunFact(index, importance, text));
                        index++;
                }
        }
        
        public List<FunFact> findAllFacts() {
                return new ArrayList<>(facts.values());
        }
        
}
