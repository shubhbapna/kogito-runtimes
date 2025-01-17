/*
 * Copyright 2023 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kie.kogito.serverless.workflow.executor;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.kie.kogito.internal.process.runtime.KogitoWorkItem;
import org.kie.kogito.serverless.workflow.SWFConstants;
import org.kie.kogito.serverless.workflow.WorkflowWorkItemHandler;

import static org.kie.kogito.serverless.workflow.SWFConstants.CONTENT_DATA;
import static org.kie.kogito.serverless.workflow.parser.types.ServiceTypeHandler.SERVICE_IMPL_KEY;
import static org.kie.kogito.serverless.workflow.parser.types.ServiceTypeHandler.SERVICE_TASK_TYPE;
import static org.kie.kogito.serverless.workflow.parser.types.ServiceTypeHandler.WORKITEM_INTERFACE;
import static org.kie.kogito.serverless.workflow.parser.types.ServiceTypeHandler.WORKITEM_INTERFACE_IMPL;
import static org.kie.kogito.serverless.workflow.parser.types.ServiceTypeHandler.WORKITEM_OPERATION;
import static org.kie.kogito.serverless.workflow.parser.types.ServiceTypeHandler.WORKITEM_OPERATION_IMPL;

public abstract class ServiceWorkItemHandler extends WorkflowWorkItemHandler {

    private static final Collection<String> keysToRemove = Set.of(SERVICE_IMPL_KEY, WORKITEM_OPERATION_IMPL, WORKITEM_INTERFACE_IMPL);

    @Override
    protected Object internalExecute(KogitoWorkItem workItem, Map<String, Object> parameters) {
        String className = (String) parameters.remove(WORKITEM_INTERFACE);
        String methodName = (String) parameters.remove(WORKITEM_OPERATION);
        parameters.keySet().removeAll(keysToRemove);
        try {
            int size = parameters.size();
            if (size == 0) {
                return invoke(className, methodName, workItem.getParameter(SWFConstants.MODEL_WORKFLOW_VAR));
            } else if (parameters.size() == 1 && parameters.containsKey(CONTENT_DATA)) {
                return invoke(className, methodName, parameters.get(CONTENT_DATA));
            } else {
                return invoke(className, methodName, parameters.values().toArray());
            }
        } catch (ReflectiveOperationException ex) {
            throw new IllegalStateException(ex);
        }
    }

    protected abstract Object invoke(String className, String methodName, Object... parameters) throws ReflectiveOperationException;

    @Override
    public String getName() {
        return SERVICE_TASK_TYPE;
    }
}
