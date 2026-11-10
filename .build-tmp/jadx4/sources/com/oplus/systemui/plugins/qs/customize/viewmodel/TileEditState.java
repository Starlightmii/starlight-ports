package com.oplus.systemui.plugins.qs.customize.viewmodel;

import androidx.collection.FloatList$;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import androidx.compose.ui.spatial.RectList$;
import com.android.settingslib.media.DynamicRouteAttributes$;
import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.viewholder.GridData;
import com.oplusos.systemui.common.viewmodel.IUiState;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class TileEditState implements IUiState {

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Loading extends TileEditState {
        public static final Loading INSTANCE = new Loading();

        public boolean equals(Object obj) {
            return this == obj || (obj instanceof Loading);
        }

        public final int hashCode() {
            return 1966033261;
        }

        public String toString() {
            return "Loading";
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Normal extends TileEditState {
        private final List<GridData> allPersonalTiles;
        private final List<Loc> allPersonalTilesLoc;
        public final boolean isEditMode;
        private final List<GridData> otherAddedTiles;
        public final boolean tileNameEnabled;

        public Normal(List<GridData> list, List<Loc> list2, List<GridData> list3, boolean z, boolean z2) {
            this.allPersonalTiles = list;
            this.allPersonalTilesLoc = list2;
            this.otherAddedTiles = list3;
            this.tileNameEnabled = z;
            this.isEditMode = z2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Normal copy$default(Normal normal, List list, List list2, List list3, boolean z, boolean z2, int i) {
            if ((i & 1) != 0) {
                list = normal.allPersonalTiles;
            }
            List list4 = list;
            if ((i & 2) != 0) {
                list2 = normal.allPersonalTilesLoc;
            }
            List list5 = list2;
            if ((i & 4) != 0) {
                list3 = normal.otherAddedTiles;
            }
            List list6 = list3;
            if ((i & 8) != 0) {
                z = normal.tileNameEnabled;
            }
            boolean z3 = z;
            if ((i & 16) != 0) {
                z2 = normal.isEditMode;
            }
            return normal.copy(list4, list5, list6, z3, z2);
        }

        public final List<GridData> component1() {
            return this.allPersonalTiles;
        }

        public final List<Loc> component2() {
            return this.allPersonalTilesLoc;
        }

        public final List<GridData> component3() {
            return this.otherAddedTiles;
        }

        public final Normal copy(List<GridData> list, List<Loc> list2, List<GridData> list3, boolean z, boolean z2) {
            return new Normal(list, list2, list3, z, z2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Normal)) {
                return false;
            }
            Normal normal = (Normal) obj;
            return Intrinsics.areEqual(this.allPersonalTiles, normal.allPersonalTiles) && Intrinsics.areEqual(this.allPersonalTilesLoc, normal.allPersonalTilesLoc) && Intrinsics.areEqual(this.otherAddedTiles, normal.otherAddedTiles) && this.tileNameEnabled == normal.tileNameEnabled && this.isEditMode == normal.isEditMode;
        }

        public final List<GridData> getAllPersonalTiles() {
            return this.allPersonalTiles;
        }

        public final List<Loc> getAllPersonalTilesLoc() {
            return this.allPersonalTilesLoc;
        }

        public final List<GridData> getOtherAddedTiles() {
            return this.otherAddedTiles;
        }

        public final boolean getTileNameEnabled() {
            return this.tileNameEnabled;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.isEditMode) + ChangeSize$.ExternalSyntheticOutline0.m(PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(this.allPersonalTiles.hashCode() * 31, 31, this.allPersonalTilesLoc), 31, this.otherAddedTiles), 31, this.tileNameEnabled);
        }

        public String toString() {
            int iHashCode = hashCode();
            int size = this.allPersonalTiles.size();
            int size2 = this.allPersonalTilesLoc.size();
            int size3 = this.otherAddedTiles.size();
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m(" ", iHashCode, size, "@Normal(allPersonalTiles=", ", allPersonalTilesLoc=");
            RectList$.ExternalSyntheticOutline0.m(size2, size3, ", otherAddedTiles=", ", isEditMode=", sbM);
            return DynamicRouteAttributes$.ExternalSyntheticOutline0.m(",tileNameEnabled=", ")", sbM, this.isEditMode, this.tileNameEnabled);
        }
    }
}
