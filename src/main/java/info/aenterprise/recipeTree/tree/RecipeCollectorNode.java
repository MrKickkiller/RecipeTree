package info.aenterprise.recipeTree.tree;

import net.minecraft.item.ItemStack;

/**
 * Created by MrKickkiller on 3/08/2016.
 */
public class RecipeCollectorNode {

    private ItemStack content;
    private int amount;

    public RecipeCollectorNode(ItemStack content) {
        this(content, 1);
    }

    public RecipeCollectorNode(ItemStack node, int count){
        this.amount = count;
        this.content = node;
    }

    private boolean matches(ItemStack checker){
        return checker == content;
    }

    public boolean add(ItemStack toBeChecked, int amount){
        if (this.matches(toBeChecked)){
            this.amount += amount;
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecipeCollectorNode that = (RecipeCollectorNode) o;

        return content != null ? content.equals(that.content) : that.content == null;

    }

    @Override
    public int hashCode() {
        return content != null ? content.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CollectorNode{");
        sb.append("content=").append(content);
        sb.append(", amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }

    public ItemStack getContent() {
        return content;
    }

    public int getAmount() {
        return amount;
    }
}