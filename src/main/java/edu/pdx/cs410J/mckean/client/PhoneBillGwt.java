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

import java.util.*;

/**
 * A basic GWT class that makes sure that we can send an Phone Bill back from the server
 */
public class PhoneBillGwt implements EntryPoint {
    /**
     * Variables that make up the widgets on the GUI for the client, contains a series of
     * buttons, panels, textboxes, and a flextable.
     */
    private VerticalPanel mainPanel = new VerticalPanel();
    private Button readMe = new Button("README");
    private Button help = new Button("Help!");
    private Label addCallLabel = new Label("Press button after forms are filled in to add a new call");
    private Label searchButtonLabel = new Label("Press button after forms are filled in to search for a phone bill");
    private Button addCall = new Button("Add a new call");
    private Button printBillButton = new Button("Print a Phone Bill");
    private Button searchButton = new Button("Search a PhoneBill");
    private TextBox searchCustomerBox = new TextBox();
    private TextBox searchStartTimeBox = new TextBox();
    private TextBox searchEndTimeBox = new TextBox();
    private TextBox printBillBox = new TextBox();
    private FlexTable callTable = new FlexTable();
    private Label customerBoxLabel = new Label("Customer:           ");
    private TextBox customerBox = new TextBox();
    private Label callerBoxLabel = new Label("Caller:");
    private TextBox calleeBox = new TextBox();
    private Label calleeBoxLabel = new Label("Callee");
    private TextBox callerBox = new TextBox();
    private Label startBoxLabel = new Label("Start Time: ");
    private TextBox startTimeBox = new TextBox();
    private Label endBoxLabel = new Label("End Time:");
    private TextBox endTimeBox = new TextBox();
    private Label searchStartTimeLabel = new Label("Start Time: ");
    private Label searchEndTimeLabel = new Label("End Time: ");
    private Label searchCustomerLabel = new Label("End Time: ");
    private Label printCustomerLabel = new Label("Customer: ");
    private Label printButtonLabel = new Label("Press the button after entering in a customer to print their phone bill");
    private HorizontalPanel AddCall = new HorizontalPanel();
    private HorizontalPanel printBill = new HorizontalPanel();
    private HorizontalPanel searchBill = new HorizontalPanel();
    private VerticalPanel AddCallCustomer = new VerticalPanel();
    private VerticalPanel AddCallCaller = new VerticalPanel();
    private VerticalPanel AddCallCallee = new VerticalPanel();
    private VerticalPanel AddCallStart = new VerticalPanel();
    private VerticalPanel AddCallEnd = new VerticalPanel();
    private VerticalPanel AddCallButton = new VerticalPanel();
    private VerticalPanel PrintCustomer = new VerticalPanel();
    private VerticalPanel PrintButton = new VerticalPanel();
    private VerticalPanel SearchCustomer = new VerticalPanel();
    private VerticalPanel SearchStart = new VerticalPanel();
    private VerticalPanel SearchEnd = new VerticalPanel();
    private VerticalPanel SearchButtonVert = new VerticalPanel();
    private HashMap<String, PhoneBill> phoneBillHashMap = new HashMap<>();
    static PingServiceAsync pinger;

    public void onModuleLoad() {

        callTable.setText(0, 0, "Customer | ");
        callTable.setText(0, 1, "Caller | ");
        callTable.setText(0, 2, "Callee | ");
        callTable.setText(0, 3, "Start Time | ");
        callTable.setText(0, 4, "End Time");

        AddCallCustomer.add(customerBoxLabel);
        AddCallCustomer.add(customerBox);

        AddCallCaller.add(callerBoxLabel);
        AddCallCaller.add(callerBox);

        AddCallCallee.add(calleeBoxLabel);
        AddCallCallee.add(calleeBox);

        AddCallStart.add(startBoxLabel);
        AddCallStart.add(startTimeBox);

        AddCallEnd.add(endBoxLabel);
        AddCallEnd.add(endTimeBox);

        AddCallButton.add(addCallLabel);
        AddCallButton.add(addCall);

        AddCall.add(AddCallCustomer);
        AddCall.add(AddCallCaller);
        AddCall.add(AddCallCallee);
        AddCall.add(AddCallStart);
        AddCall.add(AddCallEnd);
        AddCall.add(AddCallButton);

        PrintCustomer.add(printCustomerLabel);
        PrintCustomer.add(printBillBox);

        PrintButton.add(printButtonLabel);
        PrintButton.add(printBillButton);

        printBill.add(PrintCustomer);
        printBill.add(PrintButton);

        SearchCustomer.add(searchCustomerLabel);
        SearchCustomer.add(searchCustomerBox);

        SearchStart.add(searchStartTimeLabel);
        SearchStart.add(searchStartTimeBox);

        SearchEnd.add(searchEndTimeLabel);
        SearchEnd.add(searchEndTimeBox);

        SearchButtonVert.add(searchButtonLabel);
        SearchButtonVert.add(searchButton);

        searchBill.add(SearchCustomer);
        searchBill.add(SearchStart);
        searchBill.add(SearchEnd);
        searchBill.add(SearchButtonVert);

        mainPanel.add(help);
        mainPanel.add(readMe);
        mainPanel.add(callTable);
        mainPanel.add(AddCall);
        mainPanel.add(printBill);
        mainPanel.add(searchBill);

        RootPanel rootPanel = RootPanel.get();
        rootPanel.add(mainPanel);

        help.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                Window.alert(help());
            }
        });

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

        searchButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                searchBill();
            }
        });
    }

    /**
     * Function that contains the readME document.
     * @return Returns a string to the client to print the readME.
     */
    public String readMe() {
        String readMe = "Tyler McKean - CS410J - Project 5 README. This project uses Google Web Toolkit to create a web application for adding" +
                        " and viewing phone bills and phone calls. You can add phone calls to phone bills and if a phone"
                        +" exists for a user that doesn't have a phone bill, a new one will be created. This app can also"
                        +" search through phone bills by the time calls were made, as well as print the phone bills.";
        return readMe;
    }

    /**
     * Function that contains the Help document.
     * @return Returns a string to the client to print the Help document.
     */
    public String help() {
        String help = "The fist component of this client is a table"
                + " that actively shows every call that has been added to various phone bills during the session. This table"
                + " should automatically update when a new call is added, but only maintains the order in which calls were"
                + " added.\nInformation in the table is presented left to right in this order: Customer, caller number,"
                + " callee number, date and time the call began, date and time the call ended. A customer name can be any"
                + " character or combination of characters (digits included), however the other pieces of data must follow a"
                + " specific format. The caller number and the callee number must be entered in the format ###-###-#### where"
                + " the # symbol represents a digit from 0-9, the phone numbers must also include the dashes. For any date, including"
                + " the dates for adding a call and searching, they must be entered in the form MM/DD/YYYY H:mm am/pm. The H represents"
                + " the hours in 12-hour time, so 1 and 12 work. AM and PM can be entered in either uppercase"
                + " or lowercase.\nNext there are five boxes following the same order with fields to "
                + " enter information and add a new call. Once all fields are filled in, simply press the add a call button to add it.\n"
                + "The next component of the app below the add call bar is the tool to search for a phone bill and print it out by"
                + " individual calls in a readable format. Simply enter in the customer name in the field and press the print button."
                + "\nFinally, the last feature on the bottom is to search through a specific phone bill and print calls falling between"
                + " a specific time. Simply enter the customer name, then the start date and time, and the end date and time and press"
                + " the button to print out all the calls for that phonebill falling between the specified times. If none exist, the client"
                + " will let you know.";
        return help;
    }

    /**
     * Function that adds a new call to a phone bill. If a phonebill exists for the given customer it will add it to it,
     * if not a new phonebill will be created. This also error checks the user input from the GUI.
     */
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

        /*
        pinger.ping(customer, newCall, new AsyncCallback<AbstractPhoneBill>() {

                    @Override
                    public void onFailure(Throwable throwable) {
                        Window.alert("Call not added!");
                    }

                    @Override
                    public void onSuccess(AbstractPhoneBill abstractPhoneBill) {
                        Window.alert("Call added!");
                    }
                });
        */

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

    /**
     * Function to print a phone bill. Simply searches through the map of phone bills based on what the customer
     * entered and returns the phone bill in a nicely printed format and also sorted.
     */
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

    /**
     * Function that prints a list of calls within the parameters set by the user. Checks the phone bill for any calls
     * that fall between the start and end time of the parameters and prints them out.
     */
    public void searchBill() {
        String customer = searchCustomerBox.getText();
        String startTime = searchStartTimeBox.getText();
        String endTime = searchEndTimeBox.getText();

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
        Date startSearch = DateTimeFormat.getFormat("MM/dd/yyyy h:mm a").parse(startTime);
        Date endSearch = DateTimeFormat.getFormat("MM/dd/yyyy h:mm a").parse(endTime);
        if(phoneBillHashMap.get(customer) != null) {
            Window.alert("Customer: " + customer + "\n" + phoneBillHashMap.get(customer).searchBillString(startSearch, endSearch));
        }
        else {
            Window.alert("There is not a phone bill stored for the customer you specified");
        }
        searchCustomerBox.setText("");
        searchStartTimeBox.setText("");
        searchEndTimeBox.setText("");
    }
}
