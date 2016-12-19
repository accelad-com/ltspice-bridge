package com.accelad.automation.ltpsice.output.log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Yoann
 *
 */
public class Log {

    private String title;
    private String info;

    private List<Measure> measures = new ArrayList<>();

    private Date date;
    private double elapsedTime;

    private double tnom;
    private double temp;
    private String method;
    private int totiter;
    private int traniter;
    private int tranpoints;
    private int accept;
    private int rejected;
    private int matrixSize;
    private int fillins;
    private String solver;
    private String compiler1;
    private String compiler2;

    private boolean directNewtonIterationSuccess = false;
    private boolean opPointFoundByInspection = false;
    private List<String> warnings = new ArrayList<>();
    private List<HeightenedDefCon> defCons = new ArrayList<>();
    private boolean skipOperatingPoint = false;
    private String threadVector;
    private GminStepping gminStepping = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<Measure> getMeasures() {
        return measures;
    }

    public void addMeasure(Measure measure) {
        measures.add(measure);
    }

    public void setMeasures(List<Measure> measures) {
        this.measures = measures;
    }

    public Optional<Measure> findMeasureByName(String name) {
        return measures.stream()
                .filter(meas -> name.equals(meas.getName()))
                .findAny();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(double elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public double getTnom() {
        return tnom;
    }

    public void setTnom(double tnom) {
        this.tnom = tnom;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getTotiter() {
        return totiter;
    }

    public void setTotiter(int totiter) {
        this.totiter = totiter;
    }

    public int getTraniter() {
        return traniter;
    }

    public void setTraniter(int traniter) {
        this.traniter = traniter;
    }

    public int getTranpoints() {
        return tranpoints;
    }

    public void setTranpoints(int tranpoints) {
        this.tranpoints = tranpoints;
    }

    public int getAccept() {
        return accept;
    }

    public void setAccept(int accept) {
        this.accept = accept;
    }

    public int getRejected() {
        return rejected;
    }

    public void setRejected(int rejected) {
        this.rejected = rejected;
    }

    public int getMatrixSize() {
        return matrixSize;
    }

    public void setMatrixSize(int matrixSize) {
        this.matrixSize = matrixSize;
    }

    public int getFillins() {
        return fillins;
    }

    public void setFillins(int fillins) {
        this.fillins = fillins;
    }

    public String getSolver() {
        return solver;
    }

    public void setSolver(String solver) {
        this.solver = solver;
    }

    public String getCompiler1() {
        return compiler1;
    }

    public void setCompiler1(String compiler1) {
        this.compiler1 = compiler1;
    }

    public String getCompiler2() {
        return compiler2;
    }

    public void setCompiler2(String compiler2) {
        this.compiler2 = compiler2;
    }

    public void setDirectNewtonIterationSuccess(boolean directNewtonIterationSuccess) {
        this.directNewtonIterationSuccess = directNewtonIterationSuccess;
    }

    public boolean isDirectNewtonIterationSuccess() {
        return directNewtonIterationSuccess;
    }

    public void setOpPointFoundByInspection(boolean opPointFoundByInspection) {
        this.opPointFoundByInspection = opPointFoundByInspection;
    }

    public boolean isOpPointFoundByInspection() {
        return opPointFoundByInspection;
    }

    public void addWaring(String warning) {
        warnings.add(warning);
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public void addDefCon(HeightenedDefCon defcon) {
        defCons.add(defcon);
    }

    public List<HeightenedDefCon> getDefCons() {
        return defCons;
    }

    public void setSkipOperatingPoint(boolean skipOperatingPoint) {
        this.skipOperatingPoint = skipOperatingPoint;
    }

    public boolean isSkipOperatingPoint() {
        return skipOperatingPoint;
    }

    public void setThreadVector(String threadVector) {
        this.threadVector = threadVector;
    }

    public String getThreadVector() {
        return threadVector;
    }

    public void setGminStepping(GminStepping gminStepping) {
        this.gminStepping = gminStepping;
    }

    public Optional<GminStepping> getGminStepping() {
        return Optional.ofNullable(gminStepping);
    }
}
