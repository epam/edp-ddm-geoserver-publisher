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

@JsonPropertyOrder({"prefix", "uri", "isolated"})
public class NamespaceInfo {
    private String prefix;
    private String uri;
    private Boolean isolated;

    public NamespaceInfo() {
    }

    public NamespaceInfo prefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    @JsonProperty("prefix")
    @JsonInclude(Include.USE_DEFAULTS)
    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public NamespaceInfo uri(String uri) {
        this.uri = uri;
        return this;
    }

    @JsonProperty("uri")
    @JsonInclude(Include.USE_DEFAULTS)
    public String getUri() {
        return this.uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public NamespaceInfo isolated(Boolean isolated) {
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
