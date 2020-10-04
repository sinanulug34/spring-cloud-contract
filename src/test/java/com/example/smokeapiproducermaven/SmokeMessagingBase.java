package com.example.smokeapiproducermaven;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifier;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
//we are using spring boot test web env node, because it s a messaging test
@SpringBootTest(classes = SmokeApiProducerMavenApplication.class,webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureMessageVerifier
public abstract class SmokeMessagingBase {

    @Inject MessageVerifier messageVerifier;
    @Autowired PersonCheckingService personCheckingService;
        // before we run any test, we want to be a sure that there are no messages in the queue of spring cloud stream
    @Before
    public void setup(){
        this.messageVerifier.receive("output",100, TimeUnit.MILLISECONDS);
    }
    //we re trigger the positive scenario
    public void clientIsOldEnough(){
        this.personCheckingService.shouldGetSmoke(new PersonToCheck(25));
    }
    // we re trigger the negative scenario
    public void clientIsTooYoung(){
        this.personCheckingService.shouldGetSmoke(new PersonToCheck(15));
    }

}
