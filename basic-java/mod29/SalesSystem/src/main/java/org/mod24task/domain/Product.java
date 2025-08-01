package org.mod24task.domain;

import java.math.BigDecimal;

public class Product {
    private Long id;
    private String code;
    private String name;
    private String description;
    private BigDecimal value;

    public Product(){

    }

    public Product(Long id, String code, String name, String description, BigDecimal valor) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.value = valor;
    }

    public Product(String code, String name, String description, BigDecimal valor) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.value = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal valor) {
        this.value = valor;
    }
}
