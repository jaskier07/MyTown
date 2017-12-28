/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alexp.pieniezno24.resources;

import com.alexp.pieniezno24.FunFact;
import com.alexp.pieniezno24.FunFactsContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
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
import java.util.Map;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author alexp
 */
@Path("/facts")
public class FunFactsResource {

        public static final String FACTS_CONTEXT = "factsContext";
        public static final int NO_IMPORTANCE = 0;
        public static final int SORT_BY_IMPORTANCE = 0;
        public static final int SORT_BY_ID = 1;

        @Context
        ServletContext context;

        @Context
        HttpServletRequest request;

        @Context
        HttpServletResponse response;

        @GET
        @Path("list")
        @Produces(MediaType.APPLICATION_JSON)
        public ArrayList<FunFact> listFunFacts(
                @QueryParam("limit") Integer limit,
                @QueryParam("importance") Integer importance,
                @QueryParam("sort") Integer sort) throws IOException {

                ArrayList facts = getFunFactsContext().findAllFacts();

                if (importance != NO_IMPORTANCE) {
                        selectFactsByImportance(facts, importance);
                }
                sortFacts(facts, sort);
                selectTopFacts(facts, limit);

                return facts;
        }

        private void sortFacts(ArrayList<FunFact> facts, int sortType) {
                switch (sortType) {
                        case SORT_BY_ID:
                                compareFactsById(facts);
                                break;
                        case SORT_BY_IMPORTANCE:
                                compareFactsByImportance(facts);
                                break;
                }
        }

        private void compareFactsById(ArrayList<FunFact> facts) {
                Collections.sort(facts, new Comparator<FunFact>() {
                        @Override
                        public int compare(FunFact o1, FunFact o2) {
                                return o1.getId().compareTo(o2.getId());
                        }
                });
        }

        private void compareFactsByImportance(ArrayList<FunFact> facts) {
                Collections.sort(facts, new Comparator<FunFact>() {
                        @Override
                        public int compare(FunFact o1, FunFact o2) {
                                return o1.getImportance().compareTo(o2.getImportance());
                        }
                });
        }

        private void selectTopFacts(ArrayList<FunFact> facts, int limit) {
                int i = 0;
                for (Iterator<FunFact> iter = facts.listIterator(); iter.hasNext(); i++) {
                        FunFact f = iter.next();
                        if (i > limit) {
                                iter.remove();
                        }
                }
        }

        private void selectFactsByImportance(ArrayList<FunFact> facts, int importance) {
                int i = 0;
                for (Iterator<FunFact> iter = facts.listIterator(); iter.hasNext(); i++) {
                        FunFact f = iter.next();
                        if (f.getImportance() != importance) {
                                iter.remove();
                        }
                }
        }

        private FunFactsContext getFunFactsContext() throws IOException {
                FunFactsContext factsContext = (FunFactsContext) context.getAttribute(FACTS_CONTEXT);
                if (factsContext == null) {
                        factsContext = new FunFactsContext();
                        context.setAttribute(FACTS_CONTEXT, factsContext);
                }
                return factsContext;
        }
}
