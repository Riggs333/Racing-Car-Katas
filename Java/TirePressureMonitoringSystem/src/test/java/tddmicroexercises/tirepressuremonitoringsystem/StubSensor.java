package tddmicroexercises.tirepressuremonitoringsystem;

public class StubSensor extends Sensor {
    private int[] value;
    private int lastIndex = 0;

    public StubSensor(int ... value) {
        super();
        this.value = value;
    }

    @Override
    public double popNextPressurePsiValue() {
        return value[lastIndex++];
    }
}
