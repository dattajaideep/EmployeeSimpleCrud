package com.learn.employees.bean;

/**
 * @author jaideepvaranasi
 * Refer the below link
 * @apiNote {@link <a href="https://reflectoring.io/spring-data-specifications/">Learn More about Jpa Specification</a>}
 */
public enum SearchOperation {
    GREATER_THAN,
    LESS_THAN,
    GREATER_THAN_EQUAL,
    LESS_THAN_EQUAL,
    NOT_EQUAL,
    EQUAL,
    MATCH_CASE,
    MATCH_IGNORE_CASE,
    MATCH_START,
    MATCH_END,
    IN,
    NOT_IN,
    D_OP //Dummy Operation - To be Extended or Replaced
}
