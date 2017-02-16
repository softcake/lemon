package example;

// tag::exampleDemo[]
/**
 * Demo class
 */
public final class Demo {

    private Demo() {

        throw new IllegalStateException("No instances!");
    }

    public static void checkNotNull(final Object obj) {

        if (obj == null) {
            throw new IllegalArgumentException("Parameter must not be null!");
        }
    }

}
// end::exampleDemo[]
