public class Diag {
  public static void main(String[] a) throws Exception {
    Class<?> c = Class.forName("com.android.internal.os.BinderInternal");
    System.out.println("class=" + c);
    for (java.lang.reflect.Method m : c.getDeclaredMethods()) {
      System.out.println("M " + m);
    }
    try {
      java.lang.reflect.Method j = c.getMethod("joinThreadPool");
      System.out.println("found join=" + j);
      j.invoke(null);
      System.out.println("join returned");
    } catch (Throwable t) {
      System.out.println("join failed: " + t);
      t.printStackTrace(System.out);
    }
    Thread.sleep(1500);
  }
}
