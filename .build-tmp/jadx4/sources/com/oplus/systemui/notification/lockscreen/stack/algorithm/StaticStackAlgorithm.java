package com.oplus.systemui.notification.lockscreen.stack.algorithm;

import android.content.Context;
import androidx.compose.animation.ChangeSize$;
import com.android.systemui.statusbar.notification.emptyshade.ui.view.EmptyShadeView;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.systemui.statusbar.notification.stack.OplusExpandableViewStateEx;
import com.android.systemui.statusbar.notification.stack.ViewState;
import com.oplus.systemui.notification.lockscreen.effect.ExtStateAnimParams;
import com.oplus.systemui.notification.stackednotification.NotificationStackedRuler;
import com.oplus.systemui.statusbar.util.KeyguardNotificationUploadUtil;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Iterator;
import java.util.List;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.sequences.SequencesKt__SequencesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StaticStackAlgorithm {
    public static final Companion Companion = new Companion();
    private static final String TAG = "StaticStackAlgorithm";
    public static float firstStackedNotificationVisibleHeight;
    public static float gapBetweenSeedlingCardAndFingerprint;
    public static float paddingBetweenElements;
    public static float secondStackedNotificationVisibleHeight;
    private final Context context;
    public int lastKeyguardStartStackedIndex = -1;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final float getFirstStackedNotificationVisibleHeight() {
            return StaticStackAlgorithm.firstStackedNotificationVisibleHeight;
        }

        public final float getGapBetweenSeedlingCardAndFingerprint() {
            return StaticStackAlgorithm.gapBetweenSeedlingCardAndFingerprint;
        }

        public final float getPaddingBetweenElements() {
            return StaticStackAlgorithm.paddingBetweenElements;
        }

        public final float getSecondStackedNotificationVisibleHeight() {
            return StaticStackAlgorithm.secondStackedNotificationVisibleHeight;
        }

        public final void setPaddingBetweenElements(float f) {
            StaticStackAlgorithm.paddingBetweenElements = f;
        }
    }

    public StaticStackAlgorithm(Context context) {
        this.context = context;
    }

    public static void handleSinkingStackForSeedling(StaticStackRequest staticStackRequest, float f, float f2) {
        List<ExpandableView> normalStackViews = staticStackRequest.getNormalStackViews();
        int size = staticStackRequest.getNormalStackViews().size();
        layoutStaticStackNotifications(normalStackViews, 1, size <= 3 ? size : 3, staticStackRequest.getBottomBorder(), staticStackRequest.getNotificationDragAmount(), Float.valueOf(f));
        List<ExpandableView> seedlingViews = staticStackRequest.getSeedlingViews();
        if (seedlingViews == null || !(!seedlingViews.isEmpty())) {
            return;
        }
        ExpandableViewState viewState = ((ExpandableView) CollectionsKt___CollectionsKt.last(seedlingViews)).getViewState();
        float yTranslation = f2 - (viewState.getYTranslation() + viewState.height);
        for (ExpandableView expandableView : seedlingViews) {
            ((ViewState) expandableView.getViewState()).hidden = false;
            expandableView.getViewState().setAlpha(1.0f, "StaticStackAlgorithm.shiftSeedlingsAlign");
            expandableView.getViewState().setYTranslation(expandableView.getViewState().getYTranslation() + yTranslation, "StaticStackAlgorithm.shiftSeedlingsAlign");
            NotificationStackedRuler.Companion.requestStackedScale(expandableView.getViewState(), 1.0f);
            OplusExpandableViewStateEx oplusExpandableViewStateEx = expandableView.getViewState().mExpandableViewStateEx;
            if (oplusExpandableViewStateEx != null) {
                oplusExpandableViewStateEx.setNotificationStackedProgress(0.0f);
            }
        }
    }

    public static void layoutStaticStackNotifications(List list, int i, int i2, float f, float f2, Float f3) {
        float f4;
        float f5 = 0.0f;
        float fFloatValue = 0.0f;
        int i3 = 0;
        for (Object obj : list) {
            int i4 = i3 + 1;
            if (i3 < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            ExpandableView expandableView = (ExpandableView) obj;
            ExpandableViewState viewState = expandableView.getViewState();
            boolean z = i3 < i2;
            viewState.setAlpha(1.0f, "StaticStackAlgorithm.layoutStaticStackNotifications");
            if (i3 == 0 && f3 != null) {
                ((ViewState) viewState).hidden = false;
                viewState.setYTranslation(f3.floatValue(), "StaticStackAlgorithm.layoutStaticStackNotifications.firstItem");
                OplusExpandableViewStateEx oplusExpandableViewStateEx = viewState.mExpandableViewStateEx;
                if (oplusExpandableViewStateEx != null) {
                    oplusExpandableViewStateEx.setNotificationStackedProgress(f5);
                }
                fFloatValue = f3.floatValue() + stableHeightOf(expandableView);
                if (fFloatValue > f) {
                    fFloatValue = f;
                }
                f4 = f5;
            } else if (i3 == 0 || i3 < i) {
                f4 = f5;
                ((ViewState) viewState).hidden = false;
                float yTranslation = viewState.getYTranslation() + viewState.height;
                if (yTranslation > f) {
                    yTranslation = f;
                }
                fFloatValue = yTranslation;
            } else {
                ((ViewState) viewState).hidden = !z;
                boolean z2 = i3 == i;
                if (z) {
                    fFloatValue += z2 ? firstStackedNotificationVisibleHeight : secondStackedNotificationVisibleHeight;
                }
                float f6 = 0.9f - ((i3 - i) * 0.1f);
                float fFloatValue2 = f6 >= 0.1f ? f6 : 0.1f;
                float f7 = z2 ? 0.5f : 1.0f;
                f4 = f5;
                OplusExpandableViewStateEx oplusExpandableViewStateEx2 = viewState.mExpandableViewStateEx;
                if (oplusExpandableViewStateEx2 != null) {
                    oplusExpandableViewStateEx2.setNotificationStackedProgress(f7);
                }
                float fStableHeightOf = expandableView.getViewState().height > 0 ? expandableView.getViewState().height : stableHeightOf(expandableView);
                float f8 = fStableHeightOf * fFloatValue2;
                float f9 = (fStableHeightOf - f8) * 0.5f;
                viewState.setYTranslation((fFloatValue - f8) - f9, "StaticStackAlgorithm.layoutStaticStackNotifications");
                if (z && f2 > f4) {
                    float f10 = f - f2;
                    if (viewState.getYTranslation() + f8 + f9 > f10) {
                        float yTranslation2 = ((viewState.getYTranslation() + f8) + f9) - f10;
                        float f11 = yTranslation2 / (z2 ? firstStackedNotificationVisibleHeight : secondStackedNotificationVisibleHeight);
                        fFloatValue2 -= ExtStateAnimParams.DraggerParams.INSTANCE.getEXT_SCALE().get().floatValue() * (f11 <= 1.0f ? f11 : 1.0f);
                        viewState.setYTranslation(ChangeSize$.ExternalSyntheticOutline0.m(f8, fStableHeightOf * fFloatValue2, 2.0f, viewState.getYTranslation() - yTranslation2), "StaticStackAlgorithm.handleDraggerStackView");
                    }
                }
                NotificationStackedRuler.Companion.requestStackedScale(viewState, fFloatValue2);
            }
            f5 = f4;
            i3 = i4;
        }
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
        int iIntValue = num != null ? num.intValue() : 0;
        if (i <= 0 && iIntValue > 0) {
            LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "[StaticStack] stableHeightOf: viewStateHeight <= 0, fallback=" + iIntValue + ", view=" + expandableView);
        }
        return iIntValue;
    }

    public final boolean apply(StaticStackRequest staticStackRequest) {
        Object next;
        int i;
        ExpandableViewState viewState;
        List<ExpandableView> seedlingViews;
        ExpandableView expandableView;
        ExpandableViewState viewState2;
        ExpandableView expandableView2;
        ExpandableViewState viewState3;
        int iOrdinal = staticStackRequest.getPhase().ordinal();
        int iCoerceIn = 0;
        float f = 0.0f;
        if (iOrdinal != 0) {
            if (iOrdinal != 1) {
                LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
                return false;
            }
            List<ExpandableView> normalStackViews = staticStackRequest.getNormalStackViews();
            int size = staticStackRequest.getNormalStackViews().size();
            if (size > 3) {
                size = 3;
            }
            float fComputeSinkingStaticStackedAreaHeight = computeSinkingStaticStackedAreaHeight(normalStackViews, size);
            float bottomBorder = staticStackRequest.getBottomBorder() - fComputeSinkingStaticStackedAreaHeight;
            if (staticStackRequest.getFingerprintTop() == -1.0f || (seedlingViews = staticStackRequest.getSeedlingViews()) == null || !(!seedlingViews.isEmpty())) {
                handleSinkingStackForSeedling(staticStackRequest, bottomBorder, staticStackRequest.getNormalStackViews().isEmpty() ? staticStackRequest.getBottomBorder() : bottomBorder - paddingBetweenElements);
                return true;
            }
            List<ExpandableView> seedlingViews2 = staticStackRequest.getSeedlingViews();
            float topBorder = staticStackRequest.getTopBorder();
            float fingerprintTop = staticStackRequest.getFingerprintTop();
            if (seedlingViews2 != null && (!seedlingViews2.isEmpty()) && (fingerprintTop - gapBetweenSeedlingCardAndFingerprint) - topBorder >= computeSeedlingNeededHeight(seedlingViews2)) {
                if (fComputeSinkingStaticStackedAreaHeight > staticStackRequest.getBottomBorder() - ((staticStackRequest.getFingerprintTop() - gapBetweenSeedlingCardAndFingerprint) + paddingBetweenElements)) {
                    handleSinkingStackForSeedling(staticStackRequest, staticStackRequest.getBottomBorder() - fComputeSinkingStaticStackedAreaHeight, (staticStackRequest.getBottomBorder() - fComputeSinkingStaticStackedAreaHeight) - paddingBetweenElements);
                    return true;
                }
                handleSinkingStackForSeedling(staticStackRequest, (staticStackRequest.getFingerprintTop() - gapBetweenSeedlingCardAndFingerprint) + paddingBetweenElements, staticStackRequest.getFingerprintTop() - gapBetweenSeedlingCardAndFingerprint);
                return true;
            }
            List<ExpandableView> seedlingViews3 = staticStackRequest.getSeedlingViews();
            float yTranslation = (seedlingViews3 == null || (expandableView2 = (ExpandableView) CollectionsKt___CollectionsKt.last(seedlingViews3)) == null || (viewState3 = expandableView2.getViewState()) == null) ? 0.0f : viewState3.getYTranslation();
            List<ExpandableView> seedlingViews4 = staticStackRequest.getSeedlingViews();
            if (seedlingViews4 != null && (expandableView = (ExpandableView) CollectionsKt___CollectionsKt.last(seedlingViews4)) != null && (viewState2 = expandableView.getViewState()) != null) {
                f = viewState2.height;
            }
            float f2 = yTranslation + f + paddingBetweenElements;
            List<ExpandableView> normalStackViews2 = staticStackRequest.getNormalStackViews();
            int size2 = staticStackRequest.getNormalStackViews().size();
            layoutStaticStackNotifications(normalStackViews2, 1, size2 > 3 ? 3 : size2, staticStackRequest.getBottomBorder(), staticStackRequest.getNotificationDragAmount(), Float.valueOf(f2));
            return true;
        }
        List<? extends ExpandableView> normalStackViews3 = staticStackRequest.getNormalStackViews();
        float bottomBorder2 = staticStackRequest.getBottomBorder();
        float notificationDragAmount = staticStackRequest.getNotificationDragAmount();
        int size3 = normalStackViews3.size();
        if (size3 <= 1) {
            this.lastKeyguardStartStackedIndex = -1;
            return false;
        }
        Iterator it = CollectionsKt___CollectionsKt.withIndex(normalStackViews3).iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            viewState = ((ExpandableView) ((IndexedValue) next).getValue()).getViewState();
        } while (viewState.getYTranslation() + viewState.height <= bottomBorder2);
        IndexedValue indexedValue = (IndexedValue) next;
        if (indexedValue == null) {
            this.lastKeyguardStartStackedIndex = -1;
            return false;
        }
        int index = indexedValue.getIndex() - 1;
        if (index < 0) {
            index = 0;
        }
        ExpandableView expandableView3 = (ExpandableView) CollectionsKt___CollectionsKt.getOrNull(normalStackViews3, index);
        if (expandableView3 == null) {
            this.lastKeyguardStartStackedIndex = -1;
            return false;
        }
        if (indexedValue.getIndex() != 0 || size3 <= 1) {
            if (notificationDragAmount <= 0.0f || (i = this.lastKeyguardStartStackedIndex) < 0) {
                float yTranslation2 = bottomBorder2 - (expandableView3.getViewState().getYTranslation() + expandableView3.getViewState().height);
                int index2 = (indexedValue.getIndex() != size3 - 1 ? yTranslation2 <= firstStackedNotificationVisibleHeight + secondStackedNotificationVisibleHeight : yTranslation2 <= firstStackedNotificationVisibleHeight) ? indexedValue.getIndex() - 1 : indexedValue.getIndex();
                iCoerceIn = index2 < 1 ? 1 : index2;
                this.lastKeyguardStartStackedIndex = iCoerceIn;
            } else {
                iCoerceIn = RangesKt___RangesKt.coerceIn(i, 0, size3 - 1);
            }
        }
        int i2 = iCoerceIn;
        layoutStaticStackNotifications(normalStackViews3, i2, i2 + 2, bottomBorder2, notificationDragAmount, null);
        KeyguardNotificationUploadUtil.Companion.getInstance().collectStackNotificationData(normalStackViews3);
        return true;
    }

    public final float computeSeedlingNeededHeight(List<? extends ExpandableView> list) {
        if (list.isEmpty()) {
            return 0.0f;
        }
        ExpandableViewState viewState = ((ExpandableView) CollectionsKt___CollectionsKt.first(list)).getViewState();
        ExpandableViewState viewState2 = ((ExpandableView) CollectionsKt___CollectionsKt.last(list)).getViewState();
        return (viewState2.getYTranslation() + viewState2.height) - viewState.getYTranslation();
    }

    public final float computeSinkingStaticStackedAreaHeight(List<? extends ExpandableView> list, int i) {
        float f;
        if (i <= 0) {
            return 0.0f;
        }
        float fStableHeightOf = stableHeightOf(list.get(0));
        if (i >= 3) {
            fStableHeightOf += firstStackedNotificationVisibleHeight;
            f = secondStackedNotificationVisibleHeight;
        } else {
            if (i != 2) {
                return fStableHeightOf;
            }
            f = firstStackedNotificationVisibleHeight;
        }
        return fStableHeightOf + f;
    }

    public final void pinSinkingHiddenAtBottom(List<? extends ExpandableView> list, float f) {
        for (ExpandableView expandableView : list) {
            if (!(expandableView instanceof EmptyShadeView)) {
                ExpandableViewState viewState = expandableView.getViewState();
                if (((ViewState) viewState).hidden) {
                    float fStableHeightOf = stableHeightOf(expandableView);
                    float f2 = fStableHeightOf * 0.8f;
                    viewState.setYTranslation((f - f2) - ((fStableHeightOf - f2) * 0.5f), "StaticStackAlgorithm.pinSinkingHiddenAtBottom");
                    NotificationStackedRuler.Companion.requestStackedScale(viewState, 0.8f);
                    OplusExpandableViewStateEx oplusExpandableViewStateEx = viewState.mExpandableViewStateEx;
                    if (oplusExpandableViewStateEx != null) {
                        oplusExpandableViewStateEx.setNotificationStackedProgress(1.0f);
                    }
                }
            }
        }
    }

    public final void refreshDimens() {
        firstStackedNotificationVisibleHeight = this.context.getResources().getDimension(2131168192);
        secondStackedNotificationVisibleHeight = this.context.getResources().getDimension(2131172441);
        gapBetweenSeedlingCardAndFingerprint = this.context.getResources().getDimension(2131167773);
        paddingBetweenElements = this.context.getResources().getDimension(2131170633);
    }
}
