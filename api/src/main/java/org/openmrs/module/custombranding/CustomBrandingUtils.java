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
package org.openmrs.module.custombranding;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import org.openmrs.util.OpenmrsConstants;
import org.springframework.web.servlet.support.RequestContextUtils;

public class CustomBrandingUtils {

    public static Properties getThemeProperties(HttpServletRequest request) {
        Properties themeProps = new Properties();
        File themeFile = new File(getThemePath(request));
        File defaultThemeFile = new File(getDefaultThemePath(request));
        if (themeFile.exists() && defaultThemeFile.exists()) {
            try {
                themeProps.load(new FileReader(defaultThemeFile));
                themeProps.load(new FileReader(themeFile));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return themeProps;
    }

    private static String getDefaultThemePath(HttpServletRequest request) {
        String theme = RequestContextUtils.getTheme(request).getName();
        String fileSysLocation = request.getRealPath("/") + "WEB-INF" + File.separator;
        String themeFilePath = "";
        if (versionGreater(OpenmrsConstants.OPENMRS_VERSION_SHORT, "1.6")) {
            themeFilePath = fileSysLocation + "classes" + File.separator + "themes" + File.separator + "defaults.properties";
        } else {
            themeFilePath = fileSysLocation + "classes" + File.separator + "standard_" + theme + ".properties";
        }
        return themeFilePath;
    }

    private static String getThemePath(HttpServletRequest request) {
        String theme = RequestContextUtils.getTheme(request).getName();
        String fileSysLocation = request.getRealPath("/") + "WEB-INF" + File.separator;
        String themeFilePath = "";
        if (versionGreater(OpenmrsConstants.OPENMRS_VERSION_SHORT, "1.6")) {
            if (theme.trim().equals("")) {
                theme = "defaults";
            }
            themeFilePath = fileSysLocation + "classes" + File.separator + "themes" + File.separator + theme + ".properties";
        } else {
            themeFilePath = fileSysLocation + "classes" + File.separator + "standard_" + theme + ".properties";
        }
        return themeFilePath;
    }

    private static boolean versionGreater(String v1, String v2) {
        boolean greater = false;
        String arr1[] = v1.split("\\.");
        String arr2[] = v2.split("\\.");
        for (int i = 0; i < arr2.length; i++) {
            int int1 = Integer.parseInt(arr1[i]);
            int int2 = Integer.parseInt(arr2[i]);
            if (int1 > int2) {
                greater = true;
                break;
            }
        }
        return greater;
    }
}
