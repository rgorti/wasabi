/*******************************************************************************
 * Copyright 2017 Intuit
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.intuit.wasabi.repository.cassandra.impl;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

class AssignmentStatsUtil {
    private static DateTimeFormatter dayFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
    private static DateTimeFormatter hourFormatter = DateTimeFormat.forPattern("HH");

    /**
     * Helper method takes a time and returns a Date object which is an hour before the input time
     *
     * @param time number of milliseconds between Jan 1, 1970 and a desired time
     * @return Date object
     */
    static DateTime getLastCompletedHour(long time) {
        Date oldDate = new Date(time - 3600 * 1000);
        return new DateTime(oldDate);
    }

    /**
     * Helper method takes a Date object, extracts the last hour that has been completed,
     * and returns the hour as an int. (e.g.: returns 23 for date: 2017-08-10 0:05:06)

     * @param date the date containing the last completed hour
     * @return int representing an hour of the day
     */
    static int getHour(DateTime date) {
        String dtSTr = hourFormatter.print(date);
        DateTime jodaTime = hourFormatter.parseDateTime(dtSTr);
        return jodaTime.getHourOfDay();
    }

    /**
     * Helper method takes a Date object and converts it to a String in yyyy-MM-dd form
     *
     * @param date the date object for which the date string is returned
     * @return String representing a day
     */
    static String getDayString(DateTime date) {
        return dayFormatter.print(date);
    }
}
