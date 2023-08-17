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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import com.epam.digital.data.platform.geoserver.client.GeoserverFeignClient;
import com.epam.digital.data.platform.geoserver.model.DataStoreResponse;
import com.epam.digital.data.platform.geoserver.model.FeatureTypeInfoWrapper;
import com.epam.digital.data.platform.geoserver.model.FeatureTypeResponseWrapper;
import com.epam.digital.data.platform.geoserver.model.NamedLink;
import feign.FeignException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
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

@ExtendWith(MockitoExtension.class)
class LayersServiceTest {

  private static final String WORKSPACE_NAME = "workspace";
  private static final String STORE_NAME = "store";

  private LayersService service;

  @Mock private GeoserverFeignClient geoserverFeignClient;
  @Mock private Catalog catalog;

  @BeforeEach
  void beforeEach() {
    service = new LayersService(geoserverFeignClient, catalog);
  }

  @Test
  void expectNoLayersIfNoGeometryType() {
    var table = mockTableWithColumns("table", "text");
    when(catalog.getTables()).thenReturn(Collections.singletonList(table));
    var dataStore = new DataStoreResponse();

    service.create(dataStore);

    verifyNoInteractions(geoserverFeignClient);
  }

  @Test
  void expectLayerCreatedForGeometryTypeIfNotExists() {
    var geometryTableName = "table_with_geometry";
    var table1 = mockTableWithColumns(geometryTableName, "text", "geometry");
    var table2 = mockTableWithColumns("some_table", "text", "uuid");
    when(catalog.getTables()).thenReturn(List.of(table1, table2));
    when(geoserverFeignClient.getFeatureType(WORKSPACE_NAME, STORE_NAME, geometryTableName, true))
        .thenThrow(FeignException.NotFound.class);
    var dataStore =
        new DataStoreResponse().workspace(new NamedLink().name(WORKSPACE_NAME)).name(STORE_NAME);

    service.create(dataStore);

    verify(geoserverFeignClient)
        .getFeatureType(WORKSPACE_NAME, STORE_NAME, geometryTableName, true);
    ArgumentCaptor<FeatureTypeInfoWrapper> featureTypeCaptor =
        ArgumentCaptor.forClass(FeatureTypeInfoWrapper.class);
    verify(geoserverFeignClient)
        .createFeatureTypeOnStore(eq(WORKSPACE_NAME), eq(STORE_NAME), featureTypeCaptor.capture());
    var actualFt = featureTypeCaptor.getValue();
    assertThat(actualFt.getFeatureType().getName()).isEqualTo(geometryTableName);
    assertThat(actualFt.getFeatureType().getNativeName()).isEqualTo(geometryTableName);
    assertThat(actualFt.getFeatureType().getTitle()).isEqualTo(geometryTableName);
    assertThat(actualFt.getFeatureType().getNativeCRS()).isEqualTo("EPSG:4326");
    assertThat(actualFt.getFeatureType().getSrs()).isEqualTo("EPSG:4326");
    assertThat(actualFt.getFeatureType().getEnabled()).isTrue();
  }

  @Test
  void expectLayerIsUpdatedForExistingLayerWithGeometryType() {
    var geometryTableName = "table_with_geometry";
    var table1 = mockTableWithColumns(geometryTableName, "text", "geometry");
    var table2 = mockTableWithColumns("some_table", "text", "uuid");
    when(catalog.getTables()).thenReturn(List.of(table1, table2));
    when(geoserverFeignClient.getFeatureType(WORKSPACE_NAME, STORE_NAME, geometryTableName, true))
        .thenReturn(new FeatureTypeResponseWrapper());
    var dataStore =
        new DataStoreResponse().workspace(new NamedLink().name(WORKSPACE_NAME)).name(STORE_NAME);

    service.create(dataStore);

    verify(geoserverFeignClient)
        .getFeatureType(WORKSPACE_NAME, STORE_NAME, geometryTableName, true);
    ArgumentCaptor<FeatureTypeInfoWrapper> featureTypeCaptor =
        ArgumentCaptor.forClass(FeatureTypeInfoWrapper.class);
    verify(geoserverFeignClient)
        .modifyFeatureTypeByStore(
            eq(WORKSPACE_NAME),
            eq(STORE_NAME),
            eq(geometryTableName),
            featureTypeCaptor.capture(),
            eq(null));
    var actualFt = featureTypeCaptor.getValue();
    assertThat(actualFt.getFeatureType().getName()).isEqualTo(geometryTableName);
    assertThat(actualFt.getFeatureType().getNativeName()).isEqualTo(geometryTableName);
    assertThat(actualFt.getFeatureType().getTitle()).isEqualTo(geometryTableName);
    assertThat(actualFt.getFeatureType().getNativeCRS()).isEqualTo("EPSG:4326");
    assertThat(actualFt.getFeatureType().getSrs()).isEqualTo("EPSG:4326");
    assertThat(actualFt.getFeatureType().getEnabled()).isTrue();
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
