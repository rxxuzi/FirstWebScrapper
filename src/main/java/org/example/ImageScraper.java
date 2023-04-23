package org.example;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JFrame;
import javax.imageio.ImageIO;

public class ImageScraper {

    public static void main(String[] args) {

        String url = "https://en.wikipedia.org/wiki/Friedrich_Nietzsche";
        String saveDir = "./rsc"; // 保存先のディレクトリ

        try {
            // ウィキペディアのページにアクセス
            Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/Friedrich_Nietzsche").get();
            System.out.println("SUCCESS");

            // 画像要素を取得
            Elements imageElements = doc.select("img[src*=/wiki/File:]");

            int imageCount =  imageElements.size();
            System.out.println("IMAGE COUNT ->" + imageCount);

            // 画像のURLを取得して、保存する
            for (Element element : imageElements) {
                String imageUrl = element.absUrl("src");
                System.out.println("IMAGE URL ->" + imageUrl);
                saveImage(imageUrl, saveDir);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        JFrame jf = new JFrame("SCRAPPER");
        jf.setSize(500, 500);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    // 画像を保存するメソッド
    private static void saveImage(String imageUrl, String saveDir) throws IOException {
        // URL接続を開く
        URL url = new URL(imageUrl);
        URLConnection conn = url.openConnection();

        // ファイル名を取得
        String fileName = url.getFile().substring(url.getFile().lastIndexOf("/") + 1);

        // 保存先のディレクトリが存在しない場合は作成する
        Path dirPath = Paths.get(saveDir);
        if (!Files.exists(dirPath)) {
            Files.createDirectories(dirPath);
        }

        // 画像を保存する
        try (InputStream in = conn.getInputStream();
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveDir + fileName))) {

            byte[] buf = new byte[1024];
            int len;

            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
        }
    }
}

