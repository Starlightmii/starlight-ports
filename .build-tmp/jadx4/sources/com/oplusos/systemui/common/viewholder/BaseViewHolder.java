package com.oplusos.systemui.common.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder {
    private final Context context;
    private Object data;
    private final ViewModel viewModel;

    public BaseViewHolder(Context context, int i, ViewGroup viewGroup, ViewModel viewModel) {
        super(LayoutInflater.from(context).inflate(i, viewGroup, false));
        this.context = context;
        this.viewModel = viewModel;
    }

    public void bindData(Object obj) {
        this.data = obj;
    }

    public final Context getContext() {
        return this.context;
    }

    public final Object getData() {
        return this.data;
    }

    public final ViewModel getViewModel() {
        return this.viewModel;
    }

    public void onRecycled() {
        this.data = null;
    }

    public abstract void onViewCreated();

    public final void setData(Object obj) {
        this.data = obj;
    }
}
