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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({"name", "nativeName", "alias", "namespace", "title", "abstract", "description", "enabled", "advertised", "keywords", "metadataLinks", "nativeBoundingBox", "latLonBoundingBox", "srs", "nativeCRS", "projectionPolicy", "metadata", "store", "serviceConfiguration", "disabledServices", "cqlFilter", "maxFeatures", "numDecimals", "padWithZeros", "forcedDecimal", "responseSRS", "overridingServiceSRS", "skipNumberMatched", "circularArcPresent", "encodeMeasures", "linearizationTolerance", "attributes"})
public class FeatureTypeResponse {
    private String name;
    private String nativeName;
    private List<String> alias = null;
    private NamedLink namespace;
    private String title;
    private String _abstract;
    private String description;
    private Boolean enabled;
    private Boolean advertised;
    private ResourceResponseKeywords keywords;
    private MetadataLinks metadataLinks;
    private EnvelopeResponse nativeBoundingBox;
    private EnvelopeResponse latLonBoundingBox;
    private String srs;
    private Object nativeCRS;
    private ProjectionPolicy projectionPolicy;
    private MetadataMap metadata;
    private NamedLink store;
    private Boolean serviceConfiguration;
    private StringArrayResponse disabledServices;
    private String cqlFilter;
    private Integer maxFeatures;
    private Integer numDecimals;
    private Boolean padWithZeros;
    private Boolean forcedDecimal;
    private StringArrayResponse responseSRS;
    private Boolean overridingServiceSRS;
    private Boolean skipNumberMatched;
    private Boolean circularArcPresent;
    private Boolean encodeMeasures;
    private BigDecimal linearizationTolerance;
    private AttributeTypeInfoResponse attributes;

    public FeatureTypeResponse() {
    }

    public FeatureTypeResponse name(String name) {
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

    public FeatureTypeResponse nativeName(String nativeName) {
        this.nativeName = nativeName;
        return this;
    }

    @JsonProperty("nativeName")
    @JsonInclude(Include.USE_DEFAULTS)
    public String getNativeName() {
        return this.nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public FeatureTypeResponse alias(List<String> alias) {
        this.alias = alias;
        return this;
    }

    public FeatureTypeResponse addAliasItem(String aliasItem) {
        if (this.alias == null) {
            this.alias = new ArrayList();
        }

        this.alias.add(aliasItem);
        return this;
    }

    @JsonProperty("alias")
    @JsonInclude(Include.USE_DEFAULTS)
    public List<String> getAlias() {
        return this.alias;
    }

    public void setAlias(List<String> alias) {
        this.alias = alias;
    }

    public FeatureTypeResponse namespace(NamedLink namespace) {
        this.namespace = namespace;
        return this;
    }

    @JsonProperty("namespace")
    @JsonInclude(Include.USE_DEFAULTS)
    public NamedLink getNamespace() {
        return this.namespace;
    }

    public void setNamespace(NamedLink namespace) {
        this.namespace = namespace;
    }

    public FeatureTypeResponse title(String title) {
        this.title = title;
        return this;
    }

    @JsonProperty("title")
    @JsonInclude(Include.USE_DEFAULTS)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public FeatureTypeResponse _abstract(String _abstract) {
        this._abstract = _abstract;
        return this;
    }

    @JsonProperty("abstract")
    @JsonInclude(Include.USE_DEFAULTS)
    public String getAbstract() {
        return this._abstract;
    }

    public void setAbstract(String _abstract) {
        this._abstract = _abstract;
    }

    public FeatureTypeResponse description(String description) {
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

    public FeatureTypeResponse enabled(Boolean enabled) {
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

    public FeatureTypeResponse advertised(Boolean advertised) {
        this.advertised = advertised;
        return this;
    }

    @JsonProperty("advertised")
    @JsonInclude(Include.USE_DEFAULTS)
    public Boolean getAdvertised() {
        return this.advertised;
    }

    public void setAdvertised(Boolean advertised) {
        this.advertised = advertised;
    }

    public FeatureTypeResponse keywords(ResourceResponseKeywords keywords) {
        this.keywords = keywords;
        return this;
    }

    @JsonProperty("keywords")
    @JsonInclude(Include.USE_DEFAULTS)
    public ResourceResponseKeywords getKeywords() {
        return this.keywords;
    }

    public void setKeywords(ResourceResponseKeywords keywords) {
        this.keywords = keywords;
    }

    public FeatureTypeResponse metadataLinks(MetadataLinks metadataLinks) {
        this.metadataLinks = metadataLinks;
        return this;
    }

    @JsonProperty("metadataLinks")
    @JsonInclude(Include.USE_DEFAULTS)
    public MetadataLinks getMetadataLinks() {
        return this.metadataLinks;
    }

    public void setMetadataLinks(MetadataLinks metadataLinks) {
        this.metadataLinks = metadataLinks;
    }

    public FeatureTypeResponse nativeBoundingBox(EnvelopeResponse nativeBoundingBox) {
        this.nativeBoundingBox = nativeBoundingBox;
        return this;
    }

    @JsonProperty("nativeBoundingBox")
    @JsonInclude(Include.USE_DEFAULTS)
    public EnvelopeResponse getNativeBoundingBox() {
        return this.nativeBoundingBox;
    }

    public void setNativeBoundingBox(EnvelopeResponse nativeBoundingBox) {
        this.nativeBoundingBox = nativeBoundingBox;
    }

    public FeatureTypeResponse latLonBoundingBox(EnvelopeResponse latLonBoundingBox) {
        this.latLonBoundingBox = latLonBoundingBox;
        return this;
    }

    @JsonProperty("latLonBoundingBox")
    @JsonInclude(Include.USE_DEFAULTS)
    public EnvelopeResponse getLatLonBoundingBox() {
        return this.latLonBoundingBox;
    }

    public void setLatLonBoundingBox(EnvelopeResponse latLonBoundingBox) {
        this.latLonBoundingBox = latLonBoundingBox;
    }

    public FeatureTypeResponse srs(String srs) {
        this.srs = srs;
        return this;
    }

    @JsonProperty("srs")
    @JsonInclude(Include.USE_DEFAULTS)
    public String getSrs() {
        return this.srs;
    }

    public void setSrs(String srs) {
        this.srs = srs;
    }

    public FeatureTypeResponse nativeCRS(Object nativeCRS) {
        this.nativeCRS = nativeCRS;
        return this;
    }

    @JsonProperty("nativeCRS")
    @JsonInclude(Include.USE_DEFAULTS)
    public Object getNativeCRS() {
        return this.nativeCRS;
    }

    public void setNativeCRS(Object nativeCRS) {
        this.nativeCRS = nativeCRS;
    }

    public FeatureTypeResponse projectionPolicy(ProjectionPolicy projectionPolicy) {
        this.projectionPolicy = projectionPolicy;
        return this;
    }

    @JsonProperty("projectionPolicy")
    @JsonInclude(Include.USE_DEFAULTS)
    public ProjectionPolicy getProjectionPolicy() {
        return this.projectionPolicy;
    }

    public void setProjectionPolicy(ProjectionPolicy projectionPolicy) {
        this.projectionPolicy = projectionPolicy;
    }

    public FeatureTypeResponse metadata(MetadataMap metadata) {
        this.metadata = metadata;
        return this;
    }

    @JsonProperty("metadata")
    @JsonInclude(Include.USE_DEFAULTS)
    public MetadataMap getMetadata() {
        return this.metadata;
    }

    public void setMetadata(MetadataMap metadata) {
        this.metadata = metadata;
    }

    public FeatureTypeResponse store(NamedLink store) {
        this.store = store;
        return this;
    }

    @JsonProperty("store")
    @JsonInclude(Include.USE_DEFAULTS)
    public NamedLink getStore() {
        return this.store;
    }

    public void setStore(NamedLink store) {
        this.store = store;
    }

    public FeatureTypeResponse serviceConfiguration(Boolean serviceConfiguration) {
        this.serviceConfiguration = serviceConfiguration;
        return this;
    }

    @JsonProperty("serviceConfiguration")
    @JsonInclude(Include.USE_DEFAULTS)
    public Boolean getServiceConfiguration() {
        return this.serviceConfiguration;
    }

    public void setServiceConfiguration(Boolean serviceConfiguration) {
        this.serviceConfiguration = serviceConfiguration;
    }

    public FeatureTypeResponse disabledServices(StringArrayResponse disabledServices) {
        this.disabledServices = disabledServices;
        return this;
    }

    @JsonProperty("disabledServices")
    @JsonInclude(Include.USE_DEFAULTS)
    public StringArrayResponse getDisabledServices() {
        return this.disabledServices;
    }

    public void setDisabledServices(StringArrayResponse disabledServices) {
        this.disabledServices = disabledServices;
    }

    public FeatureTypeResponse cqlFilter(String cqlFilter) {
        this.cqlFilter = cqlFilter;
        return this;
    }

    @JsonProperty("cqlFilter")
    @JsonInclude(Include.USE_DEFAULTS)
    public String getCqlFilter() {
        return this.cqlFilter;
    }

    public void setCqlFilter(String cqlFilter) {
        this.cqlFilter = cqlFilter;
    }

    public FeatureTypeResponse maxFeatures(Integer maxFeatures) {
        this.maxFeatures = maxFeatures;
        return this;
    }

    @JsonProperty("maxFeatures")
    @JsonInclude(Include.USE_DEFAULTS)
    public Integer getMaxFeatures() {
        return this.maxFeatures;
    }

    public void setMaxFeatures(Integer maxFeatures) {
        this.maxFeatures = maxFeatures;
    }

    public FeatureTypeResponse numDecimals(Integer numDecimals) {
        this.numDecimals = numDecimals;
        return this;
    }

    @JsonProperty("numDecimals")
    @JsonInclude(Include.USE_DEFAULTS)
    public Integer getNumDecimals() {
        return this.numDecimals;
    }

    public void setNumDecimals(Integer numDecimals) {
        this.numDecimals = numDecimals;
    }

    public FeatureTypeResponse padWithZeros(Boolean padWithZeros) {
        this.padWithZeros = padWithZeros;
        return this;
    }

    @JsonProperty("padWithZeros")
    @JsonInclude(Include.USE_DEFAULTS)
    public Boolean getPadWithZeros() {
        return this.padWithZeros;
    }

    public void setPadWithZeros(Boolean padWithZeros) {
        this.padWithZeros = padWithZeros;
    }

    public FeatureTypeResponse forcedDecimal(Boolean forcedDecimal) {
        this.forcedDecimal = forcedDecimal;
        return this;
    }

    @JsonProperty("forcedDecimal")
    @JsonInclude(Include.USE_DEFAULTS)
    public Boolean getForcedDecimal() {
        return this.forcedDecimal;
    }

    public void setForcedDecimal(Boolean forcedDecimal) {
        this.forcedDecimal = forcedDecimal;
    }

    public FeatureTypeResponse responseSRS(StringArrayResponse responseSRS) {
        this.responseSRS = responseSRS;
        return this;
    }

    @JsonProperty("responseSRS")
    @JsonInclude(Include.USE_DEFAULTS)
    public StringArrayResponse getResponseSRS() {
        return this.responseSRS;
    }

    public void setResponseSRS(StringArrayResponse responseSRS) {
        this.responseSRS = responseSRS;
    }

    public FeatureTypeResponse overridingServiceSRS(Boolean overridingServiceSRS) {
        this.overridingServiceSRS = overridingServiceSRS;
        return this;
    }

    @JsonProperty("overridingServiceSRS")
    @JsonInclude(Include.USE_DEFAULTS)
    public Boolean getOverridingServiceSRS() {
        return this.overridingServiceSRS;
    }

    public void setOverridingServiceSRS(Boolean overridingServiceSRS) {
        this.overridingServiceSRS = overridingServiceSRS;
    }

    public FeatureTypeResponse skipNumberMatched(Boolean skipNumberMatched) {
        this.skipNumberMatched = skipNumberMatched;
        return this;
    }

    @JsonProperty("skipNumberMatched")
    @JsonInclude(Include.USE_DEFAULTS)
    public Boolean getSkipNumberMatched() {
        return this.skipNumberMatched;
    }

    public void setSkipNumberMatched(Boolean skipNumberMatched) {
        this.skipNumberMatched = skipNumberMatched;
    }

    public FeatureTypeResponse circularArcPresent(Boolean circularArcPresent) {
        this.circularArcPresent = circularArcPresent;
        return this;
    }

    @JsonProperty("circularArcPresent")
    @JsonInclude(Include.USE_DEFAULTS)
    public Boolean getCircularArcPresent() {
        return this.circularArcPresent;
    }

    public void setCircularArcPresent(Boolean circularArcPresent) {
        this.circularArcPresent = circularArcPresent;
    }

    public FeatureTypeResponse encodeMeasures(Boolean encodeMeasures) {
        this.encodeMeasures = encodeMeasures;
        return this;
    }

    @JsonProperty("encodeMeasures")
    @JsonInclude(Include.USE_DEFAULTS)
    public Boolean getEncodeMeasures() {
        return this.encodeMeasures;
    }

    public void setEncodeMeasures(Boolean encodeMeasures) {
        this.encodeMeasures = encodeMeasures;
    }

    public FeatureTypeResponse linearizationTolerance(BigDecimal linearizationTolerance) {
        this.linearizationTolerance = linearizationTolerance;
        return this;
    }

    @JsonProperty("linearizationTolerance")
    @JsonInclude(Include.USE_DEFAULTS)
    public BigDecimal getLinearizationTolerance() {
        return this.linearizationTolerance;
    }

    public void setLinearizationTolerance(BigDecimal linearizationTolerance) {
        this.linearizationTolerance = linearizationTolerance;
    }

    public FeatureTypeResponse attributes(AttributeTypeInfoResponse attributes) {
        this.attributes = attributes;
        return this;
    }

    @JsonProperty("attributes")
    @JsonInclude(Include.USE_DEFAULTS)
    public AttributeTypeInfoResponse getAttributes() {
        return this.attributes;
    }

    public void setAttributes(AttributeTypeInfoResponse attributes) {
        this.attributes = attributes;
    }
}
