package info.aenterprise.recipeTree.jei;

import info.aenterprise.recipeTree.tree.RecipeCollectorNode;
import mezz.jei.api.IRecipeRegistry;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeHandler;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static info.aenterprise.recipeTree.jei.Plugin.runtime;

/**
 * Created by MrKickkiller on 5/08/2016.
 */
public class RecipeGrabber {

    public static List<RecipeCollectorNode> getCollectorNodeForItem(ItemStack stack){
        IRecipeRegistry registry = runtime.getRecipeRegistry();

        List<IRecipeCategory> categories = registry.getRecipeCategoriesWithOutput(stack);
        List<Object> collector = new ArrayList<>();
        for (IRecipeCategory cat : categories){
            collector.addAll(registry.getRecipesWithOutput(cat,stack));
        }

        if (collector.isEmpty()){
            return null;
        }
        // TODO: pick a better solution than just the first one.
        Object recipe = collector.get(0);
        IRecipeHandler handler = registry.getRecipeHandler(recipe.getClass());

        List<RecipeCollectorNode> nodes = new ArrayList<>();
        RecipeCollectorNode node;
        for (Object ob: handler.getRecipeWrapper(recipe).getInputs()){
            ItemStack stacky;
            if (ob instanceof ItemStack){
                stacky = (ItemStack) ob;
            } else {
                List<ItemStack> temp = (List<ItemStack>) ob;
                stacky = temp.get(0);
            }

            node = new RecipeCollectorNode(stacky);

            if (nodes.contains(node)){
                int index = nodes.indexOf(node);
                nodes.get(index).add(stacky,1);
            }else {
                nodes.add(node);
            }
        }
        return nodes;
    }

    public static int getAmountCreatedInCrafting(ItemStack content){
        IRecipeRegistry registry = runtime.getRecipeRegistry();
        List<IRecipeCategory> categories = registry.getRecipeCategoriesWithOutput(content);
        List<Object> collector = new ArrayList<>();
        for (IRecipeCategory cat : categories){
            collector.addAll(registry.getRecipesWithOutput(cat,content));
        }

        if (collector.isEmpty()){
            return 0;
        }
        // TODO: pick a better solution than just the first one.
        Object recipe = collector.get(0);
        IRecipeHandler handler = registry.getRecipeHandler(recipe.getClass());
        return handler.getRecipeWrapper(recipe).getOutputs().size();
    }
}
