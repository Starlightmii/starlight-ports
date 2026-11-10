package com.oplus.systemui.plugins.qs.customize.viewmodel;

import android.content.ComponentName;
import androidx.appcompat.app.AppCompatDelegateImpl$;
import androidx.car.app.SurfaceContainer$;
import androidx.car.app.hardware.common.CarZone$;
import androidx.car.app.model.constraints.ActionsConstraints$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.constraintlayout.widget.ConstraintSet$;
import com.oplus.systemui.plugins.qs.customize.component.proxy.DragCallbackProxy$;
import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.viewholder.GridData;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class TileEditIntent {

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AddOneTileIntent extends TileEditIntent {
        private final GridData gridTileInfo;
        private final Loc loc;
        public final int position;

        public AddOneTileIntent(GridData gridData, Loc loc, int i) {
            this.gridTileInfo = gridData;
            this.loc = loc;
            this.position = i;
        }

        public final GridData component1() {
            return this.gridTileInfo;
        }

        public final Loc component2() {
            return this.loc;
        }

        public final AddOneTileIntent copy(GridData gridData, Loc loc, int i) {
            return new AddOneTileIntent(gridData, loc, i);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AddOneTileIntent)) {
                return false;
            }
            AddOneTileIntent addOneTileIntent = (AddOneTileIntent) obj;
            return Intrinsics.areEqual(this.gridTileInfo, addOneTileIntent.gridTileInfo) && Intrinsics.areEqual(this.loc, addOneTileIntent.loc) && this.position == addOneTileIntent.position;
        }

        public final GridData getGridTileInfo() {
            return this.gridTileInfo;
        }

        public final Loc getLoc() {
            return this.loc;
        }

        public final int getPosition() {
            return this.position;
        }

        public final int hashCode() {
            int iHashCode = this.gridTileInfo.hashCode() * 31;
            Loc loc = this.loc;
            return Integer.hashCode(this.position) + ((iHashCode + (loc == null ? 0 : loc.hashCode())) * 31);
        }

        public String toString() {
            GridData gridData = this.gridTileInfo;
            Loc loc = this.loc;
            StringBuilder sb = new StringBuilder("AddOneTileIntent(gridTileInfo=");
            sb.append(gridData);
            sb.append(", loc=");
            sb.append(loc);
            sb.append(", position=");
            return SurfaceContainer$.ExternalSyntheticOutline0.m(this.position, ")", sb);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CustomTileClick extends TileEditIntent {
        private final ComponentName tile;

        public CustomTileClick(ComponentName componentName) {
            this.tile = componentName;
        }

        public final ComponentName component1() {
            return this.tile;
        }

        public final CustomTileClick copy(ComponentName componentName) {
            return new CustomTileClick(componentName);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof CustomTileClick) && Intrinsics.areEqual(this.tile, ((CustomTileClick) obj).tile);
        }

        public final ComponentName getTile() {
            return this.tile;
        }

        public final int hashCode() {
            return this.tile.hashCode();
        }

        public String toString() {
            return "CustomTileClick(tile=" + this.tile + ")";
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class DeformTileIntent extends TileEditIntent {
        private final SpanSize endSize;
        public final int itemDragging;

        public DeformTileIntent(int i, SpanSize spanSize) {
            this.itemDragging = i;
            this.endSize = spanSize;
        }

        public final SpanSize component2() {
            return this.endSize;
        }

        public final DeformTileIntent copy(int i, SpanSize spanSize) {
            return new DeformTileIntent(i, spanSize);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DeformTileIntent)) {
                return false;
            }
            DeformTileIntent deformTileIntent = (DeformTileIntent) obj;
            return this.itemDragging == deformTileIntent.itemDragging && Intrinsics.areEqual(this.endSize, deformTileIntent.endSize);
        }

        public final SpanSize getEndSize() {
            return this.endSize;
        }

        public final int getItemDragging() {
            return this.itemDragging;
        }

        public final int hashCode() {
            return this.endSize.hashCode() + (Integer.hashCode(this.itemDragging) * 31);
        }

        public String toString() {
            return "DeformTileIntent(itemDragging=" + this.itemDragging + ", endSize=" + this.endSize + ")";
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class DragEndIntent extends TileEditIntent {
        public static final DragEndIntent INSTANCE = new DragEndIntent();

        public boolean equals(Object obj) {
            return this == obj || (obj instanceof DragEndIntent);
        }

        public final int hashCode() {
            return -1345410013;
        }

        public String toString() {
            return "DragEndIntent";
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class DragStartIntent extends TileEditIntent {
        public final int itemDragging;

        public DragStartIntent(int i) {
            this.itemDragging = i;
        }

        public final DragStartIntent copy(int i) {
            return new DragStartIntent(i);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof DragStartIntent) && this.itemDragging == ((DragStartIntent) obj).itemDragging;
        }

        public final int getItemDragging() {
            return this.itemDragging;
        }

        public final int hashCode() {
            return Integer.hashCode(this.itemDragging);
        }

        public String toString() {
            return ActionsConstraints$.ExternalSyntheticOutline0.m(this.itemDragging, "DragStartIntent(itemDragging=", ")");
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class DragTileIntent extends TileEditIntent {
        public final int draggingIndex;
        private final Loc endLoc;
        public final int endPosition;

        public DragTileIntent(int i, Loc loc, int i2) {
            this.draggingIndex = i;
            this.endLoc = loc;
            this.endPosition = i2;
        }

        public final Loc component2() {
            return this.endLoc;
        }

        public final DragTileIntent copy(int i, Loc loc, int i2) {
            return new DragTileIntent(i, loc, i2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DragTileIntent)) {
                return false;
            }
            DragTileIntent dragTileIntent = (DragTileIntent) obj;
            return this.draggingIndex == dragTileIntent.draggingIndex && Intrinsics.areEqual(this.endLoc, dragTileIntent.endLoc) && this.endPosition == dragTileIntent.endPosition;
        }

        public final int getDraggingIndex() {
            return this.draggingIndex;
        }

        public final Loc getEndLoc() {
            return this.endLoc;
        }

        public final int getEndPosition() {
            return this.endPosition;
        }

        public final int hashCode() {
            int iHashCode = Integer.hashCode(this.draggingIndex) * 31;
            Loc loc = this.endLoc;
            return Integer.hashCode(this.endPosition) + ((iHashCode + (loc == null ? 0 : loc.hashCode())) * 31);
        }

        public String toString() {
            Loc loc = this.endLoc;
            StringBuilder sb = new StringBuilder("DragTileIntent(draggingIndex=");
            sb.append(this.draggingIndex);
            sb.append(", endLoc=");
            sb.append(loc);
            sb.append(", endPosition=");
            return SurfaceContainer$.ExternalSyntheticOutline0.m(this.endPosition, ")", sb);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class EnterEditIntent extends TileEditIntent {
        public static final EnterEditIntent INSTANCE = new EnterEditIntent();

        public boolean equals(Object obj) {
            return this == obj || (obj instanceof EnterEditIntent);
        }

        public final int hashCode() {
            return 1789587934;
        }

        public String toString() {
            return "EnterEditIntent";
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ExitEditIntent extends TileEditIntent {
        public static final ExitEditIntent INSTANCE = new ExitEditIntent();

        public boolean equals(Object obj) {
            return this == obj || (obj instanceof ExitEditIntent);
        }

        public final int hashCode() {
            return 1880447332;
        }

        public String toString() {
            return "ExitEditIntent";
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class RemoveOneTileIntent extends TileEditIntent {
        private final GridData gridTileInfo;

        public RemoveOneTileIntent(GridData gridData) {
            this.gridTileInfo = gridData;
        }

        public final GridData component1() {
            return this.gridTileInfo;
        }

        public final RemoveOneTileIntent copy(GridData gridData) {
            return new RemoveOneTileIntent(gridData);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof RemoveOneTileIntent) && Intrinsics.areEqual(this.gridTileInfo, ((RemoveOneTileIntent) obj).gridTileInfo);
        }

        public final GridData getGridTileInfo() {
            return this.gridTileInfo;
        }

        public final int hashCode() {
            return this.gridTileInfo.hashCode();
        }

        public String toString() {
            return "RemoveOneTileIntent(gridTileInfo=" + this.gridTileInfo + ")";
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class SwapTile extends TileEditIntent {
        private final String specOfOthersArea;
        private final String specOfPersonalArea;

        public SwapTile(String str, String str2) {
            this.specOfPersonalArea = str;
            this.specOfOthersArea = str2;
        }

        public final String component1() {
            return this.specOfPersonalArea;
        }

        public final String component2() {
            return this.specOfOthersArea;
        }

        public final SwapTile copy(String str, String str2) {
            return new SwapTile(str, str2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SwapTile)) {
                return false;
            }
            SwapTile swapTile = (SwapTile) obj;
            return Intrinsics.areEqual(this.specOfPersonalArea, swapTile.specOfPersonalArea) && Intrinsics.areEqual(this.specOfOthersArea, swapTile.specOfOthersArea);
        }

        public final String getSpecOfOthersArea() {
            return this.specOfOthersArea;
        }

        public final String getSpecOfPersonalArea() {
            return this.specOfPersonalArea;
        }

        public final int hashCode() {
            return this.specOfOthersArea.hashCode() + (this.specOfPersonalArea.hashCode() * 31);
        }

        public String toString() {
            return CarZone$.ExternalSyntheticOutline0.m("SwapTile(specOfPersonalArea=", this.specOfPersonalArea, ", specOfOthersArea=", this.specOfOthersArea, ")");
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class MoveTile extends TileEditIntent {
        private final Loc dstLoc;
        public final int dstPosition;
        public boolean isFirstMoveTile;
        private Function1 moveTileCallback;
        private final String spec;

        public /* synthetic */ MoveTile(String str, int i, Loc loc, DragCallbackProxy$.ExternalSyntheticLambda5 externalSyntheticLambda5, boolean z, int i2) {
            this(str, (i2 & 2) != 0 ? -1 : i, (i2 & 4) != 0 ? null : loc, (i2 & 8) != 0 ? null : externalSyntheticLambda5, (i2 & 16) != 0 ? true : z);
        }

        public final String component1() {
            return this.spec;
        }

        public final Loc component3() {
            return this.dstLoc;
        }

        public final Function1 component4() {
            return this.moveTileCallback;
        }

        public final MoveTile copy(String str, int i, Loc loc, Function1 function1, boolean z) {
            return new MoveTile(str, i, loc, function1, z);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MoveTile)) {
                return false;
            }
            MoveTile moveTile = (MoveTile) obj;
            return Intrinsics.areEqual(this.spec, moveTile.spec) && this.dstPosition == moveTile.dstPosition && Intrinsics.areEqual(this.dstLoc, moveTile.dstLoc) && Intrinsics.areEqual(this.moveTileCallback, moveTile.moveTileCallback) && this.isFirstMoveTile == moveTile.isFirstMoveTile;
        }

        public final Loc getDstLoc() {
            return this.dstLoc;
        }

        public final int getDstPosition() {
            return this.dstPosition;
        }

        public final Function1 getMoveTileCallback() {
            return this.moveTileCallback;
        }

        public final String getSpec() {
            return this.spec;
        }

        public final int hashCode() {
            int iM = MonoSpline$.ExternalSyntheticOutline0.m(this.dstPosition, this.spec.hashCode() * 31, 31);
            Loc loc = this.dstLoc;
            int iHashCode = (iM + (loc == null ? 0 : loc.hashCode())) * 31;
            Function1 function1 = this.moveTileCallback;
            return Boolean.hashCode(this.isFirstMoveTile) + ((iHashCode + (function1 != null ? function1.hashCode() : 0)) * 31);
        }

        public final void setFirstMoveTile(boolean z) {
            this.isFirstMoveTile = z;
        }

        public final void setMoveTileCallback(Function1 function1) {
            this.moveTileCallback = function1;
        }

        public String toString() {
            String str = this.spec;
            Loc loc = this.dstLoc;
            Function1 function1 = this.moveTileCallback;
            boolean z = this.isFirstMoveTile;
            StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m(this.dstPosition, "MoveTile(spec=", str, ", dstPosition=", ", dstLoc=");
            sbM.append(loc);
            sbM.append(", moveTileCallback=");
            sbM.append(function1);
            sbM.append(", isFirstMoveTile=");
            return AppCompatDelegateImpl$.ExternalSyntheticOutline0.m(")", sbM, z);
        }

        public MoveTile(String str, int i, Loc loc, Function1 function1, boolean z) {
            this.spec = str;
            this.dstPosition = i;
            this.dstLoc = loc;
            this.moveTileCallback = function1;
            this.isFirstMoveTile = z;
        }
    }
}
