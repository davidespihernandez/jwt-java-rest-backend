package com.despi.jwtrestserver.domain.jsonb;

import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.usertype.DynamicParameterizedType;

import java.util.Properties;

/**
 * taken from:
 * https://vladmihalcea.com/2016/06/20/how-to-map-json-objects-using-generic-hibernate-types/
 */
public class JsonBinaryType
        extends AbstractSingleColumnStandardBasicType<Object>
        implements DynamicParameterizedType {

    public JsonBinaryType() {
        super(
                JsonBinarySqlTypeDescriptor.INSTANCE,
                new JsonTypeDescriptor()
        );
    }

    public String getName() {
        return "jsonb";
    }

    @Override
    public void setParameterValues(Properties parameters) {
        ((JsonTypeDescriptor) getJavaTypeDescriptor())
                .setParameterValues(parameters);
    }

}
