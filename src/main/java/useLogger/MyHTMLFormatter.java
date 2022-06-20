package useLogger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * It's a formatter that formats HTML
 */
class MyHTMLFormatter extends Formatter {
    // this method is called for every log records
    /**
     * If the log level is greater than or equal to WARNING, then the log level is displayed in red
     *
     * @param rec The log record to be formatted.
     * @return A string that is formatted to be displayed in the HTML page.
     */
    @Override
    public String format(LogRecord rec) {
        StringBuffer buf = new StringBuffer(1000);
        // colorize any levels >= WARNING in red
        buf.append("<div class=\"card\">");
        if (rec.getLevel().intValue() >= Level.WARNING.intValue()) {
            buf.append("\t<header style=\"color:red\">");
            buf.append("<b>");
            buf.append(rec.getLevel());
            buf.append("</b>");
        } else {
            buf.append("\t<header>");
            buf.append(rec.getLevel());
        }
        buf.append("</header>\n");
        buf.append("\t<span>");
        buf.append("Message: " + formatMessage(rec));
        buf.append("</span>\n");
        buf.append("\t<footer style=\"color:blue\">");
        buf.append("Time: " + calcDate(rec.getMillis()));
        buf.append("</footer>\n");
        buf.append("</div>\n");
        buf.append("<br>");
        return buf.toString();
    }

    /**
     * It takes a long millisecs value and returns a String formatted as MM/dd, yyyy HH:mm
     *
     * @param millisecs The time in milliseconds
     * @return The date in the format MM/dd, yyyy HH:mm
     */
    private String calcDate(long millisecs) {
        SimpleDateFormat date_format = new SimpleDateFormat("MM/dd, yyyy HH:mm");
        Date resultdate = new Date(millisecs);
        return date_format.format(resultdate);
    }

    // this method is called just after the handler using this
    // formatter is created
    /**
     * It returns the header of the HTML page.
     *
     * @param h The handler that is being used to process the request.
     * @return The HTML code for the header of the page.
     */
    @Override
    public String getHead(Handler h) {
        return "<!DOCTYPE html>\n<head>\n"
                + "<style>"
                + ".card {\n" +
                "            border: solid 1px black;\n" +
                "            margin: 5px 5px 5px 5px;\n" +
                "        }"
                + "</style>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>" + (new Date()) + "</h1>\n"
                + "<section border=\"1\" >\n";
    }

    // this method is called just after the handler using this
    // formatter is closed
    /**
     * The `getTail` function returns the HTML code that will be appended to the end of the log file
     *
     * @param h The handler that is calling the formatter.
     * @return The tail of the HTML document.
     */
    @Override
    public String getTail(Handler h) {
        return "</section>\n</body>\n</html>";
    }
}