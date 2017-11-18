/*
 * Copyright (C) 2017 ABC ROM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.abc.settings;

import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceCategory;
import android.support.v7.preference.PreferenceScreen;

import com.android.internal.logging.nano.MetricsProto;

import com.android.settings.SettingsPreferenceFragment;

import com.android.settings.development.DevelopmentSettings;

public class AbcSettings extends SettingsPreferenceFragment {

    private PreferenceCategory mLedsCategory;
    private PreferenceCategory mDeviceCategory;
    private Preference mChargingLeds;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        final String KEY_DEVICE_PART = "device_part";
        final String KEY_DEVICE_PART_PACKAGE_NAME = "org.omnirom.device";
        addPreferencesFromResource(R.xml.abc_settings_main);
        PreferenceScreen prefSet = getPreferenceScreen();

        // DeviceParts
	mDeviceCategory = (PreferenceCategory) findPreference("abc_sys");
        if (!DevelopmentSettings.isPackageInstalled(getActivity(), KEY_DEVICE_PART_PACKAGE_NAME)) {
            mDeviceCategory.removePreference(findPreference(KEY_DEVICE_PART));
	}

        mLedsCategory = (PreferenceCategory) findPreference("abc_leds");
        mChargingLeds = (Preference) findPreference("abc_charging_light");
        if (mChargingLeds != null
                && !getResources().getBoolean(
                        com.android.internal.R.bool.config_intrusiveBatteryLed)) {
            mLedsCategory.removePreference(mChargingLeds);
        }
    }

    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.ABC;
    }
}

