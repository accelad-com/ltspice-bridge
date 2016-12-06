package com.accelad.automation.ltpsice.output;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LTSpiceRaw {
    private final String title;
    private final Date date;
    private final String plotName;
    private final String flags;
    private final int variableCount;
    private final int pointCount;
    private final double offset;
    private final String command;
    private final List<Variable> variables = new ArrayList<>();
    private final List<Point> points = new ArrayList<>();

    private LTSpiceRaw(String title, Date date, String plotName, String flags, int variableCount,
            int pointCount, double offset, String command) {
        this.title = title;
        this.date = date;
        this.plotName = plotName;
        this.flags = flags;
        this.variableCount = variableCount;
        this.pointCount = pointCount;
        this.offset = offset;
        this.command = command;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public String getPlotName() {
        return plotName;
    }

    public String getFlags() {
        return flags;
    }

    public int getVariableCount() {
        return variableCount;
    }

    public int getPointCount() {
        return pointCount;
    }

    public double getOffset() {
        return offset;
    }

    public String getCommand() {
        return command;
    }

    public List<Variable> getVariables() {
        return variables;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {


        private String title;
        private Date date;
        private String plotName;
        private String flags;
        private int variableCount;
        private int pointCount;
        private double offset;
        private String command;
        private final List<Variable> variables = new ArrayList<>();
        private final List<Point> points = new ArrayList<>();

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withDate(Date date) {
            this.date = date;
            return this;
        }

        public Builder withPlotName(String plotName) {
            this.plotName = plotName;
            return this;
        }

        public Builder withFlags(String flags) {
            this.flags = flags;
            return this;
        }

        public Builder withVariableCount(int variableCount) {
            this.variableCount = variableCount;
            return this;
        }

        public Builder withPointCount(int pointCount) {
            this.pointCount = pointCount;
            return this;
        }

        public Builder withOffset(double offset) {
            this.offset = offset;
            return this;
        }

        public Builder withCommand(String command) {
            this.command = command;
            return this;
        }

        public Builder withVariable(Variable variable) {
            this.variables.add(variable);
            return this;
        }

        public Builder withPoint(Point point) {
            this.points.add(point);
            return this;
        }

        public LTSpiceRaw build() {
            LTSpiceRaw raw = new LTSpiceRaw(title, date, plotName,
                    flags, variableCount, pointCount, offset,
                    command);
            raw.variables.addAll(variables);
            raw.points.addAll(points);
            return raw;
        }

    }


}
