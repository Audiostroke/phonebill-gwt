package edu.pdx.cs410J.mckean.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import edu.pdx.cs410J.AbstractPhoneBill;

/**
 * The client-side interface to the ping service
 */
public interface PingServiceAsync {

  /**
   * Return the current date/time on the server
   */
  void ping(AsyncCallback<AbstractPhoneBill> async);

  /**
   * Returns the a dummy Phone Bill
   */
  void ping(String customer, PhoneCall newCall, AsyncCallback<AbstractPhoneBill> async);
}
