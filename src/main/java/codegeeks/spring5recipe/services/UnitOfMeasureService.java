package codegeeks.spring5recipe.services;

import codegeeks.spring5recipe.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> listAllUnitOfMeasures();
}
