package io.github.sulkywhale.townymetadatatypes.datatypes;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.metadata.CustomDataField;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ResidentDecimalMapDataField extends CustomDataField<Map<Resident, Double>> {

    public ResidentDecimalMapDataField(String key, Map<Resident, Double> value, String label) {
        super(key, value, label);
    }

    public ResidentDecimalMapDataField(String key, Map<Resident, Double> value) {
        super(key, value);
    }

    @NotNull
    public static String typeID() {
        return "townymetadatatypes_residentdecimalmapdf";
    }

    @Override
    public @NotNull String getTypeID() {
        return typeID();
    }

    @Override
    public void setValueFromString(String strValue) {
        final String[] pairStrSplit = strValue.split(",");
        final Map<Resident, Double> residentDecimalMap = Arrays.stream(pairStrSplit)
                .map(entry -> entry.split("="))
                .collect(Collectors.toMap(
                        entry -> TownyAPI.getInstance().getResident(entry[0]),
                        entry -> Double.parseDouble(entry[1])
                ));
        this.setValue(residentDecimalMap);
    }

    @Override
    public String displayFormattedValue() {
        final Map<Resident, Double> residentDecimalMap = this.getValue();
        if (residentDecimalMap == null || residentDecimalMap.isEmpty())
            return "<Empty:Empty>";

        return residentDecimalMap.entrySet().stream()
                .map(entry -> entry.getKey().getName() + ": " + entry.getValue())
                .collect(Collectors.joining(", "));
    }

    @Override
    protected @Nullable String serializeValueToString() {
        Map<Resident, Double> residentDecimalMap = this.getValue();
        if (residentDecimalMap == null || residentDecimalMap.isEmpty())
            return null;

        return residentDecimalMap.entrySet().stream()
                .map(entry -> entry.getKey().getUUID() + "=" + entry.getValue())
                .collect(Collectors.joining(","));
    }

    @Override
    public @NotNull CustomDataField<Map<Resident, Double>> clone() {
        final Map<Resident, Double> residentDecimalMap = this.getValue();
        Map<Resident, Double> copyList = null;
        if (residentDecimalMap != null)
            copyList = new HashMap<>(residentDecimalMap);

        final String copyLabel = hasLabel() ? getLabel() : null;
        return new ResidentDecimalMapDataField(this.getKey(), copyList, copyLabel);
    }
}
