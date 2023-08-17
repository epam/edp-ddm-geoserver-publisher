/*
 * Copyright 2023 EPAM Systems.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.epam.digital.data.platform.geoserver.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"name", "minOccurs", "maxOccurs", "nillable", "binding", "length"})
public class AttributeTypeInfo {
    private String name;
    private Integer minOccurs;
    private Integer maxOccurs;
    private Boolean nillable;
    private String binding;
    private Integer length;

    public AttributeTypeInfo() {
    }

    public AttributeTypeInfo name(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("name")
    @JsonInclude(Include.USE_DEFAULTS)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AttributeTypeInfo minOccurs(Integer minOccurs) {
        this.minOccurs = minOccurs;
        return this;
    }

    @JsonProperty("minOccurs")
    @JsonInclude(Include.USE_DEFAULTS)
    public Integer getMinOccurs() {
        return this.minOccurs;
    }

    public void setMinOccurs(Integer minOccurs) {
        this.minOccurs = minOccurs;
    }

    public AttributeTypeInfo maxOccurs(Integer maxOccurs) {
        this.maxOccurs = maxOccurs;
        return this;
    }

    @JsonProperty("maxOccurs")
    @JsonInclude(Include.USE_DEFAULTS)
    public Integer getMaxOccurs() {
        return this.maxOccurs;
    }

    public void setMaxOccurs(Integer maxOccurs) {
        this.maxOccurs = maxOccurs;
    }

    public AttributeTypeInfo nillable(Boolean nillable) {
        this.nillable = nillable;
        return this;
    }

    @JsonProperty("nillable")
    @JsonInclude(Include.USE_DEFAULTS)
    public Boolean getNillable() {
        return this.nillable;
    }

    public void setNillable(Boolean nillable) {
        this.nillable = nillable;
    }

    public AttributeTypeInfo binding(String binding) {
        this.binding = binding;
        return this;
    }

    @JsonProperty("binding")
    @JsonInclude(Include.USE_DEFAULTS)
    public String getBinding() {
        return this.binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public AttributeTypeInfo length(Integer length) {
        this.length = length;
        return this;
    }

    @JsonProperty("length")
    @JsonInclude(Include.USE_DEFAULTS)
    public Integer getLength() {
        return this.length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
