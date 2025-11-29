package io.github.sulkywhale.townymetadatatypes.datatypes;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.metadata.CustomDataField;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ResidentIntegerMapDataField extends CustomDataField<Map<Resident, Integer>> {

    public ResidentIntegerMapDataField(String key, Map<Resident, Integer> value, String label) {
        super(key, value, label);
    }

    public ResidentIntegerMapDataField(String key, Map<Resident, Integer> value) {
        super(key, value);
    }

    @NotNull
    public static String typeID() {
        return "townymetadatatypes_residentintegermapdf";
    }

    @Override
    public @NotNull String getTypeID() {
        return typeID();
    }

    @Override
    public void setValueFromString(String strValue) {

        final String[] pairStrSplit = strValue.split(",");

        final Map<Resident, Integer> residentIntegerMap = Arrays.stream(pairStrSplit)
                .map(entry -> entry.split(":"))
                .collect(Collectors.toMap(
                        entry -> TownyAPI.getInstance().getResident(entry[0]),
                        value -> Integer.parseInt(value[1])
                ));

        this.setValue(residentIntegerMap);
    }

    public String displayFormattedValue() {
        final Map<Resident, Integer> residentIntegerMap = this.getValue();
        if (residentIntegerMap == null || residentIntegerMap.isEmpty())
            return "<Empty:Empty>";

        return residentIntegerMap.entrySet().stream()
                .map(entry -> entry.getKey().getName() + ": " + entry.getValue())
                .collect(Collectors.joining(", "));
    }

    @Override
    protected @Nullable String serializeValueToString() {
        Map<Resident, Integer> residentIntegerMap = this.getValue();

        if (residentIntegerMap == null || residentIntegerMap.isEmpty())
            return null;

        return residentIntegerMap.entrySet().stream()
                .map(Objects::toString)
                .collect(Collectors.joining(","));
    }

    @Override
    public @NotNull CustomDataField<Map<Resident, Integer>> clone() {

        final Map<Resident, Integer> residentIntegerMap = this.getValue();
        Map<Resident, Integer> copyList = null;

        if (residentIntegerMap != null)
            copyList = new HashMap<>(residentIntegerMap);

        final String copyLabel = hasLabel() ? getLabel() : null;

        return new ResidentIntegerMapDataField(this.getKey(), copyList, copyLabel);
    }
}
