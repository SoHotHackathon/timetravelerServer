package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class peopleCrawlingTest {
    public static void main(String[] args) {
        String[] people = {"플라톤", "이마누엘_칸트", "프리드리히_니체", "공자", "르네_데카르트"};

        for (String person : people) {
            String url = "https://ko.wikipedia.org/wiki/" + person;
            try {
                Document doc = Jsoup.connect(url).get();

                Element nameElement = doc.selectFirst("h1#firstHeading");
                String name = nameElement.text();

                Element infoboxElement = doc.selectFirst("table.infobox");
                Elements rows = infoboxElement.select("tr");

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
                        } else if (attribute.equals("이미지")) {
                            Element imageElement = td.selectFirst("img");
                            if (imageElement != null) {
                                imageUrl = imageElement.absUrl("src");
                            }
                        }
                    }
                }

                System.out.println("이름: " + name);
                System.out.println("출생: " + birth);
                System.out.println("이미지 URL: " + imageUrl);
                System.out.println();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

