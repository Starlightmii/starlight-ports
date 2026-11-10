package com.oplus.systemui.shared.clocks;

import android.content.Context;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.graphics.Typeface;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import com.android.keyguard.OplusKeyguardDependencyEx;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.DependencyEx;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.statusbar.OlockStateUtil$;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplus.keyguard.settings.proxy.KeyguardSettingBackupRestoreProxy$;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.keyguard.clock.LunarHelper;
import com.oplus.systemui.keyguard.clock.WeatherInfoParseHelper;
import com.oplus.systemui.keyguard.wallpaper.ShadowController;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.observer.ObserverDispatcher;
import com.oplusos.systemui.common.typeface.SystemTypefaceHelper;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Calendar;
import java.util.Locale;
import kotlin.jvm.internal.DefaultConstructorMarker;
import oplus.content.res.OplusFontUtils;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RedHorizontalSingleClockView extends LinearLayout implements ConfigurationController.ConfigurationListener {
    private static final String CLOCK_SYMBOL_STR = ":";
    public static final Companion Companion = new Companion();
    private static final String KEY_IS_SHOW_LUNAR_CALENDAR = "key_is_show_lunar_calendar";
    private static final String TAG = "OnePHorizontalClockView";
    public boolean canShowLunarCalendar;
    private RelativeLayout clockRelativeLayout;
    private String formatRule;
    public boolean isShowLunarCalendarSwitchOpen;
    private String lastLocale;
    private LogBuffer logBuffer;
    private final ContentObserver showLunarObserver;
    private String symbol;
    private TextView tvColon;
    private TextView tvDate;
    private TextView tvExtraContent;
    private TextView tvExtraContentBig;
    private RedTextClock tvHour;
    private TextClock tvMinute;
    private TextClock tvWeek;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public RedHorizontalSingleClockView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.symbol = CLOCK_SYMBOL_STR;
        this.formatRule = ((LinearLayout) this).mContext.getResources().getString(2131952651);
        final Handler handler = new Handler();
        this.showLunarObserver = new ContentObserver(handler) { // from class: com.oplus.systemui.shared.clocks.RedHorizontalSingleClockView$showLunarObserver$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                super.onChange(z);
                RedHorizontalSingleClockView redHorizontalSingleClockView = this.this$0;
                redHorizontalSingleClockView.isShowLunarCalendarSwitchOpen = TextUtils.equals("true", Settings.System.getStringForUser(((LinearLayout) redHorizontalSingleClockView).mContext.getContentResolver(), "key_is_show_lunar_calendar", -2));
                RedHorizontalSingleClockView redHorizontalSingleClockView2 = this.this$0;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                    OplusLockIconView$.ExternalSyntheticOutline0.m("mShowLunarObserver onChange:", "OnePHorizontalClockView-->", "SystemUi--Keyguard", redHorizontalSingleClockView2.isShowLunarCalendarSwitchOpen);
                }
                this.this$0.refreshDate();
            }
        };
        Context context2 = ((LinearLayout) this).mContext;
        Context contextCreateConfigurationContext = context2.createConfigurationContext(context2.getResources().getConfiguration());
        ((LinearLayout) this).mContext = contextCreateConfigurationContext;
        this.lastLocale = contextCreateConfigurationContext.getApplicationContext().getResources().getConfiguration().getLocales().toLanguageTags();
    }

    public final LogBuffer getLogBuffer() {
        return this.logBuffer;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        setTextFont$1();
        ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getConfigurationController().addCallback(this);
        ObserverDispatcher.getInstance().registerObserver(Settings.System.getUriFor(KEY_IS_SHOW_LUNAR_CALENDAR), this.showLunarObserver, -1);
        updateSwitchParametersState();
    }

    public void onConfigChanged(Configuration configuration) {
        String languageTags = configuration.getLocales().toLanguageTags();
        KeyguardSettingBackupRestoreProxy$.ExternalSyntheticOutline0.m("mLastLocale = ", this.lastLocale, ",newLocale = ", languageTags, TAG);
        LunarHelper.Companion.getInstance(((LinearLayout) this).mContext).getClass();
        this.canShowLunarCalendar = LunarHelper.canShowLunarCalendar();
        String str = this.lastLocale;
        if (str == null || languageTags.charAt(0) != str.charAt(0)) {
            this.lastLocale = languageTags;
            setDateFormat();
            updateClockRelativeLayoutMargin();
            updateClockTextSize();
            KeyguardUtils.Companion.getKeyguardUtils(((LinearLayout) this).mContext).updateClockColonSymbol();
            updateSymbol$1();
        }
        setTextFont$1();
        refresh();
        FeatureOption.INSTANCE.getClass();
        int dimensionPixelSize = FeatureOption.isTablet() ? getResources().getDimensionPixelSize(2131172649) : getResources().getDimensionPixelSize(2131172300);
        setPaddingRelative(dimensionPixelSize, 0, dimensionPixelSize, 0);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getConfigurationController().removeCallback(this);
        ObserverDispatcher.getInstance().unregisterObserver(this.showLunarObserver);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.tvWeek = (TextClock) findViewById(2131365860);
        this.tvHour = (RedTextClock) findViewById(2131365813);
        this.tvColon = (TextView) findViewById(2131365801);
        this.tvMinute = (TextClock) findViewById(2131365821);
        this.tvDate = (TextView) findViewById(2131365803);
        this.tvExtraContent = (TextView) findViewById(2131365809);
        this.tvExtraContentBig = (TextView) findViewById(2131365810);
        this.clockRelativeLayout = (RelativeLayout) findViewById(2131364929);
        RedTextClock redTextClock = this.tvHour;
        if (redTextClock != null) {
            redTextClock.setFormat12Hour(((LinearLayout) this).mContext.getResources().getString(2131952649));
        }
        RedTextClock redTextClock2 = this.tvHour;
        if (redTextClock2 != null) {
            redTextClock2.setFormat24Hour(((LinearLayout) this).mContext.getResources().getString(2131952650));
        }
        setDateFormat();
        this.lastLocale = ((LinearLayout) this).mContext.getApplicationContext().getResources().getConfiguration().getLocales().toLanguageTags();
        ShadowController.INSTANCE.setShadowLayer(new TextView[]{this.tvWeek, this.tvHour, this.tvColon, this.tvMinute, this.tvDate, this.tvExtraContent});
        setTextFont$1();
        RedTextClock redTextClock3 = this.tvHour;
        if (redTextClock3 != null) {
            redTextClock3.setShowCurrentUserTime(true);
        }
        TextView textView = this.tvDate;
        if (textView != null) {
            textView.setFontVariationSettings("'wght' 500");
        }
        TextView textView2 = this.tvExtraContent;
        if (textView2 != null) {
            textView2.setFontVariationSettings("'wght' 500");
        }
        FeatureOption.INSTANCE.getClass();
        int dimensionPixelSize = FeatureOption.isTablet() ? getResources().getDimensionPixelSize(2131172649) : getResources().getDimensionPixelSize(2131172300);
        setPaddingRelative(dimensionPixelSize, 0, dimensionPixelSize, 0);
        updateClockRelativeLayoutMargin();
        updateClockTextSize();
        updateSymbol$1();
    }

    public void onMovedToDisplay(int i, Configuration configuration) {
        super.onMovedToDisplay(i, configuration);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [android.widget.TextView, com.oplus.systemui.shared.clocks.RedTextClock] */
    public final void refresh() {
        TextClock textClock = this.tvWeek;
        if (textClock != null) {
            textClock.refreshTime();
        }
        ?? r0 = this.tvHour;
        if (r0 != 0) {
            r0.onTimeChanged();
            r0.invalidate();
        }
        TextClock textClock2 = this.tvMinute;
        if (textClock2 != null) {
            textClock2.refreshTime();
        }
        refreshDate();
    }

    public final void refreshDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        CharSequence charSequence = DateFormat.format(this.formatRule, calendar);
        if (!this.isShowLunarCalendarSwitchOpen || !this.canShowLunarCalendar) {
            TextView textView = this.tvDate;
            if (textView != null) {
                textView.setText(charSequence);
                return;
            }
            return;
        }
        String dateToString = LunarHelper.Companion.getInstance(((LinearLayout) this).mContext).getDateToString(System.currentTimeMillis());
        StringBuilder sb = new StringBuilder(charSequence);
        sb.append(" ");
        sb.append(dateToString);
        TextView textView2 = this.tvDate;
        if (textView2 != null) {
            textView2.setText(sb);
        }
    }

    public final void setDateFormat() {
        if (WeatherInfoParseHelper.Companion.isZh(getContext().getApplicationContext())) {
            this.formatRule = ((LinearLayout) this).mContext.getResources().getString(2131952651);
        } else {
            this.formatRule = DateFormat.getBestDateTimePattern(Locale.getDefault(), ((LinearLayout) this).mContext.getResources().getString(2131952652));
        }
    }

    public final void setLogBuffer(LogBuffer logBuffer) {
        this.logBuffer = logBuffer;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void setTextColor(int i) {
        TextClock textClock = this.tvWeek;
        if (textClock != null) {
            textClock.setTextColor(i);
        }
        AppCompatTextView appCompatTextView = this.tvHour;
        if (appCompatTextView != null) {
            appCompatTextView.setTextColor(i);
        }
        TextView textView = this.tvColon;
        if (textView != null) {
            textView.setTextColor(i);
        }
        TextClock textClock2 = this.tvMinute;
        if (textClock2 != null) {
            textClock2.setTextColor(i);
        }
        TextView textView2 = this.tvDate;
        if (textView2 != null) {
            textView2.setTextColor(i);
        }
        TextView textView3 = this.tvExtraContent;
        if (textView3 != null) {
            textView3.setTextColor(i);
        }
        TextView textView4 = this.tvExtraContentBig;
        if (textView4 != null) {
            textView4.setTextColor(i);
        }
        ShadowController.INSTANCE.setShadowLayer(new TextView[]{this.tvWeek, this.tvHour, this.tvColon, this.tvMinute, this.tvDate, this.tvExtraContent});
    }

    public final void setTextFont$1() {
        SystemTypefaceHelper.Companion companion = SystemTypefaceHelper.Companion;
        companion.getInstance(getContext()).getClass();
        boolean z = OplusFontUtils.isFlipFontUsed;
        boolean z2 = !z;
        Typeface clockTypeface = !z ? companion.getInstance(getContext()).getClockTypeface() : Typeface.DEFAULT;
        KgdLogUtil.i(TAG, "setTextFont defaultTypeface: " + z2 + ", typeface: " + clockTypeface);
        TextClock textClock = this.tvWeek;
        if (textClock != null) {
            textClock.setTypeface(clockTypeface);
        }
        RedTextClock redTextClock = this.tvHour;
        if (redTextClock != null) {
            redTextClock.setTypeface(clockTypeface);
        }
        TextView textView = this.tvColon;
        if (textView != null) {
            textView.setTypeface(clockTypeface);
        }
        TextClock textClock2 = this.tvMinute;
        if (textClock2 != null) {
            textClock2.setTypeface(clockTypeface);
        }
        TextView textView2 = this.tvDate;
        if (textView2 != null) {
            textView2.setTypeface(clockTypeface);
        }
        TextView textView3 = this.tvExtraContent;
        if (textView3 != null) {
            textView3.setTypeface(clockTypeface);
        }
    }

    public final void updateClockRelativeLayoutMargin() {
        RelativeLayout relativeLayout = this.clockRelativeLayout;
        ViewGroup.LayoutParams layoutParams = relativeLayout != null ? relativeLayout.getLayoutParams() : null;
        ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
        if (marginLayoutParams != null) {
            int dimensionPixelSize = ((LinearLayout) this).mContext.getApplicationContext().getResources().getDimensionPixelSize(2131172303);
            marginLayoutParams.setMargins(0, dimensionPixelSize, 0, dimensionPixelSize);
            RelativeLayout relativeLayout2 = this.clockRelativeLayout;
            if (relativeLayout2 != null) {
                relativeLayout2.setLayoutParams(marginLayoutParams);
            }
        }
    }

    public final void updateClockTextSize() {
        RedTextClock redTextClock = this.tvHour;
        if (redTextClock != null) {
            redTextClock.setTextSize(0, ((LinearLayout) this).mContext.getApplicationContext().getResources().getDimensionPixelSize(2131172297));
        }
        TextView textView = this.tvColon;
        if (textView != null) {
            textView.setTextSize(0, ((LinearLayout) this).mContext.getApplicationContext().getResources().getDimensionPixelSize(2131172292));
        }
        TextClock textClock = this.tvMinute;
        if (textClock != null) {
            textClock.setTextSize(0, ((LinearLayout) this).mContext.getApplicationContext().getResources().getDimensionPixelSize(2131172302));
        }
    }

    public final void updateSwitchParametersState() {
        this.isShowLunarCalendarSwitchOpen = TextUtils.equals("true", Settings.System.getStringForUser(((LinearLayout) this).mContext.getContentResolver(), KEY_IS_SHOW_LUNAR_CALENDAR, -2));
        LunarHelper.Companion.getInstance(((LinearLayout) this).mContext).getClass();
        boolean zCanShowLunarCalendar = LunarHelper.canShowLunarCalendar();
        this.canShowLunarCalendar = zCanShowLunarCalendar;
        OlockStateUtil$.ExternalSyntheticOutline0.m("updateSwitchParametersState isShowLunarCalendarSwitchOpen =", ", canShowLunarCalendar=", TAG, this.isShowLunarCalendarSwitchOpen, zCanShowLunarCalendar);
        refreshDate();
    }

    public final void updateSymbol$1() {
        String clockColonSymbol = KeyguardUtils.Companion.getKeyguardUtils(((LinearLayout) this).mContext).getClockColonSymbol();
        if (!TextUtils.equals(this.symbol, clockColonSymbol)) {
            this.symbol = clockColonSymbol;
            TextView textView = this.tvColon;
            if (textView != null) {
                textView.setText(clockColonSymbol);
            }
        }
        TextView textView2 = this.tvColon;
        if (textView2 != null) {
            textView2.setPadding(0, ((LinearLayout) this).mContext.getApplicationContext().getResources().getDimensionPixelSize(2131172308), 0, 0);
        }
    }

    public RedHorizontalSingleClockView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, null);
    }

    public RedHorizontalSingleClockView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0, 8, null);
    }

    public /* synthetic */ RedHorizontalSingleClockView(Context context, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2);
    }

    public RedHorizontalSingleClockView(Context context) {
        this(context, null, 0, 0, 14, null);
    }
}
