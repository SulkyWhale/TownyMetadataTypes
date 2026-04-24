package io.github.sulkywhale.townymetadatatypes.deserializers;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.metadata.DataFieldDeserializer;
import io.github.sulkywhale.townymetadatatypes.datatypes.ResidentIntegerMapDataField;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ResidentIntegerMapDFDeserializer implements DataFieldDeserializer<ResidentIntegerMapDataField> {

    @Override
    @ApiStatus.Internal
    public @Nullable ResidentIntegerMapDataField deserialize(@NotNull String key, @Nullable String value) {
        Map<Resident, Integer> residentIntegerMap;
        if (value == null) {
            residentIntegerMap = new HashMap<>();
        } else {
            residentIntegerMap = Arrays.stream(value.split(","))
                    .map(entry -> entry.split("="))
                    .collect(Collectors.toMap(
                            entry -> TownyAPI.getInstance().getResident(entry[0]),
                            entry -> Integer.parseInt(entry[1])
                    ));
        }

        return new ResidentIntegerMapDataField(key, residentIntegerMap);
    }
}
