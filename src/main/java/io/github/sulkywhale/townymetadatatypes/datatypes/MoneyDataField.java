package io.github.sulkywhale.townymetadatatypes.datatypes;

import com.palmergames.bukkit.towny.TownyEconomyHandler;
import com.palmergames.bukkit.towny.object.metadata.DecimalDataField;
import io.github.sulkywhale.townymetadatatypes.DeserializableCustomDataField;
import io.github.sulkywhale.townymetadatatypes.deserializers.MoneyDFDeserializer;
import org.jetbrains.annotations.NotNull;

@DeserializableCustomDataField(MoneyDFDeserializer.class)
public class MoneyDataField extends DecimalDataField {

    public MoneyDataField(String key, Double value, String label) {
        super(key, value, label);
    }

    public MoneyDataField(String key, Double value) {
        super(key, value);
    }

    @NotNull
    public static String typeID() {
        return "townymetadatatypes_moneydf";
    }

    @Override
    public @NotNull String getTypeID() {
        return typeID();
    }

    @Override
    public String displayFormattedValue() {
        final String value = TownyEconomyHandler.getFormattedBalance(this.getValue());
        return (getValue() <= 0 ? "<red>" : "<green>") + value;
    }

    @Override
    public @NotNull DecimalDataField clone() {
        return new MoneyDataField(this.getKey(), getValue(), getLabel());
    }
}
