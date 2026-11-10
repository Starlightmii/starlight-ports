package com.oplus.systemui.shared.clocks;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.OplusBaseConfigurationAddon;
import android.database.ContentObserver;
import android.graphics.Typeface;
import android.icu.util.TimeZone;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.keyguard.OplusKeyguardDependencyEx;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.authentication.shared.model.AuthenticationResultModel$;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.OlockStateUtil$;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.keyguard.clock.ClockSwitchHelper;
import com.oplus.systemui.keyguard.clock.ImageClockHelper;
import com.oplus.systemui.keyguard.clock.LunarHelper;
import com.oplus.systemui.keyguard.clock.WeatherInfoParseHelper;
import com.oplus.systemui.keyguard.view.ClockExtraTextView;
import com.oplus.systemui.keyguard.wallpaper.ShadowController;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.FlavorTwoFeatureOption;
import com.oplusos.systemui.common.observer.ObserverDispatcher;
import com.oplusos.systemui.common.typeface.SystemTypefaceHelper;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.DeviceUtil;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;
import java.lang.invoke.VarHandle;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import oplus.content.res.OplusFontUtils;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class SingleClockView extends LinearLayout implements ConfigurationController.ConfigurationListener {
    private static final String AM_PM_FORMAT = "a";
    private static final String CLOCK_SYMBOL_STR = ":";
    public static final Companion Companion = new Companion();
    private static final String HOUR_FORMAT_12 = "h";
    private static final String HOUR_FORMAT_24 = "H";
    private static final String KEY_IS_SHOW_LUNAR_CALENDAR = "key_is_show_lunar_calendar";
    private static final String MINUTE_FORMAT = "mm";
    private static final String TAG = "SingleClockView";
    public boolean canShowLunarCalendar;
    public boolean isShowLunarCalendarSwitchOpen;
    private LogBuffer logBuffer;
    private String mAmpm;
    private Calendar mCalendar;
    private ClockExtraTextView mChargeBig;
    private ClockExtraTextView mChargeNormal;
    public int mClockMode;
    private TextView mClockSymbol;
    private LinearLayout mClockTimeContainer;
    private RelativeLayout mDataChargeRelativeLayout;
    private TextView mDate;
    private LinearLayout mDateChargeContainer;
    private String mFormat;
    private String mHour;
    public boolean mIs24HourFormat;
    public int mLastFont;
    public boolean mLastLandLockEnable;
    public int mLastLayoutDirection;
    private String mLastLocale;
    private String mMinutes;
    public int mOrientation;
    private final StatusBarStateController mStatusBarStateController;
    private String mSymbol;
    private TextView mTimeAmPm;
    private TextView mTimeHour;
    private TextView mTimeMinute;
    private TimeZone mTimeZone;
    private TextView mTvExtraContent;
    private final ContentObserver showLunarObserver;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public SingleClockView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mSymbol = CLOCK_SYMBOL_STR;
        this.mOrientation = -1;
        final Handler handler = new Handler();
        this.showLunarObserver = new ContentObserver(handler) { // from class: com.oplus.systemui.shared.clocks.SingleClockView$showLunarObserver$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                super.onChange(z);
                SingleClockView singleClockView = this.this$0;
                singleClockView.isShowLunarCalendarSwitchOpen = TextUtils.equals("true", Settings.System.getStringForUser(((LinearLayout) singleClockView).mContext.getContentResolver(), "key_is_show_lunar_calendar", -2));
                SingleClockView singleClockView2 = this.this$0;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                    OplusLockIconView$.ExternalSyntheticOutline0.m("mShowLunarObserver onChange:", "SingleClockView-->", "SystemUi--Keyguard", singleClockView2.isShowLunarCalendarSwitchOpen);
                }
                this.this$0.updateDate();
            }
        };
        Context contextCreateConfigurationContext = ((LinearLayout) this).mContext.createConfigurationContext(((LinearLayout) this).mContext.getResources().getConfiguration());
        ((LinearLayout) this).mContext = contextCreateConfigurationContext;
        this.mClockMode = contextCreateConfigurationContext.getApplicationContext().getResources().getInteger(2131427516);
        this.mLastLocale = ((LinearLayout) this).mContext.getApplicationContext().getResources().getConfiguration().getLocales().toLanguageTags();
        this.mLastFont = OplusBaseConfigurationAddon.getFlipFont(getResources().getConfiguration());
        this.mStatusBarStateController = (StatusBarStateController) Dependency.sDependency.getDependencyInner(StatusBarStateController.class);
    }

    public final LogBuffer getLogBuffer() {
        return this.logBuffer;
    }

    public final String getMAmpm() {
        return this.mAmpm;
    }

    public final Calendar getMCalendar() {
        return this.mCalendar;
    }

    public final ClockExtraTextView getMChargeBig() {
        return this.mChargeBig;
    }

    public final ClockExtraTextView getMChargeNormal() {
        return this.mChargeNormal;
    }

    public final int getMClockMode() {
        return this.mClockMode;
    }

    public final TextView getMClockSymbol() {
        return this.mClockSymbol;
    }

    public final LinearLayout getMClockTimeContainer() {
        return this.mClockTimeContainer;
    }

    public final RelativeLayout getMDataChargeRelativeLayout() {
        return this.mDataChargeRelativeLayout;
    }

    public final TextView getMDate() {
        return this.mDate;
    }

    public final LinearLayout getMDateChargeContainer() {
        return this.mDateChargeContainer;
    }

    public final String getMFormat() {
        return this.mFormat;
    }

    public final String getMHour() {
        return this.mHour;
    }

    public final boolean getMIs24HourFormat() {
        return this.mIs24HourFormat;
    }

    public final int getMLastFont() {
        return this.mLastFont;
    }

    public final boolean getMLastLandLockEnable() {
        return this.mLastLandLockEnable;
    }

    public final int getMLastLayoutDirection() {
        return this.mLastLayoutDirection;
    }

    public final String getMLastLocale() {
        return this.mLastLocale;
    }

    public final String getMMinutes() {
        return this.mMinutes;
    }

    public final int getMOrientation() {
        return this.mOrientation;
    }

    public final StatusBarStateController getMStatusBarStateController() {
        return this.mStatusBarStateController;
    }

    public final TextView getMTimeAmPm() {
        return this.mTimeAmPm;
    }

    public final TextView getMTimeHour() {
        return this.mTimeHour;
    }

    public final TextView getMTimeMinute() {
        return this.mTimeMinute;
    }

    public final TimeZone getMTimeZone() {
        return this.mTimeZone;
    }

    public final TextView getMTvExtraContent() {
        return this.mTvExtraContent;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        updateTime();
        updateKeyguardLandClock$1(false);
        boolean z = ClockSwitchHelper.Companion.getInstance(((LinearLayout) this).mContext).mLandClockMainSwitchOn;
        int i = this.mClockMode;
        if (z || i == 1) {
            TextView textView = this.mTimeMinute;
            if (textView != null) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
                layoutParams.setMargins(0, 0, 0, 0);
                TextView textView2 = this.mTimeMinute;
                if (textView2 != null) {
                    textView2.setLayoutParams(layoutParams);
                }
            }
        } else {
            int dimensionPixelSize = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131172651);
            TextView textView3 = this.mTimeMinute;
            if (textView3 != null) {
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) textView3.getLayoutParams();
                layoutParams2.setMargins(0, dimensionPixelSize, 0, 0);
                TextView textView4 = this.mTimeMinute;
                if (textView4 != null) {
                    textView4.setLayoutParams(layoutParams2);
                }
            }
        }
        updateDateViewMargin$1(z);
        updateDataChargeRelativeLayoutMargin();
        ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getConfigurationController().addCallback(this);
        ObserverDispatcher.getInstance().registerObserver(Settings.System.getUriFor(KEY_IS_SHOW_LUNAR_CALENDAR), this.showLunarObserver, -1);
        setTextFont$2();
        updateSwitchParametersState$1();
    }

    public void onConfigChanged(Configuration configuration) {
        boolean z;
        onSettingFoldedOrTablet(configuration);
        int flipFont = OplusBaseConfigurationAddon.getFlipFont(configuration);
        String languageTags = configuration.getLocales().toLanguageTags();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            Log.d("SystemUi--Keyguard", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("mLastFont = ", this.mLastFont, flipFont, " newFont = ", "SingleClockView-->"));
        }
        if (flipFont != this.mLastFont) {
            this.mLastFont = flipFont;
            z = true;
        } else {
            z = false;
        }
        ClockSwitchHelper.Companion.getInstance(((LinearLayout) this).mContext).getClass();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            Log.d("SystemUi--Keyguard", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("mLastLocale = ", this.mLastLocale, " newLocale = ", languageTags, "SingleClockView-->"));
        }
        if (!Intrinsics.areEqual(languageTags, this.mLastLocale)) {
            this.mLastLocale = languageTags;
            Context contextCreateConfigurationContext = ((LinearLayout) this).mContext.createConfigurationContext(configuration);
            ((LinearLayout) this).mContext = contextCreateConfigurationContext;
            KeyguardUtils.Companion.getKeyguardUtils(contextCreateConfigurationContext).updateClockColonSymbol();
            updateSymbol$2();
            z = true;
        }
        setTextFont$2();
        int integer = ((LinearLayout) this).mContext.getApplicationContext().getResources().getInteger(2131427516);
        if (this.mClockMode != integer) {
            this.mClockMode = integer;
            z = true;
        }
        if (z) {
            refresh(true);
        }
        LunarHelper.Companion.getInstance(((LinearLayout) this).mContext).getClass();
        this.canShowLunarCalendar = LunarHelper.canShowLunarCalendar();
        updateDate();
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        onSettingFoldedOrTablet(configuration);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getConfigurationController().removeCallback(this);
        ObserverDispatcher.getInstance().unregisterObserver(this.showLunarObserver);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mCalendar = Calendar.getInstance();
        this.mTimeHour = (TextView) findViewById(2131362572);
        this.mTimeMinute = (TextView) findViewById(2131362573);
        this.mDataChargeRelativeLayout = (RelativeLayout) findViewById(2131362781);
        this.mTimeAmPm = (TextView) findViewById(2131362570);
        this.mTvExtraContent = (TextView) findViewById(2131365809);
        this.mDate = (TextView) findViewById(2131362784);
        this.mChargeBig = findViewById(2131365810);
        this.mChargeNormal = findViewById(2131365809);
        this.mClockTimeContainer = (LinearLayout) findViewById(2131362571);
        this.mClockSymbol = (TextView) findViewById(2131362574);
        this.mDateChargeContainer = (LinearLayout) findViewById(2131362780);
        updateSymbol$2();
        setTextColor(((LinearLayout) this).mContext.getApplicationContext().getResources().getColor(2131100028));
        setPortPadding$1();
        setTextFont$2();
    }

    public void onMovedToDisplay(int i, Configuration configuration) {
        super.onMovedToDisplay(i, configuration);
    }

    public final void onSettingFoldedOrTablet(Configuration configuration) {
        FeatureOption.INSTANCE.getClass();
        if (((!FeatureOption.isFoldDevice() || FeatureOption.isFoldRemapDisabled()) && !FeatureOption.isTablet()) || this.mOrientation == configuration.orientation) {
            return;
        }
        Context contextCreateConfigurationContext = ((LinearLayout) this).mContext.createConfigurationContext(configuration);
        ((LinearLayout) this).mContext = contextCreateConfigurationContext;
        if (!ClockSwitchHelper.Companion.getInstance(contextCreateConfigurationContext).mLandClockMainSwitchOn) {
            setPortPadding$1();
        }
        this.mOrientation = configuration.orientation;
    }

    public final void onTimeZoneChanged(TimeZone timeZone) {
        if (this.mTimeZone != timeZone) {
            this.mTimeZone = timeZone;
        }
        updateTime();
    }

    public final void refresh(boolean z) {
        updateTime();
        updateKeyguardLandClock$1(z);
        if (this.mLastLayoutDirection != getLayoutDirection()) {
            this.mLastLayoutDirection = getLayoutDirection();
            if (ClockSwitchHelper.Companion.getInstance(((LinearLayout) this).mContext).mLandClockMainSwitchOn) {
                return;
            }
            setPortPadding$1();
        }
    }

    public final void setDateViewMargin$1(int i) {
        LinearLayout linearLayout = this.mDateChargeContainer;
        ViewGroup.LayoutParams layoutParams = linearLayout != null ? linearLayout.getLayoutParams() : null;
        ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
        if (marginLayoutParams != null) {
            marginLayoutParams.setMargins(0, i, 0, 0);
        }
        LinearLayout linearLayout2 = this.mDateChargeContainer;
        if (linearLayout2 != null) {
            linearLayout2.setLayoutParams(marginLayoutParams);
        }
    }

    public final void setLogBuffer(LogBuffer logBuffer) {
        this.logBuffer = logBuffer;
    }

    public final void setMAmpm(String str) {
        this.mAmpm = str;
    }

    public final void setMCalendar(Calendar calendar) {
        this.mCalendar = calendar;
    }

    public final void setMChargeBig(ClockExtraTextView clockExtraTextView) {
        this.mChargeBig = clockExtraTextView;
    }

    public final void setMChargeNormal(ClockExtraTextView clockExtraTextView) {
        this.mChargeNormal = clockExtraTextView;
    }

    public final void setMClockMode(int i) {
        this.mClockMode = i;
    }

    public final void setMClockSymbol(TextView textView) {
        this.mClockSymbol = textView;
    }

    public final void setMClockTimeContainer(LinearLayout linearLayout) {
        this.mClockTimeContainer = linearLayout;
    }

    public final void setMDataChargeRelativeLayout(RelativeLayout relativeLayout) {
        this.mDataChargeRelativeLayout = relativeLayout;
    }

    public final void setMDate(TextView textView) {
        this.mDate = textView;
    }

    public final void setMDateChargeContainer(LinearLayout linearLayout) {
        this.mDateChargeContainer = linearLayout;
    }

    public final void setMFormat(String str) {
        this.mFormat = str;
    }

    public final void setMHour(String str) {
        this.mHour = str;
    }

    public final void setMIs24HourFormat(boolean z) {
        this.mIs24HourFormat = z;
    }

    public final void setMLastFont(int i) {
        this.mLastFont = i;
    }

    public final void setMLastLandLockEnable(boolean z) {
        this.mLastLandLockEnable = z;
    }

    public final void setMLastLayoutDirection(int i) {
        this.mLastLayoutDirection = i;
    }

    public final void setMLastLocale(String str) {
        this.mLastLocale = str;
    }

    public final void setMMinutes(String str) {
        this.mMinutes = str;
    }

    public final void setMOrientation(int i) {
        this.mOrientation = i;
    }

    public final void setMTimeAmPm(TextView textView) {
        this.mTimeAmPm = textView;
    }

    public final void setMTimeHour(TextView textView) {
        this.mTimeHour = textView;
    }

    public final void setMTimeMinute(TextView textView) {
        this.mTimeMinute = textView;
    }

    public final void setMTimeZone(TimeZone timeZone) {
        this.mTimeZone = timeZone;
    }

    public final void setMTvExtraContent(TextView textView) {
        this.mTvExtraContent = textView;
    }

    public final void setPortPadding$1() {
        if (DeviceStateController.getInstance().isOpenOrHalfOpen() && DeviceUtil.shouldUseLargeFoldConfig(((LinearLayout) this).mContext.getResources())) {
            setPaddingRelative(((KeyguardUtils.Companion.getKeyguardUtils(((LinearLayout) this).mContext).getRealScreenSize()[0] - (((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131170750) - (((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131170784) * 2))) / 2) - ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131173116), 0, 0, 0);
        } else {
            setPaddingRelative(getResources().getDimensionPixelSize(2131172649), 0, getResources().getDimensionPixelSize(2131172649), 0);
        }
    }

    public final void setTextColor(int i) {
        TextView textView = this.mTimeHour;
        if (textView != null) {
            textView.setTextColor(i);
        }
        TextView textView2 = this.mTimeMinute;
        if (textView2 != null) {
            textView2.setTextColor(i);
        }
        TextView textView3 = this.mTimeAmPm;
        if (textView3 != null) {
            textView3.setTextColor(i);
        }
        TextView textView4 = this.mDate;
        if (textView4 != null) {
            textView4.setTextColor(i);
        }
        TextView textView5 = this.mTvExtraContent;
        if (textView5 != null) {
            textView5.setTextColor(i);
        }
        ClockExtraTextView clockExtraTextView = this.mChargeBig;
        if (clockExtraTextView != null) {
            clockExtraTextView.setTextColor(i);
        }
        TextView textView6 = this.mClockSymbol;
        if (textView6 != null) {
            textView6.setTextColor(i);
        }
        ShadowController.INSTANCE.setShadowLayer(new TextView[]{this.mTimeHour, this.mTimeMinute, this.mTimeAmPm, this.mDate, this.mTvExtraContent, this.mClockSymbol});
    }

    public final void setTextFont$2() {
        SystemTypefaceHelper.Companion companion = SystemTypefaceHelper.Companion;
        companion.getInstance(((LinearLayout) this).mContext).getClass();
        Typeface clockTypeface = !OplusFontUtils.isFlipFontUsed ? companion.getInstance(((LinearLayout) this).mContext).getClockTypeface() : Typeface.DEFAULT;
        TextView textView = this.mTimeHour;
        if (textView != null) {
            textView.setTypeface(clockTypeface);
        }
        TextView textView2 = this.mTimeMinute;
        if (textView2 != null) {
            textView2.setTypeface(clockTypeface);
        }
        TextView textView3 = this.mClockSymbol;
        if (textView3 != null) {
            textView3.setTypeface(clockTypeface);
        }
    }

    public final void updateDataChargeRelativeLayoutMargin() {
        RelativeLayout relativeLayout;
        RelativeLayout relativeLayout2 = this.mDataChargeRelativeLayout;
        ViewGroup.LayoutParams layoutParams = relativeLayout2 != null ? relativeLayout2.getLayoutParams() : null;
        ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
        if (marginLayoutParams != null) {
            marginLayoutParams.setMargins(0, getContext().getResources().getDimensionPixelSize(2131172650), 0, 0);
        }
        if (marginLayoutParams == null || (relativeLayout = this.mDataChargeRelativeLayout) == null) {
            return;
        }
        relativeLayout.setLayoutParams(marginLayoutParams);
    }

    public final void updateDate() {
        WeatherInfoParseHelper companion = WeatherInfoParseHelper.Companion.getInstance();
        Context context = ((LinearLayout) this).mContext;
        updateDate.1 r2 = new updateDate.1();
        r2.this$0 = this;
        VarHandle.storeStoreFence();
        companion.getLocalTimeInfo(context, r2);
    }

    public final void updateDateViewMargin$1(boolean z) {
        if (z) {
            setDateViewMargin$1(getContext().getResources().getDimensionPixelSize(2131172646));
        } else {
            setDateViewMargin$1(getContext().getResources().getDimensionPixelSize(2131172650));
        }
    }

    public final void updateKeyguardLandClock$1(boolean z) {
        boolean z2 = ClockSwitchHelper.Companion.getInstance(((LinearLayout) this).mContext).mLandClockMainSwitchOn;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            Log.d("SystemUi--Keyguard", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("updateKeyguardLandClock, mLastLandLockEnable:", ", landClockEnable:", "SingleClockView-->", this.mLastLandLockEnable, z2));
        }
        if (this.mLastLandLockEnable != z2 || z) {
            this.mLastLandLockEnable = z2;
            LinearLayout linearLayout = this.mClockTimeContainer;
            if (z2) {
                if (linearLayout != null) {
                    linearLayout.setOrientation(0);
                }
                TextView textView = this.mClockSymbol;
                if (textView != null) {
                    textView.setVisibility(0);
                }
                LinearLayout linearLayout2 = this.mDateChargeContainer;
                if (linearLayout2 != null) {
                    linearLayout2.setGravity(17);
                }
                TextView textView2 = this.mDate;
                if (textView2 != null) {
                    textView2.setGravity(17);
                }
                ClockExtraTextView clockExtraTextView = this.mChargeNormal;
                if (clockExtraTextView != null) {
                    clockExtraTextView.setGravity(17);
                }
                setGravity(1);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams.addRule(14);
                ClockExtraTextView clockExtraTextView2 = this.mChargeBig;
                if (clockExtraTextView2 != null) {
                    clockExtraTextView2.setLayoutParams(layoutParams);
                }
                setPaddingRelative(getResources().getDimensionPixelSize(2131172648), getResources().getDimensionPixelSize(2131166018), getResources().getDimensionPixelSize(2131172648), 0);
            } else {
                if (linearLayout != null) {
                    linearLayout.setOrientation(1);
                }
                TextView textView3 = this.mClockSymbol;
                if (textView3 != null) {
                    textView3.setVisibility(8);
                }
                LinearLayout linearLayout3 = this.mDateChargeContainer;
                if (linearLayout3 != null) {
                    linearLayout3.setGravity(8388611);
                }
                setGravity(8388611);
                RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
                ClockExtraTextView clockExtraTextView3 = this.mChargeBig;
                if (clockExtraTextView3 != null) {
                    clockExtraTextView3.setLayoutParams(layoutParams2);
                }
                setPortPadding$1();
            }
            TextView textView4 = this.mTimeHour;
            if (z2) {
                if (textView4 != null) {
                    textView4.setTextSize(0, getResources().getDimensionPixelSize(2131166028));
                }
                TextView textView5 = this.mTimeMinute;
                if (textView5 != null) {
                    textView5.setTextSize(0, getResources().getDimensionPixelSize(2131166028));
                }
            } else {
                if (textView4 != null) {
                    textView4.setTextSize(0, getResources().getDimensionPixelSize(2131166027));
                }
                TextView textView6 = this.mTimeMinute;
                if (textView6 != null) {
                    textView6.setTextSize(0, getResources().getDimensionPixelSize(2131166027));
                }
            }
            int i = this.mClockMode;
            if (z2 || i == 1) {
                TextView textView7 = this.mTimeMinute;
                if (textView7 != null) {
                    LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) textView7.getLayoutParams();
                    layoutParams3.setMargins(0, 0, 0, 0);
                    TextView textView8 = this.mTimeMinute;
                    if (textView8 != null) {
                        textView8.setLayoutParams(layoutParams3);
                    }
                }
            } else {
                int dimensionPixelSize = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131172651);
                TextView textView9 = this.mTimeMinute;
                if (textView9 != null) {
                    LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) textView9.getLayoutParams();
                    layoutParams4.setMargins(0, dimensionPixelSize, 0, 0);
                    TextView textView10 = this.mTimeMinute;
                    if (textView10 != null) {
                        textView10.setLayoutParams(layoutParams4);
                    }
                }
            }
            updateDataChargeRelativeLayoutMargin();
            updateDateViewMargin$1(z2);
            TextView textView11 = this.mTimeHour;
            if (z2) {
                if (textView11 != null) {
                    textView11.setFontFeatureSettings("");
                }
                TextView textView12 = this.mTimeMinute;
                if (textView12 != null) {
                    textView12.setFontFeatureSettings("");
                    return;
                }
                return;
            }
            if (textView11 != null) {
                textView11.setFontFeatureSettings("tnum");
            }
            TextView textView13 = this.mTimeMinute;
            if (textView13 != null) {
                textView13.setFontFeatureSettings("tnum");
            }
        }
    }

    public final void updateSwitchParametersState$1() {
        this.isShowLunarCalendarSwitchOpen = TextUtils.equals("true", Settings.System.getStringForUser(((LinearLayout) this).mContext.getContentResolver(), KEY_IS_SHOW_LUNAR_CALENDAR, -2));
        LunarHelper.Companion.getInstance(((LinearLayout) this).mContext).getClass();
        boolean zCanShowLunarCalendar = LunarHelper.canShowLunarCalendar();
        this.canShowLunarCalendar = zCanShowLunarCalendar;
        OlockStateUtil$.ExternalSyntheticOutline0.m("updateSwitchParametersState isShowLunarCalendarSwitchOpen =", ", canShowLunarCalendar=", TAG, this.isShowLunarCalendarSwitchOpen, zCanShowLunarCalendar);
        updateDate();
    }

    public final void updateSymbol$2() {
        if (ClockSwitchHelper.Companion.getInstance(((LinearLayout) this).mContext).mLandClockMainSwitchOn) {
            String clockColonSymbol = KeyguardUtils.Companion.getKeyguardUtils(((LinearLayout) this).mContext).getClockColonSymbol();
            if (!TextUtils.equals(this.mSymbol, clockColonSymbol)) {
                this.mSymbol = clockColonSymbol;
                this.mClockSymbol.setText(clockColonSymbol);
            }
            TextView textView = this.mClockSymbol;
            if (textView != null) {
                textView.setPadding(0, ((LinearLayout) this).mContext.getApplicationContext().getResources().getDimensionPixelSize(2131172653), 0, 0);
            }
        }
    }

    public final void updateTime() {
        Integer numValueOf;
        this.mIs24HourFormat = DateFormat.is24HourFormat(((LinearLayout) this).mContext, -2);
        long jCurrentTimeMillis = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        this.mCalendar = calendar;
        if (calendar != null) {
            calendar.setTimeInMillis(jCurrentTimeMillis);
        }
        String timeFormatString = DateFormat.getTimeFormatString(((LinearLayout) this).mContext);
        this.mFormat = timeFormatString;
        String strReplace = timeFormatString != null ? StringsKt__StringsJVMKt.replace(timeFormatString, AM_PM_FORMAT, "", false) : null;
        this.mFormat = strReplace;
        this.mFormat = strReplace != null ? StringsKt__StringsJVMKt.replace(strReplace, " ", "", false) : null;
        if (KeyguardUtils.Companion.getKeyguardUtils(getContext()).isClockGlobalThemeEnable) {
            try {
                Locale locale = Locale.US;
                Calendar calendar2 = this.mCalendar;
                this.mMinutes = String.format(locale, "%02d", Arrays.copyOf(new Object[]{calendar2 != null ? Integer.valueOf(calendar2.get(12)) : null}, 1));
                boolean z = this.mIs24HourFormat;
                Calendar calendar3 = this.mCalendar;
                if (z) {
                    this.mHour = String.format(locale, "%02d", Arrays.copyOf(new Object[]{calendar3 != null ? Integer.valueOf(calendar3.get(11)) : null}, 1));
                    this.mAmpm = null;
                } else {
                    Integer numValueOf2 = calendar3 != null ? Integer.valueOf(calendar3.get(10)) : null;
                    if (numValueOf2 != null && numValueOf2.intValue() == 0) {
                        numValueOf2 = 12;
                    }
                    this.mHour = String.format(locale, "%d", Arrays.copyOf(new Object[]{numValueOf2}, 1));
                    this.mAmpm = (String) DateFormat.format(AM_PM_FORMAT, this.mCalendar);
                }
            } catch (Exception unused) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                    Log.d("SystemUi--Keyguard", "SingleClockView-->fail to parse time while global theme enable");
                }
            }
        } else {
            this.mMinutes = (String) DateFormat.format(MINUTE_FORMAT, this.mCalendar);
            boolean z2 = this.mIs24HourFormat;
            Calendar calendar4 = this.mCalendar;
            if (z2) {
                String str = (String) DateFormat.format(HOUR_FORMAT_24, calendar4);
                this.mHour = str;
                if (str != null) {
                    try {
                        numValueOf = Integer.valueOf(Integer.parseInt(str));
                    } catch (Exception unused2) {
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("fail to parse hour string to int, mHour = ", this.mHour, "SingleClockView-->", "SystemUi--Keyguard");
                        }
                    }
                } else {
                    numValueOf = null;
                }
                this.mHour = String.format("%02d", Arrays.copyOf(new Object[]{numValueOf}, 1));
                this.mAmpm = null;
            } else {
                this.mHour = (String) DateFormat.format(HOUR_FORMAT_12, calendar4);
                this.mAmpm = (String) DateFormat.format(AM_PM_FORMAT, this.mCalendar);
            }
        }
        TextView textView = this.mTimeHour;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) (textView != null ? textView.getLayoutParams() : null);
        TextView textView2 = this.mTimeMinute;
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) (textView2 != null ? textView2.getLayoutParams() : null);
        int i = this.mClockMode;
        if (i == 0) {
            TextView textView3 = this.mTimeHour;
            if (textView3 != null) {
                textView3.setCompoundDrawables(null, null, null, null);
            }
            TextView textView4 = this.mTimeMinute;
            if (textView4 != null) {
                textView4.setCompoundDrawables(null, null, null, null);
            }
            boolean z3 = ClockSwitchHelper.Companion.getInstance(((LinearLayout) this).mContext).mLandClockMainSwitchOn;
            TextView textView5 = this.mTimeHour;
            if (textView5 != null) {
                FlavorTwoFeatureOption.INSTANCE.getClass();
                textView5.setText((FlavorTwoFeatureOption.isFlavorTwoDevice() && z3) ? KeyguardUtils.Companion.getSpannedHourString(getContext(), this.mHour) : this.mHour);
            }
            TextView textView6 = this.mTimeMinute;
            if (textView6 != null) {
                textView6.setText(this.mMinutes);
            }
            layoutParams.height = -2;
            layoutParams2.height = -2;
        } else if (i == 1) {
            ImageClockHelper.Companion companion = ImageClockHelper.Companion;
            companion.getInstance(((LinearLayout) this).mContext).setTextImg(this.mTimeHour, this.mHour);
            companion.getInstance(((LinearLayout) this).mContext).setTextImg(this.mTimeMinute, this.mMinutes);
            int dimensionPixelSize = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131166026);
            layoutParams.height = dimensionPixelSize;
            layoutParams2.height = dimensionPixelSize;
        }
        TextView textView7 = this.mTimeHour;
        if (textView7 != null) {
            textView7.setLayoutParams(layoutParams);
        }
        TextView textView8 = this.mTimeMinute;
        if (textView8 != null) {
            textView8.setLayoutParams(layoutParams2);
        }
        TextView textView9 = this.mTimeAmPm;
        if (textView9 != null) {
            textView9.setText(this.mAmpm);
        }
        boolean z4 = this.mIs24HourFormat;
        String str2 = this.mHour;
        String str3 = this.mMinutes;
        StringBuilder sbM = AuthenticationResultModel$.ExternalSyntheticOutline0.m("updateTime,  24hfmt=", ", h=", str2, z4, ", m=");
        sbM.append(str3);
        Log.d(TAG, sbM.toString());
        updateDate();
        LinearLayout linearLayout = this.mClockTimeContainer;
        if (linearLayout != null) {
            linearLayout.setContentDescription(DateFormat.format(this.mFormat, this.mCalendar));
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public SingleClockView(Context context) {
        AttributeSet attributeSet = null;
        this(context, attributeSet, 2, attributeSet);
    }

    public /* synthetic */ SingleClockView(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }
}
