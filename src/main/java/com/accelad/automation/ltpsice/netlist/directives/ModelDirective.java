package com.accelad.automation.ltpsice.netlist.directives;

import com.accelad.automation.ltpsice.netlist.Value;

public class ModelDirective implements Directive {

    private final ModelName modelName;
    private final ModelType modelType;
    private final Value value;

    public ModelDirective(ModelName modelName, ModelType modelType, Value value) {
        this.modelName = modelName;
        this.modelType = modelType;
        this.value = value;
    }

    @Override
    public void accept(DirectiveVisitor visitor) {
        visitor.visit(this);
    }

    public ModelName getModelName() {
        return modelName;
    }

    public ModelType getModelType() {
        return modelType;
    }

    public Value getValue() {
        return value;
    }
}
