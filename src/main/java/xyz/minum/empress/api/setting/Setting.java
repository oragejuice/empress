package xyz.minum.empress.api.setting;

public class Setting<T> {

    private String name;
    //private Setting<?> parent;
    //private T requiredValue;
    private T value;

    /* should be used for double or float settings only */
    private T max;
    private T min;

    public Setting(String name, T value){
        this.name = name;
        this.value = value;
    }

    public Setting(String name, T value, T min, T max){
        this.name = name;
        this.value = value;
        this.min = min;
        this.max = max;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getMax() {
        return max;
    }

    public void setMax(T max) {
        this.max = max;
    }

    public T getMin() {
        return min;
    }

    public void setMin(T min) {
        this.min = min;
    }

}
