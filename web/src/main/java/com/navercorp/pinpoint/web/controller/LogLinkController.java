package com.navercorp.pinpoint.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author hgc
 */
@Controller
public class LogLinkController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * link to graylog
	 * @param transactionId
	 * @param spanId
	 * @param time
	 * @return
	 */
	@RequestMapping(value = "/logLink")
	public String logLinkForTransactionId(@RequestParam(value = "transactionId", required = true) String transactionId,
			@RequestParam(value = "spanId", required = false) String spanId,
			@RequestParam(value = "time", required = true) long time) {

		String qparam = "txid%3A" + transactionId.replaceAll("\\^", "%5C%5E");
		String redirect_grayLog = "http://graylog.18join.com/search?rangetype=relative&relative=86400&q=" + qparam;

		long dur = System.currentTimeMillis() - time;
		long mins = dur / (60 * 1000);
		if (mins <= 0) {
			mins = 1000 * 60 * 60;
		}
		logger.info(redirect_grayLog);
		return "redirect:" + redirect_grayLog;

	}

}