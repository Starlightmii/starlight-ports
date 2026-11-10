package com.oplus.systemui.plugins.qs.customize.view.animation.deform.tile.fixed;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.dynamicanimation.animation.FloatValueHolder;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import com.oplus.systemui.plugins.qs.customize.view.deformation.DeformationFrameLayout;
import com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView;
import com.oplus.systemui.qs.base.shareanimation.ShareTransitionProperty;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.olivelink.OliveLinkConnector;
import com.oplusos.systemui.common.olivelink.Spring;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.ref.WeakReference;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class TileFixedShareTransitionProperty extends ShareTransitionProperty {
    public static final Companion Companion = new Companion();
    private static final SpanSize SIZE_1X1;
    private static final SpanSize SIZE_2X1;
    private static final String TAG = "TileFixedShareTransitionProperty";
    private SpanSize curSpanSize;
    public Object endValue;
    public boolean isCreateSpringAnimation;
    public boolean isFirstInit;
    public Object startValue;
    private final Map<SpanSize, OplusQSResizeableTileView> tilesViewMap;

    static {
        SpanCache spanCache = SpanCache.INSTANCE;
        SIZE_1X1 = spanCache.spanSize(1, 1);
        SIZE_2X1 = spanCache.spanSize(2, 1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TileFixedShareTransitionProperty(ViewGroup viewGroup, int i, Object obj, Object obj2) {
        SpanSize tileViewSize;
        super(viewGroup, i, obj, obj2);
        OplusQSResizeableTileView oplusQSResizeableTileView = viewGroup instanceof OplusQSResizeableTileView ? (OplusQSResizeableTileView) viewGroup : null;
        this.curSpanSize = (oplusQSResizeableTileView == null || (tileViewSize = oplusQSResizeableTileView.getTileViewSize()) == null) ? SIZE_1X1 : tileViewSize;
        this.tilesViewMap = new LinkedHashMap();
        this.startValue = obj;
        this.endValue = obj;
        this.isFirstInit = true;
    }

    @Override // com.oplus.systemui.qs.base.shareanimation.ShareTransitionProperty
    public void captureValues(List<? extends ViewGroup> list) {
        super.captureValues(list);
        for (Object obj : list) {
            if (obj instanceof OplusQSResizeableTileView) {
                this.tilesViewMap.put(((OplusQSResizeableTileView) obj).getTileViewSize(), (OplusQSResizeableTileView) obj);
            }
        }
    }

    public abstract TileFixedShareTransitionProperty createShareTransitionCopy(ViewGroup viewGroup);

    public final COUISpringAnimation createSpringAnimation(String str, float f, float f2, float f3, String str2) {
        Spring springSafeSpring$default = OliveLinkConnector.safeSpring$default(OliveLinkConnector.INSTANCE, str2, str, f2, f3, 0.0f, 0.0f, 0.0f, null, null, null, 1008);
        this.isCreateSpringAnimation = true;
        COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(new FloatValueHolder());
        COUISpringForce cOUISpringForce = new COUISpringForce();
        cOUISpringForce.setBounce(springSafeSpring$default.bounce());
        cOUISpringForce.setResponse(springSafeSpring$default.response());
        cOUISpringAnimation.setSpring(cOUISpringForce);
        cOUISpringAnimation.setMinimumVisibleChange(0.002f);
        cOUISpringAnimation.setStartValue(f);
        cOUISpringAnimation.setStartVelocity(springSafeSpring$default.velocity());
        return cOUISpringAnimation;
    }

    public final SpanSize getCurSpanSize() {
        return this.curSpanSize;
    }

    public final Object getEndValue() {
        return this.endValue;
    }

    public final Object getStartValue() {
        return this.startValue;
    }

    public final OplusQSResizeableTileView getTileViewHost(SpanSize spanSize) {
        return this.tilesViewMap.get(spanSize);
    }

    public void initPreView() {
    }

    @Override // com.oplus.systemui.qs.base.shareanimation.ShareTransitionProperty
    public void initValue(ViewGroup viewGroup, View view, boolean z) {
        if (this.isFirstInit) {
            this.isFirstInit = false;
            initPreView();
        }
    }

    @Override // com.oplus.systemui.qs.base.shareanimation.ShareTransitionProperty
    public final ShareTransitionProperty moveTo(ViewGroup viewGroup) {
        if (Intrinsics.areEqual(viewGroup, getTargetViewHost())) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "TileFixedShareTransitionProperty-->[moveTo] newTargetViewHost == getTargetViewHost()");
            }
            return this;
        }
        TileFixedShareTransitionProperty tileFixedShareTransitionPropertyCreateShareTransitionCopy = createShareTransitionCopy(viewGroup);
        for (Map.Entry<WeakReference<ViewGroup>, Object> entry : getValuesOfDiffState().entrySet()) {
            WeakReference<ViewGroup> key = entry.getKey();
            Object value = entry.getValue();
            ViewGroup viewGroup2 = key.get();
            if (Intrinsics.areEqual(viewGroup2 != null ? DeformationFrameLayout.Companion.getSize(viewGroup2) : null, DeformationFrameLayout.Companion.getSize(viewGroup))) {
                tileFixedShareTransitionPropertyCreateShareTransitionCopy.getValuesOfDiffState().put(new WeakReference<>(viewGroup), value);
            } else {
                tileFixedShareTransitionPropertyCreateShareTransitionCopy.getValuesOfDiffState().put(new WeakReference<>(viewGroup2), value);
            }
        }
        tileFixedShareTransitionPropertyCreateShareTransitionCopy.isFirstInit = this.isFirstInit;
        tileFixedShareTransitionPropertyCreateShareTransitionCopy.ensureSharedElementAndInit(getExpectedFromHost());
        Object savedValueWhenEnd = getSavedValueWhenEnd();
        if (savedValueWhenEnd != null) {
            tileFixedShareTransitionPropertyCreateShareTransitionCopy.setValue(savedValueWhenEnd);
        }
        tileFixedShareTransitionPropertyCreateShareTransitionCopy.curSpanSize = this.curSpanSize;
        tileFixedShareTransitionPropertyCreateShareTransitionCopy.tilesViewMap.putAll(this.tilesViewMap);
        return tileFixedShareTransitionPropertyCreateShareTransitionCopy;
    }

    public final void setCreateSpringAnimation(boolean z) {
        this.isCreateSpringAnimation = z;
    }

    public final void setCurSpanSize(SpanSize spanSize) {
        this.curSpanSize = spanSize;
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
        startTransition(f, f2, obj2, obj);
    }

    public final void setStartValue(Object obj) {
        this.startValue = obj;
    }

    public abstract void startTransition(float f, float f2, Object obj, Object obj2);
}
