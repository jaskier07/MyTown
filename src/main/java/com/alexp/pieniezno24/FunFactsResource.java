/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alexp.pieniezno24;

import java.io.IOException;
import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import java.util.List;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author alexp
 */
@Path("/facts")
public class FunFactsResource {
        public static final String FACTS_CONTEXT = "factsContext";
    
        @Context
        ServletContext context;
        
        @Context
        HttpServletRequest request;

        @Context
        HttpServletResponse response;
        
        @GET
        @Path("list")
        @Produces(MediaType.APPLICATION_JSON)
        public List<FunFact> listFunFacts() {
                return getFunFactsContext().findAllFacts();
        }
        
        
        private FunFactsContext getFunFactsContext() throws IOException {
                FunFactsContext factsContext = (FunFactsContext)context.getAttribute(FACTS_CONTEXT);
                if (factsContext == null) {
                        factsContext = new FunFactsContext();
                        context.setAttribute(FACTS_CONTEXT, factsContext);
                }                
                return factsContext;
        }
}
