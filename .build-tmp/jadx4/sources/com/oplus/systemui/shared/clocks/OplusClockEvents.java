package com.oplus.systemui.shared.clocks;

import android.icu.util.TimeZone;
import com.android.systemui.plugins.keyguard.data.model.AlarmData;
import com.android.systemui.plugins.keyguard.data.model.WeatherData;
import com.android.systemui.plugins.keyguard.data.model.ZenData;
import com.android.systemui.plugins.keyguard.ui.clocks.ClockEvents;
import com.android.systemui.plugins.keyguard.ui.clocks.TimeFormatKind;
import java.util.Locale;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OplusClockEvents implements ClockEvents {
    public void onAlarmDataChanged(AlarmData alarmData) {
    }

    public final void onBatteryLevelChanged(int i) {
    }

    public void onFinishedGoingToSleep() {
    }

    public void onLocaleChanged(Locale locale) {
    }

    public final void onOSThemeChanged() {
    }

    public final void onScreenTurnedOn() {
    }

    public void onStartedGoingToSleep() {
    }

    public void onTimeFormatChanged(TimeFormatKind timeFormatKind) {
    }

    public void onTimeZoneChanged(TimeZone timeZone) {
    }

    public void onUserSwitchComplete(int i) {
    }

    public final void onUserSwitching(int i) {
    }

    public void onWeatherDataChanged(WeatherData weatherData) {
    }

    public void onZenDataChanged(ZenData zenData) {
    }
}
