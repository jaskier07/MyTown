/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alexp.pieniezno24;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author alexp
 */
public class FunFactsContext {    
        public static final String PATH_TO_SOURCE_FILE = "C:/Users/alexp/Dropbox/Stoodia/V semestr/AUI/lab 6 - projekt/Pieniezno24/src/main/webapp/fun facts2.txt";
        public static final int IMPORTANCE_BEGIN = 0;
        public static final int IMPORTANCE_END = 1;
        public static final int TEXT_BEGIN = 2;
        
        //private final SortedMap<Integer,FunFact> facts;    
        private final List<FunFact> facts;    
       
        public FunFactsContext() throws FileNotFoundException, IOException {
                //facts = Collections.synchronizedSortedMap(new TreeMap<Integer, FunFact>());
                facts = Collections.synchronizedList(new ArrayList<FunFact>());
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(PATH_TO_SOURCE_FILE)));//new FileReader(PATH_TO_SOURCE_FILE)); 
                 
                int index = 1;
                int importance;
                String text;
                String line;
                while((line = reader.readLine()) != null) {
                        importance = Integer.parseInt(line.substring(IMPORTANCE_BEGIN, IMPORTANCE_END));
                        text = line.substring(TEXT_BEGIN,line.length());
                        //facts.put(index, new FunFact(index, importance, text));
                        facts.add(index, new FunFact(index, importance, text));
                        index++;
                }
        }
        
        public ArrayList<FunFact> findAllFacts() {
                return new ArrayList<>(facts);
        }
        
}
