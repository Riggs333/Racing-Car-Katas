package tddmicroexercises.tirepressuremonitoringsystem;

import java.util.Objects;

public class Alarm
{
    static final double LowPressureTreshold = 17;
    static final double HighPressureTreshold = 21;

    private final Sensor sensor;

    private boolean alarmOn = false;

    /**
     * @deprecated use {@link #Alarm(Sensor)}
     */
    @Deprecated
    public Alarm()
    {
        this(new Sensor());
    }

    protected Alarm(Sensor sensor)
    {
        this.sensor = Objects.requireNonNull(sensor);
    }

    public void check()
    {
        double psiPressureValue = sensor.popNextPressurePsiValue();

        if (isNotInValidRange(psiPressureValue))
        {
            alarmOn = true;
        }
    }

    private static boolean isNotInValidRange(double psiPressureValue) {
        return psiPressureValue < LowPressureTreshold || HighPressureTreshold < psiPressureValue;
    }

    public boolean isAlarmOn()
    {
        return alarmOn; 
    }
}
