package com.oplus.systemui.notification.lockscreen.stack.algorithm;

import android.content.Context;
import android.util.MathUtils;
import android.view.View;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.foundation.shape.DpCornerSize$;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.OplusLockscreenShadeTransitionControllerEx;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer;
import com.android.systemui.statusbar.notification.stack.OplusExpandableViewStateEx;
import com.android.systemui.statusbar.notification.stack.SectionHeaderView;
import com.android.systemui.statusbar.notification.stack.ViewState;
import com.oplus.systemui.notification.stackednotification.NotificationStackedRuler;
import com.oplus.systemui.statusbar.notification.row.OplusExpandableNotificationRowExImpl;
import com.oplusos.systemui.common.feature.NotificationFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.functions.Function0;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.sequences.SequencesKt__SequencesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DynamicStackAlgorithm {
    public static final Companion Companion = new Companion();
    private static final String TAG = "DynamicStackAlgorithm";
    private final Context context;
    public DynamicStackDimensCache dimensCache;
    private final Lazy lockscreenShadeTransitionController$delegate;
    public float smallScale = 1.0f;
    private final Lazy statusBarStateController$delegate;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class DampedStackLayout {
        public final float scale;
        public final float scaleFraction;
        public final float translationY;

        public DampedStackLayout(float f, float f2, float f3) {
            this.translationY = f;
            this.scale = f2;
            this.scaleFraction = f3;
        }

        public final DampedStackLayout copy(float f, float f2, float f3) {
            return new DampedStackLayout(f, f2, f3);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DampedStackLayout)) {
                return false;
            }
            DampedStackLayout dampedStackLayout = (DampedStackLayout) obj;
            return Float.compare(this.translationY, dampedStackLayout.translationY) == 0 && Float.compare(this.scale, dampedStackLayout.scale) == 0 && Float.compare(this.scaleFraction, dampedStackLayout.scaleFraction) == 0;
        }

        public final float getScale() {
            return this.scale;
        }

        public final float getScaleFraction() {
            return this.scaleFraction;
        }

        public final float getTranslationY() {
            return this.translationY;
        }

        public final int hashCode() {
            return Float.hashCode(this.scaleFraction) + ChangeSize$.ExternalSyntheticOutline0.m(this.scale, Float.hashCode(this.translationY) * 31, 31);
        }

        public String toString() {
            return DpCornerSize$.ExternalSyntheticOutline0.m(this.scaleFraction, ")", MonoSpline$.ExternalSyntheticOutline0.m("DampedStackLayout(translationY=", this.translationY, ", scale=", this.scale, ", scaleFraction="));
        }
    }

    public DynamicStackAlgorithm(Context context) {
        this.context = context;
        final int i = 0;
        this.lockscreenShadeTransitionController$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.notification.lockscreen.stack.algorithm.DynamicStackAlgorithm$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i) {
                    case 0:
                        return (OplusLockscreenShadeTransitionControllerEx) DependencyEx.sDependency.getDependency(OplusLockscreenShadeTransitionControllerEx.class);
                    default:
                        return (StatusBarStateController) Dependency.sDependency.getDependencyInner(StatusBarStateController.class);
                }
            }
        });
        final int i2 = 1;
        this.statusBarStateController$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.notification.lockscreen.stack.algorithm.DynamicStackAlgorithm$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i2) {
                    case 0:
                        return (OplusLockscreenShadeTransitionControllerEx) DependencyEx.sDependency.getDependency(OplusLockscreenShadeTransitionControllerEx.class);
                    default:
                        return (StatusBarStateController) Dependency.sDependency.getDependencyInner(StatusBarStateController.class);
                }
            }
        });
    }

    public static float computeDampedTranslationY(float f, float f2, float f3, DynamicStackDimens dynamicStackDimens, boolean z) {
        float translationDampingBaseHeight = z ? f3 - f2 : f3 - (dynamicStackDimens.getTranslationDampingBaseHeight() + f2);
        return f <= translationDampingBaseHeight ? f : MathUtils.lerp(translationDampingBaseHeight, f3 - f2, RangesKt___RangesKt.coerceIn((f - translationDampingBaseHeight) / (dynamicStackDimens.getTranslationDampingBaseHeight() + f2), 0.0f, 1.0f));
    }

    private final OplusLockscreenShadeTransitionControllerEx getLockscreenShadeTransitionController() {
        return (OplusLockscreenShadeTransitionControllerEx) this.lockscreenShadeTransitionController$delegate.getValue();
    }

    private final StatusBarStateController getStatusBarStateController() {
        return (StatusBarStateController) this.statusBarStateController$delegate.getValue();
    }

    /* JADX WARN: Code duplicated, block: B:23:0x003c  */
    public static boolean shouldRestartStack(int i, List list, ExpandableViewState expandableViewState, ExpandableView expandableView, boolean z, DynamicStackDimens dynamicStackDimens) {
        boolean z2;
        boolean z3 = !expandableView.isChildInGroup() ? i != list.size() - 1 : !(i == list.size() - 1 && z);
        boolean z4 = expandableViewState.height > dynamicStackDimens.getLargeNotificationHeight();
        boolean z5 = expandableView instanceof SectionHeaderView;
        if (expandableView instanceof ExpandableNotificationRow) {
            ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) expandableView;
            if (expandableNotificationRow.mIsSummaryWithChildren && expandableNotificationRow.isGroupExpanded()) {
                z2 = true;
            } else {
                z2 = false;
            }
        } else {
            z2 = false;
        }
        return z3 || z4 || z5 || z2;
    }

    public static float stableHeightOf(ExpandableView expandableView) {
        Object next;
        int i = expandableView.getViewState().height;
        if (i > 0) {
            return i;
        }
        Iterator it = SequencesKt__SequencesKt.sequenceOf(new Integer[]{Integer.valueOf(expandableView.getIntrinsicHeight()), Integer.valueOf(expandableView.getActualHeight()), Integer.valueOf(expandableView.getMeasuredHeight()), Integer.valueOf(expandableView.getMinHeight())}).iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (((Number) next).intValue() <= 0);
        Integer num = (Integer) next;
        float fIntValue = num != null ? num.intValue() : 0.0f;
        if (i <= 0 && fIntValue > 0.0f) {
            LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "[StaticStack] stableHeightOf: viewStateHeight <= 0, fallback=" + fIntValue + ", view=" + expandableView);
        }
        return fIntValue;
    }

    /* JADX WARN: Code duplicated, block: B:13:0x002b  */
    public static void updateStackedProgress(ExpandableViewState expandableViewState, float f, float f2, ExpandableView expandableView, DynamicStackDimens dynamicStackDimens) {
        float yTranslation;
        float yTranslation2;
        float stackedLargeNotificationLimitedHeight;
        OplusExpandableViewStateEx oplusExpandableViewStateEx = expandableViewState.mExpandableViewStateEx;
        if (oplusExpandableViewStateEx != null) {
            float fCoerceIn = 0.0f;
            if (f != 0.0f) {
                boolean z = expandableView instanceof ExpandableNotificationRow;
                if (z) {
                    ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) expandableView;
                    if (expandableNotificationRow.isChildInGroup()) {
                        ExpandableViewState viewState = expandableNotificationRow.getNotificationParent().getViewState();
                        yTranslation = (f + f2) - expandableViewState.getYTranslation();
                        yTranslation2 = viewState.getYTranslation();
                    } else {
                        yTranslation = f + f2;
                        yTranslation2 = expandableViewState.getYTranslation();
                    }
                } else {
                    yTranslation = f + f2;
                    yTranslation2 = expandableViewState.getYTranslation();
                }
                float f3 = yTranslation - yTranslation2;
                if (z && ((ExpandableNotificationRow) expandableView).mIsSummaryWithChildren) {
                    stackedLargeNotificationLimitedHeight = dynamicStackDimens.getStackedLargeNotificationLimitedHeight();
                } else {
                    int i = expandableViewState.height - expandableViewState.clipBottomAmount;
                    if (i < 1) {
                        i = 1;
                    }
                    stackedLargeNotificationLimitedHeight = i;
                }
                fCoerceIn = RangesKt___RangesKt.coerceIn(f3 / stackedLargeNotificationLimitedHeight, 0.0f, 1.0f);
            }
            oplusExpandableViewStateEx.setNotificationStackedProgress(fCoerceIn);
        }
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0298  */
    /* JADX WARN: Code duplicated, block: B:105:0x02a4  */
    /* JADX WARN: Code duplicated, block: B:108:0x02ba  */
    /* JADX WARN: Code duplicated, block: B:110:0x02c2  */
    /* JADX WARN: Code duplicated, block: B:111:0x02c5  */
    /* JADX WARN: Code duplicated, block: B:114:0x02d7  */
    /* JADX WARN: Code duplicated, block: B:115:0x02ec  */
    /* JADX WARN: Code duplicated, block: B:118:0x02f6  */
    /* JADX WARN: Code duplicated, block: B:119:0x02ff  */
    /* JADX WARN: Code duplicated, block: B:121:0x0317  */
    /* JADX WARN: Code duplicated, block: B:123:0x0333  */
    /* JADX WARN: Code duplicated, block: B:124:0x0335  */
    /* JADX WARN: Code duplicated, block: B:127:0x033f  */
    /* JADX WARN: Code duplicated, block: B:128:0x0346  */
    /* JADX WARN: Code duplicated, block: B:131:0x0358  */
    /* JADX WARN: Code duplicated, block: B:132:0x0369  */
    /* JADX WARN: Code duplicated, block: B:135:0x0383  */
    /* JADX WARN: Code duplicated, block: B:136:0x038a  */
    /* JADX WARN: Code duplicated, block: B:138:0x0390  */
    /* JADX WARN: Code duplicated, block: B:140:0x039b  */
    /* JADX WARN: Code duplicated, block: B:143:0x03b2  */
    /* JADX WARN: Code duplicated, block: B:146:0x03cb  */
    /* JADX WARN: Code duplicated, block: B:148:0x03cf  */
    /* JADX WARN: Code duplicated, block: B:149:0x03da  */
    /* JADX WARN: Code duplicated, block: B:152:0x03f5  */
    /* JADX WARN: Code duplicated, block: B:186:0x04f8  */
    /* JADX WARN: Code duplicated, block: B:188:0x0502  */
    /* JADX WARN: Code duplicated, block: B:190:0x0513  */
    /* JADX WARN: Code duplicated, block: B:192:0x0518  */
    /* JADX WARN: Code duplicated, block: B:195:0x0548  */
    /* JADX WARN: Code duplicated, block: B:198:0x0568  */
    /* JADX WARN: Code duplicated, block: B:199:0x0580  */
    /* JADX WARN: Code duplicated, block: B:201:0x058a  */
    /* JADX WARN: Code duplicated, block: B:202:0x0590  */
    /* JADX WARN: Code duplicated, block: B:205:0x0596  */
    /* JADX WARN: Code duplicated, block: B:209:0x059e  */
    /* JADX WARN: Code duplicated, block: B:56:0x0162 A[PHI: r24
      0x0162: PHI (r24v3 float) = (r24v1 float), (r24v4 float) binds: [B:55:0x0160, B:52:0x015b] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:60:0x016d  */
    /* JADX WARN: Code duplicated, block: B:65:0x017c  */
    /* JADX WARN: Code duplicated, block: B:69:0x0183  */
    /* JADX WARN: Code duplicated, block: B:72:0x0188  */
    /* JADX WARN: Code duplicated, block: B:74:0x01a0  */
    /* JADX WARN: Code duplicated, block: B:77:0x01cb  */
    /* JADX WARN: Code duplicated, block: B:83:0x01dd  */
    /* JADX WARN: Code duplicated, block: B:85:0x01ed  */
    /* JADX WARN: Code duplicated, block: B:86:0x0203  */
    /* JADX WARN: Code duplicated, block: B:88:0x0215  */
    /* JADX WARN: Code duplicated, block: B:89:0x0218  */
    /* JADX WARN: Code duplicated, block: B:92:0x026b  */
    /* JADX WARN: Code duplicated, block: B:93:0x026e  */
    /* JADX WARN: Code duplicated, block: B:97:0x0285  */
    /* JADX WARN: Code duplicated, block: B:99:0x0295  */
    /* JADX WARN: Multi-variable type inference failed */
    public final boolean apply(DynamicStackRequest dynamicStackRequest) {
        float f;
        int i;
        ExpandableViewState viewState;
        int i2;
        boolean z;
        List<ExpandableView> list;
        float f2;
        int i3;
        DynamicStackDimens dynamicStackDimens;
        boolean z2;
        List mutableList;
        Pair pair;
        Object first;
        SectionHeaderView sectionHeaderView;
        float yTranslation;
        int i4;
        Object obj;
        DynamicStackAlgorithm dynamicStackAlgorithm;
        float f3;
        float f4;
        int i5;
        DynamicStackDimens dynamicStackDimens2;
        int i6;
        ExpandableView expandableView;
        int i7;
        ExpandableView expandableView2;
        int i8;
        ExpandableViewState viewState2;
        boolean z3;
        DynamicStackDimens dynamicStackDimens3;
        boolean zShouldRestartStack;
        int i9;
        float f5;
        float fUpdateScale;
        float f6;
        boolean z4;
        ExpandableViewState expandableViewState;
        float f7;
        float f8;
        float fFloatValue;
        ExpandableView expandableView3;
        float f9;
        float f10;
        ExpandableView expandableView4;
        ExpandableViewState expandableViewState2;
        ExpandableView expandableView5;
        DynamicStackAlgorithm dynamicStackAlgorithm2;
        ExpandableView expandableView6;
        NotificationChildrenContainer childrenContainer;
        float headerHeight;
        ExpandableNotificationRow expandableNotificationRow;
        View viewFindViewById;
        float fStackViewHeight;
        float fStackViewHeight2;
        float yTranslation2;
        float fCoerceIn;
        float yTranslation3;
        ExpandableViewState viewState3;
        float fStackViewHeight3;
        float yTranslation4;
        float fCoerceIn2;
        float f11;
        float fLerp;
        DynamicStackDimens dynamicStackDimens4;
        float fUpdateScale2;
        float f12;
        boolean z5;
        float f13;
        float f14;
        ExpandableNotificationRow expandableNotificationRow2;
        float f15;
        DynamicStackAlgorithm dynamicStackAlgorithm3 = this;
        int iOrdinal = dynamicStackRequest.getPhase().ordinal();
        char c = 52429;
        float f16 = 0.5f;
        int i10 = 0;
        float f17 = 1.0f;
        int i11 = 1;
        if (iOrdinal != 0) {
            if (iOrdinal != 1) {
                LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
                return false;
            }
            List<ExpandableView> expandableViews = dynamicStackRequest.getExpandableViews();
            float topBorder = dynamicStackRequest.getTopBorder();
            float bottomBorder = dynamicStackRequest.getBottomBorder();
            if (!expandableViews.isEmpty()) {
                DynamicStackDimens dynamicStackDimensCurrentDynamicStackDimens = dynamicStackAlgorithm3.currentDynamicStackDimens();
                float paddingBetweenElements = dynamicStackDimensCurrentDynamicStackDimens.getPaddingBetweenElements();
                int i12 = 0;
                boolean z6 = false;
                float scale = 1.0f;
                float f18 = bottomBorder;
                for (Object obj2 : expandableViews) {
                    int i13 = i12 + 1;
                    if (i12 < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                        throw null;
                    }
                    ExpandableView expandableView7 = (ExpandableView) obj2;
                    ExpandableViewState viewState4 = expandableView7.getViewState();
                    float fStableHeightOf = stableHeightOf(expandableView7);
                    if (z6) {
                        ((ViewState) viewState4).hidden = true;
                        viewState4.setAlpha(1.0f, "DynamicStackAlgorithm.applySinkingDynamicStack.tail");
                        float fStackViewHeight4 = viewState4.height > 0 ? dynamicStackAlgorithm3.stackViewHeight(viewState4, dynamicStackDimensCurrentDynamicStackDimens) : stableHeightOf(expandableView7);
                        float f19 = fStackViewHeight4 * 0.8f;
                        viewState4.setYTranslation((f18 - f19) - ((fStackViewHeight4 - f19) * 0.5f), "DynamicStackAlgorithm.applySinkingDynamicStack.overflow");
                        NotificationStackedRuler.Companion.requestStackedScale(viewState4, 0.8f);
                        OplusExpandableViewStateEx oplusExpandableViewStateEx = viewState4.mExpandableViewStateEx;
                        if (oplusExpandableViewStateEx != null) {
                            oplusExpandableViewStateEx.setNotificationStackedProgress(1.0f);
                        }
                        f15 = bottomBorder;
                    } else {
                        DynamicStackDimens dynamicStackDimens5 = dynamicStackDimensCurrentDynamicStackDimens;
                        boolean zShouldRestartStack2 = shouldRestartStack(i12, expandableViews, viewState4, expandableView7, false, dynamicStackDimens5);
                        ((ViewState) viewState4).hidden = false;
                        viewState4.setAlpha(1.0f, "DynamicStackAlgorithm.applySinkingDynamicStack");
                        f15 = bottomBorder;
                        dynamicStackDimensCurrentDynamicStackDimens = dynamicStackDimens5;
                        DampedStackLayout dampedStackLayoutComputeDampedStackLayout = dynamicStackAlgorithm3.computeDampedStackLayout(topBorder, fStableHeightOf, f15, dynamicStackDimensCurrentDynamicStackDimens, zShouldRestartStack2);
                        viewState4.setYTranslation(dampedStackLayoutComputeDampedStackLayout.getTranslationY(), "DynamicStackAlgorithm.applySinkingDynamicStack");
                        if (dampedStackLayoutComputeDampedStackLayout.getScale() < scale) {
                            scale = dampedStackLayoutComputeDampedStackLayout.getScale();
                        }
                        NotificationStackedRuler.Companion.requestStackedScale(viewState4, dampedStackLayoutComputeDampedStackLayout.getScale());
                        OplusExpandableViewStateEx oplusExpandableViewStateEx2 = viewState4.mExpandableViewStateEx;
                        if (oplusExpandableViewStateEx2 != null) {
                            oplusExpandableViewStateEx2.setNotificationStackedProgress(dampedStackLayoutComputeDampedStackLayout.getScaleFraction());
                        }
                        z6 = z6;
                        if (dampedStackLayoutComputeDampedStackLayout.getTranslationY() + fStableHeightOf >= f15) {
                            float scale2 = (dampedStackLayoutComputeDampedStackLayout.getScale() * fStableHeightOf) + dampedStackLayoutComputeDampedStackLayout.getTranslationY();
                            if (scale2 > f15) {
                                scale2 = f15;
                            }
                            z6 = true;
                            f18 = scale2;
                        }
                        topBorder = fStableHeightOf + paddingBetweenElements + topBorder;
                    }
                    bottomBorder = f15;
                    expandableViews = expandableViews;
                    i12 = i13;
                    z6 = z6;
                }
                if (scale < 1.0f) {
                    return true;
                }
            }
            return false;
        }
        List<ExpandableView> expandableViews2 = dynamicStackRequest.getExpandableViews();
        float bottomBorder2 = dynamicStackRequest.getBottomBorder();
        dynamicStackAlgorithm3.smallScale = 1.0f;
        DynamicStackDimens dynamicStackDimensCurrentDynamicStackDimens2 = dynamicStackAlgorithm3.currentDynamicStackDimens();
        float paddingBetweenElements2 = dynamicStackDimensCurrentDynamicStackDimens2.getPaddingBetweenElements();
        int i14 = 0;
        int i15 = 0;
        ExpandableView expandableView8 = null;
        float fFloatValue2 = 0.0f;
        float yTranslation5 = 0.0f;
        DynamicStackAlgorithm dynamicStackAlgorithm4 = dynamicStackAlgorithm3;
        for (IndexedValue indexedValue : CollectionsKt___CollectionsKt.withIndex(expandableViews2)) {
            int i16 = i14;
            int i17 = indexedValue.index;
            ExpandableView expandableView9 = (ExpandableView) indexedValue.value;
            if (expandableView9 instanceof ExpandableNotificationRow) {
                ExpandableNotificationRow expandableNotificationRow3 = (ExpandableNotificationRow) expandableView9;
                f = f16;
                if (expandableNotificationRow3.mIsSummaryWithChildren && ((OplusExpandableNotificationRowExImpl) expandableNotificationRow3.mRowEx).isChildrenExpandedAnimating()) {
                    i = i11;
                }
                viewState = expandableView9.getViewState();
                if (expandableView9 instanceof ExpandableNotificationRow) {
                    expandableNotificationRow2 = (ExpandableNotificationRow) expandableView9;
                    if (expandableNotificationRow2.mIsSummaryWithChildren || !expandableNotificationRow2.isGroupExpanded()) {
                        i2 = i10;
                    } else {
                        i2 = i11;
                    }
                } else {
                    i2 = i10;
                }
                if (i15 == 0 && i == 0) {
                    z = i11;
                } else {
                    z = i10;
                }
                ((ViewState) viewState).hidden = z;
                if (i2 == 0) {
                    dynamicStackDimens4 = dynamicStackDimensCurrentDynamicStackDimens2;
                    boolean zShouldRestartStack3 = shouldRestartStack(i17, expandableViews2, viewState, expandableView9, false, dynamicStackDimens4);
                    list = expandableViews2;
                    fUpdateScale2 = dynamicStackAlgorithm4.updateScale(zShouldRestartStack3, expandableView9, bottomBorder2, dynamicStackDimens4);
                    if (fUpdateScale2 < dynamicStackAlgorithm4.smallScale) {
                        dynamicStackAlgorithm4.smallScale = fUpdateScale2;
                    }
                    f12 = bottomBorder2;
                    dynamicStackAlgorithm4.updateTranslationY(zShouldRestartStack3, viewState, f12, expandableView9, dynamicStackDimens4);
                    z5 = i;
                    ExpandableView expandableView10 = expandableView8;
                    f13 = fFloatValue2;
                    dynamicStackAlgorithm4.updateStackedViewAlpha(zShouldRestartStack3, expandableView9, f13, f12, z5, dynamicStackDimens4, expandableView10);
                    updateStackedProgress(viewState, f13, paddingBetweenElements2, expandableView9, dynamicStackDimens4);
                    dynamicStackAlgorithm = this;
                    f14 = paddingBetweenElements2;
                    dynamicStackAlgorithm.stackedForSectionHeader(expandableView10, viewState, fUpdateScale2, f13, f14, expandableView9);
                    expandableView = expandableView9;
                    if (expandableView instanceof SectionHeaderView) {
                        yTranslation5 = viewState.getYTranslation() + viewState.height;
                        f3 = f14;
                        i5 = i10;
                        dynamicStackDimens2 = dynamicStackDimens4;
                        i6 = z5 ? 1 : 0;
                        fFloatValue2 = f13;
                        f4 = f12;
                    } else {
                        float yTranslation6 = viewState.getYTranslation() + viewState.height;
                        if (i15 == 0 || yTranslation6 >= f12) {
                            i15 = i11;
                        } else {
                            i15 = i10;
                        }
                        f3 = f14;
                        f4 = f12;
                        i5 = i10;
                        dynamicStackDimens2 = dynamicStackDimens4;
                        i6 = z5 ? 1 : 0;
                        yTranslation5 = 0.0f;
                        fFloatValue2 = yTranslation6;
                    }
                } else {
                    list = expandableViews2;
                    f2 = paddingBetweenElements2;
                    i3 = i;
                    float f20 = fFloatValue2;
                    dynamicStackDimens = dynamicStackDimensCurrentDynamicStackDimens2;
                    ExpandableView expandableView11 = expandableView8;
                    if (i17 == list.size() - i11) {
                        z2 = i11;
                    } else {
                        z2 = i10;
                    }
                    mutableList = CollectionsKt___CollectionsKt.toMutableList(((ExpandableNotificationRow) expandableView9).getChildrenContainer().getAttachedChildren());
                    mutableList.subList(Math.min(mutableList.size(), 8), mutableList.size()).clear();
                    mutableList.add(i10, expandableView9);
                    pair = new Pair(expandableView11, Float.valueOf(f20));
                    Iterable<IndexedValue> iterableWithIndex = CollectionsKt___CollectionsKt.withIndex(mutableList);
                    float fFloatValue3 = ((Number) pair.getSecond()).floatValue();
                    Object first2 = pair.getFirst();
                    first = pair.getFirst();
                    if (first instanceof SectionHeaderView) {
                        sectionHeaderView = (SectionHeaderView) first;
                    } else {
                        sectionHeaderView = null;
                    }
                    Pair pair2 = new Pair(sectionHeaderView, pair.getSecond());
                    yTranslation = fFloatValue3;
                    i4 = i10;
                    dynamicStackAlgorithm = dynamicStackAlgorithm4;
                    obj = first2;
                    for (IndexedValue indexedValue2 : iterableWithIndex) {
                        i7 = indexedValue2.index;
                        expandableView2 = (ExpandableNotificationRow) indexedValue2.value;
                        if (i7 == i11) {
                            i8 = i11;
                        } else {
                            i8 = i10;
                        }
                        viewState2 = expandableView2.getViewState();
                        if (i4 == 0 && i3 == 0) {
                            z3 = i11;
                        } else {
                            z3 = i10;
                        }
                        ((ViewState) viewState2).hidden = z3;
                        dynamicStackDimens3 = dynamicStackDimens;
                        List list2 = mutableList;
                        zShouldRestartStack = shouldRestartStack(i7, list2, viewState2, expandableView2, z2, dynamicStackDimens3);
                        Object obj3 = obj;
                        if (i8 != 0) {
                            fStackViewHeight = bottomBorder2 - dynamicStackAlgorithm.stackViewHeight(viewState2, dynamicStackDimens3);
                            if (zShouldRestartStack) {
                                fStackViewHeight2 = fStackViewHeight;
                            } else {
                                fStackViewHeight2 = bottomBorder2 - (dynamicStackAlgorithm.stackViewHeight(viewState2, dynamicStackDimens3) + dynamicStackDimens3.getTranslationDampingBaseHeight());
                            }
                            if (expandableView2.isChildInGroup()) {
                                yTranslation2 = (viewState2.getYTranslation() + expandableView2.getNotificationParent().getViewState().getYTranslation()) - fStackViewHeight2;
                            } else {
                                yTranslation2 = viewState2.getYTranslation() - fStackViewHeight2;
                            }
                            if (yTranslation2 <= 0.0f) {
                                i9 = i10;
                                yTranslation3 = viewState2.getYTranslation();
                            } else {
                                float fStackViewHeight5 = dynamicStackAlgorithm.stackViewHeight(viewState2, dynamicStackDimens3) + dynamicStackDimens3.getTranslationDampingBaseHeight();
                                i9 = i10;
                                fCoerceIn = RangesKt___RangesKt.coerceIn(yTranslation2 / fStackViewHeight5, 0.0f, f17);
                                if (expandableView2.isChildInGroup()) {
                                    ExpandableViewState viewState5 = expandableView2.getNotificationParent().getViewState();
                                    yTranslation3 = MathUtils.lerp(fStackViewHeight2 - viewState5.getYTranslation(), fStackViewHeight - viewState5.getYTranslation(), fCoerceIn);
                                    if (yTranslation3 < 0.0f) {
                                        yTranslation3 = 0.0f;
                                    }
                                } else {
                                    yTranslation3 = viewState2.getYTranslation();
                                }
                            }
                            viewState3 = expandableView2.getViewState();
                            if (zShouldRestartStack) {
                                fStackViewHeight3 = bottomBorder2 - dynamicStackAlgorithm.stackViewHeight(viewState3, dynamicStackDimens3);
                            } else {
                                fStackViewHeight3 = bottomBorder2 - (dynamicStackAlgorithm.stackViewHeight(viewState3, dynamicStackDimens3) + dynamicStackDimens3.getScaleDampingBaseHeight());
                            }
                            if (expandableView2.isChildInGroup()) {
                                yTranslation4 = (expandableView2.getNotificationParent().getViewState().getYTranslation() + yTranslation3) - fStackViewHeight3;
                            } else {
                                yTranslation4 = yTranslation3 - fStackViewHeight3;
                            }
                            fCoerceIn2 = RangesKt___RangesKt.coerceIn(yTranslation4 / ((dynamicStackAlgorithm.stackViewHeight(viewState3, dynamicStackDimens3) + dynamicStackDimens3.getScaleDampingBaseHeight()) * 1.5f), 0.0f, f17);
                            if (fCoerceIn2 <= 0.0f) {
                                fLerp = f17;
                                f5 = fLerp;
                            } else {
                                if (dynamicStackAlgorithm.isOrGoingToKeyguard()) {
                                    f11 = 0.95f;
                                    f5 = f17;
                                } else {
                                    f11 = f17;
                                    f5 = f11;
                                }
                                fLerp = MathUtils.lerp(f11, 0.8f, fCoerceIn2);
                            }
                            NotificationStackedRuler.Companion.requestStackedScale(viewState3, fLerp);
                            float f21 = yTranslation3;
                            fUpdateScale = fLerp;
                            z4 = zShouldRestartStack;
                            expandableViewState = viewState2;
                            f8 = f21;
                            f6 = bottomBorder2;
                            f7 = 0.0f;
                        } else {
                            i9 = i10;
                            f5 = f17;
                            fUpdateScale = dynamicStackAlgorithm.updateScale(zShouldRestartStack, expandableView2, bottomBorder2, dynamicStackDimens3);
                            f6 = bottomBorder2;
                            z4 = zShouldRestartStack;
                            expandableViewState = viewState2;
                            f7 = 0.0f;
                            dynamicStackAlgorithm.updateTranslationY(z4, expandableViewState, f6, expandableView2, dynamicStackDimens3);
                            f8 = 0.0f;
                        }
                        if (fUpdateScale < dynamicStackAlgorithm.smallScale) {
                            dynamicStackAlgorithm.smallScale = fUpdateScale;
                        }
                        if (i8 != 0) {
                            fFloatValue = ((Number) pair.getSecond()).floatValue();
                        } else {
                            fFloatValue = yTranslation;
                        }
                        expandableView3 = (ExpandableView) obj3;
                        f9 = f7;
                        Pair pair3 = new Pair(Float.valueOf(fFloatValue), Float.valueOf(yTranslation5));
                        float notificationChildrenContainerDivider = dynamicStackDimens3.getNotificationChildrenContainerDivider();
                        if (expandableView3 instanceof ExpandableNotificationRow) {
                            expandableNotificationRow = (ExpandableNotificationRow) expandableView3;
                            f10 = f2;
                            if (!expandableNotificationRow.mIsSummaryWithChildren && expandableNotificationRow.isGroupExpanded() && (viewFindViewById = expandableNotificationRow.findViewById(2131364384)) != null) {
                                ExpandableViewState viewState6 = expandableNotificationRow.getViewState();
                                expandableView4 = expandableView3;
                                if (viewState6.getYTranslation() <= viewState6.clipTopAmount) {
                                    f9 = f9;
                                    expandableViewState = expandableViewState;
                                } else {
                                    float yTranslation7 = ((f5 - fUpdateScale) * expandableViewState.height * f) + viewState6.getYTranslation() + f8;
                                    float height = viewFindViewById.getHeight() + notificationChildrenContainerDivider;
                                    float yTranslation8 = viewState6.getYTranslation();
                                    float f22 = yTranslation7 - height;
                                    if (yTranslation8 > f22) {
                                        yTranslation8 = f22;
                                    }
                                    expandableViewState = expandableViewState;
                                    int i18 = expandableNotificationRow.getChildrenContainer().getAttachedChildren().size() == 1 ? 1 : i9;
                                    if (i18 == 0) {
                                        viewState6.setYTranslation(yTranslation8, "DynamicStackAlgorithm.stackedForSummaryHeader");
                                    }
                                    float fFloatValue4 = ((((Number) pair3.getSecond()).floatValue() == f9 ? ((Number) pair3.getFirst()).floatValue() : ((Number) pair3.getSecond()).floatValue()) + notificationChildrenContainerDivider) - yTranslation8;
                                    if (dynamicStackAlgorithm.getLockscreenShadeTransitionController().isAnimatingToExpand()) {
                                        viewState6.mExpandableViewStateEx.setGroupExpandHeaderAlpha(f5);
                                    } else if (i18 != 0) {
                                        ExpandableNotificationRow expandableNotificationRow4 = (ExpandableNotificationRow) CollectionsKt___CollectionsKt.first(expandableNotificationRow.getChildrenContainer().getAttachedChildren());
                                        if (expandableNotificationRow4.getTranslationY() <= height) {
                                            float translationY = height - expandableNotificationRow4.getTranslationY();
                                            OplusExpandableViewStateEx oplusExpandableViewStateEx3 = viewState6.mExpandableViewStateEx;
                                            StackAlgorithmManager.Companion.getClass();
                                            f9 = f9;
                                            oplusExpandableViewStateEx3.setGroupExpandHeaderAlpha(RangesKt___RangesKt.coerceIn(MathUtils.lerp(1.0f, f9, translationY / height), f9, 1.0f));
                                        }
                                    } else {
                                        f9 = f9;
                                        OplusExpandableViewStateEx oplusExpandableViewStateEx4 = viewState6.mExpandableViewStateEx;
                                        StackAlgorithmManager.Companion.getClass();
                                        oplusExpandableViewStateEx4.setGroupExpandHeaderAlpha(RangesKt___RangesKt.coerceIn(MathUtils.lerp(1.0f, f9, fFloatValue4 / height), f9, 1.0f));
                                    }
                                    f9 = f9;
                                }
                            }
                            if (i8 != 0) {
                                fUpdateScale = dynamicStackAlgorithm.updateScale(z4, expandableView2, f6, dynamicStackDimens3);
                                ExpandableViewState expandableViewState3 = expandableViewState;
                                dynamicStackAlgorithm.updateTranslationY(z4, expandableViewState3, f6, expandableView2, dynamicStackDimens3);
                                expandableViewState2 = expandableViewState3;
                            } else {
                                expandableViewState2 = expandableViewState;
                            }
                            boolean z7 = z4;
                            float f23 = f6;
                            float f24 = fUpdateScale;
                            if (expandableView4 == null && expandableView4.isSummaryWithChildren()) {
                                DynamicStackAlgorithm dynamicStackAlgorithm5 = this;
                                ExpandableView expandableView12 = expandableView4;
                                dynamicStackAlgorithm5.stackedForSectionHeader((ExpandableView) pair2.getFirst(), expandableView2.getViewState(), f24, ((Number) pair2.getSecond()).floatValue(), f10, expandableView12);
                                expandableView5 = expandableView12;
                                dynamicStackAlgorithm2 = dynamicStackAlgorithm5;
                            } else {
                                expandableView5 = expandableView4;
                                dynamicStackAlgorithm2 = this;
                            }
                            dynamicStackAlgorithm2.updateStackedViewAlpha(z7, r13, yTranslation, f23, i3, r12, expandableView5);
                            expandableView6 = expandableView2;
                            updateStackedProgress(expandableViewState2, yTranslation, r12.getNotificationChildrenContainerDivider(), expandableView6, r12);
                            if (expandableView6.isChildInGroup()) {
                                yTranslation = expandableViewState2.getYTranslation() + expandableView6.getNotificationParent().getViewState().getYTranslation() + expandableViewState2.height;
                            } else {
                                float yTranslation9 = expandableViewState2.getYTranslation();
                                childrenContainer = expandableView6.getChildrenContainer();
                                if (childrenContainer != null) {
                                    headerHeight = childrenContainer.getHeaderHeight();
                                } else {
                                    headerHeight = f9;
                                }
                                yTranslation = headerHeight + yTranslation9;
                            }
                            if (i4 == 0 || yTranslation >= f23) {
                                i4 = 1;
                            } else {
                                i4 = i9;
                            }
                            bottomBorder2 = f23;
                            i3 = i3;
                            mutableList = list2;
                            i10 = i9;
                            c = 52429;
                            f17 = 1.0f;
                            i11 = 1;
                            dynamicStackDimens = dynamicStackDimens3;
                            f2 = f10;
                            dynamicStackAlgorithm = dynamicStackAlgorithm2;
                            obj = expandableView6;
                        } else {
                            f10 = f2;
                        }
                        expandableView4 = expandableView3;
                        if (i8 != 0) {
                            fUpdateScale = dynamicStackAlgorithm.updateScale(z4, expandableView2, f6, dynamicStackDimens3);
                            ExpandableViewState expandableViewState4 = expandableViewState;
                            dynamicStackAlgorithm.updateTranslationY(z4, expandableViewState4, f6, expandableView2, dynamicStackDimens3);
                            expandableViewState2 = expandableViewState4;
                        } else {
                            expandableViewState2 = expandableViewState;
                        }
                        boolean z8 = z4;
                        float f25 = f6;
                        float f26 = fUpdateScale;
                        if (expandableView4 == null) {
                            expandableView5 = expandableView4;
                            dynamicStackAlgorithm2 = this;
                        } else {
                            expandableView5 = expandableView4;
                            dynamicStackAlgorithm2 = this;
                        }
                        dynamicStackAlgorithm2.updateStackedViewAlpha(z8, r13, yTranslation, f25, i3, r12, expandableView5);
                        expandableView6 = expandableView2;
                        updateStackedProgress(expandableViewState2, yTranslation, r12.getNotificationChildrenContainerDivider(), expandableView6, r12);
                        if (expandableView6.isChildInGroup()) {
                            yTranslation = expandableViewState2.getYTranslation() + expandableView6.getNotificationParent().getViewState().getYTranslation() + expandableViewState2.height;
                        } else {
                            float yTranslation10 = expandableViewState2.getYTranslation();
                            childrenContainer = expandableView6.getChildrenContainer();
                            if (childrenContainer != null) {
                                headerHeight = childrenContainer.getHeaderHeight();
                            } else {
                                headerHeight = f9;
                            }
                            yTranslation = headerHeight + yTranslation10;
                        }
                        if (i4 == 0) {
                            i4 = 1;
                        } else {
                            i4 = 1;
                        }
                        bottomBorder2 = f25;
                        i3 = i3;
                        mutableList = list2;
                        i10 = i9;
                        c = 52429;
                        f17 = 1.0f;
                        i11 = 1;
                        dynamicStackDimens = dynamicStackDimens3;
                        f2 = f10;
                        dynamicStackAlgorithm = dynamicStackAlgorithm2;
                        obj = expandableView6;
                    }
                    f3 = f2;
                    f4 = bottomBorder2;
                    i5 = i10;
                    dynamicStackDimens2 = dynamicStackDimens;
                    i6 = i3;
                    Pair pair4 = new Pair(obj, Float.valueOf(yTranslation));
                    expandableView = (ExpandableView) pair4.component1();
                    fFloatValue2 = ((Number) pair4.component2()).floatValue();
                }
                bottomBorder2 = f4;
                expandableView8 = expandableView;
                i14 = i6;
                dynamicStackDimensCurrentDynamicStackDimens2 = dynamicStackDimens2;
                paddingBetweenElements2 = f3;
                i10 = i5;
                f16 = f;
                expandableViews2 = list;
                c = 52429;
                f17 = 1.0f;
                i11 = 1;
                dynamicStackAlgorithm4 = dynamicStackAlgorithm;
            } else {
                f = f16;
            }
            if (i16 != 0) {
                i = i11;
            } else {
                i = i10;
            }
            viewState = expandableView9.getViewState();
            if (expandableView9 instanceof ExpandableNotificationRow) {
                expandableNotificationRow2 = (ExpandableNotificationRow) expandableView9;
                if (expandableNotificationRow2.mIsSummaryWithChildren) {
                    i2 = i10;
                } else {
                    i2 = i10;
                }
            } else {
                i2 = i10;
            }
            if (i15 == 0) {
                z = i10;
            } else {
                z = i10;
            }
            ((ViewState) viewState).hidden = z;
            if (i2 == 0) {
                dynamicStackDimens4 = dynamicStackDimensCurrentDynamicStackDimens2;
                boolean zShouldRestartStack4 = shouldRestartStack(i17, expandableViews2, viewState, expandableView9, false, dynamicStackDimens4);
                list = expandableViews2;
                fUpdateScale2 = dynamicStackAlgorithm4.updateScale(zShouldRestartStack4, expandableView9, bottomBorder2, dynamicStackDimens4);
                if (fUpdateScale2 < dynamicStackAlgorithm4.smallScale) {
                    dynamicStackAlgorithm4.smallScale = fUpdateScale2;
                }
                f12 = bottomBorder2;
                dynamicStackAlgorithm4.updateTranslationY(zShouldRestartStack4, viewState, f12, expandableView9, dynamicStackDimens4);
                z5 = i;
                ExpandableView expandableView13 = expandableView8;
                f13 = fFloatValue2;
                dynamicStackAlgorithm4.updateStackedViewAlpha(zShouldRestartStack4, expandableView9, f13, f12, z5, dynamicStackDimens4, expandableView13);
                updateStackedProgress(viewState, f13, paddingBetweenElements2, expandableView9, dynamicStackDimens4);
                dynamicStackAlgorithm = this;
                f14 = paddingBetweenElements2;
                dynamicStackAlgorithm.stackedForSectionHeader(expandableView13, viewState, fUpdateScale2, f13, f14, expandableView9);
                expandableView = expandableView9;
                if (expandableView instanceof SectionHeaderView) {
                    float yTranslation11 = viewState.getYTranslation() + viewState.height;
                    if (i15 == 0) {
                        i15 = i11;
                    } else {
                        i15 = i11;
                    }
                    f3 = f14;
                    f4 = f12;
                    i5 = i10;
                    dynamicStackDimens2 = dynamicStackDimens4;
                    i6 = z5 ? 1 : 0;
                    yTranslation5 = 0.0f;
                    fFloatValue2 = yTranslation11;
                } else {
                    yTranslation5 = viewState.getYTranslation() + viewState.height;
                    f3 = f14;
                    i5 = i10;
                    dynamicStackDimens2 = dynamicStackDimens4;
                    i6 = z5 ? 1 : 0;
                    fFloatValue2 = f13;
                    f4 = f12;
                }
            } else {
                list = expandableViews2;
                f2 = paddingBetweenElements2;
                i3 = i;
                float f27 = fFloatValue2;
                dynamicStackDimens = dynamicStackDimensCurrentDynamicStackDimens2;
                ExpandableView expandableView14 = expandableView8;
                if (i17 == list.size() - i11) {
                    z2 = i11;
                } else {
                    z2 = i10;
                }
                mutableList = CollectionsKt___CollectionsKt.toMutableList(((ExpandableNotificationRow) expandableView9).getChildrenContainer().getAttachedChildren());
                mutableList.subList(Math.min(mutableList.size(), 8), mutableList.size()).clear();
                mutableList.add(i10, expandableView9);
                pair = new Pair(expandableView14, Float.valueOf(f27));
                Iterable<IndexedValue> iterableWithIndex2 = CollectionsKt___CollectionsKt.withIndex(mutableList);
                float fFloatValue5 = ((Number) pair.getSecond()).floatValue();
                Object first3 = pair.getFirst();
                first = pair.getFirst();
                if (first instanceof SectionHeaderView) {
                    sectionHeaderView = (SectionHeaderView) first;
                } else {
                    sectionHeaderView = null;
                }
                Pair pair5 = new Pair(sectionHeaderView, pair.getSecond());
                yTranslation = fFloatValue5;
                i4 = i10;
                dynamicStackAlgorithm = dynamicStackAlgorithm4;
                obj = first3;
                while (r27.hasNext()) {
                    i7 = indexedValue2.index;
                    expandableView2 = (ExpandableNotificationRow) indexedValue2.value;
                    if (i7 == i11) {
                        i8 = i11;
                    } else {
                        i8 = i10;
                    }
                    viewState2 = expandableView2.getViewState();
                    if (i4 == 0) {
                        z3 = i10;
                    } else {
                        z3 = i10;
                    }
                    ((ViewState) viewState2).hidden = z3;
                    dynamicStackDimens3 = dynamicStackDimens;
                    List list3 = mutableList;
                    zShouldRestartStack = shouldRestartStack(i7, list3, viewState2, expandableView2, z2, dynamicStackDimens3);
                    Object obj4 = obj;
                    if (i8 != 0) {
                        fStackViewHeight = bottomBorder2 - dynamicStackAlgorithm.stackViewHeight(viewState2, dynamicStackDimens3);
                        if (zShouldRestartStack) {
                            fStackViewHeight2 = fStackViewHeight;
                        } else {
                            fStackViewHeight2 = bottomBorder2 - (dynamicStackAlgorithm.stackViewHeight(viewState2, dynamicStackDimens3) + dynamicStackDimens3.getTranslationDampingBaseHeight());
                        }
                        if (expandableView2.isChildInGroup()) {
                            yTranslation2 = (viewState2.getYTranslation() + expandableView2.getNotificationParent().getViewState().getYTranslation()) - fStackViewHeight2;
                        } else {
                            yTranslation2 = viewState2.getYTranslation() - fStackViewHeight2;
                        }
                        if (yTranslation2 <= 0.0f) {
                            i9 = i10;
                            yTranslation3 = viewState2.getYTranslation();
                        } else {
                            float fStackViewHeight6 = dynamicStackAlgorithm.stackViewHeight(viewState2, dynamicStackDimens3) + dynamicStackDimens3.getTranslationDampingBaseHeight();
                            i9 = i10;
                            fCoerceIn = RangesKt___RangesKt.coerceIn(yTranslation2 / fStackViewHeight6, 0.0f, f17);
                            if (expandableView2.isChildInGroup()) {
                                ExpandableViewState viewState7 = expandableView2.getNotificationParent().getViewState();
                                yTranslation3 = MathUtils.lerp(fStackViewHeight2 - viewState7.getYTranslation(), fStackViewHeight - viewState7.getYTranslation(), fCoerceIn);
                                if (yTranslation3 < 0.0f) {
                                    yTranslation3 = 0.0f;
                                }
                            } else {
                                yTranslation3 = viewState2.getYTranslation();
                            }
                        }
                        viewState3 = expandableView2.getViewState();
                        if (zShouldRestartStack) {
                            fStackViewHeight3 = bottomBorder2 - dynamicStackAlgorithm.stackViewHeight(viewState3, dynamicStackDimens3);
                        } else {
                            fStackViewHeight3 = bottomBorder2 - (dynamicStackAlgorithm.stackViewHeight(viewState3, dynamicStackDimens3) + dynamicStackDimens3.getScaleDampingBaseHeight());
                        }
                        if (expandableView2.isChildInGroup()) {
                            yTranslation4 = (expandableView2.getNotificationParent().getViewState().getYTranslation() + yTranslation3) - fStackViewHeight3;
                        } else {
                            yTranslation4 = yTranslation3 - fStackViewHeight3;
                        }
                        fCoerceIn2 = RangesKt___RangesKt.coerceIn(yTranslation4 / ((dynamicStackAlgorithm.stackViewHeight(viewState3, dynamicStackDimens3) + dynamicStackDimens3.getScaleDampingBaseHeight()) * 1.5f), 0.0f, f17);
                        if (fCoerceIn2 <= 0.0f) {
                            fLerp = f17;
                            f5 = fLerp;
                        } else {
                            if (dynamicStackAlgorithm.isOrGoingToKeyguard()) {
                                f11 = 0.95f;
                                f5 = f17;
                            } else {
                                f11 = f17;
                                f5 = f11;
                            }
                            fLerp = MathUtils.lerp(f11, 0.8f, fCoerceIn2);
                        }
                        NotificationStackedRuler.Companion.requestStackedScale(viewState3, fLerp);
                        float f28 = yTranslation3;
                        fUpdateScale = fLerp;
                        z4 = zShouldRestartStack;
                        expandableViewState = viewState2;
                        f8 = f28;
                        f6 = bottomBorder2;
                        f7 = 0.0f;
                    } else {
                        i9 = i10;
                        f5 = f17;
                        fUpdateScale = dynamicStackAlgorithm.updateScale(zShouldRestartStack, expandableView2, bottomBorder2, dynamicStackDimens3);
                        f6 = bottomBorder2;
                        z4 = zShouldRestartStack;
                        expandableViewState = viewState2;
                        f7 = 0.0f;
                        dynamicStackAlgorithm.updateTranslationY(z4, expandableViewState, f6, expandableView2, dynamicStackDimens3);
                        f8 = 0.0f;
                    }
                    if (fUpdateScale < dynamicStackAlgorithm.smallScale) {
                        dynamicStackAlgorithm.smallScale = fUpdateScale;
                    }
                    if (i8 != 0) {
                        fFloatValue = ((Number) pair.getSecond()).floatValue();
                    } else {
                        fFloatValue = yTranslation;
                    }
                    expandableView3 = (ExpandableView) obj4;
                    f9 = f7;
                    Pair pair6 = new Pair(Float.valueOf(fFloatValue), Float.valueOf(yTranslation5));
                    float notificationChildrenContainerDivider2 = dynamicStackDimens3.getNotificationChildrenContainerDivider();
                    if (expandableView3 instanceof ExpandableNotificationRow) {
                        expandableNotificationRow = (ExpandableNotificationRow) expandableView3;
                        f10 = f2;
                        if (!expandableNotificationRow.mIsSummaryWithChildren) {
                        }
                    } else {
                        f10 = f2;
                    }
                    expandableView4 = expandableView3;
                    if (i8 != 0) {
                        fUpdateScale = dynamicStackAlgorithm.updateScale(z4, expandableView2, f6, dynamicStackDimens3);
                        ExpandableViewState expandableViewState5 = expandableViewState;
                        dynamicStackAlgorithm.updateTranslationY(z4, expandableViewState5, f6, expandableView2, dynamicStackDimens3);
                        expandableViewState2 = expandableViewState5;
                    } else {
                        expandableViewState2 = expandableViewState;
                    }
                    boolean z9 = z4;
                    float f29 = f6;
                    float f210 = fUpdateScale;
                    if (expandableView4 == null) {
                        expandableView5 = expandableView4;
                        dynamicStackAlgorithm2 = this;
                    } else {
                        expandableView5 = expandableView4;
                        dynamicStackAlgorithm2 = this;
                    }
                    dynamicStackAlgorithm2.updateStackedViewAlpha(z9, r13, yTranslation, f29, i3, r12, expandableView5);
                    expandableView6 = expandableView2;
                    updateStackedProgress(expandableViewState2, yTranslation, r12.getNotificationChildrenContainerDivider(), expandableView6, r12);
                    if (expandableView6.isChildInGroup()) {
                        yTranslation = expandableViewState2.getYTranslation() + expandableView6.getNotificationParent().getViewState().getYTranslation() + expandableViewState2.height;
                    } else {
                        float yTranslation12 = expandableViewState2.getYTranslation();
                        childrenContainer = expandableView6.getChildrenContainer();
                        if (childrenContainer != null) {
                            headerHeight = childrenContainer.getHeaderHeight();
                        } else {
                            headerHeight = f9;
                        }
                        yTranslation = headerHeight + yTranslation12;
                    }
                    if (i4 == 0) {
                        i4 = 1;
                    } else {
                        i4 = 1;
                    }
                    bottomBorder2 = f29;
                    i3 = i3;
                    mutableList = list3;
                    i10 = i9;
                    c = 52429;
                    f17 = 1.0f;
                    i11 = 1;
                    dynamicStackDimens = dynamicStackDimens3;
                    f2 = f10;
                    dynamicStackAlgorithm = dynamicStackAlgorithm2;
                    obj = expandableView6;
                }
                f3 = f2;
                f4 = bottomBorder2;
                i5 = i10;
                dynamicStackDimens2 = dynamicStackDimens;
                i6 = i3;
                Pair pair7 = new Pair(obj, Float.valueOf(yTranslation));
                expandableView = (ExpandableView) pair7.component1();
                fFloatValue2 = ((Number) pair7.component2()).floatValue();
            }
            bottomBorder2 = f4;
            expandableView8 = expandableView;
            i14 = i6;
            dynamicStackDimensCurrentDynamicStackDimens2 = dynamicStackDimens2;
            paddingBetweenElements2 = f3;
            i10 = i5;
            f16 = f;
            expandableViews2 = list;
            c = 52429;
            f17 = 1.0f;
            i11 = 1;
            dynamicStackAlgorithm4 = dynamicStackAlgorithm;
        }
        boolean z10 = i10;
        if (dynamicStackAlgorithm4.smallScale < 1.0f) {
            return true;
        }
        return z10;
    }

    public final DampedStackLayout computeDampedStackLayout(float f, float f2, float f3, DynamicStackDimens dynamicStackDimens, boolean z) {
        float fComputeDampedTranslationY = computeDampedTranslationY(f, f2, f3, dynamicStackDimens, z);
        float scaleDampingBaseHeight = z ? f3 - f2 : f3 - (dynamicStackDimens.getScaleDampingBaseHeight() + f2);
        float fLerp = 1.0f;
        float fCoerceIn = f <= scaleDampingBaseHeight ? 0.0f : RangesKt___RangesKt.coerceIn((f - scaleDampingBaseHeight) / ((dynamicStackDimens.getScaleDampingBaseHeight() + f2) * 1.5f), 0.0f, 1.0f);
        if (fCoerceIn > 0.0f) {
            fLerp = MathUtils.lerp(isOrGoingToKeyguard() ? 0.95f : 1.0f, 0.8f, fCoerceIn);
        }
        return new DampedStackLayout(fComputeDampedTranslationY, fLerp, fCoerceIn);
    }

    public final DynamicStackDimens currentDynamicStackDimens() {
        DynamicStackDimensCache dynamicStackDimensCache = this.dimensCache;
        if (dynamicStackDimensCache == null) {
            dynamicStackDimensCache = null;
        }
        return dynamicStackDimensCache.getDimens();
    }

    public final void initializeDimensCache() {
        this.dimensCache = new DynamicStackDimensCache(this.context);
    }

    public final boolean isOrGoingToKeyguard() {
        return (getStatusBarStateController().getState() == 1 && !getLockscreenShadeTransitionController().isAnimatingToExpand()) || getLockscreenShadeTransitionController().isAnimatingToCollapse();
    }

    public final float stackViewHeight(ExpandableViewState expandableViewState, DynamicStackDimens dynamicStackDimens) {
        int stackedLargeNotificationLimitedHeight;
        if (isOrGoingToKeyguard()) {
            return expandableViewState.height;
        }
        int i = expandableViewState.height;
        int largeNotificationHeight = dynamicStackDimens.getLargeNotificationHeight();
        int i2 = expandableViewState.height;
        if (i > largeNotificationHeight && i2 > (stackedLargeNotificationLimitedHeight = dynamicStackDimens.getStackedLargeNotificationLimitedHeight())) {
            i2 = stackedLargeNotificationLimitedHeight;
        }
        return i2;
    }

    public final void stackedForSectionHeader(ExpandableView expandableView, ExpandableViewState expandableViewState, float f, float f2, float f3, ExpandableView expandableView2) {
        float yTranslation;
        if (expandableView instanceof SectionHeaderView) {
            ExpandableViewState viewState = ((SectionHeaderView) expandableView).getViewState();
            if (expandableView2 != null && expandableView2.isSummaryWithChildren() && expandableView2.isGroupExpanded()) {
                yTranslation = ((1.0f - f) * expandableViewState.height * 0.5f) + expandableViewState.getYTranslation() + expandableView2.getViewState().getYTranslation();
            } else {
                yTranslation = ((1.0f - f) * expandableViewState.height * 0.5f) + expandableViewState.getYTranslation();
            }
            float f4 = (viewState.height + f3) * 0.8f;
            float yTranslation2 = viewState.getYTranslation();
            float f5 = yTranslation - f4;
            if (yTranslation2 > f5) {
                yTranslation2 = f5;
            }
            viewState.setYTranslation(yTranslation2, "DynamicStackAlgorithm.stackedForSectionHeader");
            viewState.setAlpha(getLockscreenShadeTransitionController().isAnimatingToExpand() ? 1.0f : RangesKt___RangesKt.coerceIn(MathUtils.lerp(1.0f, 0.0f, ((f2 + f3) - viewState.getYTranslation()) / f4), 0.0f, 1.0f), "DynamicStackAlgorithm.stackedForSectionHeader");
        }
    }

    public final float updateScale(boolean z, ExpandableView expandableView, float f, DynamicStackDimens dynamicStackDimens) {
        float yTranslation;
        ExpandableViewState viewState = expandableView.getViewState();
        float fStackViewHeight = stackViewHeight(viewState, dynamicStackDimens);
        if (expandableView.isChildInGroup() && (expandableView instanceof ExpandableNotificationRow)) {
            yTranslation = viewState.getYTranslation() + ((ExpandableNotificationRow) expandableView).getNotificationParent().getViewState().getYTranslation();
        } else {
            yTranslation = viewState.getYTranslation();
        }
        DampedStackLayout dampedStackLayoutComputeDampedStackLayout = computeDampedStackLayout(yTranslation, fStackViewHeight, f, dynamicStackDimens, z);
        NotificationStackedRuler.Companion.requestStackedScale(viewState, dampedStackLayoutComputeDampedStackLayout.getScale());
        return dampedStackLayoutComputeDampedStackLayout.getScale();
    }

    /* JADX WARN: Code duplicated, block: B:33:0x0075  */
    /* JADX WARN: Code duplicated, block: B:36:0x0081  */
    /* JADX WARN: Code duplicated, block: B:38:0x0084  */
    /* JADX WARN: Code duplicated, block: B:44:0x00a2  */
    public final void updateStackedViewAlpha(boolean z, ExpandableView expandableView, float f, float f2, boolean z2, DynamicStackDimens dynamicStackDimens, ExpandableView expandableView2) {
        float translationY;
        float f3;
        float translationY2;
        float headerHeight;
        ExpandableNotificationRow expandableNotificationRow;
        ExpandableViewState viewState = expandableView.getViewState();
        float fStackViewHeight = f2 - stackViewHeight(viewState, dynamicStackDimens);
        if (!z) {
            fStackViewHeight = f2 - (stackViewHeight(viewState, dynamicStackDimens) + dynamicStackDimens.getTranslationDampingBaseHeight());
        }
        if (expandableView.isChildInGroup() && (expandableView instanceof ExpandableNotificationRow)) {
            ExpandableNotificationRow expandableNotificationRow2 = (ExpandableNotificationRow) expandableView;
            translationY = (expandableNotificationRow2.getTranslationY() + expandableNotificationRow2.getNotificationParent().getTranslationY()) - fStackViewHeight;
        } else {
            translationY = expandableView.getTranslationY() - fStackViewHeight;
        }
        NotificationFeatureOption.INSTANCE.getClass();
        boolean z3 = NotificationFeatureOption.isNotificationLightOs || (z2 && translationY < 0.0f);
        if (expandableView2 == null) {
            headerHeight = 0.0f;
        } else {
            boolean z4 = expandableView2 instanceof ExpandableNotificationRow;
            if (z4) {
                ExpandableNotificationRow expandableNotificationRow3 = (ExpandableNotificationRow) expandableView2;
                if (expandableNotificationRow3.mIsSummaryWithChildren && expandableNotificationRow3.isGroupExpanded()) {
                    float translationY3 = expandableNotificationRow3.getTranslationY();
                    NotificationChildrenContainer childrenContainer = expandableNotificationRow3.getChildrenContainer();
                    headerHeight = (childrenContainer != null ? childrenContainer.getHeaderHeight() : 0.0f) + translationY3;
                } else {
                    f3 = expandableView2.getViewState().height;
                    if (f3 < 0.0f) {
                        f3 = 0.0f;
                    }
                    if (z4) {
                        expandableNotificationRow = (ExpandableNotificationRow) expandableView2;
                        if (expandableNotificationRow.isChildInGroup() || expandableNotificationRow.getNotificationParent() == null) {
                            translationY2 = expandableView2.getTranslationY();
                        } else {
                            translationY2 = expandableNotificationRow.getTranslationY() + expandableNotificationRow.getNotificationParent().getTranslationY();
                        }
                    } else {
                        translationY2 = expandableView2.getTranslationY();
                    }
                    headerHeight = f3 + translationY2;
                }
            } else {
                f3 = expandableView2.getViewState().height;
                if (f3 < 0.0f) {
                    f3 = 0.0f;
                }
                if (z4) {
                    expandableNotificationRow = (ExpandableNotificationRow) expandableView2;
                    if (expandableNotificationRow.isChildInGroup()) {
                        translationY2 = expandableView2.getTranslationY();
                    } else {
                        translationY2 = expandableView2.getTranslationY();
                    }
                } else {
                    translationY2 = expandableView2.getTranslationY();
                }
                headerHeight = f3 + translationY2;
            }
        }
        if (z2 && expandableView2 != null) {
            f = headerHeight;
        }
        viewState.setAlpha(z3 ? 1.0f : MathUtils.lerp(0.0f, 1.0f, RangesKt___RangesKt.coerceIn((f2 - f) / dynamicStackDimens.getStackedNotificationFadeOutRange(), 0.0f, 1.0f)), "DynamicStackAlgorithm.updateStackedViewAlpha");
    }

    public final void updateTranslationY(boolean z, ExpandableViewState expandableViewState, float f, ExpandableView expandableView, DynamicStackDimens dynamicStackDimens) {
        float yTranslation;
        float fStackViewHeight = stackViewHeight(expandableViewState, dynamicStackDimens);
        if (expandableView.isChildInGroup() && (expandableView instanceof ExpandableNotificationRow)) {
            yTranslation = expandableViewState.getYTranslation() + ((ExpandableNotificationRow) expandableView).getNotificationParent().getViewState().getYTranslation();
        } else {
            yTranslation = expandableViewState.getYTranslation();
        }
        if (yTranslation <= (z ? f - fStackViewHeight : f - (dynamicStackDimens.getTranslationDampingBaseHeight() + fStackViewHeight))) {
            return;
        }
        float fComputeDampedTranslationY = computeDampedTranslationY(yTranslation, fStackViewHeight, f, dynamicStackDimens, z);
        if (!expandableView.isChildInGroup() || !(expandableView instanceof ExpandableNotificationRow)) {
            expandableViewState.setYTranslation(fComputeDampedTranslationY, "DynamicStackAlgorithm.updateTranslationY");
            return;
        }
        float yTranslation2 = fComputeDampedTranslationY - ((ExpandableNotificationRow) expandableView).getNotificationParent().getViewState().getYTranslation();
        if (yTranslation2 < 0.0f) {
            yTranslation2 = 0.0f;
        }
        expandableViewState.setYTranslation(yTranslation2, "DynamicStackAlgorithm.updateTranslationY.isChildInGroup");
    }
}
