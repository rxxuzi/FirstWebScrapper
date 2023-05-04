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
import java.util.Objects;


public class Scraper {
    static ArrayList<String> Images = new ArrayList<>();
    static String saveDirPath = "./rsc/pics/"; // 保存先のディレクトリのpath
    static int count = 0;
    public static String tag = "bikini";

    private static String tmpUrl = "";
    private static final int pages = 2;
    public static boolean isSuccess = false;

    public static int maxImages = 10;

    public Scraper(String tag) {
        Scraper.tag = tag;
    }

    public static void  run(){
        //space -> _
        Scraper.tag = Scraper.tag.replace(" ", "_");

        String url = "https://danbooru.me/posts?tags=" + tag;

        try{
            Document docx = Jsoup.connect(url).get();
            System.out.println(docx.title());
            //get img by  tag name = "img" and class = "has-cropped-true"
            Elements Img;
            for(int i = 1 ; i < pages+1  ; i++) {
                //page数が2以上の時にurlを変える
                if(i > 1){
                    url = "https://danbooru.me/posts?page=" + i+ "&tags=" + tag;
                }
                //get url
                docx = Jsoup.connect(url).get();
                //get img by tag name = "img"
                Img = docx.getElementsByTag("img");
                System.out.println("img count -> " + Img.size());
                System.out.println(url);
                //今あるページ
                getImageByElements(Img);

                if (maxImages < count){
                    break;
                }
            }
            isSuccess = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("total count -> " + count);
    }

    private static void getImageByElements(Elements Img) throws IOException {
        for (Element img : Img) {
            //スクレイピング
            String imageUrl = "https://danbooru.me" + img.attr("src");

            //同じ画像を拾ってきたらbreak
            if(Objects.equals(tmpUrl, imageUrl)){
                System.out.println("Same file.");
                break;
            }

            if(maxImages < count){
                break;
            }

            //tmpに保存
            tmpUrl = imageUrl;

            //画像URLを取得
            System.out.println("IMAGE URL IS -> " + imageUrl);
            Images.add(imageUrl);

            //保存先を指定
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
        if(image != null){
            File outPutFile = new File(saveDirPath + tag + count+ ".png");
            //ファイルに保存
            ImageIO.write(image, "png", outPutFile);

            System.out.println("("+count + ") SUCCESS -> " + outPutFile.getPath().toUpperCase());
        }else{
            System.out.println("("+count + ") FAIL -> " + imageUrl.toUpperCase());
        }
    }
}