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

@JsonPropertyOrder({"minx", "maxx", "miny", "maxy", "crs"})
public class EnvelopeInfo {
    private Double minx;
    private Double maxx;
    private Double miny;
    private Double maxy;
    private String crs;

    public EnvelopeInfo() {
    }

    public EnvelopeInfo minx(Double minx) {
        this.minx = minx;
        return this;
    }

    @JsonProperty("minx")
    @JsonInclude(Include.USE_DEFAULTS)
    public Double getMinx() {
        return this.minx;
    }

    public void setMinx(Double minx) {
        this.minx = minx;
    }

    public EnvelopeInfo maxx(Double maxx) {
        this.maxx = maxx;
        return this;
    }

    @JsonProperty("maxx")
    @JsonInclude(Include.USE_DEFAULTS)
    public Double getMaxx() {
        return this.maxx;
    }

    public void setMaxx(Double maxx) {
        this.maxx = maxx;
    }

    public EnvelopeInfo miny(Double miny) {
        this.miny = miny;
        return this;
    }

    @JsonProperty("miny")
    @JsonInclude(Include.USE_DEFAULTS)
    public Double getMiny() {
        return this.miny;
    }

    public void setMiny(Double miny) {
        this.miny = miny;
    }

    public EnvelopeInfo maxy(Double maxy) {
        this.maxy = maxy;
        return this;
    }

    @JsonProperty("maxy")
    @JsonInclude(Include.USE_DEFAULTS)
    public Double getMaxy() {
        return this.maxy;
    }

    public void setMaxy(Double maxy) {
        this.maxy = maxy;
    }

    public EnvelopeInfo crs(String crs) {
        this.crs = crs;
        return this;
    }

    @JsonProperty("crs")
    @JsonInclude(Include.USE_DEFAULTS)
    public String getCrs() {
        return this.crs;
    }

    public void setCrs(String crs) {
        this.crs = crs;
    }
}
