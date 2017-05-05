package com.andybristol.rabbit.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class Bun {

    private Integer id;

    @NotEmpty
    @NotNull
    private String name;

    @NotNull
    private Integer powerLevel;

    public Bun() {
        this(null, null, null);
    }

    public Bun(Integer id, String name, Integer powerLevel) {
        this.id = id;
        this.name = name;
        this.powerLevel = powerLevel;
    }

    @JsonProperty
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    public Integer getPowerLevel() {
        return powerLevel;
    }

    public void setPowerLevel(Integer powerLevel) {
        this.powerLevel = powerLevel;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", getId())
                .add("name", getName())
                .add("powerLevel", getPowerLevel())
                .toString();
    }
}
