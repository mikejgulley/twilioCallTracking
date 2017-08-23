package com.lumatik.twiliocalltracking;

import com.twilio.Twilio;
import com.twilio.http.HttpMethod;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

@Component
public class Example {
    // Find your Account Sid and Token at twilio.com/user/account
    private static String ACCOUNT_SID;
    private static String AUTH_TOKEN;
    private static String TWILIO_NUMBER;
    private static String CALL_TO_NUMBER;

    public Example(@Value("${TWILIO_ACCOUNT_SID}") String ACCOUNT_SID, @Value("${TWILIO_AUTH_TOKEN}") String AUTH_TOKEN,
                   @Value("${TWILIO_NUMBER}") String TWILIO_NUMBER, @Value("${CALL_TO_NUMBER}") String CALL_TO_NUMBER) {
        this.ACCOUNT_SID = ACCOUNT_SID;
        this.AUTH_TOKEN = AUTH_TOKEN;
        this.TWILIO_NUMBER = TWILIO_NUMBER;
        this.CALL_TO_NUMBER = CALL_TO_NUMBER;
    }

    public static void placeTestCall() throws URISyntaxException {
//        TwilioRestClient client = new TwilioRestClient.Builder(ACCOUNT_SID, AUTH_TOKEN).build();
//
//        PhoneNumber to = new PhoneNumber(CALL_TO_NUMBER); // Replace with your phone number
//        PhoneNumber from = new PhoneNumber(TWILIO_NUMBER); // Replace with a Twilio number
//        URI uri = URI.create("http://demo.twilio.com/welcome/voice/?Record=true&AnsweredBy=enable");
//
//        // Make the call
//        Call call = Call.creator(to, from, uri).create(client);
//
//        // Print the call SID (a 32 digit hex like CA123..)
//        System.out.println(call.getSid());

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        List<String> callbackEvents = Arrays.asList("initiated", "ringing", "answered", "completed");

        Call call = Call
                .creator(new PhoneNumber(CALL_TO_NUMBER), new PhoneNumber(TWILIO_NUMBER),
                        new URI("http://demo.twilio.com/docs/voice.xml"))
                .setMethod(HttpMethod.GET).setStatusCallback("https://www.myapp.com/events")
                .setStatusCallbackMethod(HttpMethod.POST).setStatusCallbackEvent(callbackEvents).create();

        System.out.println(call.getSid());
    }
}