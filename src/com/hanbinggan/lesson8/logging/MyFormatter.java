package com.hanbinggan.lesson8.logging;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class MyFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        StringBuilder builder = new StringBuilder();
        builder.append("[" + record.getLevel() + "] - ");
        builder.append(new Date(record.getMillis()) + " : ");
        builder.append(record.getSourceClassName() + "." + record.getSourceMethodName() + " : ");
        builder.append(record.getMessage() + "\n");
        return builder.toString();
    }
}
