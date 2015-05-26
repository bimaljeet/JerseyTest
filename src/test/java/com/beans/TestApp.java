package com.jersey.test;

import com.protocolBufferTest.AddressBookProtos;
import junit.framework.TestCase;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


/**
 * Created with IntelliJ IDEA.
 * User: bnayar
 * Date: 11/24/13
 * Time: 10:17 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestApp extends TestCase {

    private static boolean isOpen = true;

    public TestApp() {
    }

    public TestApp(String name) {
        super(name);
    }

//    public void d_testProtocolBuffer() {
//        HttpURLConnection conn = null;
//        try {
//            URL url = new URL("http://localhost:8080/JerseyTest/rest/person");
//            conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            conn.setRequestProperty("Accept", "application/x-protobuf");
//
//            if (conn.getResponseCode() != 200) {
//                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
//            }
//
//            System.out.print("Length = " + conn.getContentLength());
//
//            AddressBookProtos.AddressBook addressBook = AddressBookProtos.AddressBook.parseFrom(conn.getInputStream());
//
//            PrintAddressBook(addressBook);
//
//        } catch (MalformedURLException e) {
//
//            e.printStackTrace();
//
//        } catch (IOException e) {
//
//            e.printStackTrace();
//
//        } finally {
//            if (null != conn)
//                conn.disconnect();
//        }
//    }


    public void testProtocolBuffer() {
        HttpURLConnection conn = null;
        try {
            URL url = new URL("http://localhost:8080/JerseyTest/rest/person");
            URLConnection urlc = url.openConnection();
            urlc.setDoInput(true);
            urlc.setRequestProperty("Accept", "application/x-protobuf");
            AddressBookProtos.Contact person = AddressBookProtos.Contact.newBuilder().mergeFrom(urlc.getInputStream()).build();
            Printperson(person);
        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        } finally {
            if (null != conn)
                conn.disconnect();
        }
    }

//    private void PrintAddressBook(AddressBookProtos.AddressBook addressBook) {
//        for (AddressBookProtos.Person person : addressBook.getPersonList()) {
//            System.out.println("Person ID: " + person.getId());
//            System.out.println("  Name: " + person.getName());
//            if (person.hasEmail()) {
//                System.out.println("  E-mail address: " + person.getEmail());
//            }
//
//            for (AddressBookProtos.Person.PhoneNumber phoneNumber : person.getPhoneList()) {
//                switch (phoneNumber.getType()) {
//                    case MOBILE:
//                        System.out.print("  Mobile phone #: ");
//                        break;
//                    case HOME:
//                        System.out.print("  Home phone #: ");
//                        break;
//                    case WORK:
//                        System.out.print("  Work phone #: ");
//                        break;
//                }
//                System.out.println(phoneNumber.getNumber());
//            }
//        }
//    }

    private void Printperson(AddressBookProtos.Contact person) {
        System.out.println("Person ID: " + person.getId());
        System.out.println("  Name: " + person.getFormattedName());
//        if (person.hasEmail()) {
//            System.out.println("  E-mail address: " + person.getEmail());
//        }

        for (AddressBookProtos.Contact.PhoneNumber phoneNumber : person.getPhoneList()) {
            switch (phoneNumber.getType()) {
                case P_MOBILE:
                    System.out.print("  Mobile phone #: ");
                    break;
                case P_HOME:
                    System.out.print("  Home phone #: ");
                    break;
                case P_WORK:
                    System.out.print("  Work phone #: ");
                    break;
            }
            System.out.println(phoneNumber.getNumber());
        }
    }
}
