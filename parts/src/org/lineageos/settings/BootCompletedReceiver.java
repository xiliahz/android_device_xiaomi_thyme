/*
 * Copyright (C) 2015 The CyanogenMod Project
 *               2017-2019 The LineageOS Project
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

package org.lineageos.settings;
import org.lineageos.settings.dirac.DiracUtils;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemProperties;
import android.util.Log;
import androidx.preference.PreferenceManager;
import org.lineageos.settings.refreshrate.RefreshUtils;
import org.lineageos.settings.doze.DozeUtils;
import org.lineageos.settings.utils.FileUtils;
import org.lineageos.settings.system.KernelCheck;

public class BootCompletedReceiver extends BroadcastReceiver {

    private static final boolean DEBUG = false;
    private static final String TAG = "XiaomiParts";
    private static final String DC_DIMMING_ENABLE_KEY = "dc_dimming_enable";
    private static final String DC_DIMMING_NODE = "/sys/class/drm/card0-DSI-1/disp_param";

    @Override
    public void onReceive(final Context context, Intent intent) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        if (DEBUG)
            Log.d(TAG, "Received boot completed intent");
        try {
            DiracUtils.getInstance(context);
        } catch (Exception e) {
            Log.d(TAG, "Dirac is not present in system");
        }
        DozeUtils.checkDozeService(context);
        KernelCheck.check(context);
        RefreshUtils.startService(context);

        boolean dcDimmingEnabled = sharedPrefs.getBoolean(DC_DIMMING_ENABLE_KEY, false);
        FileUtils.writeLine(DC_DIMMING_NODE, dcDimmingEnabled ? "0x40000" : "0x50000");
    }
}
