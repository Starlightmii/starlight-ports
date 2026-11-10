package com.oplus.systemui.seedlingservice.flashlight;

import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StandardLayout {
    private final Icon icon;
    private final ArrayList<ArrayList<LabelText>> labels;
    private final LabelText title;
    private final String type;

    public StandardLayout(String str, Icon icon, LabelText labelText, ArrayList<ArrayList<LabelText>> arrayList) {
        this.type = str;
        this.icon = icon;
        this.title = labelText;
        this.labels = arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ StandardLayout copy$default(StandardLayout standardLayout, String str, Icon icon, LabelText labelText, ArrayList arrayList, int i, Object obj) {
        if ((i & 1) != 0) {
            str = standardLayout.type;
        }
        if ((i & 2) != 0) {
            icon = standardLayout.icon;
        }
        if ((i & 4) != 0) {
            labelText = standardLayout.title;
        }
        if ((i & 8) != 0) {
            arrayList = standardLayout.labels;
        }
        return standardLayout.copy(str, icon, labelText, arrayList);
    }

    public final String component1() {
        return this.type;
    }

    public final Icon component2() {
        return this.icon;
    }

    public final LabelText component3() {
        return this.title;
    }

    public final ArrayList<ArrayList<LabelText>> component4() {
        return this.labels;
    }

    public final StandardLayout copy(String str, Icon icon, LabelText labelText, ArrayList<ArrayList<LabelText>> arrayList) {
        return new StandardLayout(str, icon, labelText, arrayList);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StandardLayout)) {
            return false;
        }
        StandardLayout standardLayout = (StandardLayout) obj;
        return Intrinsics.areEqual(this.type, standardLayout.type) && Intrinsics.areEqual(this.icon, standardLayout.icon) && Intrinsics.areEqual(this.title, standardLayout.title) && Intrinsics.areEqual(this.labels, standardLayout.labels);
    }

    public final Icon getIcon() {
        return this.icon;
    }

    public final ArrayList<ArrayList<LabelText>> getLabels() {
        return this.labels;
    }

    public final LabelText getTitle() {
        return this.title;
    }

    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        return this.labels.hashCode() + ((this.title.hashCode() + ((this.icon.hashCode() + (this.type.hashCode() * 31)) * 31)) * 31);
    }

    public String toString() {
        return "StandardLayout(type=" + this.type + ", icon=" + this.icon + ", title=" + this.title + ", labels=" + this.labels + ")";
    }

    public /* synthetic */ StandardLayout(String str, Icon icon, LabelText labelText, ArrayList arrayList, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "t_standard" : str, icon, labelText, arrayList);
    }
}
