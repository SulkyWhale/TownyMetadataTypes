package io.github.sulkywhale.townymetadatatypes.datatypes;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.TownBlock;
import com.palmergames.bukkit.towny.object.WorldCoord;
import com.palmergames.bukkit.towny.object.metadata.CustomDataField;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TownBlockListDataField extends CustomDataField<List<TownBlock>> {

    public TownBlockListDataField(String key, List<TownBlock> value, String label) {
        super(key, value, label);
    }

    public TownBlockListDataField(String key, List<TownBlock> value) {
        super(key, value);
    }

    @NotNull
    public static String typeID() {
        return "townymetadatatypes_townblocklistdf";
    }

    @Override
    public @NotNull String getTypeID() {
        return typeID();
    }

    @Override
    public void setValueFromString(String strValue) {
        final String[] townBlockStrSplit = strValue.split(",");
        final List<TownBlock> townBlockList = Arrays.stream(townBlockStrSplit)
                .map(entry -> entry.split("_"))
                .map(townBlockWC -> TownyAPI.getInstance().getTownBlock(
                        new WorldCoord(townBlockWC[0], Integer.parseInt(townBlockWC[1]), Integer.parseInt(townBlockWC[2]))
                ))
                .toList();

        this.setValue(townBlockList);
    }

    @Override
    public String displayFormattedValue() {
        final List<TownBlock> townBlockList = this.getValue();
        if (townBlockList == null || townBlockList.isEmpty())
            return "<Empty>";

        return townBlockList.stream()
                .map(townBlock -> "[" + townBlock.getWorldCoord().toString() + "]")
                .collect(Collectors.joining(", "));
    }

    @Override
    protected @Nullable String serializeValueToString() {
        List<TownBlock> townBlockList = this.getValue();
        if (townBlockList == null || townBlockList.isEmpty())
            return null;

        return townBlockList.stream()
                .map(townBlock -> {
                    WorldCoord worldCoord = townBlock.getWorldCoord();
                    return worldCoord.getWorldName() + "_" + worldCoord.getX() + "_" + worldCoord.getZ();
                })
                .collect(Collectors.joining(","));
    }

    @Override
    public @NotNull CustomDataField<List<TownBlock>> clone() {
        final List<TownBlock> townBlockList = this.getValue();
        List<TownBlock> copyList = null;
        if (townBlockList != null)
            copyList = new ArrayList<>(townBlockList);

        final String copyLabel = hasLabel() ? getLabel() : null;
        return new TownBlockListDataField(this.getKey(), copyList, copyLabel);
    }
}
