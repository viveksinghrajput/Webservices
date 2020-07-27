/*
*ERPException.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Jul 4, 2018
*/
package com.rural.exceptions;

public class ERPException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 private String exceptionMsg;
	 public ERPException() {
	        super();
	    }
	 public ERPException(String exceptionMsg) {
	  this.exceptionMsg = exceptionMsg;
	 }
	 
    public ERPException(String s, Throwable throwable) {
        super(s, throwable);
    }
    public ERPException(Throwable throwable) {
        super(throwable);
    }
    public String getExceptionMsg(){
  	  return this.exceptionMsg;
  	 }
  	 
  	 public void setExceptionMsg(String exceptionMsg) {
  	  this.exceptionMsg = exceptionMsg;
  	 }
  	
    
}
