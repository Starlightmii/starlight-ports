package com.oplus.systemui.qs.base.res.drawable;

import android.util.StateSet;
import com.oplus.systemui.qs.base.res.model.DrawableState;
import com.oplus.systemui.qs.base.res.model.DrawableStateKt;
import com.oplus.systemui.qs.base.res.model.TileViewFlag;
import com.oplus.systemui.qs.base.res.util.QSConstant;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class GradientTileDrawable$TileTypeConfig$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ GradientTileDrawable.TileTypeConfig f$0;

    public final Object invoke() {
        int i = this.$r8$classId;
        GradientTileDrawable.TileTypeConfig tileTypeConfig = this.f$0;
        switch (i) {
            case 0:
                return new GradientTileDrawable.Builder(MapsKt__MapsKt.linkedMapOf(new Pair[]{TuplesKt.to(DrawableStateKt.toState(StateSet.WILD_CARD), Integer.valueOf(tileTypeConfig.inactiveColor))}));
            case 1:
                return new GradientTileDrawable.Builder(MapsKt__MapsKt.linkedMapOf(new Pair[]{TuplesKt.to(DrawableStateKt.toState(TileViewFlag.Active.getFlag()), Integer.valueOf(tileTypeConfig.activeColor)), TuplesKt.to(DrawableStateKt.toState(TileViewFlag.Inoperable.getFlag()), Integer.valueOf(tileTypeConfig.inoperableColor)), TuplesKt.to(DrawableStateKt.toState(TileViewFlag.Unavailable.getFlag()), Integer.valueOf(tileTypeConfig.unavailableColor)), TuplesKt.to(DrawableStateKt.toState(StateSet.WILD_CARD), Integer.valueOf(tileTypeConfig.inactiveColor))}));
            case 2:
                TileViewFlag tileViewFlag = TileViewFlag.Active;
                int[] flag = tileViewFlag.getFlag();
                TileViewFlag tileViewFlag2 = TileViewFlag.Dragged;
                Pair pair = TuplesKt.to(DrawableStateKt.toState(ArraysKt___ArraysJvmKt.plus(flag, tileViewFlag2.getFlag())), Integer.valueOf(tileTypeConfig.activeDraggedColor));
                TileViewFlag tileViewFlag3 = TileViewFlag.Inoperable;
                Pair pair2 = TuplesKt.to(DrawableStateKt.toState(ArraysKt___ArraysJvmKt.plus(tileViewFlag3.getFlag(), tileViewFlag2.getFlag())), Integer.valueOf(tileTypeConfig.inoperableDraggedColor));
                TileViewFlag tileViewFlag4 = TileViewFlag.Unavailable;
                Pair pair3 = TuplesKt.to(DrawableStateKt.toState(ArraysKt___ArraysJvmKt.plus(tileViewFlag4.getFlag(), tileViewFlag2.getFlag())), Integer.valueOf(tileTypeConfig.unavailableDraggedColor));
                Pair pair4 = TuplesKt.to(DrawableStateKt.toState(ArraysKt___ArraysJvmKt.plus(TileViewFlag.Inactive.getFlag(), tileViewFlag2.getFlag())), Integer.valueOf(tileTypeConfig.inactiveDraggedColor));
                DrawableState state = DrawableStateKt.toState(tileViewFlag.getFlag());
                int i2 = tileTypeConfig.activeColor;
                Pair pair5 = TuplesKt.to(state, Integer.valueOf(i2));
                Pair pair6 = TuplesKt.to(DrawableStateKt.toState(tileViewFlag3.getFlag()), Integer.valueOf(tileTypeConfig.inoperableColor));
                Pair pair7 = TuplesKt.to(DrawableStateKt.toState(tileViewFlag4.getFlag()), Integer.valueOf(tileTypeConfig.unavailableColor));
                DrawableState deformInactiveState = QSConstant.getDeformInactiveState();
                int i3 = tileTypeConfig.inactiveColor;
                return new GradientTileDrawable.Builder(MapsKt__MapsKt.linkedMapOf(new Pair[]{pair, pair2, pair3, pair4, pair5, pair6, pair7, TuplesKt.to(deformInactiveState, Integer.valueOf(i3)), TuplesKt.to(QSConstant.getDeformActiveState(), Integer.valueOf(i2)), TuplesKt.to(DrawableStateKt.toState(StateSet.WILD_CARD), Integer.valueOf(i3))}));
            case 3:
                int[] flag2 = TileViewFlag.Active.getFlag();
                TileViewFlag tileViewFlag5 = TileViewFlag.Dragged;
                DrawableState state2 = DrawableStateKt.toState(ArraysKt___ArraysJvmKt.plus(flag2, tileViewFlag5.getFlag()));
                int i4 = tileTypeConfig.inactiveDraggedColor;
                Pair pair8 = TuplesKt.to(state2, Integer.valueOf(i4));
                TileViewFlag tileViewFlag6 = TileViewFlag.Unavailable;
                Pair pair9 = TuplesKt.to(DrawableStateKt.toState(ArraysKt___ArraysJvmKt.plus(tileViewFlag6.getFlag(), tileViewFlag5.getFlag())), Integer.valueOf(tileTypeConfig.unavailableDraggedColor));
                Pair pair10 = TuplesKt.to(DrawableStateKt.toState(ArraysKt___ArraysJvmKt.plus(TileViewFlag.Inactive.getFlag(), tileViewFlag5.getFlag())), Integer.valueOf(i4));
                Pair pair11 = TuplesKt.to(DrawableStateKt.toState(tileViewFlag6.getFlag()), Integer.valueOf(tileTypeConfig.unavailableColor));
                DrawableState deformInactiveState2 = QSConstant.getDeformInactiveState();
                int i5 = tileTypeConfig.inactiveColor;
                return new GradientTileDrawable.Builder(MapsKt__MapsKt.linkedMapOf(new Pair[]{pair8, pair9, pair10, pair11, TuplesKt.to(deformInactiveState2, Integer.valueOf(i5)), TuplesKt.to(QSConstant.getDeformActiveState(), Integer.valueOf(tileTypeConfig.activeColor)), TuplesKt.to(DrawableStateKt.toState(StateSet.WILD_CARD), Integer.valueOf(i5))}));
            case 4:
                TileViewFlag tileViewFlag7 = TileViewFlag.Toggleable;
                return new GradientTileDrawable.Builder(MapsKt__MapsKt.linkedMapOf(new Pair[]{TuplesKt.to(DrawableStateKt.toState(ArraysKt___ArraysJvmKt.plus(tileViewFlag7.getFlag(), TileViewFlag.Active.getFlag())), Integer.valueOf(tileTypeConfig.activeColor)), TuplesKt.to(DrawableStateKt.toState(ArraysKt___ArraysJvmKt.plus(tileViewFlag7.getFlag(), TileViewFlag.Inoperable.getFlag())), Integer.valueOf(tileTypeConfig.inoperableColor)), TuplesKt.to(DrawableStateKt.toState(ArraysKt___ArraysJvmKt.plus(tileViewFlag7.getFlag(), TileViewFlag.Unavailable.getFlag())), Integer.valueOf(tileTypeConfig.unavailableColor)), TuplesKt.to(DrawableStateKt.toState(ArraysKt___ArraysJvmKt.plus(tileViewFlag7.getFlag(), TileViewFlag.Inactive.getFlag())), Integer.valueOf(tileTypeConfig.inactiveColor)), TuplesKt.to(DrawableStateKt.toState(StateSet.WILD_CARD), 0)}));
            default:
                return new GradientTileDrawable.Builder(MapsKt__MapsKt.linkedMapOf(new Pair[]{TuplesKt.to(DrawableStateKt.toState(TileViewFlag.Dragged.getFlag()), Integer.valueOf(tileTypeConfig.activeDraggedColor)), TuplesKt.to(DrawableStateKt.toState(StateSet.WILD_CARD), Integer.valueOf(tileTypeConfig.activeColor))}));
        }
    }
}
