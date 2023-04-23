package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;


public class Xc {
    public static void main(String[] args) throws IOException {
        view();
    }
    private static void view() throws IOException {
        File file = new File("./src/main/html/Densan.html");
        Document doc = Jsoup.parse(file);
        String title = doc.title();
        System.out.println("TITLE IS : \"" + title + "\"");

        Element body = doc.body();
        Elements elements = body.getElementsByTag("h2");

        //get Elements By id and class
        Element elements2 = doc.getElementById("hello");
        System.out.println("ELEMENT BY ID -> \"hello\"");
        System.out.println(elements2);

        //get Elements By tag
        System.out.println("ELEMENTS BY TAG -> \"code\"");
        Elements elements3 = doc.getElementsByTag("code");
        System.out.println(elements3);


        for (Element element : elements) {
            System.out.println(element.text());
        }
    }
}