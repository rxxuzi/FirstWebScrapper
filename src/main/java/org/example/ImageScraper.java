package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class ImageScraper {
    //array list String
    static ArrayList<String> Images = new ArrayList<>();
    public static void main(String[] args) {

        String url = "https://en.wikipedia.org/wiki/Friedrich_Nietzsche";

        String saveDir = "./rsc"; // 保存先のディレクトリ

        try {
            // ウィキペディアのページにアクセス
            Document doc = Jsoup.connect(url).get();
            System.out.println("TITILE IS -> " + doc.title().toUpperCase());

            //Element get by class
            Elements elements = doc.getElementsByClass("thumbimage");
            System.out.println("ELEMENTS SIZE IS -> " + elements.size());

            for (Element element : elements) {
                String imageUrl = element.attr("src");
                System.out.println("IMAGE URL IS -> https:" + imageUrl);
                Images.add("https:" + imageUrl);
                String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
                System.out.println("FILE NAME IS -> " + fileName);
            }

            saveImage(Images.get(0));
            System.out.println("SUCCESS");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 画像を保存するメソッド
    private static void saveImage(String imageUrl) throws IOException {
        // URL接続を開く
        URL url = new URL(imageUrl);
        //URLからread
        BufferedImage image = ImageIO.read(url);
        File outPutFile = new File("./rsc/image.png");
        //ファイルに保存
        ImageIO.write(image, "png", outPutFile);
    }
}

