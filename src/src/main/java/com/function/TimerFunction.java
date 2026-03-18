package com.function;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.TimerTrigger;
import java.time.LocalDateTime;

/**
 * Timer-triggered Azure Function that demonstrates scheduled execution.
 */
public class TimerFunction {

    /**
     * Timer-triggered function that executes on a schedule defined by TIMER_SCHEDULE app setting.
     *
     * <p>The schedule parameter uses the %TIMER_SCHEDULE% syntax to read the NCRONTAB expression
     * from the TIMER_SCHEDULE application setting, making it configurable without code changes.</p>
     *
     * <p>Note: Unlike the .NET version, the Java Azure Functions library does not support
     * the RunOnStartup parameter on the TimerTrigger annotation. The function will only
     * execute on the defined cron schedule.</p>
     *
     * @param timerInfo JSON string containing timer schedule status information
     * @param context   Function execution context providing logging capabilities
     */
    @FunctionName("timerFunction")
    public void run(
            @TimerTrigger(name = "timerInfo", schedule = "%TIMER_SCHEDULE%") String timerInfo,
            final ExecutionContext context) {

        context.getLogger().info("Java Timer trigger function executed at: " + LocalDateTime.now());

        if (timerInfo != null && timerInfo.contains("\"isPastDue\":true")) {
            context.getLogger().warning("The timer is running late!");
        }
    }
}
