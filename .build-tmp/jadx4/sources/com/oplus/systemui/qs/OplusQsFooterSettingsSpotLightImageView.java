package com.oplus.systemui.qs;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import com.android.systemui.statusbar.AlphaOptimizedImageView;
import com.oplus.systemui.notification.material.NotificationSpotLightDelegate;
import com.oplus.systemui.notification.material.NotificationSpotLightParamsKind;
import java.lang.invoke.VarHandle;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsFooterSettingsSpotLightImageView extends AlphaOptimizedImageView {

    /* JADX INFO: renamed from: com.oplus.systemui.qs.OplusQsFooterSettingsSpotLightImageView$onDraw$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements NotificationSpotLightDelegate.NotificationSpotLightClipPathProvider {
        public /* synthetic */ OplusQsFooterSettingsSpotLightImageView this$0;

        @Override // com.oplus.systemui.notification.material.NotificationSpotLightDelegate.NotificationSpotLightClipPathProvider
        public final NotificationSpotLightDelegate.ClipSpec getClipSpec() {
            return this.this$0.getSpotLightClipSpec();
        }
    }

    public /* synthetic */ OplusQsFooterSettingsSpotLightImageView(Context context, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final NotificationSpotLightDelegate.ClipSpec getSpotLightClipSpec() {
        int width = getWidth();
        int height = getHeight();
        if (width <= 0 || height <= 0) {
            return null;
        }
        float fMin = Math.min(width, height) / 2.0f;
        float width2 = getWidth() / 2.0f;
        float height2 = getHeight() / 2.0f;
        return new NotificationSpotLightDelegate.ClipSpec((int) (width2 - fMin), (int) (height2 - fMin), (int) (width2 + fMin), (int) (height2 + fMin), fMin, fMin, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void onDraw(Canvas canvas) {
        super/*android.widget.ImageView*/.onDraw(canvas);
        NotificationSpotLightDelegate.Companion companion = NotificationSpotLightDelegate.Companion;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        companion.draw(this, canvas, anonymousClass1);
    }

    public OplusQsFooterSettingsSpotLightImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, null);
    }

    public OplusQsFooterSettingsSpotLightImageView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0, 8, null);
    }

    public OplusQsFooterSettingsSpotLightImageView(Context context) {
        this(context, null, 0, 0, 14, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public OplusQsFooterSettingsSpotLightImageView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        NotificationSpotLightDelegate.Companion.markLazyParamsKind(this, NotificationSpotLightParamsKind.QS_SETTINGS_BUTTON);
    }
}
