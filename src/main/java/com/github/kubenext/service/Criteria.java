package com.github.kubenext.service;

/**
 * Implementation should usually contain fields of Filter instances.
 * @author lishangjin
 */
public interface Criteria {

    /**
     * @return a new criteria with copied filters
     */
    Criteria copy();

}
