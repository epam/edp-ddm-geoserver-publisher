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

@JsonPropertyOrder({"name", "nativeName", "namespace", "title", "abstract", "description", "enabled", "alias", "dataLinks", "disabledServices", "keywords", "latLonBoundingBox", "nativeBoundingBox", "metadata", "metadataLinks", "nativeCRS", "srs", "projectionPolicy", "advertised", "serviceConfiguration", "store", "cqlFilter", "maxFeatures", "numDecimals", "padWithZeros", "forcedDecimal", "responseSRS", "overridingServiceSRS", "skipNumberMatched", "circularArcPresent", "encodeMeasures", "linearizationTolerance", "attributes"})
public class FeatureTypeInfo {
    private String name;
    private String nativeName;
    private NamespaceInfo namespace;
    private String title;
    private String _abstract;
    private String description;
    private Boolean enabled;
    private List<String> alias = null;
    private List<DataLinkInfo> dataLinks = null;
    private List<String> disabledServices = null;
    private List<KeywordInfo> keywords = null;
    private EnvelopeInfo latLonBoundingBox;
    private EnvelopeInfo nativeBoundingBox;
    private MetadataMap metadata;
    private MetadataLinks metadataLinks;
    private String nativeCRS;
    private String srs;
    private ProjectionPolicy projectionPolicy;
    private Boolean advertised;
    private Boolean serviceConfiguration;
    private DataStoreInfo store;
    private String cqlFilter;
    private Integer maxFeatures;
    private Integer numDecimals;
    private Boolean padWithZeros;
    private Boolean forcedDecimal;
    private List<String> responseSRS = null;
    private Boolean overridingServiceSRS;
    private Boolean skipNumberMatched;
    private Boolean circularArcPresent;
    private Boolean encodeMeasures;
    private BigDecimal linearizationTolerance;
    private List<AttributeTypeInfo> attributes = null;

    public FeatureTypeInfo() {
    }

    public FeatureTypeInfo name(String name) {
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

    public FeatureTypeInfo nativeName(String nativeName) {
        this.nativeName = nativeName;
        return this;
    }

    @JsonProperty("nativeName")
    @JsonInclude(Include.ALWAYS)
    public String getNativeName() {
        return this.nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public FeatureTypeInfo namespace(NamespaceInfo namespace) {
        this.namespace = namespace;
        return this;
    }

    @JsonProperty("namespace")
    @JsonInclude(Include.USE_DEFAULTS)
    public NamespaceInfo getNamespace() {
        return this.namespace;
    }

    public void setNamespace(NamespaceInfo namespace) {
        this.namespace = namespace;
    }

    public FeatureTypeInfo title(String title) {
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

    public FeatureTypeInfo _abstract(String _abstract) {
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

    public FeatureTypeInfo description(String description) {
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

    public FeatureTypeInfo enabled(Boolean enabled) {
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

    public FeatureTypeInfo alias(List<String> alias) {
        this.alias = alias;
        return this;
    }

    public FeatureTypeInfo addAliasItem(String aliasItem) {
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

    public FeatureTypeInfo dataLinks(List<DataLinkInfo> dataLinks) {
        this.dataLinks = dataLinks;
        return this;
    }

    public FeatureTypeInfo addDataLinksItem(DataLinkInfo dataLinksItem) {
        if (this.dataLinks == null) {
            this.dataLinks = new ArrayList();
        }

        this.dataLinks.add(dataLinksItem);
        return this;
    }

    @JsonProperty("dataLinks")
    @JsonInclude(Include.USE_DEFAULTS)
    public List<DataLinkInfo> getDataLinks() {
        return this.dataLinks;
    }

    public void setDataLinks(List<DataLinkInfo> dataLinks) {
        this.dataLinks = dataLinks;
    }

    public FeatureTypeInfo disabledServices(List<String> disabledServices) {
        this.disabledServices = disabledServices;
        return this;
    }

    public FeatureTypeInfo addDisabledServicesItem(String disabledServicesItem) {
        if (this.disabledServices == null) {
            this.disabledServices = new ArrayList();
        }

        this.disabledServices.add(disabledServicesItem);
        return this;
    }

    @JsonProperty("disabledServices")
    @JsonInclude(Include.USE_DEFAULTS)
    public List<String> getDisabledServices() {
        return this.disabledServices;
    }

    public void setDisabledServices(List<String> disabledServices) {
        this.disabledServices = disabledServices;
    }

    public FeatureTypeInfo keywords(List<KeywordInfo> keywords) {
        this.keywords = keywords;
        return this;
    }

    public FeatureTypeInfo addKeywordsItem(KeywordInfo keywordsItem) {
        if (this.keywords == null) {
            this.keywords = new ArrayList();
        }

        this.keywords.add(keywordsItem);
        return this;
    }

    @JsonProperty("keywords")
    @JsonInclude(Include.USE_DEFAULTS)
    public List<KeywordInfo> getKeywords() {
        return this.keywords;
    }

    public void setKeywords(List<KeywordInfo> keywords) {
        this.keywords = keywords;
    }

    public FeatureTypeInfo latLonBoundingBox(EnvelopeInfo latLonBoundingBox) {
        this.latLonBoundingBox = latLonBoundingBox;
        return this;
    }

    @JsonProperty("latLonBoundingBox")
    @JsonInclude(Include.USE_DEFAULTS)
    public EnvelopeInfo getLatLonBoundingBox() {
        return this.latLonBoundingBox;
    }

    public void setLatLonBoundingBox(EnvelopeInfo latLonBoundingBox) {
        this.latLonBoundingBox = latLonBoundingBox;
    }

    public FeatureTypeInfo nativeBoundingBox(EnvelopeInfo nativeBoundingBox) {
        this.nativeBoundingBox = nativeBoundingBox;
        return this;
    }

    @JsonProperty("nativeBoundingBox")
    @JsonInclude(Include.USE_DEFAULTS)
    public EnvelopeInfo getNativeBoundingBox() {
        return this.nativeBoundingBox;
    }

    public void setNativeBoundingBox(EnvelopeInfo nativeBoundingBox) {
        this.nativeBoundingBox = nativeBoundingBox;
    }

    public FeatureTypeInfo metadata(MetadataMap metadata) {
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

    public FeatureTypeInfo metadataLinks(MetadataLinks metadataLinks) {
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

    public FeatureTypeInfo nativeCRS(String nativeCRS) {
        this.nativeCRS = nativeCRS;
        return this;
    }

    @JsonProperty("nativeCRS")
    @JsonInclude(Include.USE_DEFAULTS)
    public String getNativeCRS() {
        return this.nativeCRS;
    }

    public void setNativeCRS(String nativeCRS) {
        this.nativeCRS = nativeCRS;
    }

    public FeatureTypeInfo srs(String srs) {
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

    public FeatureTypeInfo projectionPolicy(ProjectionPolicy projectionPolicy) {
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

    public FeatureTypeInfo advertised(Boolean advertised) {
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

    public FeatureTypeInfo serviceConfiguration(Boolean serviceConfiguration) {
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

    public FeatureTypeInfo store(DataStoreInfo store) {
        this.store = store;
        return this;
    }

    @JsonProperty("store")
    @JsonInclude(Include.USE_DEFAULTS)
    public DataStoreInfo getStore() {
        return this.store;
    }

    public void setStore(DataStoreInfo store) {
        this.store = store;
    }

    public FeatureTypeInfo cqlFilter(String cqlFilter) {
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

    public FeatureTypeInfo maxFeatures(Integer maxFeatures) {
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

    public FeatureTypeInfo numDecimals(Integer numDecimals) {
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

    public FeatureTypeInfo padWithZeros(Boolean padWithZeros) {
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

    public FeatureTypeInfo forcedDecimal(Boolean forcedDecimal) {
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

    public FeatureTypeInfo responseSRS(List<String> responseSRS) {
        this.responseSRS = responseSRS;
        return this;
    }

    public FeatureTypeInfo addResponseSRSItem(String responseSRSItem) {
        if (this.responseSRS == null) {
            this.responseSRS = new ArrayList();
        }

        this.responseSRS.add(responseSRSItem);
        return this;
    }

    @JsonProperty("responseSRS")
    @JsonInclude(Include.USE_DEFAULTS)
    public List<String> getResponseSRS() {
        return this.responseSRS;
    }

    public void setResponseSRS(List<String> responseSRS) {
        this.responseSRS = responseSRS;
    }

    public FeatureTypeInfo overridingServiceSRS(Boolean overridingServiceSRS) {
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

    public FeatureTypeInfo skipNumberMatched(Boolean skipNumberMatched) {
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

    public FeatureTypeInfo circularArcPresent(Boolean circularArcPresent) {
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

    public FeatureTypeInfo encodeMeasures(Boolean encodeMeasures) {
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

    public FeatureTypeInfo linearizationTolerance(BigDecimal linearizationTolerance) {
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

    public FeatureTypeInfo attributes(List<AttributeTypeInfo> attributes) {
        this.attributes = attributes;
        return this;
    }

    public FeatureTypeInfo addAttributesItem(AttributeTypeInfo attributesItem) {
        if (this.attributes == null) {
            this.attributes = new ArrayList();
        }

        this.attributes.add(attributesItem);
        return this;
    }

    @JsonProperty("attributes")
    @JsonInclude(Include.USE_DEFAULTS)
    public List<AttributeTypeInfo> getAttributes() {
        return this.attributes;
    }

    public void setAttributes(List<AttributeTypeInfo> attributes) {
        this.attributes = attributes;
    }
}
