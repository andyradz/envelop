package com.codigo.aplios.envelop.system.core.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VersionHistory implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4707219892858868420L;
	private Map<Date, OrderVersion> orderVersions = new HashMap<>();
	private final List<OrderVersion> orderHourMilestones = new ArrayList<OrderVersion>();

	public OrderVersion findVersion(Date date) {

		return orderVersions.get(date);
	}

	public void addOrderVersion(Date date, OrderVersion version) {

		orderVersions.put(date, version);
	}

	public void createHourMilestone() {

		
	}
}
