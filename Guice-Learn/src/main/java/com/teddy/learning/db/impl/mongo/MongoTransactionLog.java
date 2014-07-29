package com.teddy.learning.db.impl.mongo;

import java.util.Date;

import com.teddy.learning.db.TransactionLog;

public class MongoTransactionLog implements TransactionLog {

	public void logSomething(String methodName) {
		System.out.println(new Date() + " - MongoAuditTxnLog for [ " + methodName + " ]");
	}

}
