package com.function;

import com.microsoft.azure.functions.ExecutionContext;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the TimerFunction class.
 */
class TimerFunctionTest {

    @Test
    void testTimerTriggerExecutes() {
        final ExecutionContext context = mock(ExecutionContext.class);
        final Logger logger = mock(Logger.class);
        when(context.getLogger()).thenReturn(logger);

        final TimerFunction function = new TimerFunction();
        assertDoesNotThrow(() -> function.run("{\"isPastDue\":false}", context));
    }

    @Test
    void testTimerTriggerLogsPastDueWarning() {
        final ExecutionContext context = mock(ExecutionContext.class);
        final Logger logger = mock(Logger.class);
        when(context.getLogger()).thenReturn(logger);

        final TimerFunction function = new TimerFunction();
        function.run("{\"isPastDue\":true}", context);

        verify(logger).warning("The timer is running late!");
    }
}
