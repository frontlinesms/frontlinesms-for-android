/**
 * 
 */
package com.alxndrsn.android.utils;

import android.util.Log;

/**
 * @author aga
 */
public class Logger {
	private static final boolean SYSOUT = true;
	
	private static final String DEBUG = "DEBUG";
	private static final String INFO = "INFO";
	private static final String WARN = "WARN";
	private static final String ERROR = "ERROR";
	
	private String tag;
	
//> CONSTRUCTOR
	private Logger(String tag) {
		this.tag = tag;
	}
	
//> INSTANCE METHODS
	public void info(String message) {
		sout(INFO, message);
		Log.i(this.tag, message);
	}
	public void warn(String message) {
		sout(WARN, message);
		Log.w(this.tag, message);
	}
	public void trace(String message) {
		sout(DEBUG, message);
		Log.d(this.tag, message);
	}
	public void error(String message, Throwable t) {
		sout(ERROR, message, t);
		Log.e(this.tag, message, t);
	}
	
	private void sout(String level, String message) {
		if(SYSOUT) System.out.println("[" + level + "] " + this.tag + " : " + message);
	}
	private void sout(String level, String message, Throwable t) {
		sout(level, message);
		if(SYSOUT) t.printStackTrace();
	}
	
//> STATIC FACTORIES
	public static Logger getLogger(Object object) {
		return getLogger(object.getClass());
	}
	public static Logger getLogger(Class<?> clazz) {
		return new Logger(clazz.getSimpleName());
	}
}
