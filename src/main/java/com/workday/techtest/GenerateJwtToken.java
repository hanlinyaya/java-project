package com.workday.techtest;

import org.apache.commons.codec.binary.Base64;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Signature;
import java.text.MessageFormat;

public class GenerateJwtToken {

    public static void main(String[] args) {

        String header = "{\"alg\":\"RS256\",\"typ\":\"JWT\"}";
        String claimTemplate = "'{'\"iss\": \"{0}\", \"sub\": \"{1}\", \"aud\": \"{2}\", \"exp\": \"{3}\"'}'";

        try {
            StringBuffer token = new StringBuffer();

            //Encode the JWT Header and add it to our string to sign
            token.append(Base64.encodeBase64URLSafeString(header.getBytes("UTF-8")));

            //Separate with a period
            token.append(".");


            //Create the JWT Claims Object
            String[] claimArray = new String[4];
            //iss
            // claimArray[0] = "ZTIzNzJiNGItYWY2Mi00MjZiLWE0YTgtNmM0MTBiY2FmOTI2"; // client id
            claimArray[0] = "YzE1NDg0ODYtYjllMi00MWVjLWI1NjItYWNhNzY1Yzg4YjUx";
            //sub
            claimArray[1] = "lmcneil"; // user id: OSVServiceUser?
            //aud
            claimArray[2] = "wd";
            //exp
            claimArray[3] = Long.toString( ( System.currentTimeMillis()/1000 ) + 300);
            MessageFormat claims;
            claims = new MessageFormat(claimTemplate);
            String payload = claims.format(claimArray);
            System.out.println("payload: " + payload);

            //Add the encoded claims object
            token.append(Base64.encodeBase64URLSafeString(payload.getBytes("UTF-8")));

            //Load the private key from a keystore
            KeyStore keystore = KeyStore.getInstance("JKS");
            keystore.load(new FileInputStream("/Users/lin.han/Documents/JWTkeystore.jks"), "Workday123!".toCharArray());
            PrivateKey privateKey = (PrivateKey) keystore.getKey("Workday", "Workday123!".toCharArray());

            //Sign the JWT Header + "." + JWT Claims Object
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(privateKey);
            signature.update(token.toString().getBytes("UTF-8"));
            String signedPayload = Base64.encodeBase64URLSafeString(signature.sign());

            //Separate with a period
            token.append(".");

            //Add the encoded signature
            token.append(signedPayload);

            System.out.println(token.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
