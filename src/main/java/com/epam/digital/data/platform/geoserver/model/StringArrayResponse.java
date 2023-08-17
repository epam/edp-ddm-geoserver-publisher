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
import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({"string"})
public class StringArrayResponse {
    private List<String> string = null;

    public StringArrayResponse() {
    }

    public StringArrayResponse string(List<String> string) {
        this.string = string;
        return this;
    }

    public StringArrayResponse addStringItem(String stringItem) {
        if (this.string == null) {
            this.string = new ArrayList();
        }

        this.string.add(stringItem);
        return this;
    }

    @JsonProperty("string")
    @JsonInclude(Include.USE_DEFAULTS)
    public List<String> getString() {
        return this.string;
    }

    public void setString(List<String> string) {
        this.string = string;
    }
}
