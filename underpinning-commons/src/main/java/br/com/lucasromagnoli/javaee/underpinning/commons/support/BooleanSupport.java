package br.com.lucasromagnoli.javaee.underpinning.commons.support;

public class BooleanSupport {
    private BooleanSupport() {}

    public static <T> T nullValueLogic(T primaryTarget, T secondaryTarget) {
        return primaryTarget != null ? primaryTarget : secondaryTarget;
    }

    public static boolean isNull(Object target) {
        return target == null;
    }
}
