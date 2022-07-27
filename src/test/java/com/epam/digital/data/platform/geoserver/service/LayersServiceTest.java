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

import org.geoserver.openapi.model.catalog.FeatureTypeInfo;
import org.geoserver.openapi.v1.model.DataStoreResponse;
import org.geoserver.openapi.v1.model.NamedLink;
import org.geoserver.restconfig.client.FeatureTypesClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import schemacrawler.schema.Catalog;
import schemacrawler.schema.Column;
import schemacrawler.schema.ColumnDataType;
import schemacrawler.schema.Table;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LayersServiceTest {

  private static final String WORKSPACE_NAME = "workspace";
  private static final String STORE_NAME = "store";

  private LayersService service;

  @Mock
  private FeatureTypesClient featureTypesClient;
  @Mock
  private Catalog catalog;


  @BeforeEach
  void beforeEach() {
    service = new LayersService(featureTypesClient, catalog);
  }

  @Test
  void expectNoLayersIfNoGeometryType() {
    var table = mockTableWithColumns("table", "text");
    when(catalog.getTables()).thenReturn(Collections.singletonList(table));
    var dataStore = new DataStoreResponse();

    service.create(dataStore);

    verifyNoInteractions(featureTypesClient);
  }

  @Test
  void expectLayerCreatedForGeometryTypeIfNotExists() {
    var geometryTableName = "table_with_geometry";
    var table1 = mockTableWithColumns(geometryTableName, "text", "geometry");
    var table2 = mockTableWithColumns("some_table", "text", "uuid");
    when(catalog.getTables()).thenReturn(List.of(table1, table2));
    when(featureTypesClient.getFeatureType(WORKSPACE_NAME, STORE_NAME, geometryTableName))
        .thenReturn(Optional.empty());
    var dataStore =
        new DataStoreResponse().workspace(new NamedLink().name(WORKSPACE_NAME)).name(STORE_NAME);

    service.create(dataStore);

    verify(featureTypesClient).getFeatureType(WORKSPACE_NAME, STORE_NAME, geometryTableName);
    ArgumentCaptor<FeatureTypeInfo> featureTypeCaptor = ArgumentCaptor.forClass(FeatureTypeInfo.class);
    verify(featureTypesClient).create(eq(WORKSPACE_NAME), featureTypeCaptor.capture());
    var actualFt = featureTypeCaptor.getValue();
    assertThat(actualFt.getName()).isEqualTo(geometryTableName);
    assertThat(actualFt.getNativeName()).isEqualTo(geometryTableName);
    assertThat(actualFt.getTitle()).isEqualTo(geometryTableName);
    assertThat(actualFt.getNativeCRS()).isEqualTo("EPSG:4326");
    assertThat(actualFt.getSrs()).isEqualTo("EPSG:4326");
    assertThat(actualFt.getEnabled()).isTrue();
  }

  @Test
  void expectLayerIsUpdatedForExistingLayerWithGeometryType() {
    var geometryTableName = "table_with_geometry";
    var table1 = mockTableWithColumns(geometryTableName, "text", "geometry");
    var table2 = mockTableWithColumns("some_table", "text", "uuid");
    when(catalog.getTables()).thenReturn(List.of(table1, table2));
    when(featureTypesClient.getFeatureType(WORKSPACE_NAME, STORE_NAME, geometryTableName))
            .thenReturn(Optional.of(new FeatureTypeInfo()));
    var dataStore =
            new DataStoreResponse().workspace(new NamedLink().name(WORKSPACE_NAME)).name(STORE_NAME);

    service.create(dataStore);

    verify(featureTypesClient).getFeatureType(WORKSPACE_NAME, STORE_NAME, geometryTableName);
    ArgumentCaptor<FeatureTypeInfo> featureTypeCaptor = ArgumentCaptor.forClass(FeatureTypeInfo.class);
    verify(featureTypesClient).update(eq(WORKSPACE_NAME), eq(geometryTableName), featureTypeCaptor.capture());
    var actualFt = featureTypeCaptor.getValue();
    assertThat(actualFt.getName()).isEqualTo(geometryTableName);
    assertThat(actualFt.getNativeName()).isEqualTo(geometryTableName);
    assertThat(actualFt.getTitle()).isEqualTo(geometryTableName);
    assertThat(actualFt.getNativeCRS()).isEqualTo("EPSG:4326");
    assertThat(actualFt.getSrs()).isEqualTo("EPSG:4326");
    assertThat(actualFt.getEnabled()).isTrue();
  }

  private Table mockTableWithColumns(String tableName, String... columnTypes) {
    var table = mock(Table.class);
    var columns = Arrays.stream(columnTypes).map(this::mockColumn).collect(Collectors.toList());
    lenient().when(table.getColumns()).thenReturn(columns);
    lenient().when(table.getName()).thenReturn(tableName);
    return table;
  }

  private Column mockColumn(String columnType) {
    var column = mock(Column.class);
    var columnDataType = mock(ColumnDataType.class);
    lenient().when(column.getColumnDataType()).thenReturn(columnDataType);
    lenient().when(columnDataType.getName()).thenReturn(columnType);
    return column;
  }
}
