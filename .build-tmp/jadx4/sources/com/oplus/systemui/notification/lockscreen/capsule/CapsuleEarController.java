package com.oplus.systemui.notification.lockscreen.capsule;

import android.util.Log;
import androidx.appcompat.widget.AppCompatImageView;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl$2$;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.ref.WeakReference;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleEarController {
    public static final Companion Companion = new Companion();
    private static final String KEY_BASE_TRANSITION_Y = "base";
    private static final String TAG = "CapsuleEarController";
    private WeakReference<CapsuleItemView> baseCard;
    public float baseTransitionY;
    public boolean currentIsShow;
    private String holdBaseTransitionY;
    private final CapsuleEarView leftEarView;
    public boolean needIntercept;
    public boolean needShow;
    private final CapsuleEarView rightEarView;
    private String usedTransitionYKey = "base";
    private final Map<String, Float> transitionYRequest = new LinkedHashMap();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public CapsuleEarController(CapsuleEarView capsuleEarView, CapsuleEarView capsuleEarView2) {
        this.leftEarView = capsuleEarView;
        this.rightEarView = capsuleEarView2;
    }

    private final float getTransitionY() {
        return this.rightEarView.getTranslationY();
    }

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
    private final void setTransitionY(float f) {
        this.leftEarView.setTranslationY(f);
        this.rightEarView.setTranslationY(f);
    }

    public final void checkAndClearAllTransitionY() {
        if (this.transitionYRequest.isEmpty()) {
            return;
        }
        LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "checkAndClearAllTransitionY: " + this.transitionYRequest.keySet());
        this.transitionYRequest.clear();
    }

    public final void clearTransitionY(String str) {
        this.transitionYRequest.remove(str);
        updateTransitionY();
    }

    public final WeakReference<CapsuleItemView> getBaseCard() {
        return this.baseCard;
    }

    public final boolean getCurrentIsShow() {
        return this.currentIsShow;
    }

    public final float getEarAlpha() {
        return this.leftEarView.getAlpha();
    }

    public final boolean getEarVisible() {
        return this.leftEarView.getVisibility() == 0 || this.rightEarView.getVisibility() == 0;
    }

    public final String getHoldBaseTransitionY() {
        return this.holdBaseTransitionY;
    }

    public final CapsuleEarView getLeftEarView() {
        return this.leftEarView;
    }

    public final boolean getNeedIntercept() {
        return this.needIntercept;
    }

    public final boolean getNeedShow() {
        return this.needShow;
    }

    public final CapsuleEarView getRightEarView() {
        return this.rightEarView;
    }

    public final float getTransitionX() {
        return this.rightEarView.getTranslationX();
    }

    public final boolean isUsedSelfTransitionY(String str) {
        return Intrinsics.areEqual(str, this.usedTransitionYKey);
    }

    public final void requestTransitionY(String str, float f) {
        this.transitionYRequest.put(str, Float.valueOf(f));
        updateTransitionY();
    }

    public final void setBaseCard(WeakReference<CapsuleItemView> weakReference) {
        this.baseCard = weakReference;
    }

    public final void setCurrentIsShow(boolean z) {
        this.currentIsShow = z;
    }

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
    public final void setEarAlpha(float f) {
        this.leftEarView.setTransitionAlpha(f);
        this.rightEarView.setTransitionAlpha(f);
    }

    public final void setEarVisible(boolean z) {
        if (this.needIntercept) {
            return;
        }
        AppCompatImageView appCompatImageView = this.leftEarView;
        if (z) {
            appCompatImageView.setVisibility(0);
            this.rightEarView.setVisibility(0);
        } else {
            appCompatImageView.setVisibility(4);
            this.rightEarView.setVisibility(4);
        }
    }

    public final void setHoldBaseTransitionY(String str) {
        this.holdBaseTransitionY = str;
        updateTransitionY();
    }

    public final void setNeedIntercept(boolean z) {
        this.needIntercept = z;
    }

    public final void setNeedShow(boolean z) {
        this.needShow = z;
    }

    public final void setScrimAlpha(float f) {
        this.leftEarView.setScrimAlpha(f);
        this.rightEarView.setScrimAlpha(f);
    }

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
    public final void setTransitionX(float f) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m("EarController.transitionX:", f, ", ", this.leftEarView.getTranslationX(), "CapsuleEarController-->"));
        }
        this.leftEarView.setTranslationX(f);
        this.rightEarView.setTranslationX(-f);
    }

    public final void updateTransitionY() {
        String str = this.holdBaseTransitionY;
        float fFloatValue = (str == null || this.transitionYRequest.get(str) != null) ? 0.0f : this.baseTransitionY;
        String key = "base";
        for (Map.Entry<String, Float> entry : this.transitionYRequest.entrySet()) {
            if (entry.getValue().floatValue() > fFloatValue) {
                key = entry.getKey();
                fFloatValue = entry.getValue().floatValue();
            }
        }
        if (fFloatValue == 0.0f) {
            fFloatValue = this.baseTransitionY;
        }
        this.usedTransitionYKey = key;
        if (getTransitionY() == fFloatValue) {
            return;
        }
        setTransitionY(fFloatValue);
    }
}
