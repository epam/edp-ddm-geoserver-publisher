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

@JsonPropertyOrder({"about", "type", "content"})
public class DataLinkInfo {
    private String about;
    private String type;
    private String content;

    public DataLinkInfo() {
    }

    public DataLinkInfo about(String about) {
        this.about = about;
        return this;
    }

    @JsonProperty("about")
    @JsonInclude(Include.USE_DEFAULTS)
    public String getAbout() {
        return this.about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public DataLinkInfo type(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("type")
    @JsonInclude(Include.USE_DEFAULTS)
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DataLinkInfo content(String content) {
        this.content = content;
        return this;
    }

    @JsonProperty("content")
    @JsonInclude(Include.USE_DEFAULTS)
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
