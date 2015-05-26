package com.jersey.beans;

import com.google.protobuf.Message;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: bnayar
 * Date: 11/24/13
 * Time: 7:40 AM
 * To change this template use File | Settings | File Templates.
 */

@Provider
@Produces("application/x-protobuf")
public class ProtobufMessageBodyWriter implements MessageBodyWriter<Message> {

    private Map<Object, byte[]> buffer = new WeakHashMap<Object, byte[]>();

    @Override
    public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return Message.class.isAssignableFrom(aClass);
    }

    @Override
    public long getSize(Message message, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            message.writeTo(baos);
        } catch (IOException e) {
            return -1;
        }
        byte[] bytes = baos.toByteArray();
        buffer.put(message, bytes);
        System.out.print("......... length = "+bytes.length);
        return bytes.length;
    }

    @Override
    public void writeTo(Message message, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> stringObjectMultivaluedMap, OutputStream outputStream) throws IOException, WebApplicationException {
        outputStream.write(buffer.remove(message));
    }
}
