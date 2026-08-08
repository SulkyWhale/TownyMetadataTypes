package io.github.sulkywhale.townymetadatatypes.datatypes;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.metadata.CustomDataField;
import io.github.sulkywhale.townymetadatatypes.DeserializableCustomDataField;
import io.github.sulkywhale.townymetadatatypes.deserializers.ResidentDFDeserializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@DeserializableCustomDataField(ResidentDFDeserializer.class)
public class ResidentDataField extends CustomDataField<Resident> {

    public ResidentDataField(String key, Resident value, String label) {
        super(key, value, label);
    }

    public ResidentDataField(String key, Resident value) {
        super(key, value);
    }

    @NotNull
    public static String typeID() {
        return "townymetadatatypes_residentdf";
    }

    @Override
    public @NotNull String getTypeID() {
        return typeID();
    }

    @Override
    public void setValueFromString(String strValue) {
        this.setValue(TownyAPI.getInstance().getResident(strValue));
    }

    @Override
    public String displayFormattedValue() {
        final Resident resident = this.getValue();
        if (resident == null)
            return "<Empty>";

        return resident.getName();
    }

    @Override
    protected @Nullable String serializeValueToString() {
        Resident resident = this.getValue();
        if (resident == null)
            return null;

        return resident.getUUID().toString();
    }

    @Override
    public @NotNull CustomDataField<Resident> clone() {
        final Resident resident = this.getValue();
        Resident copy = null;
        if (resident != null)
            copy = resident;

        final String copyLabel = hasLabel() ? getLabel() : null;
        return new ResidentDataField(this.getKey(), copy, copyLabel);
    }
}
