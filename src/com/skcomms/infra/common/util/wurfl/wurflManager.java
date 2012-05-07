package com.skcomms.infra.common.util.wurfl;

import javax.servlet.http.HttpServletRequest;

import net.sourceforge.wurfl.core.DefaultDeviceProvider;
import net.sourceforge.wurfl.core.DefaultWURFLManager;
import net.sourceforge.wurfl.core.DefaultWURFLService;
import net.sourceforge.wurfl.core.WURFLManager;
import net.sourceforge.wurfl.core.matchers.MatcherManager;
import net.sourceforge.wurfl.core.resource.DefaultWURFLModel;
import net.sourceforge.wurfl.core.resource.XMLResource;

public class wurflManager {

	private static String file = "WEB-INF/wurfl-2.3.1.xml";
	
	public WURFLManager getWurflManager(HttpServletRequest request) {
		return this.createWURFLManager(request);
	}
	
	private WURFLManager createWURFLManager(HttpServletRequest request) {
		DefaultWURFLModel model = new DefaultWURFLModel(getRoot(request));
		MatcherManager matcherManager = new MatcherManager(model);
		DefaultDeviceProvider deviceProvider = new DefaultDeviceProvider(model);
		DefaultWURFLService service = new DefaultWURFLService(matcherManager, deviceProvider);
		return new DefaultWURFLManager(service);		
	}
	
	private XMLResource getRoot(HttpServletRequest request) {
		return new XMLResource(request.getServletContext().getRealPath(file));
	}
}
