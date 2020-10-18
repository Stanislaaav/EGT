package com.siliev.egt.entities;

import java.sql.Timestamp;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class TimestampCreator {

    public static Timestamp cteateTimestamp(Long timestampToDatabase) {
        DateTime dateTimeParis = new DateTime(timestampToDatabase).withZone(DateTimeZone.forID("Europe/Sofia"));
        return new Timestamp(dateTimeParis.getMillis());
    }

    public static Timestamp cteateTimestamp() {
        DateTime dateTimeParis1 = new DateTime().withZone(DateTimeZone.forID("Europe/Sofia"));
        return new Timestamp(dateTimeParis1.getMillis());
    }

}
