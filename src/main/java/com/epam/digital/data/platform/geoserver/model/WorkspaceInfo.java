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

@JsonPropertyOrder({"name", "isolated"})
public class WorkspaceInfo {
    private String name;
    private Boolean isolated;

    public WorkspaceInfo() {
    }

    public WorkspaceInfo name(String name) {
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

    public WorkspaceInfo isolated(Boolean isolated) {
        this.isolated = isolated;
        return this;
    }

    @JsonProperty("isolated")
    @JsonInclude(Include.USE_DEFAULTS)
    public Boolean getIsolated() {
        return this.isolated;
    }

    public void setIsolated(Boolean isolated) {
        this.isolated = isolated;
    }
}
