package org.example;
import java.io.File;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
//        String html = "<p>Hello, World</p>";
//        Document doc = Jsoup.parse(html);

        File file = new File("./src/main/html/index.html");
        Document doc = Jsoup.parse(file);
        String title = doc.title();
        String body = doc.body().text();
        System.out.println("TITLE IS : \"" + title + "\"");
        System.out.println("BODY IS : \"" + body + "\"");
//        System.out.println(doc.html());
    }
}