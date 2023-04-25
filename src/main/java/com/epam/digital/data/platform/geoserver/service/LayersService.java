/*
 * Copyright 2022 EPAM Systems.
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

package com.epam.digital.data.platform.geoserver.service;

import org.geoserver.openapi.model.catalog.DataStoreInfo;
import org.geoserver.openapi.model.catalog.FeatureTypeInfo;
import org.geoserver.openapi.v1.model.DataStoreResponse;
import org.geoserver.restconfig.client.FeatureTypesClient;
import org.springframework.stereotype.Component;
import schemacrawler.schema.Catalog;
import schemacrawler.schema.NamedObject;
import schemacrawler.schema.Table;

import java.util.stream.Collectors;

@Component
public class LayersService {

  private static final String GEOMETRY_TYPE = "geometry";

  private final FeatureTypesClient featureTypesClient;
  private final Catalog catalog;

  public LayersService(
      FeatureTypesClient featureTypesClient,
      Catalog catalog) {
    this.featureTypesClient = featureTypesClient;
    this.catalog = catalog;
  }

  public void create(DataStoreResponse dataStore) {
    var tablesWithGeometry =
        catalog.getTables().stream()
            .filter(this::isTableContainingGeometryColumn)
            .map(NamedObject::getName)
            .collect(Collectors.toList());
    tablesWithGeometry.forEach(tableName -> createFeatureTypeForTable(tableName, dataStore));
  }

  private boolean isTableContainingGeometryColumn(Table table) {
    return table.getColumns().stream()
        .map(column -> column.getColumnDataType().getName())
        .anyMatch(GEOMETRY_TYPE::equalsIgnoreCase);
  }

  private void createFeatureTypeForTable(String tableName, DataStoreResponse dataStore) {
    var featureType = createDefaultFeatureTypeForTable(tableName, dataStore);
    if (featureTypesClient
        .getFeatureType(dataStore.getWorkspace().getName(), dataStore.getName(), tableName)
        .isPresent()) {
      featureTypesClient.update(dataStore.getWorkspace().getName(), tableName, featureType);
    } else {
      featureTypesClient.create(dataStore.getWorkspace().getName(), featureType);
    }
  }

  private FeatureTypeInfo createDefaultFeatureTypeForTable(
      String tableName, DataStoreResponse dataStore) {
    return new FeatureTypeInfo()
        .name(tableName)
        .nativeName(tableName)
        .store(new DataStoreInfo().name(dataStore.getName()))
        .title(tableName)
        .enabled(true)
        .nativeCRS("EPSG:4326")
        .srs("EPSG:4326");
  }
}
