package com.teddy.learning.db.impl.mysql;

import java.util.Date;

import com.teddy.learning.db.TransactionLog;

public class MySQLTransactionLog implements TransactionLog {

	public void logSomething(String methodName) {
		System.out.println(new Date() + " - MySQLTxnLog for [ " + methodName + " ]");
	}

}
