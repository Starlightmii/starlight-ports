package com.oplus.systemui.seedlingservice.flashlight;

import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SymActionLayout {
    private final LabelText firstContent;
    private final Icon firstIcon;
    private final LabelText firstTitle;
    private final ArrayList<LabelButton> lastBtns;
    private final String type;

    public /* synthetic */ SymActionLayout(String str, Icon icon, LabelText labelText, LabelText labelText2, ArrayList arrayList, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "t_sym_action" : str, icon, labelText, labelText2, arrayList);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SymActionLayout copy$default(SymActionLayout symActionLayout, String str, Icon icon, LabelText labelText, LabelText labelText2, ArrayList arrayList, int i, Object obj) {
        if ((i & 1) != 0) {
            str = symActionLayout.type;
        }
        if ((i & 2) != 0) {
            icon = symActionLayout.firstIcon;
        }
        if ((i & 4) != 0) {
            labelText = symActionLayout.firstTitle;
        }
        if ((i & 8) != 0) {
            labelText2 = symActionLayout.firstContent;
        }
        if ((i & 16) != 0) {
            arrayList = symActionLayout.lastBtns;
        }
        ArrayList arrayList2 = arrayList;
        LabelText labelText3 = labelText;
        return symActionLayout.copy(str, icon, labelText3, labelText2, arrayList2);
    }

    public final String component1() {
        return this.type;
    }

    public final Icon component2() {
        return this.firstIcon;
    }

    public final LabelText component3() {
        return this.firstTitle;
    }

    public final LabelText component4() {
        return this.firstContent;
    }

    public final ArrayList<LabelButton> component5() {
        return this.lastBtns;
    }

    public final SymActionLayout copy(String str, Icon icon, LabelText labelText, LabelText labelText2, ArrayList<LabelButton> arrayList) {
        return new SymActionLayout(str, icon, labelText, labelText2, arrayList);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SymActionLayout)) {
            return false;
        }
        SymActionLayout symActionLayout = (SymActionLayout) obj;
        return Intrinsics.areEqual(this.type, symActionLayout.type) && Intrinsics.areEqual(this.firstIcon, symActionLayout.firstIcon) && Intrinsics.areEqual(this.firstTitle, symActionLayout.firstTitle) && Intrinsics.areEqual(this.firstContent, symActionLayout.firstContent) && Intrinsics.areEqual(this.lastBtns, symActionLayout.lastBtns);
    }

    public final LabelText getFirstContent() {
        return this.firstContent;
    }

    public final Icon getFirstIcon() {
        return this.firstIcon;
    }

    public final LabelText getFirstTitle() {
        return this.firstTitle;
    }

    public final ArrayList<LabelButton> getLastBtns() {
        return this.lastBtns;
    }

    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        return this.lastBtns.hashCode() + ((this.firstContent.hashCode() + ((this.firstTitle.hashCode() + ((this.firstIcon.hashCode() + (this.type.hashCode() * 31)) * 31)) * 31)) * 31);
    }

    public String toString() {
        return "SymActionLayout(type=" + this.type + ", firstIcon=" + this.firstIcon + ", firstTitle=" + this.firstTitle + ", firstContent=" + this.firstContent + ", lastBtns=" + this.lastBtns + ")";
    }

    public SymActionLayout(String str, Icon icon, LabelText labelText, LabelText labelText2, ArrayList<LabelButton> arrayList) {
        this.type = str;
        this.firstIcon = icon;
        this.firstTitle = labelText;
        this.firstContent = labelText2;
        this.lastBtns = arrayList;
    }
}
