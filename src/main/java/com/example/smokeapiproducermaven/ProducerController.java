package com.example.smokeapiproducermaven;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {
    PersonCheckingService personCheckingService;

    public ProducerController(PersonCheckingService personCheckingService) {
        this.personCheckingService = personCheckingService;
    }
    @RequestMapping(value = "/check",method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
    public Response check(@RequestBody PersonToCheck personToCheck){
        if (this.personCheckingService.shouldGetSmoke(personToCheck)){
            return new Response(SmokeCheckStatus.OK);
        }
        return new Response(SmokeCheckStatus.NOT_OK);
    }
}
interface PersonCheckingService{
    Boolean shouldGetSmoke(PersonToCheck personToCheck);
}
class PersonToCheck{
    public int age;
    public PersonToCheck(int age) {
        this.age = age;
    }
    public PersonToCheck() {
    }
}
class Response{
    public SmokeCheckStatus status;

    public Response(SmokeCheckStatus status) {
        this.status = status;
    }
}
enum SmokeCheckStatus{
    OK,NOT_OK
}