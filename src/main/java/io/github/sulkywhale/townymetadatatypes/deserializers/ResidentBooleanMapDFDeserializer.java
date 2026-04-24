package io.github.sulkywhale.townymetadatatypes.deserializers;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.metadata.DataFieldDeserializer;
import io.github.sulkywhale.townymetadatatypes.datatypes.ResidentBooleanMapDataField;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ResidentBooleanMapDFDeserializer implements DataFieldDeserializer<ResidentBooleanMapDataField> {

    @Override
    @ApiStatus.Internal
    public @Nullable ResidentBooleanMapDataField deserialize(@NotNull String key, @Nullable String value) {

        Map<Resident, Boolean> residentBooleanMap;

        if (value == null) {
            residentBooleanMap = new HashMap<>();
        } else {
            residentBooleanMap = Arrays.stream(value.split(","))
                    .map(entry -> entry.split("="))
                    .collect(Collectors.toMap(
                            entry -> TownyAPI.getInstance().getResident(entry[0]),
                            val -> Boolean.parseBoolean(val[0])
                    ));
        }

        return new ResidentBooleanMapDataField(key, residentBooleanMap);
    }
}
