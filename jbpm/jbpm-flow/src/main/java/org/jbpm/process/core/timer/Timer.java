/*
 * Copyright 2010 Red Hat, Inc. and/or its affiliates.
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
package org.jbpm.process.core.timer;

import java.io.Serializable;
import java.util.Objects;

/**
 * 
 */
public class Timer implements Serializable {

    public static final int TIME_DURATION = 1;
    public static final int TIME_CYCLE = 2;
    public static final int TIME_DATE = 3;

    private long id;
    private String delay;
    private String period;
    private String date;
    private int timeType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Timer{" +
                "id='" + id + '\'' +
                ", delay='" + delay + '\'' +
                ", period='" + period + '\'' +
                ", date='" + date + '\'' +
                ", timeType=" + timeType +
                '}';
    }

    public int getTimeType() {
        if (timeType == 0) {
            // calculate type based on given data
            if (date != null && date.trim().length() > 0) {
                timeType = TIME_DATE;
            } else if (delay != null && delay.trim().length() > 0
                    && period != null && period.trim().length() > 0) {
                timeType = TIME_CYCLE;
            } else {
                timeType = TIME_DURATION;
            }
        }
        return timeType;
    }

    public void setTimeType(int timeType) {
        this.timeType = timeType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Timer)) {
            return false;
        }
        Timer timer = (Timer) o;
        return timeType == timer.timeType && id == timer.id && Objects.equals(delay, timer.delay) && Objects.equals(period,
                timer.period) && Objects.equals(date, timer.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, delay, period, date, timeType);
    }
}
