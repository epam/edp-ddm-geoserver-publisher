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
import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder({"name", "description", "enabled", "connectionParameters", "workspace"})
public class DataStoreInfo {
    private String name;
    private String description;
    private Boolean enabled = true;
    private Map<String, String> connectionParameters = null;
    private WorkspaceInfo workspace;

    public DataStoreInfo() {
    }

    public DataStoreInfo name(String name) {
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

    public DataStoreInfo description(String description) {
        this.description = description;
        return this;
    }

    @JsonProperty("description")
    @JsonInclude(Include.USE_DEFAULTS)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DataStoreInfo enabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    @JsonProperty("enabled")
    @JsonInclude(Include.USE_DEFAULTS)
    public Boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public DataStoreInfo connectionParameters(Map<String, String> connectionParameters) {
        this.connectionParameters = connectionParameters;
        return this;
    }

    public DataStoreInfo putConnectionParametersItem(String key, String connectionParametersItem) {
        if (this.connectionParameters == null) {
            this.connectionParameters = new HashMap();
        }

        this.connectionParameters.put(key, connectionParametersItem);
        return this;
    }

    @JsonProperty("connectionParameters")
    @JsonInclude(Include.USE_DEFAULTS)
    public Map<String, String> getConnectionParameters() {
        return this.connectionParameters;
    }

    public void setConnectionParameters(Map<String, String> connectionParameters) {
        this.connectionParameters = connectionParameters;
    }

    public DataStoreInfo workspace(WorkspaceInfo workspace) {
        this.workspace = workspace;
        return this;
    }

    @JsonProperty("workspace")
    @JsonInclude(Include.USE_DEFAULTS)
    public WorkspaceInfo getWorkspace() {
        return this.workspace;
    }

    public void setWorkspace(WorkspaceInfo workspace) {
        this.workspace = workspace;
    }
}

