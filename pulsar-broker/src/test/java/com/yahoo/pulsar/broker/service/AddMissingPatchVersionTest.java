/**
 * Copyright 2016 Yahoo Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yahoo.pulsar.broker.service;

import com.yahoo.pulsar.broker.PulsarService;
import com.yahoo.pulsar.utils.PulsarBrokerVersionStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddMissingPatchVersionTest {
    @Test
    public void testVersionStrings() throws Exception {
        // Fixable versions (those lacking a patch release) get normalized with a patch release of 0
        Assert.assertEquals(PulsarBrokerVersionStringUtils.fixVersionString("1.2"), "1.2.0");
        Assert.assertEquals(PulsarBrokerVersionStringUtils.fixVersionString("1.2-SNAPSHOT"), "1.2.0-SNAPSHOT");
        Assert.assertEquals(PulsarBrokerVersionStringUtils.fixVersionString("1.2-SNAPSHOT+BUILD"), "1.2.0-SNAPSHOT+BUILD");
        Assert.assertEquals(PulsarBrokerVersionStringUtils.fixVersionString("1.2+BUILD"), "1.2.0+BUILD");

        // Already valid versions get returned unchanged
        Assert.assertEquals(PulsarBrokerVersionStringUtils.fixVersionString("1.2.3"), "1.2.3");
        Assert.assertEquals(PulsarBrokerVersionStringUtils.fixVersionString("1.2.3-SNAPSHOT"), "1.2.3-SNAPSHOT");
        Assert.assertEquals(PulsarBrokerVersionStringUtils.fixVersionString("1.2.3-SNAPSHOT+BUILD"), "1.2.3-SNAPSHOT+BUILD");
        Assert.assertEquals(PulsarBrokerVersionStringUtils.fixVersionString("1.2.3+BUILD"), "1.2.3+BUILD");

        // Non-fixable versions get returned as-is
        Assert.assertEquals(PulsarBrokerVersionStringUtils.fixVersionString("1"), "1");
    }
}