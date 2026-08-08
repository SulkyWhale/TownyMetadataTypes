package io.github.sulkywhale.townymetadatatypes;

import com.palmergames.bukkit.towny.object.metadata.DataFieldDeserializer;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates the deserializer to be used by the {@link com.palmergames.bukkit.towny.object.metadata.CustomDataField} class.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DeserializableCustomDataField {

    /**
     * Returns the custom data field's deserializer.
     *
     * @return the custom data field's deserializer.
     */
    Class<? extends DataFieldDeserializer<?>> value();
}
