package io.github.sulkywhale.townymetadatatypes;

import com.palmergames.bukkit.towny.object.metadata.CustomDataField;
import com.palmergames.bukkit.towny.object.metadata.DataFieldDeserializer;
import com.palmergames.bukkit.towny.object.metadata.MetadataLoader;

import java.lang.reflect.Method;

@SuppressWarnings("unused")
public final class TownyMetadataTypes {

    /**
     * Register a custom metadata field so Towny can deserialize it.
     *
     * @param customDataFieldClass The class of the custom data type to be registered.
     * @param <T>                  The class that extends {@link CustomDataField}.
     * @return whether the deserializer was registered.
     */
    public static <T extends CustomDataField<?>> boolean registerDataField(Class<T> customDataFieldClass) {
        DeserializableCustomDataField annotation = customDataFieldClass.getAnnotation(DeserializableCustomDataField.class);
        Class<? extends DataFieldDeserializer<?>> dataFieldDeserializerClass = annotation.value();
        try {
            DataFieldDeserializer<?> dataFieldDeserializer = dataFieldDeserializerClass.getDeclaredConstructor().newInstance();
            Method typeIDMethod = customDataFieldClass.getMethod("typeID");
            String typeID = (String) typeIDMethod.invoke(null);
            return MetadataLoader.getInstance().registerDeserializer(typeID, dataFieldDeserializer);
        } catch (ReflectiveOperationException e) {
            throw new CustomDataFieldRegistrationException("An error occurred while registering custom metadata type: " + customDataFieldClass.getSimpleName(), e);
        }
    }
}
