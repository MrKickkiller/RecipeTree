package info.aenterprise.recipeTree.tree;

import info.aenterprise.recipeTree.jei.RecipeGrabber;
import info.aenterprise.recipeTree.tree.generic.NodeData;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MrKickkiller on 5/08/2016.
 */
public class DataLeaf extends NodeData<List<RecipeCollectorNode>> {

    public DataLeaf(ItemStack stack) {
        super(RecipeGrabber.getCollectorNodeForItem(stack));
    }

    public List<ItemStack> getListOfAllRecipeRequirements(){
        List<ItemStack> recipeReq = new ArrayList<>();
        for (RecipeCollectorNode node : this.getData()){
            recipeReq.add(node.getContent());
        }
        return recipeReq;
    }

}
