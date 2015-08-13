package edu.pdx.cs410J.mckean.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.mckean.client.PhoneBill;
import edu.pdx.cs410J.mckean.client.PhoneCall;
import edu.pdx.cs410J.mckean.client.PingService;

import java.lang.Override;
import java.util.HashMap;

/**
 * The server-side implementation of the Phone Bill service
 */
public class PingServiceImpl extends RemoteServiceServlet implements PingService
{
    /**
     * Data structure to hold all the phone bills, separated by customer name.
     */
    private HashMap<String, PhoneBill> phoneBillHashMap = new HashMap<>();


    /**
     * Server side ping function to add a call to a phone bill
     * @param customer takes in a string for a customer name.
     * @param newCall takes in a phone call to be added.
     * @return returns the phone bill after the call has been added.
     */
    @Override
    public AbstractPhoneBill ping(String customer, PhoneCall newCall) {
        PhoneBill newBill = phoneBillHashMap.get(customer);
        if(newBill != null) {
            newBill.addPhoneCall(newCall);
        }
        else {
            phoneBillHashMap.put(customer, new PhoneBill());
            phoneBillHashMap.get(customer).addPhoneCall(newCall);
        }
        return newBill;
    }

    /**
     * Return the current date/time on the server
     */
    @Override
    public AbstractPhoneBill ping() {
        return null;
    }


    /**
      *Log unhandled exceptions to standard error
      * @param unhandled
      *        The exception that wasn't handled
     */
    @Override
    protected void doUnexpectedFailure(Throwable unhandled) {
    unhandled.printStackTrace(System.err);
    super.doUnexpectedFailure(unhandled);
    }
}
