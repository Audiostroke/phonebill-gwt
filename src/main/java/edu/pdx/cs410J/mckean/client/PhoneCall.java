package edu.pdx.cs410J.mckean.client;

import com.google.gwt.i18n.shared.DateTimeFormat;
import edu.pdx.cs410J.AbstractPhoneCall;

import java.lang.Override;
import java.util.Date;

/**
 * The phone call class used by Phone Bill, describes the objects most often manipulated and added.
 */
public class PhoneCall extends AbstractPhoneCall implements Comparable<PhoneCall>
{
    /**
     * Variabled describing a phone call ( a number for caller, callee, and dates for the start and end times)/
     */
    protected String caller;
    protected String callee;
    protected Date startTime;
    protected Date endTime;

  @Override
  public String getCaller() {
    return caller;
  }

  @Override
  public Date getStartTime() {
    return startTime;
  }

  public String getStartTimeString() {
    return DateTimeFormat.getFormat("MM/dd/yyyy h:mm a").format(startTime);
  }

  @Override
  public String getCallee() {
    return callee;
  }

  public Date getEndTime() {
    return endTime;
  }

  public String getEndTimeString() {
    return DateTimeFormat.getFormat("MM/dd/yyyy h:mm a").format(endTime);
  }

    public String callToString() {
        String phonecall;
        phonecall = "Caller number: " + this.getCaller() + "\n" +
                "Callee number: " + this.getCallee() + "\n" + "Start Date and Time: " + this.getStartTimeString() + "\n"
                + "End Date and Time: " + this.getEndTimeString() + "\n";
        return phonecall;
    }


    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     * <p>
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     * <p>
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     * <p>
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     * <p>
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     * <p>
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(PhoneCall o) {
        int compareTime = startTime.compareTo(o.getStartTime());
        if (compareTime != 0) {
            return compareTime;
        }

        int compareCaller = caller.compareTo(o.getCaller());
        if (compareCaller != 0) {
            return compareCaller;
        }

        return 0;

    }
}