package com.oplus.systemui.statusbar.notification.row.menupanel;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Insets;
import android.graphics.Rect;
import android.util.Log;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.view.animation.PathInterpolator;
import android.widget.ImageView;
import androidx.compose.foundation.gestures.DefaultDraggableAnchors$;
import androidx.core.view.OneShotPreDrawListener;
import androidx.tracing.Trace;
import com.android.systemui.plugins.statusbar.NotificationMenuRowPlugin;
import com.android.systemui.statusbar.notification.row.ActivatableNotificationView;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableOutlineView;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundView;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundViewExt;
import com.android.systemui.util.LargeScreenUtils;
import com.oplus.systemui.aod.anim.OplusAODMaskAnimController$;
import com.oplus.systemui.notification.blur.ViewBlurManager;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationRowCloner implements NotificationViewCloner {
    public static final Companion Companion = new Companion();
    private static final PathInterpolator INTERPOLATOR = new PathInterpolator(0.4f, 0.0f, 0.2f, 1.0f);
    private static final String TAG = "NotificationRowCloner";
    private final Context context;
    public int menuContentWidth;
    public int menuHorizontalMargin;
    private final ViewBlurManager viewBlurManager;
    private final WindowManager windowManager;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final void captureCanvas(Canvas canvas, ExpandableNotificationRow expandableNotificationRow) {
            float translation = expandableNotificationRow.getTranslation();
            canvas.save();
            if (translation != 0.0f) {
                canvas.translate(-translation, 0.0f);
            }
            ((ExpandableOutlineView) expandableNotificationRow).mOutlineViewEx.setSkipClipForCapture(true);
            ((ActivatableNotificationView) expandableNotificationRow).mActivatableViewEx.setSkipNotificationSpotLightForCapture(true);
            try {
                expandableNotificationRow.draw(canvas);
            } finally {
                ((ActivatableNotificationView) expandableNotificationRow).mActivatableViewEx.setSkipNotificationSpotLightForCapture(false);
                ((ExpandableOutlineView) expandableNotificationRow).mOutlineViewEx.setSkipClipForCapture(false);
                canvas.restore();
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.row.menupanel.NotificationRowCloner$clone$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return NotificationRowCloner.this.clone((ExpandableNotificationRow) null, (ViewBlurProxy) null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.row.menupanel.NotificationRowCloner$cloneContent$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02911 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C02911(ContinuationImpl continuationImpl) {
            super(continuationImpl);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            NotificationRowCloner notificationRowCloner = NotificationRowCloner.this;
            Companion companion = NotificationRowCloner.Companion;
            return notificationRowCloner.cloneContent(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.row.menupanel.NotificationRowCloner$withEffect$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02921 extends ContinuationImpl {
        float F$0;
        float F$1;
        float F$2;
        float F$3;
        float F$4;
        float F$5;
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        int I$4;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        boolean Z$0;
        boolean Z$1;
        int label;
        /* synthetic */ Object result;

        public C02921(ContinuationImpl continuationImpl) {
            super(continuationImpl);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            NotificationRowCloner notificationRowCloner = NotificationRowCloner.this;
            Companion companion = NotificationRowCloner.Companion;
            return notificationRowCloner.withEffect(null, false, false, null, this);
        }
    }

    public NotificationRowCloner(Context context, WindowManager windowManager, ViewBlurManager viewBlurManager) {
        this.context = context;
        this.windowManager = windowManager;
        this.viewBlurManager = viewBlurManager;
        this.menuContentWidth = context.getResources().getDimensionPixelOffset(2131170760);
        this.menuHorizontalMargin = context.getResources().getDimensionPixelOffset(2131167144);
    }

    public static ValueAnimator clipRound(RoundClipView roundClipView, int i, int i2, float f, float f2) {
        ValueAnimator valueAnimatorOfFloat = ObjectAnimator.ofFloat(0.0f, 1.0f);
        int clipTopAmount = roundClipView.getClipTopAmount();
        int clipBottomAmount = roundClipView.getClipBottomAmount();
        float topCornerRadius = roundClipView.getTopCornerRadius();
        float bottomCornerRadius = roundClipView.getBottomCornerRadius();
        clipRound.1.1 r5 = new clipRound.1.1();
        r5.$view = roundClipView;
        r5.$startClipTopAmount = clipTopAmount;
        r5.$clipTopAmount = i;
        r5.$startClipBottomAmount = clipBottomAmount;
        r5.$clipBottomAmount = i2;
        r5.$startTopCornerRadius = topCornerRadius;
        r5.$topCornerRadius = f;
        r5.$startBottomCornerRadius = bottomCornerRadius;
        r5.$bottomCornerRadius = f2;
        VarHandle.storeStoreFence();
        valueAnimatorOfFloat.addUpdateListener(r5);
        return valueAnimatorOfFloat;
    }

    public static Pair getLocation(ExpandableNotificationRow expandableNotificationRow) {
        int[] iArr = new int[2];
        expandableNotificationRow.getLocationOnScreen(iArr);
        return new Pair(Integer.valueOf(iArr[0]), Integer.valueOf(iArr[1]));
    }

    public static int getRealClipTopAmount(ExpandableNotificationRow expandableNotificationRow) {
        ExpandableNotificationRow notificationParent = expandableNotificationRow.getNotificationParent();
        return (!expandableNotificationRow.isChildInGroup() || notificationParent == null || ((float) notificationParent.getClipTopAmount()) - expandableNotificationRow.getTranslationY() <= 0.0f) ? expandableNotificationRow.getClipTopAmount() : notificationParent.getClipTopAmount() - ((int) expandableNotificationRow.getTranslationY());
    }

    public static Pair getTopAndBottomRoundness(ExpandableNotificationRow expandableNotificationRow) {
        ExpandableNotificationRow notificationParent = expandableNotificationRow.getNotificationParent();
        boolean z = false;
        boolean z2 = (!expandableNotificationRow.isChildInGroup() || notificationParent == null || getRealClipTopAmount(expandableNotificationRow) == 0) ? false : true;
        if (expandableNotificationRow.isChildInGroup() && notificationParent != null && expandableNotificationRow.getClipBottomAmount() != 0) {
            z = true;
        }
        return new Pair(Float.valueOf(z2 ? notificationParent.getTopRoundness() : expandableNotificationRow.getTopRoundness()), Float.valueOf(z ? notificationParent.getBottomRoundness() : expandableNotificationRow.getBottomRoundness()));
    }

    public void animateTo(RoundClipView roundClipView, ExpandableNotificationRow expandableNotificationRow) {
        int height;
        Rect bounds;
        float f;
        float measuredWidth;
        WindowMetrics currentWindowMetrics;
        Rect bounds2;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "NotificationRowCloner-->animateTo");
        }
        Pair topAndBottomRoundness = getTopAndBottomRoundness(expandableNotificationRow);
        Pair location = getLocation(expandableNotificationRow);
        int iIntValue = ((Number) location.component1()).intValue();
        int iIntValue2 = ((Number) location.component2()).intValue();
        if (((ActivatableNotificationView) expandableNotificationRow).mDismissed || !expandableNotificationRow.isAttachedToWindow()) {
            WindowMetrics currentWindowMetrics2 = this.windowManager.getCurrentWindowMetrics();
            if (currentWindowMetrics2 == null || (bounds = currentWindowMetrics2.getBounds()) == null) {
                height = roundClipView.getHeight();
                f = -height;
            } else {
                iIntValue2 = bounds.height();
                f = iIntValue2;
            }
        } else {
            if (expandableNotificationRow.mHeadsupDisappearRunning) {
                height = roundClipView.getHeight();
            } else if (!roundClipView.isHeadsUp || expandableNotificationRow.mIsHeadsUp) {
                if (roundClipView.getHeight() != expandableNotificationRow.getActualHeight()) {
                    if (iIntValue2 <= roundClipView.getTranslationY() || (currentWindowMetrics = this.windowManager.getCurrentWindowMetrics()) == null || (bounds2 = currentWindowMetrics.getBounds()) == null) {
                        height = roundClipView.getHeight();
                    } else {
                        iIntValue2 = bounds2.height();
                    }
                }
                f = iIntValue2;
            } else {
                height = roundClipView.getHeight();
            }
            f = -height;
        }
        if (!roundClipView.isHeadsUp || expandableNotificationRow.mIsHeadsUp) {
            measuredWidth = isRtl() ? roundClipView.getMeasuredWidth() + (-windowWidth()) + iIntValue : iIntValue;
        } else {
            measuredWidth = roundClipView.getTranslationX();
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(400L);
        animatorSet.setInterpolator(INTERPOLATOR);
        Property property = View.TRANSLATION_Y;
        float[] fArr = {f};
        Property property2 = View.TRANSLATION_X;
        float[] fArr2 = {measuredWidth};
        Property property3 = View.SCALE_X;
        float[] fArr3 = {expandableNotificationRow.getScaleX()};
        Property property4 = View.SCALE_Y;
        float[] fArr4 = {expandableNotificationRow.getScaleY()};
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(roundClipView, (Property<RoundClipView, Float>) View.ALPHA, 0.0f);
        objectAnimatorOfFloat.setStartDelay(200L);
        animatorSet.playTogether(ObjectAnimator.ofFloat(roundClipView, (Property<RoundClipView, Float>) property, fArr), ObjectAnimator.ofFloat(roundClipView, (Property<RoundClipView, Float>) property2, fArr2), ObjectAnimator.ofFloat(roundClipView, (Property<RoundClipView, Float>) property3, fArr3), ObjectAnimator.ofFloat(roundClipView, (Property<RoundClipView, Float>) property4, fArr4), objectAnimatorOfFloat, clipRound(roundClipView, getRealClipTopAmount(expandableNotificationRow), expandableNotificationRow.getClipBottomAmount(), expandableNotificationRow.getMaxRadius() * ((Number) topAndBottomRoundness.getFirst()).floatValue(), expandableNotificationRow.getMaxRadius() * ((Number) topAndBottomRoundness.getSecond()).floatValue()));
        animatorSet.start();
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0019  */
    @Override // com.oplus.systemui.statusbar.notification.row.menupanel.NotificationViewCloner
    public Object clone(ExpandableNotificationRow expandableNotificationRow, ViewBlurProxy viewBlurProxy, Continuation continuation) {
        AnonymousClass1 anonymousClass1;
        Ref.ObjectRef objectRef;
        RoundClipView roundClipView;
        int i;
        ImageView imageView;
        long j;
        Ref.ObjectRef objectRef2;
        ViewBlurProxy viewBlurProxy2;
        RoundClipView roundClipView2;
        Ref.ObjectRef objectRef3;
        View menuView;
        WindowInsets windowInsets;
        Insets insetsIgnoringVisibility;
        ExpandableNotificationRow expandableNotificationRow2 = expandableNotificationRow;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            int i2 = anonymousClass1.label;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label = i2 - Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = anonymousClass1.label;
        if (i3 == 0) {
            Ref.ObjectRef objectRefM = DefaultDraggableAnchors$.ExternalSyntheticOutline0.m(obj);
            objectRef = new Ref.ObjectRef();
            long jCurrentTimeMillis = System.currentTimeMillis();
            Trace.beginSection("NotificationRowCloner clone");
            ImageView imageView2 = new ImageView(expandableNotificationRow2.getContext());
            imageView2.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView2.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            roundClipView = new RoundClipView(expandableNotificationRow2.getContext());
            roundClipView.addView(imageView2);
            Pair location = getLocation(expandableNotificationRow2);
            int iIntValue = ((Number) location.component1()).intValue();
            int realClipTopAmount = (getRealClipTopAmount(expandableNotificationRow2) + ((Number) location.component2()).intValue()) - expandableNotificationRow2.getClipBottomAmount();
            float scaleY = iIntValue - (((1.0f - expandableNotificationRow2.getScaleY()) * expandableNotificationRow2.getMeasuredWidth()) / 2.0f);
            if (LargeScreenUtils.shouldUseSplitNotificationShade(expandableNotificationRow2.getContext().getResources())) {
                int iWindowWidth = windowWidth();
                WindowMetrics currentWindowMetrics = this.windowManager.getCurrentWindowMetrics();
                Pair pair = (currentWindowMetrics == null || (windowInsets = currentWindowMetrics.getWindowInsets()) == null || (insetsIgnoringVisibility = windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.navigationBars() | WindowInsets.Type.displayCutout())) == null) ? new Pair(0, 0) : new Pair(Integer.valueOf(insetsIgnoringVisibility.left), Integer.valueOf(insetsIgnoringVisibility.right));
                int iIntValue2 = ((Number) pair.component1()).intValue();
                int iIntValue3 = ((Number) pair.component2()).intValue();
                int i4 = (this.menuHorizontalMargin * 2) + this.menuContentWidth;
                if (isRtl()) {
                    float measuredWidth = ((iWindowWidth - iIntValue3) - i4) - expandableNotificationRow2.getMeasuredWidth();
                    if (scaleY > measuredWidth) {
                        scaleY = measuredWidth;
                    }
                } else {
                    float f = iIntValue2 + i4;
                    if (scaleY < f) {
                        scaleY = f;
                    }
                }
            }
            int i5 = (int) scaleY;
            objectRef.element = new Rect(i5, realClipTopAmount, expandableNotificationRow2.getMeasuredWidth() + i5, expandableNotificationRow2.getActualHeight() + realClipTopAmount);
            Pair location2 = getLocation(expandableNotificationRow2);
            int iIntValue4 = ((Number) location2.component1()).intValue();
            int iIntValue5 = ((Number) location2.component2()).intValue();
            roundClipView.setTranslationX(isRtl() ? (expandableNotificationRow2.getWidth() - windowWidth()) + iIntValue4 : iIntValue4);
            roundClipView.setTranslationY(iIntValue5);
            roundClipView.setScaleX(expandableNotificationRow2.getScaleX());
            roundClipView.setScaleY(expandableNotificationRow2.getScaleY());
            roundClipView.setPivotX(0.0f);
            roundClipView.setPivotY(0.0f);
            imageView2.setTranslationX(expandableNotificationRow2.getTranslation());
            NotificationMenuRowPlugin provider = expandableNotificationRow2.getProvider();
            i = (provider == null || !provider.isMenuVisible()) ? 0 : 1;
            if (i != 0) {
                NotificationMenuRowPlugin provider2 = expandableNotificationRow2.getProvider();
                if (provider2 != null && (menuView = provider2.getMenuView()) != null) {
                    menuView.setVisibility(8);
                }
                ((ActivatableNotificationView) expandableNotificationRow2).mBackgroundNormal.setVisibility(8);
            }
            anonymousClass1.L$0 = expandableNotificationRow2;
            anonymousClass1.L$1 = viewBlurProxy;
            anonymousClass1.L$2 = objectRefM;
            anonymousClass1.L$3 = objectRef;
            anonymousClass1.L$4 = roundClipView;
            anonymousClass1.L$5 = roundClipView;
            anonymousClass1.L$6 = imageView2;
            anonymousClass1.L$7 = objectRefM;
            anonymousClass1.I$0 = 0;
            anonymousClass1.J$0 = jCurrentTimeMillis;
            anonymousClass1.I$1 = 0;
            anonymousClass1.I$2 = 0;
            anonymousClass1.I$3 = i;
            anonymousClass1.label = 1;
            if (cloneContent(expandableNotificationRow2, imageView2, anonymousClass1) == coroutine_suspended) {
                return coroutine_suspended;
            }
            imageView = imageView2;
            j = jCurrentTimeMillis;
            objectRef2 = objectRefM;
            viewBlurProxy2 = viewBlurProxy;
            roundClipView2 = roundClipView;
            objectRef3 = objectRef2;
        } else {
            if (i3 != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            int i6 = anonymousClass1.I$3;
            j = anonymousClass1.J$0;
            objectRef3 = (Ref.ObjectRef) anonymousClass1.L$7;
            imageView = (ImageView) anonymousClass1.L$6;
            roundClipView2 = (RoundClipView) anonymousClass1.L$5;
            roundClipView = (RoundClipView) anonymousClass1.L$4;
            objectRef = (Ref.ObjectRef) anonymousClass1.L$3;
            objectRef2 = (Ref.ObjectRef) anonymousClass1.L$2;
            viewBlurProxy2 = (ViewBlurProxy) anonymousClass1.L$1;
            ExpandableNotificationRow expandableNotificationRow3 = (ExpandableNotificationRow) anonymousClass1.L$0;
            ResultKt.throwOnFailure(obj);
            i = i6;
            expandableNotificationRow2 = expandableNotificationRow3;
        }
        if (viewBlurProxy2 != null) {
            NotificationRowCloner$cloneBgIfNeed$.inlined.doOnPreDraw.1 r2 = new NotificationRowCloner$cloneBgIfNeed$.inlined.doOnPreDraw.1();
            r2.this$0 = this;
            r2.$originView$inlined = expandableNotificationRow2;
            r2.$originBlurProxy$inlined = viewBlurProxy2;
            r2.$this_cloneBgIfNeed$inlined = roundClipView2;
            r2.$blurProxy$inlined = viewBlurProxy2;
            VarHandle.storeStoreFence();
            OneShotPreDrawListener.add(roundClipView2, r2);
        }
        if (i != 0) {
            ((ActivatableNotificationView) expandableNotificationRow2).mBackgroundNormal.setVisibility(0);
        }
        Pair topAndBottomRoundness = getTopAndBottomRoundness(expandableNotificationRow2);
        int realClipTopAmount2 = getRealClipTopAmount(expandableNotificationRow2);
        int clipBottomAmount = expandableNotificationRow2.getClipBottomAmount();
        float fFloatValue = ((Number) topAndBottomRoundness.getFirst()).floatValue();
        float fFloatValue2 = ((Number) topAndBottomRoundness.getSecond()).floatValue();
        float maxRadius = expandableNotificationRow2.getMaxRadius();
        Rect rect = (Rect) objectRef.element;
        long j2 = j;
        NotificationRowCloner$animationClip$.inlined.doOnPreDraw.1 r4 = new NotificationRowCloner$animationClip$.inlined.doOnPreDraw.1();
        r4.$this_animationClip$inlined = roundClipView2;
        r4.$originClipTopAmount$inlined = realClipTopAmount2;
        r4.$originClipBottomAmount$inlined = clipBottomAmount;
        r4.this$0 = this;
        r4.$originView$inlined = expandableNotificationRow2;
        r4.$originTopRoundness$inlined = fFloatValue;
        r4.$maxCornerRadius$inlined = maxRadius;
        r4.$originBottomRoundness$inlined = fFloatValue2;
        r4.$endPosition$inlined = rect;
        r4.$contentView$inlined = imageView;
        VarHandle.storeStoreFence();
        OneShotPreDrawListener.add(roundClipView2, r4);
        roundClipView2.setHeadsUp(expandableNotificationRow2.mIsHeadsUp);
        objectRef3.element = roundClipView;
        ((RoundClipView) objectRef2.element).setLayoutParams(new ViewGroup.LayoutParams(expandableNotificationRow2.getWidth(), -2));
        android.os.Trace.endSection();
        long jCurrentTimeMillis2 = System.currentTimeMillis() - j2;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusAODMaskAnimController$.ExternalSyntheticOutline0.m(jCurrentTimeMillis2, "clone, speed time:", "NotificationRowCloner-->", "SystemUi--Notification");
        }
        return new Pair(objectRef2.element, objectRef.element);
    }

    /* JADX WARN: Code duplicated, block: B:8:0x0014  */
    public final Object cloneContent(ExpandableNotificationRow expandableNotificationRow, ImageView imageView, ContinuationImpl continuationImpl) {
        C02911 c02911;
        Object obj;
        if (continuationImpl instanceof C02911) {
            c02911 = (C02911) continuationImpl;
            int i = c02911.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c02911.label = i - Integer.MIN_VALUE;
            } else {
                c02911 = new C02911(continuationImpl);
            }
        } else {
            c02911 = new C02911(continuationImpl);
        }
        C02911 c02912 = c02911;
        Object obj2 = c02912.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c02912.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj2);
                Function2 function2 = new cloneContent.2.1(expandableNotificationRow, imageView, (Continuation) null);
                c02912.L$0 = SpillingKt.nullOutSpilledVariable(expandableNotificationRow);
                c02912.L$1 = SpillingKt.nullOutSpilledVariable(imageView);
                c02912.I$0 = 0;
                c02912.label = 1;
                if (withEffect(expandableNotificationRow, true, true, function2, c02912) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i2 != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj2);
            }
            obj = Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "NotificationRowCloner-->".concat("cloneContent fail! " + th2));
        }
        return Unit.INSTANCE;
    }

    public final int getViewScreenPositionY(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return iArr[1];
    }

    public final boolean isRtl() {
        Configuration configuration;
        Resources resources = this.context.getResources();
        return (resources == null || (configuration = resources.getConfiguration()) == null || configuration.getLayoutDirection() != 1) ? false : true;
    }

    @Override // com.oplus.systemui.statusbar.notification.row.menupanel.NotificationViewCloner
    public void onConfigChanged(Configuration configuration) {
        this.menuContentWidth = this.context.getResources().getDimensionPixelOffset(2131170760);
        this.menuHorizontalMargin = this.context.getResources().getDimensionPixelOffset(2131167144);
    }

    public final int windowWidth() {
        Rect bounds;
        WindowMetrics currentWindowMetrics = this.windowManager.getCurrentWindowMetrics();
        if (currentWindowMetrics == null || (bounds = currentWindowMetrics.getBounds()) == null) {
            return 0;
        }
        return bounds.width();
    }

    /* JADX WARN: Code duplicated, block: B:7:0x001d  */
    public final Object withEffect(ExpandableNotificationRow expandableNotificationRow, boolean z, boolean z2, Function2 function2, ContinuationImpl continuationImpl) {
        C02921 c02921;
        int clipTopAmount;
        Integer numBoxInt;
        int i;
        NotificationBackgroundView notificationBackgroundView;
        View childAt;
        int i2;
        float f;
        float f2;
        boolean z3;
        float f3;
        boolean z4;
        float f4;
        int i3;
        int i4;
        float f5;
        NotificationBackgroundViewExt ext;
        ViewBlurProxy viewBlurProxy;
        NotificationBackgroundViewExt ext2;
        NotificationBackgroundViewExt ext3;
        NotificationBackgroundViewExt ext4;
        NotificationBackgroundView notificationBackgroundView2;
        NotificationBackgroundView notificationBackgroundView3;
        NotificationBackgroundViewExt ext5;
        ExpandableNotificationRow expandableNotificationRow2 = expandableNotificationRow;
        if (continuationImpl instanceof C02921) {
            c02921 = (C02921) continuationImpl;
            int i5 = c02921.label;
            if ((i5 & Integer.MIN_VALUE) != 0) {
                c02921.label = i5 - Integer.MIN_VALUE;
            } else {
                c02921 = new C02921(continuationImpl);
            }
        } else {
            c02921 = new C02921(continuationImpl);
        }
        Object obj = c02921.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i6 = c02921.label;
        if (i6 == 0) {
            ResultKt.throwOnFailure(obj);
            NotificationBackgroundView notificationBackgroundView4 = ((ActivatableNotificationView) expandableNotificationRow2).mBackgroundNormal;
            clipTopAmount = expandableNotificationRow2.getClipTopAmount();
            int clipBottomAmount = expandableNotificationRow2.getClipBottomAmount();
            numBoxInt = (notificationBackgroundView4 == null || (ext4 = notificationBackgroundView4.getExt()) == null) ? null : Boxing.boxInt(ext4.getClickEffectAlpha());
            float translation = expandableNotificationRow2.getTranslation();
            int viewScreenPositionY = getViewScreenPositionY(expandableNotificationRow);
            i = (viewScreenPositionY < 0 || clipBottomAmount > 0) ? 1 : 0;
            NotificationBackgroundView notificationBackgroundView5 = ((ActivatableNotificationView) expandableNotificationRow2).mBackgroundNormal;
            float alpha = notificationBackgroundView5 != null ? notificationBackgroundView5.getAlpha() : 1.0f;
            NotificationBackgroundView notificationBackgroundView6 = ((ActivatableNotificationView) expandableNotificationRow2).mBackgroundNormal;
            int stackedNotificationMaskColor = (notificationBackgroundView6 == null || (ext3 = notificationBackgroundView6.getExt()) == null) ? 0 : ext3.getStackedNotificationMaskColor();
            int childCount = expandableNotificationRow2.getChildCount();
            int i7 = 0;
            while (true) {
                if (i7 >= childCount) {
                    notificationBackgroundView = notificationBackgroundView4;
                    childAt = null;
                    break;
                }
                notificationBackgroundView = notificationBackgroundView4;
                childAt = expandableNotificationRow2.getChildAt(i7);
                int i8 = childCount;
                if (i7 != 0 && (childAt instanceof ViewGroup) && ((ViewGroup) childAt).getVisibility() == 0) {
                    break;
                }
                i7++;
                notificationBackgroundView4 = notificationBackgroundView;
                childCount = i8;
            }
            float scaleX = childAt != null ? childAt.getScaleX() : 1.0f;
            float scaleY = childAt != null ? childAt.getScaleY() : 1.0f;
            float pivotX = childAt != null ? childAt.getPivotX() : 0.0f;
            float pivotY = childAt != null ? childAt.getPivotY() : 0.0f;
            if (z) {
                expandableNotificationRow2.setClipTopAmount(0);
                expandableNotificationRow2.setClipBottomAmount(0);
            }
            if (z2) {
                NotificationBackgroundView notificationBackgroundView7 = ((ActivatableNotificationView) expandableNotificationRow2).mBackgroundNormal;
                if (notificationBackgroundView7 == null || (ext2 = notificationBackgroundView7.getExt()) == null) {
                    i2 = 0;
                } else {
                    i2 = 0;
                    ext2.setClickEffectAlpha(0);
                }
            } else {
                i2 = 0;
            }
            float f6 = scaleX;
            if (stackedNotificationMaskColor != 0) {
                ((ActivatableNotificationView) expandableNotificationRow2).mBackgroundNormal.getExt().setStackedNotificationMaskColor(i2);
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "NotificationRowCloner-->stack mask color reset to transparent before clone content");
                }
            }
            if (i != 0) {
                NotificationBackgroundView notificationBackgroundView8 = ((ActivatableNotificationView) expandableNotificationRow2).mBackgroundNormal;
                if (!Intrinsics.areEqual((notificationBackgroundView8 == null || (ext = notificationBackgroundView8.getExt()) == null || (viewBlurProxy = ext.getViewBlurProxy()) == null) ? null : viewBlurProxy.getBlurType(), ViewBlurProxy.BlurType.BlurTypeNon.INSTANCE)) {
                    NotificationBackgroundView notificationBackgroundView9 = ((ActivatableNotificationView) expandableNotificationRow2).mBackgroundNormal;
                    if (notificationBackgroundView9 != null) {
                        notificationBackgroundView9.setAlpha(0.0f);
                    }
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", "NotificationRowCloner-->if blurType is not BlurTypeNon, set alpha 0 before clone content");
                    }
                }
            }
            if (childAt != null) {
                childAt.setPivotX(expandableNotificationRow2.getWidth() / 2.0f);
                childAt.setPivotY(0.0f);
                childAt.setScaleX(1.0f);
                childAt.setScaleY(1.0f);
            }
            c02921.L$0 = expandableNotificationRow2;
            c02921.L$1 = SpillingKt.nullOutSpilledVariable(function2);
            c02921.L$2 = SpillingKt.nullOutSpilledVariable(notificationBackgroundView);
            c02921.L$3 = numBoxInt;
            c02921.L$4 = childAt;
            c02921.Z$0 = z;
            c02921.Z$1 = z2;
            c02921.I$0 = clipTopAmount;
            c02921.I$1 = clipBottomAmount;
            c02921.F$0 = translation;
            c02921.I$2 = viewScreenPositionY;
            c02921.I$3 = i;
            c02921.F$1 = alpha;
            c02921.I$4 = stackedNotificationMaskColor;
            f = f6;
            c02921.F$2 = f;
            float f7 = scaleY;
            c02921.F$3 = f7;
            float f8 = pivotX;
            c02921.F$4 = f8;
            f2 = pivotY;
            c02921.F$5 = f2;
            c02921.label = 1;
            if (((cloneContent.2.1) function2).invoke(expandableNotificationRow2, c02921) == coroutine_suspended) {
                return coroutine_suspended;
            }
            z3 = z2;
            f3 = f7;
            z4 = z;
            f4 = f8;
            i3 = stackedNotificationMaskColor;
            i4 = clipBottomAmount;
            f5 = alpha;
        } else {
            if (i6 != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            float f9 = c02921.F$5;
            f4 = c02921.F$4;
            f3 = c02921.F$3;
            float f10 = c02921.F$2;
            i3 = c02921.I$4;
            f5 = c02921.F$1;
            int i9 = c02921.I$3;
            i4 = c02921.I$1;
            clipTopAmount = c02921.I$0;
            z3 = c02921.Z$1;
            z4 = c02921.Z$0;
            View view = (View) c02921.L$4;
            numBoxInt = (Integer) c02921.L$3;
            ExpandableNotificationRow expandableNotificationRow3 = (ExpandableNotificationRow) c02921.L$0;
            ResultKt.throwOnFailure(obj);
            childAt = view;
            i = i9;
            f = f10;
            f2 = f9;
            expandableNotificationRow2 = expandableNotificationRow3;
        }
        if (childAt != null) {
            childAt.setScaleX(f);
            childAt.setScaleY(f3);
            childAt.setPivotX(f4);
            childAt.setPivotY(f2);
        }
        if (z4) {
            expandableNotificationRow2.setClipTopAmount(clipTopAmount);
            expandableNotificationRow2.setClipBottomAmount(i4);
        }
        if (z3 && numBoxInt != null && (notificationBackgroundView3 = ((ActivatableNotificationView) expandableNotificationRow2).mBackgroundNormal) != null && (ext5 = notificationBackgroundView3.getExt()) != null) {
            ext5.setClickEffectAlpha(numBoxInt.intValue());
        }
        if (i3 != 0) {
            ((ActivatableNotificationView) expandableNotificationRow2).mBackgroundNormal.getExt().setStackedNotificationMaskColor(i3);
        }
        if (i != 0 && (notificationBackgroundView2 = ((ActivatableNotificationView) expandableNotificationRow2).mBackgroundNormal) != null) {
            notificationBackgroundView2.setAlpha(f5);
        }
        return Unit.INSTANCE;
    }
}
