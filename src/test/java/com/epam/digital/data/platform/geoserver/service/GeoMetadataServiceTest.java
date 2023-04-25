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


import com.epam.digital.data.platform.geoserver.repository.GeoMetadataRepository;
import com.epam.digital.data.platform.geoserver.utils.TableUtils;
import java.util.List;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import schemacrawler.schema.Catalog;
import schemacrawler.schema.Column;
import schemacrawler.schema.ColumnDataType;
import schemacrawler.schema.PrimaryKey;
import schemacrawler.schema.Table;
import schemacrawler.schema.TableConstraintColumn;

@ExtendWith(MockitoExtension.class)
public class GeoMetadataServiceTest {

  private GeoMetadataService service;

  @Mock
  GeoMetadataRepository repository;

  @Mock
  Catalog catalog;
  @Mock
  Table table;
  @Mock
  TableConstraintColumn tableConstraint;
  @Mock
  PrimaryKey primaryKey;
  @Mock
  Column column;
  @Mock
  ColumnDataType columnDataType;

  @BeforeEach
  void init() {
    service = new GeoMetadataService(repository, catalog);
  }

  @Test
  void shouldSaveMetadataSuccess() {
    var tableNameForPk = RandomString.make() + "_v";
    var columnName = RandomString.make();
    var pk = RandomString.make();
    final List<Table> tableList = List.of(table);

    Mockito.when(column.getName()).thenReturn(columnName);
    Mockito.when(repository.getTableNameLinkedWithSC(tableNameForPk, columnName))
        .thenReturn(tableNameForPk);
    Mockito.when(catalog.getTables()).thenReturn(tableList);
    Mockito.when(table.getName()).thenReturn(tableNameForPk);
    Mockito.when(table.getPrimaryKey()).thenReturn(primaryKey);
    Mockito.when(primaryKey.getColumns()).thenReturn(List.of(tableConstraint));
    Mockito.when(tableConstraint.getName()).thenReturn(pk);
    Mockito.when(table.getColumns()).thenReturn(List.of(column));
    Mockito.when(column.getColumnDataType()).thenReturn(columnDataType);
    Mockito.when(columnDataType.getName()).thenReturn(TableUtils.GEOMETRY_TYPE);

    service.processMetadata();

    Mockito.verify(repository).saveMetadata(tableNameForPk, pk);
  }

  @Test
  void shouldSaveMetadataFailTableNotFound() {
    var tableName = RandomString.make();
    var pk = RandomString.make();
    final List<Table> tableList = List.of();

    Mockito.when(catalog.getTables()).thenReturn(tableList);

    service.processMetadata();

    Mockito.verify(repository, Mockito.never()).saveMetadata(tableName, pk);
  }

}
