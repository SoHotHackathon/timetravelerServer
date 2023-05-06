package com.example.Back.service;

import com.example.Back.controller.dto.peopleListRequest;
import com.example.Back.domain.Person;
import com.example.Back.repository.PersonRepository;
import com.example.Back.repository.PersonSearch;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PersonService
{
    private final PersonRepository personRepository;

    //검색
    public List<Person> findPeople()
    {
        return personRepository.findAll();
    }

    public List<Person> findByCategoryId(Long category_id)
    {
        return personRepository.findByCategoryId(category_id);
    }

    public void getList(peopleListRequest peopleListRequest) {
        peopleListRequest people = peopleListRequest;

        for (String person : people.getNameList()) {
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
                String job = "";

                for (Element row : rows) {
                    Element th = row.selectFirst("th");
                    Element td = row.selectFirst("td");
                    if (th != null && td != null) {
                        String attribute = th.text();
                        String value = td.text();
                        if (attribute.equals("출생")) {
                            birth = value;
                        } else if (attribute.equals("직업")) {
                            job = value;

                        }
                    }

                    // name, birth, image
                    String imgUrl;

                    // png, jpg 확인 작업
                    String target = photos.get(0);
                    String word = target.substring(target.length() - 3);

                    // word == png : .get(1) if else .get(0)
                    if (word.equals("png")) {
                        imgUrl = photos.get(1);
                        System.out.println(photos.get(photos.size() - 1));
                    } else {
                        imgUrl = photos.get(0);
                        System.out.println(photos.get(0));
                    }

                    Person p = new Person();
                    p.setName(name);

                    Pattern pattern = Pattern.compile("\\((\\d{4})-\\d{2}-\\d{2}\\)");
                    Matcher matcher = pattern.matcher(birth);

                    String year = "";
                    if (matcher.find()) {
                        year = matcher.group(1);
                    }

                    int birthYear = Integer.parseInt(year);
                    LocalDate dateOfBirth = LocalDate.of(birthYear, 5, 16);
                    p.setBirthDate(dateOfBirth);

                    p.setPhotoUrl(imgUrl);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
