package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class peopleCrawlingTest {
    public static void main(String[] args) {
        String[] people = {"존_폰_노이만", "빌_게이츠", "세르게이_브린", "찰스_배비지"};

        for (String person : people) {
            String url = "https://ko.wikipedia.org/wiki/" + person;
            try {
                Document doc = Jsoup.connect(url).get();

                Element nameElement = doc.selectFirst("h1#firstHeading");
                String name = nameElement.text();

                Element infoboxElement = doc.selectFirst("table.infobox");
                Elements rows = infoboxElement.select("tr");

                // img 태그 중에서 class 속성이 "image"인 요소 선택
                Elements infoboxElem = doc.select(".infobox");

                List<String> photos = new ArrayList<>();

                for (Element infobox : infoboxElem) {
                    Elements img = infobox.select("img[src*=upload.wikimedia.org]");
                    for (Element e : img)
                        photos.add(e.attr("src"));
                }

                String birth = "";
                String imageUrl = "";

                for (Element row : rows) {
                    Element th = row.selectFirst("th");
                    Element td = row.selectFirst("td");
                    if (th != null && td != null) {
                        String attribute = th.text();
                        String value = td.text();
                        if (attribute.equals("출생")) {
                            birth = value;
                        }
                    }
                }

                System.out.println("이름: " +  name);
                System.out.println("출생: " + birth);

                String target = photos.get(0);
                System.out.println(photos);

                String word = target.substring(target.length() - 3);

                System.out.println(word);

                if (word.equals("png")) {
                    System.out.println(photos.get(photos.size() - 1));
                } else {
                    System.out.println(photos.get(0));
                }

                System.out.println();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

