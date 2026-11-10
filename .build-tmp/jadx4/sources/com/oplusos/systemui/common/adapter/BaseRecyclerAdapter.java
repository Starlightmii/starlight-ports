package com.oplusos.systemui.common.adapter;

import android.content.Context;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.oplusos.systemui.common.viewholder.BaseViewHolder;
import com.oplusos.systemui.common.viewholder.IViewHolderCreator;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class BaseRecyclerAdapter extends RecyclerView.Adapter {
    private final List<Object> _dataListField;
    private final Context context;
    private final List<Object> dataList;
    private final IViewHolderCreator viewHolderCreator;

    public BaseRecyclerAdapter(Context context, IViewHolderCreator iViewHolderCreator) {
        this.context = context;
        this.viewHolderCreator = iViewHolderCreator;
        ArrayList arrayList = new ArrayList();
        this._dataListField = arrayList;
        this.dataList = arrayList;
    }

    public final List<Object> getDataList() {
        return this.dataList;
    }

    public int getItemCount() {
        return this._dataListField.size();
    }

    public int getItemViewType(int i) {
        return 0;
    }

    @Override // 
    public void onBindViewHolder(BaseViewHolder baseViewHolder, int i) {
        if (i < 0 || i >= this._dataListField.size()) {
            return;
        }
        baseViewHolder.bindData(this._dataListField.get(i));
    }

    public BaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        BaseViewHolder baseViewHolderCreate = this.viewHolderCreator.create(this.context, i, viewGroup);
        baseViewHolderCreate.onViewCreated();
        return baseViewHolderCreate;
    }

    public void onViewRecycled(BaseViewHolder baseViewHolder) {
        super.onViewRecycled(baseViewHolder);
        baseViewHolder.onRecycled();
    }

    public final Object removeItemData(int i) {
        if (i < 0 || i >= this._dataListField.size()) {
            return null;
        }
        Object objRemove = this._dataListField.remove(i);
        notifyItemRemoved(i);
        return objRemove;
    }

    public final void setDataList(List<Object> list) {
        this._dataListField.clear();
        this._dataListField.addAll(list);
    }

    public final Object updateItemData(Object obj, int i) {
        if (i < 0 || i >= this._dataListField.size()) {
            return null;
        }
        Object obj2 = this._dataListField.set(i, obj);
        notifyItemChanged(i);
        return obj2;
    }
}
