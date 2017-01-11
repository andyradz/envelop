package com.codigo.aplios.envelop.system.core.xbase.markers;

import java.nio.charset.Charset;

enum XbCodePages {
	MAZOVIA(
			Charset.forName("620"));

	XbCodePages(Charset codePage) {
		this.codePage = codePage;
	}

	private Charset codePage;
}
