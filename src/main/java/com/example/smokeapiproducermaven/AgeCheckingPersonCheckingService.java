package com.example.smokeapiproducermaven;

import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class AgeCheckingPersonCheckingService implements PersonCheckingService {
    private final Source source;

    public AgeCheckingPersonCheckingService(Source source) {
        this.source = source;
    }

    @Override
    public Boolean shouldGetSmoke(PersonToCheck personToCheck) {
        boolean shouldGetSmoke = personToCheck.age>=20;
           this.source
                   .output().send(MessageBuilder
           .withPayload(new Verification(shouldGetSmoke)).build());
    return shouldGetSmoke;
    }
public static class Verification{
        boolean eligible;

    public Verification(boolean eligible) {
        this.eligible = eligible;
    }

    public boolean isEligible() {
        return eligible;
    }

    public void setEligible(boolean eligible) {
        this.eligible = eligible;
    }

    public Verification() {
    }
}

}
