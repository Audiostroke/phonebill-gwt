package edu.pdx.cs410J.mckean.client;

import edu.pdx.cs410J.AbstractPhoneCall;
import edu.pdx.cs410J.AbstractPhoneBill;

import java.lang.Override;
import java.util.*;

/**
 * Phone bill class that is used in many components of the program. Describes the object that contains a list of
 * phone calls.
 */
public class PhoneBill extends AbstractPhoneBill
{
    /**
     * Data structure to hold phone calls.
     */
    private Collection<AbstractPhoneCall> calls = new ArrayList<AbstractPhoneCall>();

    /**
     * Function to return the customer, however it is not used in this program.
     * @return the string containing the customer name.
     */
    @Override
    public String getCustomer() {
        return "CS410J";
    }

    /**
     * Function that adds a phone call from the client to the phonebill list.
     * @param call -  the new call to be added.
     */
    @Override
    public void addPhoneCall(AbstractPhoneCall call) {
        this.calls.add(call);
    }

    /**
     * function that returns the list of phone calls for an individual phone bill.
     * @return returns a list of calls as a collection.
     */
    @Override
    public Collection getPhoneCalls() {
    return this.calls;
  }

    /**
     * Function that takes the list of phone calls from a phone bill and converts them to a string so they
     * can be printed in a nice format to the user.
     * @return Returns a long string containing all phone calls from the specified phone bill.
     */
    public String billToString() {
        List<PhoneCall> temp = (List<PhoneCall>) this.getPhoneCalls();
        Collections.sort(temp);
        String [] phonebill = new String[temp.size()];
        int counter = 0;
        for (PhoneCall call : temp) {
            phonebill[counter] = call.callToString();
            ++counter;
        }
        StringBuilder sb = new StringBuilder();
        for (String s : phonebill) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Function that makes a string of phone calls in a nice format that fall between the paramters set by the user.
     * @param start - the date object that calls are compared to, any returned must be after this date.
     * @param end - the date object that calls are compared to, any returned must be before this date.
     * @return returns a string containing a nicely formatted list of phone calls.
     */
    public String searchBillString(Date start, Date end) {
        int counter = 0;
        List<PhoneCall> temp = (List<PhoneCall>) this.getPhoneCalls();
        Collections.sort(temp);
        for(PhoneCall call : temp) {
                if(call.getStartTime().after(start) && call.getEndTime().before(end)) {
                    ++counter;
                }
        }
        if(counter == 0) {
            return "There are no calls within these times, please try again!";
        }
        String [] phonebill = new String[counter];
        int counter2 = 0;
        for(PhoneCall call : temp) {
            if(call.getStartTime().after(start) && call.getEndTime().before(end)) {
                phonebill[counter2] = call.callToString();
                ++counter2;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(String s : phonebill) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }
}