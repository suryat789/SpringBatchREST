package com.spring.batch.commons;

import org.springframework.batch.core.SkipListener;

public class BaseSkipListener<T, S> implements SkipListener<T, S> {

	@Override
	public void onSkipInProcess(T arg0, Throwable arg1) {
		System.out.println("-------------------------------------------------------1 " + arg0 );
		System.out.println("-------------------------------------------------------2 " + arg1 );		
	}

	@Override
	public void onSkipInRead(Throwable arg0) {
		System.out.println("-------------------------------------------------------3 " + arg0 );
		
	}

	@Override
	public void onSkipInWrite(S arg0, Throwable arg1) {
		System.out.println("-------------------------------------------------------4 " + arg0 );
		System.out.println("-------------------------------------------------------5 " + arg1 );	
	}

}
