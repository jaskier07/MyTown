/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alexp.pieniezno.resources;

import com.alexp.pieniezno.FunFact;
import com.alexp.pieniezno.FunFactsContext;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import java.util.Random;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author alexp
 */
@Path("/facts")
public class WebServlet {

        private static final String FACTS_CONTEXT = "factsContext";
        private static final int NO_IMPORTANCE = 0;
        private static final int SORT_BY_IMPORTANCE = 0;
        private static final int SORT_BY_ID = 1;
        
        private static final int TRAVEL_CAR = 0;
        private static final int TRAVEL_BICYCLE = 1;
        private static final int TRAVEL_TRANSIT = 2;
        private static final int TRAVEL_WALKING = 3;        
        
        private static final int TRAVEL_METRES = 0;
        private static final int TRAVEL_MILES = 1;

        private static final String GOOGLE_KEY = "AIzaSyAPwma7_2ztcHHAnTdnLBcQDAKPMZqFMSU";
        private static final GeoApiContext MAP_CONTEXT = new GeoApiContext().setApiKey(GOOGLE_KEY);
        
        private final Random generator = new Random();

        @Context
        ServletContext context;

        @Context
        HttpServletRequest request;

        @Context
        HttpServletResponse response;

        @GET
        @Path("distance")
        @Produces(MediaType.APPLICATION_JSON)
        public DistanceMatrix getDistance(
                @QueryParam("origins") String origin,
                @QueryParam("destinations") String destination,
                @QueryParam("mode") Integer mode,
                @QueryParam("units") Integer units) throws MalformedURLException, IOException, ApiException, InterruptedException {

                DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(MAP_CONTEXT);
                DistanceMatrix matrix = req.origins(origin)
                        .destinations(destination)
                        .mode(getTravelMode(mode))
                        .language("pl-PL")
                        .units(getUnitSystem(units))
                        .await();
                
                return matrix;
        }        
        
        private Unit getUnitSystem(int units) {
               switch (units) {
                       case TRAVEL_METRES:
                               return Unit.METRIC;
                       case TRAVEL_MILES:
                               return Unit.IMPERIAL;
               }
               return null;
        }
        
        private TravelMode getTravelMode(int mode) {
                switch (mode) {
                        case TRAVEL_CAR:
                                return TravelMode.DRIVING;
                        case TRAVEL_BICYCLE:
                                return TravelMode.BICYCLING;
                        case TRAVEL_TRANSIT:
                                return TravelMode.TRANSIT;
                        case TRAVEL_WALKING:
                                return TravelMode.WALKING;
                }
                return null;
        }

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

        @GET
        @Path("random")
        @Produces(MediaType.APPLICATION_JSON)
        public FunFact getRandomFact() throws IOException {
                ArrayList<FunFact> facts = getFunFactsContext().findAllFacts();
                String text = facts.get(drawNumberFromOneToLimit(facts.size())).getText();

                return facts.get(drawNumberFromOneToLimit(facts.size()));
        }

        private int drawNumberFromOneToLimit(int limit) {
                return generator.nextInt(limit - 1) + 1;
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
                                if (o1.getId() == 0) {
                                        return -1;
                                }
                                return o1.getId().compareTo(o2.getId());
                        }
                });
        }

        private void compareFactsByImportance(ArrayList<FunFact> facts) {
                Collections.sort(facts, new Comparator<FunFact>() {
                        @Override
                        public int compare(FunFact o1, FunFact o2) {
                                return -o1.getImportance().compareTo(o2.getImportance());
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
