package org.example;

public class Tool {
    private final String code;
    private final ToolType type;
    private final String brand;

    public Tool(String code, ToolType type, String brand) {
        this.code = code;
        this.type = type;
        this.brand = brand;
    }

    public String getCode() {
        return code;
    }

    public ToolType getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }
}
