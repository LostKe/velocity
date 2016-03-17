package com.guogee.action;

import com.opensymphony.xwork2.ActionSupport;

public class ShowAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String showMsg(){
		System.out.println("enter showMsg");
		int i=5/0;//出现异常
		return SUCCESS;
	}

}
