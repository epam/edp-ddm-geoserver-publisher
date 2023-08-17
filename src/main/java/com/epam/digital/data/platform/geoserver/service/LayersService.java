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

package com.epam.digital.data.platform.geoserver.service;

import com.epam.digital.data.platform.geoserver.client.GeoserverFeignClient;
import com.epam.digital.data.platform.geoserver.model.DataStoreInfo;
import com.epam.digital.data.platform.geoserver.model.DataStoreResponse;
import com.epam.digital.data.platform.geoserver.model.FeatureTypeInfo;
import com.epam.digital.data.platform.geoserver.model.FeatureTypeInfoWrapper;
import com.epam.digital.data.platform.geoserver.model.FeatureTypeResponseWrapper;
import feign.FeignException;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import schemacrawler.schema.Catalog;
import schemacrawler.schema.NamedObject;
import schemacrawler.schema.Table;

@Component
public class LayersService {

  private static final String GEOMETRY_TYPE = "geometry";

  private final GeoserverFeignClient geoserverFeignClient;
  private final Catalog catalog;

  public LayersService(
      GeoserverFeignClient geoserverFeignClient,
      Catalog catalog) {
    this.geoserverFeignClient = geoserverFeignClient;
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
    Optional<FeatureTypeResponseWrapper> existingFeatureType;
    try {
      existingFeatureType =
          Optional.of(
              geoserverFeignClient.getFeatureType(
                  dataStore.getWorkspace().getName(), dataStore.getName(), tableName, true));
    } catch (FeignException.NotFound ex) {
      existingFeatureType = Optional.empty();
    }
    if (existingFeatureType.isPresent()) {
      geoserverFeignClient.modifyFeatureTypeByStore(
          dataStore.getWorkspace().getName(),
          dataStore.getName(),
          tableName,
          (new FeatureTypeInfoWrapper()).featureType(featureType),
          null);
    } else {
      geoserverFeignClient.createFeatureTypeOnStore(
          dataStore.getWorkspace().getName(),
          dataStore.getName(),
          (new FeatureTypeInfoWrapper()).featureType(featureType));
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
