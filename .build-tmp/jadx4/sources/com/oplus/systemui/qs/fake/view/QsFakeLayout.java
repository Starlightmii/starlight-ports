package com.oplus.systemui.qs.fake.view;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.function.Consumer;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QsFakeLayout extends ConstraintLayout {
    public static final Companion Companion = new Companion();
    private static final String TAG = "QsFakeLayout";
    private Consumer<Configuration> mConfigurationChangedListener;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public /* synthetic */ QsFakeLayout(Context context, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onConfigurationChanged(Configuration configuration) {
        super/*android.view.ViewGroup*/.onConfigurationChanged(configuration);
        Consumer<Configuration> consumer = this.mConfigurationChangedListener;
        if (consumer != null) {
            consumer.accept(configuration);
        }
    }

    public final void setConfigurationChangedListener(Consumer<Configuration> consumer) {
        this.mConfigurationChangedListener = consumer;
    }

    public QsFakeLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, null);
    }

    public QsFakeLayout(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0, 8, null);
    }

    public QsFakeLayout(Context context) {
        this(context, null, 0, 0, 14, null);
    }

    public QsFakeLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }
}
