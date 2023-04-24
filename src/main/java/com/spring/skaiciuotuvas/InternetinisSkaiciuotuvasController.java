package com.spring.skaiciuotuvas;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.spring.skaiciuotuvas.HelloWorld;

import java.util.HashMap;

//@Restcontroller anotacija naudojama tada, kai fronte nenaudojame Springo (JS, React, angularir t.t.)
//dazniausiai grazinami formatai Json, xml
//t.y. negrazinamas vaizdas (formoss html, JSP)
//kadangi mums reikai grazinti vazda (view)pagal SPring MVC, naudosime anotacija @Controller
@Controller
@EnableAutoConfiguration
public class InternetinisSkaiciuotuvasController {
        //kadangi skaiciuotuvo forma naudoja post metoda, cai irgi nurodysime post
        @PostMapping("/skaiciuoti")
        //@RequestMapping(method = RequestMethod.POST, value = "/index")
        //RequestParam anotacija perduoda siuo atveju per URL perduotus duomenys (2 skaicai ir operacijos zenklas)
        // kurie atalpinami i sarasa (HashMap) raktas-reiksme)
        //Pirmas stringas yra raktas (sk1, sk2, zenklas), o antras yra reiksme
        //Raktai tiek frontende, tiek backende turi sutapti!
        //URl pvz.:http://localhost:8080/index?sk1=2&sk2=3&zenklas=*


        public String index(@RequestParam HashMap<String,String> ivedimoSaras, ModelMap isvedimoSarasas){
            //Per URL perduodamas raktas (kintamojo pavadinimas) sk1
            //Pagal rakta sk1istraukiama reiksme pvz. tarkim vartotojas ivede 8
            //vadinasi mums reikai konvertuoti is stringo i inta, kad apskaiciuoti rezultata
            int sk1=Integer.parseInt(ivedimoSaras.get("sk1"));
            int sk2=Integer.parseInt(ivedimoSaras.get("sk2"));
            String zenklas= ivedimoSaras.get("zenklas");
            //return sk1+" "+sk2+" "+zenklas;
            int ats=0;

            if (zenklas.equals("+")) {
                ats = sk1 + sk2;
            } else if (zenklas.equals("-")) {
                ats = sk1 - sk2;
            } else if (zenklas.equals("*")) {
                ats = sk1 * sk2;
            } else if (zenklas.equals("/")) {
                ats = sk1 / sk2;
            }

            //ivedimoSaras naudojamas siusti duomenys is Spring mvc kontrolerio i jsp faila (vaizda)
            isvedimoSarasas.put("sk1",sk1);
            isvedimoSarasas.put("sk2",sk2);
            isvedimoSarasas.put("zenklas",zenklas);
            isvedimoSarasas.put("rezultatas",ats);

            //grazinamas vaizdas(forma)
            //svarbu nurodyti per application.properties prefix ir sufix
            //nes pagal tai ieskos vaizdo projekte
            return "skaiciuoti";

            // ApplicationContext yra interfaisas skirtas suteikti informacija apie aplikacijos konfiguracija.
            //Siuo atveju naudojama koniguracija paimama is XMP failo
            //ApplicationContext appContext = new ClassPathXmlApplicationContext("beans.xml");
            // bean - kalses objektas kuris atitinka singleton sablona.
            //HelloWorld bean = (HelloWorld) appContext.getBean("helloWorld");
            //return bean.getHello();

           /* return "Internetinis skaiciuotuvas <p>"+
                    "<b>Galimos operacijos: </br>"+
                    "<li>&nbsp;&nbsp; sudeti </br>"+
                    "<li>&nbsp;&nbsp; dauginti </br>"+
                    "<li>&nbsp;&nbsp; dalinti </br>"+
                    "<li>&nbsp;&nbsp; atimti </br></p>";*/
        }
        @RequestMapping(method = RequestMethod.GET, value = "/")
        public String rodytiNamuPuslapi(){
            //graziname JSP faila kuris turi buti talpinamas "webapp->WEB-INF->JSP" aplanke
            return "skaiciuotuvas";
        }

    }


