package ru.vlad.kim.rest.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vlad.kim.rest.dao.PersonRepository;

import java.io.*;

@Service
public class PersonService {
    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public String savePerson(String person) throws IOException {
        personRepository.savePerson(person);
        String convertedXml = sendToSoap(person);
        personRepository.savePerson(convertedXml);

        return convertedXml;
    }

    private String sendToSoap(String person) throws IOException {
        String xml = convertToXml(person);
        String URL = "http://localhost:8083/ws";

        StringEntity stringEntity = new StringEntity(buildRequest(xml), "text/xml", "UTF-8");
        stringEntity.setChunked(true);

        HttpPost httpPost = new HttpPost(URL);
        httpPost.setEntity(stringEntity);
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.addHeader("SOAPAction", "getPersonRequest");

        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity entity = httpResponse.getEntity();
        String response = EntityUtils.toString(entity);

        return pasrseResponse(response);
    }

    private String pasrseResponse(String response) {
        response = response.replaceAll("&lt;", "<");
        response = response.replaceAll("&gt;", ">");

        String lines[] = response.split("<ns2:person>");
        String result = lines[1].split("</ns2:person>")[0];

        return result;
    }

    private String buildRequest(String xml) {
        String request = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"\n" +
                "    xmlns:ns0=\"http://www.kim.ru/soap/\">\n" +
                "    <SOAP-ENV:Header/>\n" +
                "    <SOAP-ENV:Body>\n" +
                "        <ns0:getPersonRequest>\n" +
                "            <ns0:person><![CDATA[%s]]></ns0:person>\n" +
                "        </ns0:getPersonRequest>\n" +
                "    </SOAP-ENV:Body>\n" +
                "</SOAP-ENV:Envelope>";
        request = String.format(request, xml);

        return request;
    }

    private String convertToXml(String person) {
        JSONObject jsonObject = new JSONObject(person);
        String xml = XML.toString(jsonObject);
        xml = "<person>" + xml + "</person>";
        return xml;
    }
}
