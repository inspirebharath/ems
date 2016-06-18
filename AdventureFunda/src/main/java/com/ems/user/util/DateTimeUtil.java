/**
 * 
 */
package com.ems.user.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.ems.user.constants.Constants;

/**
 * @author Bharath Arya
 *
 */
public final class DateTimeUtil implements Constants {
	
	/*private final int ONE_DAY = 1000 * 60 * 60 * 24;
	
	private final int ONE_HOUR = ONE_DAY / 24;
	
	private final int ONE_MINUTE = ONE_HOUR / 60;
	
	private final int ONE_SECOND = ONE_MINUTE / 60;*/

	private static SimpleDateFormat dateFormat = new SimpleDateFormat();
	
	public static Timestamp getCurrentTimeStampForDb() {
		return (new Timestamp(System.currentTimeMillis()));
	}
	
	public static String getDateFromTimestamp(Timestamp timestamp) {
		return null;
	}
	
	public static Date getDateTimeFromTimestamp(Timestamp timestamp) {
	    long milliseconds = timestamp.getTime() + (timestamp.getNanos() / 1000000);
	    return new Date(milliseconds);
	}
	
	public static String getTimeFromTimestamp(Timestamp timestamp) {
		return null;
	}
	
	public static String getCurrentDateInString() throws ParseException {
		dateFormat.applyPattern(APP_DATE_FORMAT);
		return dateFormat.format(new Date());
	}
	
	public static Timestamp getTimestamp(String date) throws ParseException {
		if(null == date || "".equals(date)) {
			return null;
		}
		dateFormat.applyPattern(APP_DATE_FORMAT);
		Date tempDate = dateFormat.parse(date);

		return new Timestamp(tempDate.getTime());
	}
	
	public static long getTimeDiffOfDate(String timeStampInString) throws ParseException {
		
		Date date1 = getDateTimeFromTimestamp(getCurrentTimeStampForDb());
		
		Date date2 = getDateTimeFromTimestamp(new Timestamp(Long.valueOf(timeStampInString)));
		
		return getTimeDifference(date1, date2, TimeField.HOUR);
		
	}
	
	 /**
     * Calculate the absolute difference between two Date without
     * regard for time offsets
     *
     * @param d1 Date one
     * @param d2 Date two
     * @param field The field we're interested in out of
     * day, hour, minute, second, millisecond
     *
     * @return The value of the required field
     */
    public static long getTimeDifference(Date d1, Date d2, TimeField field) {
        return getTimeDifference(d1, d2)[field.ordinal()];
    }

    /**
     * Calculate the absolute difference between two Date without
     * regard for time offsets
     *
     * @param d1 Date one
     * @param d2 Date two
     * @return The fields day, hour, minute, second and millisecond
     */
    public static long[] getTimeDifference(Date d1, Date d2) {
        long[] result = new long[5];
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.setTime(d1);

        long t1 = cal.getTimeInMillis();
        cal.setTime(d2);

        long diff = Math.abs(cal.getTimeInMillis() - t1);
        final int ONE_DAY = 1000 * 60 * 60 * 24;
        final int ONE_HOUR = ONE_DAY / 24;
        final int ONE_MINUTE = ONE_HOUR / 60;
        final int ONE_SECOND = ONE_MINUTE / 60;

        long d = diff / ONE_DAY;
        diff %= ONE_DAY;

        long h = diff / ONE_HOUR;
        diff %= ONE_HOUR;

        long m = diff / ONE_MINUTE;
        diff %= ONE_MINUTE;

        long s = diff / ONE_SECOND;
        long ms = diff % ONE_SECOND;
        result[0] = d;
        result[1] = h;
        result[2] = m;
        result[3] = s;
        result[4] = ms;

        return result;
    }

    public static void printDiffs(long[] diffs) {
        System.out.printf("Days:         %3d\n", diffs[0]);
        System.out.printf("Hours:        %3d\n", diffs[1]);
        System.out.printf("Minutes:      %3d\n", diffs[2]);
        System.out.printf("Seconds:      %3d\n", diffs[3]);
        System.out.printf("Milliseconds: %3d\n", diffs[4]);
    }

    public static enum TimeField {DAY,
        HOUR,
        MINUTE,
        SECOND,
        MILLISECOND;
    }
	
	public static void main(String[] args) {
		System.out.println();
	}
	
}