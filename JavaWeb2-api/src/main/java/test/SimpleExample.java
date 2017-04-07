package test;

import java.util.ArrayList;
import java.util.List;

import org.trimou.Mustache;
import org.trimou.engine.MustacheEngine;
import org.trimou.engine.MustacheEngineBuilder;
import org.trimou.engine.config.EngineConfigurationKey;
import org.trimou.engine.locator.FileSystemTemplateLocator;
import org.trimou.handlebars.HelpersBuilder;
import org.trimou.handlebars.LogHelper;
import org.trimou.handlebars.LogHelper.Level;

/**
 *
 * @author Martin Kouba
 */
public class SimpleExample {

    public static void main(String[] args) {

        // We will always start like this - building an engine
        MustacheEngineBuilder builder = MustacheEngineBuilder.newBuilder();

        // Add a template locator so that the template contents is automatically
        // loaded
        builder.addTemplateLocator(
        		FileSystemTemplateLocator.builder().setRootPath("C:\\Users\\hebin\\Desktop\\JavaWeb2-api\\src\\main\\java\\test").setSuffix("trimou").build());
                //ClassPathTemplateLocator.builder().setSuffix("trimou").build());

        // Add some extra built-in helpers
        builder.registerHelpers(HelpersBuilder.extra()
                .add("log",
                        LogHelper.builder().setDefaultLevel(Level.WARN).build())
                .build());

        // Global data are available in all templates
        builder.addGlobalData("footer", "© 2014 Trimou team");

        // HTML is not necessary
        builder.setProperty(EngineConfigurationKey.SKIP_VALUE_ESCAPING, true);

        // Now we are ready to build the engine...
        MustacheEngine engine = builder.build();

        // Note that it's not necessary to cache templates - engine has its own
        // cache enabled by default
        Mustache test = engine.getMustache("items");

        // Prepare some data
        List<Item> items = new ArrayList<>();
        items.add(new Item("Foo", 5l, true));
        items.add(new Item("Bar", 15l, true));
        items.add(new Item("Qux", -1l, false));
        items.add(new Item("Baz", 5000l, true));

        System.out.println(
                String.format("Start rendering of %s...", test.getName()));

        long start = System.nanoTime();
        // And now render the data
        String output = test.render(items);
        long end = System.nanoTime() - start;

        System.out.println(String.format("Template %s rendered in %s ms:\n",
                test.getName(), end / 1000000));
        System.out.println(output);
    }

    /**
     *
     * @author Martin Kouba
     */
    public static class Item {

        private final String name;

        private final Long amount;

        private final boolean isActive;

        Item(String name, Long amount, boolean isActive) {
            this.name = name;
            this.amount = amount;
            this.isActive = isActive;
        }

        public String getName() {
            return name;
        }

        public Long getAmount() {
            return amount;
        }

        public boolean isActive() {
            return isActive;
        }

    }

}
