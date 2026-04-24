package io.github.sulkywhale.townymetadatatypes.deserializers;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.metadata.DataFieldDeserializer;
import io.github.sulkywhale.townymetadatatypes.datatypes.ResidentListDataField;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ResidentListDFDeserializer implements DataFieldDeserializer<ResidentListDataField> {

    @Override
    public @Nullable ResidentListDataField deserialize(@NotNull String key, @Nullable String value) {
        List<Resident> residentList;
        if (value == null) {
            residentList = new ArrayList<>();
        } else {
            residentList = Arrays.stream(value.split(","))
                    .map(resName -> TownyAPI.getInstance().getResident(resName))
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        return new ResidentListDataField(key, residentList);
    }
}
