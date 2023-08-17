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
import java.net.URI;

@JsonPropertyOrder({"name", "description", "enabled", "workspace", "connectionParameters", "__default", "featureTypes"})
public class DataStoreResponse {
    private String name;
    private String description;
    private Boolean enabled;
    private NamedLink workspace;
    private ConnectionParameters connectionParameters;
    private Boolean _default = false;
    private URI featureTypes;

    public DataStoreResponse() {
    }

    public DataStoreResponse name(String name) {
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

    public DataStoreResponse description(String description) {
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

    public DataStoreResponse enabled(Boolean enabled) {
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

    public DataStoreResponse workspace(NamedLink workspace) {
        this.workspace = workspace;
        return this;
    }

    @JsonProperty("workspace")
    @JsonInclude(Include.USE_DEFAULTS)
    public NamedLink getWorkspace() {
        return this.workspace;
    }

    public void setWorkspace(NamedLink workspace) {
        this.workspace = workspace;
    }

    public DataStoreResponse connectionParameters(ConnectionParameters connectionParameters) {
        this.connectionParameters = connectionParameters;
        return this;
    }

    @JsonProperty("connectionParameters")
    @JsonInclude(Include.USE_DEFAULTS)
    public ConnectionParameters getConnectionParameters() {
        return this.connectionParameters;
    }

    public void setConnectionParameters(ConnectionParameters connectionParameters) {
        this.connectionParameters = connectionParameters;
    }

    public DataStoreResponse _default(Boolean _default) {
        this._default = _default;
        return this;
    }

    @JsonProperty("__default")
    @JsonInclude(Include.USE_DEFAULTS)
    public Boolean getDefault() {
        return this._default;
    }

    public void setDefault(Boolean _default) {
        this._default = _default;
    }

    public DataStoreResponse featureTypes(URI featureTypes) {
        this.featureTypes = featureTypes;
        return this;
    }

    @JsonProperty("featureTypes")
    @JsonInclude(Include.USE_DEFAULTS)
    public URI getFeatureTypes() {
        return this.featureTypes;
    }

    public void setFeatureTypes(URI featureTypes) {
        this.featureTypes = featureTypes;
    }
}
