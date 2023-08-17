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

@JsonPropertyOrder({"value", "language", "vocabulary"})
public class KeywordInfo {
    private String value;
    private String language;
    private String vocabulary;

    public KeywordInfo() {
    }

    public KeywordInfo value(String value) {
        this.value = value;
        return this;
    }
    
    @JsonProperty("value")
    @JsonInclude(Include.ALWAYS)
    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public KeywordInfo language(String language) {
        this.language = language;
        return this;
    }

    @JsonProperty("language")
    @JsonInclude(Include.USE_DEFAULTS)
    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public KeywordInfo vocabulary(String vocabulary) {
        this.vocabulary = vocabulary;
        return this;
    }

    @JsonProperty("vocabulary")
    @JsonInclude(Include.USE_DEFAULTS)
    public String getVocabulary() {
        return this.vocabulary;
    }

    public void setVocabulary(String vocabulary) {
        this.vocabulary = vocabulary;
    }
}
