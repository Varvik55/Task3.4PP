package org.example.task3_4;

import org.example.task3_4.entity.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Communication {

    private final RestTemplate restTemplate;
    private final HttpHeaders httpHeaders;
    private final String URL = "http://94.198.50.185:7081/api/users";

    public Communication(RestTemplate restTemplate, HttpHeaders httpHeaders) {
        this.restTemplate = restTemplate;
        this.httpHeaders = httpHeaders;
        this.httpHeaders.set("Cookie",
                String.join(";", restTemplate.headForHeaders(URL).get("Set-Cookie")));

    }


    public List<User> showAllUser(){
        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET
                , null, new ParameterizedTypeReference<List<User>>() {
        });
        return responseEntity.getBody();
    }

    public ResponseEntity<String> saveUser (){
        User user = new User(3L, "James", "Brown", (byte) 5);
        HttpEntity<User> entity = new HttpEntity<>(user,httpHeaders);
        return restTemplate.postForEntity(URL,entity,String.class);
    }

    public  ResponseEntity<String> apdateUser (){
        User user = new User(3L, "Thomas", "Shelby", (byte) 5);
        HttpEntity<User> entity = new HttpEntity<>(user,httpHeaders);
        return restTemplate.exchange(URL,HttpMethod.PUT,entity,String.class,3);
    }
    public ResponseEntity<String> deleteUser (){
        Map<String,Long> map = new HashMap<>(){{
            put("id",(long)3);
        }};
        HttpEntity<User> entity = new HttpEntity<>(null,httpHeaders);
        return restTemplate.exchange(URL + "/{id}",HttpMethod.DELETE,entity,String.class,map);
    }



}
