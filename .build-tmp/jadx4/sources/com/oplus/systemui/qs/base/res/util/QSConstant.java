package com.oplus.systemui.qs.base.res.util;

import android.content.Context;
import android.view.animation.PathInterpolator;
import com.oplus.systemui.qs.base.res.model.DrawableState;
import com.oplus.systemui.qs.base.res.model.DrawableStateKt;
import com.oplus.systemui.qs.base.res.model.TileViewFlag;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.outline.RoundRectOutlineProvider;
import com.oplusos.systemui.common.util.OplusQsSmoothRoundUtil;
import com.oplusos.systemui.common.util.OplusRoundParams;
import java.util.List;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSConstant {
    public static final String ACTIVE_SUFFIX = "_active";
    public static final String DND_LOTTIE_PREFIX = "qs_dnd_lottie";
    public static final String INACTIVE_SUFFIX = "_inactive";
    private static final DrawableState deformActiveState;
    private static final DrawableState deformInactiveState;
    public static final QSConstant INSTANCE = new QSConstant();
    private static final PathInterpolator BG_TRANSITION_INTERPOLATOR = new PathInterpolator(0.33f, 0.0f, 0.67f, 1.0f);
    public static boolean qsTileAnimationAllowed = true;
    public static final List<SpanSize> tileType = CollectionsKt__CollectionsJVMKt.listOf(SpanCache.ONE_X_ONE);
    public static final List<SpanSize> highlightTileType = CollectionsKt__CollectionsJVMKt.listOf(SpanCache.TWO_X_ONE);
    public static final List<SpanSize> mediaPanelType = CollectionsKt__CollectionsJVMKt.listOf(SpanCache.TWO_X_TWO);

    static {
        TileViewFlag tileViewFlag = TileViewFlag.Deform;
        deformInactiveState = DrawableStateKt.toState(ArraysKt___ArraysJvmKt.plus(tileViewFlag.getFlag(), TileViewFlag.Inactive.getFlag()));
        deformActiveState = DrawableStateKt.toState(ArraysKt___ArraysJvmKt.plus(tileViewFlag.getFlag(), TileViewFlag.Active.getFlag()));
    }

    public static final TileViewFlag convertTileStateToFlag(int i) {
        if (i == 0) {
            return TileViewFlag.Unavailable;
        }
        if (i != 2) {
            return i != 101 ? TileViewFlag.Inactive : TileViewFlag.Inoperable;
        }
        return TileViewFlag.Active;
    }

    public static final DrawableState getDeformActiveState() {
        return deformActiveState;
    }

    public static /* synthetic */ void getDeformActiveState$annotations() {
    }

    public static final DrawableState getDeformInactiveState() {
        return deformInactiveState;
    }

    public static /* synthetic */ void getDeformInactiveState$annotations() {
    }

    public static final RoundRectOutlineProvider getFullCornerProvider(float f) {
        OplusRoundParams fullCornerParams = OplusQsSmoothRoundUtil.getFullCornerParams(f);
        return new RoundRectOutlineProvider(fullCornerParams.getRadius(), Float.valueOf(fullCornerParams.getWeight()));
    }

    public static final boolean getQsTileAnimationAllowed() {
        return qsTileAnimationAllowed;
    }

    public static /* synthetic */ void getQsTileAnimationAllowed$annotations() {
    }

    public static final RoundRectOutlineProvider getSmoothRoundRectOutlineProvider(Context context, float f) {
        OplusRoundParams roundParams = OplusQsSmoothRoundUtil.getRoundParams(context, f);
        return roundParams != null ? new RoundRectOutlineProvider(roundParams.getRadius(), Float.valueOf(roundParams.getWeight())) : new RoundRectOutlineProvider(f, (Float) null);
    }

    public static final int setColorAlpha(int i) {
        return (i & 16777215) | (Math.round(76.5f) << 24);
    }

    public static final void setQsTileAnimationAllowed(boolean z) {
        qsTileAnimationAllowed = z;
    }

    public final PathInterpolator getBG_TRANSITION_INTERPOLATOR() {
        return BG_TRANSITION_INTERPOLATOR;
    }
}
