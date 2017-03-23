/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.nifi.android.sitetosite.service;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

import java.io.IOException;

import static org.apache.nifi.android.sitetosite.service.SiteToSiteService.IO_EXCEPTION;

public interface QueuedOperationResultCallback {
    /**
     * Handler to do the invoking
     *
     * @return the handler
     */
    Handler getHandler();

    /**
     * Success callback
     */
    void onSuccess();

    /**
     * Failure callback
     * @param exception the error
     */
    void onException(IOException exception);

    /**
     * Class to proxy the callback via a ResultReceiver (a must for the IntentService)
     */
    final class Receiver extends ResultReceiver {
        private final QueuedOperationResultCallback delegate;

        /**
         * Create a new ResultReceive to receive results.  Your
         * {@link #onReceiveResult} method will be called from the thread running
         * <var>handler</var> if given, or from an arbitrary thread if null.
         *
         * @param delegate
         */
        public Receiver(QueuedOperationResultCallback delegate) {
            super(delegate.getHandler());
            this.delegate = delegate;
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);

            if (resultCode == 0) {
                delegate.onSuccess();
            } else {
                delegate.onException((IOException) resultData.getSerializable(IO_EXCEPTION));
            }
        }

        public static ResultReceiver wrap(QueuedOperationResultCallback queuedOperationResultCallback) {
            return new Receiver(queuedOperationResultCallback);
        }

        /**
         * Sends the given result to the receiver
         *
         * @param resultReceiver         the receiver
         */
        public static void onSuccess(ResultReceiver resultReceiver) {
            if (resultReceiver == null) {
                return;
            }
            resultReceiver.send(0, new Bundle());
        }

        /**
         * Sends the given exception to the receiver
         *
         * @param resultReceiver         the receiver
         * @param exception              the exception
         */
        public static void onException(ResultReceiver resultReceiver, IOException exception) {
            if (resultReceiver == null) {
                return;
            }
            Bundle resultData = new Bundle();
            resultData.putSerializable(IO_EXCEPTION, exception);
            resultReceiver.send(1, resultData);
        }
    }
}