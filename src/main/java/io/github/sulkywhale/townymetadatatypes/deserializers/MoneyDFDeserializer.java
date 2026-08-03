package io.github.sulkywhale.townymetadatatypes.deserializers;

import com.palmergames.bukkit.towny.object.metadata.DataFieldDeserializer;
import io.github.sulkywhale.townymetadatatypes.datatypes.MoneyDataField;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MoneyDFDeserializer implements DataFieldDeserializer<MoneyDataField> {

    @Override
    @ApiStatus.Internal
    public @Nullable MoneyDataField deserialize(@NotNull String key, @Nullable String value) {
        Double moneyValue;
        if (value == null) {
            moneyValue = null;
        } else {
            moneyValue = Double.parseDouble(value);
        }

        return new MoneyDataField(key, moneyValue);
    }
}
