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

public class ResidentBooleanMapDataField extends CustomDataField<Map<Resident, Boolean>> {

    public ResidentBooleanMapDataField(String key, Map<Resident, Boolean> value, String label) {
        super(key, value, label);
    }

    public ResidentBooleanMapDataField(String key, Map<Resident, Boolean> value) {
        super(key, value);
    }

    @NotNull
    public static String typeID() {
        return "townymetadatatypes_residentbooleanmapdf";
    }

    @Override
    public @NotNull String getTypeID() {
        return typeID();
    }

    @Override
    public void setValueFromString(String strValue) {

        final String[] pairStrSplit = strValue.split(",");

        final Map<Resident, Boolean> residentBooleanMapMap = Arrays.stream(pairStrSplit)
                .map(entry -> entry.split(":"))
                .collect(Collectors.toMap(
                        entry -> TownyAPI.getInstance().getResident(entry[0]),
                        value -> Boolean.parseBoolean(value[1])
                ));

        this.setValue(residentBooleanMapMap);
    }

    @Override
    public String displayFormattedValue() {
        final Map<Resident, Boolean> residentBooleanMap = this.getValue();
        if (residentBooleanMap == null || residentBooleanMap.isEmpty())
            return "<Empty:Empty>";

        return residentBooleanMap.entrySet().stream()
                .map(entry -> entry.getKey().getName() + ": " + entry.getValue())
                .collect(Collectors.joining(", "));
    }

    @Override
    protected @Nullable String serializeValueToString() {
        Map<Resident, Boolean> residentBooleanMap = this.getValue();

        if (residentBooleanMap == null || residentBooleanMap.isEmpty())
            return null;

        return residentBooleanMap.entrySet().stream()
                .map(Objects::toString)
                .collect(Collectors.joining(","));
    }

    @Override
    public @NotNull CustomDataField<Map<Resident, Boolean>> clone() {

        final Map<Resident, Boolean> residentBooleanMap = this.getValue();
        Map<Resident, Boolean> copyList = null;

        if (residentBooleanMap != null)
            copyList = new HashMap<>(residentBooleanMap);

        final String copyLabel = hasLabel() ? getLabel() : null;

        return new ResidentBooleanMapDataField(this.getKey(), copyList, copyLabel);
    }
}
