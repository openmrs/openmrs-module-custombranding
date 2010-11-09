/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.custombranding.extension.html;

import java.util.HashMap;
import java.util.Map;
import org.openmrs.module.web.extension.AdministrationSectionExt;


public class CustomBrandingExt extends AdministrationSectionExt {

    @Override
    public String getTitle() {
        return "Custom Branding Module";
    }

    @Override
    public Map<String, String> getLinks() {
        HashMap links = new HashMap();
        links.put("/module/custombranding/custombranding.form", "custombranding.link.name");
        
        return links;
    }
}
