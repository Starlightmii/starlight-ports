package com.oplus.systemui.qs.personality;

import android.content.Context;
import android.graphics.Path;
import android.graphics.drawable.shapes.PathShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.util.PathParser;
import android.util.SparseArray;
import android.util.SparseIntArray;
import androidx.appcompat.widget.ListPopupWindow$;
import com.android.systemui.qs.personality.OnPersonalityChangeListener;
import com.android.systemui.qs.personality.PersonalityManagerEx;
import com.oplusos.systemui.common.feature.FlavorTwoFeatureOption;
import com.oplusos.systemui.common.observer.IObserverListener;
import com.oplusos.systemui.common.observer.PersonalityObserver;
import com.oplusos.systemui.common.settingsvalue.CommonSettingsValueProxy;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PersonalityManager extends PersonalityManagerEx {
    public final int mDefaultShapeType;
    private final WeakHashMap<OnPersonalityChangeListener, Object> mListenerList;
    private final SparseArray<Path> mShapeCache;
    private final SparseIntArray mShapeTypeDataMap;
    private final SparseIntArray mShapeTypeDataMapForHighlight;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.personality.PersonalityManager$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements IObserverListener {
        public /* synthetic */ PersonalityManager this$0;

        @Override // com.oplusos.systemui.common.observer.IObserverListener
        public final void onChange(boolean z) {
            int shapeType = PersonalityObserver.Companion.getInstance().getShapeType();
            LogUtil.i$default(LogUtil.TAG_QS, "PersonalityManager", ListPopupWindow$.ExternalSyntheticOutline0.m(shapeType, "observe shape type changed:"), null, 8);
            this.this$0.notifyListener(shapeType);
        }
    }

    public PersonalityManager(Context context) {
        super(context);
        this.mDefaultShapeType = 2;
        this.mListenerList = new WeakHashMap<>();
        SparseIntArray sparseIntArray = new SparseIntArray();
        this.mShapeTypeDataMap = sparseIntArray;
        this.mShapeCache = new SparseArray<>();
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        this.mShapeTypeDataMapForHighlight = sparseIntArray2;
        sparseIntArray.put(1, 2131955856);
        sparseIntArray.put(0, 2131955858);
        sparseIntArray.put(2, 2131955853);
        sparseIntArray.put(3, 2131955855);
        sparseIntArray.put(4, 2131955857);
        sparseIntArray.put(5, 2131955854);
        sparseIntArray2.put(0, 2131171942);
        sparseIntArray2.put(1, 2131171940);
        sparseIntArray2.put(2, 2131171938);
        sparseIntArray2.put(3, 2131171938);
        sparseIntArray2.put(4, 2131171941);
        sparseIntArray2.put(5, 2131171942);
        PersonalityObserver.Companion companion = PersonalityObserver.Companion;
        companion.getInstance().registerForUserSwitch(context, -1, false);
        PersonalityObserver companion2 = companion.getInstance();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        companion2.addListener(anonymousClass1);
    }

    public void addListener(OnPersonalityChangeListener onPersonalityChangeListener) {
        this.mListenerList.put(onPersonalityChangeListener, PersonalityManagerEx.Companion.getCONTENT());
    }

    public String getCurrentPathRes(Context context) {
        return context.getString(this.mShapeTypeDataMap.get(getLastShapeType()));
    }

    public float getHighlightRadius(Context context) {
        FlavorTwoFeatureOption.INSTANCE.getClass();
        return (FlavorTwoFeatureOption.isFlavorTwoDeviceExp() && getLastShapeType() == 2) ? context.getResources().getDimension(2131171939) : getShapeRadius(context);
    }

    public PathShape getLastShape(Context context) {
        Path path;
        int lastShapeType = getLastShapeType();
        if (this.mShapeCache.indexOfKey(lastShapeType) >= 0) {
            path = this.mShapeCache.get(lastShapeType);
        } else {
            Path pathCreatePathFromPathData = PathParser.createPathFromPathData(context.getString(this.mShapeTypeDataMap.get(lastShapeType)));
            this.mShapeCache.put(lastShapeType, pathCreatePathFromPathData);
            path = pathCreatePathFromPathData;
        }
        return new PathShape(path, 132.0f, 132.0f);
    }

    public int getLastShapeType() {
        return PersonalityObserver.Companion.getInstance().getShapeType();
    }

    public float getMediaPanelRadius(Context context) {
        return getShapeRadius(context);
    }

    public Shape getShapeForHighlightTile(Context context) {
        float shapeRadius = getShapeRadius(context);
        float[] fArr = new float[8];
        for (int i = 0; i < 8; i++) {
            fArr[i] = shapeRadius;
        }
        return new RoundRectShape(fArr, null, null);
    }

    public float getShapeRadius(Context context) {
        return context.getResources().getDimension(this.mShapeTypeDataMapForHighlight.get(getLastShapeType()));
    }

    public final void notifyListener(int i) {
        Iterator<Map.Entry<OnPersonalityChangeListener, Object>> it = this.mListenerList.entrySet().iterator();
        while (it.hasNext()) {
            OnPersonalityChangeListener key = it.next().getKey();
            if (key != null) {
                key.onShapeChanged(i);
            }
        }
    }

    public void removeListener(OnPersonalityChangeListener onPersonalityChangeListener) {
        this.mListenerList.remove(onPersonalityChangeListener);
    }

    public void reset(Context context) {
        CommonSettingsValueProxy.Companion.setQSShapeType(context, this.mDefaultShapeType);
    }
}
