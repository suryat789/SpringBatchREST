package com.spring.batch.commons;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.listener.ItemListenerSupport;

public class BaseItemListener extends ItemListenerSupport<Object, Object> {

	private static final Logger LOG = LogManager.getLogger(BaseItemListener.class);
		
	//Called after ItemReader.read()
	public void afterRead(Object item) {
		LOG.debug("Read the Record: {}", item);
	}
	
	//Called before ItemProcessor.process(Object).
	public void beforeProcess(Object item) {
		LOG.debug("Beginning processing of a Record: {}", item);
	}	
	
	//Called after ItemProcessor.process(Object) returns.
	public void afterProcess(Object item, Object result) {
		LOG.debug("The processing output for a record: {}", result);
	}
	
	//Called before ItemWriter.write(java.util.List)
	public void beforeWrite(java.util.List<? extends Object> item) {
		LOG.info("Beginning writing of record(s) with size (Commit Interval): {}", item.size());
		LOG.debug("Beginning writing of record(s): {}", item);
	}
	
	//Called after ItemWriter.write(java.util.List) This will be called before any transaction is committed, and before ChunkListener.afterChunk(ChunkContext)
	public void afterWrite(java.util.List<? extends Object> item) {
		LOG.info("Finished writing of record(s) with size (Commit Interval): {}", item.size());
		LOG.debug("Finished writing of record(s): {}", item);
	}

	// #################### E R R O R    L O G G I N G #################### //
	//Called if an error occurs while trying to read.
	@Override
	public void onReadError(Exception e) {
		LOG.error("Error on Read: {}", e.getMessage());
		LOG.error("Error on Read details:", e);
	}
	
	//Called if an exception was thrown from ItemProcessor.process(Object).
	@Override
	public void onProcessError(Object item, Exception e) {
		LOG.error("Error on Process for a record: {} \nError: {}", item, e.getMessage());
		LOG.error("Error on Proces details:", e);
	}
	
	//Called if an error occurs while trying to write.
	@Override
	public void onWriteError(Exception e, List item) {
		LOG.error("Error on Write for a record: {} \nError: {}", item, e.getMessage());
		LOG.error("Error on Write details:", e);
	}
	// ###################################################################### //
}