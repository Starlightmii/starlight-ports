package com.oplus.systemui.qs.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import com.android.systemui.DependencyEx;
import com.android.systemui.plugins.ActivityStarter;
import com.oplus.systemui.notification.utlils.NotificationColorUtil;
import com.oplus.systemui.qs.OplusQSCarrierTextController;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.utils.DateViewTextConfig;
import com.oplusos.systemui.common.typeface.SystemTypefaceHelper;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.Objects;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusSecondCarrierText extends AnimateChangeTextView implements SystemTypefaceHelper.SystemUIFontCallback {
    public float mAppliedSizePx;
    public CharSequence mCarrierText;
    public OplusQSCarrierTextController.CarrierTextCallback mCarrierTextCallback;
    public final OplusQSCarrierTextController mCarrierTextController;
    public final Context mContext;
    public int mLastAvailableWidth;
    public int mLastMeasuredTextLength;
    public CharSequence mLastText;
    public boolean mListening;
    public float mMaxTextSizePx;
    public float mMinTextSizePx;
    public float mTargetSizePx;
    public TextPaint mTextPaint;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.widget.OplusSecondCarrierText$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements OplusQSCarrierTextController.CarrierTextCallback {
        public /* synthetic */ OplusSecondCarrierText this$0;

        @Override // com.oplus.systemui.qs.OplusQSCarrierTextController.CarrierTextCallback
        public /* bridge */ /* synthetic */ void setVisible(boolean z) {
            super.setVisible(z);
        }

        /* JADX WARN: Type inference failed for: r2v1, types: [android.widget.TextView, com.oplus.systemui.qs.widget.AnimateChangeTextView, com.oplus.systemui.qs.widget.OplusSecondCarrierText] */
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
        @Override // com.oplus.systemui.qs.OplusQSCarrierTextController.CarrierTextCallback
        public final void updateCarrierInfo(OplusQSCarrierTextController.OplusCarrierTextCallbackInfo oplusCarrierTextCallbackInfo) {
            ?? r2 = this.this$0;
            if (r2.mListening) {
                CharSequence charSequence = oplusCarrierTextCallbackInfo.carrierText;
                r2.mCarrierText = charSequence;
                if (TextUtils.equals(charSequence, r2.mLastText)) {
                    return;
                }
                LogUtil.i(LogUtil.TAG_QS, "OplusSecondCarrierText", "refreshUI updateCarrierInfo carrierText ");
                CharSequence charSequence2 = r2.mCarrierText;
                String string = charSequence2 != null ? charSequence2.toString() : "";
                if (r2.needApplyNewTextSize() && QsColorUtil.getSeparateNotificationAndQSState()) {
                    r2.setTextWithAnima(string, r2.mTargetSizePx);
                    r2.mAppliedSizePx = r2.mTargetSizePx;
                } else {
                    r2.setTextWithAnima(string, -1.0f);
                }
                r2.setClickable(false);
                r2.mLastText = string;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public OplusSecondCarrierText(Context context, AttributeSet attributeSet, ActivityStarter activityStarter) {
        super(context, attributeSet);
        this.mLastText = "";
        this.mLastAvailableWidth = -1;
        this.mLastMeasuredTextLength = -1;
        this.mTextPaint = null;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mCarrierTextCallback = anonymousClass1;
        this.mContext = context;
        this.mCarrierTextController = (OplusQSCarrierTextController) DependencyEx.sDependency.getDependency(OplusQSCarrierTextController.class);
        this.mMaxTextSizePx = getResources().getDimension(2131171916);
        this.mMinTextSizePx = getResources().getDimension(2131171917);
        this.mAppliedSizePx = this.mMaxTextSizePx;
        this.mTextPaint = new TextPaint(getPaint());
        setHorizontalFadingEdgeEnabled(true);
        setHorizontallyScrolling(true);
        setEllipsize(null);
        setMarqueeRepeatLimit(1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean canFitAtSizePx(CharSequence charSequence, float f, int i) {
        if (this.mTextPaint == null) {
            this.mTextPaint = new TextPaint(getPaint());
        }
        this.mTextPaint.setTextSize(f);
        return this.mTextPaint.measureText(charSequence, 0, charSequence.length()) <= ((float) i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean needApplyNewTextSize() {
        int iCeil;
        float f;
        int i;
        int i2;
        if (QsColorUtil.getSeparateNotificationAndQSState()) {
            int width = (getWidth() - getCompoundPaddingLeft()) - getCompoundPaddingRight();
            if (width > 0) {
                CharSequence charSequence = this.mCarrierText;
                if (!TextUtils.equals(this.mLastText, charSequence) || this.mLastAvailableWidth != width) {
                    int length = TextUtils.isEmpty(charSequence) ? 0 : charSequence.length();
                    int i3 = this.mLastAvailableWidth;
                    boolean z = Math.abs(this.mAppliedSizePx - this.mMaxTextSizePx) < 0.5f;
                    boolean z2 = Math.abs(this.mAppliedSizePx - this.mMinTextSizePx) < 0.5f;
                    if (z && i3 >= 0 && width > i3) {
                        f = this.mMaxTextSizePx;
                    } else if (z && (i2 = this.mLastMeasuredTextLength) >= 0 && length < i2) {
                        f = this.mMaxTextSizePx;
                    } else if (!z2 || (i = this.mLastMeasuredTextLength) < 0 || length <= i) {
                        CharSequence charSequence2 = this.mCarrierText;
                        if (TextUtils.isEmpty(charSequence2)) {
                            f = this.mMaxTextSizePx;
                        } else {
                            int width2 = (getWidth() - getCompoundPaddingLeft()) - getCompoundPaddingRight();
                            float f2 = this.mMaxTextSizePx;
                            if (width2 <= 0) {
                                f = f2;
                            } else if (canFitAtSizePx(charSequence2, f2, width2)) {
                                f = this.mMaxTextSizePx;
                            } else {
                                boolean zCanFitAtSizePx = canFitAtSizePx(charSequence2, this.mMinTextSizePx, width2);
                                float f3 = this.mMinTextSizePx;
                                if (zCanFitAtSizePx) {
                                    float f4 = this.mMaxTextSizePx;
                                    float fMax = Math.max(0.0f, f4 - f3);
                                    if (fMax <= 0.0f) {
                                        iCeil = 0;
                                    } else {
                                        double dMax = fMax / Math.max(0.01f, 0.25f);
                                        iCeil = dMax <= 1.0d ? 1 : (int) Math.ceil(Math.log(dMax) / Math.log(2.0d));
                                    }
                                    for (int i4 = 0; i4 < iCeil && f4 - f3 > 0.25f; i4++) {
                                        float f5 = (f3 + f4) / 2.0f;
                                        if (canFitAtSizePx(charSequence2, f5, width2)) {
                                            f3 = f5;
                                        } else {
                                            f4 = f5;
                                        }
                                    }
                                }
                                f = f3;
                            }
                        }
                    } else {
                        f = this.mMinTextSizePx;
                    }
                    this.mLastAvailableWidth = width;
                    this.mLastMeasuredTextLength = length;
                    this.mTargetSizePx = f;
                }
            }
        }
        this.mTargetSizePx = this.mMaxTextSizePx;
        return Math.abs(this.mTargetSizePx - this.mAppliedSizePx) >= 0.5f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onAttachedToWindow() {
        super/*android.widget.TextView*/.onAttachedToWindow();
        this.mCarrierTextController.setListening(this.mCarrierTextCallback);
        SystemTypefaceHelper companion = SystemTypefaceHelper.Companion.getInstance(this.mContext);
        Objects.requireNonNull(companion);
        OplusQSClock$$ExternalSyntheticLambda0 oplusQSClock$$ExternalSyntheticLambda0 = new OplusQSClock$$ExternalSyntheticLambda0();
        oplusQSClock$$ExternalSyntheticLambda0.f$0 = companion;
        VarHandle.storeStoreFence();
        companion.addCallback(this, oplusQSClock$$ExternalSyntheticLambda0);
        updateEverything();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onConfigurationChanged(Configuration configuration) {
        super/*android.widget.TextView*/.onConfigurationChanged(configuration);
        updateEverything();
    }

    @Override // com.oplus.systemui.qs.widget.AnimateChangeTextView
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mCarrierTextController.removeCallback(this.mCarrierTextCallback);
        SystemTypefaceHelper.Companion.getInstance(this.mContext).removeCallback(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super/*android.widget.TextView*/.onSizeChanged(i, i2, i3, i4);
        LogUtil.i(LogUtil.TAG_QS, "OplusSecondCarrierText", TextFieldBuffer$.ExternalSyntheticOutline0.m(i, i3, "onSizeChanged() w = ", ", oldw ="));
        if (i != i3) {
            updateTextSize$1();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onWindowFocusChanged(boolean z) {
        super/*android.widget.TextView*/.onWindowFocusChanged(z);
        LogUtil.i(LogUtil.TAG_QS, "OplusSecondCarrierText", "onWindowFocusChanged()");
        if (z && getVisibility() == 0) {
            updateTextSize$1();
        }
    }

    @Override // com.oplusos.systemui.common.typeface.SystemTypefaceHelper.SystemUIFontCallback
    public void setFontTypeface(Typeface typeface, int i) {
        setTypeface(typeface);
        this.mLastAvailableWidth = -1;
        this.mLastMeasuredTextLength = -1;
        updateTextSize$1();
    }

    public void setListening(boolean z) {
        if (z == this.mListening) {
            return;
        }
        this.mListening = z;
        if (z) {
            updateEverything();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setMarqueeAllowedByPanel(boolean z) {
        if (z) {
            setEllipsize(TextUtils.TruncateAt.MARQUEE);
            setMarqueeRepeatLimit(1);
            setSelected(true);
        } else {
            setEllipsize(null);
            setSelected(false);
            if (getScrollX() == 0 && getScrollY() == 0) {
                return;
            }
            scrollTo(0, 0);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void updateEverything() {
        float carrierMinTextSize;
        float dimension;
        OplusQSCarrierTextController oplusQSCarrierTextController = this.mCarrierTextController;
        if (oplusQSCarrierTextController == null) {
            return;
        }
        oplusQSCarrierTextController.updateCarrierTextInBgThread(false);
        updateTextColor();
        if (QsColorUtil.getSeparateNotificationAndQSState()) {
            DateViewTextConfig dateViewTextConfig = DateViewTextConfig.INSTANCE;
            dateViewTextConfig.updateScale(this.mContext, new OplusQSDateView$$ExternalSyntheticLambda0());
            float textSize = dateViewTextConfig.getTextSize();
            carrierMinTextSize = dateViewTextConfig.getCarrierMinTextSize();
            dimension = textSize;
        } else {
            dimension = getResources().getDimension(2131171916);
            carrierMinTextSize = getResources().getDimension(2131171917);
        }
        if (Math.abs(dimension - this.mMaxTextSizePx) >= 0.5f || Math.abs(carrierMinTextSize - this.mMinTextSizePx) >= 0.5f) {
            this.mMaxTextSizePx = dimension;
            this.mMinTextSizePx = carrierMinTextSize;
            this.mLastAvailableWidth = -1;
            this.mLastMeasuredTextLength = -1;
        }
        updateTextSize$1();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void updateTextColor() {
        int qSDateTimeColor = NotificationColorUtil.getQSDateTimeColor(this.mContext);
        if (getCurrentTextColor() == qSDateTimeColor) {
            return;
        }
        LogUtil.d(LogUtil.TAG_QS, "OplusSecondCarrierText", "applyTextColor color = " + Integer.toHexString(qSDateTimeColor));
        setTextColor(qSDateTimeColor);
    }

    public final void updateTextSize$1() {
        if (needApplyNewTextSize()) {
            setSuppressTextSizeAnimation(!QsColorUtil.getSeparateNotificationAndQSState());
            float f = this.mTargetSizePx;
            LogUtil.i(LogUtil.TAG_QS, "OplusSecondCarrierText", "applyTextSize sizePx=" + f + " lastSizePx=" + this.mAppliedSizePx);
            setTextSize(0, f);
            this.mAppliedSizePx = f;
        }
    }

    public OplusSecondCarrierText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, (ActivityStarter) DependencyEx.sDependency.getDependency(ActivityStarter.class));
    }
}
