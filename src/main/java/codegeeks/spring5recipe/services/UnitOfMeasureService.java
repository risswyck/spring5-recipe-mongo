package codegeeks.spring5recipe.services;

import codegeeks.spring5recipe.commands.UnitOfMeasureCommand;
import reactor.core.publisher.Flux;

import java.util.Set;

public interface UnitOfMeasureService {
    Flux<UnitOfMeasureCommand> listAllUnitOfMeasures();
}
