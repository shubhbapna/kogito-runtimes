/*
 * Copyright 2022 Red Hat, Inc. and/or its affiliates.
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
package org.kie.kogito.event.process;

import java.util.Objects;

import org.kie.kogito.event.AbstractDataEvent;
import org.kie.kogito.event.cloudevents.CloudEventExtensionConstants;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProcessDataEvent<T> extends AbstractDataEvent<T> {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty(CloudEventExtensionConstants.PROCESS_INSTANCE_VERSION)
    private String kogitoProcessInstanceVersion;

    @JsonProperty(CloudEventExtensionConstants.PROCESS_PARENT_PROCESS_INSTANCE_ID)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected String kogitoParentProcessInstanceId;

    @JsonProperty(CloudEventExtensionConstants.PROCESS_USER_TASK_INSTANCE_STATE)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected String kogitoProcessInstanceState;

    @JsonProperty(CloudEventExtensionConstants.PROCESS_REFERENCE_ID)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected String kogitoReferenceId;

    @JsonProperty(CloudEventExtensionConstants.PROCESS_START_FROM_NODE)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected String kogitoStartFromNode;

    @JsonProperty(CloudEventExtensionConstants.BUSINESS_KEY)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected String kogitoBusinessKey;

    public ProcessDataEvent() {
    }

    public ProcessDataEvent(String type,
            String source,
            T body,
            String kogitoProcessInstanceId,
            String kogitoProcessInstanceVersion,
            String kogitoParentProcessInstanceId,
            String kogitoRootProcessInstanceId,
            String kogitoProcessId,
            String kogitoRootProcessId,
            String kogitoProcessInstanceState,
            String kogitoAddons) {
        this(type,
                source,
                body,
                kogitoProcessInstanceId,
                kogitoProcessInstanceVersion,
                kogitoParentProcessInstanceId,
                kogitoRootProcessInstanceId,
                kogitoProcessId,
                kogitoRootProcessId,
                kogitoProcessInstanceState,
                kogitoAddons,
                null);
    }

    public ProcessDataEvent(String type,
            String source,
            T body,
            String kogitoProcessInstanceId,
            String kogitoProcessInstanceVersion,
            String kogitoParentProcessInstanceId,
            String kogitoRootProcessInstanceId,
            String kogitoProcessId,
            String kogitoRootProcessId,
            String kogitoProcessInstanceState,
            String kogitoAddons,
            String kogitoReferenceId) {
        super(type,
                source,
                body,
                kogitoProcessInstanceId,
                kogitoRootProcessInstanceId,
                kogitoProcessId,
                kogitoRootProcessId,
                kogitoAddons);
        this.kogitoProcessInstanceVersion = kogitoProcessInstanceVersion;
        this.kogitoParentProcessInstanceId = kogitoParentProcessInstanceId;
        this.kogitoProcessInstanceState = kogitoProcessInstanceState;
        this.kogitoReferenceId = kogitoReferenceId;
    }

    public String getKogitoParentProcessInstanceId() {
        return kogitoParentProcessInstanceId;
    }

    public String getKogitoProcessInstanceState() {
        return kogitoProcessInstanceState;
    }

    public String getKogitoReferenceId() {
        return this.kogitoReferenceId;
    }

    public String getKogitoBusinessKey() {
        return this.kogitoBusinessKey;
    }

    public void setKogitoStartFromNode(String kogitoStartFromNode) {
        this.kogitoStartFromNode = kogitoStartFromNode;
    }

    public String getKogitoStartFromNode() {
        return this.kogitoStartFromNode;
    }

    public String getKogitoProcessInstanceVersion() {
        return kogitoProcessInstanceVersion;
    }

    @Override
    public String toString() {
        return "ProcessDataEvent{" +
                "kogitoProcessInstanceVersion='" + kogitoProcessInstanceVersion + '\'' +
                ", kogitoParentProcessInstanceId='" + kogitoParentProcessInstanceId + '\'' +
                ", kogitoProcessInstanceState='" + kogitoProcessInstanceState + '\'' +
                ", kogitoReferenceId='" + kogitoReferenceId + '\'' +
                ", kogitoStartFromNode='" + kogitoStartFromNode + '\'' +
                ", kogitoBusinessKey='" + kogitoBusinessKey + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProcessDataEvent)) {
            return false;
        }
        ProcessDataEvent<?> that = (ProcessDataEvent<?>) o;
        return Objects.equals(getKogitoParentProcessInstanceId(), that.getKogitoParentProcessInstanceId()) && Objects.equals(getKogitoProcessInstanceState(), that.getKogitoProcessInstanceState())
                && Objects.equals(getKogitoReferenceId(), that.getKogitoReferenceId()) && Objects.equals(getKogitoStartFromNode(), that.getKogitoStartFromNode())
                && Objects.equals(getKogitoBusinessKey(), that.getKogitoBusinessKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKogitoParentProcessInstanceId(), getKogitoProcessInstanceState(), getKogitoReferenceId(), getKogitoStartFromNode(), getKogitoBusinessKey());
    }
}
