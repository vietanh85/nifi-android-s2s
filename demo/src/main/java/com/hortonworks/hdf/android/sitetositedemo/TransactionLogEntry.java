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

package com.hortonworks.hdf.android.sitetositedemo;

import com.hortonworks.hdf.android.sitetosite.client.TransactionResult;

import java.io.IOException;
import java.util.Date;

/**
 * Results of a transaction
 */
public class TransactionLogEntry {
    private long id;
    private final Date created;
    private final TransactionResult transactionResult;
    private final IOException ioException;

    public TransactionLogEntry(TransactionResult transactionResult) {
        this(-1, new Date(), transactionResult, null);
    }

    public TransactionLogEntry(IOException ioException) {
        this(-1, new Date(), null, ioException);
    }

    public TransactionLogEntry(long id, Date created, TransactionResult transactionResult, IOException ioException) {
        this.id = id;
        this.created = created;
        this.transactionResult = transactionResult;
        this.ioException = ioException;
    }

    /**
     * Gets the id
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Gets the created date
     *
     * @return the created date
     */
    public Date getCreated() {
        return created;
    }

    protected void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the transaction result
     *
     * @return the transaction result
     */
    public TransactionResult getTransactionResult() {
        return transactionResult;
    }

    /**
     * Gets the io exception
     *
     * @return the io exception
     */
    public IOException getIoException() {
        return ioException;
    }
}
