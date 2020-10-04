package com.example.smokeapiproducermaven;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;

public abstract class SmokeRestBase {
    @Before
    public void setup(){
        //we use restassured mock mvc with standalonesetup.
        // and we create a controller that verifies if the argument, is that person s age is greater than 20
        RestAssuredMockMvc.standaloneSetup(new ProducerController(argument -> argument.age>=20));
    }
}
