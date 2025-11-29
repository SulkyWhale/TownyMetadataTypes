package io.github.sulkywhale.townymetadatatypes.datatypes;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.TownyObject;
import com.palmergames.bukkit.towny.object.metadata.CustomDataField;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ResidentListDataField extends CustomDataField<List<Resident>> {

    public ResidentListDataField(String key, List<Resident> value, String label) {
        super(key, value, label);
    }

    public ResidentListDataField(String key, List<Resident> value) {
        super(key, value);
    }

    @NotNull
    public static String typeID() {
        return "townymetadatatypes_residentlistdf";
    }

    @Override
    public @NotNull String getTypeID() {
        return typeID();
    }

    @Override
    public void setValueFromString(String strValue) {

        final String[] residentStrSplit = strValue.split(",");

        final List<Resident> residentList = Arrays.stream(residentStrSplit)
                .map(resName -> TownyAPI.getInstance().getResident(resName))
                .collect(Collectors.toList());

        this.setValue(residentList);
    }

    public String displayFormattedValue() {
        final List<Resident> residentListList = this.getValue();
        if (residentListList == null || residentListList.isEmpty())
            return "<Empty>";

        return residentListList.stream()
                .map(TownyObject::getName)
                .collect(Collectors.joining(", "));
    }

    @Override
    protected @Nullable String serializeValueToString() {
        List<Resident> residentList = this.getValue();

        if (residentList == null || residentList.isEmpty())
            return null;

        return residentList.stream()
                .map(TownyObject::getName)
                .collect(Collectors.joining(","));
    }

    @Override
    public @NotNull CustomDataField<List<Resident>> clone() {

        final List<Resident> residentList = this.getValue();
        List<Resident> copyList = null;

        if (residentList != null)
            copyList = new ArrayList<>(residentList);

        final String copyLabel = hasLabel() ? getLabel() : null;

        return new ResidentListDataField(this.getKey(), copyList, copyLabel);
    }
}
