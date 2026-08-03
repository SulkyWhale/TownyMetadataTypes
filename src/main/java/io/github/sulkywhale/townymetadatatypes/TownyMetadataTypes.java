package io.github.sulkywhale.townymetadatatypes;

import com.palmergames.bukkit.towny.object.metadata.DataFieldDeserializer;
import com.palmergames.bukkit.towny.object.metadata.MetadataLoader;
import io.github.sulkywhale.townymetadatatypes.datatypes.MoneyDataField;
import io.github.sulkywhale.townymetadatatypes.datatypes.ResidentBooleanMapDataField;
import io.github.sulkywhale.townymetadatatypes.datatypes.ResidentDataField;
import io.github.sulkywhale.townymetadatatypes.datatypes.ResidentDecimalMapDataField;
import io.github.sulkywhale.townymetadatatypes.datatypes.ResidentIntegerMapDataField;
import io.github.sulkywhale.townymetadatatypes.datatypes.ResidentListDataField;
import io.github.sulkywhale.townymetadatatypes.datatypes.StringListDataField;
import io.github.sulkywhale.townymetadatatypes.datatypes.TownBlockListDataField;
import io.github.sulkywhale.townymetadatatypes.deserializers.MoneyDFDeserializer;
import io.github.sulkywhale.townymetadatatypes.deserializers.ResidentBooleanMapDFDeserializer;
import io.github.sulkywhale.townymetadatatypes.deserializers.ResidentDFDeserializer;
import io.github.sulkywhale.townymetadatatypes.deserializers.ResidentDecimalMapDFDeserializer;
import io.github.sulkywhale.townymetadatatypes.deserializers.ResidentIntegerMapDFDeserializer;
import io.github.sulkywhale.townymetadatatypes.deserializers.ResidentListDFDeserializer;
import io.github.sulkywhale.townymetadatatypes.deserializers.StringListDFDeserializer;
import io.github.sulkywhale.townymetadatatypes.deserializers.TownBlockListDFDeserializer;

import java.util.HashMap;
import java.util.Map;

public class TownyMetadataTypes {

    private static final TownyMetadataTypes instance = new TownyMetadataTypes();
    private final Map<String, DataFieldDeserializer<?>> deserializerMap = new HashMap<>();

    public TownyMetadataTypes() {
        // Populate hashmap with all data types and corresponding deserializers
        deserializerMap.put(MoneyDataField.typeID(), new MoneyDFDeserializer());
        deserializerMap.put(ResidentBooleanMapDataField.typeID(), new ResidentBooleanMapDFDeserializer());
        deserializerMap.put(ResidentDataField.typeID(), new ResidentDFDeserializer());
        deserializerMap.put(ResidentIntegerMapDataField.typeID(), new ResidentIntegerMapDFDeserializer());
        deserializerMap.put(ResidentDecimalMapDataField.typeID(), new ResidentDecimalMapDFDeserializer());
        deserializerMap.put(ResidentListDataField.typeID(), new ResidentListDFDeserializer());
        deserializerMap.put(StringListDataField.typeID(), new StringListDFDeserializer());
        deserializerMap.put(TownBlockListDataField.typeID(), new TownBlockListDFDeserializer());
    }

    public static TownyMetadataTypes getInstance() {
        return instance;
    }

    public boolean registerDeserializer(String customDataFieldTypeID) {
        if (!deserializerMap.containsKey(customDataFieldTypeID))
            return false;

        DataFieldDeserializer<?> deserializer = deserializerMap.get(customDataFieldTypeID);

        return MetadataLoader.getInstance().registerDeserializer(customDataFieldTypeID, deserializer);
    }
}
