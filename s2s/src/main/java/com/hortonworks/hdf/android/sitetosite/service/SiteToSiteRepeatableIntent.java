/*
 * Copyright 2017 Hortonworks, Inc.
 * All rights reserved.
 *
 *   Hortonworks, Inc. licenses this file to you under the Apache License, Version 2.0
 *   (the "License"); you may not use this file except in compliance with
 *   the License. You may obtain a copy of the License at
 *   http://www.apache.org/licenses/LICENSE-2.0
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 * See the associated NOTICE file for additional information regarding copyright ownership.
 */

package com.hortonworks.hdf.android.sitetosite.service;

import android.app.PendingIntent;
import android.content.Intent;

/**
 * PendingIntent wrapper with enough metadata to update the intent later
 */
public class SiteToSiteRepeatableIntent {
    private final int requestCode;
    private final Intent intent;
    private final PendingIntent pendingIntent;

    public SiteToSiteRepeatableIntent(int requestCode, Intent intent, PendingIntent pendingIntent) {
        this.requestCode = requestCode;
        this.intent = intent;
        this.pendingIntent = pendingIntent;
    }

    /**
     * Gets the request code for the pending intent
     *
     * @return the request code for the pending intent
     */
    public int getRequestCode() {
        return requestCode;
    }

    /**
     * Gets the intent
     *
     * @return the intent
     */
    public Intent getIntent() {
        return intent;
    }

    /**
     * Gets the pending intent
     *
     * @return the pending intent
     */
    public PendingIntent getPendingIntent() {
        return pendingIntent;
    }
}
