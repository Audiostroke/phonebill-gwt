package edu.pdx.cs410J.mckean.client;

import edu.pdx.cs410J.AbstractPhoneCall;
import edu.pdx.cs410J.AbstractPhoneBill;

import java.lang.Override;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    public String  billToString() {
        List<PhoneCall> temp = (List<PhoneCall>) this.getPhoneCalls();
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
}
