package com.zjh.recording.system.oauth.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.zjh.recording.system.common.vo.Result;

import java.io.IOException;

/**
 * @author zjh
 */
public class CustomOauthExceptionSerializer extends StdSerializer<CustomOauth2Exception> {
    public CustomOauthExceptionSerializer() {
        super(CustomOauth2Exception.class);
    }

    @Override
    public void serialize(CustomOauth2Exception e, JsonGenerator gen, SerializerProvider provider) throws IOException {
        System.out.println("====" + e.getSummary());
        gen.writeObject(Result.fail(e.getHttpErrorCode(), e.getMessage()));
    }
}