package ru.vlad.kim.soap;

import org.springframework.stereotype.Service;
import org.springframework.xml.transform.StringSource;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.StringWriter;

@Service
public class PersonService {

    public String transformToXslt(String person) throws TransformerException {
        String xsltPath = "C:\\Users\\v.kim\\projects\\soap\\src\\main\\resources\\template.xsl";
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(new File(xsltPath));
        Transformer transformer = factory.newTransformer(xslt);
        person = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" + person;
        Source xml = new StringSource(person);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        transformer.transform(xml, result);

        String response = writer.toString();
        response = response.replaceAll("&lt;", "<");
        response = response.replaceAll("&gt;", ">");

        return response;
    }
}
