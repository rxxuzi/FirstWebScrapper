package org.example;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;


public class Main {
    static ArrayList<String> Images = new ArrayList<>();
    static String saveDirPath = "./rsc/pics/"; // 保存先のディレクトリのpath
    static int count = 0;
    private static final String tag = "bikini";

    public static void main(String[] args) throws IOException {
        String url = "https://danbooru.me/posts?tags=" + tag;

        Document docx = Jsoup.connect(url).get();
        System.out.println(docx.title());
        //get img by  tag name = "img" and class = "has-cropped-true"
        Elements Img = docx.getElementsByTag("img");
        System.out.println("img count -> " + Img.size());

        for (Element img : Img) {
            System.out.println(img.attr("src"));
            String imageUrl = "https://danbooru.me" + Img.attr("src");
            System.out.println("IMAGE URL IS -> " + imageUrl);
            Images.add(imageUrl);
            String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
            System.out.println("FILE NAME IS -> " + fileName);
            saveImage(imageUrl , fileName);
            count++;
        }
    }
    // 画像を保存するメソッド
    private static void saveImage(String imageUrl , String savePic) throws IOException {
        // URL接続を開く
        URL url = new URL(imageUrl);
        //URLからread
        BufferedImage image = ImageIO.read(url);
        File outPutFile = new File(saveDirPath + tag + count+ ".png");
        //ファイルに保存
        ImageIO.write(image, "png", outPutFile);

        System.out.println("("+count + ") SUCCESS -> " + outPutFile.getPath().toUpperCase());
    }
}