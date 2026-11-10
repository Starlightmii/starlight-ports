package com.oplus.systemui.notification.row.overlay;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.car.app.serialization.Bundler$;
import androidx.dynamicanimation.animation.FloatValueHolder;
import com.android.systemui.statusbar.notification.collection.render.NodeSpecBuilderLogger$;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import com.oplus.systemui.aod.aodclock.off.AodClockLayout$3$;
import com.oplus.systemui.notification.row.material.MaterialColorMode;
import com.oplus.systemui.notification.row.material.NotificationColorPalette;
import com.oplus.systemui.notification.row.material.NotificationMaterialColorEx;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationInfoTextView extends AppCompatTextView {
    public static final Companion Companion = new Companion();
    private static final String EMPTY_STRING = "";
    private static final String EMPTY_STRING_FOR_LOG = "empty";
    private static final String TAG = "NotificationInfoTextView";
    public FadeOutInAnimation fadeSpringAnimation;
    private final String logTag;
    private final Lazy notificationMaterialColorEx$delegate;
    private CharSequence pendingText;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class FadeOutInAnimation {
        private final COUISpringAnimation fadeSpringAnimation;
        public boolean isFadeOut;
        private final String logTag;
        private Function0 onFadeOutFinish = new NodeSpecBuilderLogger$.ExternalSyntheticLambda0();
        private Function1 onUpdate = new NotificationInfoTextView$FadeOutInAnimation$$ExternalSyntheticLambda0();

        public FadeOutInAnimation(String str) {
            this.logTag = str;
            COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(new FloatValueHolder());
            cOUISpringAnimation.setSpring(new COUISpringForce().setBounce(0.0f).setResponse(0.25f));
            cOUISpringAnimation.setStartValue(0.0f);
            cOUISpringAnimation.setMinimumVisibleChange(0.001f);
            NotificationInfoTextView$FadeOutInAnimation$fadeSpringAnimation$1$1 notificationInfoTextView$FadeOutInAnimation$fadeSpringAnimation$1$1 = new NotificationInfoTextView$FadeOutInAnimation$fadeSpringAnimation$1$1();
            notificationInfoTextView$FadeOutInAnimation$fadeSpringAnimation$1$1.this$0 = this;
            notificationInfoTextView$FadeOutInAnimation$fadeSpringAnimation$1$1.$this_apply = cOUISpringAnimation;
            VarHandle.storeStoreFence();
            cOUISpringAnimation.addUpdateListener(notificationInfoTextView$FadeOutInAnimation$fadeSpringAnimation$1$1);
            this.fadeSpringAnimation = cOUISpringAnimation;
        }

        public final void fadeOutIn(boolean z, Function1 function1, Function0 function0) {
            String str = this.logTag;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                AodClockLayout$3$.ExternalSyntheticOutline0.m("fadeOutIn: justFadeIn=", "SystemUi--Notification", Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR), z);
            }
            this.onFadeOutFinish = function0;
            this.onUpdate = function1;
            if (!z) {
                this.isFadeOut = true;
                this.fadeSpringAnimation.animateToFinalPosition(0.0f);
            } else {
                function0.invoke();
                this.isFadeOut = false;
                this.fadeSpringAnimation.animateToFinalPosition(1.0f);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean $r8$lambda$AXXhxPdoW41WaM17KA_iPL4j9v0(NotificationInfoTextView notificationInfoTextView) {
        String str = notificationInfoTextView.logTag;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            sbM.append("onFadeOutFinish: pendingText=" + ((Object) notificationInfoTextView.pendingText) + ", text=" + ((Object) notificationInfoTextView.getText()));
            Log.d("SystemUi--Notification", sbM.toString());
        }
        CharSequence charSequence = notificationInfoTextView.pendingText;
        if (charSequence != null) {
            notificationInfoTextView.setText(charSequence);
            notificationInfoTextView.pendingText = null;
        }
        return charSequence != null && charSequence.length() > 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public NotificationInfoTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.logTag = ListPopupWindow$.ExternalSyntheticOutline0.m(hashCode(), "NotificationInfoTextView-");
        this.notificationMaterialColorEx$delegate = LazyKt__LazyJVMKt.lazy(new NotificationInfoTextView$$ExternalSyntheticLambda0());
    }

    private final NotificationMaterialColorEx getNotificationMaterialColorEx() {
        return (NotificationMaterialColorEx) this.notificationMaterialColorEx$delegate.getValue();
    }

    private final int getOverlayTextColor() {
        return NotificationColorPalette.Companion.from((MaterialColorMode) getNotificationMaterialColorEx().getCurrentMaterialColor().getValue()).getPrimaryColor();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onAttachedToWindow() {
        super/*android.widget.TextView*/.onAttachedToWindow();
        setTextColor(getOverlayTextColor());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setTextWithAnimation(CharSequence charSequence) {
        CharSequence charSequence2 = EMPTY_STRING;
        if (charSequence == null) {
            charSequence = EMPTY_STRING;
        }
        CharSequence text = getText();
        if (text != null) {
            charSequence2 = text;
        }
        if ((charSequence2.equals(charSequence) && this.pendingText == null) || Intrinsics.areEqual(this.pendingText, charSequence)) {
            return;
        }
        this.pendingText = charSequence;
        String str = this.logTag;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            CharSequence charSequence3 = charSequence2.length() == 0 ? EMPTY_STRING_FOR_LOG : charSequence2;
            if (charSequence.length() == 0) {
                charSequence = EMPTY_STRING_FOR_LOG;
            }
            sbM.append("setTextWithAnimation: " + ((Object) charSequence3) + " -> " + ((Object) charSequence));
            Log.d("SystemUi--Notification", sbM.toString());
        }
        boolean z = Intrinsics.areEqual(this.pendingText, charSequence2) || charSequence2.length() == 0 || getAlpha() <= 0.05f;
        if (this.fadeSpringAnimation == null) {
            this.fadeSpringAnimation = new FadeOutInAnimation(this.logTag);
        }
        FadeOutInAnimation fadeOutInAnimation = this.fadeSpringAnimation;
        if (fadeOutInAnimation == null) {
            fadeOutInAnimation = null;
        }
        NotificationInfoTextView$changeText$1 notificationInfoTextView$changeText$1 = new NotificationInfoTextView$changeText$1(1, this, NotificationInfoTextView.class, "setAlpha", "setAlpha(F)V", 0);
        NotificationInfoTextView$.ExternalSyntheticLambda1 externalSyntheticLambda1 = new NotificationInfoTextView$.ExternalSyntheticLambda1();
        externalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        fadeOutInAnimation.fadeOutIn(z, notificationInfoTextView$changeText$1, externalSyntheticLambda1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public String toString() {
        String string = super/*android.widget.TextView*/.toString();
        CharSequence text = getText();
        CharSequence charSequence = this.pendingText;
        return string + "text=" + ((Object) text) + ", pendingText=" + ((Object) charSequence) + ", alpha=" + getAlpha();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void updateResource$1() {
        int dimensionPixelOffset = getContext().getResources().getDimensionPixelOffset(2131172957);
        setPadding(dimensionPixelOffset, 0, dimensionPixelOffset, getContext().getResources().getDimensionPixelOffset(2131172956));
        setTextColor(getOverlayTextColor());
        setTextSize(0, getContext().getResources().getDimensionPixelSize(2131172958));
        setTypeface(getTypeface());
    }

    public NotificationInfoTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public /* synthetic */ NotificationInfoTextView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public NotificationInfoTextView(Context context) {
        this(context, null, 0, 6, null);
    }
}
