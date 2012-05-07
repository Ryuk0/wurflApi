package com.skcomms.infra.controller.wurflApi;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sourceforge.wurfl.core.Device;
import net.sourceforge.wurfl.core.WURFLManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skcomms.infra.common.util.wurfl.wurflManager;
import com.skcomms.infra.common.util.wurfl.wurflXMLWriter;

public class wurflApiController {
	
	private @Autowired HttpServletRequest request;
	private static WURFLManager wurfl = null;
	private static Device device = null;

	private wurflManager wurflmanager;
	
	private wurflXMLWriter wurflxmlwriter;
	
	@RequestMapping(value="/wurflApi.cy")
	public ModelAndView wurfl(@RequestParam(value="ua", required = false) String ua) {
				
		String temp = null;
		
		if (null == wurfl) {
			wurflmanager = new wurflManager();
			wurfl = wurflmanager.getWurflManager(request);
		}
		
		if (null != ua && ua.length() > 0) {
			device = wurfl.getDeviceForRequest(ua);
		} else {
			device = wurfl.getDeviceForRequest(request);
		}

		Map<String, String> deviceInfo = device.getCapabilities();
		
		deviceInfo.put("user_agent", device.getUserAgent());
		
		wurflxmlwriter = new wurflXMLWriter();
		temp = wurflxmlwriter.createXMLDocument(deviceInfo, "CYWORLD", "WURFL");
		
		return new ModelAndView("wurfl-xml", "deviceInfo", temp);
	}
}
