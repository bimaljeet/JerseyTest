package com.jersey.beans;

import com.protocolBufferTest.AddressBookProtos;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.commons.lang.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: bnayar
 * Date: 11/24/13
 * Time: 8:08 AM
 * To change this template use File | Settings | File Templates.
 */

@Path("/person")
public class PersonJerseyBean {

    @GET
    @Produces("application/x-protobuf")
    public AddressBookProtos.Contact getPerson() {
        return createContact();
    }


//    public AddressBookProtos.Person getPerson() {
//        return AddressBookProtos.Person.newBuilder()
//                .setId(1)
//                .setName("Bimaljeet")
//                .setEmail("bimaljeet@hotmail.com")
//                .addPhone(AddressBookProtos.Person.PhoneNumber.newBuilder()
//                        .setNumber("415-555-1212")
//                        .setType(AddressBookProtos.Person.PhoneType.MOBILE)
//                        .build())
//                .build();
//    }

    private AddressBookProtos.Contact createContact() {

        AddressBookProtos.Contact.PhoneNumber phoneNumber = null;
        AddressBookProtos.Contact.EmailId emailId = null;
        AddressBookProtos.Contact.Address address = null;
        AddressBookProtos.Contact.Builder contactBuilder = AddressBookProtos.Contact.newBuilder();

        contactBuilder.setId(1);
        contactBuilder.setFormattedName("Bimaljeet Krishnan Nayar");
        contactBuilder.setLastName("Krishnan Nayar");
        contactBuilder.setFirstName("Bimaljeet");

        phoneNumber = createPhone("4045430788", AddressBookProtos.Contact.PhoneType.P_MOBILE);
        contactBuilder.addPhone(phoneNumber);
        phoneNumber = createPhone("4049835610", AddressBookProtos.Contact.PhoneType.P_HOME);
        contactBuilder.addPhone(phoneNumber);
        phoneNumber = createPhone("4044993499", AddressBookProtos.Contact.PhoneType.P_WORK);
        contactBuilder.addPhone(phoneNumber);
        phoneNumber = createPhone("4122232607", AddressBookProtos.Contact.PhoneType.P_FAX);
        contactBuilder.addPhone(phoneNumber);

        emailId = createEmail("bimal@ontinc.com", AddressBookProtos.Contact.EmailType.E_INTERNET);
        contactBuilder.addEmail(emailId);
        emailId = createEmail("bimaljeet@hotmail.com", AddressBookProtos.Contact.EmailType.E_HOME);
        contactBuilder.addEmail(emailId);

        address = createAddress(null, "724 Ashford Pkwy", null, "Dunwoody", null, "GA", "30338");
        if ( null == address ) contactBuilder.addAddress(address);

        return contactBuilder.build();
    }


    private AddressBookProtos.Contact.PhoneNumber createPhone(String phoneNumber, AddressBookProtos.Contact.PhoneType type) {
        AddressBookProtos.Contact.PhoneNumber.Builder contactPhoneBuilder = AddressBookProtos.Contact.PhoneNumber.newBuilder();

        contactPhoneBuilder.setNumber(phoneNumber);
        contactPhoneBuilder.setType(type);

        return contactPhoneBuilder.build();
    }

    private AddressBookProtos.Contact.EmailId createEmail(String emailAddress, AddressBookProtos.Contact.EmailType type) {
        AddressBookProtos.Contact.EmailId.Builder contactEmailIdBuilder = AddressBookProtos.Contact.EmailId.newBuilder();

        contactEmailIdBuilder.setEmailAddress(emailAddress);
        contactEmailIdBuilder.setType(type);

        return contactEmailIdBuilder.build();
    }

    private AddressBookProtos.Contact.Address createAddress(String pobox, String addressLine1, String addressLine2, String city, String country, String state, String zip) {
        AddressBookProtos.Contact.Address.Builder contactAddressBuilder = AddressBookProtos.Contact.Address.newBuilder();
        boolean buildIt = false;

        if (StringUtils.isNotBlank(pobox)) {
            contactAddressBuilder.setPobox(pobox);
            buildIt = true;
        }
        if (StringUtils.isNotBlank(addressLine1)) {
            contactAddressBuilder.setAddressLine1(addressLine1);
            buildIt = true;
        }
        if (StringUtils.isNotBlank(addressLine2)) {
            contactAddressBuilder.setAddressLine2(addressLine2);
            buildIt = true;
        }
        if (StringUtils.isNotBlank(city)) {
            contactAddressBuilder.setCity(city);
            buildIt = true;
        }
        if (StringUtils.isNotBlank(country)) {
            contactAddressBuilder.setCountry(country);
            buildIt = true;
        }
        if (StringUtils.isNotBlank(state)) {
            contactAddressBuilder.setState(state);
            buildIt = true;
        }
        if (StringUtils.isNotBlank(zip)) {
            contactAddressBuilder.setZip(zip);
            buildIt = true;
        }

        if (!buildIt)
            return null;
        else
            return contactAddressBuilder.build();
    }
}
