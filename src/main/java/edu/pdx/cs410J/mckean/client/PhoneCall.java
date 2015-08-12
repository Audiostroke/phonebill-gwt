package edu.pdx.cs410J.mckean.client;

import com.google.gwt.i18n.shared.DateTimeFormat;
import edu.pdx.cs410J.AbstractPhoneCall;

import java.lang.Override;
import java.util.Date;

public class PhoneCall extends AbstractPhoneCall
{
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

}