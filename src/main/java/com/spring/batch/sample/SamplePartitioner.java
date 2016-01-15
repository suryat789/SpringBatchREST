package com.spring.batch.sample;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class SamplePartitioner extends JdbcTemplate implements Partitioner {
	
	private static final Logger LOG = LogManager.getLogger(SamplePartitioner.class);

	public Map<String, ExecutionContext> partition(int gridSize) {
		
		System.out.println("SamplePartitioner");
		
		if(LOG.isDebugEnabled()){
			LOG.debug("START: Partition");
		}

		Map<String, ExecutionContext> partitionMap = new HashMap<String, ExecutionContext>();

		int min = queryForObject("SELECT MIN(id) from Person", Integer.class);
		int max = queryForObject("SELECT MAX(id) from Person", Integer.class);
		
		int targetSize = (max - min + 1) / gridSize;
		int number = 0;
		int start = min;
		int end = start + targetSize - 1;

		while (start <= max) {
			ExecutionContext value = new ExecutionContext();
			partitionMap.put("partition" + number, value);
			if (end >= max) {
				end = max;
			}
			value.putInt("startId", start);
			value.putInt("endId", end);
			
			LOG.debug("partition{} \t-\t" + "startId: {} \t endId: {}",number, start, end);
			
			start += targetSize;
			end += targetSize;
			number++;
		}
		
		LOG.debug("Min: {} \t Max: {}" , min, max);
		LOG.debug("END: Created Partitions of size: {}", partitionMap.size());
		return partitionMap;
	}
	
}
