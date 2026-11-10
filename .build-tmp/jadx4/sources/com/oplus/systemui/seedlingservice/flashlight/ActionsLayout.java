package com.oplus.systemui.seedlingservice.flashlight;

import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ActionsLayout {
    private final List<LabelButton> btns;
    private final String type;

    public /* synthetic */ ActionsLayout(String str, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "t_actions" : str, list);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ActionsLayout copy$default(ActionsLayout actionsLayout, String str, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = actionsLayout.type;
        }
        if ((i & 2) != 0) {
            list = actionsLayout.btns;
        }
        return actionsLayout.copy(str, list);
    }

    public final String component1() {
        return this.type;
    }

    public final List<LabelButton> component2() {
        return this.btns;
    }

    public final ActionsLayout copy(String str, List<LabelButton> list) {
        return new ActionsLayout(str, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ActionsLayout)) {
            return false;
        }
        ActionsLayout actionsLayout = (ActionsLayout) obj;
        return Intrinsics.areEqual(this.type, actionsLayout.type) && Intrinsics.areEqual(this.btns, actionsLayout.btns);
    }

    public final List<LabelButton> getBtns() {
        return this.btns;
    }

    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        return this.btns.hashCode() + (this.type.hashCode() * 31);
    }

    public String toString() {
        return "ActionsLayout(type=" + this.type + ", btns=" + this.btns + ")";
    }

    public ActionsLayout(String str, List<LabelButton> list) {
        this.type = str;
        this.btns = list;
    }
}
