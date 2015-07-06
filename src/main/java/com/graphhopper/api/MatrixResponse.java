package com.graphhopper.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class defines the response for a M-to-N requests.
 *
 * @author Peter Karich
 */
public class MatrixResponse {

    private String debugInfo = "";
    private final List<Throwable> errors = new ArrayList<>(4);
    private int fromCap;
    private List<GHMResponse> currentFromList;
    private final List<List<GHMResponse>> rspList;

    public MatrixResponse() {
        this(10, 10);
    }

    public MatrixResponse(int fromCap, int toCap) {
        this.fromCap = fromCap;
        rspList = new ArrayList<>(toCap);
    }

    public void add(GHMResponse rsp) {
        if (currentFromList == null) {
            throw new IllegalStateException("call newFromList before adding");
        }

        currentFromList.add(rsp);
    }

    /**
     * This method creates a new from list and needs to be called everytime a
     * new result for the 'from' points is created.
     */
    public void newFromList() {
        rspList.add(currentFromList = new ArrayList<GHMResponse>(fromCap));
    }

    public GHMResponse get(int from, int to) {
        if (from >= rspList.size()) {
            throw new IllegalStateException("maximum size for 'from' is " + rspList.size());
        }

        List<GHMResponse> list = rspList.get(from);
        if (to >= list.size()) {
            throw new IllegalStateException("maximum size for 'to' is " + list.size());
        }
        return list.get(to);
    }

    public String getDebugInfo() {
        return debugInfo;
    }

    public MatrixResponse setDebugInfo(String debugInfo) {
        if (debugInfo != null) {
            this.debugInfo = debugInfo;
        }
        return this;
    }

    /**
     * @return true if one or more error found
     */
    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public List<Throwable> getErrors() {
        return errors;
    }

    public MatrixResponse addError(Throwable error) {
        errors.add(error);
        return this;
    }

    public MatrixResponse addErrors(Collection<Throwable> errorList) {
        errors.addAll(errorList);
        return this;
    }

    @Override
    public String toString() {
        return errors.toString();
    }
}
