package edu.pdx.cs410J.mckean.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import edu.pdx.cs410J.AbstractPhoneCall;
import edu.pdx.cs410J.AbstractPhoneBill;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * A basic GWT class that makes sure that we can send an Phone Bill back from the server
 */
public class PhoneBillGwt implements EntryPoint {
    private VerticalPanel mainPanel = new VerticalPanel();
    private Button readMe = new Button("Help!");
    private Button button = new Button("Ping Server");
    private Button addCall = new Button("Add a new call");
    private Button printBillButton = new Button("Print a Phone Bill");
    private TextBox printBillBox = new TextBox();
    private FlexTable callTable = new FlexTable();
    private TextBox customerBox = new TextBox();
    private TextBox calleeBox = new TextBox();
    private TextBox callerBox = new TextBox();
    private TextBox startTimeBox = new TextBox();
    private TextBox endTimeBox = new TextBox();
    private HorizontalPanel AddCall = new HorizontalPanel();
    private HorizontalPanel printBill = new HorizontalPanel();
    private HashMap<String, PhoneBill> phoneBillHashMap = new HashMap<>();

    public void onModuleLoad() {

        callTable.setText(0, 0, "Customer | ");
        callTable.setText(0, 1, "Caller | ");
        callTable.setText(0, 2, "Callee | ");
        callTable.setText(0, 3, "Start Time | ");
        callTable.setText(0, 4, "End Time");

        AddCall.add(customerBox);
        AddCall.add(callerBox);
        AddCall.add(calleeBox);
        AddCall.add(startTimeBox);
        AddCall.add(endTimeBox);
        AddCall.add(addCall);

        printBill.add(printBillBox);
        printBill.add(printBillButton);

        mainPanel.add(readMe);
        mainPanel.add(button);
        mainPanel.add(AddCall);
        mainPanel.add(callTable);
        mainPanel.add(printBill);

        RootPanel rootPanel = RootPanel.get();
        rootPanel.add(mainPanel);

        readMe.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent clickEvent) {
                StringBuilder sb = new StringBuilder(readMe());
                Window.alert(sb.toString());
            }
        });

        addCall.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                addNewCall();
            }
        });

        printBillButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                printBill();
            }
        });

        /*
        button.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent clickEvent) {
                PingServiceAsync async = GWT.create(PingService.class);
                async.ping(new AsyncCallback<AbstractPhoneBill>() {

                    public void onFailure(Throwable ex) {
                        Window.alert(ex.toString());
                    }

                    public void onSuccess(AbstractPhoneBill phonebill) {
                        StringBuilder sb = new StringBuilder(phonebill.toString());
                        Collection<AbstractPhoneCall> calls = phonebill.getPhoneCalls();
                        for (AbstractPhoneCall call : calls) {
                            sb.append(call);
                            sb.append("\n");
                        }
                        Window.alert(sb.toString());
                    }
                });
            }
        });
        */
    }


    public String readMe() {
        String readMe = "Project 5 README. This project uses Google Web Toolkit to create a web application for adding" +
                        "and viewing phone bills and phone calls. You can add phone calls to phone bills and if a phone"
                        +" exists for a user that doesn't have a phone bill, a new one will be created. This app can also"
                        +" search through phone bills by the time calls were made, as well as print the phone bills.";
        return readMe;
    }

    public void addNewCall() {
        String customer = customerBox.getText();
        String caller = callerBox.getText();
        String callee = calleeBox.getText();
        String startTime = startTimeBox.getText();
        String endTime = endTimeBox.getText();
        int row = callTable.getRowCount();


        if(customer.matches("^[\\s]+") || customer.isEmpty()) {
            Window.alert("Customer name was entered incorrectly");
            return;
        }
        if (!caller.matches("\\d\\d\\d-\\d\\d\\d-\\d\\d\\d\\d")) {
            Window.alert("Caller phone number in the incorrect form, please enter a number with the form ###-###-#### where # means a digit");
            return;
        }
        if (!callee.matches("\\d\\d\\d-\\d\\d\\d-\\d\\d\\d\\d")) {
            Window.alert("Callee phone number 01/02/2015 02:00 pmis in the incorrect form, please enter a number with the form ###-###-#### where # means a digit");
            return;
        }
        if (!startTime.matches("(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/[0-9]{2}([0-9]{2}) (1[012]|[1-9]):[0-5][0-9] [APap][mM]$")) {
            Window.alert("Start date and time is in the incorrect form, please enter in a date with the following form: "
                    + "MM/dd/yyyy h:mm am/pm");
            return;
        }

        if (!endTime.matches("(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/[0-9]{2}([0-9]{2}) (1[012]|[1-9]):[0-5][0-9] [APap][mM]$")) {
            Window.alert("End date or time is in the incorrect form, please enter in a date with the following form: "
            + "MM/dd/yyyy h:mm am/pm");
            return;
        }

        PhoneCall newCall = new PhoneCall();
        newCall.caller = caller;
        newCall.callee = callee;
        newCall.startTime = DateTimeFormat.getFormat("MM/dd/yyyy h:mm a").parse(startTime);
        newCall.endTime = DateTimeFormat.getFormat("MM/dd/yyyy h:mm a").parse(endTime);

        PhoneBill newBill = phoneBillHashMap.get(customer);
        if(newBill != null) {
            newBill.addPhoneCall(newCall);
        }
        else {
            phoneBillHashMap.put(customer, new PhoneBill());
            phoneBillHashMap.get(customer).addPhoneCall(newCall);
        }


        callTable.setText(row, 0, customer);
        callTable.setText(row, 1, newCall.getCaller());
        callTable.setText(row, 2, newCall.getCallee());
        callTable.setText(row, 3, newCall.getStartTimeString());
        callTable.setText(row, 4, newCall.getEndTimeString());

        customerBox.setText("");
        callerBox.setText("");
        calleeBox.setText("");
        startTimeBox.setText("");
        endTimeBox.setText("");
    }

    public void printBill() {
        String customer = printBillBox.getText();
        if(phoneBillHashMap.get(customer) != null) {
            Window.alert("Customer: " + customer + "\n" + phoneBillHashMap.get(customer).billToString());
        }
        else {
            Window.alert("There is not a phone bill stored for the customer you specified");
        }

        printBillBox.setText("");
    }
}
