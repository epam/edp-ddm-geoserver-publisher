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

@JsonPropertyOrder({"entry"})
public class MetadataMap {
    private List<MetadataEntry> entry = null;

    public MetadataMap() {
    }

    public MetadataMap entry(List<MetadataEntry> entry) {
        this.entry = entry;
        return this;
    }

    public MetadataMap addEntryItem(MetadataEntry entryItem) {
        if (this.entry == null) {
            this.entry = new ArrayList();
        }

        this.entry.add(entryItem);
        return this;
    }

    @JsonProperty("entry")
    @JsonInclude(Include.USE_DEFAULTS)
    public List<MetadataEntry> getEntry() {
        return this.entry;
    }

    public void setEntry(List<MetadataEntry> entry) {
        this.entry = entry;
    }
}
