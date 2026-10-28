package okuhuku.manita_powder;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(Manaita_powder.MODID)
public class Manaita_powder {

    public static final String MODID = "manita_powder";

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final RegistryObject<Item> POWDER = ITEMS.register("powder",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<RecipeSerializer<PowderRecipe>> POWDER_RECIPE =
            RECIPES.register("powder_multiply", () -> PowderRecipe.SERIALIZER);

    public static final RegistryObject<CreativeModeTab> TAB = CREATIVE_MODE_TABS.register("tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.manita_powder.tab"))
                    .icon(() -> POWDER.get().getDefaultInstance())
                    .displayItems((parameters, output) -> output.accept(POWDER.get()))
                    .build());

    public Manaita_powder() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(bus);
        RECIPES.register(bus);
        CREATIVE_MODE_TABS.register(bus);
    }
}