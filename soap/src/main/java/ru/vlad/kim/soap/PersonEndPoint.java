package ru.vlad.kim.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.kim.soap.GetPersonRequest;
import ru.kim.soap.GetPersonResponse;

import javax.xml.transform.TransformerException;

@Endpoint
public class PersonEndPoint {
    private PersonService service;
    private static final String NAMESPACE_URI = "http://www.kim.ru/soap/";

    @Autowired
    public PersonEndPoint(PersonService service) {
        this.service = service;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonRequest")
    @ResponsePayload
    public GetPersonResponse getPerson(@RequestPayload GetPersonRequest request) throws TransformerException {
        GetPersonResponse response = new GetPersonResponse();
        String xslt = service.transformToXslt(request.getPerson());

        response.setPerson(xslt);
        return response;
    }

}
