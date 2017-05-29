/**
 * Created by carlosalberto on 5/27/2017.
 */
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.helper.Validate;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("Inserte una URL valida: ");
        String url = "";
        Scanner entradaEscaner = new Scanner(System.in);
        url = entradaEscaner.nextLine();
        System.out.println("la URL escrita fue: "+ url);


        Document doc = Jsoup.connect(url).get();

       //File input = new File("C:\\Users\\carlosalberto\\Desktop\\html\\MiPaginaWeb.html");
        //Document doc = Jsoup.parse(input, "UTF-8");


        ///////////////////////////Contador de lineas///////////////////////////////////////////
        String count = doc.html();
        int lineas = 0;
        String[] parts =  count.split("\n");
        lineas = parts.length;
        System.out.println("La cantidad de lineas del recurso retornado es de: "+lineas);
        //////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////Contador de tags P/////////////////////////////////////////

        Elements p = doc.getElementsByTag("p");
        System.out.println("Cantidad de P elemento: "+p.size());
        //////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////Contador de IMG dentro de p/////////////////////////////////////
        Elements img = p.select("img");
        System.out.println("Cantidad de imagenes dentro del tag P: "+img.size());
        /////////////////////////////////////////////////////////////////////////////////////////////


        Elements formularios = doc.getElementsByTag("FORM");

        Elements withget = new Elements();
        Elements withpost = new Elements();

        for( Element element : formularios )
        {
            for( Attribute attribute : element.attributes() )
            {
                if( attribute.getValue().equalsIgnoreCase("GET") )
                {
                    withget.add(element);
                }
            }
        }

        for( Element element : formularios )
        {
            for( Attribute attribute : element.attributes() )
            {
                if( attribute.getValue().equalsIgnoreCase("POST") )
                {
                    withpost.add(element);
                }
            }
        }
        System.out.println("Formularios con el metodo GET: "+ withget.size());
        System.out.println("Formularios con el metodo POST: "+ withpost.size());




        int x = 0;
        for( Element element : formularios ) {

            System.out.println("Formulario-"+(x+1));

            Elements inputs = element.select("INPUT");
            int y =0;
            for (Element ele : inputs){
                System.out.println("input"+(y+1)+"   Type: " +ele.attr("Type"));
             y++;
            }
             x++;

        }
       ////////////////////////////Enviar asignatura//////////////////////////////////////////////////////////////

        for(Element elem : withpost){
            int j = elem.attr("action").length();
            String action =  elem.attr("action").toString().substring(1,j);

            Document docc = Jsoup.connect(url + action).data("asignatura","practica1").post();
            System.out.println(docc);
        }
        


    }
}
