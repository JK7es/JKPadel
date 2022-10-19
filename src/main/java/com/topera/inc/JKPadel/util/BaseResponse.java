package com.topera.inc.JKPadel.util;

public class BaseResponse {
	
    private boolean ok;
    
    private String responseMessage;

    public boolean isOk() {
        return ok;
    }
    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
