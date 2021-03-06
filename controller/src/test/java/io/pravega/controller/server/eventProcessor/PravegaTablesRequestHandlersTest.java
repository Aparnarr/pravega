/**
 * Copyright (c) 2017 Dell Inc., or its subsidiaries. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package io.pravega.controller.server.eventProcessor;

import io.pravega.controller.mocks.SegmentHelperMock;
import io.pravega.controller.server.SegmentHelper;
import io.pravega.controller.server.rpc.auth.AuthHelper;
import io.pravega.controller.store.stream.StreamMetadataStore;
import io.pravega.controller.store.stream.StreamStoreFactory;
import io.pravega.controller.store.stream.Version;
import lombok.Synchronized;


public class PravegaTablesRequestHandlersTest extends RequestHandlersTest {

    private SegmentHelper segmentHelper;
    
    @Override
    @Synchronized
    StreamMetadataStore getStore() {
        if (segmentHelper == null) {
            segmentHelper = SegmentHelperMock.getSegmentHelperMockForTables(executor);
        }
        return StreamStoreFactory.createPravegaTablesStore(segmentHelper, AuthHelper.getDisabledAuthHelper(), zkClient, executor);
    }

    @Override
    int getVersionNumber(Version version) {
        return (int) version.asLongVersion().getLongValue();
    }
}
