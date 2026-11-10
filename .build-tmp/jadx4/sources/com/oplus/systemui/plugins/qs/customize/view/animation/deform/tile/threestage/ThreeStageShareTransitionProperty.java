package com.oplus.systemui.plugins.qs.customize.view.animation.deform.tile.threestage;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import androidx.dynamicanimation.animation.FloatValueHolder;
import com.android.keyguard.logging.KeyguardLogger$;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.ThreeStageDeformationAnimationController;
import com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView;
import com.oplus.systemui.qs.base.shareanimation.ShareTransitionProperty;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.olivelink.OliveLinkConnector;
import com.oplusos.systemui.common.olivelink.Spring;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class ThreeStageShareTransitionProperty extends ShareTransitionProperty {
    public static final Companion Companion = new Companion();
    private static final String TAG = "ThreeStageShareTransitionProperty";
    private final SpanSize curEndSpanSize;
    private DeformAnimateLister deformAnimateLister;
    public Object endValue;
    public boolean isFirstInit;
    private SpanSize originEndSpanSize;
    private SpringAnimParam springAnimParam;
    private COUISpringAnimation springAnimation;
    public Object startValue;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface DeformAnimateLister {
        void onAnimateEnd(ThreeStageShareTransitionProperty threeStageShareTransitionProperty);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ThreeStageShareTransitionProperty(ViewGroup viewGroup, int i, Object obj, Object obj2) {
        super(viewGroup, i, obj, obj2);
        this.springAnimParam = new SpringAnimParam(0.0f, 1.0f, 0.0f, 0.0f, "", 0.0f);
        this.startValue = obj;
        this.endValue = obj;
        this.isFirstInit = true;
        OplusQSResizeableTileView oplusQSResizeableTileView = viewGroup instanceof OplusQSResizeableTileView ? (OplusQSResizeableTileView) viewGroup : null;
        SpanSize spanSize = (oplusQSResizeableTileView == null || (spanSize = oplusQSResizeableTileView.getTileViewSize()) == null) ? SpanCache.INSTANCE.spanSize(1, 1) : spanSize;
        this.originEndSpanSize = spanSize;
        this.curEndSpanSize = spanSize;
    }

    public static /* synthetic */ COUISpringAnimation createSpringAnimation$default(ThreeStageShareTransitionProperty threeStageShareTransitionProperty, String str, float f, float f2, float f3, int i) {
        if ((i & 4) != 0) {
            f2 = 0.0f;
        }
        return threeStageShareTransitionProperty.createSpringAnimation(str, f, f2, OliveLinkConnector.SEP_QS_SERVER_NAME, (i & 16) != 0 ? 0.0f : f3);
    }

    public void copyAnimaParams(ThreeStageShareTransitionProperty threeStageShareTransitionProperty) {
        threeStageShareTransitionProperty.originEndSpanSize = this.originEndSpanSize;
        threeStageShareTransitionProperty.startValue = this.startValue;
        threeStageShareTransitionProperty.endValue = this.endValue;
        threeStageShareTransitionProperty.springAnimParam = SpringAnimParam.copy$default(this.springAnimParam);
        COUISpringAnimation cOUISpringAnimationCreateSpringAnimation$default = createSpringAnimation$default(threeStageShareTransitionProperty, this.springAnimParam.getName(), this.springAnimParam.getResponse(), 0.0f, 0.0f, 28);
        cOUISpringAnimationCreateSpringAnimation$default.setStartValue(this.springAnimParam.getValue());
        cOUISpringAnimationCreateSpringAnimation$default.setStartVelocity(this.springAnimParam.getVelocity());
        threeStageShareTransitionProperty.setSpringAnimation(cOUISpringAnimationCreateSpringAnimation$default);
    }

    public abstract ThreeStageShareTransitionProperty createShareTransitionCopy(ViewGroup viewGroup);

    public final COUISpringAnimation createSpringAnimation(String str, float f, float f2, String str2, float f3) {
        Spring springSafeSpring$default = OliveLinkConnector.safeSpring$default(OliveLinkConnector.INSTANCE, str2, str, f2, f, 0.0f, 0.0f, 0.0f, null, null, null, 1008);
        this.springAnimParam.setName(str);
        this.springAnimParam.setResponse(f);
        COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(new FloatValueHolder());
        COUISpringForce cOUISpringForce = new COUISpringForce();
        cOUISpringForce.setBounce(springSafeSpring$default.bounce());
        cOUISpringForce.setResponse(springSafeSpring$default.response());
        cOUISpringAnimation.setSpring(cOUISpringForce);
        cOUISpringAnimation.setMinimumVisibleChange(0.00390625f);
        cOUISpringAnimation.setStartValue(f3);
        cOUISpringAnimation.setStartVelocity(springSafeSpring$default.velocity());
        createSpringAnimation.1.2 r14 = new createSpringAnimation.1.2();
        r14.this$0 = this;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addUpdateListener(r14);
        createSpringAnimation.1.3 r15 = new createSpringAnimation.1.3();
        r15.this$0 = this;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addEndListener(r15);
        setSpringAnimation(cOUISpringAnimation);
        return cOUISpringAnimation;
    }

    public final SpanSize getCurEndSpanSize() {
        return this.curEndSpanSize;
    }

    public final DeformAnimateLister getDeformAnimateLister() {
        return this.deformAnimateLister;
    }

    public final Object getEndValue() {
        return this.endValue;
    }

    public final SpringAnimParam getSpringAnimParam() {
        return this.springAnimParam;
    }

    public COUISpringAnimation getSpringAnimation() {
        return this.springAnimation;
    }

    public final Object getStartValue() {
        return this.startValue;
    }

    @Override // com.oplus.systemui.qs.base.shareanimation.ShareTransitionProperty
    public void initValue(ViewGroup viewGroup, View view, boolean z) {
        Object value;
        if (this.isFirstInit) {
            this.isFirstInit = false;
            Iterator<Map.Entry<WeakReference<ViewGroup>, Object>> it = getValuesOfDiffState().entrySet().iterator();
            do {
                value = null;
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry<WeakReference<ViewGroup>, Object> next = it.next();
                if (!Intrinsics.areEqual(next.getKey().get(), getTargetViewHost())) {
                    value = next.getValue();
                }
            } while (value == null);
            if (value != null) {
                setValue(value);
            } else {
                LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m("No element of the map was transformed to a non-null value.");
            }
        }
    }

    @Override // com.oplus.systemui.qs.base.shareanimation.ShareTransitionProperty
    public ShareTransitionProperty moveTo(ViewGroup viewGroup) {
        if (Intrinsics.areEqual(viewGroup, getTargetViewHost())) {
            return this;
        }
        ThreeStageShareTransitionProperty threeStageShareTransitionPropertyCreateShareTransitionCopy = createShareTransitionCopy(viewGroup);
        threeStageShareTransitionPropertyCreateShareTransitionCopy.getValuesOfDiffState().putAll(getValuesOfDiffState());
        threeStageShareTransitionPropertyCreateShareTransitionCopy.isFirstInit = this.isFirstInit;
        threeStageShareTransitionPropertyCreateShareTransitionCopy.ensureSharedElementAndInit(getExpectedFromHost());
        copyAnimaParams(threeStageShareTransitionPropertyCreateShareTransitionCopy);
        COUISpringAnimation springAnimation = threeStageShareTransitionPropertyCreateShareTransitionCopy.getSpringAnimation();
        if (springAnimation != null) {
            boolean zAreEqual = Intrinsics.areEqual(threeStageShareTransitionPropertyCreateShareTransitionCopy.curEndSpanSize, threeStageShareTransitionPropertyCreateShareTransitionCopy.originEndSpanSize);
            SpringAnimParam springAnimParam = threeStageShareTransitionPropertyCreateShareTransitionCopy.springAnimParam;
            springAnimation.animateToFinalPosition(zAreEqual ? springAnimParam.getEndValue() : springAnimParam.getStartValue());
        }
        Object savedValueWhenEnd = getSavedValueWhenEnd();
        if (savedValueWhenEnd != null) {
            threeStageShareTransitionPropertyCreateShareTransitionCopy.setValue(savedValueWhenEnd);
        }
        return threeStageShareTransitionPropertyCreateShareTransitionCopy;
    }

    public final void onAnimateEnd(boolean z) {
        DeformAnimateLister deformAnimateLister;
        if (z || (deformAnimateLister = this.deformAnimateLister) == null) {
            return;
        }
        ((ThreeStageDeformationAnimationController) deformAnimateLister).onAnimateEnd(this);
    }

    public abstract void onAnimateUpdate(float f);

    @Override // com.oplus.systemui.qs.base.shareanimation.ShareTransitionProperty
    public void onTransitionEnd(boolean z) {
        Context context;
        Resources resources;
        COUISpringAnimation springAnimation = getSpringAnimation();
        if (springAnimation != null) {
            springAnimation.cancel();
        }
        boolean zAreEqual = Intrinsics.areEqual(this.curEndSpanSize, this.originEndSpanSize);
        Object value = getValue();
        ViewGroup targetViewHost = getTargetViewHost();
        String resourceEntryName = (targetViewHost == null || (context = targetViewHost.getContext()) == null || (resources = context.getResources()) == null) ? null : resources.getResourceEntryName(getSharedElementId());
        boolean inOverlay = getInOverlay();
        float value2 = this.springAnimParam.getValue();
        Object obj = this.startValue;
        Object obj2 = this.endValue;
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("onTransitionEnd: id = ", resourceEntryName, ", targetSame = ", zAreEqual, ", inOverlay = ");
        sbM.append(inOverlay);
        sbM.append(", value = ");
        sbM.append(value);
        sbM.append(", animValue = ");
        sbM.append(value2);
        sbM.append(", startValue = ");
        sbM.append(obj);
        sbM.append(", endValue = ");
        sbM.append(obj2);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, sbM.toString(), null, 8);
        if (zAreEqual) {
            if (getInOverlay()) {
                setValue(this.startValue);
            } else {
                setValue(this.endValue);
            }
        } else if (getInOverlay()) {
            setValue(this.endValue);
        } else {
            setValue(this.startValue);
        }
        super.onTransitionEnd(false);
        setSavedValueWhenEnd(value);
        ViewGroup targetViewHost2 = getTargetViewHost();
        if (targetViewHost2 != null) {
            ShareTransitionProperty.Companion.clearIdCache(targetViewHost2, getSharedElementId());
        }
    }

    public final void setDeformAnimateLister(DeformAnimateLister deformAnimateLister) {
        this.deformAnimateLister = deformAnimateLister;
    }

    public final void setEndValue(Object obj) {
        this.endValue = obj;
    }

    @Override // com.oplus.systemui.qs.base.shareanimation.ShareTransitionProperty
    public final void setProgress(float f, float f2, Object obj, Object obj2) {
        Context context;
        Resources resources;
        ViewGroup targetViewHost = getTargetViewHost();
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "startTransition: id = " + ((targetViewHost == null || (context = targetViewHost.getContext()) == null || (resources = context.getResources()) == null) ? null : resources.getResourceEntryName(getSharedElementId())) + ", startValue = " + obj2 + ", endValue = " + obj, null, 8);
        this.startValue = obj2;
        this.endValue = obj;
        startTransition(obj2, obj);
    }

    public final void setSpringAnimParam(SpringAnimParam springAnimParam) {
        this.springAnimParam = springAnimParam;
    }

    public void setSpringAnimation(COUISpringAnimation cOUISpringAnimation) {
        this.springAnimation = cOUISpringAnimation;
    }

    public final void setStartValue(Object obj) {
        this.startValue = obj;
    }

    public abstract void startTransition(Object obj, Object obj2);
}
