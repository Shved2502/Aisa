package com.example.aisa.unit;

import com.example.aisa.dao.WorkRepository;
import com.example.aisa.service.WorkService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@SpringBootTest
class WorkServiceTest {

    @Autowired
    private WorkService workService;

    @MockBean
    private WorkRepository workRepository;

    @Test
    void makeCoffee_nullName_ExceptionThrows() {
        assertThrows(ConstraintViolationException.class,
                () -> workService.makeCoffee(null));
    }

    @Test
    void makeCoffee_notOnlyCharactersName_ExceptionThrows() {
        assertThrows(ConstraintViolationException.class,
                () -> workService.makeCoffee("1Latte"));
    }

    @Test
    void makeCoffee_correctName_NothingWasThrown() {

        workService.makeCoffee("Latte");

        verify(workRepository, atLeast(1)).saveAndFlush(any());
    }

    @Test
    void updateContainer_nullContainer_ExceptionThrows() {
        assertThrows(ConstraintViolationException.class,
                () -> workService.updateContainer(null));
    }

    @Test
    void updateContainer_notOnlyCharactersContainer_ExceptionThrows() {
        assertThrows(ConstraintViolationException.class,
                () -> workService.updateContainer("123Water"));
    }

    @Test
    void updateContainer_correctContainer_NothingWasThrown() {

        workService.updateContainer("Water");

        verify(workRepository).saveAndFlush(any());
    }
}
