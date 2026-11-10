package com.oplus.systemui.shared.clocks;

import android.app.ActivityManager;
import android.app.UserSwitchObserver;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Process;
import android.provider.Settings;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.RemotableViewMethod;
import android.view.ViewDebug;
import android.view.ViewHierarchyEncoder;
import androidx.appcompat.widget.AppCompatTextView;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.Dependency;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.R;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.observer.ObserverDispatcher;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.Calendar;
import java.util.TimeZone;
import kotlin.Deprecated;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RedTextClock extends AppCompatTextView {
    public static final Companion Companion = new Companion();
    private static final CharSequence DEFAULT_FORMAT_12_HOUR = "h:mm a";
    private static final CharSequence DEFAULT_FORMAT_24_HOUR = "H:mm";
    public static final String NUMBER_ONE = "1";
    public static final String TAG = "RedTextClock";

    @ViewDebug.ExportedProperty
    private CharSequence format;
    private CharSequence mDescFormat;
    private CharSequence mDescFormat12;
    private CharSequence mDescFormat24;
    private CharSequence mFormat12;
    private CharSequence mFormat24;
    private ContentObserver mFormatChangeObserver;
    public boolean mHasSeconds;
    private final BroadcastReceiver mIntentReceiver;
    public boolean mRegistered;
    public boolean mShouldRunTicker;
    public boolean mShowCurrentUserTime;
    private final Runnable mTicker;
    private Calendar mTime;
    private String mTimeZone;
    private final RedTextClock$userSwitchObserver$1 userSwitchObserver;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        @Deprecated
        public static /* synthetic */ void getDEFAULT_FORMAT_12_HOUR$annotations() {
        }

        @Deprecated
        public static /* synthetic */ void getDEFAULT_FORMAT_24_HOUR$annotations() {
        }

        public final CharSequence getDEFAULT_FORMAT_12_HOUR() {
            return RedTextClock.DEFAULT_FORMAT_12_HOUR;
        }

        public final CharSequence getDEFAULT_FORMAT_24_HOUR() {
            return RedTextClock.DEFAULT_FORMAT_24_HOUR;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.oplus.systemui.shared.clocks.RedTextClock$userSwitchObserver$1] */
    public RedTextClock(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.userSwitchObserver = new UserSwitchObserver() { // from class: com.oplus.systemui.shared.clocks.RedTextClock$userSwitchObserver$1
            public void onUserSwitchComplete(int i2) {
                super.onUserSwitchComplete(i2);
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                    OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i2, "onUserSwitchComplete newUserId:", "RedTextClock-->", "SystemUi--Keyguard");
                }
                this.this$0.chooseFormat();
            }
        };
        this.mIntentReceiver = new BroadcastReceiver() { // from class: com.oplus.systemui.shared.clocks.RedTextClock$mIntentReceiver$1
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                if (this.this$0.mTimeZone == null && "android.intent.action.TIMEZONE_CHANGED".equals(intent.getAction())) {
                    this.this$0.createTime(intent.getStringExtra("time-zone"));
                }
                this.this$0.onTimeChanged();
            }
        };
        RedTextClock$mTicker$1 redTextClock$mTicker$1 = new RedTextClock$mTicker$1();
        redTextClock$mTicker$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mTicker = redTextClock$mTicker$1;
        KgdLogUtil.i(TAG, "constructor attrs: " + attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RedTextClock, i, 0);
        try {
            CharSequence text = typedArrayObtainStyledAttributes.getText(0);
            this.mFormat12 = text == null ? context.getString(2131952649) : text;
            CharSequence text2 = typedArrayObtainStyledAttributes.getText(1);
            this.mFormat24 = text2 == null ? context.getString(2131952650) : text2;
            this.mTimeZone = typedArrayObtainStyledAttributes.getString(2);
            typedArrayObtainStyledAttributes.recycle();
            KgdLogUtil.i(TAG, "init mFormat12: " + ((Object) this.mFormat12) + ", mFormat24: " + ((Object) this.mFormat24));
            CharSequence charSequence = this.mFormat12;
            if (charSequence == null || this.mFormat24 == null) {
                if (charSequence == null) {
                    this.mFormat12 = getContext().getString(2131952649);
                }
                if (this.mFormat24 == null) {
                    this.mFormat24 = getContext().getString(2131952650);
                }
            }
            createTime(this.mTimeZone);
            chooseFormat();
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void chooseFormat() {
        if (this.mShowCurrentUserTime ? DateFormat.is24HourFormat(getContext(), KeyguardUpdateMonitor.getCurrentUser()) : DateFormat.is24HourFormat(getContext())) {
            Companion companion = Companion;
            CharSequence charSequence = this.mFormat24;
            CharSequence charSequence2 = this.mFormat12;
            companion.getClass();
            if (charSequence == null) {
                charSequence = charSequence2;
            }
            this.format = charSequence;
            CharSequence charSequence3 = this.mDescFormat24;
            CharSequence charSequence4 = this.mDescFormat12;
            if (charSequence3 == null) {
                charSequence3 = charSequence4;
            }
            this.mDescFormat = charSequence3;
        } else {
            Companion companion2 = Companion;
            CharSequence charSequence5 = this.mFormat12;
            CharSequence charSequence6 = this.mFormat24;
            companion2.getClass();
            if (charSequence5 == null) {
                charSequence5 = charSequence6;
            }
            this.format = charSequence5;
            CharSequence charSequence7 = this.mDescFormat12;
            CharSequence charSequence8 = this.mDescFormat24;
            if (charSequence7 == null) {
                charSequence7 = charSequence8;
            }
            this.mDescFormat = charSequence7;
        }
        boolean z = this.mHasSeconds;
        boolean zHasSeconds = DateFormat.hasSeconds(this.format);
        this.mHasSeconds = zHasSeconds;
        if (this.mShouldRunTicker && z != zHasSeconds) {
            if (z) {
                getHandler().removeCallbacks(this.mTicker);
            } else {
                this.mTicker.run();
            }
        }
        KgdLogUtil.i(TAG, "chooseFormat format result: " + ((Object) this.format) + ", mFormat24: " + ((Object) this.mFormat24) + ", mFormat12: " + ((Object) this.mFormat12));
    }

    public final void createTime(String str) {
        this.mTime = str != null ? Calendar.getInstance(TimeZone.getTimeZone(str)) : Calendar.getInstance();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void encodeProperties(ViewHierarchyEncoder viewHierarchyEncoder) {
        super/*android.widget.TextView*/.encodeProperties(viewHierarchyEncoder);
        CharSequence format12Hour = getFormat12Hour();
        viewHierarchyEncoder.addProperty("format12Hour", format12Hour != null ? format12Hour.toString() : null);
        CharSequence format24Hour = getFormat24Hour();
        viewHierarchyEncoder.addProperty("format24Hour", format24Hour != null ? format24Hour.toString() : null);
        CharSequence charSequence = this.format;
        viewHierarchyEncoder.addProperty("format", charSequence != null ? String.valueOf(charSequence) : null);
        viewHierarchyEncoder.addProperty("hasSeconds", this.mHasSeconds);
    }

    public final CharSequence getFormat() {
        return this.format;
    }

    @ViewDebug.ExportedProperty
    public final CharSequence getFormat12Hour() {
        return this.mFormat12;
    }

    @ViewDebug.ExportedProperty
    public final CharSequence getFormat24Hour() {
        return this.mFormat24;
    }

    public final String getTimeZone() {
        return this.mTimeZone;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onAttachedToWindow() {
        super/*android.widget.TextView*/.onAttachedToWindow();
        if (this.mRegistered) {
            return;
        }
        this.mRegistered = true;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.TIME_TICK");
        intentFilter.addAction("android.intent.action.TIME_SET");
        intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
        BroadcastDispatcher.registerReceiverWithHandler$default((BroadcastDispatcher) Dependency.sDependency.getDependencyInner(BroadcastDispatcher.class), this.mIntentReceiver, intentFilter, getHandler(), Process.myUserHandle(), 0, 48);
        registerObserver();
        ActivityManager.getService().registerUserSwitchObserver(this.userSwitchObserver, TAG);
        createTime(this.mTimeZone);
    }

    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mRegistered) {
            ((BroadcastDispatcher) Dependency.sDependency.getDependencyInner(BroadcastDispatcher.class)).unregisterReceiver(this.mIntentReceiver);
            if (this.mFormatChangeObserver != null) {
                ObserverDispatcher.getInstance().unregisterObserver(this.mFormatChangeObserver);
            }
            this.mRegistered = false;
            ActivityManager.getService().unregisterUserSwitchObserver(this.userSwitchObserver);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onTimeChanged() {
        Calendar calendar;
        CharSequence charSequence;
        CharSequence charSequence2 = this.format;
        KgdLogUtil.i(TAG, "onTimeChanged format: " + ((Object) charSequence2) + ", mTime: " + this.mTime + ", mDescFormat: " + ((Object) this.mDescFormat));
        if (this.format == null || (calendar = this.mTime) == null || !this.mShouldRunTicker) {
            return;
        }
        calendar.setTimeInMillis(System.currentTimeMillis());
        CharSequence charSequence3 = this.format;
        String string = null;
        if (charSequence3 != null && (charSequence = DateFormat.format(charSequence3, this.mTime)) != null) {
            string = charSequence.toString();
        }
        setText(KeyguardUtils.Companion.getSpannedHourString(getContext(), string));
        CharSequence charSequence4 = this.mDescFormat;
        if (charSequence4 != null) {
            setContentDescription(DateFormat.format(charSequence4, this.mTime));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onVisibilityAggregated(boolean z) {
        super/*android.widget.TextView*/.onVisibilityAggregated(z);
        boolean z2 = this.mShouldRunTicker;
        if (z2 || !z) {
            if (!z2 || z) {
                return;
            }
            this.mShouldRunTicker = false;
            getHandler().removeCallbacks(this.mTicker);
            return;
        }
        this.mShouldRunTicker = true;
        if (this.mHasSeconds) {
            this.mTicker.run();
        } else {
            onTimeChanged();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void registerObserver() {
        if (this.mRegistered) {
            if (this.mFormatChangeObserver == null) {
                this.mFormatChangeObserver = new FormatChangeObserver(this, getHandler());
            }
            Uri uriFor = Settings.System.getUriFor("time_12_24");
            if (this.mShowCurrentUserTime) {
                ObserverDispatcher.getInstance().registerObserver(uriFor, this.mFormatChangeObserver, -1);
            } else {
                ObserverDispatcher.getInstance().registerObserver(uriFor, this.mFormatChangeObserver);
            }
        }
    }

    public final void setContentDescriptionFormat12Hour(CharSequence charSequence) {
        this.mDescFormat12 = charSequence;
        chooseFormat();
        onTimeChanged();
    }

    public final void setContentDescriptionFormat24Hour(CharSequence charSequence) {
        this.mDescFormat24 = charSequence;
        chooseFormat();
        onTimeChanged();
    }

    @RemotableViewMethod
    public final void setFormat12Hour(CharSequence charSequence) {
        this.mFormat12 = charSequence;
        chooseFormat();
        onTimeChanged();
    }

    @RemotableViewMethod
    public final void setFormat24Hour(CharSequence charSequence) {
        this.mFormat24 = charSequence;
        chooseFormat();
        onTimeChanged();
    }

    public final void setShowCurrentUserTime(boolean z) {
        this.mShowCurrentUserTime = z;
        chooseFormat();
        onTimeChanged();
        if (this.mFormatChangeObserver != null) {
            ObserverDispatcher.getInstance().unregisterObserver(this.mFormatChangeObserver);
        }
        registerObserver();
    }

    @RemotableViewMethod
    public final void setTimeZone(String str) {
        this.mTimeZone = str;
        createTime(str);
        onTimeChanged();
    }

    public /* synthetic */ RedTextClock(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public RedTextClock(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }
}
