package io.github.andrea_lyz.assistrestore;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import io.github.libxposed.api.XposedInterface;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class WalletHooks {
    private static final String QUICK_ACCESS_WALLET_ACTIVITY = "com.android.systemui.wallet.ui.WalletActivity";
    private static final String SYSTEMUI_PACKAGE = "com.android.systemui";
    private static final String WALLET_CONTROLLER = "com.android.server.policy.OplusWalletController";

    private WalletHooks() {
    }

    public static void install(final AssistRestoreModule assistRestoreModule, ClassLoader classLoader) {
        try {
            Class<?> cls = Class.forName(WALLET_CONTROLLER, true, classLoader);
            Method declaredMethod = cls.getDeclaredMethod("startWallet", Boolean.TYPE);
            declaredMethod.setAccessible(true);
            final Field declaredField = cls.getDeclaredField("mContext");
            declaredField.setAccessible(true);
            assistRestoreModule.hook(declaredMethod).setId(AssistConfig.KEY_DOUBLE_PRESS_WALLET).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new XposedInterface.Hooker() { // from class: io.github.andrea_lyz.assistrestore.i
                public final Object intercept(XposedInterface.Chain chain) {
                    return WalletHooks.lambda$install$0(assistRestoreModule, declaredField, chain);
                }
            });
            assistRestoreModule.logInfo("hook_installed target=com.android.server.policy.OplusWalletController.startWallet");
        } catch (Throwable th) {
            assistRestoreModule.logError("hook_failed target=com.android.server.policy.OplusWalletController.startWallet", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object lambda$install$0(AssistRestoreModule assistRestoreModule, Field field, XposedInterface.Chain chain) {
        if (!AssistConfig.isEnabled(HookPrefs.get()) || !AssistConfig.doublePressWallet(HookPrefs.get())) {
            return chain.proceed();
        }
        if (launchQuickAccessWallet(assistRestoreModule, chain.getThisObject(), field)) {
            return null;
        }
        return chain.proceed();
    }

    private static boolean launchQuickAccessWallet(AssistRestoreModule assistRestoreModule, Object obj, Field field) {
        try {
            Object obj2 = field.get(obj);
            if (!(obj2 instanceof Context)) {
                assistRestoreModule.logWarn("double_press_wallet_failed reason=no_context");
                return false;
            }
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(SYSTEMUI_PACKAGE, QUICK_ACCESS_WALLET_ACTIVITY));
            intent.addFlags(268435456);
            startForCurrentUser((Context) obj2, intent);
            assistRestoreModule.logInfo("double_press_wallet target=quick_access_wallet launched=true");
            return true;
        } catch (Throwable th) {
            assistRestoreModule.logWarn("double_press_wallet_failed " + th);
            return false;
        }
    }

    private static void startForCurrentUser(Context context, Intent intent) {
        try {
            Context.class.getMethod("startActivityAsUser", Intent.class, UserHandle.class).invoke(context, intent, UserHandle.class.getField("CURRENT").get(null));
        } catch (Throwable unused) {
            context.startActivity(intent);
        }
    }
}
