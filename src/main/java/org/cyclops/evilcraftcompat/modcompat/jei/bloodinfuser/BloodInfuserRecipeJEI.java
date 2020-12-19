package org.cyclops.evilcraftcompat.modcompat.jei.bloodinfuser;

import mezz.jei.util.Translator;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.fluids.FluidStack;
import org.cyclops.cyclopscore.modcompat.jei.RecipeRegistryJeiRecipeWrapper;
import org.cyclops.evilcraft.RegistryEntries;
import org.cyclops.evilcraft.core.recipe.type.IInventoryFluidTier;
import org.cyclops.evilcraft.core.recipe.type.RecipeBloodInfuser;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Recipe wrapper for Blood Infuser recipes
 * @author rubensworks
 */
public class BloodInfuserRecipeJEI extends RecipeRegistryJeiRecipeWrapper<IInventoryFluidTier, RecipeBloodInfuser, BloodInfuserRecipeJEI> {

    private final FluidStack inputFluid;
    private final int inputTier;
    private final List<ItemStack> inputItems;
    private final ItemStack outputItem;
    private final String xpString;

    public BloodInfuserRecipeJEI(RecipeBloodInfuser recipe) {
        super(RegistryEntries.RECIPETYPE_BLOOD_INFUSER, recipe);
        this.inputFluid = recipe.getInputFluid();
        this.inputTier = recipe.getInputTier();
        this.inputItems = Arrays.stream(recipe.getInputIngredient().getMatchingStacks()).collect(Collectors.toList());
        this.outputItem = recipe.getRecipeOutput();
        this.xpString = Translator.translateToLocalFormatted("gui.jei.category.smelting.experience", recipe.getXp());
    }

    protected BloodInfuserRecipeJEI() {
        super(RegistryEntries.RECIPETYPE_BLOOD_INFUSER, null);
        this.inputFluid = null;
        this.inputTier = -1;
        this.inputItems = null;
        this.outputItem = null;
        this.xpString = null;
    }

    public FluidStack getInputFluid() {
        return inputFluid;
    }

    public int getInputTier() {
        return inputTier;
    }

    public List<ItemStack> getInputItems() {
        return inputItems;
    }

    public ItemStack getOutputItem() {
        return outputItem;
    }

    public String getXpString() {
        return xpString;
    }

    @Override
    protected IRecipeType<RecipeBloodInfuser> getRecipeType() {
        return RegistryEntries.RECIPETYPE_BLOOD_INFUSER;
    }

    @Override
    protected BloodInfuserRecipeJEI newInstance(RecipeBloodInfuser recipe) {
        return new BloodInfuserRecipeJEI(recipe);
    }

    public static Collection<BloodInfuserRecipeJEI> getAllRecipes() {
        return new BloodInfuserRecipeJEI().createAllRecipes()
                .stream()
                .sorted(Comparator.comparing(r -> r.inputTier))
                .collect(Collectors.toList());
    }
}
