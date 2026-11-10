package com.oplus.systemui.separate;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import android.util.MathUtils;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.collection.FloatList$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import androidx.core.view.insets.Protection$;
import androidx.fragment.app.Fragment$5$;
import com.android.internal.dynamicanimation.animation.FloatValueHolder;
import com.android.internal.dynamicanimation.animation.SpringAnimation;
import com.android.internal.dynamicanimation.animation.SpringForce;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.launcher3.icons.IconProvider$;
import com.android.systemui.DependencyEx;
import com.android.systemui.panelanimation.LevelPositionAlgorithm;
import com.android.systemui.panelanimation.PanelAnimationEx;
import com.android.systemui.separate.OplusSimpleQSManager;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.coui.appcompat.springchain.COUIChainItem;
import com.coui.appcompat.springchain.COUIGridSpringChain;
import com.coui.appcompat.springchain.api.IChainItem;
import com.coui.appcompat.springchain.api.ISpringUpdateListener;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.systemui.util.OplusSpringAnimationExtKt;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeparatePanelAnimation implements LevelPositionAlgorithm.LevelPosition {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SeparatePanelAnimation";
    public static float extraSimpleQSFootTranslationY;
    private final Context context;
    public int currentChildCount;
    private final SeparatePanelAnimation$elementShowStateChangeListener$1 elementShowStateChangeListener;
    public float firstItemTranslation;
    private final SimpleQSHeaderTySmoother headerTySmoother;
    public boolean inTracking;
    public boolean lastFirstElementShowing;
    public float lastTrackingMoveDistance;
    public float maxPositionForFirstItem;
    private final SpringAnimation moveDistanceAnimation;
    public final PanelAnimationEx panelAnimationEx;
    private final Lazy panelViewController;
    private COUIGridSpringChain scrollGridChain;
    private ViewGroup simpleQSHeader;
    private NotificationStackScrollLayout stackScrollLayout;
    public int thresholdTopArea;
    private final COUIGridSpringChain.TransCalculator transCalculator;
    public int screenHeight = 2440;
    public float firstItemMaxTranslation = 1952.0f;
    private final Set<LevelPositionAlgorithm.LevelPositionAnimationListener> callbacks = new LinkedHashSet();

    /* JADX INFO: renamed from: com.oplus.systemui.separate.SeparatePanelAnimation$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements ISpringUpdateListener, COUIGridSpringChain.TransCalculator {
        public /* synthetic */ SeparatePanelAnimation this$0;

        /* JADX WARN: Code duplicated, block: B:26:0x003f A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:27:0x0040 A[RETURN] */
        public float getTrans(float f, int i, int i2) {
            float f2;
            float fPow;
            float fCoerceIn = RangesKt___RangesKt.coerceIn(f, 0.0f, this.this$0.firstItemMaxTranslation);
            if (i != 0) {
                if (i == 1) {
                    fCoerceIn *= 0.2f;
                } else {
                    if (i == 2) {
                        f2 = fCoerceIn * 0.2f;
                        fPow = 0.75f;
                    } else if (3 > i || i >= 16) {
                        fCoerceIn = 0.0f;
                    } else {
                        f2 = fCoerceIn * 0.2f;
                        fPow = (float) Math.pow(0.75d, i - 1);
                    }
                    fCoerceIn = f2 * fPow;
                }
            }
            if (i2 == 1) {
                if (fCoerceIn < 0.0f) {
                    return 0.0f;
                }
                return fCoerceIn;
            }
            if (i2 != 2) {
                return (int) fCoerceIn;
            }
            if (fCoerceIn > 0.0f) {
                return 0.0f;
            }
            return fCoerceIn;
        }

        public void onUpdate(int i, float f, int i2, IChainItem iChainItem) {
            float f2;
            ViewGroup viewGroup;
            SeparatePanelAnimation separatePanelAnimation = this.this$0;
            NotificationStackScrollLayout notificationStackScrollLayout = separatePanelAnimation.stackScrollLayout;
            if (notificationStackScrollLayout != null) {
                notificationStackScrollLayout.requestChildrenAndTopPaddingUpdate();
            }
            if (i == 0) {
                separatePanelAnimation.firstItemTranslation = f;
                SimpleQSHeaderTySmoother simpleQSHeaderTySmoother = separatePanelAnimation.headerTySmoother;
                if (f <= 0.0f) {
                    simpleQSHeaderTySmoother.delta = 0.0f;
                    simpleQSHeaderTySmoother.reboundStartDelta = 0.0f;
                    simpleQSHeaderTySmoother.reboundStartLayerTranslation = 0.0f;
                    f2 = 0.0f;
                } else {
                    float f3 = simpleQSHeaderTySmoother.delta;
                    if (f3 != 0.0f) {
                        if (simpleQSHeaderTySmoother.reboundStartLayerTranslation <= 0.0f) {
                            simpleQSHeaderTySmoother.reboundStartDelta = f3;
                            simpleQSHeaderTySmoother.reboundStartLayerTranslation = f < 1.0f ? 1.0f : f;
                        }
                        simpleQSHeaderTySmoother.delta = simpleQSHeaderTySmoother.reboundStartDelta * RangesKt___RangesKt.coerceIn(f / simpleQSHeaderTySmoother.reboundStartLayerTranslation, 0.0f, 1.0f);
                    }
                    f2 = simpleQSHeaderTySmoother.delta;
                }
                if (SeparatePanelAnimation.Companion.getExtraSimpleQSFootTranslationY() == 0.0f && (viewGroup = separatePanelAnimation.simpleQSHeader) != null) {
                    viewGroup.setTranslationY(f2 + f);
                }
            }
            NotificationStackScrollLayout notificationStackScrollLayout2 = separatePanelAnimation.stackScrollLayout;
            int notGoneChildCount = notificationStackScrollLayout2 != null ? notificationStackScrollLayout2.getNotGoneChildCount() - 1 : 0;
            if (i == notGoneChildCount && f < 1.0f && f > 0.0f) {
                float f4 = separatePanelAnimation.firstItemTranslation;
                if (f4 < 1.0f && f4 >= 0.0f && separatePanelAnimation.scrollGridChain.isSpringSystemRunning() > 0 && separatePanelAnimation.scrollGridChain.getSpringChainY().getAllSprings().size() > 0) {
                    LogUtil.i$default(LogUtil.TAG_NOTIFICATION, SeparatePanelAnimation.TAG, Protection$.ExternalSyntheticOutline0.m(separatePanelAnimation.firstItemTranslation, "onUpdate releaseSpringComplete, firstItemTranslation: "), null, 8);
                    separatePanelAnimation.scrollGridChain.releaseSpringComplete();
                }
            }
            if (i == notGoneChildCount && f == 0.0f) {
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, SeparatePanelAnimation.TAG, "onUpdate notifyFractionEnd", null, 8);
                SeparatePanelAnimation.access$notifyAnimationEnd(separatePanelAnimation);
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final float getExtraSimpleQSFootTranslationY() {
            return SeparatePanelAnimation.extraSimpleQSFootTranslationY;
        }

        public final void setExtraSimpleQSFootTranslationY(float f) {
            SeparatePanelAnimation.extraSimpleQSFootTranslationY = f;
        }
    }

    public SeparatePanelAnimation(Context context, Lazy lazy) {
        this.context = context;
        this.panelViewController = lazy;
        PanelAnimationEx panelAnimationEx = (PanelAnimationEx) DependencyEx.sDependency.getDependency(PanelAnimationEx.class);
        this.panelAnimationEx = panelAnimationEx;
        SimpleQSHeaderTySmoother simpleQSHeaderTySmoother = new SimpleQSHeaderTySmoother();
        VarHandle.storeStoreFence();
        this.headerTySmoother = simpleQSHeaderTySmoother;
        this.lastTrackingMoveDistance = Float.NaN;
        SpringAnimation springAnimation = new SpringAnimation(new FloatValueHolder());
        springAnimation.setSpring(new SpringForce().setDampingRatio(1.0f).setStiffness(1000.0f));
        springAnimation.setStartValue(-this.thresholdTopArea);
        springAnimation.setMinimumVisibleChange(0.001f);
        SeparatePanelAnimation$moveDistanceAnimation$1$1 separatePanelAnimation$moveDistanceAnimation$1$1 = new SeparatePanelAnimation$moveDistanceAnimation$1$1();
        separatePanelAnimation$moveDistanceAnimation$1$1.this$0 = this;
        VarHandle.storeStoreFence();
        springAnimation.addUpdateListener(separatePanelAnimation$moveDistanceAnimation$1$1);
        this.moveDistanceAnimation = springAnimation;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.transCalculator = anonymousClass1;
        SeparatePanelAnimation$elementShowStateChangeListener$1 separatePanelAnimation$elementShowStateChangeListener$1 = new SeparatePanelAnimation$elementShowStateChangeListener$1();
        separatePanelAnimation$elementShowStateChangeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.elementShowStateChangeListener = separatePanelAnimation$elementShowStateChangeListener$1;
        int i = Settings.System.getInt(context.getContentResolver(), "setting_main_tension1", 150);
        int i2 = Settings.System.getInt(context.getContentResolver(), "setting_main_friction1", 30);
        int i3 = Settings.System.getInt(context.getContentResolver(), "setting_attachment_tension1", 450);
        int i4 = Settings.System.getInt(context.getContentResolver(), "setting_attachment_friction1", 20);
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, Fragment$5$.ExternalSyntheticOutline0.m(FloatList$.ExternalSyntheticOutline0.m("settingMainTension: ", i, i2, ", settingMainFriction: ", ", settingAttachmentTension: "), i3, i4, " settingAttachmentFriction: "), null, 8);
        COUIGridSpringChain cOUIGridSpringChain = new COUIGridSpringChain(i, i2, i3, i4, i, i2, i3, i4);
        this.scrollGridChain = cOUIGridSpringChain;
        AnonymousClass1 anonymousClass2 = new AnonymousClass1();
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        cOUIGridSpringChain.setSpringUpdateListener(anonymousClass2);
        this.scrollGridChain.setTranCalculator(anonymousClass1, 1);
        panelAnimationEx.getElementShowState().addCallback(separatePanelAnimation$elementShowStateChangeListener$1);
    }

    public static final void access$notifyAnimationEnd(SeparatePanelAnimation separatePanelAnimation) {
        synchronized (separatePanelAnimation.callbacks) {
            Iterator<T> it = separatePanelAnimation.callbacks.iterator();
            while (it.hasNext()) {
                ((LevelPositionAlgorithm.LevelPositionAnimationListener) it.next()).onAnimationEnd();
            }
        }
    }

    public static final void access$updatePosition(SeparatePanelAnimation separatePanelAnimation, float f) {
        HeaderTyTrackingGesture headerTyTrackingGesture;
        ViewGroup viewGroup;
        PanelAnimationEx panelAnimationEx = separatePanelAnimation.panelAnimationEx;
        if (separatePanelAnimation.screenHeight <= 0) {
            separatePanelAnimation.screenHeight = 2440;
            separatePanelAnimation.maxPositionForFirstItem = 488.0f;
        }
        if (Float.isNaN(0.2f * f) && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "SeparatePanelAnimation-->firstViewDistance is NaN");
        }
        float fExp = (float) ((((double) f) * 0.20000000298023224d) + ((1.0d - Math.exp(((double) (-f)) / 200.0d)) * 120.00000178813934d));
        float f2 = separatePanelAnimation.maxPositionForFirstItem;
        if (fExp >= f2) {
            fExp = f2;
        }
        separatePanelAnimation.scrollGridChain.updateMoveTranslation(fExp, 1);
        boolean zIsFirstElementShowing = panelAnimationEx.isFirstElementShowing();
        boolean z = separatePanelAnimation.lastFirstElementShowing;
        boolean z2 = (zIsFirstElementShowing == z || z) ? false : true;
        float currentSpringY = separatePanelAnimation.scrollGridChain.getCurrentSpringY(0);
        if (z2) {
            ViewGroup viewGroup2 = separatePanelAnimation.simpleQSHeader;
            float translationY = viewGroup2 != null ? viewGroup2.getTranslationY() : currentSpringY;
            SimpleQSHeaderTySmoother simpleQSHeaderTySmoother = separatePanelAnimation.headerTySmoother;
            simpleQSHeaderTySmoother.delta = translationY - currentSpringY;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(MonoSpline$.ExternalSyntheticOutline0.m("onSeamTransition delta:", simpleQSHeaderTySmoother.delta, " currentHeaderTy:", translationY, " chainTy:"), currentSpringY, "SimpleQSHeaderTySmoother-->", "SystemUi--Notification");
            }
        } else if (panelAnimationEx.isFirstElementShowing()) {
            if (Float.isNaN(separatePanelAnimation.lastTrackingMoveDistance)) {
                separatePanelAnimation.lastTrackingMoveDistance = f;
                headerTyTrackingGesture = HeaderTyTrackingGesture.IDLE;
            } else {
                float f3 = f - separatePanelAnimation.lastTrackingMoveDistance;
                separatePanelAnimation.lastTrackingMoveDistance = f;
                if (f3 > 1.0f) {
                    headerTyTrackingGesture = HeaderTyTrackingGesture.EXPANDING;
                } else {
                    headerTyTrackingGesture = f3 < -1.0f ? HeaderTyTrackingGesture.COLLAPSING : HeaderTyTrackingGesture.IDLE;
                }
            }
            separatePanelAnimation.headerTySmoother.onTrackingFrame(headerTyTrackingGesture);
        }
        separatePanelAnimation.lastFirstElementShowing = panelAnimationEx.isFirstElementShowing();
        if (!panelAnimationEx.isFirstElementShowing() || (viewGroup = separatePanelAnimation.simpleQSHeader) == null) {
            return;
        }
        viewGroup.setTranslationY(currentSpringY + separatePanelAnimation.headerTySmoother.delta);
    }

    public static /* synthetic */ void getTransCalculator$annotations() {
    }

    public void addCallback(LevelPositionAlgorithm.LevelPositionAnimationListener levelPositionAnimationListener) {
        synchronized (this.callbacks) {
            this.callbacks.add(levelPositionAnimationListener);
        }
    }

    public final Context getContext() {
        return this.context;
    }

    public final boolean getInTracking() {
        return this.inTracking;
    }

    public final float getOverScrollHeight() {
        float paddingWithIndex = 0.0f;
        for (int i = 0; i < 2; i++) {
            paddingWithIndex += getPaddingWithIndex(i);
        }
        return paddingWithIndex;
    }

    public final float getPaddingWithIndex(int i) {
        float currentSpringY;
        float f = 0.0f;
        if (i == 0) {
            currentSpringY = this.scrollGridChain.getCurrentSpringY(i);
        } else {
            currentSpringY = (1 > i || i >= 16) ? 0.0f : this.scrollGridChain.getCurrentSpringY(i) - this.scrollGridChain.getCurrentSpringY(i - 1);
        }
        if (Float.isNaN(currentSpringY)) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(currentSpringY, "padding: ", " may be wrong"), null, 8);
        } else {
            f = currentSpringY;
        }
        float f2 = this.screenHeight;
        return f > f2 ? f2 : f;
    }

    public final Lazy getPanelViewController() {
        return this.panelViewController;
    }

    public final COUIGridSpringChain.TransCalculator getTransCalculator() {
        return this.transCalculator;
    }

    public final boolean isInReboundAnimation() {
        return !this.inTracking && this.scrollGridChain.isSpringSystemRunning() > 0;
    }

    public void removeCallback(LevelPositionAlgorithm.LevelPositionAnimationListener levelPositionAnimationListener) {
        synchronized (this.callbacks) {
            this.callbacks.remove(levelPositionAnimationListener);
        }
    }

    public final void resetSpringAnimation() {
        this.scrollGridChain.releaseSpringAndLoc();
    }

    public final void setInTracking(boolean z) {
        if (this.inTracking == z) {
            return;
        }
        this.inTracking = z;
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("inTracking ", z), null, 8);
    }

    public final void startTracking(Context context, NotificationStackScrollLayout notificationStackScrollLayout, float f, int i) {
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "startTracking", null, 8);
        this.stackScrollLayout = notificationStackScrollLayout;
        this.maxPositionForFirstItem = f;
        this.screenHeight = i;
        this.firstItemMaxTranslation = i * 0.8f;
        setInTracking(true);
        this.lastTrackingMoveDistance = Float.NaN;
        SimpleQSHeaderTySmoother simpleQSHeaderTySmoother = this.headerTySmoother;
        simpleQSHeaderTySmoother.delta = 0.0f;
        simpleQSHeaderTySmoother.reboundStartDelta = 0.0f;
        simpleQSHeaderTySmoother.reboundStartLayerTranslation = 0.0f;
        if (this.scrollGridChain.isSpringSystemRunning() > 0) {
            this.scrollGridChain.releaseSpring();
        }
        this.simpleQSHeader = ((OplusSimpleQSManager) DependencyEx.sDependency.getDependency(OplusSimpleQSManager.class)).getHeader();
        OplusSpringAnimationExtKt.resetTo(this.moveDistanceAnimation, 0.0f);
        this.thresholdTopArea = ColumnWidthUtil.dp2px(context, 24.0f);
    }

    public final void stopTracking() {
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("stopTracking, inTracking: ", this.inTracking), null, 8);
        if (this.moveDistanceAnimation.isRunning()) {
            this.moveDistanceAnimation.cancel();
        }
        if (this.inTracking) {
            setInTracking(false);
            SimpleQSHeaderTySmoother simpleQSHeaderTySmoother = this.headerTySmoother;
            simpleQSHeaderTySmoother.reboundStartDelta = 0.0f;
            simpleQSHeaderTySmoother.reboundStartLayerTranslation = 0.0f;
            OplusSpringAnimationExtKt.resetTo(this.moveDistanceAnimation, 0.0f);
            this.scrollGridChain.startRebound(1);
        }
    }

    public final void updateMoveDistance(float f, float f2, boolean z) {
        int iIsSpringSystemRunning = this.scrollGridChain.isSpringSystemRunning();
        if (iIsSpringSystemRunning > 0) {
            this.scrollGridChain.releaseSpring();
        }
        if (f == 0.0f && this.moveDistanceAnimation.getSpring().getFinalPosition() == 0.0f) {
            return;
        }
        float fLerp = MathUtils.lerp(1000.0f, 100.0f, RangesKt___RangesKt.coerceIn(f2, 0.0f, 1.0f));
        SpringAnimation springAnimation = this.moveDistanceAnimation;
        if (z) {
            springAnimation.getSpring().setStiffness(1000.0f);
        } else {
            float f3 = 1000.0f - fLerp;
            springAnimation.getSpring().setStiffness(f3 >= 100.0f ? f3 : 100.0f);
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            float stiffness = this.moveDistanceAnimation.getSpring().getStiffness();
            StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("updateMoveDistance, distance: ", f, " ", f, " hasSpring ");
            IconProvider$.ExternalSyntheticOutline0.m(f2, iIsSpringSystemRunning, " fraction: ", " moveDistanceAnimation.spring.stiffness ", sbM);
            VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(sbM, stiffness, "SeparatePanelAnimation-->", "SystemUi--Notification");
        }
        this.moveDistanceAnimation.animateToFinalPosition(f);
    }

    public final void updateVisibleChildCount(int i) {
        int i2 = i + 1;
        int i3 = this.currentChildCount;
        if (i3 != i2) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, TextFieldBuffer$.ExternalSyntheticOutline0.m(i3, i2, "updateVisibleChildCount change, currentChildCount: ", " totalCount "), null, 8);
            this.currentChildCount = i2;
            this.scrollGridChain.clearAllItems();
            int i4 = this.currentChildCount;
            for (int i5 = 0; i5 < i4; i5++) {
                this.scrollGridChain.addItem(new COUIChainItem((View) null, 0, i5, 1, 1, false));
            }
        }
    }
}
