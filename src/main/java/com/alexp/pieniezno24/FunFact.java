/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alexp.pieniezno24;

/**
 *
 * @author alexp
 */

public class FunFact {
        private Integer id;
        private Integer importance;
        private String text;
        
        public FunFact() {
                
        }
        
        public FunFact(Integer id, Integer importance, String text) {
                this.id = id;
                this.importance = importance;
                this.text = text;
        }
        
        public Integer getId() {
                return id;
        }
        
        public Integer getImportance() {
                return importance;
        }
        
        public String getText() {
                return text;
        }
}
