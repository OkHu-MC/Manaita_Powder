package okuhuku.manita_powder;

import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraft.world.level.Level;

public class PowderRecipe extends CustomRecipe {

    public static final RecipeSerializer<PowderRecipe> SERIALIZER =
            new SimpleCraftingRecipeSerializer<>(PowderRecipe::new);

    public PowderRecipe(ResourceLocation id, CraftingBookCategory category) {
        super(id, category);
    }

    @Override
    public boolean matches(CraftingContainer inv, Level level) {
        int powder = 0, other = 0;
        for (int i = 0; i < inv.getContainerSize(); i++) {
            ItemStack s = inv.getItem(i);
            if (s.isEmpty()) continue;
            if (s.is(Manaita_powder.POWDER.get())) powder++;
            else other++;
        }
        return powder == 1 && other == 1;
    }

    @Override
    public ItemStack assemble(CraftingContainer inv, RegistryAccess access) {
        for (int i = 0; i < inv.getContainerSize(); i++) {
            ItemStack s = inv.getItem(i);
            if (s.isEmpty() || s.is(Manaita_powder.POWDER.get())) continue;
            ItemStack out = s.copy();
            out.setCount(64);
            return out;
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int w, int h) {
        return w * h >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }
}