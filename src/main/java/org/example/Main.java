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
        String url = "https://danbooru.me/posts?tags=dark_elf";
        String iconUrl = null;

        Document docx = Jsoup.connect(url).get();
        System.out.println(docx.title());
//        Element iconElement = docx.select("img[src*=profile_images]").first();
        //get img class = "has-cropped-true"
        Elements imgs = docx.getElementsByClass("has-cropped-true");

        for (Element img : imgs) {
            System.out.println(img.attr("src"));
        }

    }
}