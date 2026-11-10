package com.oplus.vfxsdk.common;

import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import androidx.exifinterface.media.ExifInterface$;
import com.android.launcher3.icons.IconProvider$;
import com.oplus.vfxsdk.common.parameter.IPop;
import com.oplus.vfxsdk.common.parameter.IUpdate;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AnimLine {
    private final IKey[] animKeys;
    private ChannelData channelData;
    private final int index;
    private final String key;
    private final float lastTime;
    private final String name;
    private final String nodeId;
    private IPop pop;
    private final UniformType type;
    private IUpdate update;

    public AnimLine(String str, String str2, String str3, UniformType uniformType, int i, float f, IKey[] iKeyArr, ChannelData channelData) {
        this.nodeId = str;
        this.key = str2;
        this.name = str3;
        this.type = uniformType;
        this.index = i;
        this.lastTime = f;
        this.animKeys = iKeyArr;
        this.channelData = channelData;
    }

    public static /* synthetic */ AnimLine copy$default(AnimLine animLine, String str, String str2, String str3, UniformType uniformType, int i, float f, IKey[] iKeyArr, ChannelData channelData, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = animLine.nodeId;
        }
        if ((i2 & 2) != 0) {
            str2 = animLine.key;
        }
        if ((i2 & 4) != 0) {
            str3 = animLine.name;
        }
        if ((i2 & 8) != 0) {
            uniformType = animLine.type;
        }
        if ((i2 & 16) != 0) {
            i = animLine.index;
        }
        if ((i2 & 32) != 0) {
            f = animLine.lastTime;
        }
        if ((i2 & 64) != 0) {
            iKeyArr = animLine.animKeys;
        }
        if ((i2 & 128) != 0) {
            channelData = animLine.channelData;
        }
        IKey[] iKeyArr2 = iKeyArr;
        ChannelData channelData2 = channelData;
        int i3 = i;
        float f2 = f;
        return animLine.copy(str, str2, str3, uniformType, i3, f2, iKeyArr2, channelData2);
    }

    public final String component1() {
        return this.nodeId;
    }

    public final String component2() {
        return this.key;
    }

    public final String component3() {
        return this.name;
    }

    public final UniformType component4() {
        return this.type;
    }

    public final int component5() {
        return this.index;
    }

    public final float component6() {
        return this.lastTime;
    }

    public final IKey[] component7() {
        return this.animKeys;
    }

    public final ChannelData component8() {
        return this.channelData;
    }

    public final AnimLine copy(String str, String str2, String str3, UniformType uniformType, int i, float f, IKey[] iKeyArr, ChannelData channelData) {
        return new AnimLine(str, str2, str3, uniformType, i, f, iKeyArr, channelData);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AnimLine)) {
            return false;
        }
        AnimLine animLine = (AnimLine) obj;
        return Intrinsics.areEqual(this.nodeId, animLine.nodeId) && Intrinsics.areEqual(this.key, animLine.key) && Intrinsics.areEqual(this.name, animLine.name) && this.type == animLine.type && this.index == animLine.index && Float.compare(this.lastTime, animLine.lastTime) == 0 && Intrinsics.areEqual(this.animKeys, animLine.animKeys) && Intrinsics.areEqual(this.channelData, animLine.channelData);
    }

    public final IKey[] getAnimKeys() {
        return this.animKeys;
    }

    public final ChannelData getChannelData() {
        return this.channelData;
    }

    public final int getIndex() {
        return this.index;
    }

    public final String getKey() {
        return this.key;
    }

    public final float getLastTime() {
        return this.lastTime;
    }

    public final String getName() {
        return this.name;
    }

    public final String getNodeId() {
        return this.nodeId;
    }

    public final IPop getPop() {
        return this.pop;
    }

    public final UniformType getType() {
        return this.type;
    }

    public final IUpdate getUpdate() {
        return this.update;
    }

    public int hashCode() {
        int iM = (ChangeSize$.ExternalSyntheticOutline0.m(this.lastTime, MonoSpline$.ExternalSyntheticOutline0.m(this.index, (this.type.hashCode() + PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(this.nodeId.hashCode() * 31, 31, this.key), 31, this.name)) * 31, 31), 31) + Arrays.hashCode(this.animKeys)) * 31;
        ChannelData channelData = this.channelData;
        return iM + (channelData == null ? 0 : channelData.hashCode());
    }

    public final void setChannelData(ChannelData channelData) {
        this.channelData = channelData;
    }

    public final void setPop(IPop iPop) {
        this.pop = iPop;
    }

    public final void setUpdate(IUpdate iUpdate) {
        this.update = iUpdate;
    }

    public String toString() {
        String str = this.nodeId;
        String str2 = this.key;
        String str3 = this.name;
        UniformType uniformType = this.type;
        int i = this.index;
        float f = this.lastTime;
        String string = Arrays.toString(this.animKeys);
        ChannelData channelData = this.channelData;
        StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("AnimLine(nodeId=", str, ", key=", str2, ", name=");
        sbM.append(str3);
        sbM.append(", type=");
        sbM.append(uniformType);
        sbM.append(", index=");
        IconProvider$.ExternalSyntheticOutline0.m(f, i, ", lastTime=", ", animKeys=", sbM);
        sbM.append(string);
        sbM.append(", channelData=");
        sbM.append(channelData);
        sbM.append(")");
        return sbM.toString();
    }
}
