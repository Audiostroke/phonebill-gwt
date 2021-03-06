package edu.pdx.cs410J.mckean.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import edu.pdx.cs410J.AbstractPhoneBill;

/**
 * A GWT remote service that returns a dummy Phone Bill
 */
@RemoteServiceRelativePath("ping")
public interface PingService extends RemoteService {

  /**
   * Returns the a dummy Phone Bill
   */
  public AbstractPhoneBill ping(String customer, PhoneCall newCall);

  /**
   * Return the current date/time on the server
   */
  AbstractPhoneBill ping();
}
