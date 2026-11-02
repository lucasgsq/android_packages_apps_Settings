/*
 * Copyright (C) 2026 The LineageOS Project
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

package com.android.settings.deviceinfo.firmwareversion;

import android.content.Context;
import android.text.format.DateFormat;

import com.android.settings.R;
import com.android.settings.core.BasePreferenceController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/** Displays the audited backport batch reference separately from the official SPL. */
public class LineageSecurityBackportPreferenceController extends BasePreferenceController {

    public LineageSecurityBackportPreferenceController(Context context, String key) {
        super(context, key);
    }

    @Override
    public int getAvailabilityStatus() {
        return AVAILABLE;
    }

    @Override
    public CharSequence getSummary() {
        String month = mContext.getString(R.string.config_lineageSecurityBackportMonth);
        if (!month.matches("[0-9]{4}-(0[1-9]|1[0-2])")) {
            return mContext.getString(R.string.lineage_security_backport_unverified);
        }
        try {
            SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM", Locale.ROOT);
            parser.setLenient(false);
            Date date = parser.parse(month);
            String pattern = DateFormat.getBestDateTimePattern(Locale.getDefault(), "MMMMyyyy");
            String label = DateFormat.format(pattern, date).toString();
            return mContext.getString(R.string.lineage_security_backport_summary, label);
        } catch (ParseException e) {
            return mContext.getString(R.string.lineage_security_backport_unverified);
        }
    }
}
