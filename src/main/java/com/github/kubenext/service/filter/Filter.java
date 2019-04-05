package com.github.kubenext.service.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Base class for the various attribute filters. It can be added to a criteria class as a member,
 * to support the following query parameters:
 * <pre>
 *     fieldName.equals='something'
 *     fieldName.specified=true
 *     fieldName.specified=false
 *     fieldName.in='something','other'
 * </pre>
 * @param <FIELD_TYPE>
 */
public class Filter<FIELD_TYPE> implements Serializable {

    private static final long serialVersionUID = 1L;

    private FIELD_TYPE equals;
    private Boolean specified;
    private List<FIELD_TYPE> in;

    public Filter() { }

    public Filter(Filter<FIELD_TYPE> filter) {
        this.equals = filter.equals;
        this.specified = filter.specified;
        this.in = filter.in == null ? null : new ArrayList<>(filter.in);
    }

    public Filter<FIELD_TYPE> copy() {
        return new Filter<>(this);
    }

    public FIELD_TYPE getEquals() {
        return equals;
    }

    public Filter<FIELD_TYPE> setEquals(FIELD_TYPE equals) {
        this.equals = equals;
        return this;
    }

    public Boolean getSpecified() {
        return this.specified;
    }

    public Filter<FIELD_TYPE> setSpecified(Boolean specified) {
        this.specified = specified;
        return this;
    }

    public List<FIELD_TYPE> getIn() {
        return this.in;
    }

    public Filter<FIELD_TYPE> setIn(List<FIELD_TYPE> in) {
        this.in = in;
        return this;
    }

    protected String getFilterName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Filter<?> filter = (Filter<?>) o;
        return Objects.equals(equals, filter.equals) &&
                Objects.equals(specified, filter.specified) &&
                Objects.equals(in, filter.in);
    }

    @Override
    public int hashCode() {
        return Objects.hash(equals, specified, in);
    }

    @Override
    public String toString() {
        return "Filter{" +
                "equals=" + equals +
                ", specified=" + specified +
                ", in=" + in +
                '}';
    }
}
