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

package com.epam.digital.data.platform.geoserver.repository;

import static com.epam.digital.data.platform.geoserver.repository.GeoMetadataRepository.INSERT_QUERY;
import static com.epam.digital.data.platform.geoserver.repository.GeoMetadataRepository.SELECT_QUERY;
import static org.mockito.ArgumentMatchers.anyString;

import net.bytebuddy.utility.RandomString;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

@ExtendWith(MockitoExtension.class)
public class GeoMetadataRepositoryTest {

  @Mock
  JdbcTemplate jdbcTemplate;

  GeoMetadataRepository repository;

  @BeforeEach
  void init() {
    repository = new GeoMetadataRepository(jdbcTemplate, "registry");
  }

  @Test
  void getTableNameLinkedWithSCTest() {
    var scNameV = "scName_v";
    var scName = "scName";
    var tableName = "tableName";
    var colName = "colName";

    Mockito.when(jdbcTemplate.queryForObject(SELECT_QUERY,
        String.class, scName, colName)).thenReturn(tableName);
    final String tableNameLinkedWithSC = repository.getTableNameLinkedWithSC(scNameV, colName);
    Mockito.verify(jdbcTemplate).queryForObject(SELECT_QUERY, String.class, scName, colName);

    Assertions.assertThat(tableNameLinkedWithSC).isEqualTo(tableName);
  }

  @Test
  void notSaveMetadataTest() {
    repository.saveMetadata(null, null);
    Mockito.verify(jdbcTemplate, Mockito.never()).update(anyString());
  }

  @Test
  void saveMetadataTest() {
    var table = RandomString.make();
    var column = RandomString.make();

    repository.saveMetadata(table, column);
    Mockito.verify(jdbcTemplate).update(INSERT_QUERY, "registry", table, column);
  }
}
