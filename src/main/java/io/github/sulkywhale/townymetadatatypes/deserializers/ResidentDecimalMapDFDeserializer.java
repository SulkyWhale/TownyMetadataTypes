package io.github.sulkywhale.townymetadatatypes.deserializers;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.metadata.DataFieldDeserializer;
import io.github.sulkywhale.townymetadatatypes.datatypes.ResidentDecimalMapDataField;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class ResidentDecimalMapDFDeserializer implements DataFieldDeserializer<ResidentDecimalMapDataField> {

    @Override
    @ApiStatus.Internal
    public @Nullable ResidentDecimalMapDataField deserialize(@NotNull String key, @Nullable String value) {
        Map<Resident, Double> residentDecimalMap;
        if (value == null) {
            residentDecimalMap = new HashMap<>();
        } else {
            residentDecimalMap = Arrays.stream(value.split(","))
                    .map(entry -> entry.split("="))
                    .collect(Collectors.toMap(
                            entry -> TownyAPI.getInstance().getResident(UUID.fromString(entry[0])),
                            entry -> Double.parseDouble(entry[1])
                    ));
        }

        return new ResidentDecimalMapDataField(key, residentDecimalMap);
    }
}
