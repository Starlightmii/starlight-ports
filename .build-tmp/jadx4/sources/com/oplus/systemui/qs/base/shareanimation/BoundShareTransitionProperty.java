package com.oplus.systemui.qs.base.shareanimation;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.OneShotPreDrawListener;
import androidx.exifinterface.media.ExifInterface$;
import com.oplus.systemui.aod.anim.OplusAODMaskAnimController$;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BoundShareTransitionProperty extends ShareTransitionProperty {
    public static final Companion Companion = new Companion();
    private static final String TAG = "BoundShareTransitionProperty";
    private final Rect endRect;
    private final Rect getRectHost;
    public final boolean isNestedBoundShare;
    private final RectLost rectLost;
    private final Rect targetValueHost;
    private final Rect targetValueTransferredHost;

    public BoundShareTransitionProperty(ViewGroup viewGroup, int i, boolean z) {
        super(viewGroup, i, new Rect(), new Rect());
        this.isNestedBoundShare = z;
        this.targetValueHost = new Rect();
        this.targetValueTransferredHost = new Rect();
        this.getRectHost = new Rect();
        this.endRect = new Rect();
        this.rectLost = new RectLost(0.0f, 0.0f, 0.0f, 0.0f);
    }

    @Override // com.oplus.systemui.qs.base.shareanimation.ShareTransitionProperty
    public void captureValues(List<? extends ViewGroup> list) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (((ViewGroup) obj).isLaidOut()) {
                arrayList.add(obj);
            }
        }
        super.captureValues(arrayList);
        if (!this.isNestedBoundShare) {
            HashMap<WeakReference<ViewGroup>, Object> valuesOfDiffState = getValuesOfDiffState();
            captureValues.2 r0 = new captureValues.2();
            r0.this$0 = this;
            VarHandle.storeStoreFence();
            valuesOfDiffState.replaceAll(r0);
        }
        for (Map.Entry<WeakReference<ViewGroup>, Object> entry : getValuesOfDiffState().entrySet()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                StringBuilder sb = new StringBuilder("BoundShareTransitionProperty-->");
                ShareTransitionProperty.Companion companion = ShareTransitionProperty.Companion;
                String strSimpleNameAndHash = companion.simpleNameAndHash(this);
                ViewGroup viewGroup = entry.getKey().get();
                String strSimpleNameAndHash2 = viewGroup != null ? companion.simpleNameAndHash(viewGroup) : null;
                Object value = entry.getValue();
                StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("[captureValues]", strSimpleNameAndHash, " for ", strSimpleNameAndHash2, " = ");
                sbM.append(value);
                sb.append(sbM.toString());
                Log.d("SystemUi--QuickSettings", sb.toString());
            }
        }
    }

    @Override // com.oplus.systemui.qs.base.shareanimation.ShareTransitionProperty
    public Rect getValue(View view) {
        Rect rect = new Rect();
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        return rect;
    }

    @Override // com.oplus.systemui.qs.base.shareanimation.ShareTransitionProperty
    public void initValue(ViewGroup viewGroup, View view, boolean z) {
        this.endRect.set((Rect) getValue());
        super.initValue(viewGroup, view, z);
        this.targetValueHost.set((Rect) getValue());
        tryApplyBoundsDiff(viewGroup, this.targetValueHost);
    }

    @Override // com.oplus.systemui.qs.base.shareanimation.ShareTransitionProperty
    public ShareTransitionProperty moveTo(ViewGroup viewGroup) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "BoundShareTransitionProperty-->".concat("[moveTo]" + ShareTransitionProperty.Companion.simpleNameAndHash(this) + " from " + getTargetViewHost() + " to " + viewGroup));
        }
        if (Intrinsics.areEqual(viewGroup, getTargetViewHost())) {
            return this;
        }
        BoundShareTransitionProperty boundShareTransitionProperty = new BoundShareTransitionProperty(viewGroup, getSharedElementId(), false);
        boundShareTransitionProperty.getValuesOfDiffState().clear();
        boundShareTransitionProperty.getValuesOfDiffState().putAll(getValuesOfDiffState());
        this.rectLost.copyTo(boundShareTransitionProperty.rectLost);
        boundShareTransitionProperty.ensureSharedElementAndInit(getExpectedFromHost());
        BoundShareTransitionProperty$moveTo$.inlined.doOnPreDraw.1 r5 = new BoundShareTransitionProperty$moveTo$.inlined.doOnPreDraw.1();
        r5.this$0 = this;
        r5.$copied$inlined = boundShareTransitionProperty;
        r5.$newTargetViewHost$inlined = viewGroup;
        VarHandle.storeStoreFence();
        OneShotPreDrawListener.add(viewGroup, r5);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusAODMaskAnimController$.ExternalSyntheticOutline0.m("[moveTo]", ShareTransitionProperty.Companion.simpleNameAndHash(this), " continue successfully", "BoundShareTransitionProperty-->", "SystemUi--QuickSettings");
        }
        return boundShareTransitionProperty;
    }

    @Override // com.oplus.systemui.qs.base.shareanimation.ShareTransitionProperty
    public final void onTransitionEnd(boolean z) {
        Rect rectTryApplyBoundsDiff;
        super.onTransitionEnd(false);
        Rect rect = (Rect) getSavedValueWhenEnd();
        if (rect == null) {
            return;
        }
        if (!rect.isEmpty()) {
            ViewGroup targetViewHost = getTargetViewHost();
            if (targetViewHost != null && (rectTryApplyBoundsDiff = tryApplyBoundsDiff(targetViewHost, rect)) != null) {
                rect = rectTryApplyBoundsDiff;
            }
            setSavedValueWhenEnd(rect);
        }
        ViewGroup targetViewHost2 = getTargetViewHost();
        if (targetViewHost2 != null) {
            ShareTransitionProperty.Companion companion = ShareTransitionProperty.Companion;
            View viewFindViewByIdUseCache = companion.findViewByIdUseCache(targetViewHost2, getSharedElementId(), true);
            Object tag = targetViewHost2.getTag(getSharedElementId());
            WeakReference weakReference = tag instanceof WeakReference ? (WeakReference) tag : null;
            if ((weakReference != null ? (View) weakReference.get() : null) == null && viewFindViewByIdUseCache != null) {
                ViewOverlayManagerKt.minusBoundsDiff(viewFindViewByIdUseCache, this.endRect);
            }
            if (viewFindViewByIdUseCache != null) {
                Rect rect2 = this.endRect;
                viewFindViewByIdUseCache.setLeftTopRightBottom(rect2.left, rect2.top, rect2.right, rect2.bottom);
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "BoundShareTransitionProperty-->".concat("[onTransitionEnd]" + companion.simpleNameAndHash(this) + " reset to " + this.endRect));
            }
            companion.clearIdCache(targetViewHost2, getSharedElementId());
        }
        RectLost rectLost = this.rectLost;
        rectLost.left = 0.0f;
        rectLost.top = 0.0f;
        rectLost.right = 0.0f;
        rectLost.bottom = 0.0f;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "BoundShareTransitionProperty-->".concat("[onTransitionEnd]" + ShareTransitionProperty.Companion.simpleNameAndHash(this) + " savedValueWhenEnd=" + getSavedValueWhenEnd()));
        }
    }

    @Override // com.oplus.systemui.qs.base.shareanimation.ShareTransitionProperty
    public void setProgress(Rect rect, float f, float f2, Rect rect2) {
        int iApply;
        int iApply2;
        int iApply3;
        int iApply4;
        int i;
        float f3;
        int i2;
        int iApply5;
        int iApply6;
        int i3;
        float f4;
        int i4;
        int iApply7;
        int iApply8;
        boolean z = false;
        boolean z2 = 0.0f <= f && f <= 1.0f;
        if (0.0f <= f2 && f2 <= 1.0f) {
            z = true;
        }
        if (z2 || z) {
            Rect rect3 = rect2;
            if (Intrinsics.areEqual(getInvalidValue(), rect3)) {
                ViewGroup targetViewHost = getTargetViewHost();
                if (targetViewHost != null) {
                    View viewFindViewByIdUseCache = ShareTransitionProperty.Companion.findViewByIdUseCache(targetViewHost, getSharedElementId(), true);
                    if (viewFindViewByIdUseCache != null) {
                        this.getRectHost.set(viewFindViewByIdUseCache.getLeft(), viewFindViewByIdUseCache.getTop(), viewFindViewByIdUseCache.getRight(), viewFindViewByIdUseCache.getBottom());
                    }
                    tryApplyBoundsDiff(targetViewHost, this.getRectHost);
                }
                rect3 = this.getRectHost;
            }
            ShareTransitionProperty.Companion companion = ShareTransitionProperty.Companion;
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "[setProgress]" + companion.simpleNameAndHash(this) + " [" + rect3 + "~" + rect + "], xProgress=" + f + ",yProgress=" + f2, null, 8);
            if (rect3.isEmpty() && rect.isEmpty()) {
                this.targetValueHost.setEmpty();
            } else if (rect3.equals(getDefValue())) {
                Rect rect4 = this.targetValueHost;
                if (z2) {
                    i3 = rect.left;
                    f4 = 0.0f;
                    this.rectLost.setLeft(0.0f);
                } else {
                    i3 = rect4.left;
                    f4 = 0.0f;
                }
                if (z) {
                    i4 = rect.top;
                    this.rectLost.setTop(f4);
                } else {
                    i4 = this.targetValueHost.top;
                }
                if (!z2) {
                    iApply7 = this.targetValueHost.right;
                } else if (0.0f == f) {
                    iApply7 = rect.right;
                    this.rectLost.setRight(f4);
                } else {
                    iApply7 = this.rectLost.apply((rect.width() * f) + rect.left, 3);
                }
                if (!z) {
                    iApply8 = this.targetValueHost.bottom;
                } else if (0.0f != f && 0.0f == f2) {
                    iApply8 = rect.bottom;
                    this.rectLost.setBottom(0.0f);
                } else {
                    iApply8 = this.rectLost.apply((rect.height() * f2) + rect.top, 4);
                }
                rect4.set(i3, i4, iApply7, iApply8);
            } else {
                boolean zAreEqual = Intrinsics.areEqual(rect, getDefValue());
                Rect rect5 = this.targetValueHost;
                if (zAreEqual) {
                    if (z2) {
                        i = rect3.left;
                        f3 = 0.0f;
                        this.rectLost.setLeft(0.0f);
                    } else {
                        i = rect5.left;
                        f3 = 0.0f;
                    }
                    if (z) {
                        i2 = rect3.top;
                        this.rectLost.setTop(f3);
                    } else {
                        i2 = this.targetValueHost.top;
                    }
                    if (!z2) {
                        iApply5 = this.targetValueHost.right;
                    } else if (0.0f == f) {
                        iApply5 = rect3.right;
                        this.rectLost.setRight(f3);
                    } else {
                        iApply5 = this.rectLost.apply(rect3.right - (rect3.width() * f), 3);
                    }
                    if (!z) {
                        iApply6 = this.targetValueHost.bottom;
                    } else if (0.0f != f && 0.0f == f2) {
                        iApply6 = rect3.bottom;
                        this.rectLost.setBottom(0.0f);
                    } else {
                        iApply6 = this.rectLost.apply(rect3.bottom - (rect3.height() * f2), 4);
                    }
                    rect5.set(i, i2, iApply5, iApply6);
                } else {
                    if (z2) {
                        RectLost rectLost = this.rectLost;
                        int i5 = rect3.left;
                        iApply = rectLost.apply(((rect.left - i5) * f) + i5, 1);
                    } else {
                        iApply = rect5.left;
                    }
                    if (z) {
                        RectLost rectLost2 = this.rectLost;
                        int i6 = rect3.top;
                        iApply2 = rectLost2.apply(((rect.top - i6) * f2) + i6, 2);
                    } else {
                        iApply2 = this.targetValueHost.top;
                    }
                    if (z2) {
                        RectLost rectLost3 = this.rectLost;
                        int i7 = rect3.right;
                        iApply3 = rectLost3.apply(((rect.right - i7) * f) + i7, 3);
                    } else {
                        iApply3 = this.targetValueHost.right;
                    }
                    if (z) {
                        RectLost rectLost4 = this.rectLost;
                        int i8 = rect3.bottom;
                        iApply4 = rectLost4.apply(((rect.bottom - i8) * f2) + i8, 4);
                    } else {
                        iApply4 = this.targetValueHost.bottom;
                    }
                    rect5.set(iApply, iApply2, iApply3, iApply4);
                }
            }
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "[setProgress]" + companion.simpleNameAndHash(this) + " " + this.targetValueHost, null, 8);
            ViewGroup targetViewHost2 = getTargetViewHost();
            if (targetViewHost2 != null) {
                this.targetValueTransferredHost.set(this.targetValueHost);
                tryMinusBoundsDiff(targetViewHost2, this.targetValueTransferredHost);
            }
            setValue(this.targetValueTransferredHost);
        }
    }

    @Override // com.oplus.systemui.qs.base.shareanimation.ShareTransitionProperty
    public void setValue(View view, Rect rect) {
        view.setLeftTopRightBottom(rect.left, rect.top, rect.right, rect.bottom);
    }

    public final Rect tryApplyBoundsDiff(ViewGroup viewGroup, Rect rect) {
        Rect rectApplyBoundsDiff;
        if (!this.isNestedBoundShare) {
            View viewFindViewByIdUseCache = ShareTransitionProperty.Companion.findViewByIdUseCache(viewGroup, getSharedElementId(), true);
            Object tag = viewGroup.getTag(getSharedElementId());
            WeakReference weakReference = tag instanceof WeakReference ? (WeakReference) tag : null;
            if ((weakReference != null ? (View) weakReference.get() : null) != null && viewFindViewByIdUseCache != null && (rectApplyBoundsDiff = ViewOverlayManagerKt.applyBoundsDiff(viewFindViewByIdUseCache, rect)) != null) {
                return rectApplyBoundsDiff;
            }
        }
        return rect;
    }

    public final Rect tryMinusBoundsDiff(ViewGroup viewGroup, Rect rect) {
        Rect rectMinusBoundsDiff;
        if (!this.isNestedBoundShare) {
            View viewFindViewByIdUseCache = ShareTransitionProperty.Companion.findViewByIdUseCache(viewGroup, getSharedElementId(), true);
            Object tag = viewGroup.getTag(getSharedElementId());
            WeakReference weakReference = tag instanceof WeakReference ? (WeakReference) tag : null;
            if ((weakReference != null ? (View) weakReference.get() : null) != null && viewFindViewByIdUseCache != null && (rectMinusBoundsDiff = ViewOverlayManagerKt.minusBoundsDiff(viewFindViewByIdUseCache, rect)) != null) {
                return rectMinusBoundsDiff;
            }
        }
        return rect;
    }
}
