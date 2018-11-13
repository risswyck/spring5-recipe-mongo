package codegeeks.spring5recipe.converters;

import codegeeks.spring5recipe.commands.UnitOfMeasureCommand;
import codegeeks.spring5recipe.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

    @Synchronized
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand source) {
        if (source == null) {
            return null;
        }
        final UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(source.getId());
        unitOfMeasure.setDescription(source.getDescription());
        return unitOfMeasure;
    }
}
