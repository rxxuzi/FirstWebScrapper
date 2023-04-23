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
import java.util.Locale;


public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://twitter.com/rxxuzi";
        String iconUrl = null;

        Document docx = Jsoup.connect(url).get();
        Element iconElement = docx.select("img[src*=profile_images]").first();
        if (iconElement != null) {
            iconUrl = iconElement.attr("src");
        }
        System.out.println(iconUrl);
//        view();
    }

    private static void view() throws IOException {
        File file = new File("./src/main/html/index.html");
        Document doc = Jsoup.parse(file);
        String title = doc.title();
        String body = doc.body().html().toUpperCase(Locale.ROOT);
        System.out.println("TITLE IS : \"" + title + "\"");
        System.out.println("BODY IS : \"" + body + "\"");
    }
}