package com.oplus.systemui.qs.base.customizer;

import android.content.Context;
import android.support.v4.content.FileProvider$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.plugins.qs.QSTileQueryHelper;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OplusQSPagerDragAdapter extends OplusQSBaseDragAdapter {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQSCustomizerPagerDragAdapter";
    private final IQSCustomizer$EditType editType;
    private Context mContext;
    private Callback mEditModeCallback;
    public boolean mHasChanged;
    public boolean mNeedShowAppLabel;
    private final String mTag;
    private final List<QSTileQueryHelper.OplusTileInfo> mTiles = new ArrayList();
    private List<String> mCurrentSpecs = new ArrayList();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Callback {
        void addRemoveTile(QSTileQueryHelper.OplusTileInfo oplusTileInfo);

        boolean checkLimitCount();

        QSTileQueryHelper.OplusTileInfo getEmptyTileInfo();

        void shouldAddEmptyTileInfo();
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusQSPagerDragAdapter(Context context, IQSCustomizer$EditType iQSCustomizer$EditType) {
        this.mContext = context;
        this.editType = iQSCustomizer$EditType;
        this.mTag = iQSCustomizer$EditType.name();
    }

    @Override // com.oplus.systemui.qs.base.customizer.OplusQSBaseDragAdapter
    public void addTile(QSTileQueryHelper.OplusTileInfo oplusTileInfo, int i) {
        int size;
        if (i > this.mTiles.size() - 1) {
            this.mTiles.add(oplusTileInfo);
            size = this.mTiles.size() - 1;
        } else {
            int i2 = i > 0 ? i : 0;
            this.mTiles.add(i2, oplusTileInfo);
            size = i2;
        }
        log("addTile position=" + i);
        notifyItemInserted(size);
    }

    public final List<String> getCurrentSpecs() {
        return this.mCurrentSpecs;
    }

    @Override // com.oplus.systemui.qs.base.customizer.OplusQSBaseDragAdapter
    public IQSCustomizer$EditType getEditType() {
        return this.editType;
    }

    public final int getEmptyTileIndex() {
        List<QSTileQueryHelper.OplusTileInfo> list = this.mTiles;
        ListIterator<QSTileQueryHelper.OplusTileInfo> listIterator = list.listIterator(list.size());
        while (listIterator.hasPrevious()) {
            QSTileQueryHelper.OplusTileInfo oplusTileInfoPrevious = listIterator.previous();
            if (oplusTileInfoPrevious != null && !oplusTileInfoPrevious.getVisible() && oplusTileInfoPrevious.getSpec() == null) {
                return listIterator.nextIndex();
            }
        }
        return -1;
    }

    public int getItemCount() {
        return this.mTiles.size();
    }

    public final Callback getMEditModeCallback() {
        return this.mEditModeCallback;
    }

    public final int getRealItemCount() {
        List<QSTileQueryHelper.OplusTileInfo> tiles = getTiles();
        ArrayList arrayList = new ArrayList();
        for (Object obj : tiles) {
            QSTileQueryHelper.OplusTileInfo oplusTileInfo = (QSTileQueryHelper.OplusTileInfo) obj;
            if ((oplusTileInfo != null ? oplusTileInfo.getSpec() : null) != null) {
                arrayList.add(obj);
            }
        }
        return arrayList.size();
    }

    @Override // com.oplus.systemui.qs.base.customizer.OplusQSBaseDragAdapter
    public QSTileQueryHelper.OplusTileInfo getTile(int i) {
        if (i < 0 || i >= this.mTiles.size()) {
            return null;
        }
        return this.mTiles.get(i);
    }

    @Override // com.oplus.systemui.qs.base.customizer.OplusQSBaseDragAdapter
    public int getTilePosition(QSTileQueryHelper.OplusTileInfo oplusTileInfo) {
        return this.mTiles.indexOf(oplusTileInfo);
    }

    public final List<QSTileQueryHelper.OplusTileInfo> getTiles() {
        return this.mTiles;
    }

    public final void log(String str) {
        LogUtil.i$default(LogUtil.TAG_QS, FileProvider$.ExternalSyntheticOutline0.m("OplusQSCustomizerPagerDragAdapter-", this.mTag), str, null, 8);
    }

    public abstract void onBind(OplusQSBaseDragAdapter.BaseHolder baseHolder);

    public void onBindViewHolder(OplusQSBaseDragAdapter.BaseHolder baseHolder, int i) {
        baseHolder.bind(this.mTiles.get(i), this.mNeedShowAppLabel);
        onBind(baseHolder);
    }

    public final void onTilesChanged(List<QSTileQueryHelper.OplusTileInfo> list) {
        Callback callback = this.mEditModeCallback;
        if (!this.mHasChanged) {
            this.mTiles.clear();
            this.mTiles.addAll(list);
            if (callback != null) {
                callback.shouldAddEmptyTileInfo();
            }
            notifyDataSetChanged();
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<QSTileQueryHelper.OplusTileInfo> it = this.mTiles.iterator();
        while (it.hasNext()) {
            QSTileQueryHelper.OplusTileInfo next = it.next();
            arrayList.add(next != null ? next.getSpec() : null);
        }
        for (QSTileQueryHelper.OplusTileInfo oplusTileInfo : list) {
            if (!arrayList.contains(oplusTileInfo.getSpec())) {
                this.mTiles.add(oplusTileInfo);
                notifyItemChanged(this.mTiles.size() - 1);
            }
        }
        if (callback != null) {
            callback.shouldAddEmptyTileInfo();
        }
        log("Changed before Tiles is ready");
    }

    @Override // com.oplus.systemui.qs.base.customizer.OplusQSBaseDragAdapter
    public final void removeTile(int i) {
        if (this.mTiles.size() > i) {
            this.mTiles.remove(i);
            notifyItemRemoved(i);
            log("removeTile position=" + i);
        }
    }

    public final void resetTileSpecs(List<String> list) {
        setTileSpecs(list);
    }

    public final void setContext(Context context) {
        this.mContext = context;
    }

    public final void setEditModeCallback(Callback callback) {
        this.mEditModeCallback = callback;
    }

    public final void setMEditModeCallback(Callback callback) {
        this.mEditModeCallback = callback;
    }

    public final void setNeedShowAppLabel(boolean z) {
        this.mNeedShowAppLabel = z;
    }

    public final void setQsColorState(int i) {
    }

    public final void setTileSpecs(List<String> list) {
        if (Intrinsics.areEqual(list, this.mCurrentSpecs)) {
            return;
        }
        this.mCurrentSpecs = list;
    }

    @Override // com.oplus.systemui.qs.base.customizer.OplusQSBaseDragAdapter
    public void setTileVisible(int i, boolean z) {
        QSTileQueryHelper.OplusTileInfo tile = getTile(i);
        if (tile == null) {
            return;
        }
        String spec = tile.getSpec();
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("setTileVisible position=", i, " visible=", " tileInfo.spec=", z);
        sbM.append(spec);
        log(sbM.toString());
        tile.setVisible(z);
        notifyItemChanged(i);
    }

    public final void setTilesChanged(boolean z) {
        this.mHasChanged = z;
    }

    @Override // com.oplus.systemui.qs.base.customizer.OplusQSBaseDragAdapter
    public final void swapTile(int i, int i2) {
        if (i >= i2) {
            int i3 = i2 + 1;
            if (i3 <= i) {
                int i4 = i;
                while (true) {
                    Collections.swap(this.mTiles, i4, i4 - 1);
                    if (i4 == i3) {
                        break;
                    } else {
                        i4--;
                    }
                }
            }
        } else {
            int i5 = i;
            while (i5 < i2) {
                int i6 = i5 + 1;
                Collections.swap(this.mTiles, i5, i6);
                i5 = i6;
            }
        }
        log("swapTile");
        notifyItemMoved(i, i2);
    }
}
