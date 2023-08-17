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

@JsonPropertyOrder({"metadataLink"})
public class MetadataLinks {
    private List<MetadataLinkInfo> metadataLink = null;

    public MetadataLinks() {
    }

    public MetadataLinks metadataLink(List<MetadataLinkInfo> metadataLink) {
        this.metadataLink = metadataLink;
        return this;
    }

    public MetadataLinks addMetadataLinkItem(MetadataLinkInfo metadataLinkItem) {
        if (this.metadataLink == null) {
            this.metadataLink = new ArrayList();
        }

        this.metadataLink.add(metadataLinkItem);
        return this;
    }

    @JsonProperty("metadataLink")
    @JsonInclude(Include.USE_DEFAULTS)
    public List<MetadataLinkInfo> getMetadataLink() {
        return this.metadataLink;
    }

    public void setMetadataLink(List<MetadataLinkInfo> metadataLink) {
        this.metadataLink = metadataLink;
    }
}
