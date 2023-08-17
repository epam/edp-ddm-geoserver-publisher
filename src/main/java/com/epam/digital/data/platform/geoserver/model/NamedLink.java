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

@JsonPropertyOrder({"@class", "name", "href", "link"})
public class NamedLink {
    private String atClass;
    private String name;
    private String href;
    private String link;

    public NamedLink() {
    }

    public NamedLink atClass(String atClass) {
        this.atClass = atClass;
        return this;
    }
    
    @JsonProperty("@class")
    @JsonInclude(Include.USE_DEFAULTS)
    public String getAtClass() {
        return this.atClass;
    }

    public void setAtClass(String atClass) {
        this.atClass = atClass;
    }

    public NamedLink name(String name) {
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

    public NamedLink href(String href) {
        this.href = href;
        return this;
    }

    @JsonProperty("href")
    @JsonInclude(Include.USE_DEFAULTS)
    public String getHref() {
        return this.href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public NamedLink link(String link) {
        this.link = link;
        return this;
    }

    @JsonProperty("link")
    @JsonInclude(Include.USE_DEFAULTS)
    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

