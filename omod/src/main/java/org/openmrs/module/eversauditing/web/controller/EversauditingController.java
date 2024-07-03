/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.eversauditing.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Encounter;
import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.module.eversauditing.AuditEntity;
import org.openmrs.module.eversauditing.api.AuditService;
import org.openmrs.module.eversauditing.api.EversauditingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * This class configured as controller using annotation and mapped with the URL of
 * 'module/${rootArtifactid}/${rootArtifactid}Link.form'.
 */
@Controller
@RequestMapping(value = "module/enversauditing/enversauditing.form")
public class EversauditingController {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	private final String VIEW = "/module/enversauditing/enversauditing";
	
	private final AuditService auditService;
	
	public EversauditingController(AuditService auditService) {
		this.auditService = auditService;
	}
	
	/**
	 * Initially called after the getUsers method to get the landing form name
	 * 
	 * @return String form view name
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String onGet() {
		return VIEW;
	}
	
	@ModelAttribute("classes")
	protected List<String> getClasses() throws Exception {
		List<String> classes = new ArrayList<>();
		classes.add(Patient.class.getName());
		classes.add(Person.class.getName());
		classes.add(Encounter.class.getName());
		return classes;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String showClassFormAndAudits(@RequestParam(value = "selectedClass", required = false) String className,
	        Model model) {
		if (className != null && !className.isEmpty()) {
			model.addAttribute("audits", auditService.getAllRevisions(className));
			model.addAttribute("currentClass", className);
		}
		return VIEW;
	}
}
