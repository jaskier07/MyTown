/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alexp.pieniezno;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author alexp
 */
public class FunFactsContext {
        public static final String PATH_TO_SOURCE_FILE = "C:/Users/alexp/Dropbox/Stoodia/V semestr/AUI/lab 6 - projekt/Pieniezno/src/main/webapp/data/fun facts3.txt";
        public static final int IMPORTANCE_BEGIN = 0;
        public static final int IMPORTANCE_END = 1;
        public static final int TEXT_BEGIN = 2;

        public static final boolean LOAD_FROM_FILE = false;
        private final List<FunFact> facts;

        public FunFactsContext() throws IOException {
                facts = Collections.synchronizedList(new ArrayList<FunFact>());

               if (LOAD_FROM_FILE) {
                        //loadFromFile();
                } else {
                        loadFromApp();
                }
        }

        private void loadFromFile() throws IOException {
                BufferedReader reader;
                try {
                        reader = new BufferedReader(new InputStreamReader(new FileInputStream(PATH_TO_SOURCE_FILE)));                        
                        
                        int index = 0;
                        int importance;
                        String text;
                        String line;
                        while ((line = reader.readLine()) != null) {
                                importance = Integer.parseInt(line.substring(IMPORTANCE_BEGIN, IMPORTANCE_END));
                                text = line.substring(TEXT_BEGIN, line.length());
                                //facts.put(index, new FunFact(index, importance, text));
                                facts.add(index, new FunFact(index, importance, text));
                                index++;
                        }
                } catch (FileNotFoundException ex) {
                        loadFromApp();
               }

        }

        public ArrayList<FunFact> findAllFacts() {
                return new ArrayList<>(facts);
        }

        private void loadFromApp() {
                int index = 0;
                
                facts.add(index, new FunFact(index++, 2, "W Pieniężnie znajduje się uczelnia wyższa - największe w Polsce Seminarium Werbistów."));
                facts.add(index, new FunFact(index++, 3, "Do pieniężniejskiego seminarium przyjechał kiedyś na parę dni sam Jan Paweł II. Oczywiście nie będąc jeszcze wtedy papieżem."));
                facts.add(index, new FunFact(index++, 2, "W Pieniężnie znajduje się najwyższy czynny most kolejowy w Polsce."));
                facts.add(index, new FunFact(index++, 3, "W Pieniężnie policja jest czynna od 8 do 16."));
                facts.add(index, new FunFact(index++, 1, "Ciekawostka od doktora Jędrucha: W latach międzywojennych w Pieniężnie zatrzymywały się pociągi na linii Kaliningrad - Wiedeń."));
                facts.add(index, new FunFact(index++, 3, "Doktor Jędruch jest wielkim fanem Pieniężna."));
                facts.add(index, new FunFact(index++, 1, "W Pieniężnie, przed wyborami występował zespół Lady Pank. Zespół jeszcze przed zakwaterowaniem w lokalnym hotelu udał się do sklepu monopolowego."));
                facts.add(index, new FunFact(index++, 2, "Wodecki, gdy zobaczył pieniężniejskie elewatory powiedział \"Ale czad!\"."));
                facts.add(index, new FunFact(index++, 1, "Braniewska policja notorycznie patroluje wszystkie najsensowniejsze miejscówki służące alkoholizacji lokalnej młodzieży."));
                facts.add(index, new FunFact(index++, 3, "Gdyby zsumować wszystkich pracowników pieniężniejskiej policji pracujących po godzinie 16 i przemnożyć ich liczbę przez milion, to uzyskamy liczbę zero."));
                facts.add(index, new FunFact(index++, 1, "Obecny dyrektor podstawówki jest tak łysy, że podczas wiosennych zajęć puszczał czołem zajączki na suficie."));
                facts.add(index, new FunFact(index++, 2, "W Pieniężnie nie wybudowano dotąd żadnego lotniska."));
                facts.add(index, new FunFact(index++, 1, "Pieniężno posiada własną obwodnicę, czym zawstydza np. Olsztyn."));
                facts.add(index, new FunFact(index++, 3, "Pieniężno podczas wojny polsko-krzyżackiej jako jedyne miasto na Warmi opowiedziało się za Krzyżakami."));
                facts.add(index, new FunFact(index++, 1, "Nazwa miasta pochodzi od Seweryna Pieniężnego. Ma on jedną wspólną rzecz z nazwą miasta - nazwisko."));
                facts.add(index, new FunFact(index++, 2, "95% miasta zostało zrujnowanych w czasie II Wojny Światowej."));
                facts.add(index, new FunFact(index++, 1, "Józef Piłsudski nigdy nie był w Pieniężnie."));
                facts.add(index, new FunFact(index++, 2, "Pienieżno jako jedyna miejscowość w gminie posiada biedronkę."));
                facts.add(index, new FunFact(index++, 1, "Papierosy z przemytu można kupić już od 6 złotych."));
                facts.add(index, new FunFact(index++, 2, "Pieniężno znajduje się w strefie nadgranicznej, co wiąże się z konkurencyjnymi cenami rosyjskich papierosów i spirytusu."));
                facts.add(index, new FunFact(index++, 1, "W lipcu w Pieniężnie wystąpi Zenek Martyniuk."));
                facts.add(index, new FunFact(index++, 2, "Podczas jednych z dożynek na scenie występował rolnik, który w TVP szukał żony."));
                facts.add(index, new FunFact(index++, 2, "W Pieniężnie wybudowano nowy budynek gimnazjum w roku, w którym zlikwidowano gimnazja."));
                facts.add(index, new FunFact(index++, 3, "Mikołaj Kopernik spędził 3 dni w Pieniężnie."));
                facts.add(index, new FunFact(index++, 2, "Rosyjski biznesman chciał zakupić pieniężniejski pomnik za ciężarówkę zboża."));
                facts.add(index, new FunFact(index++, 1, "Na pieniężniejskim wiadukcie znajduje się nieoficjalna Polska Galeria Zespołów Muzycznych."));
                facts.add(index, new FunFact(index++, 1, "W Pieniężnie w 2008 roku pewna rodzina wygrała 8 milionów złotych w lotku. Mieszkańcy zaczęli witać się z nimi hasłem \"pożycz stówę\"."));
                facts.add(index, new FunFact(index++, 3, "Żeby nie było - osobiście kocham Pieniężno, czasem jednak można przedstawić je w trochę krzywszym zwierciadle ;)"));
        }

}
