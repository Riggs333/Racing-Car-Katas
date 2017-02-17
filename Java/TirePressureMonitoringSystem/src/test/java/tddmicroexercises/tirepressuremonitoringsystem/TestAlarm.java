package tddmicroexercises.tirepressuremonitoringsystem;


import org.junit.*;
import static org.junit.Assert.*;

public class TestAlarm {

    public static final int VALUE_LOWER_LIMIT = 0;

    @Test(expected = NullPointerException.class)
    public void sensorMustNotBeNull() {
        Sensor NULL_SENSOR = null;
        new Alarm(NULL_SENSOR);
    }

    @Test
    public void expectTheAlarmIsInitiallyOff() {
        Alarm alarm = new Alarm();
        assertEquals(false, alarm.isAlarmOn());
    }

    @Test
    public void givenTheSensorProvidesLowerThanThreshold_Then_Expect_Alarm_To_Be_On() {
        assertAlarmForPressureValue(VALUE_LOWER_LIMIT, true);
    }

    @Test
    public void givenTheSensorProvidesHigherThanThreshold_Then_Expect_Alarm_To_Be_On() {
        assertAlarmForPressureValue(22, true);
    }

    @Test
    public void givenTheSensorProvidesEqualToLowerThreshold_Then_Expect_Alarm_To_Be_Off() {
        assertAlarmForPressureValue(17, false);
    }

    @Test
    public void givenTheSensorProvidesEqualToHigherThreshold_Then_Expect_Alarm_To_Be_Off() {
        assertAlarmForPressureValue(21, false);
    }

    @Test
    public void givenTheSensorProvidesInsideThreshold_Then_Expect_Alarm_To_Be_Off() {
        assertAlarmForPressureValue(19, false);
    }

    @Test
    public void givenAlarmOnThresholdShouldNotBeReset() {
        Sensor sensor = new StubSensor(5, 19);
        Alarm alarm = new Alarm(sensor);
        alarm.check();
        alarm.check();
        assertEquals(true, alarm.isAlarmOn());
    }

    @Test
    public void verifyInitialThresholds(){
        assertEquals(17, Alarm.LowPressureTreshold, 0);
        assertEquals(21, Alarm.HighPressureTreshold, 0);
    }

    private void assertAlarmForPressureValue(int value, boolean expected) {
        Sensor sensor = new StubSensor(value);
        Alarm alarm = new Alarm(sensor);
        alarm.check();
        assertEquals(expected, alarm.isAlarmOn());
    }
}
