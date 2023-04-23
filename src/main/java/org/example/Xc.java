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
        File file = new File("./src/main/html/index.html");
        Document doc = Jsoup.parse(file);
        String title = doc.title();
        System.out.println("TITLE IS : \"" + title + "\"");

        Element body = doc.body();
        Elements elements = body.getElementsByTag("p");
        for (Element element : elements) {
            System.out.println(element.text());
        }
    }
}