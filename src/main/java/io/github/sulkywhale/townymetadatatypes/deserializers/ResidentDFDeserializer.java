package io.github.sulkywhale.townymetadatatypes.deserializers;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.metadata.DataFieldDeserializer;
import io.github.sulkywhale.townymetadatatypes.datatypes.ResidentDataField;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class ResidentDFDeserializer implements DataFieldDeserializer<ResidentDataField> {

    @Override
    @ApiStatus.Internal
    public @Nullable ResidentDataField deserialize(@NotNull String key, @Nullable String value) {
        Resident resident;
        if (value == null) {
            resident = null;
        } else {
            resident = TownyAPI.getInstance().getResident(UUID.fromString(value));
        }

        return new ResidentDataField(key, resident);
    }
}
