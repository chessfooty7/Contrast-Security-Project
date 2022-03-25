package com.metrics.myfilter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class ContentCaptureResponse extends HttpServletResponseWrapper {

private ByteArrayOutputStream contentBuffer;
private PrintWriter writer;

public ContentCaptureResponse(HttpServletResponse response) {
super(response);
}

@Override
public PrintWriter getWriter() throws IOException {
if (writer == null) {
writer = new PrintWriter(getContentBuffer());
}

return writer;
}

private ByteArrayOutputStream getContentBuffer() {
if (contentBuffer == null) {
contentBuffer = new ByteArrayOutputStream();
}

return contentBuffer;
}

public String getContent() throws IOException {
getWriter().flush();

return new String(getContentBuffer().toString());
}
}