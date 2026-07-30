package io.github.sulkywhale.townymetadatatypes.deserializers;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.TownBlock;
import com.palmergames.bukkit.towny.object.WorldCoord;
import com.palmergames.bukkit.towny.object.metadata.DataFieldDeserializer;
import io.github.sulkywhale.townymetadatatypes.datatypes.TownBlockListDataField;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TownBlockListDFDeserializer implements DataFieldDeserializer<TownBlockListDataField> {

    @Override
    @ApiStatus.Internal
    public @Nullable TownBlockListDataField deserialize(@NotNull String key, @Nullable String value) {
        List<TownBlock> townBlockList;
        if (value == null) {
            townBlockList = new ArrayList<>();
        } else {
            townBlockList = Arrays.stream(value.split(","))
                    .map(entry -> entry.split("_"))
                    .map(townBlockWC -> TownyAPI.getInstance().getTownBlock(
                            new WorldCoord(townBlockWC[0], Integer.parseInt(townBlockWC[1]), Integer.parseInt(townBlockWC[2]))
                    ))
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        return new TownBlockListDataField(key, townBlockList);
    }
}
