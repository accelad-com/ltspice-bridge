package com.accelad.automation.ltpsice.output.raw;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LTSpiceRaw {
    private final String title;
    private final Date date;
    private final String plotName;
    private final ListOfFlags flags;
    private final double offset;
    private final String command;
    private final ListOfTraces traces;

    private LTSpiceRaw(String title, Date date, String plotName, ListOfFlags flags,
            double offset, String command, ListOfTraces traces) {
        this.title = title;
        this.date = date;
        this.plotName = plotName;
        this.flags = flags;
        this.offset = offset;
        this.command = command;
        this.traces = traces;
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

    public ListOfFlags getFlags() {
        return flags;
    }

    public double getOffset() {
        return offset;
    }

    public String getCommand() {
        return command;
    }

    public ListOfTraces getTraces() {
        return traces;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String title;
        private Date date;
        private String plotName;
        private ListOfFlags flags;
        private int variableCount;
        private int pointCount;
        private double offset;
        private String command;
        private final List<Signal> signals = new ArrayList<>();
        private final List<List<Double>> values = new ArrayList<>();

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

        public Builder withFlags(ListOfFlags flags) {
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

        public Builder withSignal(Signal signal) {
            this.signals.add(signal);
            return this;
        }

        public int getVariableCount() {
            return variableCount;
        }

        public int getPointCount() {
            return pointCount;
        }

        public Builder withPoint(List<Double> list) {
            this.values.add(list);
            return this;
        }

        public LTSpiceRaw build() {
            double[][] valuesArray = new double[variableCount][pointCount];

            for (int i = 0; i < values.size(); i++) {
                List<Double> listOfValue = values.get(i);
                for (int j = 0; j < listOfValue.size(); j++) {
                    valuesArray[j][i] = listOfValue.get(j);
                }
            }

            ListOfTraces traces = ListOfTraces.buildFromSignals(signals, valuesArray);

            return new LTSpiceRaw(title, date, plotName,
                    flags, offset, command, traces);
        }

    }


}
