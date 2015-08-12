package edu.pdx.cs410J.mckean.client;

import edu.pdx.cs410J.AbstractPhoneCall;
import edu.pdx.cs410J.AbstractPhoneBill;

import java.lang.Override;
import java.util.*;

public class PhoneBill extends AbstractPhoneBill
{
    private Collection<AbstractPhoneCall> calls = new ArrayList<AbstractPhoneCall>();

    @Override
    public String getCustomer() {
        return "CS410J";
    }

    @Override
    public void addPhoneCall(AbstractPhoneCall call) {
        this.calls.add(call);
    }

    @Override
    public Collection getPhoneCalls() {
    return this.calls;
  }

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