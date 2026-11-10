package com.oplusos.systemui.common.utils;

import android.content.Context;
import android.service.notification.ZenModeConfig;
import android.text.format.DateFormat;
import java.lang.invoke.VarHandle;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.stream.IntStream;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DndInfoUtil {
    public static final Object LOCK_OBJECT = new Object();
    public static volatile DndInfoUtil sInstance;
    public String mAutomaticRuleTitle;
    public long mEndTime;
    public boolean mIsAutomaticActive;

    public static String getFormattedTime12or24(Context context, long j, boolean z) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        boolean zIs24HourFormat = DateFormat.is24HourFormat(context);
        Locale locale = Locale.getDefault();
        if (z) {
            return new SimpleDateFormat(DateFormat.getBestDateTimePattern(locale, zIs24HourFormat ? "MMMdHm" : "MMMdhma"), locale).format(calendar.getTime());
        }
        return new SimpleDateFormat(DateFormat.getBestDateTimePattern(locale, zIs24HourFormat ? "Hm" : "hma"), locale).format(calendar.getTime());
    }

    public static final DndInfoUtil getInstance() {
        DndInfoUtil dndInfoUtil;
        if (sInstance != null) {
            return sInstance;
        }
        synchronized (LOCK_OBJECT) {
            try {
                if (sInstance == null) {
                    sInstance = new DndInfoUtil();
                }
                dndInfoUtil = sInstance;
            } catch (Throwable th) {
                throw th;
            }
        }
        return dndInfoUtil;
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0080  */
    /* JADX WARN: Code duplicated, block: B:25:0x0083  */
    /* JADX WARN: Code duplicated, block: B:28:0x00a1 A[LOOP:1: B:24:0x0081->B:28:0x00a1, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:62:0x00a5 A[EDGE_INSN: B:62:0x00a5->B:29:0x00a5 BREAK  A[LOOP:1: B:24:0x0081->B:28:0x00a1], SYNTHETIC] */
    public static ValidRuleInfo getValidRule(ZenModeConfig zenModeConfig) {
        ZenModeConfig.ScheduleInfo scheduleInfoTryParseScheduleConditionId;
        int i;
        IntStream intStreamOf;
        DndInfoUtil$.ExternalSyntheticLambda0 externalSyntheticLambda0;
        if (zenModeConfig != null) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            long j = Long.MAX_VALUE;
            long j2 = Long.MIN_VALUE;
            ZenModeConfig.ZenRule zenRule = null;
            for (ZenModeConfig.ZenRule zenRule2 : zenModeConfig.automaticRules.values()) {
                if (!zenRule2.isActive()) {
                    if (zenRule2.enabled && (scheduleInfoTryParseScheduleConditionId = ZenModeConfig.tryParseScheduleConditionId(zenRule2.conditionId)) != null && scheduleInfoTryParseScheduleConditionId.days != null) {
                        Calendar calendar = Calendar.getInstance();
                        int[] iArr = scheduleInfoTryParseScheduleConditionId.days;
                        int i2 = scheduleInfoTryParseScheduleConditionId.startHour;
                        int i3 = scheduleInfoTryParseScheduleConditionId.startMinute;
                        Calendar calendar2 = (Calendar) calendar.clone();
                        calendar2.set(11, i2);
                        calendar2.set(12, i3);
                        int i4 = calendar2.get(7);
                        if (calendar2.after(calendar)) {
                            IntStream intStreamOf2 = IntStream.of(iArr);
                            DndInfoUtil$.ExternalSyntheticLambda0 externalSyntheticLambda1 = new DndInfoUtil$.ExternalSyntheticLambda0();
                            externalSyntheticLambda1.f$0 = i4;
                            VarHandle.storeStoreFence();
                            if (!intStreamOf2.anyMatch(externalSyntheticLambda1)) {
                                i = 1;
                                for (int i5 = 7; i <= i5; i5 = 7) {
                                    calendar2.add(5, 1);
                                    int i6 = calendar2.get(i5);
                                    intStreamOf = IntStream.of(iArr);
                                    externalSyntheticLambda0 = new DndInfoUtil$.ExternalSyntheticLambda0();
                                    externalSyntheticLambda0.f$0 = i6;
                                    VarHandle.storeStoreFence();
                                    if (intStreamOf.anyMatch(externalSyntheticLambda0)) {
                                        break;
                                    }
                                    i++;
                                }
                            }
                        } else {
                            i = 1;
                            while (i <= i5) {
                                calendar2.add(5, 1);
                                int i7 = calendar2.get(i5);
                                intStreamOf = IntStream.of(iArr);
                                externalSyntheticLambda0 = new DndInfoUtil$.ExternalSyntheticLambda0();
                                externalSyntheticLambda0.f$0 = i7;
                                VarHandle.storeStoreFence();
                                if (intStreamOf.anyMatch(externalSyntheticLambda0)) {
                                    break;
                                    break;
                                }
                                i++;
                            }
                        }
                        Calendar calendar3 = (Calendar) calendar2.clone();
                        calendar3.set(11, scheduleInfoTryParseScheduleConditionId.endHour);
                        calendar3.set(12, scheduleInfoTryParseScheduleConditionId.endMinute);
                        if (calendar3.before(calendar2)) {
                            calendar3.add(5, 1);
                        }
                        long timeInMillis = calendar2.getTimeInMillis();
                        long timeInMillis2 = calendar3.getTimeInMillis();
                        if (timeInMillis > jCurrentTimeMillis && (timeInMillis < j || (timeInMillis == j && timeInMillis2 > j2))) {
                            zenRule = zenRule2;
                            j = timeInMillis;
                            j2 = timeInMillis2;
                        }
                    }
                }
            }
            if (zenRule == null) {
                return null;
            }
            String str = zenRule.name;
            ValidRuleInfo validRuleInfo = new ValidRuleInfo();
            validRuleInfo.name = str;
            validRuleInfo.endTime = j2;
            return validRuleInfo;
        }
        return null;
    }

    public String getAutomaticRuleTitle() {
        return this.mAutomaticRuleTitle;
    }

    public final String getFormatEndTime(Context context, long j) {
        if ((j >= 0 ? j : this.mEndTime) <= 0) {
            return "";
        }
        boolean zIs24HourFormat = DateFormat.is24HourFormat(context);
        Locale locale = Locale.getDefault();
        if (ZenModeConfig.isToday(j)) {
            return new SimpleDateFormat(DateFormat.getBestDateTimePattern(locale, zIs24HourFormat ? "Hm" : "hma"), locale).format(Long.valueOf(j));
        }
        return new SimpleDateFormat(DateFormat.getBestDateTimePattern(locale, zIs24HourFormat ? "EHm" : "Ehma"), locale).format(Long.valueOf(j));
    }
}
