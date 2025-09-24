package com.souvik.model.gate;

public abstract class Gate {
    private final int gateId;

    public Gate(int gateId) {
        this.gateId = gateId;
    }

    public int getGateId() {
        return gateId;
    }
}
