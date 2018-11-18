package codegeeks.spring5recipe.services;

import codegeeks.spring5recipe.commands.UnitOfMeasureCommand;
import codegeeks.spring5recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import codegeeks.spring5recipe.domain.UnitOfMeasure;
import codegeeks.spring5recipe.repositories.reactive.UnitOfMeasureReactiveRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UnitOfMeasureServiceImplTest {
    private UnitOfMeasureService unitOfMeasureService;

    @Mock
    private UnitOfMeasureReactiveRepository unitOfReactiveMeasureRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        unitOfMeasureService = new UnitOfMeasureServiceImpl(
                unitOfReactiveMeasureRepository,
                new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void listAllUnitOfMeasures() {
        // given
        Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();

        UnitOfMeasure unitOfMeasure1 = new UnitOfMeasure();
        unitOfMeasure1.setId("1");
        unitOfMeasures.add(unitOfMeasure1);

        UnitOfMeasure unitOfMeasure2 = new UnitOfMeasure();
        unitOfMeasure2.setId("2");
        unitOfMeasures.add(unitOfMeasure2);

        when(unitOfReactiveMeasureRepository.findAll()).thenReturn(Flux.fromIterable(unitOfMeasures));

        // when
        List<UnitOfMeasureCommand> commands = unitOfMeasureService.listAllUnitOfMeasures().collectList().block();

        // then
        assertEquals(2, commands.size());
        verify(unitOfReactiveMeasureRepository, times(1)).findAll();
    }
}