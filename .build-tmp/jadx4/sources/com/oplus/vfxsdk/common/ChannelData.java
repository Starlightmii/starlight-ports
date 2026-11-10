package com.oplus.vfxsdk.common;

import com.oplus.vfxsdk.common.parameter.IUpdate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.jvm.internal.markers.KMutableMap;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ChannelData {
    private IUpdate update;
    public boolean updateDirty;
    private Object values;
    private String name = "";
    private UniformType type = UniformType.Float;
    public int passId = -1;

    public static Object deepCopyValue(Object obj) {
        if (TypeIntrinsics.isMutableList(obj)) {
            List list = (List) obj;
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                arrayList.add(next != null ? deepCopyValue(next) : null);
            }
            return arrayList;
        }
        if (!(obj instanceof Map) || ((obj instanceof KMappedMarker) && !(obj instanceof KMutableMap))) {
            if (!(obj instanceof Object[])) {
                return obj;
            }
            Object[] objArr = (Object[]) obj;
            ArrayList arrayList2 = new ArrayList(objArr.length);
            int length = objArr.length;
            for (int i = 0; i < length; i++) {
                Object obj2 = objArr[i];
                arrayList2.add(obj2 != null ? deepCopyValue(obj2) : null);
            }
            return arrayList2.toArray(new Object[0]);
        }
        Set<Map.Entry> setEntrySet = ((Map) obj).entrySet();
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(setEntrySet, 10));
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (Map.Entry entry : setEntrySet) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            linkedHashMap.put(key, value != null ? deepCopyValue(value) : null);
        }
        return linkedHashMap;
    }

    public final void addChannel(Object obj, int i) {
        switch (this.type.ordinal()) {
            case 0:
                if (this.values == null) {
                    this.values = new float[2];
                }
                ((float[]) this.values)[i] = ((Float) obj).floatValue();
                break;
            case 1:
                if (this.values == null) {
                    this.values = new float[3];
                }
                ((float[]) this.values)[i] = ((Float) obj).floatValue();
                break;
            case 2:
            case 3:
                if (this.values == null) {
                    this.values = new float[4];
                }
                ((float[]) this.values)[i] = ((Float) obj).floatValue();
                break;
            case 4:
                this.values = Integer.valueOf(Math.round(((Float) obj).floatValue()));
                break;
            case 5:
            case 6:
                this.values = (Float) obj;
                break;
            default:
                this.values = obj;
                break;
        }
        IUpdate iUpdate = this.update;
        if (iUpdate != null) {
            iUpdate.updateValue(this.name, this.values);
        }
    }

    public final ChannelData deepCopy() {
        ChannelData channelData = new ChannelData();
        channelData.name = this.name;
        channelData.type = this.type;
        channelData.updateDirty = this.updateDirty;
        channelData.values = deepCopyValue(this.values);
        return channelData;
    }

    public final Object getChannel(int i) {
        float fFloatValue;
        switch (this.type.ordinal()) {
            case 0:
            case 1:
            case 2:
            case 3:
                return Float.valueOf(((float[]) this.values)[i]);
            case 4:
                Object obj = this.values;
                if (!(obj instanceof Integer)) {
                    return 0;
                }
                obj.getClass();
                return obj;
            case 5:
            case 6:
                Object obj2 = this.values;
                if (obj2 instanceof Float) {
                    fFloatValue = ((Float) obj2).floatValue();
                } else {
                    fFloatValue = obj2 instanceof Number ? ((Number) obj2).floatValue() : 0.0f;
                }
                return Float.valueOf(fFloatValue);
            default:
                return new Object();
        }
    }

    public final String getName() {
        return this.name;
    }

    public final int getPassId() {
        return this.passId;
    }

    public final UniformType getType() {
        return this.type;
    }

    public final IUpdate getUpdate() {
        return this.update;
    }

    public final boolean getUpdateDirty() {
        return this.updateDirty;
    }

    public final Object getV() {
        return this.values;
    }

    public final Object getValues() {
        return this.values;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final void setPassId(int i) {
        this.passId = i;
    }

    public final void setType(UniformType uniformType) {
        this.type = uniformType;
    }

    public final void setUpdate(IUpdate iUpdate) {
        this.update = iUpdate;
    }

    public final void setUpdateDirty(boolean z) {
        this.updateDirty = z;
    }

    public final void setV(Object obj) {
        this.values = obj;
    }

    public final void setValues(Object obj) {
        this.values = obj;
    }
}
