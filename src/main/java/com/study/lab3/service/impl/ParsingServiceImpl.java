package com.study.lab3.service.impl;

import com.study.lab3.dto.CategoryDto;
import com.study.lab3.dto.ItemDto;
import com.study.lab3.service.CategoryService;
import com.study.lab3.service.ItemService;
import com.study.lab3.service.ParsingService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParsingServiceImpl implements ParsingService {

    private final ItemService itemService;
    private final CategoryService categoryService;

    XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

    @Override
    public void parseXML(MultipartFile file) {
        try (InputStream is = file.getInputStream()) {
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(is);
            Long id = null;
            Long parentId = null;
            String name = null;
            String description = null;
            Long categoryId = null;
            String category = null;
            Integer price = null;
            Integer bnPrice = null;
            String url = null;
            String image = null;
            String vendor = null;
            while (reader.hasNext()) {
                XMLEvent nextEvent = reader.nextEvent();
                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();
                    switch (startElement.getName().getLocalPart()) {
                        case "category":
                            Attribute idAttr = startElement.getAttributeByName(new QName("id"));
                            Attribute parentIdAttr = startElement.getAttributeByName(new QName("parentID"));
                            nextEvent = reader.nextEvent();
                            name = nextEvent.asCharacters().getData();
                            if (idAttr != null) {
                                id = Long.parseLong(idAttr.getValue());
                            }
                            if (parentIdAttr != null) {
                                parentId = Long.parseLong(parentIdAttr.getValue());
                            }
                            categoryService.create(CategoryDto.builder()
                                    .id(id)
                                    .parentId(parentId)
                                    .name(name)
                                    .build());
                            break;
                        case "item":
                            idAttr = startElement.getAttributeByName(new QName("id"));
                            if (idAttr != null) {
                                id = Long.parseLong(idAttr.getValue());
                            }
                            break;
                        case "name":
                            nextEvent = reader.nextEvent();
                            name = nextEvent.asCharacters().getData();
                            break;
                        case "categoryId":
                            nextEvent = reader.nextEvent();
                            categoryId = Long.valueOf(nextEvent.asCharacters().getData());
                            break;
                        case "description":
                            try {
                                nextEvent = reader.nextEvent();
                                description = nextEvent.asCharacters().getData();
                            } catch (Exception ignored) {}
                            break;
                        case "price":
                            try {
                                nextEvent = reader.nextEvent();
                                price = Integer.parseInt(nextEvent.asCharacters().getData());
                            } catch (Exception ignored) {
                            }
                            break;
                        case "bnprice":
                            nextEvent = reader.nextEvent();
                            bnPrice = Integer.parseInt(nextEvent.asCharacters().getData());
                            break;
                        case "url":
                            nextEvent = reader.nextEvent();
                            url = nextEvent.asCharacters().getData();
                            break;
                        case "image":
                            nextEvent = reader.nextEvent();
                            image = nextEvent.asCharacters().getData();
                            break;
                        case "vendor":
                            nextEvent = reader.nextEvent();
                            vendor = nextEvent.asCharacters().getData();
                            break;
                    }
                }
                if (nextEvent.isEndElement()) {
                    EndElement endElement = nextEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("item")) {
                        itemService.create(ItemDto.builder()
                                .id(id)
                                .name(name)
                                .description(description)
                                .categoryId(categoryId)
                                .price(price)
                                .bnPrice(bnPrice)
                                .url(url)
                                .image(image)
                                .vendor(vendor)
                                .build()
                        );
                    }
                }
            }
        } catch (IOException | XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }
}
