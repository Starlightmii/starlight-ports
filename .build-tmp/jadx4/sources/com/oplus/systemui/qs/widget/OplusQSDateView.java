package com.oplus.systemui.qs.widget;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;
import com.android.systemui.Dependency;
import com.android.systemui.res.R;
import com.oplus.systemui.keyguard.clock.WeatherInfoParseHelper;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.helper.OplusQSFontHelper;
import com.oplus.systemui.qs.receiver.TimeReceiver;
import com.oplus.systemui.qs.utils.DateViewTextConfig;
import com.oplusos.systemui.common.receiver.IReceiverListener;
import com.oplusos.systemui.common.typeface.SystemTypefaceHelper;
import java.lang.invoke.VarHandle;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusQSDateView extends TextView implements SystemTypefaceHelper.SystemUIFontCallback {
    public static final /* synthetic */ int $r8$clinit = 0;
    public DateTimeFormatter mDateFormat;
    public final String mDatePattern;
    public final Handler mHandler;
    public String mLastText;
    public final AnonymousClass1 mTimeListener;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.widget.OplusQSDateView$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements IReceiverListener {
        public /* synthetic */ OplusQSDateView this$0;

        @Override // com.oplusos.systemui.common.receiver.IReceiverListener
        public void onReceive(Context context, Intent intent) {
            OplusQSDateView oplusQSDateView = this.this$0;
            String action = intent.getAction();
            if ("android.intent.action.LOCALE_CHANGED".equals(action) || "android.intent.action.TIMEZONE_CHANGED".equals(action)) {
                Handler handler = oplusQSDateView.mHandler;
                OplusQSDateView$1$$ExternalSyntheticLambda0 oplusQSDateView$1$$ExternalSyntheticLambda0 = new OplusQSDateView$1$$ExternalSyntheticLambda0(0);
                oplusQSDateView$1$$ExternalSyntheticLambda0.f$0 = this;
                VarHandle.storeStoreFence();
                handler.post(oplusQSDateView$1$$ExternalSyntheticLambda0);
            }
            Handler handler2 = oplusQSDateView.mHandler;
            OplusQSDateView$1$$ExternalSyntheticLambda0 oplusQSDateView$1$$ExternalSyntheticLambda1 = new OplusQSDateView$1$$ExternalSyntheticLambda0(1);
            oplusQSDateView$1$$ExternalSyntheticLambda1.f$0 = this;
            VarHandle.storeStoreFence();
            handler2.post(oplusQSDateView$1$$ExternalSyntheticLambda1);
        }
    }

    public OplusQSDateView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mTimeListener = anonymousClass1;
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.DateView, 0, 0);
        try {
            String string = typedArrayObtainStyledAttributes.getString(0);
            this.mDatePattern = string;
            typedArrayObtainStyledAttributes.recycle();
            if (string == null) {
                this.mDatePattern = getContext().getString(2131956772);
            }
            this.mHandler = (Handler) Dependency.sDependency.getDependencyInner(Dependency.MAIN_HANDLER);
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        TimeReceiver.INSTANCE.addListener(this.mTimeListener);
        updateClock();
        SystemTypefaceHelper.Companion companion = SystemTypefaceHelper.Companion;
        SystemTypefaceHelper companion2 = companion.getInstance(((TextView) this).mContext);
        SystemTypefaceHelper companion3 = companion.getInstance(((TextView) this).mContext);
        Objects.requireNonNull(companion3);
        OplusQSClock$$ExternalSyntheticLambda0 oplusQSClock$$ExternalSyntheticLambda0 = new OplusQSClock$$ExternalSyntheticLambda0();
        oplusQSClock$$ExternalSyntheticLambda0.f$0 = companion3;
        VarHandle.storeStoreFence();
        companion2.addCallback(this, oplusQSClock$$ExternalSyntheticLambda0);
    }

    @Override // android.widget.TextView, android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateFontSize$1();
    }

    @Override // android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        TimeReceiver.INSTANCE.removeListener(this.mTimeListener);
        SystemTypefaceHelper.Companion.getInstance(((TextView) this).mContext).removeCallback(this);
        this.mDateFormat = null;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        updateFontSize$1();
    }

    @Override // com.oplusos.systemui.common.typeface.SystemTypefaceHelper.SystemUIFontCallback
    public void setFontTypeface(Typeface typeface, int i) {
        setTypeface(typeface);
    }

    public final void updateClock() {
        WeatherInfoParseHelper companion = WeatherInfoParseHelper.Companion.getInstance();
        Context context = ((TextView) this).mContext;
        OplusQSDateView$$ExternalSyntheticLambda1 oplusQSDateView$$ExternalSyntheticLambda1 = new OplusQSDateView$$ExternalSyntheticLambda1();
        oplusQSDateView$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        companion.getLocalTimeInfo(context, oplusQSDateView$$ExternalSyntheticLambda1);
    }

    public final void updateFontSize$1() {
        if (!QsColorUtil.getSeparateNotificationAndQSState()) {
            setTextSize(OplusQSFontHelper.getInstance(((TextView) this).mContext).getAdapterTextSizeSp(2));
            return;
        }
        DateViewTextConfig dateViewTextConfig = DateViewTextConfig.INSTANCE;
        dateViewTextConfig.updateScale(((TextView) this).mContext, new OplusQSDateView$$ExternalSyntheticLambda0());
        setTextSize(0, dateViewTextConfig.getTextSize());
    }
}
