package br.com.felipe.sorveteria.util;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("rest")
public class SorveteriaResourceConfig extends ResourceConfig{
	public SorveteriaResourceConfig() {
		packages("br.com.felipe.sorveteria.service");
	}
}
