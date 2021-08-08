package dan.morefurnaces.recipes;

import dan.morefurnaces.FurnaceType;
import dan.morefurnaces.MFLog;
import dan.morefurnaces.MoreFurnaces;
import dan.morefurnaces.items.Upgrades;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.ShapedOreRecipe;

import static dan.morefurnaces.FurnaceType.*;
import static dan.morefurnaces.items.Upgrades.*;

public class Recipes {

    public static void registerFurnaceRecipes() {

        // Netherrack Furnace
        addShapedRecipe("netherrack_furnace", getFurnace(NETHERRACK),
                "NNN", "NFN", "NNN",
                'N', Blocks.NETHERRACK,
                'F', Blocks.FURNACE);

        // Copper Furnace
        addShapedRecipe("copper_furnace", getFurnace(COPPER),
                "CCC", "CFC", "CCC",
                'C', "ingotCopper",
                'F', Blocks.FURNACE);

        // Iron Furnace
        addShapedRecipe("iron_furnace", getFurnace(IRON),
                "III", "IFI", "III",
                'I', "ingotIron",
                'F', Blocks.FURNACE);

        // Silver Furnace
        addShapedRecipe("silver_furnace", getFurnace(SILVER),
                "SSS", "SFS", "SSS",
                'S', "ingotSilver",
                'F', getFurnace(IRON));

        // Gold Furnace
        addShapedRecipe("gold_furnace", getFurnace(GOLD),
                "GGG", "GFG", "GGG",
                'G', "ingotGold",
                'F', getFurnace(IRON));

        // Diamond Furnace
        addShapedRecipe("diamond_furnace", getFurnace(DIAMOND),
                "DDD", "DFD", "DDD",
                'D', Items.DIAMOND,
                'F', getFurnace(GOLD));

        // Obsidian Furnace
        addShapedRecipe("obsidian_furnace", getFurnace(OBSIDIAN),
                "OOO", "OFO", "OOO",
                'O', Blocks.OBSIDIAN,
                'F', getFurnace(DIAMOND));
    }

    public static void registerUpgradeRecipes() {

        // Stone to Netherrack Upgrade
        addShapedRecipe("stone_to_netherrack", getUpgrade(STONE_TO_NETHERRACK),
                "NNN", "NSN", "NNN",
                'N', Blocks.NETHERRACK,
                'S', Blocks.STONE);

        // Stone to Iron Upgrade
        addShapedRecipe("stone_to_iron", getUpgrade(STONE_TO_IRON),
                "III", "ISI", "III",
                'I', "ingotIron",
                'S', Blocks.STONE);

        // Stone to Copper Upgrade
        addShapedRecipe("stone_to_copper", getUpgrade(STONE_TO_COPPER),
                "CCC", "CSC", "CCC",
                'C', "ingotCopper",
                'S', Blocks.STONE);

        // Iron to Silver Upgrade
        addShapedRecipe("iron_to_silver", getUpgrade(IRON_TO_SILVER),
                "SSS", "SIS", "SSS",
                'S', "ingotSilver",
                'I', "nuggetIron");

        // Copper to Silver Upgrade
        addShapedRecipe("copper_to_silver", getUpgrade(COPPER_TO_SILVER),
                "SSS", "SCS", "SSS",
                'S', "ingotSilver",
                'C', "nuggetCopper");

        // Iron to Gold Upgrade
        addShapedRecipe("iron_to_gold", getUpgrade(IRON_TO_GOLD),
                "GGG", "GIG", "GGG",
                'G', "ingotGold",
                'I', "nuggetIron");

        // Iron to Obsidian Upgrade
        addShapedRecipe("iron_to_obsidian", getUpgrade(IRON_TO_OBSIDIAN),
                "OOO", "OIO", "OOO",
                'O', Blocks.OBSIDIAN,
                'I', "nuggetIron");

        // Gold to Diamond Upgrade
        addShapedRecipe("gold_to_diamond", getUpgrade(GOLD_TO_DIAMOND),
                "DDD", "DGD", "DDD",
                'D', Items.DIAMOND,
                'G', "nuggetGold");
    }

    private static void addShapedRecipe(String regName, ItemStack result, Object... recipe) {
        if (result.isEmpty()) {
            MFLog.logger.error("Result cannot be an empty ItemStack. Recipe: {}", regName);
            MFLog.logger.error("Stacktrace:", new IllegalArgumentException());
            return;
        }

        IRecipe shapedOreRecipe = new ShapedOreRecipe(null, result.copy(), recipe)
                .setMirrored(false)
                .setRegistryName(regName);
        ForgeRegistries.RECIPES.register(shapedOreRecipe);
    }

    private static ItemStack getUpgrade(Upgrades type) {
        return new ItemStack(MoreFurnaces.ITEM_UPGRADE, 1, type.ordinal());
    }

    private static ItemStack getFurnace(FurnaceType type) {
        return new ItemStack(MoreFurnaces.BLOCK_FURNACE, 1, type.ordinal());
    }
}
