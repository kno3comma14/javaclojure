package knowcomma.core;

import clojure.java.api.Clojure;
import clojure.lang.IFn;

import knowcomma.core.GetterBoolean;


class Knowcomma {
    private static volatile Knowcomma instance = null;
    private static Object mutex = new Object();
    private static IFn clojureRequire;
    private static IFn knowcommaFn;
    private Knowcomma() {
        clojureRequire = Clojure.var("clojure.core", "require");
        clojureRequire.invoke(Clojure.read("knowcomma.core"));
        knowcommaFn = Clojure.var("knowcomma.core", "foo");
    }
    private static void callFoo(String s) {
        knowcommaFn.invoke(s);
    }
    public static void run() {
        System.out.println("Hello, World! (from Java), boolean: " + new GetterBoolean().getRunning());
        callFoo("Java calling!");
    }
    public static Knowcomma getInstance() {
        Knowcomma result = instance;
        if (result == null) {
            synchronized(mutex) {
                result = instance;
                if (result == null)
                    instance = result = new Knowcomma();
            }
        }
        return instance;
    }
    public static void main(String[] args) {
        Knowcomma me = getInstance();
        me.run();
    }
}
