package com.guogee.action;

import com.guogee.exception.BussinessException;
import com.opensymphony.xwork2.ActionSupport;

public class ShowAction extends ActionSupport{
	
	
	private static final long serialVersionUID = 1L;

	public String showMsg() throws Exception{
		System.out.println("enter showMsg");
		throw new BussinessException("define Exception");
	}
	
	
}
