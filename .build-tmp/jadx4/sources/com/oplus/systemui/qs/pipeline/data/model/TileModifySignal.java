package com.oplus.systemui.qs.pipeline.data.model;

import com.android.systemui.qs.pipeline.shared.TileSpec;
import java.util.Collection;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface TileModifySignal {

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Add implements TileModifySignal {
        public final int position;
        private final TileSpec spec;

        public Add(TileSpec tileSpec, int i) {
            this.spec = tileSpec;
            this.position = i;
        }

        public final TileSpec component1() {
            return this.spec;
        }

        public final Add copy(TileSpec tileSpec, int i) {
            return new Add(tileSpec, i);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Add)) {
                return false;
            }
            Add add = (Add) obj;
            return Intrinsics.areEqual(this.spec, add.spec) && this.position == add.position;
        }

        public final int getPosition() {
            return this.position;
        }

        public final TileSpec getSpec() {
            return this.spec;
        }

        public final int hashCode() {
            return Integer.hashCode(this.position) + (this.spec.hashCode() * 31);
        }

        public String toString() {
            return "Add(spec=" + this.spec + ", position=" + this.position + ")";
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Remove implements TileModifySignal {
        private final Collection<TileSpec> specs;

        public Remove(Collection<? extends TileSpec> collection) {
            this.specs = collection;
        }

        public final Collection<TileSpec> component1() {
            return this.specs;
        }

        public final Remove copy(Collection<? extends TileSpec> collection) {
            return new Remove(collection);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Remove) && Intrinsics.areEqual(this.specs, ((Remove) obj).specs);
        }

        public final Collection<TileSpec> getSpecs() {
            return this.specs;
        }

        public final int hashCode() {
            return this.specs.hashCode();
        }

        public String toString() {
            return "Remove(specs=" + this.specs + ")";
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Reset implements TileModifySignal {
        public static final Reset INSTANCE = new Reset();

        public boolean equals(Object obj) {
            return this == obj || (obj instanceof Reset);
        }

        public final int hashCode() {
            return -1327582335;
        }

        public String toString() {
            return "Reset";
        }
    }
}
