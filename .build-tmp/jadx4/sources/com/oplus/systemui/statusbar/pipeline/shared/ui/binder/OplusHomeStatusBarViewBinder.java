package com.oplus.systemui.statusbar.pipeline.shared.ui.binder;

import android.view.View;
import com.android.systemui.statusbar.pipeline.shared.ui.binder.StatusBarVisibilityChangeListener;
import com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface OplusHomeStatusBarViewBinder {
    void bind(int i, View view, OplusHomeStatusBarViewModel oplusHomeStatusBarViewModel, Function1 function1, Function1 function2, StatusBarVisibilityChangeListener statusBarVisibilityChangeListener);
}
